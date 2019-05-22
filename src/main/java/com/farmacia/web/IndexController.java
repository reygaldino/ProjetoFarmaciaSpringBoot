package com.farmacia.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/")
	public String inicio() {
		return "index";
	}

	// Método logar no sistema
		@GetMapping({ "/", "/login" })
		public String login(Model model, String error, String logout) {
			if (error != null) {
				model.addAttribute("message", "Login e/ou senha invalidos");
			}else {
				model.addAttribute("message", "Login realizado com sucesso,Bem vindo!");
			}
			if (logout != null) {
				model.addAttribute("message", "Logout realizado com sucesso!");
			}
			System.out.println(error);
			return "login";
		}
}
