package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.Auxiliares;
import com.senac.petchopp.model.produto.Produto;

public class ProdutoDAO implements IDAO {

	static Connection cn = null;

	@Override
	public void salvar(Object bean) {

		String sql = "INSERT INTO Produto (Codigo, Nome, Preco, Custo, Descricao, dtCompra, dtValidade, urlImagem, emEstoque, Disable) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = null;

		cn = ConnectionFactory.getConnection();

		// Cast do Object para Produto com intuito de utilizar os getters e setters
		Produto novo = (Produto) bean;

		try {

			stmt = cn.prepareStatement(sql);

			stmt.setString(1, novo.getCodigo());
			stmt.setString(2, novo.getNome());
			stmt.setDouble(3, novo.getPreco());
			stmt.setDouble(4, novo.getCusto());
			stmt.setString(5, novo.getDescricao());
			stmt.setDate(6, new java.sql.Date(Auxiliares.UtilDateToCalendar(novo.getDtCompra()).getTimeInMillis()));
			stmt.setDate(7, new java.sql.Date(Auxiliares.UtilDateToCalendar(novo.getDtValidade()).getTimeInMillis()));
			stmt.setString(8, novo.getUrlImagem());
			stmt.setBoolean(9, novo.isEmEstoque());
			stmt.setBoolean(10, novo.isDisable());

			stmt.execute();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt);
		}

	}

	@Override
	public void atualizar(Object bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletar(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getById(long id) {

		String sql = "SELECT * FROM Produto WHERE idProduto = ? LIMIT 1";
		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Produto produto = null;

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				produto = new Produto(rs);
			}
			return produto;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return null;
	}

	@Override
	public List<Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getByCodigo(String codigo) {

		String sql = "SELECT * FROM Produto WHERE Codigo = ? LIMIT 1";
		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Produto produto = new Produto();

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, codigo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				produto = new Produto(rs);
			}
			return produto;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return produto;
	}

}
