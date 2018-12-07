/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.service;

import com.senac.petchopp.daos.UsuarioDAO;
import com.senac.petchopp.model.retaguarda.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author macop
 */
public class UsuarioService {
    
    private UsuarioDAO uDAO = new UsuarioDAO();
    
    public Usuario efetuaLogin(String email, String senha){
        Usuario user = new Usuario();
        
        try {
            user = uDAO.getbyEmail(email);
            if(user.isAtivo()){
                if(BCrypt.checkpw(senha, user.getSenha())){
                user.setLogado(true);
                return user;
                }
            }
        } catch (Exception e) {
        }
        return user;
    }
    
    public List<Usuario> listaUsuarios(){
        List<Usuario> lista = new ArrayList<>();
        try {
            lista = uDAO.getAllUsers();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public boolean salvar(Usuario novo){
        try {
            novo.setAtivo(true);
            uDAO.salvar(novo);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean atualizar(Usuario novo){
        
        try {
            uDAO.atualizar(novo);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    public Usuario buscar(Long id) throws SQLException{
        return (Usuario) uDAO.getById(id);
    }
}
