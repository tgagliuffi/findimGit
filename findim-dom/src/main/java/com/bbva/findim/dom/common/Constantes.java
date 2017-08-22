package com.bbva.findim.dom.common;

public class Constantes {

	public static final String PREFIJO_ARCHIVO_ESTADO_CUENTA_CUOTA = "T";
	public static final String PREFIJO_ARCHIVO_ESTADO_CUENTA_CONTRATO = "M";
	
	public static final String PREFIJO_ARCHIVO_COMPROBANTE = "C";
	
	public static final int SEGUROINDIVIDUALMAYORA70 = 2;
	public static final int TIPOTASA = 1;
	
	public static final String EXTENSION_PDF    = ".pdf";
	public static final String EXTENSION_TXT    = ".txt";
	public static final String DOCUMENTO_T      = "T";
	public static final String DOCUMENTO_CRO    = "CRO";
	public static final String DOCUMENTO_PAG    = "PAG";
	public static final String DOCUMENTO_ACE    = "ACE";
	public static final String DOCUMENTO_HRI    = "HRI";
	public static final String DOCUMENTO_CON    = "CON";
	public static final String DOCUMENTO_CONDET    = "CONDET";
	public static final String DOCUMENTO_SOL    = "SOL";
	public static final String DOCUMENTO_SDE    = "SDE";	
	public static final String PREFIJO_TEMPORAL = "PRE_";
	public static final String PREFIJO_BCCF     = "BCCF";
	
	public static final String FORMATO_FECHA  = "yyyyMMdd";
	public static final String FORMATO_FECHA_SIMPLE  = "dd/MM/yyyy";
	public static final String FORMATO_FECHA_YYYY_MM_DD  = "yyyy-mm-dd";
	public static final String FORMATO_FECHA_DD_MM_YYYY  = "dd-mm-yyyy";
	
	public static final String UBIGEO_DEPARTAMENTO  = "DEPARTMEN";
	public static final String UBIGEO_PROVINCIA  = "PROVINCE";
	public static final String UBIGEO_DISTRITO  = "DISTRICT";
	
	public static final Integer PARAM_ACTIVOS = 1;
	
	public static final Integer PARAM_DATOS_TIPOS_DOCUMENTOS = 1;
	
	public static final String DOCUMENTO_PREPAG = "PREPAG";
	
	public static final Integer C_CUOTA_PREPAGO =999;
	
	public enum Error {
		DataAccessException(102), InvalidResultSetAccessException(103), ParseException(104), Exception(
				105), SQLException(106), HttpException(404);

		private Integer inError;

		Error(Integer inError) {
			this.inError = inError;
		}

		public Integer getError() {
			return inError;
		}
	}

	public enum TipoRespuestaWS {
		Satisfactorio(1), Error(0);

		private Integer tipoRespuesta;

		TipoRespuestaWS(Integer tipoRespuesta) {
			this.tipoRespuesta = tipoRespuesta;
		}

		public Integer getTipoRespuestaWS() {
			return tipoRespuesta;
		}
	}

	public enum TipoServicio {
		WS_ALTA_CONTRATO(1), WS_PARAMETRIA(2), WS_DOCUMENTO(3);

		private Integer inTipoServicio;

		TipoServicio(Integer inTipoServicio) {
			this.inTipoServicio = inTipoServicio;
		}

		public Integer getTipoServicio() {
			return inTipoServicio;
		}
	}

	public enum RespuestaWS {
		Aprobado(1), Rechazado(2), ErrorConsultaWS(3);

		private Integer inRespuesta;

		RespuestaWS(Integer inRespuesta) {
			this.inRespuesta = inRespuesta;
		}

		public Integer getRespuesta() {
			return inRespuesta;
		}
	}

	public enum RespuestaFiltroWS {
		Aprobado(1), Rechazado(2), ErrorConsultaWS(3);

		private Integer respuesta;

		RespuestaFiltroWS(Integer respuesta) {
			this.respuesta = respuesta;
		}

		public Integer getRespuestaFiltroWS() {
			return respuesta;
		}
	}

	public enum RespuestaBusquedaContratoWS {
		NoExisteCliente(1), ClienteSinContrato(2), 
		ClienteRechazado(3), ErrorBusqueda(4);
		private Integer respuesta;

		RespuestaBusquedaContratoWS(Integer respuesta) {
			this.respuesta = respuesta;
		}

		public Integer getRespuestaBusquedaContratoWS() {
			return respuesta;
		}
	}

	public enum RespuestaContratoCierre {
		CierreCorrecto(1), CierreIncorrecto(0);

		private Integer inResultado;

		RespuestaContratoCierre(Integer inResultado) {
			this.inResultado = inResultado;
		}

		public Integer getInResultado() {
			return inResultado;
		}
	}

	public enum TipoCuota {
		CuotaSimple(1), CuotaDobleJulio(2), CuotaDobleDiciembre(3), CuotaDobleJulioDiciembre(4);

		private Integer tipoCuota;

		TipoCuota(Integer inResultado) {
			this.tipoCuota = inResultado;
		}

		public Integer getTipoCuota() {
			return tipoCuota;
		}
	}

	public enum TipoCalculoCronograma {
		MANTENER_CUOTA(1), MANTENEER_PLAZO(2);

		private Integer tipoCalculoCronograma;

		private TipoCalculoCronograma(Integer inResultado) {
			this.tipoCalculoCronograma = inResultado;
		}

		public Integer getTipoCalculoCronograma() {
			return tipoCalculoCronograma;
		}
	}
	
	public enum NumeroMes {
		MesJulio(7), MesDiciembre(12);

		private Integer mes;

		NumeroMes(Integer inResultado) {
			this.mes = inResultado;
		}

		public Integer getMes() {
			return mes;
		}
	}
	public enum PesoCuota {
		CuotaSimple(1), CuotaDoble(2);

		private Integer peso;

		PesoCuota(Integer inResultado) {
			this.peso = inResultado;
		}

		public Integer getPeso() {
			return peso;
		}
	}	
	public enum EstadoCuota {
		Vigente(1), Anulada(0);

		private Integer estado;

		EstadoCuota(Integer inResultado) {
			this.estado = inResultado;
		}
		public Integer getEstado() {
			return estado;
		}
	}
	
	public enum NotificacionAlta {
		FechaNormal(0),FechaDiferida(1);

		private Integer estado;

		NotificacionAlta(Integer estado) {
			this.estado = estado;
		}
		public Integer getEstado() {
			return estado;
		}
	}	

	public enum TipoEvalua {
		Aprobado(0),
		EvaluaCastigo(1),
		EvaluaClasificacion(2),
		EvaluaInelegible(3),
		// EvaluaPEP(4),
		EvaluaBioMetria(5);

		private Integer tipoEvalua;

		TipoEvalua(Integer inResultado) {
			this.tipoEvalua = inResultado;
		}

		public Integer getTipoEvalua() {
			return tipoEvalua;
		}
	}
	
	public enum ValidaRecaudacion {
		ErrorTotalRegistros(1),ErrorPagos(2),
		ErrorDeposito(3), ErrorMora(4);

		private Integer estado;

		ValidaRecaudacion(Integer estado) {
			this.estado = estado;
		}
		public Integer getEstadoValidacion() {
			return estado;
		}
	}	
	
	public static final  String PREFIJO_ARCHIVO_HOJA_RESUMEN_INFORMATIVO = "HRI";
	public static final  String PREFIJO_ARCHIVO_CONTRATO = "CON";
	public static final  String PREFIJO_ARCHIVO_CRONOGRAMA = "CRO";
	public static final  String PREFIJO_ARCHIVO_SEDE = "SDE";
	public static final  String PREFIJO_ARCHIVO_SOL = "SOL";
	public static final  String PREFIJO_ARCHIVO_PAG = "PAG";
	
	public static final  Integer GENERAR_DOCUMENTO_HRI = 0;
	public static final  Integer GENERAR_DOCUMENTO_CON = 1;
	public static final  Integer GENERAR_DOCUMENTO_PRE = 2;
	public static final  Integer GENERAR_DOCUMENTO_CRO = 3;
	public static final  Integer GENERAR_DOCUMENTO_PAG = 4;
	public static final  Integer GENERAR_DOCUMENTO_SEG = 5;
	
	public static final  Boolean RUTA_FIRMADO = true;

	/* Parametros de configuracion de Emails */
	public static final Integer COD_PARAM_TEXTO_CORREO_PADRE = 48;

	public static final Integer COD_PARAM_TEXTO_CORREO_DESEMBOLSO = 1;
	public static final Integer COD_PARAM_ASUNTO_CORREO_DESEMBOLSO = 3;
	public static final Integer COD_PARAM_REMITENTE_CORREO_DESEMBOLSO = 5;
	public static final Integer COD_PARAM_BCC_CORREO_DESEMBOLSO = 28;

	public static final Integer COD_PARAM_ASUNTO_CORREO_RECAUDACION = 4;
	public static final Integer COD_PARAM_TEXTO_CORREO_RECAUDACION = 2;
	public static final Integer COD_PARAM_REMITENTE_CORREO_RECAUDACION = 6;
	public static final Integer COD_PARAM_BCC_CORREO_RECAUDACION = 29;

	public static final Integer COD_PARAM_ASUNTO_CORREO_FACTURACION = 7;
	public static final Integer COD_PARAM_TEXTO_CORREO_FACTURACION = 8;
	public static final Integer COD_PARAM_REMITENTE_CORREO_FACTURACION = 9;
	public static final Integer COD_PARAM_BCC_CORREO_FACTURACION = 30;

	public static final Integer COD_PARAM_ASUNTO_CORREO_CAMBIO_TIPO_ENVIO = 10;
	public static final Integer COD_PARAM_TEXTO_CORREO_CAMBIO_TIPO_ENVIO = 11;
	public static final Integer COD_PARAM_REMITENTE_CAMBIO_TIPO_ENVIO = 12;
	public static final Integer COD_PARAM_BCC_CAMBIO_TIPO_ENVIO = 31;

	public static final Integer COD_PARAM_ASUNTO_PREPAGO_TOTAL = 13;
	public static final Integer COD_PARAM_TEXTO_PREPAGO_TOTAL = 14;
	public static final Integer COD_PARAM_REMITENTE_PREPAGO_TOTAL = 15;
	public static final Integer COD_PARAM_BCC_PREPAGO_TOTAL = 32;

	public static final Integer COD_PARAM_ASUNTO_PREPAGO_PARCIAL = 16;
	public static final Integer COD_PARAM_TEXTO_PREPAGO_PARCIAL = 17;
	public static final Integer COD_PARAM_REMITENTE_PREPAGO_PARCIAL = 18;
	public static final Integer COD_PARAM_BCC_PREPAGO_PARCIAL = 33;

	public static final Integer COD_PARAM_ASUNTO_DOCUMENTO_REENVIO = 19;
	public static final Integer COD_PARAM_TEXTO_DOCUMENTO_REENVIO = 20;
	public static final Integer COD_PARAM_REMITENTE_DOCUMENTO_REENVIO = 21;
	public static final Integer COD_PARAM_BCC_DOCUMENTO_REENVIO = 34;

	public static final Integer COD_PARAM_TEXTO_CORREO_COBRANZA_INTERNA_DEFINITIVA = 22;
	public static final Integer COD_PARAM_ASUNTO_CORREO_COBRANZA_INTERNA_DEFINITIVA = 23;
	public static final Integer COD_PARAM_REMITENTE_CORREO_COBRANZA_INTERNA_DEFINITIVA = 24;
	public static final Integer COD_PARAM_BCC_CORREO_COBRANZA_INTERNA_DEFINITIVA = 35;

	public static final Integer COD_PARAM_TEXTO_DOCUMENTO_CAMBIO_FECHA = 25;
	public static final Integer COD_PARAM_ASUNTO_DOCUMENTO_CAMBIO_FECHA = 26;
	public static final Integer COD_PARAM_REMITENTE_DOCUMENTO_CAMBIO_FECHA = 27;
	public static final Integer COD_PARAM_BCC_DOCUMENTO_CAMBIO_FECHA = 36;

	/* Parametros Datos Generales BBVA */
	public static final Integer PARAM_DATOS_GENERALES_BBVA_CF = 12;

	public static final Integer COD_PARAM_RUC_BBVA_CF = 1;
	public static final Integer COD_PARAM_DIRECCION_BBVA_CF = 2;
	
	public static final  String COD_CONTRATO_NULO = null;

	public static final Integer COD_PARAM_MINIMOS = 55;
	public static final Integer COD_PARAM_MINIMOS_SEGURO = 1;
	public static final Double IMP_MINIMOS_SEGURO_DEFA = 0.01;
	
	public static final Integer COD_PARAM_IMPORTE_MINIMO_FINANCIAR = 2;
	
	public static final Integer COD_TIPO_ORIGEN_FONDO =50;
	public static final Integer COD_TIPO_MEDIO_PAGO =51;
	public static final Integer COD_TIPO_CANAL_OPERACION =52;
	
	public static final Integer COD_TIPO_ENVIO =19;
	public static final Integer COD_TIPO_OCUPACION =16;
	public static final Integer COD_TIPO_MODALIDAD =20;
	public static final Integer COD_TIPO_CRONOGRAMA =56;

	public static final  String PREPAGO_TOTAL = "T";
	public static final  String PREPAGO_PARCIAL = "P";
	
	public static final Integer  TIPO_PREPAGO_PARCIAL = 8;
	public static final Integer  TIPO_PREPAGO_TOTAL = 9;

	public static final String GENERA_HRI_CRO = "Se genero los Documentos HRI y CRO";

	public static final Integer EVENTO_ESTADO_CONTRATO         = 14;
	public static final Integer EVENTO_FIRMA_CONTRATO		   = 15;
	public static final Integer EVENTO_PREPAGO_PARCIAL         = 16;
	public static final Integer EVENTO_PREPAGO_TOTAL           = 17;
	public static final Integer EVENTO_CAMBIO_TIPO_ENVIO       = 18;
	public static final Integer EVENTO_CAMBIO_VENCIMIENTO      = 19;
	public static final Integer EVENTO_CAMBIO_EMAIL_CONTRATO   = 20;
	public static final Integer EVENTO_CAMBIO_FECHA_PAGO       = 22;
	public static final Integer EVENTO_FECHA_VENCIMIENTO_CUOTA = 23;

	public static final Integer ESTADO_DESEMBOLSADO = 5;
	public static final Integer ESTADO_CANCELADO = 13;

	public static final String GLOSA_PREPAGO_PARCIAL = "PREPAGO PARCIAL";
	public static final String GLOSA_PREPAGO_TOTAL = "PREPAGO TOTAL";
	
	/* Recaudación */
	public static final String RECAUDACION_CABECERA = "01";
	public static final String RECAUDACION_DETALLE = "02";
	public static final String RECAUDACION_TOTALES = "03";
	
	/* Errores de validación */
	public static final String ERROR_TOTAL_REGISTROS = "Error, la suma de los registros no coinciden con el total";
	public static final String ERROR_TOTAL_PAGOS = "Error, la suma de los pagos no coincide con el total de pagos";
	public static final String ERROR_TOTAL_DEPOSITOS = "Error, la suma de los depositos no coincide con el total de depositos";
	public static final String ERROR_TOTAL_MORA = "Error, la suma de las moras no coincide con el total de mora";
	
	public static final String TIPO_USUARIO_TDP = "TDP";
	public static final String TIPO_USUARIO_BBVA = "BBVA";
	
	public static final Integer OPCION_MANTENIMIENTO_4 = 4;
	
	public static final Integer ENVIO_CORREO_CORRECTO = 1;
	public static final Integer ENVIO_CORREO_ERROR = 0;
	
	
	public static final int CD_CABECERA_12 = 12;
	public static final int CD_CABECERA_13 = 13;
	public static final int CD_CABECERA_14 = 14;
	public static final int CD_CABECERA_4 = 4;
	public static final int CD_CABECERA_22 = 22;
	public static final int CD_CABECERA_18 = 18;
	public static final int CD_CABECERA_16 = 16;
	public static final int CD_CABECERA_25 = 25;
	public static final int CD_CABECERA_64 = 64;
	public static final int CD_CABECERA_19 = 19;
	
	public static final int CD_DETALLE_1 = 1;
	public static final int CD_DETALLE_2 = 2;
	public static final int CD_DETALLE_3 = 3;
	public static final int CD_DETALLE_4 = 4;
	public static final int CD_DETALLE_5 = 5;
	public static final int CD_DETALLE_6 = 6;
}
