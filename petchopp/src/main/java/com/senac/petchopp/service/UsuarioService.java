/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.service;

import com.senac.petchopp.daos.UsuarioDAO;
import com.senac.petchopp.model.retaguarda.Usuario;
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
    
}
