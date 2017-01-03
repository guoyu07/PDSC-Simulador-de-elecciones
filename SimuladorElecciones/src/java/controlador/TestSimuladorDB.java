/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simulador.SimuladorDB;
import utils.Eleccion;
import utils.TipoEleccion;
import utils.Usuario;

/**
 * Servlet empleado para probar la conexion con la base de datos
 * 
 * @author daniel
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestSimuladorDB extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestSimuladorDB at " + request.getContextPath() + "</h1>");
            
//____________________UNIT_TEST____________________
            int id;
            Usuario usuario = new Usuario("Nuevo", "nuevo@nuevo.com", "111");

            // Insert de Usuario
            id = SimuladorDB.insertUsuario(usuario);
            assert(id > 0);

            // Select de Usuario
            Usuario usuarioSelected = SimuladorDB.selectUsuario(id);
            assert((usuarioSelected.getId() == id)
                    && usuarioSelected.getNombre().equals(usuario.getNombre())
                    && usuarioSelected.getCorreoElectronico().equals(usuario.getCorreoElectronico())
            );
            
            
            Eleccion eleccion = new Eleccion(new Date(), TipoEleccion.Autonomicas);
            id = SimuladorDB.insertEleccion(eleccion);
            assert(id > 0);
            Eleccion eleccionSelected = SimuladorDB.selectEleccion(id);
            assert((eleccionSelected.getID() == id)
                    && eleccionSelected.getFecha().equals(eleccion.getFecha())
                    && (eleccionSelected.getTipoEleccion() == eleccion.getTipoEleccion())
            );
//____________________UNIT_TEST____________________
            
            out.println("<p>Test Superado</p>");
            out.println("</body>");
            out.println("</html>");
            
            
            
        } finally {
            out.close();
        }
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