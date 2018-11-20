package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.tag.Tag;

public class TagDAO implements IDAO {

	private Connection cn = null;

	public TagDAO() {

	}

	@Override
	public void salvar(Object bean) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void atualizar(Object bean) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletar(Long id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getById(Long id) throws SQLException {
		int idInt = Integer.parseInt(id.toString());
		String sql = "SELECT * FROM Tags WHERE idTags = ?";
		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Tag tag = null;

		try {
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, idInt);
			rs = stmt.executeQuery();
			while (rs.next()) {
				tag = new Tag(rs);
			}
			return tag;
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return tag;
	}

	@Override
	public List<Object> getAll() throws SQLException {
		String sql = "SELECT * FROM Tags";
		cn = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Object> tags = new ArrayList<>();

		try {
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				tags.add(new Tag(rs));
			}
			return tags;
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.closeConnection(cn, stmt, rs);
		}
		return tags;
	}

}
