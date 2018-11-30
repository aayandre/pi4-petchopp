/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.service;

import com.senac.petchopp.daos.ClienteDAO;
import com.senac.petchopp.model.cliente.Cliente;

/**
 *
 * @author macop
 */
public class ClienteService {
    
    ClienteDAO clienteDAO = new ClienteDAO();
    
    
    public Cliente salvar(Cliente cliente){
        return cliente;
    }
    
    public Cliente atualizar(Cliente cliente){
       
        try {
            clienteDAO.atualizar(cliente);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return cliente;
    }
    
}
