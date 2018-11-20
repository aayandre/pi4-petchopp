package com.senac.petchopp.wos;

import com.senac.petchopp.model.filtro.Filtro;

public class FormularioSearch {

	private String procura;
	private Filtro filtros;

	public FormularioSearch() {
		super();
	}

	public FormularioSearch(String procura, Filtro filtros) {
		super();
		this.procura = procura;
		this.filtros = filtros;
	}

	public String getProcura() {
		return procura;
	}

	public void setProcura(String procura) {
		this.procura = procura;
	}

	public Filtro getFiltros() {
		return filtros;
	}

	public void setFiltros(Filtro filtros) {
		this.filtros = filtros;
	}

	@Override
	public String toString() {
		return "FormularioSearch [procura=" + procura + ", filtros=" + filtros + "]";
	}

}
