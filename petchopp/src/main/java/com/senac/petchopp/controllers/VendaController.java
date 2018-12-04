package com.senac.petchopp.controllers;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.daos.VendaDAO;
import com.senac.petchopp.exception.EstoqueException;
import com.senac.petchopp.model.cliente.Cliente;
import com.senac.petchopp.model.estoqueProduto.EstoqueProduto;
import com.senac.petchopp.model.produto.ProdutoService;
import com.senac.petchopp.model.produto.ProdutoVenda;
import com.senac.petchopp.model.tipo.Tipo;
import com.senac.petchopp.model.venda.Venda;
import com.senac.petchopp.service.ServicoEstoqueProduto;

@Controller
@RequestMapping("checkout")
@SessionAttributes({ "cliente", "venda" })
public class VendaController {

	private VendaDAO vendaBanco = new VendaDAO();
	private ProdutoService produtoService = new ProdutoService();

	@RequestMapping("formulario")
	public ModelAndView formVenda(@ModelAttribute("venda") Venda venda, @ModelAttribute("cliente") Cliente cliente) {
		
		System.out.println(cliente.getNome());

		if (!cliente.isLogado()) {
			return new ModelAndView("redirect:/cliente/login");
		}

		return new ModelAndView("checkout").addObject("venda", venda);
	}

	@RequestMapping("comprar")
	public ModelAndView realizarCompra(@ModelAttribute("venda") Venda venda,
			@SessionAttribute("cliente") Cliente cliente) throws EstoqueException {
		ServicoEstoqueProduto servicoEstoque = new ServicoEstoqueProduto();
		String erro = null;
		Venda nova = venda;

		nova.setIdCliente(cliente.getIdCliente());
		nova.setData(new Date());
		nova.setDataView(LocalDateTime.now());
		nova.setIdFretes(new Long("2"));

		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		nova.setProtocolo(f.format(nova.getData()) + Long.toString(nova.getData().getTime()));
		nova.setStatus(new Tipo(17));
		nova.setValorTotal(nova.getCarrinho().getTotal());

		// Salvar infos
		try {
			// Validação de estoque
			List<EstoqueProduto> estoqueProd = new ArrayList<>();
			for (ProdutoVenda prods : nova.getCarrinho().getProdutos()) {
				estoqueProd.add(new EstoqueProduto((long) prods.getIdProduto(),
						servicoEstoque.ObtemQuantidadeByIdProduto(prods.getIdProduto())));
			}
			erro = servicoEstoque.validaQtdeEstoquePedido(estoqueProd, nova.getCarrinho().getProdutos());

			if (erro != null) {
				return new ModelAndView("checkout").addObject("venda", venda).addObject("erro", erro);
			}

			// Atualizando a qtde estoque no banco
			for (EstoqueProduto estoqueProduto : estoqueProd) {
				servicoEstoque.AtualizarEstoque(estoqueProduto);
			}

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
	public ModelAndView addProdutoCart(@PathVariable("codigo") String codigo, @ModelAttribute("venda") Venda venda) {

		try {
			venda.getCarrinho()
					.setProdutos(produtoService.addProdutoNoCarrinho(codigo, venda.getCarrinho().getProdutos()));
			return new ModelAndView("redirect:/cart").addObject("venda", venda);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/removefromcart/{codigo}")
	public ModelAndView removeProdutoCart(@PathVariable("codigo") String codigo, @ModelAttribute("venda") Venda venda) {

		venda.getCarrinho().setProdutos(produtoService.removerDoCarrinho(codigo, venda.getCarrinho().getProdutos()));
		return new ModelAndView("redirect:/cart");
	}

}
