package com.bbva.findim.bck.service;

import java.util.List;

import com.bbva.findim.bck.domain.customers.Customer;
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ParametroBean;

public interface CustomerService {

	ClienteBean altaCliente(String tSec, ClienteBean clienteNuevo, Customer obj) throws Exception;
	ClienteBean obtenerDatosCliente(String tSec, String tipoDocumento, String numeroDocumento, List<ParametroBean> lstIndicadoresVia)throws Exception;

	enum NivelDetalle {
		
		ADDRESSES("addresses")
		, CONTACT_DETAILS("contact-details")
		, INDICATORS("indicators")
		, TERMS("terms")
		;
		
		private String nivel;

		NivelDetalle(String nivel) {
	        this.nivel = nivel;
	    }

		public String getNivel() {
	        return this.nivel;
	    }

	}

	enum EstadoCivilBack {

		SOLTERO("SINGLE")
		, CASADO("MARRIED")
		, DIVORCIADO("DIVORCED")
		, CONVIVIENTE("COHABITANT")
		, VIUDO("WIDOWED")
		;

		private String estado;

		private EstadoCivilBack(String estado) {
			this.estado = estado;
		}

		public String getEstado() {
			return estado;
		}
	}
	enum EstadoCivilFront {

		SOLTERO("1")
		, CASADO("2")
		, DIVORCIADO("4")
		, CONVIVIENTE("5")
		, VIUDO("3")
		;

		private String estado;

		private EstadoCivilFront(String estado) {
			this.estado = estado;
		}

		public String getEstado() {
			return estado;
		}
	}

	enum Genero {

		MASCULINO("MALE")
		, FENEMINO("FEMALE")
		;

		private String descipcion;

		private Genero(String descipcion) {
			this.descipcion = descipcion;
		}

		public String getDescipcion() {
			return descipcion;
		}
	}

	enum Residencia {

		PERMANENTE("PERMANENT")
		, TEMPORAL("TEMPORARY")
		;

		private String tipo;

		private Residencia(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}
	}

	enum TipoContacto {

		TELEFONO("PHONE_NUMBER")
		, CELULAR("MOBILE_NUMBER")
		, CORREO("EMAIL")
		;

		private String tipo;

		private TipoContacto(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}
	}

	enum TipoIngreso {

		FIJO("FIXED")
		, VARIABLE("VARIABLES")
		;

		private String tipo;

		private TipoIngreso(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}
	}

	enum TipoDireccion {

		LEGAL("LEGAL")
		,POSTAL("POSTAL")
		,WORK("WORK")
		,HOME("HOME")
		,VACATION("VACATION")
		,LOCKER("LOCKER")
		;

		private String tipo;

		private TipoDireccion(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}
	}

	enum TipoPropiedad {

		RENTED("RENTED")
		, OWNER("OWNER")
		, FAMILY("FAMILY")
		, MORTGAGED("MORTGAGED")
		;

		private String tipo;

		private TipoPropiedad(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}
	}

	enum GrupoDireccion {

		 URBANIZACION("URBANIZATION")
		 ,AAHH("AAHH")
		 ,AVENIDA("AVENUE")
		 ,CALLE("STREET")
		 ,JIRON("JIRON")
		 ,PASAJE("PASAJE")
		 ,QUINTA("QUINTA")
		 ,BLOQUE("BLOCK")
		 ,NUMERO_EXTERIOR("EXTERIOR_NUMBER")
		 ,NUMERO_INTERIOR("INTERIOR_NUMBER")
		 ,PUERTA("DOOR")
		 ,PISO("FLOOR")
		 ,DISTRITO("DISTRITO")
		 ,BARRIO("BARRIO")
		 ,COLONIA("COLONIA")
		 ,DELEGACION("DELEGACION")
		 ,UBIGEO("UBIGEO")
		 ,PARQUE("PARK")
		 ,OVALO("OVAL")
		;

		private String tipo;

		private GrupoDireccion(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}
	}

	enum Indicador {

		PEP("PEP")
		 ,INE("INE")
		 ,EMBASSY("EMBASSY")
		;

		private String tipo;

		private Indicador(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return tipo;
		}
	}

	enum EstadoTermino {

		ACTIVADO("ACTIVE")
		 ,NO_SOLICITADO("NOT_REQUESTED")
		 ,ACEPTADO_NUEVO("S")
		 ,ACEPTADO_MODIF("ACCEPTED")
		 ,RECHAZADO("")
		 ,EXPIRADO("EXPIRED")
		 ;

		private String estado;

		private EstadoTermino(String estado) {
			this.estado = estado;
		}

		public String getEstado() {
			return estado;
		}

	}
	
	enum TituloPersona {

		SR("MR")
		, SRA("MRS")
		, SRTA("MISS")
		, SR_A("MR(A)")
		;

		private String titulo;

		private TituloPersona(String titulo) {
			this.titulo = titulo;
		}

		public String getTitulo() {
			return titulo;
		}
	}
	
	enum GeneroFront {
		MALE("1")
		, FEMALE("2");
		private String descipcion;

		private GeneroFront(String descipcion) {
			this.descipcion = descipcion;
		}

		public String getDescipcion() {
			return descipcion;
		}
	}

}
