package com.senac.petchopp.model.cliente;

/**
 *
 * @author Marcelo Pereira
 */
public class Endereco {
    
   private long idEndereco;
   private String CEP;
   private String Logradouro;
   private String Bairro;
   private String Cidade;
   private String UF;

    public Endereco() {
        
    }

    public Endereco(String CEP, String Logradouro, String Bairro, String Cidade, String UF) {
        this.CEP = CEP;
        this.Logradouro = Logradouro;
        this.Bairro = Bairro;
        this.Cidade = Cidade;
        this.UF = UF;
    }
   
   
    
}
