/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jesushm.listeners;

import es.jesushm.persistence.HibernateUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Carga las categorías y características en el contexto
 *
 * @author jesus
 */
@WebListener
public class Inicio implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Se ha llamado a la construccion de la factoria de sesiones.");
        HibernateUtil.buildSessionFactory();
//        HibernateUtil.openSessionAndBindToThread();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Se ha llamado al cierre de la factoria de sesiones.");
//        HibernateUtil.closeSessionAndUnbindFromThread();
        HibernateUtil.closeSessionFactory();
    }

}
