package com.senac.petchopp.model;

import java.util.Arrays;

public class FormularioSearch {

	private String procura;
	private Filtros filtros;

	public FormularioSearch() {
		super();
	}

	public FormularioSearch(String procura, Filtros filtros) {
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

	public Filtros getFiltros() {
		return filtros;
	}

	public void setFiltros(Filtros filtros) {
		this.filtros = filtros;
	}

	@Override
	public String toString() {
		return "FormularioSearch [procura=" + procura + ", filtros=" + filtros + "]";
	}

	public static class Filtros {

		private String[] tipos;
		private String[] tags;

		public Filtros() {
			super();
		}

		public Filtros(String[] tipos, String[] tags) {
			super();
			this.tipos = tipos;
			this.tags = tags;
		}

		public String[] getTipos() {
			return tipos;
		}

		public void setTipos(String[] tipos) {
			this.tipos = tipos;
		}

		public String[] getTags() {
			return tags;
		}

		public void setTags(String[] tags) {
			this.tags = tags;
		}

		@Override
		public String toString() {
			return "Filtros [tipos=" + Arrays.toString(tipos) + ", tags=" + Arrays.toString(tags) + "]";
		}

	}

}
