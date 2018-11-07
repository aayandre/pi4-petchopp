package com.senac.petchopp.model.carrinho;

import java.util.ArrayList;

import com.senac.petchopp.model.produto.Produto;

public class Carrinho {

	private ArrayList<Produto> produtos;
	private double total;

	public Carrinho() {
		super();
	}

	public Carrinho(ArrayList<Produto> produtos, double total) {
		super();
		this.produtos = produtos;
		this.total = total;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
