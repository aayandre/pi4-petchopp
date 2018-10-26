package com.senac.petchopp.controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.daos.ProdutoDAO;
import com.senac.petchopp.daos.UsuarioDAO;
import com.senac.petchopp.model.TipoCategoria;
import com.senac.petchopp.model.Upload;
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

	// Formulario
	@GetMapping("/formularioprod")
	public ModelAndView formulario() {
		return new ModelAndView("testeCadastro").addObject("produto", new Produto());
	}

	// Controller que recebe o codigo do produto pela url,
	// utiliza o mesmo para procurar no banco
	// e no fim adiciona o resultado da pesquisa na pagina "testes" e a mostra
	@GetMapping("/getprodutoteste/{codigo}")
	public ModelAndView testeGetProduto(@PathVariable String codigo) {
		ProdutoDAO produtoBanco = new ProdutoDAO();
		Produto adquirido = (Produto) produtoBanco.getByCodigo(codigo);
		return new ModelAndView("testes").addObject("produto", adquirido);
	}

	// Controller que salva o produto recebido no banco,
	// redireciona para o controller que visualiza o produto adicionado
	@PostMapping("/addprodutoteste")
	public ModelAndView testeAddProduto(@ModelAttribute("produto") Produto novo,
			@RequestParam("arquivo") MultipartFile arquivo) {
		// Instanciando o DAO para poder usar o método não static salvar()
		ProdutoDAO adicionar = new ProdutoDAO();
		try {
			Upload.salvar(arquivo);
			novo.setUrlImagem(arquivo.getOriginalFilename());
			adicionar.salvar(novo);
			return new ModelAndView("redirect:/testes/getprodutoteste/" + novo.getCodigo());
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			// Isso é um teste
			return new ModelAndView("redirect:/erro").addObject("msgErro", "Erro ao salvar produto.");
		}
	}

}
