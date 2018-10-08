/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.daos;

import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.Auxiliares;
import com.senac.petchopp.model.cliente.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcelo
 */
public class ClienteDAO implements IDAO{
    Connection cn = null;
   

    @Override
    public void salvar(Object bean) {
        PreparedStatement stmt = null;
        
        String sql = " INSERT INTO CLIENTE (idCliente, dtCadastro, Nome, dtNasc, RG, CPF"
                + "Email, Senha, Ativo) VALUES (?,?,?,?,?,?,?,?,?)";
        
        try {
            Cliente cliente = (Cliente) bean;
            stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, cliente.getIdCliente());
            stmt.setDate(2, new java.sql.Date(Auxiliares.UtilDateToCalendar(cliente.getDtCadastro()).getTimeInMillis()));
            stmt.setString(3, cliente.getNome());
            stmt.setDate(4, new java.sql.Date(Auxiliares.UtilDateToCalendar(cliente.getDtNasc()).getTimeInMillis()));
            stmt.setString(0, sql);
            
//            Pega o id do cliente
            ResultSet rs = stmt.getGeneratedKeys();
            int i = 0;
            if (rs.next()) {
                i = rs.getInt(1);
                System.out.println(i);
                cliente.setIdCliente(i);
                System.out.println(cliente.getIdCliente());
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
        }
        
    }

    @Override
    public void atualizar(Object bean) {
         
    }

    @Override
    public void deletar(int id) {
         
    }

    @Override
    public Object getById(int id) {
        
        return null;
    }

    @Override
    public List<Object> getAll() {
        
        return null;
    }
    
    
}
