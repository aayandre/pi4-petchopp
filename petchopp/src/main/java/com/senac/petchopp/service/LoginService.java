package com.senac.petchopp.service;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.senac.petchopp.daos.ClienteDAO;
import com.senac.petchopp.model.cliente.Cliente;

@Service
public class LoginService {
	
	private ClienteDAO clienteDao = new ClienteDAO();
	
	public Cliente clienteLogon(String email, String senha)  {
		Cliente cli = null;
		
		try {
			cli = clienteDao.getClienteByEmail(email);
			if(cli!=null) {
				boolean validaSenha = BCrypt.checkpw(senha, cli.getSenha());
				if(validaSenha) {
					System.out.println(cli.getEmail());
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cli;
	}

}
