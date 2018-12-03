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
import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.tag.Tag;
import com.senac.petchopp.model.tipo.TipoTag;
import com.senac.petchopp.wos.FormularioProduto;

public class ProdutoDAO implements IDAO {

	static Connection cn = null;

	public ProdutoDAO() {
	}

	@Override
	public void salvar(Object bean) throws SQLException {

		String produtoSql = "INSERT INTO Produto (idTipo, Codigo, Nome, Descricao, Peso, Preco, Custo, dtCompra, dtValidade, urlImagem, emEstoque, Disable) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		String tagsSql = "INSERT INTO ProdutoTags (idProduto, idTags) VALUES(?, ?)";

		PreparedStatement stmt = null;

		cn = ConnectionFactory.getConnection();

		// Cast do Object para Produto com intuito de utilizar os getters e setters
		FormularioProduto novo = (FormularioProduto) bean;

		try {

			// Produto insert
			stmt = cn.prepareStatement(produtoSql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, novo.getProduto().getIdTipo());
			stmt.setString(2, novo.getProduto().getCodigo());
			stmt.setString(3, novo.getProduto().getNome());
			stmt.setString(4, novo.getProduto().getDescricao());
			stmt.setDouble(5, novo.getProduto().getPeso());
			stmt.setDouble(6, novo.getProduto().getPreco());
			stmt.setDouble(7, novo.getProduto().getCusto());
			stmt.setTimestamp(8, new java.sql.Timestamp(novo.getProduto().getDtCompra().getTime()));
			stmt.setTimestamp(9, new java.sql.Timestamp(novo.getProduto().getDtValidade().getTime()));
			stmt.setString(10, novo.getProduto().getUrlImagem());
			stmt.setBoolean(11, novo.getProduto().isEmEstoque());
			stmt.setBoolean(12, novo.getProduto().isDisable());

			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			stmt.close();

			// Tags insert
			stmt = cn.prepareStatement(tagsSql);
			
			rs.next();
			stmt.setLong(1, rs.getLong(1));
			for (Tag tag : novo.getTagRetorno()) {
				if (tag.getIdTag() != null && tag.getIdTag() > 0) {
					stmt.setInt(2, tag.getIdTag());
					stmt.execute();
				}
			}

		} catch (SQLException e) {
			throw new SQLException("Erro ao salvar o objeto no banco.", e);
		} finally {
			ConnectionFactory.closeConnection(cn, stmt);
		}

	}

	@Override
	public void atualizar(Object bean) {

		String sql = "UPDATE Produto "
				+ "SET Nome = ?, Descricao = ?, Peso = ?, Preco = ?, Custo = ?, dtCompra = ?, dtValidade = ?, urlImagem = ?, emEstoque = ?, Disable = ? "
				+ "WHERE Codigo = ?";

		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();

		Produto alterado = (Produto) bean;

		try {

			stmt = cn.prepareStatement(sql);

			// SET
			stmt.setString(1, alterado.getNome());
			stmt.setString(2, alterado.getDescricao());
			stmt.setDouble(3, alterado.getPreco());
			stmt.setDouble(4, alterado.getCusto());
			stmt.setTimestamp(5, new java.sql.Timestamp(alterado.getDtCompra().getTime()));
			stmt.setTimestamp(6, new java.sql.Timestamp(alterado.getDtValidade().getTime()));
			stmt.setString(7, alterado.getUrlImagem());
			stmt.setBoolean(8, alterado.isEmEstoque());
			stmt.setBoolean(9, alterado.isDisable());

			// WHERE
			stmt.setString(10, alterado.getCodigo());

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
			ConnectionFactory.closeConnection(cn, stmt);
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

	public ArrayList<Produto> getByNome(String nome) throws SQLException {

		String sql = "SELECT * FROM Produto WHERE Nome LIKE ?";
		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> resultados = new ArrayList<>();

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, nome);
			rs = stmt.executeQuery();
			while (rs.next()) {
				resultados.add(new Produto(rs));
			}
			return resultados;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new SQLException("Erro ao adquirir lista de produtos do banco.", e.getCause());
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
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

	// Criar uma query que traga todos os produtos
	// mais vendidos de tal categoria ou algo do tipo
	// em paginacao ou com um limite fixo e um link
	// para a pagina de pesquisa com os mesmo
	public ArrayList<Produto> lista1() {
		String sql = "SELECT * FROM Produto LIMIT 5";
		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();
		ArrayList<Produto> pegos = new ArrayList<>();
		ResultSet rs = null;
		try {
			stmt = cn.prepareStatement(sql);
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

	public List<Produto> getByTipo(String descricao) throws SQLException {
//		String sql = "SELECT Produto.idProduto, Produto.Nome, Produto.Descricao, Produto.Peso, Produto.Preco, "
//				+ "Produto.Custo, Produto.qtdeVendas, Produto.dtCompra, Produto.dtValidade, Produto.urlImagem, "
//				+ "Produto.emEstoque, Produto.Disable FROM Produto "
//				+ "LEFT JOIN ProdutoTags ON Produto.idProduto = ProdutoTags.idProduto "
//				+ "LEFT JOIN Tags ON ProdutoTags.idTags = Tags.idTags " + "LEFT JOIN Tipo ON Tags.idTags = Tipo.idTipo "
//				+ "WHERE Tipo.Descricao = ?";

		String sql = "SELECT Produto.* FROM Produto " + "LEFT JOIN Tipo ON Tipo.idTipo = Produto.idTipo "
				+ "WHERE Tipo.Descricao = ?";

		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Produto> resultados = new ArrayList<>();

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, descricao);
			rs = stmt.executeQuery();
			while (rs.next()) {
				resultados.add(new Produto(rs));
			}
			return resultados;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new SQLException("Erro ao adquirir lista de produtos do banco.", e.getCause());
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
	}

	public List<Produto> getByTipo(int idTipo) throws SQLException {
//		String sql = "SELECT Produto.idProduto, Produto.Codigo, Produto.Nome, Produto.Descricao, Produto.Peso, "
//				+ "Produto.Preco, Produto.Custo, Produto.qtdeVendas, Produto.dtCompra, Produto.dtValidade, "
//				+ "Produto.urlImagem, Produto.emEstoque, Produto.Disable " + "FROM Produto "
//				+ "LEFT JOIN ProdutoTags ON Produto.idProduto = ProdutoTags.idProduto "
//				+ "LEFT JOIN Tags ON ProdutoTags.idTags = Tags.idTags " + "LEFT JOIN Tipo ON Tags.idTags = Tipo.idTipo "
//				+ "WHERE Tipo.idTipo = ?";

		String sql = "SELECT Produto.* FROM Produto WHERE idTipo = ?";

		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Produto> resultados = new ArrayList<>();

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, idTipo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				resultados.add(new Produto(rs));
			}
			return resultados;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new SQLException("Erro ao adquirir lista de produtos do banco.", e.getCause());
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
	}

	public List<Produto> searchByVarios(String procura, String tags) throws SQLException {
		String sql = "SELECT Produto.idProduto, Produto.Codigo, Produto.Nome, Produto.Descricao, Produto.Peso, Produto.Preco,  "
				+ "Produto.Custo, Produto.qtdeVendas, Produto.dtCompra, Produto.dtValidade, Produto.urlImagem,  "
				+ "Produto.emEstoque, Produto.Disable  FROM Produto "
				+ "LEFT JOIN ProdutoTags ON Produto.idProduto = ProdutoTags.idProduto "
				+ "LEFT JOIN Tags ON ProdutoTags.idTags = Tags.idTags " + "WHERE Produto.Nome LIKE '%" + procura
				+ "%'  " + "or Produto.Descricao LIKE '%" + procura + "%'  " + "or Tags.Nome REGEXP '" + tags + "' " + // TODO
																														// Alterar
																														// as
																														// tags
																														// -
																														// Original:
																														// 'Sachê|Biscoito'
				"GROUP BY Produto.idProduto, Produto.Nome, Produto.Descricao, Produto.Peso, Produto.Preco,  "
				+ "Produto.Custo, Produto.qtdeVendas, Produto.dtCompra, Produto.dtValidade, Produto.urlImagem,  "
				+ "Produto.emEstoque, Produto.Disable,   " + "CASE " + "	WHEN Produto.Nome LIKE '" + procura
				+ "%' THEN 1 " + "	WHEN Produto.Nome LIKE '%" + procura + "' THEN 2 " + "	WHEN Produto.Nome LIKE '%"
				+ procura + "%' THEN 3 " + "	WHEN Tags.Nome REGEXP '" + tags + "' THEN 4 " + // TODO Alterar as tags
																								// - Original:
																								// 'Sachê|Biscoito'
				"	WHEN Produto.Descricao LIKE '" + procura + "%' THEN 5 " + "	WHEN Produto.Descricao LIKE '%"
				+ procura + "' THEN 6 " + "	WHEN Produto.Descricao LIKE '%" + procura + "%' THEN 7  " + "END "
				+ "ORDER BY " + "CASE " + "	WHEN Produto.Nome LIKE '" + procura + "%' THEN 1 "
				+ "	WHEN Produto.Nome LIKE '%" + procura + "' THEN 2 " + "	WHEN Produto.Nome LIKE '%" + procura
				+ "%' THEN 3 " + "	WHEN Tags.Nome REGEXP '" + tags + "' THEN 4 " + // TODO Alterar as tags - Original:
																					// 'Sachê|Biscoito'
				"	WHEN Produto.Descricao LIKE '" + procura + "%' THEN 5 " + "	WHEN Produto.Descricao LIKE '%"
				+ procura + "' THEN 6 " + "	WHEN Produto.Descricao LIKE '%" + procura + "%' THEN 7 " + "END";

		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Produto> resultados = new ArrayList<>();

		try {
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				resultados.add(new Produto(rs));
			}
			return resultados;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new SQLException("Erro ao adquirir lista de produtos do banco.", e.getCause());
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
	}

	public void sumProdutoQtdeVenda(Integer quantidade, String codigo) throws SQLException {
		String sql = "UPDATE Produto " + "SET qtdeVendas = (SELECT Produto.qtdeVendas WHERE Produto.Codigo = ?) + ? "
				+ "WHERE Codigo = ?";

		PreparedStatement stmt = null;
		cn = ConnectionFactory.getConnection();

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, codigo);
			stmt.setInt(2, quantidade);
			stmt.setString(3, codigo);

			stmt.execute();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new SQLException("Erro ao adquirir lista de produtos do banco.", e.getCause());
		} finally {
			ConnectionFactory.closeConnection(cn, stmt);
		}
	}

}
