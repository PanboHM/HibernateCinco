package es.jesushm.DAOFactory;

import es.jesushm.DAOs.IPersonaDAO;

public abstract class DAOFactory {

    public abstract IPersonaDAO getPersonaDAO();

    public static DAOFactory getDAOFactory() {
        DAOFactory daof = new MySQLDAOFactory();

        return daof;
    }

}
