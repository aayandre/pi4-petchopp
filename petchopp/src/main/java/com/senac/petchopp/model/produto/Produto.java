package com.senac.petchopp.model.produto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.senac.petchopp.model.Auxiliares;

public class Produto {

	private long idProduto;
	private String Codigo;
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

	public Produto(long idProduto, String codigo, String nome, double preco, double custo, Date dtCompra,
			Date dtValidade, String urlImagem, boolean emEstoque, boolean disable) {
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

	public Produto(ResultSet rs) throws SQLException {
		super();
		this.idProduto = rs.getLong(1);
		Codigo = rs.getString(2);
		Nome = rs.getString(3);
		Preco = rs.getDouble(4);
		Custo = rs.getDouble(5);
		this.dtCompra = Auxiliares.SqlDateToUtilDate(rs.getDate(6));
		this.dtValidade = Auxiliares.SqlDateToUtilDate(rs.getDate(7));
		this.urlImagem = rs.getString(8);
		EmEstoque = rs.getBoolean(9);
		Disable = rs.getBoolean(10);
	}

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
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
