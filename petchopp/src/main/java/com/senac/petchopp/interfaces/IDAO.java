package com.senac.petchopp.interfaces;

import java.util.List;

/**
 *
 * @author Marcelo Pereira
 */
public interface IDAO {
    
    public void salvar(Object bean);
    public void atualizar(Object bean);
    public void deletar(int id);
    public Object getById(long id);
    public List<Object> getAll();
    
}
