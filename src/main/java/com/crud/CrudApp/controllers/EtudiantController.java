package com.crud.CrudApp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.CrudApp.entities.Etudiant;
import com.crud.CrudApp.entities.Sexe;
import com.crud.CrudApp.form.EtudiantForm;
import com.crud.CrudApp.services.IEtudiantService;

@Controller
@RequestMapping(path = "/etudiants")
public class EtudiantController {
	
	private final IEtudiantService etudiantService;
	
	@Autowired
	public EtudiantController(IEtudiantService etudiantService) {
		this.etudiantService = etudiantService;
	}
	
	@GetMapping(path = { "", "/" })
	public String index(
		Model model,
		@RequestParam(name = "page", defaultValue = "0") String p,
		@RequestParam(name = "size", defaultValue = "5") String s,
		@RequestParam(name = "q", defaultValue = "") String q
	) {
		int page, size;
		try {
			page = Integer.parseInt(p);
		} catch (Exception ex) {
			page = 0;
		}
		try {
			size = Integer.parseInt(s);
		} catch (Exception ex) {
			size = 5;
		}
		Page<Etudiant> listEtudiants = etudiantService.getBy(q, page, size);
		
		model.addAttribute("listEtudiants", listEtudiants.getContent());
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("q", q);
		int totalPages = listEtudiants.getTotalPages();
		model.addAttribute("totalPages", new int[totalPages]);
		model.addAttribute("isPrev", page != 0);
		model.addAttribute("isNext", page != totalPages - 1);
		
		return "etudiant/index";
	}
	
	@GetMapping(path = "/ajouter")
	public String create(EtudiantForm etudiantForm) {
		return "etudiant/add";
	}
	@PostMapping(path = "/ajouter")
	public String save(@Valid EtudiantForm etudiantForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "etudiant/add";
		}
		Etudiant etudiant = etudiantService.save(etudiantForm);
		
		return "redirect:/etudiants";
	}
	
	@GetMapping(path = "/modifier/{nom}-{prenom}")
	public String update(
		@PathVariable(name = "nom") String nom,
		@PathVariable(name = "prenom") String prenom,
		EtudiantForm etudiantForm
	) {
		Etudiant etudiant = etudiantService.getByNomAndPrenom(nom, prenom);
		if (etudiant != null) {
			etudiantForm.setId(etudiant.getId());
			etudiantForm.setNom(etudiant.getNom());
			etudiantForm.setPrenom(etudiant.getPrenom());
			etudiantForm.setDateNaissance(etudiant.getDateNaissance());
			etudiantForm.setLieuNaissance(etudiant.getLieuNaissance());
			int s = etudiant.getSexe() == Sexe.homme ? 0 : 1;
			etudiantForm.setSexe(s);
		}
		return "etudiant/edit";
	}
	@PostMapping(path = "/modifier")
	public String edit(@Valid EtudiantForm etudiantForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "etudiant/edit";
		}
		Etudiant etudiant = etudiantService.update(etudiantForm);
		
		return "redirect:/etudiants";
	}
	
	@PostMapping(path = "/supprimer")
	public String delete(@Validated Long id) {
		etudiantService.deleteById(id);
		return "redirect:/etudiants";
	}

}
