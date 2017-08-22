var BIOMATCH_CODIGO_OK = 19000;
var BIOMATCH_LICENCIA_NO_ENCONTRADA = 'NL';
var CODIGO_ERROR_NO_HIT = '70007';

var COD_RESULTADO_ERROR = 0;
var COD_RESULTADO_APROBADO = 1;
var COD_RESULTADO_RECHAZADO = 2;

$.support.cors = true;

function inicioControles(){
}

function filtroCliente(){
	var tipoDocumento = (
		objetoContenedor==null?0:(  
			objetoContenedor.tipoDocumento==undefined?0:objetoContenedor.tipoDocumento
		)	
	);

	var numeroDocumento = (
		objetoContenedor == null ? '' : (
			objetoContenedor.numeroDocumento == undefined ? '' : objetoContenedor.numeroDocumento
		)
	);
	var parametros = "?tipoDocumento=" + tipoDocumento + "&numeroDocumento=" + numeroDocumento;

    $.ajax({
    	type: "GET",
	    url: gPath+"/filtroCliente" + parametros,
	    data: {},
	    context: document.body,
	    success: function(response) {
	    	debugger;
	    	objetoContenedor = response;
	    	if (constants.BIOMATCH_ACTIVO) {
		    	if (objetoContenedor.codigoResultado == COD_RESULTADO_APROBADO) {
		    		verificarHuellaDactilar(tipoDocumento,
		    				numeroDocumento,
		    				PROCESO_BIOMETRIA_FILTRO,
		    				callbackHuellaDactilarFiltroCliente,
		    				callbackResultadoBiometriaFiltro);
		    	} else {
			    	respuesta();
		    	}
	    	} else {
	    		respuesta();
	    	}
	    },
	    error: function(e) {
	    	console.log('Error: ' + e);
	    }
    });
}

function verificarHuellaDactilar(tipoDocumento, numeroDocumento, codigoProceso, callback, callbackResultado) {
	alert("tipo documentp : " +tipoDocumento);
	alert("numeroDocumento : " +numeroDocumento);
	alert("codigoProceso : " +codigoProceso);
	alert("callback : " +callback);
	alert("callbackResultado : " +callbackResultado);
	// Validar maximo de intentos
	$.ajax({
		type: 'GET',
		url: gPath + '/biometria/validarMaximoIntentos/' + numeroDocumento + '?r=' + Math.random(),
		success: function(response) {
			if (!response.resultado) {
				callbackResultado({ codigoError: response.mensaje, resultado: 'E' });
				return;
			}

			var params = 'r=' + Math.random() + '&op=getTk';
			var isIE8Request = window.XDomainRequest;
			if (isIE8Request) {
				var invocation = new window.XDomainRequest();
				invocation.onload = function() {
					var response = invocation.responseText;
					console.log(response);
					handleGetToken(response, tipoDocumento, numeroDocumento, codigoProceso, callback, callbackResultado);
				};
		        invocation.open('GET', constants.BIOMATCH_URL_BASE + '?' + params, true);
		        invocation.send();
			} else {
				$.ajax({
					type: 'GET',
					url: constants.BIOMATCH_URL_BASE + "?" + params,
					crossDomain: true,
					success: function(response) {
						console.log(response);
						handleGetToken(response, tipoDocumento, numeroDocumento, codigoProceso, callback, callbackResultado);
					},
					error: function(xhr, ajaxOptions, thrownError) {
						console.log('No es posible conectarse con el BioMatch: ');
						console.log(thrownError);
						callbackResultado({ codigoError: 0, resultado: 'E' });
					}
				});
			}
		},
		error: function(xhr, error) {
			console.log(error);
			callbackResultado({ codigoError: 0, resultado: 'E' });
		}
	});
}

function handleGetToken(response, tipoDocumento, numeroDocumento, codigoProceso, callback, callbackResultado) {
	var error = response.substring(0, 5);
	if (error == BIOMATCH_CODIGO_OK) {
        var token = response.substring(5, response.length);
        handShakeHuellaDactilar(token, tipoDocumento, numeroDocumento, codigoProceso, callback, callbackResultado);
    } else {
        if (error == BIOMATCH_LICENCIA_NO_ENCONTRADA) {
            console.log('Licencia no encontrada!');
        }
        callbackResultado({ codigoError: error, resultado: 'E' });
    }
}

function handShakeHuellaDactilar(token, tipoDocumento, numeroDocumento, codigoProceso, callback, callbackResultado) {
	var url = gPath + '/handshakeBioMatch?token=' + token
		+ '&numeroDocumento=' + numeroDocumento
		+ '&codigoProceso=' + codigoProceso
		+ '&r=' + Math.random();
	url = encodeURI(url);
	var userOrClient = 1; //0=user, 1=client

	$.ajax({
		type: 'GET',
		url: url,
		success: function(response) {
			var encryptedToken = response.encryptedToken;
			var exData = response.extraData;

			verifyUser(encryptedToken, exData, userOrClient, tipoDocumento, numeroDocumento, callback, callbackResultado);
		},
		error: function(xhr, error) {
			console.log(error);
			callbackResultado({ codigoError: 0, resultado: 'E' });
		}
	});
}

function verifyUser(_etk, _exData, _userOrClient, tipoDocumento, numeroDocumento, callback, callbackResultado) {
	
	if(tipoDocumento=='DNI')
		tipoDocumento = '1';
	
    var params = "r=" + Math.random()
    	+ "&op=bioTxnBBVAT"
    	+ "&etk=" + _etk
    	+ "&userOrClient=" + _userOrClient
    	+ "&exData=" + _exData
    	+ "&tiDoc=" + tipoDocumento
    	+ "&nuDoc=" + numeroDocumento;

    var url = constants.BIOMATCH_URL_BASE + "?" + params;
	url = encodeURI(url);
	console.log('URL: ' + url);

	var isIE8Request = window.XDomainRequest;
	if (isIE8Request) {
		var invocation = new window.XDomainRequest();
		invocation.onload = function() {
			callback(invocation.responseText, callbackResultado);
		};
        invocation.open('GET', url, true);
        invocation.send();
	} else {
		$.ajax({
			type: 'GET',
			url: url,
			success: function(response) {
				callback(response, callbackResultado);
			},
			error: function(xhr, error) {
				console.log(error);
				callbackResultado({ codigoError: 0, resultado: 'E' });
			}
		});
	}
}

function callbackHuellaDactilarFiltroCliente(text, callbackResultado) {
	console.log('Text: ' + text);
    var error = text.substring(0, 5);

    if (error == BIOMATCH_CODIGO_OK) {
        console.log('Procesamiento local finalizado, espere un momento...');
        obtenerResultado(text, callbackResultado);
    } else {
    	callbackResultado({ codigoError: error, resultado: 'E' });
    }
}

function obtenerResultado(data, callbackResultado) {
	var url = gPath + "/biometria/resultado?data=" + data + "&r=" + Math.random();
	url = encodeURI(url);

	$.ajax({
		type: 'GET',
		url: url,
		success: function(response) {
			console.log(response);
			// response.resultado = 'A'; // TODO eliminar
			callbackResultado(response);
		},
		error: function(xhr, error) {
			console.log(error);
			callbackResultado({ codigoError: 'E' });
		}
	});
}

function callbackResultadoBiometriaFiltro(response) {
	debugger;
	console.log('callbackResultadoBiometriaFiltro');
	console.log(response);
	if (response.resultado == 'R') {
		if (CODIGO_ERROR_NO_HIT == response.codigoError) {
			objetoContenedor.codigoError = 'Rechazado por validaci\u00F3n de huella';
		}
		objetoContenedor.codigoResultado = COD_RESULTADO_RECHAZADO; 
	}
	else if (response.resultado == 'A') {
		objetoContenedor.codigoResultado = COD_RESULTADO_APROBADO;
	}
	else if (response.resultado == 'E') {
		objetoContenedor.codigoResultado = COD_RESULTADO_ERROR;
		objetoContenedor.codigoError = response.codigoError;
	}
	respuesta();
}

function busquedaContrato() {
	var tipoDocumento = (
			objetoContenedor==null?0:(  
			objetoContenedor.tipoDocumento==undefined?0:objetoContenedor.tipoDocumento
		)	
	);

	var numeroDocumento = (
		objetoContenedor==null?'':(
			objetoContenedor.numeroDocumento==undefined?'':objetoContenedor.numeroDocumento
		)	
	);

	var parametros = "?tipoDocumento=" + tipoDocumento
		+ "&numeroDocumento=" + numeroDocumento
		+ "&r=" + Math.random();
    $.ajax({
    	type: "GET",
	    url: gPath+"/busquedaContrato"+parametros,
	    data: {},
	    success: function(response){
	    	objetoContenedor = response;
	    	console.log(response);
	    	respuesta();
	    },
	    	error: function(e){
	    	console.log('Error: ' + e);
	    }
    });
}

function beginLoadingCargarDatosComplementarios() {
	$('#btnCerrarModalInformacion').hide();
	$('#btn_completar_info_form').button('loading');
	$('#completar_info_email').attr('disabled', true);
	$('#completar_info_envio').attr('disabled', true);
	$('#completar_info_profesion').attr('disabled', true);
	$('#completar_info_empresa').attr('disabled', true);
	$('#completar_info_cargo').attr('disabled', true);
	$('#completar_info_modalidad').attr('disabled', true);
	$('#modalInformacionCerrar').attr('disabled', true);
	modalGuardarDatosIsLoading = true;
}

function endLoadingCargarDatosComplementarios() {
	modalGuardarDatosIsLoading = false;
	$('#btnCerrarModalInformacion').show();
	$('#btn_completar_info_form').button('reset');
	$('#completar_info_email').attr('disabled', false);
	$('#completar_info_envio').attr('disabled', false);
	$('#completar_info_profesion').attr('disabled', false);
	$('#completar_info_empresa').attr('disabled', false);
	$('#completar_info_cargo').attr('disabled', false);
	$('#completar_info_modalidad').attr('disabled', false);
	$('#modalInformacionCerrar').attr('disabled', false);
	$('#completar_info_profesion').attr('disabled', false);
	
}

function findPurpose(purposeName){
	return $.grep(purposeObjects, function(n, i){ return n.purpose == purposeName; }); 
	};
function guardarDatosComplementarios(objetoPrueba){	
//	var idCliente = (
//		objetoContenedor==null?0:(  
//			objetoContenedor.idCliente==undefined?0:objetoContenedor.idCliente
//		)
//	);
	objetoPrueba.idContrato = (
			objetoContenedor==null?0:(  
					objetoContenedor.idContrato==undefined?'':objetoContenedor.idContrato
				)	
	);
	
	objetoPrueba.correo = (
		objetoContenedor==null?0:(  
			objetoContenedor.correo==undefined?0:objetoContenedor.correo
		)	
	);

	objetoPrueba.idTipoEnvio = (
		objetoContenedor==null?'':(
			objetoContenedor.idTipoEnvio==undefined?'':objetoContenedor.idTipoEnvio
		)	
	);
	
	objetoPrueba.idTipoOcupacion = (
		objetoContenedor==null?0:(  
			objetoContenedor.idTipoOcupacion==undefined?0:objetoContenedor.idTipoOcupacion
		)	
	);

	var centroTrabajo = (
		objetoContenedor==null?'':(
			objetoContenedor.centroTrabajo==undefined?'':objetoContenedor.centroTrabajo
		)	
	);
		
	var cargo = (
		objetoContenedor==null?0:(  
			objetoContenedor.cargo==undefined?0:objetoContenedor.cargo
		)	
	);

//	var idTipoModalidad = (
//		objetoContenedor==null?'':(
//			objetoContenedor.idTipoModalidad==undefined?'':objetoContenedor.idTipoModalidad
//		)	
//	);
	objetoPrueba.estadoContrato = (
		objetoContenedor==null?'':(
			objetoContenedor.estadoContrato==undefined?'':objetoContenedor.estadoContrato
		)	
	);
	
	objetoPrueba.nombreEnvio = (
			objetoContenedor==null?'':(
					objetoContenedor.nombreEnvio==undefined?'':objetoContenedor.nombreEnvio
				)	
			);
    $.ajax({
    	type: 'POST',
		dataType : 'json',
		contentType: 'application/json',
	    url: gPath + '/guardarDatosComplementarios',
	    data: JSON.stringify(objetoPrueba),
//	    	idCliente: idCliente,
//	    	codigoContrato: idContrato,
//	    	correo: correoCliente,
//	    	idTipoEnvio: idTipoEnvio,
//	    	estadoContrato: estadoUpdate,
//	    	tipoEnvio: tipoEnvioNombre,
//	    	clienteContrato: objetoContenedor,
	    	
//	    	idTipoOcupacion: idTipoOcupacion,
//	    	centroTrabajo: centroTrabajo,
//	    	cargo: cargo,
//	    	idTipoModalidad: idTipoModalidad
//	    }),
	    beforeSend: function() {
	    	beginLoadingCargarDatosComplementarios();
	    },
	    success: function(response) {
	    	objetoContenedor = response;
	    	endLoadingCargarDatosComplementarios();
	    	actualizarTabla(); // despues de actualizar objetoContenedor
	    },
	    error: function(e) {
	    	endLoadingCargarDatosComplementarios();
	    	console.log(e);
	    }
    });
}

function datosInicialesDeSimulacion (){
	var parametros =null;
    $.ajax({
    	type: "GET",
	    url: gPath+"/datosInicialesSimulacion?r=" + Math.random(),
		dataType: 'json',
	    type: 'GET',
	      
	    success: function(response){
	    	console.log("exito");
	    	objetoContenedor=response;
	    	cargarParametria();
	    	console.log(objetoContenedor);
	    	
	    },
	    	error: function(e){
	    	console.log('Error: ' + e);
	    }
    });
    
};

function cargarSimulacion(){
	var cargoFijoMaximo = (
			objetoContenedor==null?0:(  
				objetoContenedor.cargoFijoMaximo==undefined?0:objetoContenedor.cargoFijoMaximo
			)	
		);
	var tipoMoneda = (
			objetoContenedor==null?0:(  
				objetoContenedor.tipoMoneda==undefined?0:objetoContenedor.tipoMoneda
			)	
		);
	var valorEquipo = (
			objetoContenedor==null?0:(  
				objetoContenedor.valorEquipo==undefined?0:objetoContenedor.valorEquipo
			)	
		);
	var tarifaFinanciamiento = (
			objetoContenedor==null?0:(  
				objetoContenedor.tarifaFinanciamiento==undefined?0:objetoContenedor.tarifaFinanciamiento
			)	
		);
	var porcentajeCuotaInicial = (
			objetoContenedor==null?0:(  
				objetoContenedor.porcentajeCuotaInicial==undefined?0:objetoContenedor.porcentajeCuotaInicial
			)	
		);
	var cuotaInicial = (
			objetoContenedor==null?0:(  
				objetoContenedor.cuotaInicial==undefined?0:objetoContenedor.cuotaInicial
			)	
		);
	var saldoAFinanciar = (
			objetoContenedor==null?0:(  
				objetoContenedor.saldoAFinanciar==undefined?0:objetoContenedor.saldoAFinanciar
			)	
		);
	var fechaInicio = (
			objetoContenedor==null?0:(  
				objetoContenedor.fechaInicio==undefined?0:objetoContenedor.fechaInicio
			)	
		);
	var fechaVencimiento = (
			objetoContenedor==null?0:(  
				objetoContenedor.fechaVencimiento==undefined?0:objetoContenedor.fechaVencimiento
			)	
		);
	var metodoEnvio = (
			objetoContenedor==null?0:(  
				objetoContenedor.metodoEnvio==undefined?0:objetoContenedor.metodoEnvio
			)	
		);
	
	
	console.log(objetoContenedor);
		
    $.ajax({
    	type: "GET",
	    url: gPath+"/simulacionDelPago1",
	    data: {
	    	 cargoFijoMaximo         : cargoFijoMaximo,
	    	 tipoMoneda   		     : tipoMoneda,
	    	 valorEquipo  			 : valorEquipo,
	    	 tarifaFinanciamiento 	 : tarifaFinanciamiento,
	    	 porcentajeCuotaInicial  : porcentajeCuotaInicial,
	    	 cuotaInicial 			 : cuotaInicial,
	    	 saldoAFinanciar 		 : saldoAFinanciar,
			 metodoEnvio 			 : metodoEnvio
	    },
		dataType: 'json',
	    type: 'GET',
	    success: function(response){
	    	console.log("exito");
	    	objetoContenedor=response;
	    	respuesta();
	    	console.log(objetoContenedor);
	    	
	    },
	    	error: function(e){
	    		console.log("error");
	    		console.log(e);
	    	console.log('Error: ' + e);
	    }
    });
	
};

$(function() {
	$(document).ajaxError(function(event, request, settings) {
		if (request.status == 403) {
			window.location = gPath;
		}
	});
	if (jQuery.validator) {
		jQuery.validator.addMethod("correoElectronico", function(value, element) {
			return this.optional(element) || /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value);
		}, "Ingrese una direcci\u00F3n de correo v\u00E1lida.");
	}
});

function printDivById(printpage) {
	var printContents = document.getElementById(printpage).innerHTML;
	var originalContents = document.body.innerHTML;
	document.body.innerHTML = printContents;
	window.print();
	document.body.innerHTML = originalContents;
}