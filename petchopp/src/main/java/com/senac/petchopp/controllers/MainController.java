package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.model.TipoCategoria;

@Controller
public class MainController {

	@RequestMapping("/")
	public String homePage() {
		return "index";
	}

	@RequestMapping("search")
	public String searchPage() {
		return "search";
	}

	@RequestMapping("cart")
	public String cartPage() {
		return "cart";
	}

	@GetMapping("teste")
	public ModelAndView paginaExemplo() {
		return new ModelAndView("testeCadastro").addObject("tipoCategoria", new TipoCategoria());
	}

}
