package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.senac.petchopp.model.produto.Produto;

@Controller
@RequestMapping("produto")
public class ProdutoController {

	//Create
	@PostMapping("/new")
	public String novoProduto(@ModelAttribute("produto") Produto p) {
		return "produto/" + p.getCodigo();
	}
	
	//Get
	@GetMapping("/{codigo}")
	public String detalhesProduto(@PathVariable String codigo) {
		//TODO metodo para buscar o produto por codigo
		return null;
	}
	
	//Delete
	
	//Put(Update)
}
