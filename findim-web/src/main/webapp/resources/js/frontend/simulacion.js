function inicioControles(){
}

function PrintElem(elem)
{
    Popup($(elem).html());
}

function Popup(data)
{
    var mywindow = window.open('', 'mydiv', 'height=400,width=600');
    mywindow.document.write('<html><head><title>my div</title>');
    /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
    mywindow.document.write('</head><body >');
    mywindow.document.write(data);
    mywindow.document.write('</body></html>');

    mywindow.print();
    mywindow.close();

    return true;
}

function Popup(data) 
{
    var mywindow = window.open('', 'mydiv', 'height=400,width=600');
    mywindow.document.write('<html><head><title>SIMULACION DE CUOTAS</title>');
    /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
    mywindow.document.write('</head><body >');
    mywindow.document.write(data);
    mywindow.document.write('</body></html>');
    
    mywindow.document.close(); // necessary for IE >= 10
    mywindow.focus(); // necessary for IE >= 10

    mywindow.print();
    mywindow.close();

    return true;
}

function printdiv(printpage) {
	// window.print();
	/*var divToPrint = document.getElementById('mydiv');
	var newWin = window.open();
	newWin.document.write(divToPrint.innerHTML);
	newWin.document.close();
    window.setTimeout(function() {newWin.close()}, 3000);
	newWin.focus();
	newWin.print();
	newWin.close();*/

	var mapForm = document.createElement("form");
	    mapForm.target = 'Map';
	    mapForm.method = 'POST';
	    mapForm.action = gPath + '/simulacionImpresion';

	    var mapInput = document.createElement('input');
	    mapInput.type = 'text';
        mapInput.name = 'objetoContenedor';
	    mapInput.value = JSON.stringify(objetoContenedor);
	    mapInput.setAttribute('style','display:none');
	    mapForm.appendChild(mapInput);

	    document.body.appendChild(mapForm);

	    map = window.open(gPath + '/simulacionImpresion', 'Map', 'status=0,title=0,height=0,width=0,scrollbars=1');

	if (map) {
	    mapForm.submit();
	} else {
	    alert('Debe permitir popups para esta pagina.');
	}

}

// Definir variables usando globales (ver globalVars.js) 
//variables Globales
var navStatus = 0,
	cabecera = $("#cabecera"),
	cabeceraTitulo =$("#cabeceraTitulo"),
	formulario =$("#formulario"),
	preloader = $("#preloader"),    
	alerta = $("#alerta"),
	contenido =$("#contenido"),
	simulacionResultadoMoneda = $("#simulacion-resultado-moneda"),
	simulacionCodigoTarifa = $("#simulacion-codigo-tarifa"),
	simulacionResultadoEquipo=$("#simulacion-resultado-equipo"),
	simulacionSaldoFinanciar =$("#simulacion-resultado-Saldo"),
	simulacionNumCuotas = $("#simulacion-resultado-num-cuotas"),
	simulacionResultadoTEA = $("#simulacion-resultado-TEA"),
	simulacionResultadoTCEA = $("#simulacion-resultado-TCEA"),
	numeroCuotas =$("#numeroCuotas"),
	simulacionResultadoSeguro = $("#simulacion-resultado-seguro"),
	contenidoTabla = $("#tablaContenidoBody"),
	totalSaldoCapital = $("#totalSaldoCapital"),
	totalAmortizacion = $("#totalAmortizacion"),
	totalIntereses = $("#totalIntereses"),
	totalSeguroDesgravamen = $("#totalSeguroDesgravamen"),
	totalComisionEnvio = $("#totalComisionEnvio"),
	totalCuotaTotal = $("#totalCuotaTotal"),
	listen_valor = $("#simulacion_valor_equipo"),
	listen_porcentaje =$("#simulacion_cuota_inicial_porcentaje"),
	changeCuotaInicial =$("#simulacion_cuota_inicial"),
	changeSaldoAFinanciar =$("#simulacion_saldo_a_financiar"),
	cargoFijoMaximoResultado = $("#cargoFijoMaximoResultado"),
	formularioDocumento = $("#form-simulacion");


// Esconde todos los elementos para ver que muestra
	formulario.hide();
	preloader.hide();
	alerta.hide();
	contenido.hide();

function cargarParametria () {
	//carga las variables de la parametria en la vista del controlador 
	console.log("cargando Parametria");
	$("#simulacion_fecha_inicio").val(objetoContenedor.fechaPrestamo);
	$("#simulacion_fecha_vencimiento").val(objetoContenedor.fechaVencimiento);

	//codigoTarifa cuota tasa
		
	var lista = objetoContenedor.listaTarifa;
	
	for (var i = 0; i < lista.length ; i++){
		$("#simulacion_codigo_tarifa").append(
			"<option value='" + objetoContenedor.listaTarifa[i].codigoTarifa + " | " + objetoContenedor.listaTarifa[i].cuota +  " | " + objetoContenedor.listaTarifa[i].tasa + "'>" + objetoContenedor.listaTarifa[i].codigoTarifa + " | " + objetoContenedor.listaTarifa[i].cuota +  " | " + objetoContenedor.listaTarifa[i].tasa  +  " % " + "</option>"
		);
	}
	
	var listaParam = objetoContenedor.listaComprobantePago;
	
	for (var i = 0; i < listaParam.length ; i++){
		$("#simulacion_metodo_envio").append(
			"<option value='" + objetoContenedor.listaComprobantePago[i].nb_valorparamdeta + "'>" + objetoContenedor.listaComprobantePago[i].nb_paramdetalle+ "</option>"
		);
	}
	

	if (lista.length == 0){
		$('#btn_simulacion_form').attr("disabled", true);
		$("#simulacion_codigo_tarifa").append(
				"<option value='0'>No Disponible</option>"
		);
	}
	
	cambiarVentana(0);
	
}


function actualizarContenidos (){
 // actualiza el resultado de la simulacion
	if ((objetoContenedor.cabecera.cargoFijoMaximo)!=""){
		cargoFijoMaximoResultado.text(parseFloat(((objetoContenedor.cabecera.cargoFijoMaximo).replace(',', '.'))).toFixed(2));
	}else
	{
		cargoFijoMaximoResultado.text(objetoContenedor.cabecera.cargoFijoMaximo);
	}
	simulacionResultadoMoneda.text(objetoContenedor.cabecera.moneda);
	simulacionCodigoTarifa.text(objetoContenedor.cabecera.codigoTarifa);
	simulacionResultadoEquipo.text(objetoContenedor.cabecera.valorEquipoS);
	simulacionSaldoFinanciar.text((objetoContenedor.cabecera.saldoFinanciar).toFixed(2));	
	simulacionNumCuotas.text(objetoContenedor.cabecera.numeroCuotas);
	simulacionResultadoTEA.text(objetoContenedor.cabecera.tea);
	simulacionResultadoTCEA.text(parseFloat(((objetoContenedor.cabecera.tcea).replace(',', '.'))).toFixed(2));
//	if ((objetoContenedor.cabecera.cargoFijoMaximo)!=""){
//	simulacionResultadoSeguro.text((objetoContenedor.cabecera.seguroDesgravamen).toFixed(3));
	simulacionResultadoSeguro.text(objetoContenedor.cabecera.seguroDesgravamen);
//	}else
//	{
//		simulacionResultadoSeguro.text((objetoContenedor.cabecera.seguroDesgravamen));
//	}
	totalArrayTabla = objetoContenedor.detalle.length;
	
	contenidoTabla.children().remove();
	
	//contenidoTabla
	for(i=0;i < totalArrayTabla ;i++){
		contenidoTabla.append(
				"<tr>" + "<td>" + objetoContenedor.detalle[i].numeroCuota +  "</td>"
                + "<td>" + objetoContenedor.detalle[i].fechaPago + "</td>"
                + "<td>" + objetoContenedor.detalle[i].saldoAntesS + "</td>"
                + "<td>" + objetoContenedor.detalle[i].amortizacion + "</td>"
                + "<td>" + objetoContenedor.detalle[i].interesMensual + "</td>"
                + "<td style='text-align: center'>" + objetoContenedor.detalle[i].seguro + "</td>"
                + "<td style='text-align: center'>" + objetoContenedor.detalle[i].comisionEnvioPago + "</td>"
                + "<td>" + objetoContenedor.detalle[i].cuotaTotal + "</td>"
                + "</tr>"
		);
		
		console.log(objetoContenedor.detalle[i].saldoCapital);
		console.log(objetoContenedor.detalle[i].seguroDesgravamen);
		console.log(objetoContenedor.detalle[i].comisionEnvioPago);
	}
	
	// table Footer
//	totalSaldoCapital.text( objetoContenedor.totales.totalSaldoCapital );
	totalAmortizacion.text( objetoContenedor.totales.totalAmortizacion );
	totalIntereses.text( objetoContenedor.totales.totalIntereses);
//	totalSeguroDesgravamen.text((objetoContenedor.totales.totalSeguroDesgravamen).toFixed(2));
	totalSeguroDesgravamen.text(parseFloat(((objetoContenedor.totales.totalSeguroDesgravamen).replace(',', '.'))).toFixed(2));
	totalComisionEnvio.text( objetoContenedor.totales.comisionEnvio );
	totalCuotaTotal.text( objetoContenedor.totales.totalCuotaTotal );
	
	console.log(objetoContenedor);
	
	
} 

listen_valor.change(function() { calcularValor(); });

listen_porcentaje.change(function() { calcularValor(); });

function calcularValor(){
    if ( listen_valor.val() > 0.01  && listen_porcentaje.val() >= 0.0 ) {
        changeCuotaInicial.val('Calculando');

        var k = ( listen_valor.val() *  listen_porcentaje.val() ) / 100;
        k = k.toFixed(2);		

        var i = (  listen_valor.val() * 100) / 100;
        i = i.toFixed(2);
        listen_valor.val(i);

        changeCuotaInicial.val(k);
        calcularSaldo ();

    } else {
        changeCuotaInicial.val('No se puede calcular');
        console.log(" Nulo o cero");
    }
}

function calcularSaldo (){	
    var resultado = ( listen_valor.val() - changeCuotaInicial.val() );
    resultado = resultado.toFixed(2);
    changeSaldoAFinanciar.val(resultado);

}

$(function() {
	
	console.log(gPath);
	
	
    // Valida formulario     
    formularioDocumento.validate({
        rules: {
            simulacion_valor_equipo: {
                required: true,
                number: true
            },

            simulacion_cuota_inicial_porcentaje: {
                required: true,
                number:true
            }

        },

        messages: {
            simulacion_valor_equipo: {
                number: "Utilice sólo numeros",
            },

            simulacion_cuota_inicial_porcentaje: {	
                number: "Utilice sólo numeros",
            }
        },
        submitHandler: function(form) {
        	procesarFormulario();
        	
        }
    });  
    
    //procesa formulario    
    function procesarFormulario (){
    	datosSimulacion();
        cambiarVentana(1);        
    }
    
    
    cambiarVentana(1);
    datosInicialesDeSimulacion();
    console.log("ejecuto accion  " + gPath);
});

function datosSimulacion (){		
	objetoContenedor.cargoFijoMaximo = $( "#cargoFijoMaximo" ).val();		
    objetoContenedor.tipoMoneda = $( "#simulacion_moneda option:selected" ).val();
    objetoContenedor.valorEquipo = $( "#simulacion_valor_equipo" ).val(); 
    objetoContenedor.tarifaFinanciamiento = $( "#simulacion_codigo_tarifa option:selected" ).val(); 
    objetoContenedor.porcentajeCuotaInicial = $( "#simulacion_cuota_inicial_porcentaje" ).val();  
    objetoContenedor.cuotaInicial = $( "#simulacion_cuota_inicial" ).val();
    objetoContenedor.saldoAFinanciar = $( "#simulacion_saldo_a_financiar" ).val();  
    objetoContenedor.fechaInicio = $( "#simulacion_fecha_inicio" ).val();
    objetoContenedor.fechaVencimiento = $( "#simulacion_fecha_vencimiento" ).val(); 
    objetoContenedor.metodoEnvio = $( "#simulacion_metodo_envio option:selected" ).val(); 
    
    cargarSimulacion();
};
