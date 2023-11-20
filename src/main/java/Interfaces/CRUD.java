package Interfaces;

import java.util.ArrayList;

/**
 * Interfaz para establecer los metodos basicos del DAO.
 * @author Ryuka
 */
public interface CRUD {
    
    public boolean insert(Object objeto);
    public ArrayList<?> selectAll();
    public Object select(Object objeto);
    public boolean update(Object objeto);
    public boolean delete(Object objeto);
    
}
