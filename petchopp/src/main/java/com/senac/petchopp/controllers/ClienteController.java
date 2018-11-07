package com.senac.petchopp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
<<<<<<< HEAD

=======
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senac.petchopp.daos.ClienteDAO;
>>>>>>> todayy
import com.senac.petchopp.model.cliente.Cliente;

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
<<<<<<< HEAD
	@GetMapping("Cadastro")
	public String novoCliente() {
		Cliente cli = new Cliente();
		return "cliente-index"; //
	}
	
	@GetMapping("NCadastro")
	public ModelAndView formCliente() {
		return new ModelAndView("novoCliente").addObject("cliente", new Cliente());
=======
	
	private ClienteDAO clienteDAO = new ClienteDAO();
	
	@GetMapping("conta")
	public String minhaConta() {
		return "cliente-index";
>>>>>>> todayy
	}
	
	@GetMapping("Cadastro")
	public ModelAndView novoCliente() {
		ModelAndView modelAndView = new ModelAndView("novoCliente");
		modelAndView.addObject("cliente", new Cliente());
		System.out.println("Entrando em CadastroCliente");
		return modelAndView;
	}
	
	@PostMapping("salvar")
	public ModelAndView salvar(Cliente cliente, RedirectAttributes redirect) {
		ModelAndView modelAndView = new ModelAndView("login");
		System.out.println("Cadastrando o Cliente");
		try {
			clienteDAO.salvar(cliente);
			redirect.addFlashAttribute("msg", "Cadastro realizado com sucesso");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return modelAndView.addObject("redirect:cliente");
	}
		
}
