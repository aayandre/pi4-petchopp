package com.senac.petchopp.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.petchopp.model.filtro.Filtro;
import com.senac.petchopp.model.filtro.FiltroService;
import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.produto.ProdutoService;
import com.senac.petchopp.model.tipo.Tipo;
import com.senac.petchopp.model.tipo.TipoService;
import com.senac.petchopp.wos.FormularioSearch;

@RestController
@RequestMapping("rest")
public class TesteRest {

	private ProdutoService servico = new ProdutoService();
	private FiltroService filtroService = new FiltroService();
	private TipoService tiposervice = new TipoService();

	@RequestMapping("mostrar")
	public Produto mostrarProduto() {
		Produto teste = servico.searchByCodigo("alalalla");
		return teste;
	}

	@GetMapping("mostrarLista")
	public List<Produto> listaProdutos() {
		List<Produto> lista;
		try {
			lista = servico.searchByNome("");
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@RequestMapping("filtros")
	public ResponseEntity<Filtro> listaDeFiltros() {
		return new ResponseEntity<Filtro>(filtroService.getAll(), HttpStatus.OK);
	}

	@RequestMapping("formulario")
	public List<Produto> formulario(@RequestBody FormularioSearch resultado) {
		List<Produto> lista;
		try {
			lista = servico.searchByNome("");
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@RequestMapping("tipos")
	public List<Tipo> listaTipos() {
		List<Tipo> tipos;
		try {
			tipos = tiposervice.getAllTiposProdutos();
			return tipos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}