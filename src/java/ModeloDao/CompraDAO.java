/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Compra;
import modelo.Tarjeta;
import modelo.detallecompras;

/**
 *
 * @author VALERY
 */
public class CompraDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    DecimalFormat df = new DecimalFormat("#.00");

    public int GenerarCompra(Compra co) throws SQLException {
        int r = 0;
        String sql = "insert into compras(idCliente,idPago, FechaCompras,Monto,descuento,igv,montoFinal,Estado)values(?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, co.getIdCliente());
            ps.setInt(2, co.getIdPago());
            ps.setString(3, co.getFecha());
            ps.setDouble(4, co.getMonto());
            ps.setDouble(5, co.getDescuento());
            ps.setDouble(6, co.getIgv());
            ps.setDouble(7, co.getMontofinal());
            ps.setString(8, co.getEstado());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
        return 1;
    }

    public int guardarDetalleCompra(detallecompras dc) {
        String sql = "insert into Detalle_Compras(idProducto,idCompras, Cantidad, PrecioCompra,descuento,precioFinal,subtotal)values(?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dc.getIdproducto());
            ps.setInt(2, dc.getIdcompra());
            ps.setInt(3, dc.getCantidad());
            ps.setDouble(4, dc.getPrecioCompra());
            ps.setDouble(5, dc.getDescuento());
            ps.setDouble(6, dc.getPreciofinal());
            ps.setDouble(7, dc.getSubtotal());
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
        }
        return 1;
    }

    public List misCompras(int id) {
        List lista = new ArrayList();
        String sql = "select idCompras,idCliente,idPago,FechaCompras,cast(Monto as decimal(10,2)),cast(descuento as decimal(10,2)),cast(igv as decimal(10,2)),cast(montoFinal as decimal(10,2)),Estado from compras where idCliente=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Compra com = new Compra();
                com.setId(rs.getInt(1));
                com.setIdCliente(rs.getInt(2));
                com.setIdPago(rs.getInt(3));
                com.setFecha(rs.getString(4));
                com.setMonto(rs.getDouble(5));
                com.setDescuento(rs.getDouble(6));
                com.setIgv(rs.getDouble(7));
                com.setMontofinal(rs.getDouble(8));
                com.setEstado(rs.getString(9));
                lista.add(com);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public List Detalle(int id) {
        List lista = new ArrayList();
        String sql = "SELECT p.idProducto, p.Nombres,p.Foto,dc.PrecioCompra,dc.Cantidad,dc.descuento,dc.precioFinal, \n"
                + "(dc.PrecioCompra*dc.Cantidad) as subtotal\n"
                + "FROM detalle_compras dc INNER JOIN compras c INNER JOIN producto p\n"
                + "on dc.idCompras=c.idCompras and dc.idProducto=p.idProducto\n"
                + "WHERE c.idCompras=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] obd = new Object[8];
                obd[0] = rs.getInt(1);//idprodcuto
                obd[1] = rs.getString(2);//nombreproducto
                obd[2] = rs.getString(3);//foto
                obd[3] = rs.getDouble(4);//precioCompra
                obd[4] = rs.getInt(5);//dcCantidad
                obd[5] = df.format(rs.getDouble(6));//descuento
                obd[6] = df.format(rs.getDouble(7));//precioFinal
                obd[7] = df.format(rs.getDouble("subtotal"));//Subtotal
                lista.add(obd);
            }
        } catch (Exception e) {
            System.err.println("Error de Listar" + e);
        }
        return lista;
    }

    public int Pagar(double monto, int idt) {
        int r = 0;
        String sql = "insert into pago(Monto,idTarjeta)values(?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, monto);
            ps.setDouble(2, idt);
            r = ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public int IdPago() {
        int idpago = 0;
        String sql = "select max(idPago) from pago";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idpago = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return idpago;
    }

    public Tarjeta ListarId(String numero) {
        String sql = "select * from tarjeta where Numero=?";
        Tarjeta t = new Tarjeta();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, numero);
            rs = ps.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setNombre(rs.getString(2));
                t.setNumero(rs.getString(3));
                t.setFecha(rs.getString(4));
                t.setCodigo(rs.getString(5));
                t.setSaldo(rs.getDouble(6));
            }
        } catch (Exception e) {
        }
        return t;
    }

    public int ActualizarPrecio(double monto, int id) {
        int r = 0;
        String sql = "update tarjeta set saldo=? where idTarjeta=?";
        Tarjeta t = new Tarjeta();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, monto);
            ps.setInt(2, id);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error al Guardar los Datos" + e);
        }
        return r;
    }

    public int IdCompra() {
        int idc = 0;
        String sql = "select max(idCompras) from compras";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idc = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return idc;
    }

}
