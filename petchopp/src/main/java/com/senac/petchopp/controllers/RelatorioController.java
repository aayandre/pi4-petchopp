package com.senac.petchopp.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.daos.VendaDAO;
import com.senac.petchopp.model.venda.Venda;

@Controller
@RequestMapping("relatorio")
public class RelatorioController {

	private VendaDAO vendaDAO = new VendaDAO();
	
	
	@RequestMapping("vendas")
	public ModelAndView relVendas() {
		
		ArrayList<Venda> vendas = vendaDAO.getVendas();
		ModelAndView modelAndView = new ModelAndView("relatorio/vendas");
		for (Venda venda : vendas) {
			venda.setHoraRelatorio(venda.getDataView());
			venda.setDtRelatorio(venda.getDataView());
			System.out.println(venda.getDtRelatorio() + " - " + venda.getHoraRelatorio());
		}
		modelAndView.addObject("vendas", vendas);
		return modelAndView;
	}
	
}
