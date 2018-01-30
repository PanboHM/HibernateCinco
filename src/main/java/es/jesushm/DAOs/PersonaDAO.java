package es.jesushm.DAOs;

import es.jesushm.beans.Persona;
import es.jesushm.persistence.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class PersonaDAO implements IPersonaDAO {

    @Override
    public void add(Persona persona) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(persona);
            sesion.getTransaction().commit();
        } catch (org.hibernate.JDBCException jdbce) {
            if (sesion != null) {
                sesion.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
    }

    @Override
    public List<Persona> get() {
        List<Persona> listado = null;
        Session sesion = null;
        try {
            HibernateUtil.openSessionAndBindToThread();
            sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            sesion.beginTransaction();
            listado = sesion.createQuery(" from Persona").list();
            sesion.getTransaction().commit();
        } catch (org.hibernate.JDBCException jdbce) {
            if (sesion != null) {
                sesion.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        return listado;
    }

    @Override
    public void delete(Persona persona) {
        Session sesion = null;
        try {
            HibernateUtil.openSessionAndBindToThread();
            sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            sesion.beginTransaction();
            sesion.delete(persona);
            sesion.getTransaction().commit();
        } catch (org.hibernate.JDBCException jdbce) {
            if (sesion != null) {
                sesion.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
    }

    @Override
    public Persona getOne(Long pk) {
        Persona puerto = new Persona();
        Session sesion = null;
        try {
            HibernateUtil.openSessionAndBindToThread();
            sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            sesion.beginTransaction();
            puerto = (Persona) sesion.get(Persona.class, pk);
            sesion.getTransaction().commit();
        } catch (org.hibernate.JDBCException jdbce) {
            if (sesion != null) {
                sesion.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        return puerto;
    }

    @Override
    public void update(Persona persona) {
        Session sesion = null;
        try {
            HibernateUtil.openSessionAndBindToThread();
            sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            sesion.beginTransaction();
            sesion.update(persona);
            sesion.getTransaction().commit();
        } catch (org.hibernate.JDBCException jdbce) {
            if (sesion != null) {
                sesion.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
    }

}
