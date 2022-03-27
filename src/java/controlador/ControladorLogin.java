/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ClienteDAO;
import modelo.Clientee;
import modelo.Rol;
import modelo.RolDAO;
import utils.Constants;

/**
 *
 * @author VALERY
 */
public class ControladorLogin extends HttpServlet {

    ClienteDAO cdao = new ClienteDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        HttpSession session = request.getSession();
        switch (action) {
            case "IniciarSession":
                sendToVista(Constants.VISTA_ISESSION, request, response);
                break;
            case "Login":
                String email = request.getParameter("Email");
                String password = request.getParameter("Password");
                Clientee usuario = cdao.Validar(email, password);
                session.setAttribute("UsuarioLogueado", usuario.getDni() != null ? usuario : null);
                sendToVista(Constants.URL_HOME, request, response);
                break;
            case "CerrarSession":
                if (session != null) {
                    session.removeAttribute("UsuarioLogueado");
                }
                session.setAttribute("UsuarioLogueado", null);
                sendToVista(Constants.URL_HOME, request, response);
                break;
        }

    }

    private void sendToVista(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
