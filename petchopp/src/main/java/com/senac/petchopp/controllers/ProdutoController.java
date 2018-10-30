package com.senac.petchopp.controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.daos.ProdutoDAO;
import com.senac.petchopp.model.Upload;
import com.senac.petchopp.model.produto.Produto;

@Controller
@RequestMapping("produto")
public class ProdutoController {

	private ProdutoDAO produtoBanco = new ProdutoDAO();

	// Create
	@PostMapping("/new")
	public ModelAndView novoProduto(@ModelAttribute("produto") Produto novo,
			@RequestParam("arquivo") MultipartFile arquivo) {
		try {
			Upload.salvar(arquivo);
			novo.setUrlImagem(arquivo.getOriginalFilename());
			produtoBanco.salvar(novo);
		} catch (IOException | SQLException e) {
			// TODO Criar pagina de erro para mostrar aqui
			e.printStackTrace();
		}
		return new ModelAndView("produto/" + novo.getCodigo());
	}

	// Get
	@GetMapping("/{codigo}")
	public ModelAndView detalhesProduto(@PathVariable("codigo") String codigo) {
		Produto adquirido = (Produto) produtoBanco.getByCodigo(codigo);
		return new ModelAndView("detalhe").addObject("produto", adquirido);
	}

	// Delete
	@DeleteMapping("/desabilitar/{codigo}")
	public ModelAndView desativarProduto(@PathVariable("codigo") String codigo) {
		produtoBanco.deletar(codigo);
		// TODO criar pagina de erro com uma div que recebe a menssagem do erro que
		// ocorreu
		return new ModelAndView("/");
	}

	// Put(Update)
	@PutMapping("/alterar/{codigo}")
	public ModelAndView alterarProduto(@PathVariable("codigo") String codigo,
			@ModelAttribute("produto") Produto alterado) {
		produtoBanco.atualizar(alterado);
		return new ModelAndView("redirect:/produto/" + codigo);
	}

	// Get Formulario
	@GetMapping("/formulario/{codigo}")
	public ModelAndView formularioProduto(@PathVariable("codigo") String codigo) {
		Produto selecionado = (Produto) produtoBanco.getByCodigo(codigo);
		return new ModelAndView("produto/formulario").addObject("produto", selecionado);
	}
}
