package com.senac.petchopp.model.carrinho;

import java.util.ArrayList;

import com.senac.petchopp.model.produto.ProdutoVenda;

public class Carrinho {

	private ArrayList<ProdutoVenda> produtos = new ArrayList<>();
	private double total;

	public Carrinho() {
		super();
	}

	public Carrinho(ArrayList<ProdutoVenda> produtos, double total) {
		super();
		this.produtos = produtos;
		this.total = total;
	}

	public ArrayList<ProdutoVenda> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<ProdutoVenda> produtos) {
		this.produtos = produtos;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
