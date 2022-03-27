<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./estructura/header.jsp" %>
<%@include file="./estructura/menu.jsp" %>
<div class="container mt-4">  
    <h3>Carrito</h3>
    <br>
    <div class="row">
        <div class="col-sm-8">
            <table class="table table-horver">
                <thead>
                    <tr>
                        <th>ITEM</th>
                        <th>Nombres</th>
                        <th>Descripcion</th>
                        <th>Precio</th>
                        <th>Cant</th>
                        <th>SubTotal</th>
                        <th>Accion</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="car" items="${carrito}">
                        <tr>
                            <td>${car.getItem()}</td>
                            <td>${car.getNombres()}</td>
                            <td>${car.getDescripcion()}
                                <img src="ControladorIMG?id=${car.getIdProducto()}" width="100" height="100"> </td>
                            <td>${car.getPrecioCompra()}</td>
                            <td> 
                                <input type="hidden" id="idpro" value="${car.getIdProducto()}">
                                <input type="number" id="Cantidad" value="${car.getCantidad()}" class="form-control text-center" min="1">
                            </td>
                            <td>${car.getSubTotal()}</td>
                            <td>
                                <input type="hidden" id="idp" value="${car.getIdProducto()}"> 
                                <a class="btn btn-danger btn-sm trash" id="btnDelete">eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>

        </div>
        <div class="col-sm-4">
            <div class="card">
                <div class="card-header">
                    <h3>Generar Compra</h3>
                </div>
                <div class="card-body">
                    <label>Subtotal:</label>
                    <input type="text" value="$.${totalPagar}0" readonly="" class="form-control">
                    <label>Descuento:</label>
                    <input type="text" value="$.0.00" readonly="" class="form-control">
                    <label>Total a Pagar:</label>
                    <input type="text" value="$.${totalPagar}0" readonly="" class="form-control">
                </div>
                <div class="card-footer">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                        Realizar Pago
                    </button>
                    <!--<a href="Controlador?accion=GenerarCompra" class= "btn btn-danger btn-block" id="GenerarCompra">Generar Compra</a>-->
                    <form method="post" action="Controlador">
                        <c:forEach var="car" items="${carrito}">
                            <input type="hidden" class="btn btn-success"  value="${car.getItem()}" >  
                            <input type="hidden" class="btn btn-success"  value="${car.getNombres()}" > 
                            <input type="hidden" class="btn btn-success"  value="${car.getDescripcion()}" >
                            <input type="hidden" class="btn btn-success"  value="${car.getIdProducto()}" >
                            <input type="hidden" class="btn btn-success"  value="${car.getPrecioCompra()}" >
                        </c:forEach>

                        <input type="hidden" value="Comprar" name="accion">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div> 



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="Controlador" method="POST">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">REALIZAR PAGO</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">NOMBRES TITULAR</label>
                        <input type="text" class="form-control" name="txtnombre">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">CARD NUMBER</label>
                        <input type="text" class="form-control" name="txtnumero">
                        <div id="emailHelp" class="form-text">El numero debe contener 16 digitos.</div>
                    </div>
                    <div class="row">
                        <div class="col col-sm-6">
                            <label class="form-label">CODIGO SEGURIDAD</label>
                            <input type="text" name="txtcodigo" class="form-control" id="exampleInputPassword1">
                        </div>
                        <div class="col col-sm-6">
                            <label  class="form-label">FECHA VENCIMIENTO</label>
                            <input type="text" name="txtfecha" class="form-control" id="exampleInputPassword1">
                        </div>
                    </div>                            
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" name="accion" value="RealizarPago" class="btn btn-primary">Pagar</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="./estructura/footer.jsp" %>
