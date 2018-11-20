package com.senac.petchopp.model.tag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tag {

	private int idTag;
	private String nome;
	private int idTipo;

	public Tag() {
		super();
	}

	public Tag(int idTags, String nome, int idTipo) {
		super();
		this.idTag = idTags;
		this.nome = nome;
		this.idTipo = idTipo;
	}

	public Tag(ResultSet rs) throws SQLException {
		super();
		this.idTag = rs.getInt("idTags");
		this.nome = rs.getString("Nome");
		this.idTipo = rs.getInt("idTipo");
	}

	public int getIdTags() {
		return idTag;
	}

	public void setIdTags(int idTags) {
		this.idTag = idTags;
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
		return "Tag [idTags=" + idTag + ", nome=" + nome + ", idTipo=" + idTipo + "]";
	}

}
