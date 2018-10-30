package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.daos.ProdutoDAO;
import com.senac.petchopp.model.produto.Produto;

@Controller
@RequestMapping("produto")
public class ProdutoController {

	// Create
	@PostMapping("/new")
	public String novoProduto(@ModelAttribute("produto") Produto p) {
		return "produto/" + p.getCodigo();
	}

	// Get
	@GetMapping("/{codigo}")
	public ModelAndView detalhesProduto(@PathVariable("codigo") String codigo) {
		ProdutoDAO produtoBanco = new ProdutoDAO();
		Produto adquirido = (Produto) produtoBanco.getByCodigo(codigo);
		return new ModelAndView("detalhe").addObject("produto", adquirido);
	}

	// Delete
	@DeleteMapping("/desabilitar/{codigo}")
	public ModelAndView desativarProduto(@PathVariable("codigo") String codigo) {
		ProdutoDAO produtoBanco = new ProdutoDAO();
		produtoBanco.deletar(codigo);
		// TODO criar pagina de erro com uma div que recebe a menssagem do erro que ocorreu
		return new ModelAndView("/");
	}

	// Put(Update)
	@PutMapping("/alterar/{codigo}")
	public ModelAndView alterarProduto(@PathVariable("codigo") String codigo, @ModelAttribute("produto") Produto alterado) {
		ProdutoDAO produtoBanco = new ProdutoDAO();
		produtoBanco.atualizar(alterado);
		return new ModelAndView("redirect:/produto/" + codigo);
	}
}
