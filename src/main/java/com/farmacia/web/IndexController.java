package com.farmacia.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout){
		if (error != null) {
			model.addAttribute("message", "Login e/ou senha invalidos");
		}
		if (logout != null) {
			model.addAttribute("message", "Logout realizado com sucesso!");
		}
        return "login";
    }
	
	@GetMapping("/")
    public ModelAndView inicio() {
        return new ModelAndView("index");
    }
}
