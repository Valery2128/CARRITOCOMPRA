<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
    <a class="navbar-brand" href="#"><img src="estructura/logoit.png" width="128" height="auto"/></a>
    
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link active" aria-current="page" href="/Carrito/Controlador?accion=home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Controlador?accion=Carrito"><i class="fas fa-cart-plus">(<label style="color: orange"></label>)</i>Carrito</a>
            </li>
            <c:if test="${UsuarioLogueado.rol.rol=='ADMINISTRADOR'}">
                <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
                    <i class="fas fa-cog"></i> Configuracion
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="ControladorUsuario?accion=listarusuario"><i class="fas fa-user-cog"></i> Roles</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="ControladorUsuario?accion=listarusuario"><i class="fas fa-users"></i> Usuarios</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="Controlador?accion=Productos" ><i class="fas fa-luggage-cart"></i> Productos</a>
                </div>
            </li>
            </c:if>            
            <li class="nav-item">
                <a class="nav-link" href="Controlador?accion=Oferta">Ofertas</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/Carrito/Controlador?accion=home">Seguir Comprando</a>
            </li>
        </ul>
        <form action="ControladorLogin" method="POST" class="form-inline my-2 my-lg-0">            
            <a class="mr-2"><i class="fas fa-user-circle"></i> ${UsuarioLogueado.getNombres()}</a>
            <c:if test="${UsuarioLogueado!=null}">
                <a href="ControladorLogin?accion=CerrarSession" class="btn btn-outline-success my-2 my-sm-0" type="submit">Cerrar Sesion</a>
            </c:if>
            <c:if test="${UsuarioLogueado==null}">
                <button name="accion" value="IniciarSession" class="btn btn-outline-success my-2 my-sm-0" type="submit">Iniciar Sesion</button>
            </c:if>
        </form>
    </div>
</nav>
