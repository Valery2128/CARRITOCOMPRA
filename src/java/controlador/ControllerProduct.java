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
import modelo.ProductoDAO;
import modelo.Producto;
import utils.Constants;

/**
 *
 * @author VALERY
 */
public class ControllerProduct extends HttpServlet {
public class ControllerProducto extends HttpServlet {

    ProductoDAO Pdao = new ProductoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        switch (action) {
            case "listarproducto":
                List<Producto> producto = Pdao.listar();
                request.setAttribute("producto", producto);
                sendToVista(Constants.VISTA_PROD, request, response);
                break;
            case "eliminarproducto":
                int idProd = Integer.parseInt(request.getParameter("id"));
                Pdao.EliminarProducto(idProd);
                sendToVista(Constants.URL_USER_LISTARP, request, response);
                break;
            case "editarusuario":
                int idPro = Integer.parseInt(request.getParameter("id"));
                Producto p = Pdao.listarId(idPro);
                request.setAttribute("producto", p);
                request.setAttribute("producto", Pdao.listar());
                sendToVista(Constants.URL_USER_LISTARP, request, response);
                break;
            case "nuevoproducto":
                request.setAttribute("producto", new Producto());
                request.setAttribute("producto", Pdao.listar());
                sendToVista(Constants.URL_USER_LISTARP, request, response);
                break;
            case "guardarCambios":
                int txtid = Integer.parseInt(request.getParameter("txtid"));
                String txtnombre = request.getParameter("txtnombre");
                String txtfoto = request.getParameter("txtfoto");
                String txtdescripcion = request.getParameter("txtdescripcion");
                Producto pr = new Producto();
                pr.setId(txtid);
                pr.setNombres(txtnombre);
                pr.setFoto(txtfoto);
                pr.setDescripcion(txtdescripcion);

                int r = 0;
                if (txtid != 0) {
                    r = Pdao.ActualizarProducto(pr);
                } else {
                    r = Pdao.AgregarNuevoProducto(pr);
                }
                if (r != 0) {
                    sendToVista(Constants.URL_USER_LISTARP, request, response);
                } else {
                    sendToVista(Constants.VISTA_MSG, request, response);
                }
                

        }

    }

        private void sendToVista(String VISTA_PROD, HttpServletRequest request, HttpServletResponse response) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       
    }

  
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
