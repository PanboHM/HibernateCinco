package es.jesushm.DAOFactory;

import es.jesushm.DAOs.GenericoDAO;
import es.jesushm.DAOs.IGenericoDAO;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public IGenericoDAO getGenericoDAO() {
        return new GenericoDAO();
    }

//    @Override
//    public IGenericoDAO getGenericoDAO() {
//        return new GenericoDAO();
//    }
}
