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
import es.jesushm.DAOs.IGenericoDAO;

/**
 * Este es el controlador intermedio. <br>
 * Excepto si se quiere actualizar algo, se vuelve al índice y por tanto se cierra la sesión
 * 
 * @author Jesus
 */
@WebServlet(name = "Controlador", urlPatterns = {"/control"})
public class Controlador extends HttpServlet {

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
        IGenericoDAO<Persona> pdao = daof.getGenericoDAO();
        Persona persona = new Persona();
        List<Libro> libros = new ArrayList();
        Libro libro;
        String url = null;
        switch (request.getParameter("op")) {
            case "add":
                try {
                    BeanUtils.populate(persona, request.getParameterMap());
                    
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
                String[] librosFormulario = request.getParameterValues("libro");
                for (String libroFor : librosFormulario) {
                    libro = new Libro();
                    libro.setTitulo(libroFor);
                    libros.add(libro);
                }
                persona.setLibros(libros);
                pdao.insertOrUpdate(persona);
                url = "index.html";
                break;
            case "delete":
                persona = pdao.getById(Long.parseLong(request.getParameter("registro")), Persona.class);
                pdao.delete(persona);
                url = "index.html";
                break;
            case "update":
                persona = pdao.getById(Long.parseLong(request.getParameter("registro")), Persona.class);
                request.setAttribute("persona", persona);
                url = "JSP/formularioActualizar.jsp";
                break;
            case "menu":
                url = "index.html";
                break;
        }
//        if (url.equals("index.html")) {
//            //vamos a volver al index, así que vamos a cerrar la sesión.
//            System.out.println("Se llamó al cierre de las sesión");
//            HibernateUtil.closeSessionAndUnbindFromThread();
//        }
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
