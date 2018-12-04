package com.senac.petchopp.service;

import com.senac.petchopp.daos.EstoqueProdutoDAO;
import com.senac.petchopp.exception.EstoqueException;
import com.senac.petchopp.model.estoqueProduto.EstoqueProduto;
import com.senac.petchopp.model.movimentoEstoque.MovimentoEstoque;
import com.senac.petchopp.model.produto.ProdutoVenda;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoEstoqueProduto {

    //Esse metodo irá checar se é nessário chamar o cadastro ou atualização de estoque.
    public void AtualizarEstoque(EstoqueProduto estoqueProduto) {
        //Deverá conter validações para que não seja permitido realizar uma transação inválida (estoque negativo).
        try {
            if (estoqueProduto.possuiCadastroEstoque()) {
                EstoqueProdutoDAO.AtualizarEstoque(estoqueProduto);
            } else {
                EstoqueProdutoDAO.CadastrarEstoque(estoqueProduto);
            }

            //Após atualizar o estoque, chamar o serviço para armazenar a movimentação.
            //Date dataAtual = Calendar.getInstance().getTime();
            MovimentoEstoque movimentoEstoque = new MovimentoEstoque(
                    estoqueProduto.getIdProduto(),
                    estoqueProduto.getQuantidadeMovimento(),
                    new Date(),
                    1, //Falta definir 
                    1);//Falta definir
            ServicoMovimentoEstoque.CadastrarMovimentoEstoque(movimentoEstoque);
        } catch (Exception e) {

        }
    }

    public void AtualizarEstoqueVenda(EstoqueProduto estoqueProduto) {
        //Deverá conter validações para que não seja permitido realizar uma transação inválida (estoque negativo).
        try {
            if (estoqueProduto.possuiCadastroEstoque()) {
                EstoqueProdutoDAO.AtualizarEstoque(estoqueProduto);
            } else {
                EstoqueProdutoDAO.CadastrarEstoque(estoqueProduto);
            }

            //Após atualizar o estoque, chamar o serviço para armazenar a movimentação.
            Date dataAtual = Calendar.getInstance().getTime();
            MovimentoEstoque movimentoEstoque = new MovimentoEstoque(
                    estoqueProduto.getIdProduto(),
                    estoqueProduto.getQuantidade(),
                    dataAtual,
                    1, //Tipo venda sera 1 mesmo
                    1);//Natureza ainda n sei
            ServicoMovimentoEstoque.CadastrarMovimentoEstoque(movimentoEstoque);
        } catch (Exception e) {

        }
    }

    public List<EstoqueProduto> ListarEstoquePorIdsProduto(List<ProdutoVenda> produtos) {

        long[] idsProdutos = new long[produtos.size()];
        List<EstoqueProduto> listaAtual = new ArrayList<>();

        int counter = 0;
        for (ProdutoVenda produto : produtos) {
            idsProdutos[counter] = produto.getIdProduto();
            counter++;
        }

        try {
            listaAtual = EstoqueProdutoDAO.ListaProdutosEstoqueById(idsProdutos);
        } catch (SQLException ex) {
            Logger.getLogger(ServicoEstoqueProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAtual;
    }

    public void listarEstoque(int[] tipos, String nome) {

        try {
            EstoqueProdutoDAO.ListarEstoque(tipos, nome);
        } catch (Exception e) {

        }
    }

    public int ObtemQuantidadeByIdProduto(Long idproduto)
            throws EstoqueException {

        try {
            return EstoqueProdutoDAO.getQuantidadeByIdProduto(idproduto);
        } catch (SQLException ex) {
            throw new EstoqueException("Erro ao obter a quantidade.(ServicoEstoqueProduto)", ex.getCause());
        }
    }
    
    public String validaQtdeEstoquePedido(List<EstoqueProduto> estoqueProdutos, ArrayList<ProdutoVenda> produtosVenda){
        
        try {
            for (ProdutoVenda produtoVenda : produtosVenda) {
                for (EstoqueProduto estoqueProduto : estoqueProdutos) {
                    if (produtoVenda.getIdProduto() == estoqueProduto.getIdProduto()){
                        if (produtoVenda.getQuantidade() > estoqueProduto.getQuantidade()){
                            return "Quantidade insuficiente em estoque para o item '" + produtoVenda.getNome() + "'";
                        }else{
                            //Atualizando a quantidade do estoque no objeto
                            estoqueProduto.setQuantidade(estoqueProduto.getQuantidade() - produtoVenda.getQuantidade());
                            estoqueProduto.setQuantidadeMovimento(produtoVenda.getQuantidade());
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        
        return null;
    }
}
