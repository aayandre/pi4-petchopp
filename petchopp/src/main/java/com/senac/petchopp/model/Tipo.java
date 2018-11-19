package com.senac.petchopp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tipo {

    private int idTipo;
    private String nome;
    private String descricao;

    public Tipo() {
    }

    public Tipo(int idTipo, String nome, String descricao) {
        this.idTipo = idTipo;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Tipo(ResultSet rs) throws SQLException {
        this.idTipo = rs.getInt("idTipo");
        this.nome = rs.getString("nome");
        this.descricao = rs.getString("descricao");
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
