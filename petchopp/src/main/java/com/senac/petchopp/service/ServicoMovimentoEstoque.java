package com.senac.petchopp.service;

import com.senac.petchopp.daos.MovimentoEstoqueDAO;
import com.senac.petchopp.exception.MovimentoEstoqueException;
import com.senac.petchopp.model.movimentoEstoque.MovimentoEstoque;
import java.util.Date;

public class ServicoMovimentoEstoque {

    public static void CadastrarMovimentoEstoque(MovimentoEstoque movimentoEstoque) {

        try {
            MovimentoEstoqueDAO.CadastrarMovimentoEstoque(movimentoEstoque);
        } catch (MovimentoEstoqueException e) {
        }
    }

    public static void ListarMovimentoEstoque(Integer idProduto, 
            Date dataInicial, Date dataFinal, Integer tipo, Integer natureza) {

        try {
            MovimentoEstoqueDAO.ListarMovimentoEstoque(idProduto, dataInicial, dataFinal, tipo, natureza);
        } catch (Exception e) {
        }
    }
}
