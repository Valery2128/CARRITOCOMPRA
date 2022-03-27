/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import config.Conexion;
import java.awt.Robot;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author VALERY
 */
public class ClienteDAO {

    public List<Clientee> listar() {
        List<Clientee> lista = new ArrayList<>();
        String sql = "SELECT * FROM  cliente WHERE estado='1'";

        try {
            Conexion cn = new Conexion();
            Connection con = cn.getConnection();
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clientee c = new Clientee();
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombres(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setEmail(rs.getString(5));
                c.setPass(rs.getString(6));
                c.setFoto(rs.getString(7));
                c.setEstado(rs.getString(8));
                c.setRol(RolDAO.listarId(rs.getInt("idRol")));
                lista.add(c);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return lista;
    }

    public List listarRol() {
        List<Rol> lista = new ArrayList<>();
        String sql = "select * from rol";
        try {
            Conexion cn = new Conexion();
            Connection con = cn.getConnection();
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol r = new Rol();
                r.setId(rs.getInt(1));
                r.setRol(rs.getString(2));
                lista.add(r);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return lista;
    }

    public Clientee listarId(int id) {
        Clientee c = new Clientee();
//        String sql = "select * from cliente where idCliente=" + id;
        String sql = "SELECT * FROM  cliente c  where c.idCliente=" + id;

        try {
            Conexion cn = new Conexion();
            Connection con = cn.getConnection();
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombres(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setEmail(rs.getString(5));
                c.setPass(rs.getString(6));
                c.setFoto(rs.getString(7));
                c.setEstado(rs.getString(8));
                c.setRol(RolDAO.listarId(rs.getInt("idRol")));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return c;
    }

    public Clientee Validar(String email, String pass) {
        String sql = "SELECT * FROM  cliente where Email=? and Password=? and estado='1'";
        Clientee c = new Clientee();
        try {
            Conexion cn = new Conexion();
            Connection con = cn.getConnection();
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombres(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setEmail(rs.getString(5));
                c.setPass(rs.getString(6));
                c.setRol(RolDAO.listarId(rs.getInt("idRol")));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return c;
    }

    public int AgregarCliente(Clientee c) {
        String sql = "INSERT INTO cliente (Documento, Nombres, Direccion, Email, estado,idRol)values(?,?,?,?,?,?)";
        try {
            Conexion cn = new Conexion();
            Connection con = cn.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombres());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getEmail());
            ps.setString(5, "1");/*1-Activo-0-Desactivado*/
            ps.setInt(6, c.getRol().getId());

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return 1;
    }

    public int AgregarUsuario(Clientee c) {
        String sql = "INSERT INTO cliente (Dni, Nombres, Direccion, Email, Password,Foto,idRol)values(?,?,?,?,?,?,?)";
        try {
            Conexion cn = new Conexion();
            Connection con = cn.getConnection();
            PreparedStatement ps;
            ResultSet rs;
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombres());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getPass());
            ps.setString(6, c.getFoto());
            ps.setInt(7, c.getRol().getId());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return 1;
    }

    public int ActualizarUsuario(Clientee c) {
        int r = 0;
        String sql = "";
        Conexion cn = new Conexion();
        Connection con = cn.getConnection();
        PreparedStatement ps;
        try {
            sql = "update cliente set Documento=?, Nombres=?, Direccion=?, Email=?,idRol=? where idCliente=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombres());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getEmail());
            ps.setInt(5, c.getRol().getId());
            ps.setInt(6, c.getId());
            r = ps.executeUpdate();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return r;
    }

    public void DeleteUsuario(int id) {
        String sql = "update cliente set estado='0' where idCliente=" + id;

        try {
            Conexion cn = new Conexion();
            Connection con = cn.getConnection();
            PreparedStatement ps;

            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("error: " + e);
        }

    }

    public int ActualizarPass(String doc, String corre, String pass) {
        String sql = "update cliente set Password=?,Con_pass=? where Documento =? AND Email=? and estado='1'";
        int r1 = 0;
        try {
            Conexion cn = new Conexion();
            Connection con = cn.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1,pass);
            ps.setString(2,pass);
            ps.setString(3,doc);
            ps.setString(4,corre);
            r1 = ps.executeUpdate();
            ps.close();
           con.close();
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return r1;
    }
}
