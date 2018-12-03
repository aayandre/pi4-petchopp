package com.senac.petchopp.model.tag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tag {

	private Integer idTag;
	private String nome;
	private String nomeView;
	private int idTipo;

	public Tag() {
		super();
	}

	public Tag(Integer idTag, String nome, String nomeView, int idTipo) {
		super();
		this.idTag = idTag;
		this.nome = nome;
		this.nomeView = nomeView;
		this.idTipo = idTipo;
	}

	public Tag(ResultSet rs) throws SQLException {
		super();
		this.idTag = rs.getInt("idTags");
		this.nome = rs.getString("Nome");
		this.nomeView = rs.getString("NomeView");
		this.idTipo = rs.getInt("idTipo");
	}

	public Integer getIdTag() {
		return idTag;
	}

	public void setIdTag(Integer idTag) {
		this.idTag = idTag;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeView() {
		return nomeView;
	}

	public void setNomeView(String nomeView) {
		this.nomeView = nomeView;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	@Override
	public String toString() {
		return "Tag [idTag=" + idTag + ", nome=" + nome + ", nomeView=" + nomeView + ", idTipo=" + idTipo + "]";
	}

}
