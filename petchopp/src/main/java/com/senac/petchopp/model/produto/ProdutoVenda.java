package com.senac.petchopp.model.produto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProdutoVenda {

    private Long idProduto;
    private String codigo;
    private String nome;
    private double valor;
    private double total;
    private String urlImagem;
    private Integer quantidade;

    public ProdutoVenda() {
    }

    public ProdutoVenda(Long idProduto, String codigo, String nome, double valor, double total, String urlImagem, Integer quantidade) {
        this.idProduto = idProduto;
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.total = total;
        this.urlImagem = urlImagem;
        this.quantidade = quantidade;
    }

    public ProdutoVenda(ResultSet rs) throws SQLException {
        this.idProduto = rs.getLong("idProduto");
        this.codigo = rs.getString("Codigo");
        this.nome = rs.getString("Nome");
        this.valor = rs.getDouble("valor");
        this.total = rs.getDouble("total");
        this.urlImagem = rs.getString("urlImagem");
        this.quantidade = rs.getInt("quantidade");
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
