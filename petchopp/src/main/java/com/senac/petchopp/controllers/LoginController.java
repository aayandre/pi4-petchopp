package com.senac.petchopp.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senac.petchopp.daos.ClienteDAO;
import com.senac.petchopp.model.cliente.Cliente;
import com.senac.petchopp.service.LoginService;

@Controller
@RequestMapping("auth")
public class LoginController {

//	private ClienteDAO clienteDAO = new ClienteDAO();
	
	
	
//	@PostMapping("login")
//	public ModelAndView registrarLogin(@RequestParam(value="email") String email, @RequestParam(value="password") String senha
//			,RedirectAttributes redirect, HttpSession session) {
//		try {
////			Cliente cli = LoginService.
//			String msg = "Usuário ou senha incorretos, tente novamente!";
////			System.out.println(cli.getNome());
//			if(!cli.getEmail().isEmpty()) {
//				System.out.println(cli.getNome());
//				boolean validaLogin = BCrypt.checkpw(senha, cli.getSenha());
//				System.out.println(cli.getCpf());
//				if(validaLogin) {
//					System.out.println(cli.getSenha());
//					return new ModelAndView("cli/cliente-index").addObject("redirect:cli");
//				}else {
//					redirect.addAttribute("msg", msg);
//					return new ModelAndView("cli/login").addObject("redirect:msg");
//				}
//				
//			}else {
//				redirect.addAttribute("msg", msg);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView("cli/login").addObject("redirect:msg");
//	}
}
