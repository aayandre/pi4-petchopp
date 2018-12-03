package com.senac.petchopp.model.venda;

import com.senac.petchopp.model.tipo.Tipo;

public class FormaPagto {
    private Tipo idFormaPagto;
    private String documento;
    private String nome;
    private String vencimento;
    private String codSeguranca;

    public FormaPagto() {
    }

    public FormaPagto(Tipo idFormaPagto, String documento, String nome, String vencimento, String codSeguranca) {
        this.idFormaPagto = idFormaPagto;
        this.documento = documento;
        this.nome = nome;
        this.vencimento = vencimento;
        this.codSeguranca = codSeguranca;
    }

    public Tipo getIdFormaPagto() {
        return idFormaPagto;
    }

    public void setIdFormaPagto(Tipo idFormaPagto) {
        this.idFormaPagto = idFormaPagto;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getCodSeguranca() {
        return codSeguranca;
    }

    public void setCodSeguranca(String codSeguranca) {
        this.codSeguranca = codSeguranca;
    }
}
