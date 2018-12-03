package com.senac.petchopp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.senac.petchopp.connection.ConnectionFactory;

public class Testess {

	public static void main(String[] args) {

		try {

			// create a temp file
			File temp = File.createTempFile("temp-file-name", ".tmp");
			FileOutputStream fos = new FileOutputStream(temp);

			System.out.println("Temp file : " + temp.getAbsolutePath());

			// Get tempropary file path
			String absolutePath = temp.getAbsolutePath();
			String tempFilePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));

			System.out.println("Temp file path : " + tempFilePath);

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public static ArrayList<String> getAll() {
		String sql = "SELECT * FROM teste";
		Connection cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<String> resultado = new ArrayList<>();
		Date dataa = new Date();

		try {
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				resultado.add("Tipo sql.date: " + rs.getDate("Datasql").toString() + "\nTipo sql.timestamp: "
						+ rs.getTimestamp("Timestampsql").toString());
				dataa = rs.getDate("Datasql");
				System.out.println("Dentro do while util.date: " + dataa);
				dataa = rs.getTimestamp("Timestampsql");
				System.out.println("Dentro do while util.date: " + dataa);
			}
			return resultado;
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return resultado;
	}

	public static void saveDate(Date data) throws SQLException {
		String sql = "INSERT INTO teste (Datasql, Timestampsql) VALUES(?, ?)";

		PreparedStatement stmt = null;

		Connection cn = ConnectionFactory.getConnection();

		try {

			stmt = cn.prepareStatement(sql);

			stmt.setDate(1, new java.sql.Date(data.getTime()));
			stmt.setTimestamp(2, (Timestamp) data);

			stmt.execute();

		} catch (SQLException e) {
			throw new SQLException("Erro ao salvar o objeto no banco.", e);
		} finally {
			ConnectionFactory.closeConnection(cn, stmt);
		}
	}

}
