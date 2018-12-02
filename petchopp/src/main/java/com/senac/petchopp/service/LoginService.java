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
				System.out.println(cli.getSenha());
				
				if(BCrypt.checkpw(senha, cli.getSenha())) {
					System.out.println("Senha OK");
                                        System.out.println(cli.getIdCliente());
					cli.setLogado(true);
					
				}else {
					cli.setLogado(false);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cli;
	}

        public Cliente clienteLogoff(){
            Cliente cliente = null;
            return cliente;
        }
}
