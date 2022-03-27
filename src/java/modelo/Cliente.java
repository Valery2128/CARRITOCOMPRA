package modelo;

import config.Conexion;
import controlador.ConexionC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {

    private int id;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public class login extends ConexionC {

        public boolean autenticacion(String Email, String Password) throws SQLException {
            PreparedStatement pst = null;
            ResultSet rs = null;
            try {
                String consulta = "select*from Cliente where Email=? and Password = ?";
                pst = getConexion().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                pst.setString(1, Email);
                pst.setString(2, Password);
                rs = pst.executeQuery();
                if (rs.absolute(1)) {
                    return true;

                }
            } catch (SQLException e) {
                System.out.println("Error" + e);
            } finally {
                try {
                    if (getConexion() != null) {
                        getConexion().close();
                        if (pst != null) {
                            pst.close();
                        }
                        if (rs != null) {
                            rs.close();
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Error" + e);

                }

            }

            return false;
        }

        public boolean registrar(String Documento, String Nombres, String Direccion, String Email, String Password) {
            PreparedStatement pst = null;
            try {
                String consulta = ("insert into Cliente (Documento,Nombres,Direccion,Email,Password) values (?,?,?,?,?)");
                pst = getConexion().prepareStatement(consulta);
                pst.setString(1, Documento);
                pst.setString(2, Nombres);
                pst.setString(3, Direccion);
                pst.setString(4, Email);
                pst.setString(5, Password);

                if (pst.executeUpdate() == 1) {
                    return true;

                }
            } catch (Exception f) {
                System.out.println("Error" + f);

            } finally {
                try {
                    if (getConexion() != null) {
                        getConexion().close();
                    }
                    if (pst != null) {
                        pst.close();
                    }
                } catch (Exception F) {
                    System.out.println("Error" + F);
                }

            }
            return false;
        }

    }

}
