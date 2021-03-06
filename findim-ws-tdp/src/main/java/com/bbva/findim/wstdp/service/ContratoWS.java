package com.bbva.findim.wstdp.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.bbva.findim.dom.RespuestaBean;

@WebService
public interface ContratoWS{	
	public RespuestaBean altaContrato(
			@WebParam(name ="llaveUnica") String llaveunica,			//	 1	cd_llaveunica 		string(32)
			@WebParam(name ="atributo") String atributo, // 2	nb_atributo 		string(50)
			@WebParam(name ="tarifa") String tarifa, // 3	cd_tarifa 		string(10)
			@WebParam(name ="importeBien") Double importeBien , // 4	im_bien 		numeric(14,2) 
			@WebParam(name ="importePrestamo") Double ImportePrestamo , // 5	im_prestamo 		numeric(14,2) 
			@WebParam(name ="diaFacturacion") Integer diaFacturacion, // 6	nu_diafacturacion 		numeric(2)
			@WebParam(name ="diaPagoCred") Integer diaPagocred , // 7	nu_diapagocred		numeric(2)
			@WebParam(name ="tipoDocIdentidad") String 	tipoDocIdentidad, // 8	tp_docidentidad 		string(5) 
			@WebParam(name ="numeroDocIdentidad") String 	numeroDocIdentidad, // 9	cd_docidentidad 		string(20)
			@WebParam(name ="direccion") String 	direccion , // 10	nb_direccion	string(100) 
			@WebParam(name ="departamento") String 	departamento, // 11	cd_departamento 	string(3) 
			@WebParam(name ="provincia") String 	provincia , // 12	cd_provincia	string(3) 
			@WebParam(name ="distrito") String 	distrito, // 13	cd_distrito 	string(3) 
			@WebParam(name ="telefonoFijo") String 	telefonoFijo, // 14	nb_numerotelefono_FJ	string(15)
			@WebParam(name ="telefonoReferencia") String 	telefonoReferencia, // 15	nb_numerotelefono_RF	string(15)
			@WebParam(name ="telefonoFinanciado") String 	telefonoFinanciado, // 16	nb_numerotelefono_FN	string(15)
			@WebParam(name ="correo") String 	correo, // 17	nb_mail 	string(50)
			@WebParam(name ="tipoTransaccion") String 	transacporta, // 18	tp_transacporta 	string(5) 
			@WebParam(name ="codigoCanal") String 	codigoCanal , // 19	cd_canal	string(5) 
			@WebParam(name ="nombreCanal") String 	nombreCanal , // 20	nb_canal							string(100) 
			@WebParam(name ="codigoEntidad") Integer codigoEntidad , // 21	cd_entidad	numeric(5)
			@WebParam(name ="nombreEntidad") String 	nombreEntidad , // 22	nb_entidad						string(100) 
			@WebParam(name ="codigoPuntoVenta") Integer codigoPuntoVenta, // 23	cd_puntoventa 	numeric(10) 
			@WebParam(name ="nombrePuntoVenta") String 	nombrePuntoVenta, // 24	nb_puntoventa					string(100) 
			@WebParam(name ="tipoEnvio") Integer tipoEnvio , // 25	tp_envio	numeric(5)
			@WebParam(name ="departamentoFirma") String departamentoFirma//26	cd_dpto_ptovta				string(3)         
			);
}
