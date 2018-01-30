package es.jesushm.DAOs;

import es.jesushm.beans.Persona;
import java.util.List;

public interface IPersonaDAO {

    public void add(Persona persona);

    public List<Persona> get();

    public Persona getOne(Long pk);

    public void update(Persona persona);

    public void delete(Persona persona);

}
