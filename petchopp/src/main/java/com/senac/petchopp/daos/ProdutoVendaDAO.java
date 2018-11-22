package com.senac.petchopp.daos;

import com.senac.petchopp.connection.ConnectionFactory;
import static com.senac.petchopp.daos.ProdutoDAO.cn;
import com.senac.petchopp.model.produto.ProdutoVenda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoVendaDAO {
    
    public List<ProdutoVenda> getProdutoVendaByVenda(long idVenda){
        String sql = "SELECT ItemVenda.*, Produto.codigo, Produto.nome, Produto.urlImagem " + 
                "FROM ItemVenda INNER JOIN Produto ON ItemVenda.idProduto = Produto.idProduto " + 
                "WHERE ItemVenda.idVenda = ?";
        PreparedStatement stmt = null;
        cn = ConnectionFactory.getConnection();
        List<ProdutoVenda> ProdutosVenda = new ArrayList<>();
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

        } finally {
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }
        return ProdutosVenda;
    }
}
