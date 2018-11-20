package com.senac.petchopp.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.petchopp.model.filtro.Filtro;
import com.senac.petchopp.model.filtro.FiltroService;
import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.produto.ProdutoService;
import com.senac.petchopp.wos.FormularioSearch;

@RestController
@RequestMapping("rest")
public class TesteRest {

	private ProdutoService servico = new ProdutoService();
	private FiltroService filtroService = new FiltroService();

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

	@RequestMapping("filtros")
	public ResponseEntity<Filtro> listaDeFiltros() {
		return new ResponseEntity<Filtro>(filtroService.getAll(), HttpStatus.OK);
	}

	@PostMapping("formulario")
	public String formulario(@RequestBody FormularioSearch resultado) {
		resultado.setProcura("Texto adicionado no controller");
		System.out.println(resultado.toString());
		return resultado.getProcura();
	}
}