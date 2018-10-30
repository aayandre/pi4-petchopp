package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	@GetMapping("/Cadastro")
	public String novoCliente() {
		return "novoCliente";
	}
	
	 
	
}
