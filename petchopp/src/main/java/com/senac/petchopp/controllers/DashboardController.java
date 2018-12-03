package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.model.cliente.Cliente;
import com.senac.petchopp.model.produto.Produto;

@Controller
@RequestMapping("dash")
public class DashboardController {

	@RequestMapping("")
	public ModelAndView telaLogin() {
		return new ModelAndView("/dashboard/dashboard-login");
	}

	@PostMapping("logon")
	public ModelAndView logon(@RequestParam("email") String email, @RequestParam("senha") String senha) {
		// TODO Lógica de verificar se é admin
		System.out.println(email + "\n" + senha);
		return new ModelAndView("redirect:home").addObject("cliente", new Cliente());
	}

	@RequestMapping("home")
	public ModelAndView home(@ModelAttribute("cliente") Cliente admin) {
		admin.setNome("Leonildo"); // pro teste
		if (!admin.getNome().equalsIgnoreCase("")) {
			return new ModelAndView("/dashboard/dashboard-home").addObject("admin", admin);
		} else {
			return new ModelAndView("erro");
		}
	}

	@RequestMapping("/novoproduto")
	public ModelAndView novoProdutoForm() {
		return new ModelAndView("/dashboard/dashboard-produto").addObject("liActivator", "liNewProdId")
				.addObject("produto", new Produto());
	}

}
