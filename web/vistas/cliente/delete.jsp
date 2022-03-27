<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Connection con;
        String url="jdbc:mysql://localhost:3306/proyectotienda";
        String Driver="com.mysql.jdbc.Driver";
        String user="root";
        String clave="";
        Class.forName(Driver);
        con=DriverManager.getConnection(url,user,clave);
        
        PreparedStatement ps;
        
        int id=Integer.parseInt(request.getParameter("idCliente"));
        ps=con.prepareStatement("delete from cliente where idCliente="+id);
        ps.executeUpdate();
        response.sendRedirect("indexC.jsp");
        
        
        %>
        
    </body>
</html>