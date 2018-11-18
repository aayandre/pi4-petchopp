package com.senac.petchopp.model;

public class FormularioSearch {

	private String procura;
	private String filtros;

	public FormularioSearch() {
		super();
	}

	public FormularioSearch(String procura, String filtros) {
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

	public String getFiltros() {
		return filtros;
	}

	public void setFiltros(String filtros) {
		this.filtros = filtros;
	}

}
