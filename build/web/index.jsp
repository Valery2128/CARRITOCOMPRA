<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="estructura/header.jsp" %>
<%@include file="estructura/menu.jsp" %>
<div class="container mt-4">
    <div class="row">
        <c:forEach var="p" items="${productos}"> 
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-header text-center">
                        <label>${p.getNombres()}</label>
                    </div>
                    <div class="card-body">
                        <i>$.${p.getPrecio()}</i>
                        <img src="ControladorIMG?id=${p.getId()}" width="200" height="180">   
                    </div>
                    <div class="card-footer text-center">                                
                        <label>${p.getDescripcion()}</label>
                        <br>
                        <div>
                            <c:if test="${UsuarioLogueado==null}">
                                <a href="ControladorLogin?accion=IniciarSession" class="btn btn-outline-info">Agregar a Carrito</a>                                
                             
                            </c:if>
                            <c:if test="${UsuarioLogueado!=null}">
                               <a href="Controlador?accion=AgregarCarrito&id=${p.getId()}" class="btn btn-outline-info">Agregar a Carrito</a>
                            
                            </c:if>
                           <a href="Controlador?accion=Comprar&id=${p.getId()}" class="btn btn-danger">Comprar</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="estructura/footer.jsp" %>







