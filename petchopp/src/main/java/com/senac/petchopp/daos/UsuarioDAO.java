package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.model.TipoCategoria;

public class UsuarioDAO {

	private static Connection cn = null;

	public static void teste(TipoCategoria tc) {

		String sql = "INSERT INTO TipoCategoria (idTipo, Codigo, Descricao, Categoria) " + "VALUES(?, ?, ?, ?)";

		PreparedStatement stmt = null;

		cn = ConnectionFactory.getConnection();

		try {

			stmt = cn.prepareStatement(sql);

			stmt.setInt(1, tc.getId());
			stmt.setInt(2, tc.getCodigo());
			stmt.setString(3, tc.getDescricao());
			stmt.setString(4, tc.getCategoria());

			stmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cn, stmt);
		}

	}

	public static TipoCategoria testeGet() {

		ResultSet rs = null;
		PreparedStatement stmt = null;
		TipoCategoria resultado = new TipoCategoria();

		String sql = "SELECT * FROM TipoCategoria LIMIT 1";

		cn = ConnectionFactory.getConnection();

		try {

			stmt = cn.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {

				resultado = new TipoCategoria(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));

			}

			return resultado;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		
		return null;
	}
}
