package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.senac.petchopp.daos.UsuarioDAO;
import com.senac.petchopp.model.TipoCategoria;

@Controller
@RequestMapping("/testepost")
public class Teste {

	@PostMapping("/tipocad")
	public ModelAndView insertDBTeste(@ModelAttribute("tipoCategoria") TipoCategoria tc) {

		UsuarioDAO.teste(tc);

		TipoCategoria resultado = UsuarioDAO.testeGet();

		return new ModelAndView("testeVisualizacao").addObject("resultado", resultado);

	}

}
