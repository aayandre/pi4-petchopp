package com.senac.petchopp.model.produto;

import java.util.Date;

public class Produto {

	private long idProduto;
	private int Codigo;
	private String Nome;
	private double Preco;
	private double Custo;
	private Date dtCompra;
	private Date dtValidade;
	private String urlImagem;
	private boolean EmEstoque;
	private boolean Disable;

	public Produto() {
	};

	public Produto(long idProduto, int codigo, String nome, double preco, double custo, Date dtCompra, Date dtValidade,
			String urlImagem, boolean emEstoque, boolean disable) {
		super();
		this.idProduto = idProduto;
		Codigo = codigo;
		Nome = nome;
		Preco = preco;
		Custo = custo;
		this.dtCompra = dtCompra;
		this.dtValidade = dtValidade;
		this.urlImagem = urlImagem;
		EmEstoque = emEstoque;
		Disable = disable;
	}

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public double getPreco() {
		return Preco;
	}

	public void setPreco(double preco) {
		Preco = preco;
	}

	public double getCusto() {
		return Custo;
	}

	public void setCusto(double custo) {
		Custo = custo;
	}

	public Date getDtCompra() {
		return dtCompra;
	}

	public void setDtCompra(Date dtCompra) {
		this.dtCompra = dtCompra;
	}

	public Date getDtValidade() {
		return dtValidade;
	}

	public void setDtValidade(Date dtValidade) {
		this.dtValidade = dtValidade;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public boolean isEmEstoque() {
		return EmEstoque;
	}

	public void setEmEstoque(boolean emEstoque) {
		EmEstoque = emEstoque;
	}

	public boolean isDisable() {
		return Disable;
	}

	public void setDisable(boolean disable) {
		Disable = disable;
	}

}
