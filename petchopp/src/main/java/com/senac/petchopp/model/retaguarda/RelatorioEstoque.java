/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.model.retaguarda;

/**
 *
 * @author macop
 */
public class RelatorioEstoque {
    
    private String codigo;
    private String nome;
    private Integer saida;
    private Integer estoque;

    public RelatorioEstoque() {
    }

    public RelatorioEstoque(String codigo, String nome, Integer saida, Integer estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.saida = saida;
        this.estoque = estoque;
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

    public Integer getSaida() {
        return saida;
    }

    public void setSaida(Integer saida) {
        this.saida = saida;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
    
    
    
}
