
package modelo;

import config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;

public class ProductoDAO extends Conexion {
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
     int r = 0;
     
   public List buscar(String nombre) {
        List list = new ArrayList();
        String sql = "select * from producto where Nombres like '%" + nombre + "%'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));//      
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }
    public  Producto listarId(int id){
        String sql="select *from producto where idProducto="+id;
        Producto p=new Producto();
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                
                
                
            }
        } catch (Exception e) {
        }
        return p;
    }
    
    public List listar(){
    List<Producto>productos=new ArrayList();
    String sql="select * from producto";
        try {
           con=cn.getConnection();
           ps=con.prepareStatement(sql);
           rs=ps.executeQuery();
            while (rs.next()) {                
                Producto p=new  Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                productos.add(p);
            }
            
           
        } catch (Exception e) {
        }
        return  productos;
    }
    public  void listarImg(int id,HttpServletResponse response){
        String sql="select Foto from producto where idProducto="+id;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream=null;
        
        try {
            outputStream=response.getOutputStream();
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if (rs.next()) {
                inputStream=rs.getBinaryStream("Foto");
            }
            bufferedInputStream=new BufferedInputStream(inputStream);
            bufferedOutputStream=new BufferedOutputStream(outputStream);
            int i=0;
            while ((i=bufferedInputStream.read())!=-1) {                
                bufferedOutputStream.write(i);
            }
            con.close();
            ps.close();
            rs.close();
            bufferedInputStream.close();
            bufferedOutputStream.close();            
        } catch (Exception e) {
        }
    }


    public Producto IdProducto (int id) {
        Producto p = new Producto();
        String sql = "select * from producto where IdProducto=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getString(3));
//                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));                
            }
        } catch (Exception e) {
        }
        return p;
    }
   public List listarxCategoria(int cat) {
        List list = new ArrayList();
        String sql = "SELECT p.idProducto,p.Nombres,p.Foto,p.Descripcion,p.Precio,p.Stock, c.Descripcion, p.estado\n"
                + "FROM producto p INNER JOIN categoria c \n"
                + "on p.idCategoria=c.idCategoria where p.idCategoria=" + cat;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));//      
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                p.setCategoria(new Categoria());
                p.getCategoria().setCategoria(rs.getString(7));
                p.setEstado(rs.getString(8));
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }
       public List listarP() {
        List lista = new ArrayList();
        String sql = "SELECT p.idProducto,p.Nombres,p.Foto,p.Descripcion,p.Precio,p.Stock, c.Descripcion, p.estado\n"
                + "FROM producto p INNER JOIN categoria c \n"
                + "on p.idCategoria=c.idCategoria ORDER BY p.idProducto ASC";
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                p.setCategoria(new Categoria());
                p.getCategoria().setCategoria(rs.getString(7));
                p.setEstado(rs.getString(8));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }
 

    public List listarPag(int pag1, int pag2) {  
        List lista = new ArrayList();
        String sql = "SELECT p.idProducto,p.Nombres,p.Foto,p.Descripcion,p.Precio,p.Stock, c.Descripcion, p.estado\n"
                + "FROM producto p INNER JOIN categoria c \n"
                + "on p.idCategoria=c.idCategoria where  p.idProducto BETWEEN "+pag1+" AND "+pag2;
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                p.setCategoria(new Categoria());
                p.getCategoria().setCategoria(rs.getString(7));
                p.setEstado(rs.getString(8));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    public List listarProductoOferta() {
        List lista = new ArrayList();
        String sql = "SELECT p.idProducto,p.Nombres,p.Foto,p.Descripcion,p.Precio,p.Stock, c.Descripcion, p.estado\n"
                + "FROM producto p INNER JOIN categoria c \n"
                + "on p.idCategoria=c.idCategoria where p.estado='20' || p.estado='30' || p.estado='40' || p.estado='50'|| p.estado='60'|| p.estado='70'";
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                p.setCategoria(new Categoria());
                p.getCategoria().setCategoria(rs.getString(7));
                p.setEstado(rs.getString(8));
                lista.add(p);
                 con.close();;
            ps.close();
            rs.close();
            }
        } catch (Exception e) {
            System.err.println("Error al imprimir Producto Oferta" + e);
        }
        return lista;
    }
    public int AgregarNuevoProducto(Producto p) {        
        if(p.getEstado()==null){
            p.setEstado("Normal");
           
        }
        String sql = "insert into producto(Nombres,Foto,Descripcion,Precio,Stock)values(?,?,?,?,?)";
        try {
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getFoto());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            r= ps.executeUpdate();
            
            con.close();;
            ps.close();
            rs.close();
        } catch (Exception e) {
        }
        return r;
    }
    
     public int ActualizarProducto(Producto p) {
        String sql = "update producto set Nombres=?,Foto=?,Descripcion=?,Precio=?,Stock=?,where idProducto=?";
        try {
            if (p.getFoto()!= null) {
                ps = getConnection().prepareStatement(sql);
                ps.setString(1, p.getNombres());
                ps.setString(2, p.getFoto());
                ps.setString(3, p.getDescripcion());
                ps.setDouble(4, p.getPrecio());
                ps.setInt(5, p.getStock());
                ps.setInt(6, p.getId());
                r = ps.executeUpdate();
            } else {
                sql = "update producto set Nombres=?,Descripcion=?,Precio=?,Stock=?, where idProducto=?";
                ps = getConnection().prepareStatement(sql);
                ps.setString(1, p.getNombres());
                ps.setString(2, p.getDescripcion());
                ps.setDouble(3, p.getPrecio());
                ps.setInt(4, p.getStock());
                ps.setInt(5, p.getId());
                r = ps.executeUpdate();
                
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar" + e);
        }
        return r;
    }
   public int ActualizarStock(Producto p) {
        String sql = "update producto set Stock=Stock-? where idProducto=?";
        try {
            ps = getConnection().prepareStatement(sql);
            ps.setInt(1, p.getStock());
            ps.setInt(2, p.getId());
            r = ps.executeUpdate();
            
        } catch (Exception e) {
            System.err.println("Error al actualizar Stock" + e);
            
        }
        return r;
        
    }
    public void EliminarProducto(int id) {
        String sql = "delete from producto where idProducto=" + id;
        try {
            ps = getConnection().prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error al actualizar" + e);
        }
    }
    public List ListarCategoria() {
        List list = new ArrayList();
        String sql = "select * from categoria";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt(1));
                cat.setCategoria(rs.getString(2));
                list.add(cat);
            }
        } catch (Exception e) {
        }
        return list;
    }
        
    }
   

        
    
  
