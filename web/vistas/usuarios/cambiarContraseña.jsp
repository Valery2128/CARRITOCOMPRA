<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../estructura/header.jsp" %>
<%@include file="../../estructura/menu.jsp" %>
<div class="container mt-4" >
    <div class="card border-success">
        <form action="ControladorUsuario" method="POST">
            <div class="card-header bg-transparent border-success">Cambiar Contraseña</div>
            <div class="card-body text-success">
                <div class="row">
                    <div class="col-sm-4 form-group">
                        <label >Documento</label>
                        <input type="text" name="txtDocumento" class="form-control">
                    </div>
                    <div class="col-sm-4 form-group">
                        <label >Correo</label>
                        <input type="email" name="txtCorreo" class="form-control">
                    </div> 
                    <div class="col-sm-4 form-group">
                        <label >Nueva Contraseña</label>
                        <input type="text" name="txtContraseña" class="form-control">
                    </div>
                </div> 
                <button class="btn btn-outline-primary" name="accion" type="submit"value="ActualizarPass">Guardar Cambios</button>
            </div>
        </form>
    </div>
</div>

