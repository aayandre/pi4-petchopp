package com.senac.petchopp.model.filtro;

import java.util.List;

public class Filtro {

	private List<Object> tipos;
	private List<Object> tags;

	public Filtro() {
		super();
	}

	public Filtro(List<Object> tipos, List<Object> tags) {
		super();
		this.tipos = tipos;
		this.tags = tags;
	}

	public List<Object> getTipos() {
		return tipos;
	}

	public void setTipos(List<Object> tipos) {
		this.tipos = tipos;
	}

	public List<Object> getTags() {
		return tags;
	}

	public void setTags(List<Object> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Filtros [tipos=" + tipos + ", tags=" + tags + "]";
	}

}
