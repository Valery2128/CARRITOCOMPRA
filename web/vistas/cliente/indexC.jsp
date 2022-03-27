<<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link href="cssB/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="buscar.css" rel="stylesheet" type="text/css"/>
        
        <script src="https://kit.fontawesome.com/4a02ae2b25.js" crossorigin="anonymous"></script>
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
        Statement smt;
        smt = con.createStatement();
        ResultSet rs;
        ps=con.prepareStatement("select * from cliente");
        rs=ps.executeQuery();

        %>
        <div class="container ">
           
            <br>
            <br>
            <form action="" class="form">
                <input class="form-control" type="text" name="txtbuscar">
                <input class="btn btn-info " type="submit" value="buscar" >
            </form>
            <a class="btn btn-success btn-lg" href="agregar.jsp"><i class="fas fa-user-plus"></i></a>
            <%
                String nombuscar=request.getParameter("txtbuscar");
                if(nombuscar!=null){
                    
                    rs=smt.executeQuery("select * from cliente where Nombres LIKE"+"'%"+nombuscar+"%'");
                }else{
                    System.err.println("Error");
                    
                }
            %>
            <br>
            <br>
            <table class="table table-bordered">
                <tr class="table-primary">
                    <th class="text-center">idCliente</th>
                    <th class="text-center" >Documento</th>
                    <th class="text-center">Nombres</th>
                    <th class="text-center" >Direccion</th>
                  
                    <th class="text-center" >Email</th>
                    <th class="text-center" >Password</th>
                    
                    
                    
                   
                    <th class="text-center" >Acciones</th>
                                     
                </tr>
                <%
                    while(rs.next()){
                %>
                <tr>
                    <td class="table-light text-center"><%= rs.getInt("idCliente")%> </td>
                     <td class="table-light text-center"><%= rs.getInt("Documento")%> </td>
                    <td class="table-light text-center"><%= rs.getString("Nombres")%> </td>
                  
                    <td class="table-light text-center"><%= rs.getString("Direccion")%> </td>
                    <td class="table-light text-center"><%= rs.getString("Email")%> </td>
                    <td class="table-light text-center"><%= rs.getString("Password")%> </td>
                    
                    
                    
                    
                    
                    <td class="table-light text-center">
                        <a href="editar.jsp?idCliente=<%= rs.getInt("idCliente")%>" class="btn btn-warning btn-lg bi bi-arrow-repeat"><i class="fas fa-sync"></i> </a>
                        <a href="delete.jsp?idCliente=<%= rs.getInt("idCliente")%>" class="btn btn-danger btn-lg" ><i class="fas fa-trash-alt"></i> </a>
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
        
    </body>
</html>
