package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.produto.Produto;

public class ProdutoDAO implements IDAO {

	static Connection cn = null;

	@Override
	public void salvar(Object bean) throws SQLException {

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
			stmt.setDate(6, java.sql.Date.valueOf(novo.getDtCompra()));
			stmt.setDate(7, java.sql.Date.valueOf(novo.getDtValidade()));
			stmt.setString(8, novo.getUrlImagem());
			stmt.setBoolean(9, novo.isEmEstoque());
			stmt.setBoolean(10, novo.isDisable());

			stmt.execute();

		} catch (SQLException e) {
			throw new SQLException("Erro ao salvar o objeto no banco.", e);
		} finally {
			ConnectionFactory.closeConnection(cn, stmt);
		}

	}

	@Override
	public void atualizar(Object bean) {
		@SuppressWarnings("unused")
		String sql = "UPDATE Produto SET (Nome, Preco, Custo, dtCompra, dtValidade, urlImagem, emEstoque, Disable) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?) WHERE Codigo = ?";

		String sql2 = "UPDATE Produto "
				+ "SET Nome = ?, Preco = ?, Custo = ?, dtCompra = ?, dtValidade = ?, urlImagem = ?, emEstoque = ?, Disable = ? "
				+ "WHERE Codigo = ?";

		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();

		Produto alterado = (Produto) bean;

		try {

			stmt = cn.prepareStatement(sql2);

			// SET
			stmt.setString(1, alterado.getNome());
			stmt.setDouble(2, alterado.getPreco());
			stmt.setDouble(3, alterado.getCusto());
			stmt.setDate(4, java.sql.Date.valueOf(alterado.getDtCompra()));
			stmt.setDate(5, java.sql.Date.valueOf(alterado.getDtValidade()));
			stmt.setString(6, alterado.getUrlImagem());
			stmt.setBoolean(7, alterado.isEmEstoque());
			stmt.setBoolean(8, alterado.isDisable());

			// WHERE
			stmt.setString(9, alterado.getCodigo());

			stmt.execute();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt);
		}

	}

	@Override
	public void deletar(Long id) {
		String sql = "UPDATE Produto SET Disable = true WHERE idProduto = ?";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
	}

	@Override
	public Object getById(Long id) throws SQLException {

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
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return produto;
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

	public void deletar(String codigo) {
		String sql = "UPDATE Produto SET Disable = true WHERE Codigo = ?";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, codigo);
			stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt);
		}
	}

	public ArrayList<Produto> searchByNome(String nome) {
		String sql = "SELECT * FROM Produto WHERE Nome LIKE ? LIMIT 12";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();
		ArrayList<Produto> encontrados = new ArrayList<>();
		ResultSet rs = null;

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, nome);
			rs = stmt.executeQuery();

			while (rs.next()) {
				if (new Produto(rs) != null) {
					encontrados.add(new Produto(rs));
				}
			}
			return encontrados;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return encontrados;
	}

	public ArrayList<Produto> testeCarrinho(int quantidade) {
		String sql = "SELECT * FROM Produto LIMIT ?";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();
		ArrayList<Produto> pegos = new ArrayList<>();
		ResultSet rs = null;

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, quantidade);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (new Produto(rs) != null) {
					pegos.add(new Produto(rs));
				}
			}
			return pegos;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return null;
	}

}
