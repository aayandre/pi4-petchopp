/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.daos;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.Auxiliares;
import com.senac.petchopp.model.cliente.Cliente;
import com.senac.petchopp.model.cliente.Endereco;

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
    private boolean statusCommit;
    
	@Override
	public void salvar(Object bean) throws SQLException {
		PreparedStatement stmt = null;
		Cliente novo = (Cliente) bean;
		String sql = "INSERT INTO Cliente\r\n" + 
				"(idCliente, dtCadastro, Nome, dtNasc, RG, CPF, Email, Senha, Telefone1, Telefone2, Ativo)\r\n" + 
				"VALUES(?,?,?,?,?,?,?,?,?)";
		cn = ConnectionFactory.getConnection();
		System.out.println("teste cliente");
		try {
//			cn.setAutoCommit(false);
			stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, novo.getIdCliente());
			stmt.setDate(2, new java.sql.Date(Auxiliares.UtilDateToCalendar(novo.getDtCadastro()).getTimeInMillis()));
			stmt.setString(3, novo.getNome());
			stmt.setDate(4, new java.sql.Date(Auxiliares.UtilDateToCalendar(novo.getDtNasc()).getTimeInMillis()));
			stmt.setString(5, novo.getRg());
			stmt.setString(6, novo.getCpf());
			stmt.setString(7, novo.getEmail());
			stmt.setString(8, novo.getSenha());
			stmt.setNString(9, novo.getTelefone1());
			stmt.setNString(10, novo.getTelefone2());
			stmt.setBoolean(11, novo.isAtivo());
			stmt.execute();
			System.out.println("teste cliente 2");
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				novo.setIdCliente(rs.getLong(1));
				System.out.println(rs.getLong(1));
//				cn.commit();
//				EnderecoDAO ed = new EnderecoDAO();
//				ed.salvarEndereco(novo.getEnderecos(), novo.getIdCliente());
//				if(this.statusCommit) {
//					
//				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
//			cn.rollback();
		} finally {
//			cn.setAutoCommit(true);
			ConnectionFactory.closeConnection(cn, stmt);
		}
	}

	@Override
	public void atualizar(Object bean) throws SQLException {
		PreparedStatement stmt = null;
		Cliente novo = (Cliente) bean;
		String sql = "UPDATE Cliente\r\n" + 
				"SET idCliente = ?, dtCadastro = ?, Nome = ?, dtNasc = ?, RG = ?, CPF = ?, Email = ?, Senha = ?, Telefone1 = ?, Telefone2 = ?, Ativo = ?"
				+ " WHERE idCliente = ?";
		cn = ConnectionFactory.getConnection();
		
		try {
			cn.setAutoCommit(false);
			stmt = cn.prepareStatement(sql);
			stmt.setLong(1, novo.getIdCliente());
			stmt.setDate(2, new java.sql.Date(Auxiliares.UtilDateToCalendar(novo.getDtCadastro()).getTimeInMillis()));
			stmt.setString(3, novo.getNome());
			stmt.setDate(4, new java.sql.Date(Auxiliares.UtilDateToCalendar(novo.getDtNasc()).getTimeInMillis()));
			stmt.setString(5, novo.getRg());
			stmt.setString(6, novo.getCpf());
			stmt.setString(7, novo.getEmail());
			stmt.setString(8, novo.getSenha());
			stmt.setNString(9, novo.getTelefone1());
			stmt.setNString(10, novo.getTelefone2());
			stmt.setBoolean(11, novo.isAtivo());
			stmt.setLong(12, novo.getIdCliente());
			stmt.execute();
			
			
				EnderecoDAO ed = new EnderecoDAO();
				ed.salvarEndereco(novo.getEnderecos(), novo.getIdCliente());
				cn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			cn.rollback();
		} finally {
			cn.setAutoCommit(true);
			ConnectionFactory.closeConnection(cn, stmt);
		}
		
	}

	@Override
	public void deletar(long id) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "UPDATE Cliente SET Ativo = false WHERE idCliente = ?";
		cn = ConnectionFactory.getConnection();
		
		try {
			cn.setAutoCommit(false);
			stmt = cn.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			cn.rollback();
		} finally {
			cn.commit();
			cn.setAutoCommit(true);
			ConnectionFactory.closeConnection(cn, stmt);
		}

		
	}

	@Override
	public Object getById(long id) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT idCliente, dtCadastro, Nome, dtNasc, RG, CPF, Email, Senha, Telefone1, Telefone2, Ativo "
				+ "FROM cliente WHERE idCliente = ?";
		cn = ConnectionFactory.getConnection();
		
		try {
			
			stmt = cn.prepareStatement(sql);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			rs.next();
			
			Cliente cli = new Cliente(rs);
			if(cli != null) {
				EnderecoDAO ed = new EnderecoDAO();
				cli.setEnderecos(ed.getAllEnd(cli.getIdCliente()));
			}
			return cli;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			ConnectionFactory.closeConnection(cn, stmt);
		}

		
		return null;
	}

	@Override
	public List<Object> getAll() {
		
		return null;
	}

	
	
	public boolean isStatusCommit() {
		return statusCommit;
	}

	public void setStatusCommit(boolean statusCommit) {
		this.statusCommit = statusCommit;
	}

	
    
}
