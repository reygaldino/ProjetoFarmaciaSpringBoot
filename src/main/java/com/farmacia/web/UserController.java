package com.farmacia.web;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.farmacia.model.User;
import com.farmacia.repository.UserRepository;
import com.farmacia.service.SecurityService;
import com.farmacia.service.UserService;
import com.farmacia.validator.UserValidator;

@Controller
@RequestMapping("/funcionario")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private UserValidator userValidater;
	
	// Buscar Funcionarios
	@GetMapping("/buscar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("user", userRepository.findAll());
        return new ModelAndView("funcionario/funcionarioBuscar", model);
    }
	
	// pré cadastra
	@GetMapping("/novo")
    public String funcionarioCadastrar(@ModelAttribute("user") User user) {
        return "funcionario/funcionarioCadastrar";
    }
	// Cadastrar
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "funcionario/funcionarioCadastrar";
        }
        userService.save(user);
        attr.addFlashAttribute("message", "Funcionario salvo com sucesso.");
        return "redirect:/funcionario/novo";
    }
    
    @GetMapping("/excluir")
    public ModelAndView buscar(ModelMap model) {
        model.addAttribute("user", userRepository.findAll());
        return new ModelAndView("funcionario/funcionarioExcluir", model);
    }
    
    @GetMapping("/excluir/{id}")
 	public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
 		userRepository.deleteById(id);
 		attr.addFlashAttribute("message", "Usuario removido com sucesso");
 		return "redirect:/funcionario/excluir";
 	}
    
    @GetMapping("/alterar")
    public String lista(Model model) {
        model.addAttribute("user", userRepository.findAll());
        return "/funcionario/funcionarioAlterar";
    }
    
    @GetMapping("/alterar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("user" , userService.findById(id));
		return "funcionario/funcionarioCadastrar";
    }
	
}
