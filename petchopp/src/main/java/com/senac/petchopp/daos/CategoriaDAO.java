package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.model.categoria.Categoria;

public class CategoriaDAO {

	private Connection cn = null;

	public ArrayList<Categoria> getAll() throws SQLException {

		String sql = "SELECT * FROM Categoria";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<Categoria> resultados = new ArrayList<>();

		cn = ConnectionFactory.getConnection();

		try {
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				resultados.add(new Categoria(rs));
			}
			return resultados;
		} catch (SQLException e) {
			throw new SQLException("Erro ao adiquirir todas as categorias", e);
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}

	}

	public ArrayList<Categoria> getAllByProdutoId(Long id) throws SQLException {
		String sql = "SELECT * FROM Categoria WHERE ";
		return null;
	}
}
