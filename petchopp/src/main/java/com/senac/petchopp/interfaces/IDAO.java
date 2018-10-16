package com.senac.petchopp.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Marcelo Pereira
 */
public interface IDAO {
	
	static Connection cn = null;
    
    public void salvar(Object bean) throws SQLException;
    public void atualizar(Object bean) throws SQLException;
    public void deletar(long id) throws SQLException;
    public Object getById(long id) throws SQLException;
    public List<Object> getAll() throws SQLException;
    
}
