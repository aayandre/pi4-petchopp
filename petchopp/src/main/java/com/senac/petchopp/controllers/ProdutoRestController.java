package com.senac.petchopp.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senac.petchopp.model.filtro.FiltroService;
import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.produto.ProdutoService;
import com.senac.petchopp.model.tipo.TipoService;
import com.senac.petchopp.wos.FormularioSearch;

@RestController
@RequestMapping("produtorest")
public class ProdutoRestController {

	private ProdutoService produtoServico = new ProdutoService();
	private FiltroService filtroService = new FiltroService();

	@RequestMapping("tipo/{id}")
	public List<Produto> listProdutosTipo(@PathVariable("id") String idTipo) {
		List<Produto> lista = new ArrayList<>();
		System.out.println(idTipo);
		try {
			lista = produtoServico.listyTipoId(idTipo);
			return lista;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("formulario")
	public List<Produto> formulario(@RequestBody FormularioSearch procura) {
		List<Produto> lista = new ArrayList<>();
		try {
			lista = produtoServico.searchByFormularioSearch(procura);
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
