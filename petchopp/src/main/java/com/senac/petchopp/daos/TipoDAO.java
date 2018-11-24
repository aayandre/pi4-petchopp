package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.tipo.Tipo;

public class TipoDAO implements IDAO {

	private Connection cn = null;

	public TipoDAO() {
		super();
	}

	@Override
	public void salvar(Object bean) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void atualizar(Object bean) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletar(Long id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getAll() throws SQLException {

		String sql = "SELECT * FROM Tipo WHERE Nome = ?";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Object> resultados = new ArrayList<>();

		cn = ConnectionFactory.getConnection();

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, "Produto");
			rs = stmt.executeQuery();
			while (rs.next()) {
				resultados.add(new Tipo(rs));
			}
			return resultados;
		} catch (SQLException e) {
			throw new SQLException("Erro ao adiquirir todas os Tipos do banco.", e);
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}

	}

	public List<Tipo> getAllTipos() throws SQLException {

		String sql = "SELECT * FROM Tipo WHERE Nome = ?";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Tipo> resultados = new ArrayList<>();

		cn = ConnectionFactory.getConnection();

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, "Produto");
			rs = stmt.executeQuery();
			while (rs.next()) {
				resultados.add(new Tipo(rs));
			}
			return resultados;
		} catch (SQLException e) {
			throw new SQLException("Erro ao adiquirir todas os Tipos do banco.", e);
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
	}

	public Tipo getTipoByID(int idTipo) {
		String sql = "SELECT * FROM Tipo WHERE idTipo = ?";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();
		Tipo tipo = new Tipo();
		ResultSet rs = null;

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, idTipo);
			rs = stmt.executeQuery();

			if (rs.next()) {
				tipo = new Tipo(rs);
			}

		} catch (Exception e) {

		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return tipo;
	}

	public Tipo getTipoByNome(String nome) {
		String sql = "SELECT * FROM Tipo WHERE nome = ?";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();
		Tipo tipo = new Tipo();
		ResultSet rs = null;

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, nome);
			rs = stmt.executeQuery();

			if (rs.next()) {
				tipo = new Tipo(rs);
			}

		} catch (Exception e) {

		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return tipo;
	}

}
