package com.senac.petchopp.model.estoqueProduto;

public class EstoqueProdutoRelatorio extends EstoqueProduto{
    private String nome;
    
    public EstoqueProdutoRelatorio(Long id_produto, int quantidade, String nome) {
        super(id_produto, quantidade);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
