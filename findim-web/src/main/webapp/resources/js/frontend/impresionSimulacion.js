//variables Globales
var cabecera = $("#cabecera"),
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
	formularioDocumento = $("#form-simulacion"),
	textoLegal=$("#textoLegal");

function closediv() {
	// window.top.close();
	window.close();
}

function printdiv(printpage) {

  var printContents = document.getElementById(printpage).innerHTML;
  var originalContents = document.body.innerHTML;

  document.body.innerHTML = printContents;

  window.print();

  document.body.innerHTML = originalContents;

  closediv();
}

function actualizarContenidos (){
    // actualiza el resultado de la simulacion
	//console.log("Actualizo");
	//Contenidos Superior
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
	simulacionResultadoTCEA.text(objetoContenedor.cabecera.tcea);
//	simulacionResultadoSeguro.text((objetoContenedor.cabecera.seguroDesgravamen).toFixed(2));
	simulacionResultadoSeguro.text(parseFloat(((objetoContenedor.cabecera.seguroDesgravamen).replace(',', '.'))).toFixed(3));
	totalArrayTabla = objetoContenedor.detalle.length;

	contenidoTabla.children().remove();

	//contenidoTabla
	for(var i = 0; i < totalArrayTabla; i++) {
		contenidoTabla.append(
				"<tr>" + "<td>" + objetoContenedor.detalle[i].numeroCuotas +  "</td>"
                + "<td>" + objetoContenedor.detalle[i].fechaPago + "</td>"
                + "<td>" + objetoContenedor.detalle[i].saldoAntesS + "</td>"
                + "<td>" + objetoContenedor.detalle[i].amortizacion + "</td>"
                + "<td>" + objetoContenedor.detalle[i].interesMensual + "</td>"
                + "<td>" + (objetoContenedor.detalle[i].seguro).toFixed(2) + "</td>"
                + "<td>" + objetoContenedor.detalle[i].comisionEnvioPago + "</td>"
                + "<td>" + objetoContenedor.detalle[i].cuotaTotal + "</td>"
                + "</tr>"
		);
	}

	// table Footer
	// totalSaldoCapital.text( objetoContenedor.totales.totalSaldoCapital );
	totalAmortizacion.text( objetoContenedor.totales.totalAmortizacion );
	totalIntereses.text( objetoContenedor.totales.totalIntereses);
//	totalSeguroDesgravamen.text((objetoContenedor.totales.totalSeguroDesgravamen).toFixed(2));
	totalSeguroDesgravamen.text(parseFloat(((objetoContenedor.totales.totalSeguroDesgravamen).replace(',', '.'))).toFixed(2));
	totalComisionEnvio.text( objetoContenedor.totales.comisionEnvio );
	totalCuotaTotal.text( objetoContenedor.totales.totalCuotaTotal );

	///Jalar Array
	/// Concadenar
	var tamanoTablaLegal = objetoContenedor.cabecera.listaTexto.length;

	for (j=0;j < tamanoTablaLegal; j++) {
		textoLegal.append("<tr><td class='footer-text-desc'>" + objetoContenedor.cabecera.listaTexto[j].descripcionTexto +  "</td></tr>"				
		);	
	}
}

$(function() {
	actualizarContenidos();
});

$(window).load(function() {
	setTimeout(function () {
		$('#imprimirSimulacion').click();
	}, 500);
});