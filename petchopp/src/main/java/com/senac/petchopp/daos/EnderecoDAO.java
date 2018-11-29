package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.cliente.Endereco;

public class EnderecoDAO implements IDAO{
	 Connection cn = null;
	 
	 
	public void salvarEndereco(List<Endereco> enderecos, Long idcliente) throws SQLException {
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();
		String sql = "INSERT INTO Endereco\r\n" + 
				"(CEP, Logradouro, Numero, Complemento, Bairro, Cidade, UF, idCliente, TipoEndereco)\r\n" + 
				"VALUES(?,?,?,?,?,?,?,?,?)";
		try {
//			cn.setAutoCommit(false);
			stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for(Endereco end: enderecos) {
//				stmt.setLong(1, end.getIdEndereco());
				stmt.setString(1, end.getCep());
				stmt.setString(2, end.getLogradouro());
				stmt.setString(3, end.getNum());
				stmt.setString(4, end.getComp());
				stmt.setString(5, end.getBairro());
				stmt.setString(6, end.getCidade());
				stmt.setString(7, end.getUf());
				stmt.setLong(8, idcliente);
				stmt.setString(9, end.getTipoEndereco());
				stmt.execute();
				ResultSet rs = stmt.getGeneratedKeys();
				if(rs.next()) {
					end.setIdEndereco(rs.getLong(1));
				}
			}
			
//			cn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
//			cn.rollback();
		} finally {
//			cn.setAutoCommit(true);
			ConnectionFactory.closeConnection(cn, stmt);
		}
	}
	
		public List<Endereco> getAllEnd(Long idCliente) throws SQLException {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			List<Endereco> enderecos = new ArrayList<>();
			String sql = "SELECT idEndereco, CEP, Logradouro, Numero, Complemento, Bairro, Cidade, UF, idCliente, TipoEndereco "
					+ "FROM Endereco WHERE idCliente = ?";
			cn = ConnectionFactory.getConnection();
			
			try {
				stmt = cn.prepareStatement(sql);
				stmt.setLong(1, idCliente);
				rs= stmt.executeQuery();
				
				while(rs.next()) {
					Endereco end = new Endereco();
					
					end.setIdEndereco(rs.getLong("idEndereco"));
					end.setCep(rs.getString("CEP"));
					end.setLogradouro(rs.getString("Logradouro"));
					end.setNum(rs.getString("Numero"));
					end.setComp(rs.getString("Complemento"));
					end.setBairro(rs.getString("Bairro"));
					end.setCidade(rs.getString("Cidade"));
					end.setUf(rs.getString("UF"));
					end.setIdCliente(rs.getLong("idCliente"));
					end.setTipoEndereco(rs.getString("TipoEndereco"));
					enderecos.add(end);
					
				}
				
			} finally {
				ConnectionFactory.closeConnection(null, stmt, rs);
			}
			
			
			
			return enderecos;
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
		
		return null;
	}

	@Override
	public List<Object> getAll() {
		
		return null;
	}

	@Override
	public void salvar(Object bean) {
		// TODO Auto-generated method stub
		
	}

}
