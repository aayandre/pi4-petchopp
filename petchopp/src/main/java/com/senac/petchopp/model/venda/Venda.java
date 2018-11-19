package com.senac.petchopp.model.venda;

import com.senac.petchopp.model.Tipo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import com.senac.petchopp.model.carrinho.Carrinho;
import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.produto.ProdutoVenda;
import java.sql.Date;
import java.sql.Timestamp;

public class Venda {

    private Long idVenda;
    private Long idCliente;
    private Long idFretes;
    private String protocolo;
    private double valorFrete;
    private Timestamp data;
    private double valorTotal;
    private Tipo status;
    private Tipo formaPagto;
    private int qtdeItensVenda;
    private Carrinho carrinho;
    private LocalDateTime dataView;

    public Venda() {
        super();
    }

    public Venda(Long idVenda, Long idCliente, Long idFretes, double valorFrete, Timestamp data, double valorTotal) {
        super();
        this.idVenda = idVenda;
        this.idCliente = idCliente;
        this.idFretes = idFretes;
        this.valorFrete = valorFrete;
        this.data = data;
        this.valorTotal = valorTotal;
        this.qtdeItensVenda = 0;
    }

    public Venda(ResultSet rs) throws SQLException {
        super();
        this.idVenda = rs.getLong("idVenda");
        this.idCliente = rs.getLong("idCliente");
        this.data = rs.getTimestamp("data");
        this.protocolo = rs.getString("Protocolo");
        this.valorTotal = rs.getDouble("valorTotal");
        this.dataView = LocalDateTime.ofInstant(Instant.ofEpochMilli(rs.getTimestamp("data").getTime()),
                ZoneId.systemDefault());
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdFretes() {
        return idFretes;
    }

    public void setIdFretes(Long idFretes) {
        this.idFretes = idFretes;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public void setCarrinho(ArrayList<ProdutoVenda> produtos, Double valorTotal) {
        this.carrinho = new Carrinho(produtos, valorTotal);
    }

    public LocalDateTime getDataView() {
        return dataView;
    }

    public void setDataView(LocalDateTime dataView) {
        this.dataView = dataView;
    }

    public void setCarrinhoByVenda(int idVenda) {
        this.carrinho = null;
    }

    public Tipo getStatus() {
        return status;
    }

    public void setStatus(Tipo status) {
        this.status = status;
    }

    public Tipo getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(Tipo formaPagto) {
        this.formaPagto = formaPagto;
    }

    public int getQtdeItensVenda() {
        return qtdeItensVenda;
    }

    public void setQtdeItensVenda(int qtdeItensVenda) {
        this.qtdeItensVenda = qtdeItensVenda;
    }
}
