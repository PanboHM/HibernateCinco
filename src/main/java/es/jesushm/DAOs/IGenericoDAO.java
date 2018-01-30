package es.jesushm.DAOs;

import java.io.Serializable;
import java.util.List;

public interface IGenericoDAO<T> {

    public void insertOrUpdate(T objeto);

    public <T> List<T> selectAll(Class<T> claseEntidad);

    public <T> T getById(Serializable pk, Class<T> claseEntidad);

    public void delete(T objeto);

}
