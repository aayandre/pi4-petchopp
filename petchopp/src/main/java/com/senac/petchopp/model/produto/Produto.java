package com.senac.petchopp.model.produto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Produto {

	private Long idProduto;
	private String Codigo;
	private String Nome;
	private String Descricao;
	private double Peso;
	private double Preco;
	private double Custo;
	private int qtdeVendas;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dtCompra;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dtValidade;
	private String urlImagem;
	private boolean EmEstoque;
	private boolean Disable;

	public Produto() {
	}

	// Construtor que preenche o produto para fins de teste
	public Produto(boolean preencher) {
		if (preencher) {
			this.idProduto = Long.valueOf(999);
			this.Codigo = "produtoCriadoPorTeste";
			this.Nome = "Produto Teste";
			this.Descricao = "Descricao do produto teste que tem apenas 500 caracteres de espaço";
			this.Peso = 5.8;
			this.Preco = 399.99;
			this.Custo = 187.67;
			this.qtdeVendas = 4;
			this.dtCompra = new Date();
			this.dtValidade = new Date();
			this.urlImagem = "url da imagem do produto";
			this.EmEstoque = true;
			this.Disable = false;
		} else {
			this.Disable = true;
		}
	}

	public Produto(Long idProduto, String codigo, String nome, String descricao, double peso, double preco,
			double custo, int qtdeVendas, Date dtCompra, Date dtValidade, String urlImagem, boolean emEstoque,
			boolean disable) {
		super();
		this.idProduto = idProduto;
		Codigo = codigo;
		Nome = nome;
		Descricao = descricao;
		Peso = peso;
		Preco = preco;
		Custo = custo;
		this.qtdeVendas = qtdeVendas;
		this.dtCompra = dtCompra;
		this.dtValidade = dtValidade;
		this.urlImagem = urlImagem;
		EmEstoque = emEstoque;
		Disable = disable;
	}

	public Produto(ResultSet rs) throws SQLException {
		super();
		this.idProduto = rs.getLong("idProduto");
		this.Codigo = rs.getString("Codigo");
		this.Nome = rs.getString("Nome");
		this.Descricao = rs.getString("Descricao");
		this.Peso = rs.getDouble("Peso");
		this.Preco = rs.getDouble("Preco");
		this.Custo = rs.getDouble("Custo");
		this.qtdeVendas = rs.getInt("qtdeVendas");
		this.dtCompra = rs.getTimestamp("dtCompra");
		this.dtValidade = rs.getTimestamp("dtValidade");
		this.urlImagem = rs.getString("urlImagem");
		this.EmEstoque = rs.getBoolean("emEstoque");
		this.Disable = rs.getBoolean("Disable");
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
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

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public double getPeso() {
		return Peso;
	}

	public void setPeso(double peso) {
		Peso = peso;
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

	public int getQtdeVendas() {
		return qtdeVendas;
	}

	public void setQtdeVendas(int qtdeVendas) {
		this.qtdeVendas = qtdeVendas;
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

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", Codigo=" + Codigo + ", Nome=" + Nome + ", Descricao=" + Descricao
				+ ", Peso=" + Peso + ", Preco=" + Preco + ", Custo=" + Custo + ", qtdeVendas=" + qtdeVendas
				+ ", dtCompra=" + dtCompra + ", dtValidade=" + dtValidade + ", urlImagem=" + urlImagem + ", EmEstoque="
				+ EmEstoque + ", Disable=" + Disable + "]";
	}

}
