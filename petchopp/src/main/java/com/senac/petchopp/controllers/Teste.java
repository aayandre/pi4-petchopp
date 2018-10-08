package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.daos.ProdutoDAO;
import com.senac.petchopp.daos.UsuarioDAO;
import com.senac.petchopp.model.TipoCategoria;
import com.senac.petchopp.model.produto.Produto;

@Controller
@RequestMapping("/testes")
public class Teste {

	@PostMapping("/tipocad")
	public ModelAndView insertDBTeste(@ModelAttribute("tipoCategoria") TipoCategoria tc) {

		UsuarioDAO.teste(tc);

		TipoCategoria resultado = UsuarioDAO.testeGet();

		return new ModelAndView("testeVisualizacao").addObject("resultado", resultado);

	}

	@GetMapping("/mostraprodutoteste")
	public ModelAndView mostrarProduto() {
		Produto teste = new Produto(true);
		return new ModelAndView("testes").addObject("produto", teste);
	}

	@GetMapping("/getprodutoteste/{codigo}")
	public ModelAndView testeGetProduto(@PathVariable String codigo) {
		ProdutoDAO produtoBanco = new ProdutoDAO();
		Produto adquirido = (Produto) produtoBanco.getByCodigo(codigo);
		return new ModelAndView("testes").addObject("produto", adquirido);
	}

	@RequestMapping("/addprodutoteste")
	public ModelAndView testeAddProduto(@ModelAttribute("produto") Produto novo) {
		novo = new Produto(true);
		// Instanciando o DAO para poder usar o método não static salvar()
		ProdutoDAO adicionar = new ProdutoDAO();
		adicionar.salvar(novo);
		return new ModelAndView("redirect:/testes/getprodutoteste/" + novo.getCodigo());
	}

}
