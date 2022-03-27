<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
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
        ResultSet rs;
        int idE=Integer.parseInt(request.getParameter("idCliente"));
        ps=con.prepareStatement("select * from cliente where idCliente="+idE);
        rs=ps.executeQuery();
        while(rs.next()){
        
       

        %>
       <div class="container" style="padding-left: 90px;">
            <h1>Editar Cliente!</h1>
            <hr>
            <form action="" method="post" class="form-control" style="width:400px; height: 600px">
                
             
                <br>
                Documento
                <br>
                <input type="text" name="txtDocumento" class="form-control" value="<%= rs.getString("Documento")%>"/>
                <br>
                Nombres
                <br>
                <input type="text" name="txtNombres" class="form-control" value="<%= rs.getString("Nombres")%>"/><br>
                Direccion
                <br>
                <input type="text" name="txtdirec" class="form-control" value="<%= rs.getString("Direccion")%>"/><br>
               
               Email
                <br>
                <input type="text" name="txtemail" class="form-control" value="<%= rs.getString("Email")%>"/><br>
               Contraseña
                <br>
                <input type="text"   name="txtpass" class="form-control" value="<%= rs.getString("Password")%>"/><br>
                
                
                <input type="submit" name="Guardar" class="btn btn-primary btn-sm"/>
                
                <br>
                <br>
                <a class="btn btn-info btn-ms" href="indexT.jsp">Regresar</a>  
                
            </form>
            <%}%>
        </div>
        
    </body>
</html>

<%
    String docu,nom,direc,email,pass;
       
        docu=request.getParameter("txtDocumento");
        nom=request.getParameter("txtNombres");
        
        direc=request.getParameter("txtdirec");
        email=request.getParameter("txtemail");
        pass=request.getParameter("txtpass");
        
        if(docu!=null && nom!=null && direc!=null  && email!=null && pass!=null){
            ps=con.prepareStatement("update cliente set Documento='"+docu+"',Nombres='"+nom+"',Direccion='"+direc+"',Email='"+email+"',Password='"+pass+"' where idCliente="+idE);
            
            ps.executeUpdate();
            response.sendRedirect("indexC.jsp");
        }
%>