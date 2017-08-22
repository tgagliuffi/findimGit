'use strict';
// Definir variables usando globales (ver globalVars.js) 
//variables Globales
var navStatus = 0,
    cabecera = $("#cabecera"),
    cabeceraTitulo =$("cabeceraTitulo"),
    formulario =$("#formulario"),
    preloader = $("#preloader"),    
    alerta = $("#alerta"),
    contenido =$("#contenido"),
    formularioDocumento = $("#formularioDocumento"),
    alertaStatus = $("#alertaStatus"),
    alertaTitulo = $("#alertaTitulo"),
    alertaTexto = $("#alertaTexto"),
    alertaFiltro = $("#alertaFiltro"),
    alertaFiltroTitulo = $("#alertaFiltroTitulo"),
    alertaFiltroTexto = $("#alertaFiltroTexto");
    
    //variables locales BTNS
    var volverForm = $("#volverForm"),
        volverContenido = $("#volverContenido"),
        volverAlerta = $("#volverAlerta");

    // Esconde todos los elementos para ver que muestra
    formulario.hide();
    preloader.hide();
    alerta.hide();
    contenido.hide();

    
function actualizarContenidos (){
	alertaFiltro.removeClass(" alert-danger alert-success alert-warning ");
    //filtro BBVA
    switch(objetoContenedor.codigoResultado){  
	    case 2:
            // no aprobado //
            alertaFiltro.addClass(" alert-danger ");
            alertaFiltro.addClass(" alert-danger-text ");
            alertaFiltroTitulo.text(objetoContenedor.tituloMostrar);
            alertaTexto.text(""); 
            break;
        case 1:
            alertaFiltro.addClass(" alert-success ");
            alertaFiltro.addClass(" alert-success-text ");
            alertaFiltroTitulo.text(objetoContenedor.tituloMostrar);
            alertaTexto.text("");
            break;
	    default:
            // Dispara un Error de timeout -- fallo de conexión
            alertaFiltro.addClass(" alert-warning ");
            alertaFiltro.addClass(" alert-warning-text ");
            alertaFiltroTitulo.text("Error en sistema");
            alertaTexto.text("error código: " + objetoContenedor.tipoError ); 
		break;  
    }           
}     
    


$(document).ready(function() {
    // Valida formulario     
    formularioDocumento.validate({
        rules: {
            numDocumento: {
                required: true,
                number: true,
                minlength:8,
                maxlength:8
            }
        },
        messages: {
            numDocumento: {
                number: "Utilice sólo numeros",
                minlength: "Mínimo 8 caracteres",
                maxlength:"Máximo 8 caracteres"
            }
        },
        submitHandler: function(form) {procesarFormulario();}
    });  

    //procesa formulario    
    function procesarFormulario() {
        objetoContenedor.numeroDocumento = $("#numDocumento").val();
        objetoContenedor.tipoDocumento = $("#tipoDocumento").val();
        console.log(objetoContenedor);
        cambiarVentana(1);
        filtroCliente();
    }

    //cambiarVentana(657);
     
    /*
     *     objetoContenedor.codigoResultado = 1;  este es 0 o 1 y solo para filtro Aprobado o Rechazado
     * objetoContenedor.tipoRespuesta = 125;   0 = problema WS 1 = salio bien me envias data

    objetoContenedor.tipoError = 105; Tipo de error de lista parametria
   */
    //cambiarVentana(3);
    respuesta();  
   
});
