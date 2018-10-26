package com.senac.petchopp.model;

public class TipoCategoria {

	private int id;
	private int codigo;
	private String descricao;
	private String categoria;

	public TipoCategoria() {
	}

	public TipoCategoria(int id, int codigo, String descricao, String categoria) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
