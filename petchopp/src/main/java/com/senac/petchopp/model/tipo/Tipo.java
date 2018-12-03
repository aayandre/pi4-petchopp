package com.senac.petchopp.model.tipo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tipo {

	private int idTipo;
	private String nome;
	private String nomeView;
	private String descricao;

	public Tipo() {
		super();
	}
        
        public Tipo(int idTipo) {
	    this.idTipo = idTipo;
	}

	public Tipo(int idTipo, String nome, String nomeView, String descricao) {
		super();
		this.idTipo = idTipo;
		this.nome = nome;
		this.nomeView = nomeView;
		this.descricao = descricao;
	}

	public Tipo(ResultSet rs) throws SQLException {
		super();
		this.idTipo = rs.getInt("idTipo");
		this.nome = rs.getString("Nome");
		this.nomeView = rs.getString("NomeView");
		this.descricao = rs.getString("Descricao");
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Tipo [idTipo=" + idTipo + ", nome=" + nome + ", nomeView=" + nomeView + ", descricao=" + descricao
				+ "]";
	}

}
