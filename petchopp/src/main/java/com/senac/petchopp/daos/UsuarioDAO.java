/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.daos;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.retaguarda.Usuario;
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
public class UsuarioDAO implements IDAO{

    Connection cn = null;
    
    @Override
    public void salvar(Object bean) throws SQLException {
        PreparedStatement stmt = null;
        Usuario user = (Usuario) bean;
        String sql = "ISERT INTO Usuario "
                + "(Nome, User, Password, Role, Ativo) "
                + "VALUES(?,?,?,?,?)";
        cn = ConnectionFactory.getConnection();
        
        try {
            stmt = cn.prepareStatement(sql);
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getUser());
            stmt.setString(3, user.getSenha());
            stmt.setInt(4, user.getRole());
            stmt.setBoolean(5, user.isAtivo());
            stmt.execute();
            
        } catch (Exception e) {
        }finally{
            ConnectionFactory.closeConnection(cn, stmt);
        }
    }

    @Override
    public void atualizar(Object bean) throws SQLException {
        PreparedStatement stmt = null;
        Usuario user = (Usuario) bean;
        String sql = "UPDATE Usuario "
                + "SET Nome=?, User=?, Password=?, Role=?, Ativo=? "
                + "WHERE idUsuario=?";
        cn = ConnectionFactory.getConnection();
        
        try {
            stmt = cn.prepareStatement(sql);
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getUser());
            stmt.setString(3, user.getSenha());
            stmt.setInt(4, user.getRole());
            stmt.setBoolean(5, user.isAtivo());
            stmt.setLong(6, user.getIdUsuario());
            stmt.execute();
            
        } catch (Exception e) {
        }finally{
            ConnectionFactory.closeConnection(cn, stmt);
        }
    }

    @Override
    public void deletar(Long id) throws SQLException {
        PreparedStatement stmt = null;
        String sql = "UPDATE Usuario "
                + "SET Ativo=? "
                + "WHERE idUsuario=?";
        cn = ConnectionFactory.getConnection();
        
        try {
            stmt = cn.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setLong(2, id);
            stmt.execute();
            
        } catch (Exception e) {
        }finally{
            ConnectionFactory.closeConnection(cn, stmt);
        }
    }

    @Override
    public Object getById(Long id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = new Usuario();
        String sql = "SELECT idUsuario, Nome, User, Password, Role, Ativo"
                + "FROM Usuario WHERE idUsuario = ?";
        cn = ConnectionFactory.getConnection();
        
        try {
            stmt = cn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                user.setIdUsuario(rs.getLong("idUsuario"));
                user.setNome(rs.getString("Nome"));
                user.setUser(rs.getString("User"));
                user.setSenha(rs.getString("Password"));
                user.setRole(rs.getInt("Role"));
                user.setAtivo(rs.getBoolean("Ativo"));
            }
        } catch (Exception e) {
        }finally{
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }
        return user;
    }
    public Usuario getbyEmail(String email){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = new Usuario();
        String sql = "SELECT idUsuario, Nome, User, Password, Role, Ativo"
                + "FROM Usuario WHERE User LIKE ?";
        cn = ConnectionFactory.getConnection();
        
        try {
            stmt = cn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while(rs.next()){
                user.setIdUsuario(rs.getLong("idUsuario"));
                user.setNome(rs.getString("Nome"));
                user.setUser(rs.getString("User"));
                user.setSenha(rs.getString("Password"));
                user.setRole(rs.getInt("Role"));
                user.setAtivo(rs.getBoolean("Ativo"));
            }
        } catch (Exception e) {
        }finally{
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }
        return user;
    }

    @Override
    public List<Object> getAll() throws SQLException {
        return null;
    }
    public List<Usuario> getAllUsers() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT idUsuario, Nome, User, Password, Role, Ativo"
                + "FROM Usuario WHERE Ativo = ?";
        cn = ConnectionFactory.getConnection();
        
        try {
            stmt = cn.prepareStatement(sql);
            stmt.setBoolean(1, true);
            rs = stmt.executeQuery();
            while(rs.next()){
                Usuario user = new Usuario();
                user.setIdUsuario(rs.getLong("idUsuario"));
                user.setNome(rs.getString("Nome"));
                user.setUser(rs.getString("User"));
                user.setSenha(rs.getString("Password"));
                user.setRole(rs.getInt("Role"));
                user.setAtivo(rs.getBoolean("Ativo"));
                usuarios.add(user);
            }
        } catch (Exception e) {
        }finally{
            ConnectionFactory.closeConnection(cn, stmt, rs);
        }
        return usuarios;
    }
    
    public void atualizarSenha(Long id, String senha) {
        PreparedStatement stmt = null;
        String sql = "UPDATE Usuario SET  Password = ? WHERE idUsuario = ?";
        cn = ConnectionFactory.getConnection();

        try {
            stmt = cn.prepareStatement(sql);
            stmt.setString(1, senha);
            stmt.setLong(2, id);
            stmt.execute();

        } catch (SQLException e) {
        } finally {
            ConnectionFactory.closeConnection(cn, stmt);
        }
    }
}
