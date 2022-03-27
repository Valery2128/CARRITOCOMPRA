package utils;

public class Constants {

    //GENERALES
    public static final String VISTA_MSG = "mensaje.jsp";

    //LOGIN
    public static final String VISTA_ISESSION = "iniciarsesion.jsp";
    public static final String VISTA_REGISTRARSE = "";
    public static final String VISTA_CAMBIAR_PASS = "vistas/usuarios/cambiarContraseña.jsp";
    public static final String URL_INICIARSESION = "ControladorLogin?accion=IniciarSession";
    //CONTROLADOR USUSARIO        
    public static final String VISTA_USER = "vistas/usuarios/usuario.jsp";
    public static final String VISTA_USER_SAVE = "vistas/usuarios/save.jsp";
    public static final String URL_USER_LISTAR = "ControladorUsuario?accion=listarusuario";

    //CONTROLADOR         
    public static final String VISTA_HOME = "index.jsp";
    public static final String URL_HOME = "Controlador?accion=home";
    //CONTROLADOR PRODUCTO
    public static final String VISTA_ADDPROD = "addproducto.jsp";
    public static final String VISTA_AGREGARPRO = "agregar.jsp";
    public static final String VISTA_ELIMINARPRO = "delete.jsp";
    public static final String URL_USER_LISTARP = "ControllerProducto?accion=listarproducto";
    public static final String VISTA_PROD = "indexP.jsp";

}
