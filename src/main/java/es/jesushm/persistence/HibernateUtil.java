/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jesushm.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.context.internal.ThreadLocalSessionContext;

/**
 *
 * @author jesus
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static synchronized void buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
                Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
                sessionFactory = metaData.getSessionFactoryBuilder().build();
                System.out.println("Se creó una factoria de sesiones");
            } catch (Throwable th) {
                System.err.println("Enitial SessionFactory creation failed" + th);
                throw new ExceptionInInitializerError(th);
            }
        }
    }

    public static void openSessionAndBindToThread() {
        Session session = sessionFactory.openSession();
        ThreadLocalSessionContext.bind(session);
//        sessionFactory.openSession();
        System.out.println("Se abrio una sesion");
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void closeSessionAndUnbindFromThread() {
//        Session session = ThreadLocalSessionContext.unbind(sessionFactory);
        Session session = sessionFactory.getCurrentSession();
//        Session session = sessionFactory.getCurrentSession();
        if (session != null) {
            session.close();
            System.out.println("La sesion se cerró");
        } else {
            System.out.println("La sesión estaba vacia");
        }
    }

    public static void closeSessionFactory() {
        if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
            sessionFactory.close();
            System.out.println("Se cerró la factoria de sesiones");
        } else {
            System.out.println("La factoria de sesiones estaba vacía o cerrada");
        }
    }
}
