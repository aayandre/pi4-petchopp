package com.senac.petchopp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.senac.petchopp.connection.ConnectionFactory;
import com.senac.petchopp.interfaces.IDAO;
import com.senac.petchopp.model.cliente.Endereco;

public class EnderecoDAO implements IDAO {

    Connection cn = null;

    public void salvarEndereco(List<Endereco> enderecos, Long idcliente) throws SQLException {
        PreparedStatement stmt = null;
        cn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO Endereco\r\n"
                + "(CEP, Logradouro, Numero, Complemento, Bairro, Cidade, UF, idCliente, TipoEndereco, Ativo)\r\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
//			cn.setAutoCommit(false);
            stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (Endereco end : enderecos) {
//				stmt.setLong(1, end.getIdEndereco());
                stmt.setString(1, end.getCep());
                stmt.setString(2, end.getLogradouro());
                stmt.setString(3, end.getNum());
                stmt.setString(4, end.getComp());
                stmt.setString(5, end.getBairro());
                stmt.setString(6, end.getCidade());
                stmt.setString(7, end.getUf());
                stmt.setLong(8, idcliente);
                stmt.setString(9, end.getTipoEndereco());
                stmt.setBoolean(10, end.isAtivo());
                stmt.execute();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    end.setIdEndereco(rs.getLong(1));
                }
            }

//			cn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
//			cn.rollback();
        } finally {
//			cn.setAutoCommit(true);
            ConnectionFactory.closeConnection(cn, stmt);
        }
    }
//    Salvar Novo endereço
     public void salvarNovoEndereco(Endereco endereco, Long idcliente) throws SQLException {
        PreparedStatement stmt = null;
        cn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO Endereco\r\n"
                + "(CEP, Logradouro, Numero, Complemento, Bairro, Cidade, UF, idCliente, TipoEndereco, Ativo)\r\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
//			cn.setAutoCommit(false);
            stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, endereco.getCep());
                stmt.setString(2, endereco.getLogradouro());
                stmt.setString(3, endereco.getNum());
                stmt.setString(4, endereco.getComp());
                stmt.setString(5, endereco.getBairro());
                stmt.setString(6, endereco.getCidade());
                stmt.setString(7, endereco.getUf());
                stmt.setLong(8, idcliente);
                stmt.setString(9, endereco.getTipoEndereco());
                stmt.setBoolean(10, endereco.isAtivo());
                stmt.execute();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    endereco.setIdEndereco(rs.getLong(1));
                }

//			cn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
//			cn.rollback();
        } finally {
//			cn.setAutoCommit(true);
            ConnectionFactory.closeConnection(cn, stmt);
        }
    }

    public List<Endereco> getAllEnd(Long idCliente) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT idEndereco, CEP, Logradouro, Numero, Complemento, Bairro, Cidade, UF, idCliente, TipoEndereco, Ativo "
                + "FROM Endereco WHERE idCliente = ? AND Ativo = true";
        cn = ConnectionFactory.getConnection();

        try {
            stmt = cn.prepareStatement(sql);
            stmt.setLong(1, idCliente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Endereco end = new Endereco();

                end.setIdEndereco(rs.getLong("idEndereco"));
                end.setCep(rs.getString("CEP"));
                end.setLogradouro(rs.getString("Logradouro"));
                end.setNum(rs.getString("Numero"));
                end.setComp(rs.getString("Complemento"));
                end.setBairro(rs.getString("Bairro"));
                end.setCidade(rs.getString("Cidade"));
                end.setUf(rs.getString("UF"));
                end.setIdCliente(rs.getLong("idCliente"));
                end.setTipoEndereco(rs.getString("TipoEndereco"));
                end.setAtivo(rs.getBoolean("Ativo"));
                enderecos.add(end);

            }

        } finally {
            ConnectionFactory.closeConnection(null, stmt, rs);
        }

        return enderecos;
    }

    @Override
    public void atualizar(Object bean) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deletar(Long id) {
        PreparedStatement stmt = null;
        String sql = "UPDATE Endereco SET Ativo = false WHERE idEndereco = ?";
        cn = ConnectionFactory.getConnection();
        try {
            stmt = cn.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(cn, stmt);
        }
    }

    @Override
    public Endereco getById(Long idEndereco) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Endereco endereco = new Endereco();
        String sql = "SELECT idEndereco, CEP, Logradouro, Numero, Complemento, Bairro, Cidade, UF, idCliente, TipoEndereco "
                + "FROM Endereco WHERE idEndereco = ?";
        cn = ConnectionFactory.getConnection();

        try {
            stmt = cn.prepareStatement(sql);
            stmt.setLong(1, idEndereco);
            rs = stmt.executeQuery();

            if (rs.next()) {

                endereco.setIdEndereco(rs.getLong("idEndereco"));
                endereco.setCep(rs.getString("CEP"));
                endereco.setLogradouro(rs.getString("Logradouro"));
                endereco.setNum(rs.getString("Numero"));
                endereco.setComp(rs.getString("Complemento"));
                endereco.setBairro(rs.getString("Bairro"));
                endereco.setCidade(rs.getString("Cidade"));
                endereco.setUf(rs.getString("UF"));
                endereco.setIdCliente(rs.getLong("idCliente"));
                endereco.setTipoEndereco(rs.getString("TipoEndereco"));
            }

        } finally {
            ConnectionFactory.closeConnection(null, stmt, rs);
        }

        return endereco;
    }

    @Override
    public List<Object> getAll() {

        return null;
    }

    @Override
    public void salvar(Object bean) {
        // TODO Auto-generated method stub

    }

}
