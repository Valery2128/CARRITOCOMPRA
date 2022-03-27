<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../../CSS/Registro.css" rel="stylesheet" type="text/css"/>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    </head>
    <body>
        <div class="container">
            <div class="title">Formulario De Registro</div>
            <div class="content">
                <form method="post" action="" id="" onsubmit="">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Documento</span>
                            <input type="text" name="txtdocum" placeholder="Ingrese su Documento" autofocus="true"
                                   required pattern="[A-Za-Z ]" minlength="5" maxlength="25">
                        </div>
                        <div class="input-box">
                            <span class="details">Nombres</span>
                            <input type="text" name="txtnombres" placeholder="Ingrese sus Nombres" required pattern="[A-Za-Z ]" minlength="6" maxlength="12">
                        </div>
                        <div class="input-box">
                            <span class="details">Direccion</span>
                            <input type="Direccion" name="txtdirec" id="Direccion" placeholder="Ingrese su Direccion" required minlength="8" maxlength="20">
                            </div>
                               <div class="input-box">
                                <span class="details">Email</span>
                                <input type="text" name="txtemail" placeholder="Ingrese su Email" autofocus="true"
                                       required pattern="[A-Za-Z ]" minlength="5" maxlength="25">
                                 </div>
                                  <div class="input-box">
                                
                                    <span class="user-details ">Password</span>
                                    <input type="text" name="txtpass" placeholder="Ingrese su Contraseña" autofocus="true"
                                           required pattern="[A-Za-Z ]" minlength="5" maxlength="25">
                                     </div>

                                    <div class="input-box">
                                        <span class="details">Confirmar ontraseña</span>
                                        <input type="text" name="txtconpass" placeholder="Confirme su contraseña" autofocus="true"
                                               required pattern="[A-Za-Z ]" minlength="5" maxlength="25">
                                    </div>
                               
                            
                       



                

            </div>
            <div class="gender-details">
                <input type="radio" name="gender" id="dot-1">          
                <span class="gender-title"></span>
                <div class="category">
                    <label for="dot-1">
                        <span class="dot one"></span>
                        <span for="cb1" type="checkbox" class="gender" required >Estoy de acuerdo con los términos y condiciones</span>            
                    </label>         
                </div>
            </div>
            <div class="button">
                <input type="submit" value="Registrar">
            </div>
        </form>
    </div>
</div>
</div

</body>
</html>

<%
    Connection con;
    String url = "jdbc:mysql://localhost:3306/proyectotienda";
    String Driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String clave = "";
    Class.forName(Driver);
    con = DriverManager.getConnection(url, user, clave);

    PreparedStatement ps;
    String docum, nombres, direccion, email, pass,conpass;

    docum = request.getParameter("txtdocum");
    nombres = request.getParameter("txtnombres");
    direccion = request.getParameter("txtdirec");
    email = request.getParameter("txtemail");
    pass = request.getParameter("txtpass");
    conpass = request.getParameter("txtconpass");

    if (docum != null && nombres != null && direccion != null && email != null && pass != null && conpass != null) {
        ps = con.prepareStatement("insert into cliente(Documento,Nombres,Direccion,Email,Password,Con_pass)values('" + docum + "','" + nombres + "','" + direccion + "','" + email + "','" + pass + "','"+conpass+"')");
        ps.executeUpdate();
        response.sendRedirect("../../iniciarsesion.jsp");
    }
%>
<