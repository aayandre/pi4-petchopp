package com.senac.petchopp.model.cliente;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Marcelo Pereira
 */
public class Cliente {

	private Long idCliente;
	private Date dtCadastro;
	private String nome;
	private Date dtNasc;
	private String rg;
	private String cpf;
	private String email;
	private String senha;
	private List<Endereco> enderecos;
	private boolean ativo;

	public Cliente() {

	}

	public Cliente(Long idCliente, Date dtCadastro, String nome, Date dtNasc, String rg, String cpf, String email,
			String senha, boolean ativo) {
		this.idCliente = idCliente;
		this.dtCadastro = dtCadastro;
		this.nome = nome;
		this.dtNasc = dtNasc;
		this.rg = rg;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
	}

	public Cliente(ResultSet rs) throws SQLException {
		this.idCliente = rs.getLong("dtCadastro");
		this.dtCadastro = rs.getDate("dtCadastro");
		this.nome = rs.getString("nome");
		this.dtNasc = rs.getDate("dtNasc");
		this.rg = rs.getString("RG");
		this.cpf = rs.getString("CPF");
		this.email = rs.getString("Email");
		this.senha = rs.getString("Senha");
		this.ativo = rs.getBoolean("Ativo");
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(Date dtNasc) {
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}
