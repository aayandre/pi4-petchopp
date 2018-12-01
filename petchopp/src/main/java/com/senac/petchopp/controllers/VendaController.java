package com.senac.petchopp.controllers;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.daos.ProdutoDAO;
import com.senac.petchopp.daos.VendaDAO;
import com.senac.petchopp.model.carrinho.Carrinho;
import com.senac.petchopp.model.cliente.Cliente;
import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.produto.ProdutoVenda;
import com.senac.petchopp.model.venda.Venda;

@Controller
@RequestMapping("checkout")
@SessionAttributes({ "cliente", "carrinho" })
public class VendaController {

	private VendaDAO vendaBanco = new VendaDAO();
	private ProdutoDAO produtoBanco = new ProdutoDAO();

//	@GetMapping("")
//	public ModelAndView carrinho(@ModelAttribute("carrinho") Carrinho carrinho) {
//		carrinho.setProdutos(new ProdutoDAO().testeCarrinho(3));
//		return new ModelAndView("cart").addObject("carrinho", carrinho);
//	}

	@RequestMapping("formulario")
	public ModelAndView formVenda(@ModelAttribute("carrinho") Carrinho carrinho) {
		/*
		 * TODO pegar o codigo do cliente que deve estar na session e utiliza-lo para
		 * pegar: endereço ou informaçoes de pagto salvas
		 */
//		Object msgS = session.getAttribute("teste");
//		model.addAttribute("teste", msgS);
		System.out.println(carrinho);
		return new ModelAndView("checkout").addObject("carrinho", carrinho);
	}

	@RequestMapping("comprar")
	public ModelAndView realizarCompra(@ModelAttribute("carrinho") Carrinho carrinho
                        ,@SessionAttribute("cliente") Cliente cliente
                        ,@ModelAttribute("ends") String idEndereco) {
		Venda nova = new Venda();

		nova.setCarrinho(carrinho);

		nova.setIdCliente(cliente.getIdCliente());
		// nova.setData(LocalDate.now());
		nova.setData(new Date());
		nova.setDataView(LocalDateTime.now());
		nova.setIdFretes(new Long("2"));
		
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		nova.setProtocolo(f.format(nova.getData()) + Long.toString(nova.getData().getTime()));
		
		nova.setValorTotal(nova.getCarrinho().getTotal());

		// Salvar infos
		try {
			vendaBanco.salvar(nova);
//			Long idVenda = vendaBanco.getIdVenda(nova);
//			nova.setIdVenda(idVenda);
//			vendaBanco.salvarItensVenda(nova);
			return new ModelAndView("orderFinish").addObject("protocolo", nova.getProtocolo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/addcart/{codigo}")
	public ModelAndView addProdutoCart(@PathVariable("codigo") String codigo,
			@ModelAttribute("carrinho") Carrinho carrinho) {

		ArrayList<ProdutoVenda> lista = carrinho.getProdutos();
		try {
			Produto adiquirido = (Produto) produtoBanco.getByCodigo(codigo);
			lista.add(new ProdutoVenda(adiquirido.getIdProduto(), adiquirido.getCodigo(), adiquirido.getNome(),
					adiquirido.getPreco(), adiquirido.getPreco(), adiquirido.getUrlImagem(),
					// quantidade padrao ao adicionar um item no carrinho
					1));
			carrinho.setProdutos(lista);
			return new ModelAndView("redirect:/cart").addObject("carrinho", carrinho);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
