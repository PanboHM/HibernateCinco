package es.jesushm.controllers;

import es.jesushm.DAOFactory.DAOFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import es.jesushm.beans.Persona;
import es.jesushm.beans.Libro;
import es.jesushm.persistence.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import es.jesushm.DAOs.IGenericoDAO;

/**
 * A este servlet se llega únicamente cuando ya hemos modificado la Persona o
 * libro/s, simplemente actualiza los valores en la base de datos y se guardan
 *
 * @author Jesus
 */
@WebServlet(name = "Conclusion", urlPatterns = {"/conclusion"})
public class Conclusion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO<Persona> pDAO = daof.getGenericoDAO();
        Persona persona = new Persona();
        List<Libro> libros = new ArrayList();
        Libro libro;
        String url = null;
        switch (request.getParameter("op")) {
            case "update":
                try {
                    BeanUtils.populate(persona, request.getParameterMap());
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
                Map<String, String[]> librosFormulario = request.getParameterMap();
                for (Map.Entry<String, String[]> entrada : librosFormulario.entrySet()) {
                    if (entrada.getKey().startsWith("libro")) {
                        libro = new Libro();
                        libro.setIdLibro(Long.parseLong(entrada.getKey().replace("libro", "")));
                        for (String titulin : entrada.getValue()) {
                            libro.setTitulo(titulin);
                        }
                        libros.add(libro);
                    }
                }
                persona.setLibros(libros);
                pDAO.insertOrUpdate(persona);
                url = "index.html";
                break;
        }
        //cerramos la sesión porque volvemos al index
        HibernateUtil.closeSessionAndUnbindFromThread();
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
