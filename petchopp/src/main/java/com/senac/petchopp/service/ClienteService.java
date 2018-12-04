/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.petchopp.service;

import com.senac.petchopp.daos.ClienteDAO;
import com.senac.petchopp.model.cliente.Cliente;
import java.util.UUID;
import static javax.management.Query.lt;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author macop
 */
public class ClienteService {

    ClienteDAO clienteDAO = new ClienteDAO();

    public Cliente salvar(Cliente cliente) {
        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {

        try {
            clienteDAO.atualizar(cliente);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cliente;
    }
    
    public String novaSenha(String senha){
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public String gerarNovaSenha() {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        System.out.println(myRandom.substring(0, 8));
        return myRandom.substring(0, 8);
    }
    
    public boolean verificaSeExiste(String email){
        try {
            Cliente cli = clienteDAO.getClienteByEmail(email);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    public void envianovaSenha(String email, String senhaNoCrypt){
        EmailService sendEmail = new EmailService();
        String subject = "PETCHOPP - Nova Senha";
        String msg = " Olá; \n"
                + "Você solicitou uma nova senha na Loja PetChopp - Seu PET em primeiro lugar!\n"
                + "\n"
                + "Nova senha: " + senhaNoCrypt + "\n"
                + "\nNão esqueça de alterar a senha no próximo login.\n"
                + "Obrigado!";
        sendEmail.enviar(email, subject, msg);
    }

}
