<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../estructura/header.jsp" %>
<%@include file="../../estructura/menu.jsp" %>
<div class="container mt-4" >     
    <c:if test="${UsuarioLogueado!=null}">
    <div class="d-flex form-group">
        <a href="ControladorUsuario?accion=nuevousuario" class="btn btn-success"><i class="fas fa-user-plus"></i> Nuevo Usuario </a>
        <h4 class="ml-2 mt-2">Lista de Usuarios</h4>
    </div>
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Documento</th>
                <th scope="col">Nombres</th>
                <th scope="col">Direccion</th>
                <th scope="col">Email</th>
                <th scope="col">Rol</th>  
                <th scope="col">accion</th>  

            </tr>
        </thead>
        <tbody>
            <c:forEach var="usuario" items="${usuarios}" varStatus="in">
                <tr> 
                    <th scope="row"> ${in.index+1}</th>
                    <td>${usuario.dni}</td>
                    <td>${usuario.getNombres()}</td>
                    <td>${usuario.direccion}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.rol.rol}</td>                    
                    <td> 
                        <c:if test="${usuario.dni!='00000001'}">
                            <a href="ControladorUsuario?accion=eliminarusuario&id=${usuario.id}" class="btn btn-danger btn-sm"> <i class="fas fa-trash"></i> </a>
                            <a  href="ControladorUsuario?accion=editarusuario&id=${usuario.id}" class="btn btn-warning btn-sm"><i class="fas fa-edit"></i> </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </c:if>
    <c:if test="${UsuarioLogueado==null}">
        <div class="text-center">
            <img src="img/fondo.png" style="width: 50%; height: 50%">
        </div>
    </c:if>
</div>

<%@include file="../../estructura/footer.jsp" %>
