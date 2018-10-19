/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.daos;

import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.Auxiliares;
import com.senac.petchopp.model.cliente.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo Pereira
 */
public class ClienteDAO implements IDAO{
    Connection cn = null;

	@Override
	
	public void salvar(Object bean) {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO petchoppbase.Cliente (idCliente,dtCadastro,Nome,dtNasc,RG,CPF,Email,Senha,Ativo) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		
	}

	@Override
	public void atualizar(Object bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
   

   
    
    
}
