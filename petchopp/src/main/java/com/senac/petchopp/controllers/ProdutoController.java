package com.senac.petchopp.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

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

import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.produto.ProdutoService;

@Controller
@RequestMapping("produto")
public class ProdutoController {

	private ProdutoService servico = new ProdutoService();

	// Create
	@PostMapping("/new")
	public ModelAndView novoProduto(@ModelAttribute("produto") Produto novo,
			@RequestParam("arquivo") MultipartFile arquivo) {
		try {
			servico.saveProduto(arquivo, novo);
		} catch (Exception e) {
			// TODO Mostrar pagina de erro com uma menssagem personalizada
			e.printStackTrace();
		}
		return new ModelAndView("produto/" + novo.getCodigo());
	}

	// Get (codigo)
	@GetMapping("/{codigo}")
	public ModelAndView detalhesProduto(@PathVariable("codigo") String codigo) {
		return new ModelAndView("detalhe").addObject("produto", servico.searchByCodigo(codigo));
	}

	// Get (nome)
	public ModelAndView procuraProdutos(@ModelAttribute("procura") String procura) {
		try {
			ArrayList<Produto> resultados = servico.searchByNome(procura);
			return new ModelAndView("search").addObject("resultados", resultados);
		} catch (SQLException e) {
			// TODO Caso de erro
			e.printStackTrace();
			String msg = "Produto não encontrado.";
			return new ModelAndView("search").addObject("msg", msg);
		}

	}

	// Delete
	@DeleteMapping("/desabilitar/{codigo}")
	public ModelAndView desativarProduto(@PathVariable("codigo") String codigo) {
		servico.disableProduto(codigo);
		// TODO criar pagina de erro com uma div que recebe a menssagem do erro que
		// ocorreu
		return new ModelAndView("/");
	}

	// Put(Update)
	@PutMapping("/alterar/{codigo}")
	public ModelAndView alterarProduto(@PathVariable("codigo") String codigo,
			@ModelAttribute("produto") Produto alterado) {
		servico.updateProduto(alterado);
		return new ModelAndView("redirect:/produto/" + codigo);
	}

	// Get Formulario
	@GetMapping("/formulario/{codigo}")
	public ModelAndView formularioProduto(@PathVariable("codigo") String codigo) {
		return new ModelAndView("produto/formulario").addObject("produto", servico.searchByCodigo(codigo));
	}

}
