package com.senac.petchopp.model.cliente;

/**
 *
 * @author Marcelo Pereira
 */
public class Endereco {

   private Long idEndereco;
   private String cep;
   private String logradouro;
   private String num;
   private String comp;
   private String bairro;
   private String cidade;
   private String uf;
   private Long idCliente;
   private String tipoEndereco;

    public Endereco() {

    }

    public Endereco(String cep, String logradouro, String num, String comp, String bairro, String cidade, String uf, Long idCliente, String tipoEndereco) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.num = num;
        this.comp = comp;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.idCliente = idCliente;
        this.tipoEndereco = tipoEndereco;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }


    public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }


}
