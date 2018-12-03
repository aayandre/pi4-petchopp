package com.senac.petchopp.wos;

import java.util.ArrayList;
import java.util.List;

import com.senac.petchopp.model.produto.Produto;
import com.senac.petchopp.model.tag.Tag;
import com.senac.petchopp.model.tipo.TipoTag;

public class FormularioProduto {

	private Produto produto;
	private List<Tag> tagRetorno = new ArrayList<>();
	private List<TipoTag> tipoTags = new ArrayList<>();

	public FormularioProduto() {
		super();
	}

	public FormularioProduto(Produto produto, List<Tag> tagRetorno, List<TipoTag> tipoTags) {
		super();
		this.produto = produto;
		this.tagRetorno = tagRetorno;
		this.tipoTags = tipoTags;
	}

	public void addTipoTag(TipoTag tt) {
		this.tipoTags.add(tt);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Tag> getTagRetorno() {
		return tagRetorno;
	}

	public void setTagRetorno(List<Tag> tagRetorno) {
		this.tagRetorno = tagRetorno;
	}

	public List<TipoTag> getTipoTags() {
		return tipoTags;
	}

	public void setTipoTags(List<TipoTag> tipoTags) {
		this.tipoTags = tipoTags;
	}

	@Override
	public String toString() {
		return "FormularioProduto [produto=" + produto + ", tagRetorno=" + tagRetorno + ", tipoTags=" + tipoTags + "]";
	}

}
