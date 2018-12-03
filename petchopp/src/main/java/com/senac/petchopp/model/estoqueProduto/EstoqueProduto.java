package com.senac.petchopp.model.estoqueProduto;

import com.senac.petchopp.daos.EstoqueProdutoDAO;

public class EstoqueProduto {
    private Long idProduto;
    private int quantidade;
    private int quantidadeMovimento;
    
    public EstoqueProduto(Long idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public EstoqueProduto(Long idProduto, int quantidade, int quantidadeMovimento) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.quantidadeMovimento = quantidadeMovimento;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeMovimento() {
        return quantidadeMovimento;
    }

    public void setQuantidadeMovimento(int quantidadeMovimento) {
        this.quantidadeMovimento = quantidadeMovimento;
    }
    
    public boolean possuiCadastroEstoque(){
        return EstoqueProdutoDAO.PossuiCadastroEstoque(this.idProduto);
    }
}
