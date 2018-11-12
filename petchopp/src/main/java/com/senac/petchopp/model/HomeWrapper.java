package com.senac.petchopp.model;

import java.util.ArrayList;

import com.senac.petchopp.model.produto.Produto;

public class HomeWrapper {

	private String[] carosel;
	private ArrayList<Produto> lista1;
	private ArrayList<Produto> lista2;
	private ArrayList<Produto> lista3;
	private ArrayList<Produto> lista4;

	public HomeWrapper() {
		super();
	}

	public HomeWrapper(String[] carosel, ArrayList<Produto> lista1, ArrayList<Produto> lista2,
			ArrayList<Produto> lista3, ArrayList<Produto> lista4) {
		super();
		this.carosel = carosel;
		this.lista1 = lista1;
		this.lista2 = lista2;
		this.lista3 = lista3;
		this.lista4 = lista4;
	}

	public String[] getCarosel() {
		return carosel;
	}

	public void setCarosel(String[] carosel) {
		this.carosel = carosel;
	}

	public ArrayList<Produto> getLista1() {
		return lista1;
	}

	public void setLista1(ArrayList<Produto> lista1) {
		this.lista1 = lista1;
	}

	public ArrayList<Produto> getLista2() {
		return lista2;
	}

	public void setLista2(ArrayList<Produto> lista2) {
		this.lista2 = lista2;
	}

	public ArrayList<Produto> getLista3() {
		return lista3;
	}

	public void setLista3(ArrayList<Produto> lista3) {
		this.lista3 = lista3;
	}

	public ArrayList<Produto> getLista4() {
		return lista4;
	}

	public void setLista4(ArrayList<Produto> lista4) {
		this.lista4 = lista4;
	}

}
