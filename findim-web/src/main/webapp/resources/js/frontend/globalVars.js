if (!window.console) {
     window.console = {};
     window.console.log = function(message) {};
}

var RESULTADO_FIRMA_OK = 0,
	RESULTADO_FIRMA_ERROR = 1;

var PROCESO_BIOMETRIA_FILTRO = 1,
	PROCESO_BIOMETRIA_FIRMA = 2;

var gPath = null;
var 
    /* Objeto que contiene las respuestas de WS*/
	objetoContenedor = null;
	console.log(" Objeto Reset = " + objetoContenedor); 

var objetoContenedor = new Object();
	console.log(" Objeto creado = " + objetoContenedor);

var navStatus = "",
    cabecera = "",
    cabeceraTitulo="",
    formulario ="",
    preloader = "",
    alerta = "",
    contenido ="",

    /*documentos para busqueda y filtro*/
    tipoDocumento ="",
    numDocumento ="",
    /* Contratos */
    datosCliente="",
    codigoContrato="",
    nombreCompleto="",
    idCliente="",
    idContrato="",
    listaContratos="",
   
    /* simulacion */    
    simulacion_moneda = "",
    simulacionValorEquipo = "",
    simulacion_codigo_tarifa = "",
    simulacion_cuota_inicial_porcentaje = "",
    simulacion_cuota_inicial = "",
    simulacion_saldo_a_financiar = "",
    simulacion_fecha_inicio = "",
    simulacion_fecha_vencimiento = "",
    simulacion_metodo_envio="";
    
/* Common functions */
function parseBoolean(boolInput) {
	if (typeof boolInput == 'boolean') {
		return boolInput;
	}
	return boolInput == 'true';
}
function htmlEncode(value) {
	return $('<div/>').text(value).html();
}
function htmlDecode(value) {
	return $('<div/>').html(value).text();
}