package com.bbva.findim.bck.util;

import com.bbva.findim.bck.domain.communs.Value;

public final class ConstantesConection {
	
	private ConstantesConection() {}
	
	public static final String CODIGO_PERSONA_INELEGIBLE = "INE";
	public static final String CODIGO_PERSONA_EXPUESTA_PUBLICAMENTE = "PEP";
	public static final Value VALUE_PERU = new Value("PER");
	
	public static final String BANCO_BBVA_CONTINENTAL = "0011";
	public static final String UBIGEO_HOST_DPTO_LIMA = "01";
	public static final String OFICINA_DEFAULT_LIMA = "0486";
	
	public final static class Empresas {
//		public static final String EMP_TEL_COD_TELF = "financiamiento.codigo.empresa";
//		public static final String EMP_TEL_IDEN_CE = "financiamiento.codigo.identif";
	}
	

	
	public final class FORMATOS_TEXTO{
		public static final String FECHA = "dd/MM/yyyy";
		public static final String HORA = "HH:mm:ss";	
		public static final String FECHA_ISO_8601 = "yyyy-MM-dd"; 
	}
	

	public final static class Parametro {
		
		public final static class GENERAL {
			public static final String TIPO = "PLD_GENERAL";
			public static final String CODIGO_CAMPANHA = "PLD_COD_CAMPANHA";
			public static final String STR_VACIO = "";
			public static final String STR_ESPACIO = " ";
			public static final String COD_UBIGEO = "UBIGEO";
			
			public static final String DEPARTMENT = "DEPARTMENT";
			public static final String PROVINCE = "PROVINCE";
			public static final String DISTRICT = "DISTRICT";
			
			public static final String[] nombresTipoVia= {
					"Alameda","Avenida","Calle","Centro Comercial","Carretera","Galería comercial","Jirón","Malecón","Óvalo","Parque","Pasaje","Paseo","Plaza","Prolongación","Puente"
					};
			public static final String[] codigosTipoVia = {
					"ALAMEDA","AVENUE","STREET","MALL","ROAD","SHOPPING_ARCADE","JIRON","JETTY","OVAL","PARK","PASSAGE","PEDESTRIAN_WALK","SQUARE","PROLONGATION","BRIDGE"
					};
			public static final String[] aliasTipoVia = {
					"Alameda","AV","CALLE","Centro Comercial","Carretera","Galeria comercial","JR","MALECON","OV","Parque","PSJ","Paseo","Plaza","PROLONG","Puente"
				};
			public static final String[] nombresTipoZona = {
					"Agrupación","Asentamiento Humano","Conjunto Habitacional","Comunidad","Etapa","Pueblo Joven","Sector","Unidad Vecinal","Urbanización","Cooperativa Vivienda","Zona"
					};
			public static final String[] codigosTipoZona = {
					"GROUP","AAHH","HOUSING_COMPLEX","COMMUNITY","STAGE","SHANTYTOWN","NEIGHBORHOOD","NEIGHBORHOOD_UNIT","URBANIZATION","HOUSING_COOPERATIVE","ZONE"
					};
			public static final String[] aliasTipoZona = {
					"Agrupación","AA HH","Conjunto Habitacional","Comunidad","ET","Pueblo Joven","ST","Unidad Vecinal","URB","COOP VIV","ZNA"
				};
		}
		
		public final class SMTP {
			public static final String TIPO = "OAO_SMTP";
			public static final String CODIGO_CTA_GANADORA_SOLES = "CNTGNDR_PEN";
			public static final String CODIGO_CTA_GANADORA_DOLARES = "CNTGNDR_USD";
		}
		
		
		public final class CatalogoConstant {			
			public static final String TIPO = "OAO_PARM_CATALOGS";			
			public static final String CODIGO_URL_CATALOGS = "catalogs.codigo.url.listCatalogs";
			public static final String CODIGO_CATALOG_ID_KEY = "catalogs.codigo.catalogId.key";
			public static final String CODIGO_PAGINATION_CATALOGS_KEY = "PAGINATION_CATALOGS_KEY";
			public static final String CODIGO_SIZE_PAGE_CATALOGS_KEY = "SIZE_PAGE_CATALOGS_KEY";
		}
		
		public final class ClienteConstant {	
			public static final String CODIGO_URL_CUSTOMERS_LIST = "customers.codigo.url.listCustomer";
			public static final String CODIGO_URL_CUSTOMERS_CREATE = "customers.codigo.url.createCustomer";
			public static final String CODIGO_PARAMETER_FILTER = "customers.codigo.parameter.filter";
			public static final String CODIGO_NUMDOI_CUSTOMERS_KEY = "customers.codigo.numdoi.key";
			public static final String CODIGO_TIPDOI_CUSTOMERS_KEY = "customers.codigo.tipdoi.key";

		}
	
		public final class TarifaConstant {	
			public static final String CODIGO_URL_TARIFF_LIST = "tariff.codigo.url.lisTariffSchemas";
			public static final String CODIGO_PARAMETER_THIRDPARTY = "tariff.codigo.thirdPartyProvider.id";
			public static final String CODIGO_PARAMETER_EXTERNALPRODUCT = "tariff.codigo.externalProduct.category.type.id";
			public static final String CODIGO_CATEGOTY_TARIFF_TERM = "TERM";
			public static final String CODIGO_CATEGOTY_TARIFF_EAR = "EAR";
		}
		
		public final class Reniec {			
			public static final String TIPO = "OAO_PARM_RENIEC";			
			public static final String CODIGO_URL_RENIEC = "URL_RENIEC";
			public static final String CODIGO_REGISTRO_RENIEC = "REGISTRO_RENIEC";
			public static final String CODIGO_TIMEOUT_RENIEC = "TIMEOUT_RENIEC";
			public static final String CODIGO_FORMATOFECHA_RESPONSE_RENIEC = "FORMATOFECHA_RESPONSE_RENIEC";
			public static final String CODIGO_FORMATOFECHA_REQUEST_RENIEC = "FORMATOFECHA_REQUEST_RENIEC";
			public static final String CODIGO_INDFIR_RENIEC = "INDFIR_RENIEC";
			public static final String CODIGO_INDFOT_RENIEC = "INDFOT_RENIEC";
			public static final String CODIGO_INDDAT_RENIEC = "INDDAT_RENIEC";
			public static final String CODIGO_TIPAPP_RENIEC = "TIPAPP_RENIEC";
			public static final String CODIGO_CODINT_RENIEC = "CODINT_RENIEC";
			public static final String CODIGO_IDEMP_RENIEC = "IDEMP_RENIEC";
			public static final String CODIGO_CODAPP_RENIEC = "CODAPP_RENIEC";
			public static final String CODIGO_CANAL_RENIEC = "CANAL_RENIEC";
		}		


		public final class GrantingTicket {
			public static final String TIPO = "OAO_PARM_GT";
			
			public static final String CODIGO_URL_GRANTINGTICKET = "grantingTicket.codigo.url";
			public static final String CODIGO_USERID_GRANTINGTICKET = "grantingTicket.userid";
			public static final String CODIGO_CONSUMERID_GRANTINGTICKET = "grantingTicket.consumerid";	
			public static final String CODIGO_AUTHENTICTYPE_GRANTINGTICKET = "grantingTicket.authentictype";
			public static final String CODIGO_AUTHENTICAVAL_GRANTINGTICKET = "grantingTicket.autdatumval.";
			public static final String CODIGO_AUTHENTICAID_GRANTINGTICKET = "grantingTicket.AuthenticationDataId";
			
			public static final String CODIGO_USERID_GRANTINGTICKET_PUBLIC = "grantingTicket.userid.public";
			public static final String CODIGO_CONSUMERID_GRANTINGTICKET_PUBLIC = "grantingTicket.consumerid.public";
			public static final String CODIGO_AUTHENTICTYPE_GRANTINGTICKET_PUBLIC = "grantingTicket.authentictype.public";
			public static final String CODIGO_AUTHENTICAVAL_GRANTINGTICKET_PUBLIC = "grantingTicket.autdatumval.public";
			public static final String CODIGO_AUTHENTICAID_GRANTINGTICKET_PUBLIC = "grantingTicket.autdatumkey.public";
			
			public static final String CODIGO_USERID_GRANTINGTICKET_BACK = "grantingTicket.userid.back";
			public static final String CODIGO_CONSUMERID_GRANTINGTICKET_BACK = "grantingTicket.consumerid.back";
			public static final String CODIGO_AUTHENTICTYPE_GRANTINGTICKET_BACK = "grantingTicket.authentictype.back";
			public static final String CODIGO_AUTHENTICAVAL_GRANTINGTICKET_BACK = "grantingTicket.autdatumval.back";
			public static final String CODIGO_AUTHENTICAID_GRANTINGTICKET_BACK= "grantingTicket.autdatumkey.back";
			//public static final String CODIGO_ACCESS_COD_GRANTINGTICKET_BNET = "grantingTicket.accesscod.bnet";

		}
		
		public final class Flag {			
			public static final String TIPO = "OAO_PARM";			
			public static final String CODIGO_SERVICIO_ARQUITECTURA = "SERVICIO_ARQUITECTURA";
			public static final String VALOR_HABILITADO = "1";
			public static final String LISTA_OFICINAS_A_REEMPLAZAR = "OFICINAS_A_REEMPLAZAR";
		}
		
		public final class PrestamoConstant {	
			public static final String CODIGO_URL_LOAN = "loan.codigo.url.getLoan";
			public static final String CODIGO_URL_LOAN_SIMULATE = "loan.codigo.url.simulateLoan";
			public static final String CODIGO_NUMID_LOAN = "loan.codigo.loanId.key";
			public static final String CODIGO_PARAMETER_LOAN = "loan.codigo.loanId.key";
			public static final String CODIGO_PARAMETER_ENVIO_FISICO = "loan.simulate.envioVirtual.id";
			public static final String CODIGO_PARAMETER_RELATEDCONTRACTS = "loan.simulate.relatedContracts";
			public static final String CODIGO_PARAMETER_PART_BIRTHDATE = "loan.simulate.participants.birthDate";
			public static final String CODIGO_PARAMETER_PRODUCT_PURSPOSES = "loan.simulate.product.purposes";
			public static final String CODIGO_PARAMETER_TERM_TIMEUNIT = "loan.simulate.term.timeUnit";
			public static final String CODIGO_PARAMETER_IND_INSURANCECOVERAGE = "loan.simulate.indicators.insuranceCoverage";

		}
		
		public final class CalificacionConstant {
			public static final String CODIGO_URL_APPROVALS = "approvals.codigo.url.credit-approvals";
			public static final String CODIGO_NUMID_APPROVALS = "approvals.codigo.documentNumber.key";
			public static final String CODIGO_TYPEID_APPROVALS = "approvals.codigo.documentType.key";
			
			
			public static final String APROBADO = "APPROVED";
			public static final String REJECTED = "REJECTED";
            public static final String RECHAZADO = "DESAPROVED";
            public static final int CODIGO_RESULTADO_APROBADO = 1;
            public static final int CODIGO_RESULTADO_RECHAZADO = 2;
            public static final int TIPO_RESPUESTA_OK = 1;
            public static final int TIPO_RESPUESTA_ERROR = 0;
			
		}
		
		public final class PersonaConstant {
			public static final String CODIGO_URL_PERSON = "person.codigo.url.getPerson";
			public static final String CODIGO_URL_PERSON_MODIFY = "person.codigo.url.ModifyPerson";
			public static final String CODIGO_NUMID_PERSON = "person.codigo.documentId.key";
			public static final String CODIGO_TYPEID_PERSON = "person.codigo.documentType.key";
			public static final String CODIGO_SOURCE_PERSON = "person.codigo.source.key";
			public static final String CODIGO_PARAMETER_FILTER = "person.codigo.parameter.filter";
			
		}
		
		public final class PropuestaConstant {
			public static final String CODIGO_URL_PROPOSALS_CREATE = "proposals.codigo.url.createExternalFinancingProposal";
			public static final String CODIGO_URL_PROPOSALS_MODIFY = "proposals.codigo.url.modifyExternalFinancingProposal";
			public static final String CODIGO_URL_PROPOSALS_LIST = "proposals.codigo.url.listExternalFinancingProposal";
			
			public static final String CODIGO_PARAM_THIRDPARTPROV_ID = "proposals.codigo.thirdPartyProvider.userId";
			public static final String CODIGO_PARAM_THIRDPARTPROV_ID_LIST = "proposals.codigo.thirdPartyProvider.userId.getList";

			public static final String CODIGO_NUMID_PROPOSALS  = "proposals.codigo.documentNumber";
			public static final String CODIGO_TYPEID_PROPOSALS = "proposals.codigo.documentType";
			public static final String CODIGO_NUMID_PROPOSALS_LIST = "proposals.codigo.documentNumber.getList";
			public static final String CODIGO_TYPEID_PROPOSALS_LIST = "proposals.codigo.documentType.getList";
			
			public static final String CODIGO_FROM_DATE = "proposals.codigo.fromRequestDate";
			public static final String CODIGO_TO_DATE = "proposals.codigo.toRequestDate";
			public static final String CODIGO_CATEGORY = "proposals.codigo.category.type";

			
		}
	}

}
