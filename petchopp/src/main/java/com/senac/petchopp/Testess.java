package com.senac.petchopp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.senac.petchopp.connection.ConnectionFactory;

public class Testess {

	public static void main(String[] args) {

		Scanner sn = new Scanner(System.in);

		System.out.println("Digite com ponto ç etc...");
		String entrada = sn.nextLine();
		sn.close();

		// Utilizar no cadastro de tags e tipos
		String saida = Normalizer.normalize(entrada, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
		
		System.out.println("Saída sem os caracteres especiais: ");
		System.out.println(saida);

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
