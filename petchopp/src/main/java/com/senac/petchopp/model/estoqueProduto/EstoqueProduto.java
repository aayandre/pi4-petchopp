package com.senac.petchopp.model.estoqueProduto;

import com.senac.petchopp.daos.EstoqueProdutoDAO;

public class EstoqueProduto {
    private int idProduto;
    private int quantidade;

    public EstoqueProduto(int idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
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

    public boolean possuiCadastroEstoque(){
        return EstoqueProdutoDAO.PossuiCadastroEstoque(this.idProduto);
    }
}
