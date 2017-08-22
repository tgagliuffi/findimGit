package com.bbva.findim.bck.service;

import java.util.List;

import com.bbva.findim.dom.ContratoAltaBean;
import com.bbva.findim.dom.ContratoBean;

public interface ProposalService {
	
	String altaProposal(String tSec, ContratoAltaBean contratoBean) throws Exception;
	
	int updateProposal(String tSec, ContratoBean contratoBean) throws Exception;
	
	List<ContratoBean> listarPropuesta(String tSec,String pCategoria, String pProveed, String tipoDocumento, String nroDocumento, String pFromDate);
	
	enum Moneda {
		
		PEN("PEN"), USD("USD");
		private String moneda;
		Moneda(String moneda) {
	        this.moneda = moneda;
	    }
		public String getMoneda() {
	        return this.moneda;
	    }
	}

	enum TipoEnvio {
		FISICO("PHYSICAL"), VIRTUAL("DIGITAL")
		,AMBOS("BOTH"), NINGUNO("NONE");
		private String tipoEnvio;
		TipoEnvio(String tipoEnvio) {
	        this.tipoEnvio = tipoEnvio;
	    }
		public String getTipoEnvio() {
	        return this.tipoEnvio;
	    }
	}

	enum TipoDocumento {
		DNI("DNI"), RUC("RUC"), CE("CE");
		private String tipoDocumento;
		TipoDocumento(String tipoDocumento) {
	        this.tipoDocumento = tipoDocumento;
	    }
		public String getTipoDocumento() {
	        return this.tipoDocumento;
	    }
	}

	enum Estado {
		ESTADO_DESEMBOLSADO("DISBURSED"),//HOST(0)- 5-ESTADO-DESEMBOLSAD
		ESTADO_ANULADO("ANNULLED"),//HOST(6)-6-ESTADO-ANULADO
		ESTADO_CANCELADO("CANCELLED"),//HOST(6)-6-ESTADO-ANULADO
		ESTADO_FIRMADO("SIGNED"),//HOST(F)-2-ESTADO-FIRMADO
		ESTADO_TRAMITE("PENDING_COMPLETION_DATA"),//HOST(7)-4, 7, 10-ESTADO-TRAMITE 
		ESTADO_PENDIENTE("PENDING_SIGNATURE");//HOST(8)-1-ESTADO-PENDTE
		private String estado;
		Estado(String estado) {
	        this.estado = estado;
	    }
		public String getEstado() {
	        return this.estado;
	    }
	}
	
	enum EstadoHost {
		DISBURSED("ESTADO_DESEMBOLSADO"),//HOST(0)- 5-ESTADO-DESEMBOLSAD
		ANNULLED("ESTADO_ANULADO"),//HOST(6)-6-ESTADO-ANULADO
		CANCELLED("ESTADO_CANCELADO"),//HOST(6)-6-ESTADO-ANULADO
		SIGNED("ESTADO_FIRMADO"),//HOST(F)-2-ESTADO-FIRMADO
		PENDING_COMPLETION_DATA("ESTADO_TRAMITE"),//HOST(7)-4, 7, 10-ESTADO-TRAMITE
		PENDING_SIGNATURE("ESTADO_PENDIENTE");//HOST(8)-1-ESTADO-PENDTE
		private String estadoHost;
		EstadoHost(String estadoHost) {
	        this.estadoHost = estadoHost;
	    }
		public String getEstado() {
	        return this.estadoHost;
	    }
	}
	
	enum IndicadorDireccion{
		
		ALM("ALAMEDA"),
		AV("AVENUE"),
		BAJ("DESCENT"),
		CAL("STREET"),
		CC("MALL"),
		CRT("ROAD"),
		GAL("SHOPPING_ARCADE"),
		JR("JIRON"),
		MAL("JETTY"),
		OVA("OVAL"),
		PAS("PEDESTRIAN_WALK"),
		PLZ("SQUARE"),
		POR("PORTAL"),
		PQE("PARK"),
		PRL("PROLONGATION"),
		PSJ("PASSAGE"),
		PTE("BRIDGE"),
		AGR("GROUP"),
		AHH("AAHH");
		private String indicador;
		IndicadorDireccion(String indicador) {
	        this.indicador = indicador;
	    }
		public String getIndicadorDireccion() {
	        return this.indicador;
	    }
		
	}
}
