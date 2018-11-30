package com.senac.petchopp.model.filtro;

import java.sql.SQLException;

import com.senac.petchopp.daos.TagDAO;
import com.senac.petchopp.daos.TipoDAO;

public class FiltroService {

	private TagDAO tagdao = new TagDAO();
	private TipoDAO tipodao = new TipoDAO();

	public FiltroService() {

	}

	public Filtro getAll() {

		Filtro filtro = new Filtro();
		try {
			filtro.setTags(tagdao.getAllTags());
			filtro.setTipos(tipodao.getAll());
			return filtro;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
