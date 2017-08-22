// Definir variables usando globales (ver globalVars.js) 
//variables Globales
var contratoActualizar;
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
	tablaContenido = $("#tablaContenido"),
	datosCliente= $("#datos-cliente"),
	tienePDF = "",
	alertaFormNoCompletado = $("#alertaFormNoCompletado"),
	loadPDF ="",
	estadoContratoUpdate="",
	modalGuardarDatosIsLoading = false;

//variables locales BTNS
var volverForm = $("#volverForm"),
    volverContenido = $("#volverContenido"),
    volverAlerta = $("#volverAlerta"),
    rutaDinamicaPDF = "";

// Esconde todos los elementos para ver que muestra
formulario.hide();
preloader.hide();
alerta.hide();
contenido.hide();

function mostrarMensajeAlerta() {
	alertaFormNoCompletado.show();
}

function actualizarContenidos(){
	
    if (objetoContenedor.tipoError == 1) {
        tablaContenido.hide();
        datosCliente.text("No Existe Cliente");
        
    } else if ( objetoContenedor.tipoError == 2 ) {
        tablaContenido.hide();
        datosCliente.text("Cliente no tiene contratos");
    } else if ( objetoContenedor.tipoError == 3 ){
        tablaContenido.hide();
        datosCliente.text("Cliente rechazado");  
    } else if ( objetoContenedor.tipoError != undefined ){
        tablaContenido.hide();
        var mensajeError = 'Error del Sistema';
        if (objetoContenedor.descError) {
        	mensajeError += ': ' + objetoContenedor.descError;
        }
        datosCliente.text(mensajeError);
        
    }
        
    if (objetoContenedor.tipoError == undefined) {
        tablaContenido.show();

        // Busqueda Contrato   
        // codigoContrato= objetoContenedor.codigoContrato,
        var rutaServicioRest= objetoContenedor.rutaServicioRest,
        	rutaPdf= objetoContenedor.rutaPdf,
            nombreCompleto= objetoContenedor.nombreCompleto,
            idCliente= objetoContenedor.idCliente,
            idContrato= objetoContenedor.idContrato,
            listaContrato = objetoContenedor.listaContrato,
            totalArrayTabla = objetoContenedor.listaContrato ? objetoContenedor.listaContrato.length : 0,
            tablaContenidoBody = $("#tablaContenidoBody");

        datosCliente.text(objetoContenedor.nombreCompleto);
        tablaContenidoBody.children().remove();
            
        for(var i = 0; i < totalArrayTabla ; i++) {
            var btnHolderContrato = "",
            	destinoModal ="",
            	textoBoton ="";

            console.log(listaContrato[i].tipoEnvio);
            
            var esModalFirma = false;
            
            var estado="";
            var tipoEnvio="";
            if(listaContrato[i].tipoEnvio== "NONE"){
            	tipoEnvio="NINGUNO";
            	estado="PENDIENTE";
           }
           	if(listaContrato[i].tipoEnvio == "DIGITAL"){
           		tipoEnvio="VIRTUAL";
           		estado="APROBADO";
           	}
        	if(listaContrato[i].tipoEnvio == "PHYSICAL" ){
        		tipoEnvio="FISICO";
        		estado="APROBADO";
           	}
            switch ($.trim(listaContrato[i].tipoEnvio)){
	        	case 'NONE': //ex PENDIENTE
	        		destinoModal = "#modalInformacion";
	        		textoBoton = "Completar datos";
	        		break;
	        	case 'DIGITAL':
	        		esModalFirma = true;
	        		destinoModal = "#modalContratosFirma";
	        		textoBoton = "Firmar Documentos";
	        		break;
	        	case 'PHYSICAL':
	        		esModalFirma = true;
	        		destinoModal = "#modalContratosFirma";
	        		textoBoton = "Firmar Documentos";
	        		break;
	        	case 'DESEMBOLSADO':
	        		destinoModal = "#anulacionContrato";
	        		textoBoton = "Anulación";
	        		break;
	        	case 'VER':
	        		destinoModal = "#modalVer";
	        		textoBoton = "Ver documentos";					
	        		break;
	        	case 'ANULAR':
	        		destinoModal = "#modalExtorno";
	        		textoBoton = "Anular";					
	        		break;
	        	default:
	        		console.log("default");
	        		break;
	        }
            
            if(listaContrato[i].estadoContrato=="PENDING_COMPLETION_DATA"){
            	destinoModal = "#modalInformacion";
        		textoBoton = "Completar datos";
        	}

            alertaFormNoCompletado.hide();

            var tablaContenidoBodyHTML =
	        	"<tr>" +
	            "<td>" + htmlEncode(listaContrato[i].fechaCreacion) + "</td>" +
	            "<td>" + htmlEncode(listaContrato[i].tipoMoneda) + "</td>" +
	            "<td>" + htmlEncode(listaContrato[i].importeBien) + "</td>" +
	            "<td>" + htmlEncode(listaContrato[i].importeInicial) + "</td>" +
	            "<td>" + htmlEncode(listaContrato[i].importePrestamo) +  "</td>" +
	            "<td>" + htmlEncode(listaContrato[i].codigoTarifa) + "</td>" +
	            "<td>" + htmlEncode(listaContrato[i].numeroCuotas) + "</td>" +
	            "<td>" + htmlEncode(listaContrato[i].tasaFinanciamiento) + "%" + "</td>" +
	            "<td>" + htmlEncode(listaContrato[i].descripcionTasaSeguro + listaContrato[i].valorTasaSeguro) + "%" + "</td>" +
	            "<td>" + htmlEncode(tipoEnvio) + "</td>" +
	            "<td>" + htmlEncode(listaContrato[i].fechaDesembolso) + "</td>" +
	            "<td>" + estado + "<br>";

        	var documentoFirmado = listaContrato[i].firmaContrato == 1;

            if (!documentoFirmado) {
            	tablaContenidoBodyHTML += "<div class='btn-group '>" +
            		"<a data-toggle='modal'" +
            		" data-target='" + htmlEncode(destinoModal) + "'" +
            		" data-valor='" + htmlEncode(i) + "'" +
            		" data-contrato='" + htmlEncode(listaContrato[i].codigoContrato) + "'" +
            		(esModalFirma ? " data-pdf='" + htmlEncode(listaContrato[i].nombreArchivo) + "'" : '') +
            		" data-cod-auxiliar='" + htmlEncode(listaContrato[i].codigoAuxiliar) + "'" +
            		" class='btn btn-primary btn-xs butStatusContrato'> " + htmlEncode(textoBoton) + " </a>" +
            		"</div>";
            } else if (listaContrato[i].estadoContrato == 'DESEMBOLSADO') {
            	tablaContenidoBodyHTML += "<div class='btn-group '>" +
            	" Doc. Firmados" +
            	"<a data-toggle='modal'" +
        		" data-target='" + htmlEncode(destinoModal) + "'" +
        		" data-valor='" + i + "'" +
        		" data-contrato='" + htmlEncode(listaContrato[i].codigoContrato) + "'" +
        		(esModalFirma ? " data-pdf='" + htmlEncode(listaContrato[i].nombreArchivo) + "'" : '') +
        		" data-cod-auxiliar='" + htmlEncode(listaContrato[i].codigoAuxiliar) + "'" +
        		// Boton anular
        		" class='btn btn-primary btn-xs butStatusContrato anular'> " + htmlEncode(textoBoton) + " </a>" +
            	"</div>";
        	} else {
            	tablaContenidoBodyHTML += "<div class='btn-group '>" +
            	" Doc. Firmados" +
//            	"<a data-toggle='modal'" +
//        		" data-target='" + htmlEncode(destinoModal) + "'" +
//        		" data-valor='" + i + "'" +
//        		" data-contrato='" + htmlEncode(listaContrato[i].codigoContrato) + "'" +
//        		(esModalFirma ? " data-pdf='" + htmlEncode(listaContrato[i].nombreArchivo) + "'" : '') +
//        		" data-cod-auxiliar='" + htmlEncode(listaContrato[i].codigoAuxiliar) + "'" +
//        		" class='btn btn-primary btn-xs butStatusContrato'> " + htmlEncode(textoBoton) + " </a>" +
            	"</div>";
            }

            tablaContenidoBodyHTML += "</td>" + "</tr>";
            
            tablaContenidoBody.append(tablaContenidoBodyHTML);
        }
    }
    
    // PROGRAMA LOS BOTONES
    $(".butStatusContrato").click(function() {
    	console.log( $(this).attr("data-contrato"));
    	actualizarModalContratos($(this).attr("data-valor"));

    	if ($(this).attr("data-pdf") != null) {
			cargarPDF($(this).attr("data-pdf"), $(this).data('cod-auxiliar'), $(this).data('contrato'));
    	}
    });
} 

$(function() {
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
    function procesarFormulario (){
    	
        objetoContenedor.numeroDocumento = $( "#numDocumento" ).val();
        objetoContenedor.tipoDocumento = $( "#tipoDocumento" ).val();
        
        console.log(objetoContenedor);        
        cambiarVentana(1);
        busquedaContrato();
    }

    respuesta();

    ///MODALS ///////////////////////////////////////////////////////////////////////////
    $('#btnFirmarDocumento').click(function() {
    	beginLoadingFirma();
    	if (constants.BIOMATCH_ACTIVO) {
    		verificarHuellaDactilar(objetoContenedor.tipoDocumento,
    				objetoContenedor.numeroDocumento,
    				PROCESO_BIOMETRIA_FIRMA,
    				callbackHuellaDactilarFirmarDocumento,
    				callbackResultadoBiometriaFirma);
    	} else {
    		firmarContrato();
    	}
    });
    
    $('#modalAlertaFirma').on('hidden.bs.modal', function () {
    	var refreshOnClose = parseBoolean($('#modalAlertaFirma #refreshOnClose').val());
    	if (refreshOnClose) {
    		$('#modalContratosFirma').modal('hide');
    		actualizarContenidos();
    	} else {
    		$('#modalContratosFirma').show();
    	}
    	endLoadingFirma();
    });
    
    $('#modalInformacion').on('hide.bs.modal', function (e) {
    	if (modalGuardarDatosIsLoading) {
    		e.preventDefault();
    	}
    });

    $('#contenido').on('click', '.anular', function() {
    	var nombreCliente = objetoContenedor.nombreCompleto;
    	var dniCliente = objetoContenedor.numeroDocumento;
    	var numeroContrato = $(this).data('cod-auxiliar');
    	var cdContrato = $(this).data('contrato');
    	console.log('cdContrato: ' + cdContrato);

    	$('#nombreCliente, #nombreCliente2').text(nombreCliente);
    	$('#dniCliente, #dniCliente2').text(dniCliente);
    	$('#contratoCliente').text(numeroContrato);
    	$('#cdContrato').val(cdContrato);
    });

    $('#anulacionContrato #btnPrintDocumento').click(function() {
    	$('#formAnulacionContrato').remove();
		var mapForm = document.createElement('form');
	    mapForm.target = 'Map';
	    mapForm.method = 'POST';
	    mapForm.id = 'formAnulacionContrato';
	    mapForm.action = gPath + '/impresionAnulacion';

	    var inputNombreCliente = document.createElement('input');
	    inputNombreCliente.type = 'text';
	    inputNombreCliente.name = 'nombreCliente';
	    inputNombreCliente.value = objetoContenedor.nombreCompleto;
	    inputNombreCliente.setAttribute('style','display:none');
	    mapForm.appendChild(inputNombreCliente);
	    
	    var inputDniCliente = document.createElement('input');
	    inputDniCliente.type = 'text';
	    inputDniCliente.name = 'dniCliente';
	    inputDniCliente.value = objetoContenedor.numeroDocumento;
	    inputDniCliente.setAttribute('style','display:none');
	    mapForm.appendChild(inputDniCliente);

	    var inputNumeroContrato = document.createElement('input');
	    inputNumeroContrato.type = 'text';
	    inputNumeroContrato.name = 'numeroContrato';
	    inputNumeroContrato.value = $('#contratoCliente').text();
	    inputNumeroContrato.setAttribute('style','display:none');
	    mapForm.appendChild(inputNumeroContrato);

	    var inputCdContrato = document.createElement('input');
	    inputCdContrato.type = 'text';
	    inputCdContrato.name = 'cdContrato';
	    inputCdContrato.value = $('#cdContrato').val();
	    console.log('Modal - cdContrato: ' + $('#cdContrato').val());
	    inputCdContrato.setAttribute('style','display:none');
	    mapForm.appendChild(inputCdContrato);

	    document.body.appendChild(mapForm);

	    map = window.open(gPath + '/impresionAnulacion', 'Map', 'status=0,title=0,height=0,width=0,scrollbars=1');

		if (map) {
		    mapForm.submit();
		} else {
		    alert('Debe permitir popups para esta pagina.');
		}
    });

});

function callbackHuellaDactilarFirmarDocumento(text, callbackResultado) {
	console.log('Text: ' + text);
    var error = text.substring(0, 5);

    if (error == BIOMATCH_CODIGO_OK) {
        console.log('Procesamiento local finalizado, espere un momento...');
        obtenerResultado(text, callbackResultado);
    } else {
    	callbackResultado({ codigoError: error, resultado: 'E' });
    }
}

function callbackResultadoBiometriaFirma(response) {
	console.log('** callbackResultadoBiometriaFirma **');
	console.log(response.resultado);
	if (response.resultado == 'R') {
		abrirModalAlertaFirma('No se pudo validar la huella del cliente.', false);
	}
	else if (response.resultado == 'A') {
		firmarContrato();
	}
	else if (response.resultado == 'E') {
		
		firmarContrato();
		//TODO: abrirModalAlertaFirma('Error <' + response.codigoError + '>', false);
	}
}

function beginLoadingFirma() {
	$('#btnCerrarModalFirma').hide();
	$('#btnFirmarDocumento').button('loading');
	$('#modalContratoCerrar').attr('disabled', true);
}

function endLoadingFirma() {
	$('#btnCerrarModalFirma').show();
	$('#btnFirmarDocumento').button('reset');
	$('#modalContratoCerrar').attr('disabled', false);
}

function firmarContrato() {
	beginLoadingFirma();

    var codAuxiliar = $('#btnFirmarDocumento').data('cod-auxiliar');
    var codContrato = $('#btnFirmarDocumento').data('contrato');

    var contrato = {
    	codigoAuxiliar: codAuxiliar,
    	codigoContrato: codContrato,
    	clienteContrato: {
    		numeroDocumento: objetoContenedor.numeroDocumento,
    		tipoDocumento: objetoContenedor.tipoDocumento,
    		idCliente: objetoContenedor.idCliente
    	}
    };

    var url = gPath + '/firmarContrato';
    $.ajax({
		type: 'POST',
		contentType:'application/json',
		url: url,
		data: JSON.stringify(contrato),
		success: function(response) {
			console.log(response);
			if (response.resultadoFirma == RESULTADO_FIRMA_OK) {
				objetoContenedor = response;
				abrirModalAlertaFirma('Documentos firmados con \u00E9xito', true);
			} else {
				abrirModalAlertaFirma('Se produjo un error al firmar los documentos', false);
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			abrirModalAlertaFirma('Se produjo un error al firmar los documentos', false);
			console.log(errorThrown);
		}
	});
}

function abrirModalAlertaFirma(mensaje, refreshOnClose) {
	$('#modalContratosFirma').hide();
	$('#modalAlertaFirma').modal('show');
	$('#modalAlertaFirma #mensaje').text(mensaje);
	$('#modalAlertaFirma #refreshOnClose').val(refreshOnClose);
}

////////////////////////////////////////////////////////////////////////////////////////
//Modal Completar informacion
///////////////////////////////////////////////////////////////////////////////////////
var contratosCompletarInfoForm = $("#form-completar-informacion"),
  completarInfoNombreCliente = $("#CompletarNombreCliente"),
  completarInfoEmail = $("#completar_info_email"),
  completarInfoCodCliente =$("#completar_info_cod_cliente"),
  completarInfoCodContrato = $("#completar_info_cod_contrato"),
  completarInfoEnvio= $("#completar_info_envio"),
  completarInfoProfesion = $("#completar_info_profesion"),
  completarInfoEmpresa= $("#completar_info_empresa"),
  completarInfoCargo =$("#completar_info_cargo"),
  completarInfoModalidad= $("#completar_info_modalidad"),
  alertaPDF = $("#alert_contratos_resultado"),
  completarInfoDireccion= $("#completar_info_direccion"),
  completarInfoIndicador= $("#completar_info_indicador"),
  completarInfoDirNom= $("#completar_info_dir_nom"),
  completarInfoDirNum= $("#completar_info_dir_num"),  
  conteoUpdate = 0;

contratosCompletarInfoForm.validate({
  rules: {
      completar_info_email: {
          required: true,
          correoElectronico: true
      }
  },

  messages: {
      completar_info_email: {
          email: "Ingrese un email valido",

      }
  },

  submitHandler: function(form) {procesarCompletarInformacion();}
});

function procesarCompletarInformacion (e){
  contratoActualizar.idTipoEnvio = completarInfoEnvio.val();
  objetoContenedor.idCliente= completarInfoCodCliente.val();
  objetoContenedor.idContrato=  completarInfoCodContrato.val();
  objetoContenedor.correo = completarInfoEmail.val();    
  objetoContenedor.idTipoEnvio = completarInfoEnvio.val();

  objetoContenedor.centroTrabajo = completarInfoEmpresa.val();
  objetoContenedor.cargo= completarInfoCargo.val();
  objetoContenedor.idTipoModalidad = completarInfoModalidad.val();
  objetoContenedor.estadoContrato = estadoContratoUpdate;
  objetoContenedor.nombreEnvio = completarInfoEnvio.find('option:selected').text();
  
//  objetoContenedor.lstGrupoGeografico[0].id =completarInfoIndicador.find('option:selected').val();
//  objetoContenedor.lstGrupoGeografico[0].nombre =completarInfoDirNom.val();
//  
//  objetoContenedor.lstGrupoGeografico[1].id ="exterior_number";
//  objetoContenedor.lstGrupoGeografico[1].nombre =completarInfoDirNum.val();
  
  if(!objetoContenedor.esCliente){
	  objetoContenedor.idTipoOcupacion = completarInfoProfesion.val();
  }
  guardarDatosComplementarios(contratoActualizar);    
}

function actualizarModalContratos(i){
  contratoActualizar=objetoContenedor.listaContrato[i];
  completarInfoNombreCliente.text(objetoContenedor.nombreCompleto);
  completarInfoCodContrato.val(objetoContenedor.listaContrato[i].codigoContrato);
  completarInfoCodCliente.val(objetoContenedor.idCliente);
  completarInfoEmail.val($.trim(objetoContenedor.listaContrato[i].correo));
  completarInfoDireccion.val(objetoContenedor.direccion);
  estadoContratoUpdate = objetoContenedor.listaContrato[i].estadoContrato;
//  completarInfoDirNum.val(objetoContenedor.lstDireccionBean[0].numeroExterno);
  completarInfoEmpresa.val(objetoContenedor.centroTrabajo);
  
  if(objetoContenedor.esCliente){
		$('#completar_info_email').attr('disabled', false);
		$('#completar_info_envio').attr('disabled', false);
		$('#completar_info_profesion').attr('disabled', true);
		$('#completar_info_empresa').attr('disabled', true);
		$('#modalInformacionCerrar').attr('disabled', false);
		$('#completar_info_profesion').attr('disabled', true);
		$('#div_direccion_ref').hide();
		$('#div_direccion_via').hide();
//	  $('#completar_info_cargo').prop('readonly', true);
	  
	  $('#completar_info_empresa').attr('disabled', true);
	  $('#completar_info_profesion').attr('disabled', true);
	  
  }
  //Parametria
  if(conteoUpdate == 0) {
	  conteoUpdate = 1;
	  if(objetoContenedor.idTipoEnvio !=null){
		  completarInfoEnvio.find('option').remove();
	      for(j=0;j<objetoContenedor.listaTipoEnvio.length; j++){
	          completarInfoEnvio.append('<option value=' + htmlEncode(objetoContenedor.listaTipoEnvio[j].cd_paramdetalle) + '>' + htmlEncode(objetoContenedor.listaTipoEnvio[j].nb_paramdetalle) + '</option>');
	      }
	
	  } else {
//	      completarInfoEnvio.append('<option value=' + '0' + '>' + 'Lista Vacia' + '</option>');
	  }
	 
	  
	  for (var i = 0; i < objetoContenedor.listaComprobantePago.length ; i++){
			$("#completar_info_envio").append(
				"<option value='" + objetoContenedor.listaComprobantePago[i].nb_valorparamdeta + "'>" + objetoContenedor.listaComprobantePago[i].nb_paramdetalle+ "</option>"
			);
		}
	  
	  
//	 if(objetoContenedor.idTipoOcupacion !=null){
		 completarInfoProfesion.find('option:not(:first)').remove();
	      for(k=0;k<objetoContenedor.listaCatalogo.length; k++){
	    	  if(objetoContenedor.idTipoOcupacion == objetoContenedor.listaCatalogo[k].idCatalogo){
	    		  completarInfoProfesion.append('<option selected value id="'+ objetoContenedor.listaCatalogo[k].nbCatalogo +'" value="' + objetoContenedor.listaCatalogo[k].idCatalogo  +'">' + htmlEncode(objetoContenedor.listaCatalogo[k].stValue) + '</option>');
	    	  }else{
	    		  completarInfoProfesion.append('<option id="'+ objetoContenedor.listaCatalogo[k].nbCatalogo +'" value="' + objetoContenedor.listaCatalogo[k].idCatalogo  +'">' + htmlEncode(objetoContenedor.listaCatalogo[k].stValue) + '</option>');
	    	  }
	    	  
	      }
//	 }else {
//		 completarInfoProfesion.append('<option value=' + '0' + '>' + 'Lista Vacia' + '</option>');
//	 }
	      
	      completarInfoIndicador.find('option:not(:first)').remove();
	      for(k=0;k<objetoContenedor.listaCatalogoDireccion.length; k++){
	    	  completarInfoIndicador.append('<option value="'+ objetoContenedor.listaCatalogoDireccion[k].idCatalogo +'">' + htmlEncode(objetoContenedor.listaCatalogoDireccion[k].stValue) + '</option>');
	      }
	 
	 if(objetoContenedor.idTipoModalidad !=null){
		 completarInfoModalidad.find('option:not(:first)').remove();
	      for(k=0;k<objetoContenedor.listaTipoModalidad.length; k++){
	    	  completarInfoModalidad.append('<option value=' + htmlEncode(objetoContenedor.listaTipoModalidad[k].cd_paramdetalle) + '>' + htmlEncode(objetoContenedor.listaTipoModalidad[k].nb_paramdetalle) + '</option>');
	      }
	 }else {
		 completarInfoModalidad.append('<option value=' + '0' + '>' + 'Lista Vacia' + '</option>');
	 }	
  }
  
  /******************************************/
  var correoCliente = (
	objetoContenedor==null?'':(  
		objetoContenedor.listaContrato[i].correo==undefined?'':(
				objetoContenedor.listaContrato[i].correo=null?'': $.trim(objetoContenedor.listaContrato[i].correo)
		 )
	  )	
   );
	/****************************************/
	if($.trim(correoCliente) == ''){
		$('#completar_info_envio option').eq(1).prop('selected', true);
	}
	
  /*****************************************/
  
}

function cargarPDF(rutaCargaPDF, codAuxiliar, codContrato) {
	var linkRutaPDF = $("#pdfRoute");

	$('#btnFirmarDocumento').data('cod-auxiliar', codAuxiliar);
	$('#btnFirmarDocumento').data('contrato', codContrato);

	var path;
	if (isIE8) {
		path = gPath + '/contrato/pdf/' + $.trim(rutaCargaPDF) + '/view#toolbar=0&view=FitBV&statusbar=0&messages=0&navpanes=0'; // if IE8
	} else {
		path = gPath + '/resources/js/pdfviewer/web/viewer.html?file=' + gPath + '/contrato/pdf/' + $.trim(rutaCargaPDF) + '/view#zoom=page-width';
	}

	linkRutaPDF.attr('src', path);

	alertaPDF.hide();
	linkRutaPDF.show();
}

function actualizarTabla() {
	  $("#tablaContenidoBody").children().remove();
	  $('#modalInformacion').modal('toggle');
	  actualizarContenidos();
}
