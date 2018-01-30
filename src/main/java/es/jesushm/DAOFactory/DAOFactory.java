package es.jesushm.DAOFactory;

import es.jesushm.DAOs.IGenericoDAO;

public abstract class DAOFactory {

    public abstract IGenericoDAO getGenericoDAO();

    public static DAOFactory getDAOFactory() {
        DAOFactory daof = new MySQLDAOFactory();

        return daof;
    }

}
