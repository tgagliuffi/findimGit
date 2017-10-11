// Cambia los mensajes de error de todos los negocios
function actualizarAlertas(){
    switch(objetoContenedor.tipoError){  
        case 0:
            // no aprobado //
            alertaTitulo.text("No se encuentra el servidor");
            alertaTexto.text("Error 3");

            break;

        case 1:
            alertaTitulo.text("Error de conexión");
            alertaTexto.text("Error Código 2");
            break;
        case 2:
            alertaTitulo.text("Tiempo de espera superado");
            alertaTexto.text("Error 4");	                
            break;		    
        default:
            // Dispara un Error de timeout -- fallo de conexión
            alertaTitulo.text("Fallo de Conexión, el servidor está tardando mucho en responder");
            alertaTexto.text("");		
        break;  
    }   
        
}


// Controlador JS  = cambia entre las pantallas del negocio, ocultando o mostrando 
// el formulario, preload, alerta o contenido de las paginas.
function cambiarVentana(e){
    console.log("cambiando ventanas");
    switch (e){
        case 0:
            //console.log("entra a form");
            formulario.show();preloader.hide();alerta.hide();contenido.hide();
            navStatus = 0;
            
            
            break;
        case 1:
            //console.log("entra a preload");
            formulario.hide();preloader.show();alerta.hide();contenido.hide();
            navStatus = 1;
            break;
        case 2:
            //console.log("entra a alerta");
            formulario.hide();preloader.hide();alerta.show();contenido.hide();
            actualizarAlertas();
            navStatus = 2;
            //$('#formulario')[0].reset();
            break;
        case 3:
            console.log("entra a contenido");
            formulario.hide();preloader.hide();alerta.hide();contenido.show();   
            navStatus = 3;
            actualizarContenidos();
            //$('#formulario')[0].reset();
            break;
        default:
            //console.log("entra a error");
            formulario.hide();preloader.hide();alerta.show();contenido.hide();
            objetoContenedor.tipoRespuesta = 0;
            objetoContenedor.tipoError = 0;
            navStatus = 2;
            cambiarVentana(navStatus); 
            break;
    }        
}

// Respuesta es el controlador 
function respuesta() {
	debugger;
	$('#alertaFiltroTexto').text(objetoContenedor.codigoError || '');
    switch(objetoContenedor.tipoRespuesta){
        case 0:
            console.log("error desde ws");
            //error no ejecuto el codigo correctamente desde WS
            cambiarVentana(2);
            break;
        case 1:
            console.log("respuesta: 1");
            //exito, tenemos data para trabajar
            cambiarVentana(3);
            break;
        default:
            //error, cualquier valor distinto a 0 o 1 devuelve error
            if (objetoContenedor.tipoRespuesta == undefined) {
                //console.log("undefined");
                //formularioDocumento[0].reset();
                cambiarVentana(0);  
            } else {
                //console.log("no es undefined");
                cambiarVentana(2);
            }
            break;
    }   
}

var volverInicio = $("#volverAlerta"),
	volverInicio2 = $("#volverContenido");

volverInicio.click(function() {
	cambiarVentana(0);
});

volverInicio2.click(function(){
	cambiarVentana(0);
});
