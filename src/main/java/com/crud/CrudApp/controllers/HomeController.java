package com.crud.CrudApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(path = { "/", "" })
	private String index() {
		return "redirect:/etudiants";
	}

}
