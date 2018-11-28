package com.senac.petchopp.wos;

import com.senac.petchopp.model.filtro.Filtro;

public class FormularioSearch {

	private String procura;
	private Double precoMax;
	private Double precoMin;
	private Filtro filtros;

	public FormularioSearch() {
		super();
	}

	public FormularioSearch(String procura, Double precoMax, Double precoMin, Filtro filtros) {
		super();
		this.procura = procura;
		this.precoMax = precoMax;
		this.precoMin = precoMin;
		this.filtros = filtros;
	}

	public String getProcura() {
		return procura;
	}

	public void setProcura(String procura) {
		this.procura = procura;
	}

	public Double getPrecoMax() {
		return precoMax;
	}

	public void setPrecoMax(String precoMax) {
		this.precoMax = Double.parseDouble(precoMax);
	}

	public Double getPrecoMin() {
		return precoMin;
	}

	public void setPrecoMin(Double precoMin) {
		this.precoMin = precoMin;
	}

	public Filtro getFiltros() {
		return filtros;
	}

	public void setFiltros(Filtro filtros) {
		this.filtros = filtros;
	}

	@Override
	public String toString() {
		return "FormularioSearch [procura=" + procura + ", precoMax=" + precoMax + ", precoMin=" + precoMin
				+ ", filtros=" + filtros + "]";
	}

}
