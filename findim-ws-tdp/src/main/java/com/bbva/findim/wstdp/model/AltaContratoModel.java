package com.bbva.findim.wstdp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Properties;

import com.bbva.findim.bck.service.PersonService;
import com.bbva.findim.dom.CabeceraBean;
import com.bbva.findim.dom.ContratoAltaBean;
import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.TarifaBean;
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.dom.reniec.Ciudadano;
import com.bbva.findim.dom.util.DateUtil;
import com.bbva.server.reniec.WS_PersonaReniec;
import com.grupobbva.pe.sir.ents.body.consultapordni.DatosNacimiento;
import com.grupobbva.pe.sir.ents.body.consultapordni.DatosPersona;
import com.grupobbva.pe.sir.ents.body.consultapordni.RespuestaDNI;
import com.grupobbva.pe.sir.ents.body.consultapordni.RespuestaDatos;
import com.grupobbva.pe.sir.ents.header.RequestHeader;
import com.grupobbva.pe.sir.ents.header.ResponseHeader;
import com.grupobbva.pe.sir.service.message.ConsultaPorDNIRequest;
import com.grupobbva.pe.sir.service.message.ConsultaPorDNIResponse;

public class AltaContratoModel {

	public static Ciudadano obtenerInformacionReniec(String numeroDocumento, Properties prop, WS_PersonaReniec wS_PersonaReniec_Service) {
	    //consulta a reniec
		String registro = prop.getProperty("ws.consulta.reniec.registro");// "P017737";
		String fhActual = DateUtil.getFechaActual("yyyy-MM-dd HH.mm.ss.SSSSSS");
		StringBuilder sRutaSoap = new StringBuilder();
		
		sRutaSoap.append(prop.getProperty("ws.consulta.reniec.service.url"));

		ConsultaPorDNIRequest consultaPorDNIRequest = new ConsultaPorDNIRequest();
		consultaPorDNIRequest.setRefRequestHeader(new RequestHeader());
		consultaPorDNIRequest.getRefRequestHeader().setCanal(prop.getProperty("ws.consulta.reniec.canal"));
		consultaPorDNIRequest.getRefRequestHeader().setCodigoAplicacion(prop.getProperty("ws.consulta.reniec.codigo.aplicacion"));
		consultaPorDNIRequest.getRefRequestHeader().setIdEmpresa(prop.getProperty("ws.consulta.reniec.id.empresa"));
		consultaPorDNIRequest.getRefRequestHeader().setUsuario(registro);
		consultaPorDNIRequest.getRefRequestHeader().setFechaHoraEnvio(fhActual);//"2016-01-05-17.38.55.223456"
		fhActual=(fhActual.replace(".", "")).replace("-", "");
		consultaPorDNIRequest.getRefRequestHeader().setIdTransaccion(fhActual+registro);//"20160105173855223456PICP017737"
		consultaPorDNIRequest.getRefRequestHeader().setCodigoInterfaz(prop.getProperty("ws.consulta.reniec.codigo.interfaz"));
		// TODO ADD-REQUEST
		consultaPorDNIRequest.setRefConsultaPorDNIRequest(new com.grupobbva.pe.sir.ents.body.consultapordni.ConsultaPorDNIRequest());
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setTipoAplicacion("E");
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setRegistroCodUsuario(registro);
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setNumeroDNIConsultado(numeroDocumento);
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setIndConsultaDatos("S");
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setIndConsultaFoto("N");
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setIndConsultaFirma("N");

		ConsultaPorDNIResponse dniResponseDocument = null;

		dniResponseDocument = wS_PersonaReniec_Service.consultaPorDNI(consultaPorDNIRequest);

		Ciudadano ciudadano = new Ciudadano();
		ResponseHeader responseHeader = dniResponseDocument.getRefResponseHeader();
		if (responseHeader.getCodigoRespuesta().equals("0000")) {
			com.grupobbva.pe.sir.ents.body.consultapordni.ConsultaPorDNIResponse dniResponse = dniResponseDocument.getRefConsultaPorDNIResponse();
			RespuestaDNI respuestaDNI = dniResponse.getRespuestaDNI();
			RespuestaDatos respuestaDatos = dniResponse.getRespuestaDatos();
			DatosPersona datosPersona = respuestaDatos.getDatosPersona();
			DatosNacimiento datosNacimiento = respuestaDatos.getDatosNacimiento();
			ciudadano.setFechaCaducidad(respuestaDatos.getDatosDNI().getFechaExpedicion());
			ciudadano.setNumeroDNI(respuestaDNI.getNumDNIConsultado());
			ciudadano.setNombres(datosPersona.getNombres());
			ciudadano.setApellidoPaterno(datosPersona.getApellidoPaterno());
			ciudadano.setApellidoMaterno(datosPersona.getApellidoMaterno());
			ciudadano.setApellidoCasada(datosPersona.getApellidoCasada());
			ciudadano.setSexo(datosPersona.getSexo());
			ciudadano.setCodigoEstadoCivil(datosPersona.getCodigoEstadoCivil());
			ciudadano.setDescripcionEstadoCivil(datosPersona.getDescripcionEstadoCivil());
			ciudadano.setFechaNacimiento(datosNacimiento.getFecha());// DATOS DE
																		// NACIMIENTO
		}
		ciudadano.setCodigoRespuesta(responseHeader.getCodigoRespuesta());
		ciudadano.setMensajeRespuesta(responseHeader.getMensajeRespuesta());
		return ciudadano;
	}



	public static String obtenerSexoPersona(String sexo) {
		String sexoPersona = "";
		if (sexo.equals(PersonService.MASCULINO)) {
			sexoPersona = "M";
		} else if (sexo.equals(PersonService.FEMENINO)) {
			sexoPersona = "F";
		}
		return sexoPersona;
	}

	public static String obtenerEstadoCivilPersona(String cdEstCivilFront, Integer cdEstCivilBack) {
		if(cdEstCivilFront!=null) {
			if (cdEstCivilFront.equals(Constantes.EstadoCivil.S.name())) {
				return Constantes.EstadoCivil.S.getEstadoCivil().toString();
			} else if (cdEstCivilFront.equals(Constantes.EstadoCivil.C.name())) {
				return Constantes.EstadoCivil.C.getEstadoCivil().toString();
			} else if (cdEstCivilFront.equals(Constantes.EstadoCivil.V.name())) {
				return Constantes.EstadoCivil.V.getEstadoCivil().toString();
			} else if (cdEstCivilFront.equals(Constantes.EstadoCivil.D.name())) {
				return Constantes.EstadoCivil.D.getEstadoCivil().toString();
			}else {
				return Constantes.EstadoCivil.S.getEstadoCivil().toString();
			}
		}else {
			if(cdEstCivilBack==Constantes.EstadoCivil.S.getEstadoCivil())
				return Constantes.EstadoCivil.S.name();
			else if (cdEstCivilBack==Constantes.EstadoCivil.C.getEstadoCivil())
				return Constantes.EstadoCivil.C.name();
			else if (cdEstCivilBack==Constantes.EstadoCivil.D.getEstadoCivil())
				return Constantes.EstadoCivil.D.name();
			else if (cdEstCivilBack==Constantes.EstadoCivil.V.getEstadoCivil())
				return Constantes.EstadoCivil.V.name();
			else 
				return Constantes.EstadoCivil.S.name().toString();
		}
		
	}

	public static ContratoAltaBean obtenerValoresBean(String llaveunica, 
			//test
			String atributo, // 2 nb_atributo string(50)
			String tarifa, // 3 cd_tarifa string(10)
			Double importeBien, // 4 im_bien numeric(14,2)
			Double importePrestamo, // 5 im_prestamo numeric(14,2)
			Integer diaFacturacion, // 6 nu_diafacturacion numeric(2)
			Integer diaPagocred, // 7 nu_diapagocred numeric(2)
			String tipoDocIdentidad, // 8 tp_docidentidad string(5)
			String numeroDocIdentidad, // 9 cd_docidentidad string(20)
			String direccion, // 10 nb_direccion string(100)
			String departamento, // 11 cd_departamento string(3)
			String provincia, // 12 cd_provincia string(3)
			String distrito, // 13 cd_distrito string(3)
			String telefonoFijo, // 14 nb_numerotelefono_FJ string(15)
			String telefonoReferencia, // 15 nb_numerotelefono_RF string(15)
			String telefonoFinanciado, // 16 nb_numerotelefono_FN string(15)
			String correo, // 17 nb_mail string(50)
			String transacporta, // 18 tp_transacporta string(5)
			String codigoCanal, // 19 cd_canal string(5)
			Integer codigoEntidad, // 21 cd_entidad numeric(5)
			String nombreEntidad, // 22 nb_entidad string(100)
			Integer codigoPuntoVenta, // 23 cd_puntoventa numeric(10)
			String nombrePuntoVenta, // 24 nb_puntoventa string(100)
			Integer tipoEnvio, // 25 tp_envio numeric(5)
			String departamentoFirma // 26 cd_dpto_ptovta string(3)
	) throws Exception{
		ContratoAltaBean contratoAltaBean = new ContratoAltaBean();

		try {
			contratoAltaBean.setAtributos(atributo);
			contratoAltaBean.setIdPuntoVenta(codigoPuntoVenta);
			contratoAltaBean.setDescripcionPuntoVenta(nombrePuntoVenta);
			contratoAltaBean.setValorEquipo(importeBien);
			contratoAltaBean.setImportePrestamo(importePrestamo);

			contratoAltaBean.setIdTarifa(1);
			contratoAltaBean.setKeyUnica(llaveunica);
			contratoAltaBean.setTipoTransaccion(0);
			contratoAltaBean.setIdEntidad(0);
			contratoAltaBean.setDescripcionEntidad("");
			contratoAltaBean.setIdCanal(2);
			contratoAltaBean.setDescripcionCanal(codigoCanal);
			contratoAltaBean.setDiaPago(diaPagocred);
			contratoAltaBean.getClienteBean().setTipoDocumento("DNI");
			contratoAltaBean.getClienteBean().setNumeroDocumento(numeroDocIdentidad);
			contratoAltaBean.getClienteBean().setCorreoCliente(correo);
			contratoAltaBean.getDomicilio().setDireccion(direccion);
			contratoAltaBean.getDomicilio().setIdDistrito(distrito);
			contratoAltaBean.getDomicilio().setIdDepartamento(departamento);
			contratoAltaBean.getDomicilio().setIdProvincia(provincia);
			contratoAltaBean.getClienteBean().setTelefonoFijo(telefonoFijo);
			contratoAltaBean.getClienteBean().setTelefonoReferencial("");
			contratoAltaBean.setTelefonoFinanciado(telefonoFinanciado);
			contratoAltaBean.setIdTipoEnvio(0);

			contratoAltaBean.setAddDepartamentoFirma("");
			contratoAltaBean.setAddNombreTarifa(tarifa);
			contratoAltaBean.setAdddiaFacturacion(diaFacturacion);
			contratoAltaBean.getClienteBean().setAddtipoDocIdentidad(tipoDocIdentidad);
			contratoAltaBean.setAddtransacporta(transacporta);
			contratoAltaBean.setAddnombreCanal(codigoCanal);

		} catch (Exception ex) {
//			LOGGER.error(ex.getMessage(), ex);
			System.out.println();
		}
		return contratoAltaBean;
	}
	
	@SuppressWarnings("deprecation")
	public static  SimulacionBean mapper(String tipoMoneda, BigDecimal valorEquipo, TarifaBean tarifaFinanciamiento,
			BigDecimal porcentajeCuotaInicial, BigDecimal cuotaInicial, BigDecimal saldoAFinanciar,
			                      String metodoEnvio)
		{
		SimulacionBean simulacion = new SimulacionBean();
		
		simulacion.setCdSubProducto(tarifaFinanciamiento.getCdSubProducto());
		simulacion.setCabecera(new CabeceraBean());
		simulacion.getCabecera().setCuotaInicial(cuotaInicial);
		simulacion.getCabecera().setMoneda(tipoMoneda);
		
		if(metodoEnvio.equals("0")){//VIRTUAL
			simulacion.getCabecera().setMetodoEnvio(0);
		}else if(metodoEnvio.equals("10")){//FISICO
			simulacion.getCabecera().setMetodoEnvio(1);
		}else if(metodoEnvio.equals("20")){//AMBOS
			simulacion.getCabecera().setMetodoEnvio(1);
		}
		
		simulacion.getCabecera().setSaldoFinanciar(saldoAFinanciar);
		simulacion.getCabecera().setNumeroCuotas(Integer.parseInt(tarifaFinanciamiento.getCuota()));
		simulacion.getCabecera().setDiaPago((new Date()).getDay());
		simulacion.getCabecera().setTea(tarifaFinanciamiento.getTasa()); 
		simulacion.getCabecera().setCodigoTarifa(tarifaFinanciamiento.getCodigoTarifa());
		simulacion.getCabecera().setValorEquipo(valorEquipo);
		simulacion.getCabecera().setSeguroDesgravamen(tarifaFinanciamiento.getTasaSeguro());
		
		return simulacion;
	
	}

}
