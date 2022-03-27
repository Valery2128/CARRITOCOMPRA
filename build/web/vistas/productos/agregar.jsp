<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        
    </head>
    <body>
       <div class="container " style="padding-left: 290px;">
           <h2>Agregar producto!</h2>
            <hr>
            <form action="" method="post" class="form-control" style="width:600px; height: 500px ;">
                Producto
                <br>
                <input type="text" name="txtId" class="form-control"/>
                <br>
                Nombres
                <br>
                <input type="text" name="txtnombres" class="form-control"/><br>
             descripcion
                <br>
                <input type="text" name="txtdesc" class="form-control"/><br>
                
               precio
                <br>
                <input type="text" name="txtprecio" class="form-control"/><br>
              
               
                
                
                <input type="submit" name="Guardar" class="btn btn-primary btn-sm"/>
                
                <br>
                <br>
                <a class="btn btn-info btn-ms" href="indexP.jsp">Regresar</a>  
                
            </form>
        </div>
        
    </body>
</html>








<%
        Connection con;
        String url="jdbc:mysql://localhost:3306/proyectotienda";
        String Driver="com.mysql.jdbc.Driver";
        String user="root";
        String clave="";
        Class.forName(Driver);
        con=DriverManager.getConnection(url,user,clave);
        
        PreparedStatement ps;
        String idpro,nombre,descripcion,precio;
        
        idpro=request.getParameter("txtId");
        nombre=request.getParameter("txtnombre");
        descripcion=request.getParameter("txtdesc");
        precio=request.getParameter("txtprecio");
        
        
      
        
        if(idpro!=null && nombre!=null && descripcion!=null  && precio!=null ){
            ps=con.prepareStatement("insert into producto(idProducto,Nombres,Descripcion,Precio)values('"+idpro+"','"+nombre+"','"+descripcion+"','"+precio+"',')");
            ps.executeUpdate();
            response.sendRedirect("indexP.jsp");
        }
        %>