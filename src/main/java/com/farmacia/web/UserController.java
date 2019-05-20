package com.farmacia.web;

import java.util.Optional;

import javax.sound.midi.Soundbank;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	// Método logar no sistema
	@GetMapping({ "/", "/login" })
	public String login(Model model, String error, String logout) {
		if (error != null) {
			model.addAttribute("error", "Login e/ou senha invalidos");
		}else {
			model.addAttribute("message", "Login realizado com sucesso,Bem vindo!");
		}
		if (logout != null) {
			model.addAttribute("message", "Logout realizado com sucesso!");
		}
		return "login";
	}
	// Buscar Funcionarios
	@GetMapping("/funcionarioBuscar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("user", userRepository.findAll());
        return new ModelAndView("/funcionarioBuscar", model);
    }
	// pré cadastra
	@GetMapping("/funcionarioCadastrar")
    public String funcionarioCadastrar(@ModelAttribute("user") User user) {
        return "/funcionarioCadastrar";
    }
	// Cadastrar
    @PostMapping("/funcionarioCadastrar")
    public String salvar(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/funcionarioCadastrar";
        }
 
        userService.save(user);
        attr.addFlashAttribute("message", "Funcionario salvo com sucesso.");
        return "redirect:/funcionarioCadastrar";
    }
    
    // Método que buscar funcionarios para poder excluir
 	@GetMapping("/funcionarioExcluir")
 	public ModelAndView buscarFuncionarioExcluir() {
 		ModelAndView mv = new ModelAndView("/funcionarioExcluir");
 		mv.addObject("user", userRepository.findAll());
 		return mv;
 	}

 	// Método que exclui funcionarios
 	@GetMapping("/funcionarioExcluir/{id}")
 	public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
 		userRepository.deleteById(id);
 		attr.addFlashAttribute("message", "Usuario removido com sucesso");
 		return "redirect:/funcionarioExcluir";
 	}
 	
 	//Método que buscar funcionarios para poder Alterar
 	@GetMapping("/funcionarioAlterar")
 	public ModelAndView buscarFuncionarioAlterar() {
 		ModelAndView mv = new ModelAndView("/funcionarioAlterar");
 		mv.addObject("user", userRepository.findAll());
 		return mv;
 	}
 	
 	// Metodo pré Alterar
 	@RequestMapping("/funcionarioAlterar/{id}")
 	public ModelAndView atualiza(@PathVariable("id") long id, ModelMap model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return new ModelAndView("/funcionarioCadastrar", model);
	}
 	
 	@PutMapping("/salvar")
 	public String atualizar(@Valid @ModelAttribute("user") User user, BindingResult result,RedirectAttributes attr){
 		if(result.hasErrors()) {
 			return "/funcionarioCadastrar";
 		}
 		userService.save(user);
 		attr.addFlashAttribute("message", "Funcionario atualizado com sucesso.");
 		return "redirect:/funcionarioAlterar";
 	}
	
}
