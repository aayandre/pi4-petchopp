package com.senac.petchopp.controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senac.petchopp.daos.ClienteDAO;
import com.senac.petchopp.model.Auxiliares;
import com.senac.petchopp.model.cliente.Cliente;
import com.senac.petchopp.model.cliente.Endereco;
import com.senac.petchopp.service.LoginService;

@Controller
@RequestMapping("cliente")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ClienteController {

	private ClienteDAO clienteDAO = new ClienteDAO();

	@GetMapping("conta")
	public String minhaConta() {
		return "cli/cliente-index";
	}

	@GetMapping("Cadastro")
	public ModelAndView novoCliente(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("cli/novoCliente");
		modelAndView.addObject("cliente", new Cliente());
		modelAndView.addObject("endereco", new Endereco());
		System.out.println(session.getId());
		return modelAndView;
	}

	@PostMapping("salvar")
	public ModelAndView salvar(Cliente cliente, @RequestParam(value = "dtNasc") String dtNasc, Endereco endereco,
			RedirectAttributes redirect) throws SQLException, Exception {
		ModelAndView modelAndView = new ModelAndView("cli/login");
		LocalDate stringToLocalDateParse = Auxiliares.stringToLocalDateParse(dtNasc);
		cliente.addEnderecoToList(endereco);

		try {

			cliente.setDtCadastro(LocalDate.now());
			cliente.setDtNasc(stringToLocalDateParse);
			cliente.setAtivo(true);
			clienteDAO.salvar(cliente);
			String msg = "Cliente cadastrado com sucesso";
			System.out.println("Usuário cadastrado = " + cliente.getNome());
			redirect.addAttribute("msg", msg);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelAndView.addObject("redirect:cliente");
	}
	
	@GetMapping("login")
	public String loginCliente() {
		return "cli/login"; 
	}
	
	@PostMapping("logon")
	public ModelAndView logonUsurio(@RequestParam(value="email") String email, @RequestParam(value="password") String senha
			,RedirectAttributes redirect, HttpSession session) throws SQLException {
		
		Cliente cliente = new LoginService().clienteLogon(email, senha);
		session.setAttribute("cliente", cliente);
		if(cliente!=null) {
			System.out.println(cliente.getEmail());
		return new ModelAndView("cli/cliente-index");
		}
		
		return new ModelAndView("cli/login");
	}

}
