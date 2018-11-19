package com.senac.petchopp.daos;

import com.senac.petchopp.connection.ConnectionFactory;
import static com.senac.petchopp.daos.ProdutoDAO.cn;
import com.senac.petchopp.model.Tipo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TipoDAO {
    
    public static Tipo getTipoByID(int idTipo){
        String sql = "SELECT * FROM Tipo WHERE idTipo = ?";
        PreparedStatement stmt = null;
        cn = ConnectionFactory.getConnection();
        Tipo tipo = new Tipo();
        ResultSet rs = null;

        try {
            stmt = cn.prepareStatement(sql);
            stmt.setInt(1, idTipo);
            rs = stmt.executeQuery();

            if (rs.next()) {
                tipo = new Tipo(rs);
            }
         
        } catch (Exception e) {

        } finally {
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }
        return tipo;
    }
    
    public static Tipo getTipoByNome(String nome){
        String sql = "SELECT * FROM Tipo WHERE nome = ?";
        PreparedStatement stmt = null;
        cn = ConnectionFactory.getConnection();
        Tipo tipo = new Tipo();
        ResultSet rs = null;

        try {
            stmt = cn.prepareStatement(sql);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();

            if (rs.next()) {
                tipo = new Tipo(rs);
            }
         
        } catch (Exception e) {

        } finally {
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }
        return tipo;
    }
}
