package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.venda.Venda;

public class VendaDAO implements IDAO {

	private static Connection cn = null;
	private TipoDAO tipodao = new TipoDAO();

	@Override
	public void salvar(Object bean) throws SQLException {
		String sql = "INSERT INTO Venda (idCliente, idFretes, Protocolo, Data, ValorTotal) " + "VALUES(?, ?, ?, ?, ?)";

		PreparedStatement stmt = null;

		cn = ConnectionFactory.getConnection();

		Venda venda = (Venda) bean;

		try {

			stmt = cn.prepareStatement(sql);

			stmt.setLong(1, venda.getIdCliente());
			stmt.setLong(2, venda.getIdFretes());
			stmt.setString(3, venda.getProtocolo());
			stmt.setDate(4,
					new java.sql.Date(venda.getDataView().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
			stmt.setDouble(5, venda.getValorTotal());

			stmt.execute();

		} catch (SQLException e) {
			throw new SQLException("Erro ao salvar a venda no banco.", e);
		} finally {
			ConnectionFactory.closeConnection(cn, stmt);
		}
	}

	@Override
	public void atualizar(Object bean) throws SQLException {
		// TODO UPDATE Teoricamente nao precisa fazer

	}

	@Override
	public void deletar(Long id) throws SQLException {
		// TODO DELETE Teoricamente nao precisa fazer

	}

	@Override
	public Object getById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getIdVenda(Venda venda) {
		String sql = "SELECT * FROM Venda WHERE idCliente = ? AND Protocolo = ? AND data = ? AND valorTotal = ? LIMIT 1";
		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Long idVenda = null;

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setLong(1, venda.getIdCliente());
			stmt.setString(2, venda.getProtocolo());
			stmt.setTimestamp(3, venda.getData());
			stmt.setDouble(4, venda.getValorTotal());
			rs = stmt.executeQuery();
			while (rs.next()) {
				idVenda = rs.getLong("idVenda");
			}
			return idVenda;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return idVenda;
	}

	public void salvarItensVenda(Venda venda) throws SQLException {
		String sql = "INSERT INTO ItemVenda (idVenda, idProduto, Quantidade, Valor) VALUES(?, ?, ?, ?)";

		PreparedStatement stmt = null;

		cn = ConnectionFactory.getConnection();

		try {

			for (Produto produto : venda.getCarrinho().getProdutos()) {
				stmt = cn.prepareStatement(sql);

				stmt.setLong(1, venda.getIdVenda());
				stmt.setLong(2, produto.getIdProduto());
				stmt.setInt(3, produto.getQuantidade().intValue());
				stmt.setDouble(4, produto.getPreco());

				stmt.execute();
			}

		} catch (SQLException e) {
			throw new SQLException("Erro ao salvar a venda no banco.", e);
		} finally {
			ConnectionFactory.closeConnection(cn, stmt);
		}
	}

	public ArrayList<Venda> getVendas() {
		String sql = "SELECT * FROM Venda LIMIT 12";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();
		ArrayList<Venda> encontrados = new ArrayList<>();
		ResultSet rs = null;

		try {
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				if (new Venda(rs) != null) {
					encontrados.add(new Venda(rs));
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

	public ArrayList<Produto> getItensVendaByVenda(int idVenda) {
		String sql = "SELECT * FROM ItemVenda WHERE idVenda = ?";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();
		ArrayList<Produto> produtos = new ArrayList<>();
		ResultSet rs = null;

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, idVenda);
			rs = stmt.executeQuery();

			while (rs.next()) {
				ProdutoDAO prodDAO = new ProdutoDAO();
				produtos.add((Produto) prodDAO.getById(rs.getLong("idProduto")));
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return produtos;
	}

	public List<Venda> getVendasByCliente(int idCliente) throws SQLException {
		String sql = "SELECT * FROM Venda WHERE idCliente = ? and idVenda IN (4, 7)";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();
		List<Venda> vendas = new ArrayList<>();
		ResultSet rs = null;

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Venda venda = new Venda(rs);
				venda.setCarrinho(getItensVendaByVenda(Integer.parseInt(venda.getIdVenda().toString())),
						venda.getValorTotal());
				venda.setStatus(tipodao.getTipoByID(rs.getInt("status")));
				venda.setFormaPagto(tipodao.getTipoByID(rs.getInt("formaPagto")));
				venda.setQtdeItensVenda(venda.getCarrinho().getProdutos().size());
				vendas.add(venda);
			}
			if (rs.next()) {

			}
		} catch (Exception e) {

		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return vendas;
	}

}
