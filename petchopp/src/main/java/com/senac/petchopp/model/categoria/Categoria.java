package com.senac.petchopp.model.categoria;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Categoria {

    private int idCategoria;
    private String Nome;

    public Categoria() {
    }

    public Categoria(int idCategoria, String nome) {
        super();
        this.idCategoria = idCategoria;
        Nome = nome;
    }

    public Categoria(ResultSet rs) throws SQLException {
        this.idCategoria = rs.getInt("idCategoria");
        this.Nome = rs.getString("Nome");
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

}
