package com.senac.petchopp.daos;

import static com.senac.petchopp.daos.ProdutoDAO.cn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.model.produto.ProdutoVenda;

public class ProdutoVendaDAO {
    
    public ArrayList<ProdutoVenda> getProdutoVendaByVenda(long idVenda){
        String sql = "SELECT ItemVenda.*, Produto.codigo, Produto.nome, Produto.urlImagem " + 
                "FROM ItemVenda INNER JOIN Produto ON ItemVenda.idProduto = Produto.idProduto " + 
                "WHERE ItemVenda.idVenda = ?";
        PreparedStatement stmt = null;
        cn = ConnectionFactory.getConnection();
        ArrayList<ProdutoVenda> ProdutosVenda = new ArrayList<>();
        ResultSet rs = null;

        try {
            stmt = cn.prepareStatement(sql);
            stmt.setLong(1, idVenda);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutoVenda produtoVenda = new ProdutoVenda(rs);
                ProdutosVenda.add(produtoVenda);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }
        return ProdutosVenda;
    }
}
