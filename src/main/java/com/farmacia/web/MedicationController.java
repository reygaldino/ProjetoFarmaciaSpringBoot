package com.farmacia.web;

import java.util.List;

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

import com.farmacia.model.Medication;
import com.farmacia.model.TypeMedication;
import com.farmacia.repository.MedicationRepository;
import com.farmacia.repository.TypeMedicationRepository;
import com.farmacia.service.MedicationService;

@Controller
@RequestMapping("/medicamento")
public class MedicationController {
	
	@Autowired
	private MedicationRepository medicationRepository;
	
	@Autowired
	private MedicationService medicationService;
	
	@Autowired
	private TypeMedicationRepository typeMedicationRepository;
	
	@GetMapping("/novo")
	public String medicamentoCadastrar(@ModelAttribute("medication") Medication medication) {
		return "medicamento/medicamentoCadastrar";
	}
	
	@PostMapping("/salvar")
	public String medicamentoSalvar(@Valid @ModelAttribute("medication") Medication medication, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "medicamento/medicamentoCadastrar";
		}
		medicationRepository.save(medication);
		attr.addAttribute("message", "Medicamento salvo com sucesso.");
		return "redirect:/medicamento/novo";
	}

	@GetMapping("/buscar")
	public ModelAndView buscar(ModelMap model) {
		model.addAttribute("medication" , medicationRepository.findAll());
		return new ModelAndView("medicamento/medicamentoBuscar",model);
	}
	
	@GetMapping("/excluir")
	public ModelAndView buscarExcluiur(ModelMap model) {
		model.addAttribute("medication" , medicationRepository.findAll());
		return new ModelAndView("medicamento/medicamentoExcluir",model);
	}
	
	@GetMapping("/excluir/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
		medicationRepository.deleteById(id);
	 	attr.addFlashAttribute("message", "Medicamento removido com sucesso");
	 	return "redirect:/medicamento/excluir";
	 	}
	
	@GetMapping("/alterar")
	public String buscarAlterar(Model model) {
		model.addAttribute("medication" , medicationRepository.findAll());
		return "/medicamento/medicamentoAlterar";
	}
	
	@GetMapping("/alterar/{id}")
	public String editar(@PathVariable("id") Long id, Model model){
		model.addAttribute("medication" , medicationService.findById(id));
		return "medicamento/medicamentoCadastrar";
	}
	@ModelAttribute("typeMedication")
	public List<TypeMedication> buscarMedicamentos(){
		return typeMedicationRepository.findAll();
	}
}
