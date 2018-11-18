package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.model.tipo.Tipo;

public class TipoDAO {

	private Connection cn = null;

	public ArrayList<Tipo> getAll() throws SQLException {

		String sql = "SELECT * FROM Tipo WHERE Nome = Produto";

		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<Tipo> resultados = new ArrayList<>();

		cn = ConnectionFactory.getConnection();

		try {
			stmt = cn.prepareStatement(sql);
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

}
