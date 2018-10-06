package com.senac.petchopp.model.cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Marcelo Pereira
 */
public class Cliente {
    
   private long idCliente;
   private Calendar dtCadastro;
   private String nome;
   private Calendar dtNasc;
   private String rg;
   private String cpf;
   private String email;
   private String senha;
   private boolean ativo;

   
   
    public Cliente() {
        
    }

    public Cliente(Calendar dtCadastro, String nome, Calendar dtNasc, String rg, String cpf, String email, String senha, boolean ativo) {
        this.dtCadastro = dtCadastro;
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }
    
    public Cliente(ResultSet rs) throws SQLException{
        this.idCliente = rs.getLong("dtCadastro");
        this.nome = rs.getString("nome");
        this.dtNasc = dtNasc;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public Calendar getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Calendar dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Calendar dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
