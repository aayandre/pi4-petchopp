package com.senac.petchopp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.produto.ProdutoService;

@RestController
@RequestMapping("rest")
public class TesteRest {

	private ProdutoService servico;

	@RequestMapping("mostrar")
	public Produto mostrarProduto() {
		Produto teste = servico.searchByCodigo("alalalla");
		return teste;
	}

}