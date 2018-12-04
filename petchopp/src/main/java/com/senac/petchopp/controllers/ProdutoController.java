package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.senac.petchopp.model.tipo.Tipo;
import com.senac.petchopp.model.tipo.TipoService;
import com.senac.petchopp.wos.FormularioProduto;

@Controller
@RequestMapping("produto")
public class ProdutoController {

	private ProdutoService produtoService = new ProdutoService();
	private TipoService tipoService = new TipoService();

	// Create
	@PostMapping("/new")
	public ModelAndView novoProduto(@ModelAttribute("formularioProduto") FormularioProduto novo,
			@RequestParam("arquivo") MultipartFile arquivo, ModelMap model) {
		String msg;
		try {
			if (novo.getProduto().getIdProduto() != null) {
				produtoService.updateProduto(novo);
			} else {
				produtoService.saveProduto(arquivo, novo);
			}
			msg = "Produto salvo com sucesso!";
			novo.setProduto(produtoService.searchByCodigo(novo.getProduto().getCodigo()));

			model.addAttribute("sucesso", msg);
			model.addAttribute("formularioProduto", novo);
			return new ModelAndView("redirect:/dash/novoproduto", model);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Erro ao salvar produto.";

			model.addAttribute("falha", msg);
			model.addAttribute("formularioProduto", novo);
			return new ModelAndView("redirect:/dash/novoproduto", model);
		}
	}

	// Get (codigo)
	@GetMapping("/{codigo}")
	public ModelAndView detalhesProduto(@PathVariable("codigo") String codigo) {
		Produto produto = produtoService.searchByCodigo(codigo);
		return new ModelAndView("detalhe").addObject("produto", produto).addObject("titulo", produto.getNome());
	}

	// Put(Update)
	@PutMapping("/alterar/{codigo}")
	public ModelAndView alterarProduto(@PathVariable("codigo") String codigo,
			@ModelAttribute("produto") FormularioProduto alterado) {
		produtoService.updateProduto(alterado);
		return new ModelAndView("redirect:/produto/" + codigo);
	}

	// Get produtos de tal tipo
	@GetMapping("tipos/{descricao}")
	public ModelAndView produtosDoTipo(@PathVariable("descricao") String descricao) {
		try {
			// Os produtos são obtidos pelo JAVASCRIPT no caminho do /produtorest
			Tipo adiquirido = tipoService.getByDescricao(descricao);
			return new ModelAndView("tipo").addObject("tipo", adiquirido).addObject("titulo", adiquirido.getNomeView())
					.addObject("titulo", adiquirido.getNomeView());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
