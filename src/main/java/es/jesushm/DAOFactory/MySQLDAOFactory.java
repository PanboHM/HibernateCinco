package es.jesushm.DAOFactory;

import es.jesushm.DAOs.PersonaDAO;
import es.jesushm.DAOs.IPersonaDAO;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public IPersonaDAO getPersonaDAO() {
        return new PersonaDAO();
    }

//    @Override
//    public IGenericoDAO getGenericoDAO() {
//        return new GenericoDAO();
//    }
}
