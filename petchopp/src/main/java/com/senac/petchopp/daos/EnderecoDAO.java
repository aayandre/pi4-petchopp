package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.cliente.Endereco;

public class EnderecoDAO implements IDAO{
	 Connection cn = null;
	 
	 
	public void salvarEndereco(List<Endereco> enderecos, long idcliente) throws SQLException {
		ClienteDAO cd = new ClienteDAO();
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO Endereco\r\n" + 
				"(idEndereco, CEP, Logradouro, Bairro, Cidade, UF, idCliente, TipoEndereco)\r\n" + 
				"VALUES(?,?,?,?,?,?,?,?)";
		try {
			cn.setAutoCommit(false);
			stmt = cn.prepareStatement(sql);
			for(Endereco end: enderecos) {
				stmt.setLong(1, end.getIdEndereco());
				stmt.setString(2, end.getCep());
				stmt.setString(3, end.getLogradouro());
				stmt.setString(4, end.getBairro());
				stmt.setString(5, end.getCidade());
				stmt.setString(6, end.getUf());
				stmt.setLong(7, idcliente);
				stmt.setString(8, end.getTipoEndereco());
			}
			cn.commit();
			cd.setStatusCommit(true);
		}catch (Exception e) {
			e.printStackTrace();
			cn.rollback();
		} finally {
			cn.setAutoCommit(true);
			ConnectionFactory.closeConnection(cn, stmt);
		}
	}

	@Override
	public void atualizar(Object bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(Object bean) {
		// TODO Auto-generated method stub
		
	}

}
