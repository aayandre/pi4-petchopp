package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.model.carrinho.Carrinho;
import com.senac.petchopp.model.cliente.Cliente;

@Controller
@SessionAttributes({ "cliente", "carrinho" })
public class MainController {

	@RequestMapping({ "/", "" })
	public String homePage(Model model, @ModelAttribute("cliente") Cliente cliente) {
		model.addAttribute("cliente", cliente);
		System.out.println(cliente.isLogado());
		return "index";
	}

	@RequestMapping("search")
	public String searchPage(Model model, @ModelAttribute("cliente") Cliente cliente) {
		model.addAttribute("cliente", cliente);
		return "search";
	}

	@RequestMapping("cart")
	public ModelAndView cartPage(@ModelAttribute("carrinho") Carrinho carrinho
                                , @ModelAttribute("cliente") Cliente cliente) {
		return new ModelAndView("cart").addObject("carrinho", carrinho);
	}

	@RequestMapping("login")
	public String loginPage() {
		return "cli/login";
	}

	@ModelAttribute("cliente")
	public Cliente cliente() {
		return new Cliente();
	}

	@ModelAttribute("carrinho")
	public Carrinho carrinho() {
		return new Carrinho();
	}

}
