package com.senac.petchopp.model.tipo;

import java.util.ArrayList;
import java.util.List;

import com.senac.petchopp.model.tag.Tag;

public class TipoTag {

	private Tipo tipo;
	private List<Tag> tags = new ArrayList<>();

	public TipoTag() {
		super();
	}

	public TipoTag(Tipo tipo, List<Tag> tags) {
		super();
		this.tipo = tipo;
		this.tags = tags;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "TipoTag [tipo=" + tipo + ", tags=" + tags + "]";
	}

}
