package com.senac.petchopp.model.movimentoEstoque;

import java.util.Calendar;
import java.util.Date;

public class MovimentoEstoque {

    private int idMovimento;
    private int idProduto;
    private int quantidade;
    private Date Data;
    private Calendar dataCalendar;
    private int tipo;
    private int natureza;

    public MovimentoEstoque(int idProduto, int quantidade, Date Data, int tipo, int natureza) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.Data = Data;
        setDataCalendar();
        this.tipo = tipo;
        this.natureza = natureza;
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getNatureza() {
        return natureza;
    }

    public void setNatureza(int natureza) {
        this.natureza = natureza;
    }

    public Calendar getDataCalendar() {
        return dataCalendar;
    }

    private void setDataCalendar() {
        Calendar dataCal = Calendar.getInstance();
        dataCal.setTime(this.Data);
        this.dataCalendar = dataCal;
    }
}
