package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.cliente.Endereco;
import com.senac.petchopp.model.produto.ProdutoVenda;
import com.senac.petchopp.model.venda.Venda;

public class VendaDAO implements IDAO {

	private static Connection cn = null;
	private TipoDAO tipodao = new TipoDAO();

	@Override
	public void salvar(Object bean) throws SQLException {
		String sql = "INSERT INTO Venda (idCliente, idEndereco, Protocolo, Data, ValorTotal, idFormaPagto, status) " + "VALUES(?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = null;

		cn = ConnectionFactory.getConnection();

		Venda venda = (Venda) bean;

		try {

			stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setLong(1, venda.getIdCliente());
                        stmt.setLong(2, venda.getEndereco().getIdEndereco());
			stmt.setString(3, venda.getProtocolo());
			stmt.setTimestamp(4, new java.sql.Timestamp(venda.getData().getTime()));
			stmt.setDouble(5, venda.getValorTotal());
                        stmt.setLong(6, venda.getFormaPagto().getIdTipo());
                        stmt.setLong(7, venda.getStatus().getIdTipo());

			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();
			Venda retornada = new Venda();
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				retornada = (Venda) getById(rs.getLong(1));
			}
			retornada.setCarrinho(venda.getCarrinho());
			salvarItensVenda(retornada);

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
		String sql = "SELECT * FROM Venda WHERE idVenda = ? LIMIT 1";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();
		Venda adiquirida = new Venda();
		ResultSet rs = null;

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				adiquirida = new Venda(rs);
			}
			return adiquirida;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
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
			stmt.setTimestamp(3, new java.sql.Timestamp(venda.getData().getTime()));
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
		String sql = "INSERT INTO ItemVenda (idVenda, idProduto, Quantidade, Valor, total) VALUES(?, ?, ?, ?, ?)";

		PreparedStatement stmt = null;

		cn = ConnectionFactory.getConnection();

		try {

			for (ProdutoVenda produto : venda.getCarrinho().getProdutos()) {
				stmt = cn.prepareStatement(sql);

				stmt.setLong(1, venda.getIdVenda());
				stmt.setLong(2, produto.getIdProduto());
				stmt.setInt(3, produto.getQuantidade().intValue());
				stmt.setDouble(4, produto.getValor());
				stmt.setDouble(5, produto.getValor()*produto.getQuantidade());

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

	public List<Venda> getVendasByCliente(Long idCliente) throws SQLException {
		String sql = "SELECT * FROM Venda WHERE idCliente = ?";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();
		List<Venda> vendas = new ArrayList<>();
		ResultSet rs = null;
		ProdutoVendaDAO prodVendaDAO = new ProdutoVendaDAO();
                EnderecoDAO enderecoDAO = new EnderecoDAO();
		try {
			stmt = cn.prepareStatement(sql);
			stmt.setLong(1, idCliente);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Venda venda = new Venda(rs);
				venda.setCarrinho(prodVendaDAO.getProdutoVendaByVenda(Integer.parseInt(venda.getIdVenda().toString())),
						venda.getValorTotal());
				venda.setStatus(tipodao.getTipoByID(rs.getInt("status")));
				venda.setFormaPagto(tipodao.getTipoByID(rs.getInt("idFormaPagto")));
				venda.setQtdeItensVenda(venda.getCarrinho().getProdutos().size());
                                venda.setEndereco((Endereco)enderecoDAO.getById(rs.getLong("idEndereco")));
				vendas.add(venda);
			}
			return vendas;
		} catch (SQLException e) {
			throw new SQLException("Erro ao adquirir as vendas do cliente.",e.getCause());
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
	}

}
