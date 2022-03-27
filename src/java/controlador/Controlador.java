package controlador;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import config.fecha;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.Categoria;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Clientee;
import modelo.Compra;
import ModeloDao.CompraDAO;
import modelo.Pago;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.Rol;
import modelo.Tarjeta;
import modelo.detallecompras;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Controlador extends HttpServlet {

    Pago pago = new Pago();
    Cliente cl = new Cliente();
    ProductoDAO pdao = new ProductoDAO();
    Producto p = new Producto();
    List<Producto> productos = new ArrayList<>();
    List<Carrito> ListaCarrito = new ArrayList<>();
    Compra compra = new Compra();
    String logueo = "iniciarsesion";
    String correo = "iniciarsesion";
    int item = 0;
    DecimalFormat df = new DecimalFormat("#.00");
    double totalPagar = 0.0;
    int cantidad = 1;
    int ipd;
    Carrito car;
    fecha fecha = new fecha();
    List<Clientee> clientes = new ArrayList<>();
    int rpago = 0;
    Double datos[] = new Double[4];
    double[] descuento = new double[6];
    List<Categoria> categorias;
    List<Rol> roles;
    ClienteDAO cldao = new ClienteDAO();
    CompraDAO cdao = new CompraDAO();
    int idproeditado;
    String email;
    String pass;
    Fecha fechaSistem = new Fecha();
    int pag1 = 0;
    int pag2 = 0;
    List<Producto> Lpag = new ArrayList();
    List<Double> listapd = new ArrayList<>();
    Clientee clientelogueado = new Clientee();
    String accionPrincipal = "Otros";
    List<Carrito> listaProductos = new ArrayList<>();
    double totalDescuento = 0.0;
    int idpago;
    double monto;
    String estado;
    String detallecompras;
    double igv;
    double montopagar = 0.0;
    int idcompra;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();

        session.setAttribute("clientelogueado", clientelogueado);

        categorias = pdao.ListarCategoria();
        request.setAttribute("categorias", categorias);

        roles = cldao.listarRol();
        request.setAttribute("roles", roles);

        Lpag = pdao.listar();
        request.setAttribute("paginacion", Lpag);

        productos = pdao.listar();
        //clientelogueado.setId(19);//Cuando implementes quitar
        clientelogueado = (Clientee) session.getAttribute("UsuarioLogueado");
        switch (accion) {
            case "Comprar":
                totalPagar = 0.0;
                int idp = Integer.parseInt(request.getParameter("id"));
                p = pdao.listarId(idp);
                item = item + 1;
                Carrito car = new Carrito();
                car.setItem(item);
                car.setIdProducto(p.getId());
                car.setNombres(p.getNombres());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                car.setSubTotal(cantidad * p.getPrecio());
                ListaCarrito.add(car);
                for (int i = 0; i < ListaCarrito.size(); i++) {
                    totalPagar = totalPagar + ListaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", ListaCarrito);

                request.setAttribute("contador", ListaCarrito.size());
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "AgregarCarrito":
                int pos = 0;
                cantidad = 1;
                idp = Integer.parseInt(request.getParameter("id"));
                p = pdao.listarId(idp);
                if (ListaCarrito.size() > 0) {
                    for (int i = 0; i < ListaCarrito.size(); i++) {
                        if (idp == ListaCarrito.get(i).getIdProducto()) {
                            pos = i;
                        }
                    }
                    if (idp == ListaCarrito.get(pos).getIdProducto()) {
                        cantidad = ListaCarrito.get(pos).getCantidad() + cantidad;
                        double subtotal = ListaCarrito.get(pos).getPrecioCompra() * cantidad;
                        ListaCarrito.get(pos).setCantidad(cantidad);
                        ListaCarrito.get(pos).setSubTotal(subtotal);
                    } else {
                        item = item + 1;
                        car = new Carrito();
                        car.setItem(item);
                        car.setIdProducto(p.getId());
                        car.setNombres(p.getNombres());
                        car.setDescripcion(p.getDescripcion());
                        car.setPrecioCompra(p.getPrecio());
                        car.setCantidad(cantidad);
                        car.setSubTotal(cantidad * p.getPrecio());
                        ListaCarrito.add(car);
                    }

                } else {
                    item = item + 1;
                    car = new Carrito();
                    car.setItem(item);
                    car.setIdProducto(p.getId());
                    car.setNombres(p.getNombres());
                    car.setDescripcion(p.getDescripcion());
                    car.setPrecioCompra(p.getPrecio());
                    car.setCantidad(cantidad);
                    car.setSubTotal(cantidad * p.getPrecio());
                    ListaCarrito.add(car);
                }

                request.setAttribute("contador", ListaCarrito.size());
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);

                break;
            case "Delete":
                int idproducto = Integer.parseInt(request.getParameter("idp"));
                for (int i = 0; i < ListaCarrito.size(); i++) {
                    if (ListaCarrito.get(i).getIdProducto() == idproducto) {
                        ListaCarrito.remove(i);
                    }
                }
                break;
            case "MisCompras":
                if (clientelogueado.getId() != 0) {
                    List compra = cdao.misCompras(clientelogueado.getId());
                    request.setAttribute("myCompras", compra);
                    request.getRequestDispatcher("vistas/compras.jsp").forward(request, response);
                } else if (listaProductos.size() > 0) {
                    request.getRequestDispatcher("Controlador?accion=carrito").forward(request, response);
                } else {
                    request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                }
                break;
            case "verDetalle":
                double montocompra = 0;
                int idcompras = Integer.parseInt(request.getParameter("idcompra"));
                montocompra = Double.parseDouble(request.getParameter("monto"));
                List detalle = cdao.Detalle(idcompras);
                request.setAttribute("myDetalle", detalle);
                request.setAttribute("montoCompra", df.format(montocompra));
                request.getRequestDispatcher("vistas/detalle.jsp").forward(request, response);
                break;
            case "Salir":
                listaProductos = new ArrayList();
                clientelogueado = new Clientee();
                session.invalidate();
                request.getRequestDispatcher("Controlador?accion=Salirr").forward(request, response);
                break;
            case "Imprimir":
                idcompra = cdao.IdCompra();
                List detalle1 = cdao.Detalle(idcompra);
                request.setAttribute("myDetalle", detalle1);
                request.setAttribute("datos", datos);
                request.getRequestDispatcher("vistas/Imprimir.jsp").forward(request, response);
                break;
            case "Oferta":
                accionPrincipal = "Oferta";
                productos = pdao.listarProductoOferta();
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "RealizarPago":
                String nombres = request.getParameter("txtnombre");
                String numeroT = request.getParameter("txtnumero");
                String fecha = request.getParameter("txtfecha");
                String codS = request.getParameter("txtcodigo");
                Tarjeta t = cdao.ListarId(numeroT);
                if (t.getNumero() != null) {
                    if (nombres.equals(t.getNombre()) && codS.equals(t.getCodigo())) {
                        montopagar = totalPagar - totalDescuento;
                        if (t.getSaldo() >= montopagar) {
                            if (clientelogueado.getId() != 0 && montopagar > 0) {
                                rpago = cdao.Pagar(montopagar, t.getId());
                                if (rpago == 1) {
                                    cdao.ActualizarPrecio(t.getSaldo() - montopagar, t.getId());
                                    request.getRequestDispatcher("Controlador?accion=GenerarCompra").forward(request, response);
                                    request.getRequestDispatcher("vistas/Imprimir.jsp").forward(request, response);
                                }
                            } else {
                                montopagar = 0;
                                request.getRequestDispatcher("Controlador?accion=carrito").forward(request, response);

                            }
                        } else {
                            out.print(" <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>");
                            out.print("<div class='container mt-4'>");
                            out.print("<div class='col-sm-6'>");
                            out.print("<div class='card'>");
                            out.print("<div class='card-body'>");
                            out.print("<div class='alert alert-danger' role='alert'>");
                            out.print("<h3>Saldo Insuficiente...!!!<h3>");
                            out.print("</div>");
                            out.print("<a href='Controlador?accion=carrito' class='btn btn-outline-primary btn-lg'>Intente de Nuevo</a>");
                            out.print("</div>");
                            out.print("</div>");
                            out.print("</div>");
                            out.print("</div>");
                        }
                    } else {
                        out.print(" <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>");
                        out.print("<div class='container mt-4'>");
                        out.print("<div class='col-sm-6'>");
                        out.print("<div class='card'>");
                        out.print("<div class='card-body'>");
                        out.print("<div class='alert alert-danger' role='alert'>");
                        out.print("<h3>Datos de la Tarjeta no son Correctos<h3>");
                        out.print("</div>");
                        out.print("<a href='Controlador?accion=carrito' class='btn btn-outline-primary btn-lg'>Intente de Nuevo</a>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                    request.setAttribute("montopagar", df.format(montopagar));
                } else {
                    out.print(" <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>");
                    out.print("<div class='container mt-4'>");
                    out.print("<div class='col-sm-6'>");
                    out.print("<div class='card'>");
                    out.print("<div class='card-body'>");
                    out.print("<div class='alert alert-danger' role='alert'>");
                    out.print("<h3>Error al Ingresar Datos de la Tarjeta<h3>");
                    out.print("</div>");
                    out.print("<a href='Controlador?accion=carrito' class='btn btn-outline-primary btn-lg'>Intente de Nuevo</a>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                }
                break;

            case "ActualizarCantidad":
                int idpro = Integer.parseInt(request.getParameter("idpro"));
                int cant = Integer.parseInt(request.getParameter("cant"));
                for (int i = 0; i < ListaCarrito.size(); i++) {
                    if (ListaCarrito.get(i).getIdProducto() == idpro) {
                        ListaCarrito.get(i).setCantidad(cant);
                        double st = ListaCarrito.get(i).getPrecioCompra() * cant;
                        ListaCarrito.get(i).setSubTotal(st);
                    }
                }
            case "Productos":
                productos = pdao.listar();
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("vistas/productos/addproducto.jsp").forward(request, response);
                break;

            case "Carrito":
                totalPagar = 0.0;
                request.setAttribute("carrito", ListaCarrito);
                for (int i = 0; i < ListaCarrito.size(); i++) {
                    totalPagar = totalPagar + ListaCarrito.get(i).getSubTotal();
                    totalDescuento = totalDescuento + ListaCarrito.get(i).getDescuento();
                }
                request.setAttribute("totalPagar", totalPagar);

                datos[0] = totalDescuento;//Descuento
                request.setAttribute("totalDescuento", df.format(totalDescuento));
                datos[1] = totalPagar;//Subtotal
                request.setAttribute("subtotal", df.format(totalPagar));
                datos[2] = totalPagar - totalDescuento;//Total a PAgar
                request.setAttribute("totalPagar", df.format(totalPagar - totalDescuento));
                datos[3] = (totalPagar - totalDescuento) * 0.18;//IGV
                request.setAttribute("igv", df.format((totalPagar - totalDescuento) * 0.18));
                request.setAttribute("totalDescuento", df.format(totalDescuento));
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "BuscarProducto":
                String nombre = request.getParameter("txtBuscar");
                productos = pdao.buscar(nombre);
                request.setAttribute("cont", listaProductos.size());
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "Nuevo":
                ListaCarrito = new ArrayList();
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
            case "GuardarProducto":
                ArrayList<String> pro = new ArrayList<>();
                try {
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload fileUpload = new ServletFileUpload(factory);
                    List items = fileUpload.parseRequest(request);
                    for (int i = 0; i < items.size(); i++) {
                        FileItem fileItem = (FileItem) items.get(i);
                        if (!fileItem.isFormField()) {
                            File file = new File("C:\\XAMPP2\\htdocs\\img\\" + fileItem.getName());
                            fileItem.write(file);
                            //p.setFoto("http://localhost/img/" + fileItem.getName());
                        } else {
                            pro.add(fileItem.getString());
                        }
                    }
                    p.setNombres(pro.get(0));
                    p.setDescripcion(pro.get(1));
                    p.setPrecio(Double.parseDouble(pro.get(2)));
                    p.setStock(Integer.parseInt(pro.get(3)));
                    pdao.AgregarNuevoProducto(p);

                } catch (Exception e) {
                    System.err.println("Debe Crear la Carpeta dentro de LocalHost: " + e);
                }
                request.getRequestDispatcher("Controlador?accion=Productos").forward(request, response);
                break;
                    

            case "GenerarCompra":
                idpago = cdao.IdPago();
                if (clientelogueado.getId() != 0 && ListaCarrito.size() != 0 && rpago != 0) {//rpago->CuandoHaganElPago
                    if (totalPagar > 0.0) {
                        Compra co = new Compra();
                        co.setIdCliente(clientelogueado.getId());
                        co.setFecha(fechaSistem.FechaBD());
                        co.setMonto(totalPagar);
                        co.setDescuento(totalDescuento);
                        co.setIgv((totalPagar - totalDescuento) * 0.18);
                        co.setMontofinal(montopagar - totalDescuento);
                        co.setIdPago(idpago);
                        co.setEstado("Cancelado - En Proceso de Envio");
                        cdao.GenerarCompra(co);
                        montopagar = 0;

                        idcompra = cdao.IdCompra();
                        for (int i = 0; i < ListaCarrito.size(); i++) {
                            detallecompras dc = new detallecompras();
                            dc.setIdcompra(idcompra);
                            dc.setIdproducto(ListaCarrito.get(i).getIdProducto());
                            dc.setCantidad(ListaCarrito.get(i).getCantidad());
                            dc.setPrecioCompra(ListaCarrito.get(i).getPrecioCompra());
                            double desc = ListaCarrito.get(i).getPrecioCompra() - ListaCarrito.get(i).getPreciofinal();
                            dc.setDescuento(desc);
                            dc.setPreciofinal(ListaCarrito.get(i).getPreciofinal());
                            dc.setSubtotal(ListaCarrito.get(i).getSubTotalDes());
                            cdao.guardarDetalleCompra(dc);
                        }
                        //------Actualizar Stock-----------------
                        for (Carrito listaProducto : ListaCarrito) {
                            Producto producto = new Producto();
                            producto.setId(listaProducto.getIdProducto());
                            producto.setStock(listaProducto.getCantidad());
                            pdao.ActualizarStock(producto);
                        }
                        //------Fin Metodo Actualizar Stock------
                        ListaCarrito = new ArrayList<>();
                        List compra = cdao.misCompras(clientelogueado.getId());
                        request.setAttribute("myCompras", compra);
                        request.getRequestDispatcher("Controlador?accion=Imprimir").forward(request, response);
                    } else {
                        request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("Controlador?accion=Carrito").forward(request, response);
                }
                break;

            default:
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("index.jsp").forward(request, response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException,
            IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException,
            IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
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
