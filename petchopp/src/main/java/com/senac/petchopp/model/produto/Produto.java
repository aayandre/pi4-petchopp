package com.senac.petchopp.model.produto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Produto {

    private Long idProduto;
    private String Codigo;
    private String Nome;
    private double Preco;
    private double Custo;
    private String Descricao;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dtCompra;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtValidade;
    private String urlImagem;
    private boolean EmEstoque;
    private boolean Disable;

    private Integer quantidade;

    public Produto() {
    }

    ;

	// Construtor que preenche o produto para fins de teste
	public Produto(boolean preencher) {
        if (preencher) {
            this.idProduto = Long.valueOf(999);
            this.Codigo = "produtoCriadoPorTeste";
            this.Nome = "Produto Teste";
            this.Preco = 399.99;
            this.Custo = 187.67;
            this.Descricao = "Descricao do produto teste que tem apenas 500 caracteres de espaço";
            this.dtCompra = LocalDate.now();
            this.dtValidade = LocalDate.now();
            this.urlImagem = "url da imagem do produto";
            this.EmEstoque = true;
            this.Disable = false;
        } else {
            this.Disable = true;
        }
    }

    public Produto(Long idProduto, String codigo, String nome, double preco, double custo, String descricao,
            LocalDate dtCompra, LocalDate dtValidade, String urlImagem, boolean emEstoque, boolean disable) {
        super();
        this.idProduto = idProduto;
        Codigo = codigo;
        Nome = nome;
        Preco = preco;
        Custo = custo;
        Descricao = descricao;
        this.dtCompra = dtCompra;
        this.dtValidade = dtValidade;
        this.urlImagem = urlImagem;
        EmEstoque = emEstoque;
        Disable = disable;
    }

    public Produto(ResultSet rs) throws SQLException {
        super();
        this.idProduto = rs.getLong("idProduto");
        Codigo = rs.getString("Codigo");
        Nome = rs.getString("Nome");
        Preco = rs.getDouble("Preco");
        Custo = rs.getDouble("Custo");
        Descricao = rs.getString("Descricao");
        this.dtCompra = rs.getDate("dtCompra").toLocalDate();
        this.dtValidade = rs.getDate("dtValidade").toLocalDate();
        this.urlImagem = rs.getString("urlImagem");
        EmEstoque = rs.getBoolean("emEstoque");
        Disable = rs.getBoolean("Disable");
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

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getDescricao() {
        return Descricao;
    }

    public LocalDate getDtCompra() {
        return dtCompra;
    }

    public void setDtCompra(LocalDate dtCompra) {
        this.dtCompra = dtCompra;
    }

    public LocalDate getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(LocalDate dtValidade) {
        this.dtValidade = dtValidade;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public void setUrlImagemPath(String fileName) {
        this.urlImagem = fileName;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
