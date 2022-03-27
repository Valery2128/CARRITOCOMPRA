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
           <h2>Agregar Cliente!</h2>
            <hr>
            <form action="" method="post" class="form-control" style="width:600px; height: 500px ;">
               
                <br>
                Documento
                <br>
                <input type="text" name="txtdocum" class="form-control"/><br>
            Nombres
                <br>
                <input type="text" name="txtnombres" class="form-control"/><br>
               Direccion 
               
                <br>
                <input type="text" name="txtdirec" class="form-control"/><br>
                Email
                 <input type="text" name="txtemail" class="form-control"/><br>
                 Password
                  <input type="text" name="txtpass" class="form-control"/><br>
              
               
                
                
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
        String docum,nombres,direccion,email,pass;
        
        
        docum=request.getParameter("txtdocum");
        nombres=request.getParameter("txtnombres");
        direccion=request.getParameter("txtdirec");
        email=request.getParameter("txtemail");
        pass=request.getParameter("txtpass");
        
        
      
        
        if(  docum!=null  && nombres!=null && direccion!=null && email!=null && pass!=null){
            ps=con.prepareStatement("insert into cliente(Documento,Nombres,Direccion,Email,Password)values('"+docum+"','"+nombres+"','"+direccion+"','"+email+"','"+pass+"')");
            ps.executeUpdate();
            response.sendRedirect("indexC.jsp");
        }
        %>