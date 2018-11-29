package com.senac.petchopp.model.tipo;

import java.sql.SQLException;
import java.util.List;

import com.senac.petchopp.daos.TipoDAO;

public class TipoService {

	private TipoDAO tipodao = new TipoDAO();

	public TipoService() {

	}

	public List<Tipo> getAllTiposProdutos() throws SQLException {
		List<Tipo> tipos = tipodao.getAllTipos();
		return tipos;
	}

	public Tipo getByDescricao(String descricao) throws SQLException {
		Tipo adiquirido = tipodao.getByDescricao(descricao);
		return adiquirido;
	}

}
