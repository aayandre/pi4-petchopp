package com.senac.petchopp.model.estoqueProduto;

public class EstoqueProdutoRelatorio extends EstoqueProduto{
    private double preco;
    private String nome;
    
    public EstoqueProdutoRelatorio(Long id_produto, int quantidade, double preco, String nome) {
        super(id_produto, quantidade);
        this.preco = preco;
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
