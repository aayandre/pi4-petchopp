package com.senac.petchopp.model.tag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tag {

	private int idTags;
	private String nome;
	private int idTipo;

	public Tag() {
		super();
	}

	public Tag(int idTags, String nome, int idTipo) {
		super();
		this.idTags = idTags;
		this.nome = nome;
		this.idTipo = idTipo;
	}

	public Tag(ResultSet rs) throws SQLException {
		super();
		this.idTags = rs.getInt("idTags");
		this.nome = rs.getString("Nome");
		this.idTipo = rs.getInt("idTipo");
	}

	public int getIdTags() {
		return idTags;
	}

	public void setIdTags(int idTags) {
		this.idTags = idTags;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	@Override
	public String toString() {
		return "Tag [idTags=" + idTags + ", nome=" + nome + ", idTipo=" + idTipo + "]";
	}

}
