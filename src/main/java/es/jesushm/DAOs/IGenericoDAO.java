package es.jesushm.DAOs;

import java.io.Serializable;
import java.util.List;

public interface IGenericoDAO<T> {

    /**
     * Inserta o actualiza un objeto
     * 
     * @param objeto
     */
    public void insertOrUpdate(T objeto);

    /**
     * Devuelve una lista de todos los registros de la Entidad en cuestión
     * 
     * @param <T>
     * @param claseEntidad
     * @return
     */
    public <T> List<T> selectAll(Class<T> claseEntidad);

    /**
     * Rescata de la base de datos un unico registro de una Entidad basado en la clave primaria.
     * 
     * @param <T>
     * @param pk
     * @param claseEntidad
     * @return
     */
    public <T> T getById(Serializable pk, Class<T> claseEntidad);

    /**
     * Borra el objeto pasado por parámetros
     * 
     * @param objeto
     */
    public void delete(T objeto);

}
