package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("paginaexemplo")
	public String paginaExemplo() {
		return "PaginaExemplo";
	}

}
