package com.senac.petchopp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	// N.Virginia
	private static final String URL = "jdbc:mysql://awsdbmysqlnvirginia.cvvx4cghxp8c.us-east-1.rds.amazonaws.com:3306/projetointegrador4?useSSL=false";

	// Sao Paulo
	// TO-DO
	// Criar as tabelas do banco
	// private static final String URL =
	// "jdbc:mysql://awsdbmysqlsaopaulo.cm72cae7hfji.sa-east-1.rds.amazonaws.com:3306/projetointegrador4?useSSL=false";

	private static final String USER = "pi4tadsandre";
	private static final String PASS = "senacpi4TADS";

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);

		} catch (ClassNotFoundException | SQLException ex) {
			throw new RuntimeException("Erro De Conexão!");
		}
	}

	public static void closeConnection(Connection cn) {
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException ex) {
				Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void closeConnection(Connection cn, PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		closeConnection(cn);
	}

	public static void closeConnection(Connection cn, PreparedStatement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		closeConnection(cn, stmt);
	}

}
