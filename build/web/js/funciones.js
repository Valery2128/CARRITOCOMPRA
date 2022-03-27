/* global swal */

$(document).ready(function (){
    $("tr #btnDelete").click(function (){
        var idp=$(this).parent().find("#idp").val();
        swal({
                title: "Estas Seguro?",
                text: "Te Puedes Arrepentir!",
                icon: "warning",
                buttons: true,
                dangerMode: true,
                
        }) .then((willDelete) => {
              if (willDelete) {
                eliminar(idp);
                swal("Exelente! Compra Eliminada!", {
                  icon: "success",
                }).then((willDelete)=> {
                    if(willDelete){
                        parent.location.href="Controlador?accion=Carrito";
                    }
                });
              } else {
                swal("Registro no eliminado!");
              }
         });
        
    }) ;
    function eliminar(idp){
        var url="Controlador?accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp="+idp,
            success: function(data,textStatus,jqXHR){
                
            }
        });
    }
    $("tr td #Cantidad").click(function(){
       var idp=$(this).parent().find("#idpro").val();
       var cantidad=$(this).parent().find("#Cantidad").val();
       var url="Controlador?accion=ActualizarCantidad";
       console.log(cantidad);
       $.ajax({
          type:"POST",
          url: url,
          data: {idpro: idp, cant: cantidad},
          success: function (data,textStatus,jqXHR){
              location.href="Controlador?accion=Carrito"; 
          }         

       });
       
    });
    
     $("tr #GenerarCompra").click(function(){
       
     
       var url="Controlador?accion=GenerarCompra";
       $.ajax({
          type:"POST",
          url: url,
     success: function (data,textStatus,jqXHR){
              location.href="Controlador?accion=Carrito";
        
              
              
          }
          

       });
       
    });
});


