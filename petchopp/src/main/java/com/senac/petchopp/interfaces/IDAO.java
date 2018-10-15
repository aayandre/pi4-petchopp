package com.senac.petchopp.interfaces;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Marcelo Pereira
 */
public interface IDAO {
	
	static Connection cn = null;
    
    public void salvar(Object bean);
    public void atualizar(Object bean);
    public void deletar(long id);
    public Object getById(long id);
    public List<Object> getAll();
    
}
