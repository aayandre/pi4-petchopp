package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.model.cliente.Cliente;

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	@GetMapping("conta")
	public String minhaConta() {
		return "cliente-index";
	}
	
	@GetMapping("Cadastro")
	public ModelAndView novoCliente() {
		ModelAndView modelAndView = new ModelAndView("novoCliente");
		modelAndView.addObject("cliente", new Cliente());
		System.out.println("Entrando em CadastroCliente");
		return modelAndView;
	}
		
}
