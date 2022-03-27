<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../estructura/header.jsp" %>
<%@include file="../../estructura/menu.jsp" %>
<div class="container mt-4" >
    <div class="card border-success">
        <form action="ControladorUsuario" method="POST">
            <div class="card-header bg-transparent border-success">Editar Usuario</div>
            <div class="card-body text-success">
                <div class="row">
                    <div class="col-sm-4 form-group">
                        <label >Id</label>
                        <input type="text" value="${usuario.id}" name="txtid" class="form-control" readonly="">
                    </div>
                    <div class="col-sm-4 form-group">
                        <label >Documento</label>
                        <input type="text"  value="${usuario.dni}" name="txtdni" class="form-control">
                    </div>
                    <div class="col-sm-4 form-group">
                        <label >Nombre</label>
                        <input type="text" value="${usuario.getNombres()}"  name="txtnombre" class="form-control">
                    </div>
                    <div class="col-sm-4 form-group">
                        <label >Direccion</label>
                        <input type="text" value="${usuario.direccion}" name="txtdirec" class="form-control">
                    </div>
                    <div class="col-sm-4 form-group">
                        <label >Email</label>
                        <input type="email" value="${usuario.email}" name="txtemail" class="form-control">
                    </div>
                    <div class="col-sm-4 form-group">
                        <label >Rol</label>
                        <select name="txtrol"class="form-control">
                            <option value="0">Seleccionar</option>
                            <c:forEach var="rol" items="${roles}">
                                <c:if test="${usuario.rol.id==rol.id}">
                                    <option selected="" value="${rol.id}">${rol.rol}</option>
                                </c:if>                               
                                <c:if test="${usuario.rol.id!=rol.id}">
                                    <option  value="${rol.id}">${rol.rol}</option>
                                </c:if>                               
                            </c:forEach>                            
                        </select>
                    </div>
                </div>

            </div>
            <div class="card-footer bg-transparent border-success">
                <button type="submit" value="guardarCambios" class="btn btn-success" name="accion"><i class="far fa-save"></i> Guardar Cambios</button>            
                <a href="ControladorUsuario?accion=listarusuario">Volver</a>           
            </div>
        </form>

    </div>



</div>

<%@include file="../../estructura/footer.jsp" %>
