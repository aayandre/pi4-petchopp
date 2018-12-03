package com.senac.petchopp.daos;

import com.senac.petchopp.auxiliares.AuxiliaresDAO;
import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.exception.MovimentoEstoqueException;
import com.senac.petchopp.model.movimentoEstoque.MovimentoEstoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimentoEstoqueDAO {

    public static void CadastrarMovimentoEstoque(MovimentoEstoque movimentoEstoque)
            throws MovimentoEstoqueException {
        PreparedStatement stmt = null;

        String sql = "INSERT INTO MovimentoEstoque (dtMovimento, Quantidade, idTipo, idNatureza, idProduto) "
                + "VALUES (?, ?, ?, ?, ?)";

        Connection cn = ConnectionFactory.getConnection();

        try {
            stmt = cn.prepareStatement(sql);

            stmt.setTimestamp(1, new java.sql.Timestamp(movimentoEstoque.getData().getTime()));
            stmt.setInt(2, movimentoEstoque.getQuantidade());
            stmt.setInt(3, movimentoEstoque.getTipo());
            stmt.setInt(4, movimentoEstoque.getNatureza());
            stmt.setLong(5, movimentoEstoque.getIdProduto());
            stmt.execute();

        } catch (Exception e) {
            throw new MovimentoEstoqueException("Erro ao cadastrar movimento de estoque.", e.getCause());
        } finally {
            ConnectionFactory.closeConnection(cn, stmt);
        }
    }

    public static List<MovimentoEstoque> ListarMovimentoEstoque(Integer idProduto, 
            Date dataInicial, Date dataFinal, Integer tipo, Integer natureza) {

        ResultSet rs = null;
        PreparedStatement stmt = null;
        String[] strWhere = new String[6];
        String sql = "";
        List<MovimentoEstoque> listaMovimentoEstoque = new ArrayList<>();

        if (idProduto != null) {
            strWhere[0] = "idProduto = " + idProduto;
        }
        if (dataInicial != null || dataFinal != null) {
            strWhere[2] = AuxiliaresDAO.filtraRangeDate("data", dataInicial, dataFinal);
        }
        if (tipo != null) {
            strWhere[3] = "idTipo = " + tipo;
        }
        if (natureza != null) {
            strWhere[4] = "idNatureza = " + natureza;
        }

        if (strWhere[0] != null || strWhere[1] != null || strWhere[2] != null
                || strWhere[3] != null || strWhere[4] != null) {
            sql = "SELECT * FROM estoque WHERE " + AuxiliaresDAO.ligaVetorAND(strWhere);
        } else {
            sql = "SELECT idProduto FROM estoque";
        }

        Connection cn = ConnectionFactory.getConnection();

        try {
            stmt = cn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                MovimentoEstoque movimentoEstoque = new MovimentoEstoque(
                        rs.getLong("id_produto"),
                        rs.getInt("Quantidade"),
                        rs.getDate("DataMovimento"),
                        rs.getInt("Tipo"),
                        rs.getInt("Natureza"));
                listaMovimentoEstoque.add(movimentoEstoque);
            }

        } catch (Exception e) {

        } finally {
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }
        return listaMovimentoEstoque;
    }

}
