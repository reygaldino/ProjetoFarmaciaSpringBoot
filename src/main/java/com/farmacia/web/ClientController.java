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

import com.farmacia.model.Client;
import com.farmacia.repository.ClientRepository;

@Controller
@RequestMapping("/cliente")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/novo")
	public String clienteCadastrar(@ModelAttribute("client") Client client) {
		return "cliente/clienteCadastrar";
	}
	
	@PostMapping("/salvar")
	public String clienteSalvar(@Valid @ModelAttribute("client") Client client, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "cliente/clienteCadastrar";
		}
		clientRepository.save(client);
		attr.addAttribute("message", "Cliente salvo com sucesso.");
		return "redirect:/cliente/novo";
	}
	
	@GetMapping("/buscar")
	public ModelAndView buscar(ModelMap model) {
		model.addAttribute("client" , clientRepository.findAll());
		return new ModelAndView("cliente/clienteBuscar", model);
	}
	
	@GetMapping("/excluir")
	public ModelAndView listar(ModelMap model) {
		model.addAttribute("client",clientRepository.findAll());
		return new ModelAndView("cliente/clienteExcluir",model);
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		clientRepository.deleteById(id);
		attr.addFlashAttribute("message", "Cliente removido com sucesso");
		return "redirect:/cliente/excluir";
	}
	
	@GetMapping("/alterar")
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("client" , clientRepository.findAll());
		return new ModelAndView("cliente/clienteAlterar",model);
	}
	
	@GetMapping("/alterar/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("client",clientRepository.findById(id));
		return "cliente/clienteCadastrar";
	}
}
