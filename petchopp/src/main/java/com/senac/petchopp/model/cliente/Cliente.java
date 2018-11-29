package com.senac.petchopp.model.cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.format.annotation.DateTimeFormat;

import com.senac.petchopp.model.Auxiliares;

/**
 *
 * @author Marcelo Pereira
 */

public class Cliente {

	private Long idCliente;
	private LocalDate dtCadastro;
	private String nome;
	private LocalDate dtNasc;
	private String rg;
	private String cpf;
	private String email;
	private String senha;
	private List<Endereco> enderecos = new ArrayList<>();
	private String telefone1;
	private String telefone2;
	private boolean ativo;
	private boolean logado;

	public Cliente() {

	}

	public Cliente(Long idCliente, LocalDate dtCadastro, String nome, LocalDate dtNasc, String rg, String cpf,
			String email, String senha, String telefone1, String telefone2, boolean ativo) {
		this.idCliente = idCliente;
		this.dtCadastro = dtCadastro;
		this.nome = nome;
		this.dtNasc = dtNasc;
		this.rg = rg;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.ativo = ativo;
	}

	public Cliente(ResultSet rs) throws SQLException {
		this.idCliente = rs.getLong("dtCadastro");
		this.dtCadastro = rs.getDate("dtCadastro").toLocalDate();
		this.nome = rs.getString("nome");
		this.dtNasc = rs.getDate("dtNasc").toLocalDate();
		this.rg = rs.getString("RG");
		this.cpf = rs.getString("CPF");
		this.email = rs.getString("Email");
		this.senha = rs.getString("Senha");
		this.telefone1 = rs.getString("telefone1");
		this.telefone2 = rs.getString("telefone2");
		this.ativo = rs.getBoolean("Ativo");
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public LocalDate getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(LocalDate dtNasc) {
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

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	
	public boolean checkPassword(String openPass) {
        if (senha != null) {
            return BCrypt.checkpw(senha, openPass);
        }
        return false;
    }

	public void addEnderecoToList(Endereco end) {
		List<Endereco> enderecos = this.enderecos;
		enderecos.add(end);
		for (Endereco endereco : enderecos) {
			System.out.println("CEP CADASTRADO: " + endereco.getCep());
		}

	}

	public void impimirEnderecos() {
		List<Endereco> enderecos = this.enderecos;
		for (Endereco endereco : enderecos) {
			System.out.println("CEP CADASTRADO: " + endereco.getCep());
		}
	}

}
