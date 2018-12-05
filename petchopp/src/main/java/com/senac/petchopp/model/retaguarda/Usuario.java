/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.model.retaguarda;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author macop
 */
public class Usuario {
    
    private Long idUsuario;
    private String nome;
    private String user;
    private String senha;
    private int role;
    private boolean ativo;
    private boolean logado;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nome, String user, String senha, int role, boolean ativo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.user = user;
        this.senha = senha;
        this.role = role;
        this.ativo = ativo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
    
    
}
