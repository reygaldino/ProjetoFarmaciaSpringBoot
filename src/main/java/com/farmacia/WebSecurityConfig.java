package com.farmacia;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
    private DataSource dataSource;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{		
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
        	.ignoring()
            .antMatchers("/img/**", "/css/**", "/js/**", "/scss/**", "/vendor/**");
    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{            
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/funcionario/**").hasAnyRole("ADMIN")
			.antMatchers("/medicamento/excluir").hasAnyRole("ADMIN")
			.antMatchers("/cliente/excluir").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception{
		 return authenticationManager();
	}
	
	PersistentTokenRepository persistentTokenRepository(){
	     JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
	     tokenRepositoryImpl.setDataSource(dataSource);
	     return tokenRepositoryImpl;
	    }
	
	/*@Bean
	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
	    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.addDialect(new SpringSecurityDialect());
        templateEngine.setTemplateResolver(templateResolver);
	    return templateEngine;
	}*/
}
