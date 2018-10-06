package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.Auxiliares;
import com.senac.petchopp.model.produto.Produto;

public class ProdutoDAO implements IDAO {

	private static Connection cn = null;

	@Override
	public void salvar(Object bean) {

		String sql = "INSERT INTO Produto (idProduto, Codigo, Nome, Preco, Custo, dtCompra, dtValidade, urlImagem, emEstoque, Disable) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = null;

		cn = ConnectionFactory.getConnection();

		// Cast do Object para Produto com intuito de utilizar os getters e setters
		Produto novo = (Produto) bean;

		try {

			stmt = cn.prepareStatement(sql);

			stmt.setLong(1, novo.getIdProduto());
			stmt.setInt(2, novo.getCodigo());
			stmt.setString(3, novo.getNome());
			stmt.setDouble(4, novo.getPreco());
			stmt.setDouble(5, novo.getCusto());
			stmt.setDate(6, new java.sql.Date(Auxiliares.UtilDateToCalendar(novo.getDtCompra()).getTimeInMillis()));
			stmt.setDate(7, new java.sql.Date(Auxiliares.UtilDateToCalendar(novo.getDtValidade()).getTimeInMillis()));
			stmt.setString(8, novo.getUrlImagem());
			stmt.setBoolean(9, novo.isEmEstoque());
			stmt.setBoolean(10, novo.isDisable());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void atualizar(Object bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletar(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
