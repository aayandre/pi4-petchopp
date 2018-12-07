/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.daos;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.retaguarda.RelatorioEstoque;
import com.senac.petchopp.model.venda.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macop
 */
public class RelatorioDAO implements IDAO {

    private static Connection cn = null;

    @Override
    public void salvar(Object bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Object bean) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getById(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<RelatorioEstoque> getEstoqueAtual() throws SQLException {
        String sql = "SELECT * FROM EstoqueAtualVW";
        PreparedStatement stmt = null;
        cn = ConnectionFactory.getConnection();
        List<RelatorioEstoque> estoque = new ArrayList<>();
        ResultSet rs = null;

        try {
            stmt = cn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                RelatorioEstoque rl = new RelatorioEstoque();

                rl.setCodigo(rs.getString("Codigo"));
                rl.setNome(rs.getString("nome"));
                rl.setSaida(rs.getInt("Saida"));
                rl.setEstoque(rs.getInt("EstoqueAtual"));
                estoque.add(rl);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao adquirir as vendas do cliente.", e.getCause());
        } finally {
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }

        return estoque;
    }

}
