package com.senac.petchopp.controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.daos.ProdutoDAO;
import com.senac.petchopp.daos.VendaDAO;
import com.senac.petchopp.model.carrinho.Carrinho;
import com.senac.petchopp.model.venda.Venda;
import java.sql.Timestamp;

@Controller
@RequestMapping("checkout")
@SessionAttributes({ "cliente", "carrinho" })
public class VendaController {

	private VendaDAO vendaBanco = new VendaDAO();

	@GetMapping("")
	public ModelAndView carrinho(@ModelAttribute("carrinho") Carrinho carrinho) {
		carrinho.setProdutos(new ProdutoDAO().testeCarrinho(3));
		return new ModelAndView("cart").addObject("carrinho", carrinho);
	}

	@RequestMapping("formulario")
	public ModelAndView formVenda(@ModelAttribute("carrinho") Carrinho carrinho) {
		/*
		 * TODO pegar o codigo do cliente que deve estar na session e utiliza-lo para
		 * pegar: endereço ou informaçoes de pagto salvas
		 */
//		Object msgS = session.getAttribute("teste");
//		model.addAttribute("teste", msgS);
		return new ModelAndView("checkout").addObject("carrinho", carrinho);
	}

	@RequestMapping("comprar")
	public ModelAndView realizarCompra(@SessionAttribute("carrinho") Carrinho carrinho) {
		Venda nova = new Venda();

		nova.setCarrinho(carrinho);

		nova.setIdCliente(new Long("1"));
		//nova.setData(LocalDate.now());
                nova.setData(new Timestamp(System.currentTimeMillis()));
		nova.setDataView(LocalDateTime.now());
		nova.setIdFretes(new Long("2"));
		nova.setProtocolo(nova.getDataView().toString());
		nova.setValorTotal(nova.getCarrinho().getTotal());

		// Salvar infos
		try {
			vendaBanco.salvar(nova);
			Long idVenda = vendaBanco.getIdVenda(nova);
			nova.setIdVenda(idVenda);
			vendaBanco.salvarItensVenda(nova);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
}
