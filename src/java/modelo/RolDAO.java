/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VALERY
 */
public class RolDAO {
   
    
    
    
    //Atributos encapsulados
    
   public static Rol listarId(int id) {
       Rol rol = new Rol();
//        String sql = "select * from cliente where idCliente=" + id;
        String sql = "SELECT * FROM rol where idRol=?";
        try {
            Conexion cn=new Conexion();
            Connection con = cn.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               rol.setId(rs.getInt(1));
               rol.setRol(rs.getString(2));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
        return rol;
    }
   public static List<Rol> listar() {
       List<Rol>roles=new ArrayList<>();
        String sql = "SELECT * FROM rol";
        try {
            Conexion cn=new Conexion();
            Connection con = cn.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Rol rol = new Rol();
               rol.setId(rs.getInt(1));
               rol.setRol(rs.getString(2));
               roles.add(rol);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
        }
        return roles;
    }
	
}
