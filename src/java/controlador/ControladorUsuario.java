package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ClienteDAO;
import modelo.Clientee;
import modelo.Rol;
import modelo.RolDAO;
import utils.Constants;

/**
 *
 * @author VALERY
 */
public class ControladorUsuario extends HttpServlet {

    ClienteDAO cdao = new ClienteDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");

        switch (action) {
            case "listarusuario":
                List<Clientee> usuarios = cdao.listar();
                request.setAttribute("usuarios", usuarios);
                sendToVista(Constants.VISTA_USER, request, response);
                break;
            case "eliminarusuario":
                int idUsu = Integer.parseInt(request.getParameter("id"));
                cdao.DeleteUsuario(idUsu);
                sendToVista(Constants.URL_USER_LISTAR, request, response);
                break;
            case "editarusuario":
                int idU = Integer.parseInt(request.getParameter("id"));
                Clientee c = cdao.listarId(idU);
                request.setAttribute("usuario", c);
                request.setAttribute("roles", RolDAO.listar());
                sendToVista(Constants.VISTA_USER_SAVE, request, response);
                break;
            case "nuevousuario":
                request.setAttribute("usuario", new Clientee());
                request.setAttribute("roles", RolDAO.listar());
                sendToVista(Constants.VISTA_USER_SAVE, request, response);
                break;
            case "guardarCambios":
                int txtid = Integer.parseInt(request.getParameter("txtid"));
                String txtdni = request.getParameter("txtdni");
                String txtnombre = request.getParameter("txtnombre");
                String txtdirec = request.getParameter("txtdirec");
                String txtemail = request.getParameter("txtemail");
                String txtrol = request.getParameter("txtrol");
                Clientee cl = new Clientee();
                cl.setId(txtid);
                cl.setDni(txtdni);
                cl.setNombres(txtnombre);
                cl.setDireccion(txtdirec);
                cl.setEmail(txtemail);
                cl.setRol(new Rol(Integer.parseInt(txtrol), ""));
                int r = 0;
                if (txtid != 0) {
                    r = cdao.ActualizarUsuario(cl);
                } else {
                    r = cdao.AgregarCliente(cl);
                }
                if (r != 0) {
                    sendToVista(Constants.URL_USER_LISTAR, request, response);
                } else {
                    sendToVista(Constants.VISTA_MSG, request, response);
                }
                break;
            case "CambiarPassword":
                sendToVista(Constants.VISTA_CAMBIAR_PASS, request, response);
                break;
            case "ActualizarPass":
                String txtDocum = request.getParameter("txtDocumento");
                String txtcorreo = request.getParameter("txtCorreo");
                String txtnuevacon = request.getParameter("txtContraseña");
                int r1=cdao.ActualizarPass(txtDocum, txtcorreo, txtnuevacon);
                sendToVista(Constants.URL_INICIARSESION, request, response);
                break;
        }

    }

    private void sendToVista(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
