package com.farmacia.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.farmacia.model.User;
import com.farmacia.repository.UserRepository;
import com.farmacia.service.SecurityService;
import com.farmacia.service.UserService;
import com.farmacia.validator.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private UserValidator userValidater;
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null) {
			model.addAttribute("error", "Login e/ou senha invalidos");
			System.out.println("Burro");
		}
		if (logout != null) {
			model.addAttribute("message" , "Logout realizado com sucesso!");
			System.out.println("OK");
		}
		return "login";
	}
	
	@GetMapping("/funcionarioCadastrar")
	public String form(Model model) {
		model.addAttribute("funcionarioCadastrar", new User());
		
		return "funcionarioCadastrar";
	}
	
	@PostMapping("/funcionarioCadastrar")
	public String from(@ModelAttribute("funcionarioCadastrar") User userForm, BindingResult bindingResult) {
		userValidater.validate(userForm, bindingResult); 
		
		if (bindingResult.hasErrors()) {
			return "/funcionarioCadastrar";
		}
		
		userService.save(userForm);
		securityService.autoLogin(userForm.getUsername(), userForm.getPassword());
		return "redirect:/funcionarioCadastrar";
	}
	
	@GetMapping("/funcionarioBuscar")
	public ModelAndView  buscar() {
		ModelAndView mv = new ModelAndView("funcionarioBuscar");
		Iterable<User> user = userRepository.findAll();
		mv.addObject("user",user);
		return mv;
	}
	
	@GetMapping("/funcionarioExcluir")
	public ModelAndView buscarFuncionario() {
		ModelAndView mv = new ModelAndView("funcionarioExcluir");
		Iterable<User> user = userRepository.findAll();
		mv.addObject("user",user);
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public String excluirFuncionario(long id) {
		userRepository.deleteById(id);
		return "redirect:/funcionarioExcluir";
		
	}
}
