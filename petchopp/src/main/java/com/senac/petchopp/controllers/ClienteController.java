package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.model.cliente.Cliente;

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	@GetMapping("Cadastro")
	public String novoCliente() {
		Cliente cli = new Cliente();
		return "cliente-index"; //
	}
	
	@GetMapping("NCadastro")
	public ModelAndView formCliente() {
		return new ModelAndView("novoCliente").addObject("cliente", new Cliente());
	}
	
	 
	
}
