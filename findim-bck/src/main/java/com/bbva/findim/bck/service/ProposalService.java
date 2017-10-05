package com.bbva.findim.bck.service;

import java.util.List;

import com.bbva.findim.dom.ContratoAltaBean;
import com.bbva.findim.dom.ContratoBean;
import com.bbva.findim.dom.EmpresaBean;

public interface ProposalService {
	
	ContratoAltaBean altaProposal(String tSec, ContratoAltaBean contratoBean) throws Exception;
	
	int updateProposal(String tSec, ContratoBean contratoBean) throws Exception;
	
	List<ContratoBean> listarPropuesta(String tSec, String tipoDocumento, String nroDocumento, EmpresaBean empresa);
	
	ContratoBean obtenerPropuesta(String tSec,String pCategoria, String pProveed, String tipoDocumento, String nroDocumento, String pFromDate, String cdPropuesta);
	
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
		DESEMBOLSADO("DISBURSED"),//HOST(0)- 5-ESTADO-DESEMBOLSAD
		ANULADO("ANNULLED"),//HOST(6)-6-ESTADO-ANULADO
		CANCELADO("CANCELLED"),//HOST(6)-6-ESTADO-ANULADO
		FIRMADO("SIGNED"),//HOST(F)-2-ESTADO-FIRMADO
		EN_TRAMITE("PENDING_COMPLETION_DATA"),//HOST(7)-4, 7, 10-ESTADO-TRAMITE 
		PENDIENTE_FIRMA("PENDING_SIGNATURE");//HOST(8)-1-ESTADO-PENDTE
		private String estado;
		Estado(String estado) {
	        this.estado = estado;
	    }
		public String getEstado() {
	        return this.estado;
	    }
	}
	
	enum EstadoHost {
		DISBURSED("DESEMBOLSADO"),//HOST(0)- 5-ESTADO-DESEMBOLSAD
		ANNULLED("ANULADO"),//HOST(6)-6-ESTADO-ANULADO
		CANCELLED("CANCELADO"),//HOST(6)-6-ESTADO-ANULADO
		SIGNED("FIRMADO"),//HOST(F)-2-ESTADO-FIRMADO
		PENDING_COMPLETION_DATA("EN_TRAMITE"),//HOST(7)-4, 7, 10-ESTADO-TRAMITE
		PENDING_SIGNATURE("PENDIENTE_FIRMA");//HOST(8)-1-ESTADO-PENDTE
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
