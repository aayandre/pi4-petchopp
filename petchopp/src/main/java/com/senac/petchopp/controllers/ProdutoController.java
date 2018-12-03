package com.senac.petchopp.controllers;

import java.sql.SQLException;
import java.util.List;

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
			@RequestParam("arquivo") MultipartFile arquivo) {
		String msg;
		try {
			if (novo.getProduto().getIdProduto() != null) {
				produtoService.updateProduto(novo);
			} else {
				produtoService.saveProduto(arquivo, novo);
			}
			msg = "Produto salvo com sucesso!";
			novo.setProduto(produtoService.searchByCodigo(novo.getProduto().getCodigo()));
			return new ModelAndView("redirect:/dash/novoproduto").addObject("sucesso", msg)
					.addObject("formularioProduto", novo);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Erro ao salvar produto.";
			return new ModelAndView("redirect:/dash/novoproduto").addObject("falha", msg).addObject("formularioProduto",
					novo);
		}
	}

	// Get (codigo)
	@GetMapping("/{codigo}")
	public ModelAndView detalhesProduto(@PathVariable("codigo") String codigo) {
		return new ModelAndView("detalhe").addObject("produto", produtoService.searchByCodigo(codigo));
	}

	// Get (nome)
	public ModelAndView procuraProdutos(@ModelAttribute("procura") String procura) {
		try {
			List<Produto> resultados = produtoService.searchByNome(procura);
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
		produtoService.disableProduto(codigo);
		// TODO criar pagina de erro com uma div que recebe a menssagem do erro que
		// ocorreu
		return new ModelAndView("/");
	}

	// Put(Update)
	@PutMapping("/alterar/{codigo}")
	public ModelAndView alterarProduto(@PathVariable("codigo") String codigo,
			@ModelAttribute("produto") FormularioProduto alterado) {
		produtoService.updateProduto(alterado);
		return new ModelAndView("redirect:/produto/" + codigo);
	}

	// Get Formulario
	@GetMapping("/formulario/{codigo}")
	public ModelAndView formularioProduto(@PathVariable("codigo") String codigo) {
		return new ModelAndView("produto/formulario").addObject("produto", produtoService.searchByCodigo(codigo));
	}

	// Get produtos de tal tipo
	@GetMapping("tipos/{descricao}")
	public ModelAndView produtosDoTipo(@PathVariable("descricao") String descricao) {
		try {
			Tipo adiquirido = tipoService.getByDescricao(descricao);
			return new ModelAndView("tipo").addObject("tipo", adiquirido).addObject("titulo",
					adiquirido.getDescricao());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
