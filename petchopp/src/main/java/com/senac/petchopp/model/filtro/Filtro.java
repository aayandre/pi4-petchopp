package com.senac.petchopp.model.filtro;

import java.util.List;

import com.senac.petchopp.model.tag.Tag;

public class Filtro {

	private List<Object> tipos;
	private List<Tag> tags;

	public Filtro() {
		super();
	}

	public Filtro(List<Object> tipos, List<Tag> tags) {
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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Filtros [tipos=" + tipos + ", tags=" + tags + "]";
	}

}
