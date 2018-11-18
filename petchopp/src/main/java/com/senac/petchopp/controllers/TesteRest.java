package com.senac.petchopp.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.petchopp.model.FormularioSearch;
import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.produto.ProdutoService;

@RestController
@RequestMapping("rest")
public class TesteRest {

	private ProdutoService servico = new ProdutoService();

	@RequestMapping("mostrar")
	public Produto mostrarProduto() {
		Produto teste = servico.searchByCodigo("alalalla");
		return teste;
	}

	@GetMapping("mostrarLista")
	public ArrayList<Produto> listaProdutos() {
		ArrayList<Produto> lista;
		try {
			lista = servico.searchByNome("");
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@GetMapping("formulario")
	public ResponseEntity<FormularioSearch> formulario(@RequestBody FormularioSearch resultado) {
		System.out.println(resultado.toString());
		resultado.setProcura("Texto adicionado no controller");
		return new ResponseEntity<FormularioSearch>(resultado, HttpStatus.OK);
	}
}