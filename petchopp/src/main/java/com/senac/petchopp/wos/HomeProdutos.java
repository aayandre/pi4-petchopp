package com.senac.petchopp.wos;

import java.util.ArrayList;
import java.util.List;

import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.tipo.Tipo;

public class HomeProdutos {

	private Tipo tipo;
	private List<Produto> produtos = new ArrayList<>();

	public HomeProdutos() {
		super();
	}

	public HomeProdutos(Tipo tipo, List<Produto> produtos) {
		super();
		this.tipo = tipo;
		this.produtos = produtos;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "HomeProdutos [tipo=" + tipo + ", produtos=" + produtos + "]";
	}

}
