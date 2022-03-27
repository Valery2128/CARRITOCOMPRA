<%-- 
    Document   : iniciarsesion
    Created on : 26/10/2021, 04:21:09 PM
    Author     : VALERY
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="CSS/InicarSe.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <center>
        <br>
        <br>
        <div class="">
            <div class="login-info-container">
                <h1 class="title">Iniciar Sesion</h1>

                <form action="ControladorLogin" method="POST" class="inputs-container">
                    <div class="h1">Ingrese usuario </div><br>  

                    <input class="input" name="Email" type="text" placeholder="Correo"><br>
                    <div class="h1">Ingrese contraseña </div> <br> 

                    <input type="text" class="input" name="Password"  placeholder="Contraseña"><br>

                    <input class="btn" type="submit"  name="accion"  value="Login"/><br>
                    No tienes una cuenta...<br>  <a style= "color:black"class="span" href="vistas/cliente/registro.jsp"> <br> Registrate</a>
                    <a style= "color:black" class="span" href="ControladorUsuario?accion=CambiarPassword"> <br> Olvidò su contraseña?</a>
                </form>
            </div>

        </div>
    </center>  
</body>
</html>


