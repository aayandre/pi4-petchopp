package com.senac.petchopp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.model.cliente.Cliente;
import com.senac.petchopp.model.produto.ProdutoService;
import com.senac.petchopp.wos.FormularioProduto;

@Controller
@RequestMapping("dash")
public class DashboardController {

	private ProdutoService produtoService = new ProdutoService();

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
	public ModelAndView novoProdutoForm(HttpServletRequest request) {
		String msg;
		try {
			FormularioProduto formProd = produtoService.populaFormularioProduto();
			return new ModelAndView("/dashboard/dashboard-produto").addObject("liActivator", "liNewProdId")
					.addObject("formularioProduto", formProd);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Erro ao criar o FormularioProduto.";
			return new ModelAndView("/dashboard/dashboard-produto").addObject("falha", msg);
		}

	}

}
