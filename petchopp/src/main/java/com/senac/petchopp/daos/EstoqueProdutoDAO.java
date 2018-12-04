package com.senac.petchopp.daos;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.auxiliares.AuxiliaresDAO;
import com.senac.petchopp.model.estoqueProduto.EstoqueProduto;
import com.senac.petchopp.model.estoqueProduto.EstoqueProdutoRelatorio;
import com.senac.petchopp.model.tipo.Tipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstoqueProdutoDAO {

    //Metodo para verificar se já existe uma linha no banco para o produto + pessoa informado no objeto.
    public static boolean PossuiCadastroEstoque(Long id_produto) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        
        String sql = "SELECT idProduto FROM Estoque WHERE idProduto = ?";
        
        Connection cn = ConnectionFactory.getConnection();
        
        try {
            stmt = cn.prepareStatement(sql);
            stmt.setLong(1, id_produto);
            
            rs = stmt.executeQuery();
            
            return rs.next();
            
        } catch (Exception e) {
            
        } finally {
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }
        return false;
    }
    
    public static void CadastrarEstoque(EstoqueProduto estoqueProduto) {
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO Estoque (idProduto, quantidade) VALUES (?, ?)";
        
        Connection cn = ConnectionFactory.getConnection();
        
        try {
            stmt = cn.prepareStatement(sql);
            
            stmt.setLong(1, estoqueProduto.getIdProduto());
            stmt.setInt(2, estoqueProduto.getQuantidade());
            stmt.execute();
            
        } catch (Exception e) {
            
        } finally {
            ConnectionFactory.closeConnection(cn, stmt);
        }
    }
    
    public static List<EstoqueProduto> ListaProdutosEstoqueById(long[] idsProduto)
            throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs;
        
        List<EstoqueProduto> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM Estoque WHERE (";
        
        int counter = 0;
        for (long i : idsProduto) {
            if (counter == 0) {
                sql += "idProduto = " + i;
            } else {
                sql += " or idProduto = " + i;
            }
            if (counter == idsProduto.length - 1) {
                sql += ")";
            }
            counter++;
        }
        
        Connection cn = ConnectionFactory.getConnection();
        
        try {
            stmt = cn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                EstoqueProduto estoqueProduto = new EstoqueProduto(
                        rs.getLong("idProduto"),
                        rs.getInt("Quantidade"));
                
                lista.add(estoqueProduto);
            }
            
            return lista;
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar os produtos em estoque", e.getCause());
        } finally {
            ConnectionFactory.closeConnection(cn, stmt);
        }
    }
    
    public static void AtualizarEstoque(EstoqueProduto estoqueProduto) {
        PreparedStatement stmt = null;
        
        String sql = "UPDATE Estoque SET idProduto = ?, Quantidade = ? "
                + "WHERE idProduto = ?;";

        Connection cn = ConnectionFactory.getConnection();
        
        try {
            stmt = cn.prepareStatement(sql);
            
            stmt.setLong(1, estoqueProduto.getIdProduto());
            stmt.setInt(2, estoqueProduto.getQuantidade());
            //WHERE
            stmt.setLong(3, estoqueProduto.getIdProduto());
            stmt.execute();
            
        } catch (Exception e) {
            
        } finally {
            ConnectionFactory.closeConnection(cn, stmt);
        }
    }

    // Poderá ser listado estoque por: item ou empresa
    public static List<EstoqueProdutoRelatorio> ListarEstoque(int[] tipos, String nome) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        String[] strWhere = new String[2];
        String sql = "";
        List<EstoqueProdutoRelatorio> listaEstoqueProduto = new ArrayList<>();
        
        if (!nome.isEmpty()) {
            strWhere[0] = "produto.nome LIKE %" + nome + "%";
        }
        
        if (tipos.length != 0) {
            strWhere[1] = "produto.idTipo IN " + AuxiliaresDAO.montaIN(tipos);
        }
        
        if (strWhere[0] != null || strWhere[1] != null) {
            sql = "SELECT * FROM Estoque INNER JOIN produto ON produto.idProduto = estoque.idProduto "
                    + "WHERE " + AuxiliaresDAO.ligaVetorAND(strWhere);
        } else {
            sql = "SELECT * FROM Estoque INNER JOIN produto ON produto.idProduto = estoque.idProduto";
        }
        
        Connection cn = ConnectionFactory.getConnection();
        
        try {
            stmt = cn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                EstoqueProdutoRelatorio estoqueProduto = new EstoqueProdutoRelatorio(
                        rs.getLong("idProduto"),
                        rs.getInt("quantidade"),
                        rs.getString("nome"));
                listaEstoqueProduto.add(estoqueProduto);
            }
            
        } catch (Exception e) {
            
        } finally {
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }
        return listaEstoqueProduto;
    }

    public static int getQuantidadeByIdProduto(Long idproduto)
            throws SQLException {
        ResultSet rs = null;
        PreparedStatement stmt = null;

        String sql = "SELECT * FROM Estoque WHERE idProduto = ?";

        Connection cn = ConnectionFactory.getConnection();

        try {
            stmt = cn.prepareStatement(sql);

            stmt.setLong(1, idproduto);

            rs = stmt.executeQuery();

            if (rs.next()){
                return rs.getInt("Quantidade");
            }else{
                return 0;
            }
            

        } catch (SQLException e) {
            throw new SQLException("Erro ao obter quantidade do produto em estoque por id do produto.(EstoqueProdutoDAO)", e.getCause());
        } finally {
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }
    }
}
