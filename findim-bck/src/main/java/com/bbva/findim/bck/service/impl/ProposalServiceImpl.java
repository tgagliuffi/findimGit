package com.bbva.findim.bck.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.helpers.IOUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.bbva.findim.bck.configuration.domain.error.TestErrorHandler;
import com.bbva.findim.bck.domain.communs.ErrorService;
import com.bbva.findim.bck.domain.communs.Status;
import com.bbva.findim.bck.domain.communs.Type;
import com.bbva.findim.bck.domain.communs.Value;
import com.bbva.findim.bck.domain.proposals.Branch;
import com.bbva.findim.bck.domain.proposals.CommercialValue;
import com.bbva.findim.bck.domain.proposals.Delivery;
import com.bbva.findim.bck.domain.proposals.DocumentType;
import com.bbva.findim.bck.domain.proposals.ExternalProduct;
import com.bbva.findim.bck.domain.proposals.ExternalSalesChannel;
import com.bbva.findim.bck.domain.proposals.Holder;
import com.bbva.findim.bck.domain.proposals.IdentityDocument;
import com.bbva.findim.bck.domain.proposals.InitialAmount;
import com.bbva.findim.bck.domain.proposals.Operation;
import com.bbva.findim.bck.domain.proposals.OperationType;
import com.bbva.findim.bck.domain.proposals.ProposalResult;
import com.bbva.findim.bck.domain.proposals.Proposals;
import com.bbva.findim.bck.domain.proposals.ProposalsUpdate;
import com.bbva.findim.bck.domain.proposals.Tariff;
import com.bbva.findim.bck.domain.proposals.ThirdPartyProvider;
import com.bbva.findim.bck.service.ProposalService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.service.TariffService;
import com.bbva.findim.bck.util.ConstantesConection.Parametro.PropuestaConstant;
import com.bbva.findim.bck.util.PropertyUtilCnx;
import com.bbva.findim.bck.util.Util;
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ContratoAltaBean;
import com.bbva.findim.dom.ContratoBean;
import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.dom.RespuestaService;
import com.bbva.findim.dom.common.ConstantResponseMessage;
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.dom.util.DateUtil;
import com.google.gson.Gson;

@Service
public class ProposalServiceImpl extends BaseServiceBackImpl  implements ProposalService {
	
	@Autowired
	private PropertyUtilCnx propertyUtilCnx;
	
	@Autowired
	TariffService tariffService;
	
	@Override
	public ContratoAltaBean altaProposal(String tSec, ContratoAltaBean contratoBean) throws Exception {
		String cadenaRptaError = "";
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		ResponseEntity<String> responseEntity = null;
		contratoBean.setRepuestaService(new RespuestaService());
		try {
			Proposals proposals = new Proposals();
			if(contratoBean!=null){
				proposals = mapperProposalsFromContratoBean(contratoBean);
				String url = propertyUtilCnx.getString(PropuestaConstant.CODIGO_URL_PROPOSALS_CREATE).toString();
				String parametro = propertyUtilCnx.getString(PropuestaConstant.CODIGO_PARAM_THIRDPARTPROV_ID).toString();
				
				MultiValueMap<String, String> params = null;
				if(StringUtils.isNotBlank(contratoBean.getProductoExterno())){
					params = new LinkedMultiValueMap<String, String>();
						params.add(parametro, contratoBean.getProductoExterno());
				}
				
				UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();		
				HttpEntity<Proposals> entity = new HttpEntity<Proposals>(proposals, headers);
				responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, entity, String.class);
				
				if(responseEntity!=null){
					contratoBean.setRepuestaService(new RespuestaService());
					contratoBean.getRepuestaService().setExitoCode(ConstantResponseMessage.CODE_RPTA_OK);
					String[] cdContrato = responseEntity.getHeaders().getLocation().toString().split("/");
					contratoBean.getRepuestaService().setExitoDescription(ConstantResponseMessage.MSJ_OK_ALTAPROP_UNO + contratoBean.getClienteBean().getNumeroDocumento()+ ConstantResponseMessage.MSJ_OK_APTAPROP_DOS +  cdContrato[cdContrato.length-1]);
				}
				return contratoBean;
			}
		} catch (HttpClientErrorException e) {
			LOGGER.info("\t"+ "\t"+"\t" + e.getResponseHeaders().values());
		 	cadenaRptaError = e.getResponseBodyAsString();
		}catch (HttpServerErrorException e2) {
			LOGGER.info("\t"+ "\t"+"\t" + e2.getResponseHeaders().values());
			cadenaRptaError = e2.getResponseBodyAsString();
		}catch (Exception e3) {
			LOGGER.info("\t"+ "\t"+"\t" + "ERROR" , e3);
		}finally {
			ObjectMapper mapper = new ObjectMapper();
			ErrorService error = null;
			try {
				if(!cadenaRptaError.equals("")){
					error = mapper.readValue(new ErrorService().toString(cadenaRptaError), ErrorService.class);
					contratoBean.setRepuestaService(new RespuestaService());
					contratoBean.getRepuestaService().setErrorCode(error.getErrorCode());
					contratoBean.getRepuestaService().setErrorDescription(error.getSystemErrorCause());
					return contratoBean;
				}
			} catch (JsonParseException eA) {
				LOGGER.info("\t"+ "\t"+"\t" + eA.getStackTrace());
				contratoBean.setRepuestaService(new RespuestaService());
				contratoBean.getRepuestaService().setErrorCode(ConstantResponseMessage.CODE_RPTA_ERROR);
				contratoBean.getRepuestaService().setErrorDescription(ConstantResponseMessage.CODE_RPTA_ERROR_DESCRIP);
				return contratoBean;
			} catch (JsonMappingException eB) {
				LOGGER.info("\t"+ "\t"+"\t" + eB.getStackTrace());
				contratoBean.setRepuestaService(new RespuestaService());
				contratoBean.getRepuestaService().setErrorCode(ConstantResponseMessage.CODE_RPTA_ERROR);
				contratoBean.getRepuestaService().setErrorDescription(ConstantResponseMessage.CODE_RPTA_ERROR_DESCRIP);
				return contratoBean;
			} catch (IOException eC) {
				LOGGER.info("\t"+ "\t"+"\t" + eC.getStackTrace());
				contratoBean.setRepuestaService(new RespuestaService());
				contratoBean.getRepuestaService().setErrorCode(ConstantResponseMessage.CODE_RPTA_ERROR);
				contratoBean.getRepuestaService().setErrorDescription(ConstantResponseMessage.CODE_RPTA_ERROR_DESCRIP);
				return contratoBean;
			}
		}
		return contratoBean;
	}

	@Override
	public int updateProposal(String tSec, ContratoBean contratoBean) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		
		try {
			ProposalsUpdate proposals = new ProposalsUpdate();
			if(contratoBean!=null){
				proposals = mapperProposalsUpdateFromContratoBean(contratoBean);
				String url = propertyUtilCnx.getString(PropuestaConstant.CODIGO_URL_PROPOSALS_MODIFY).toString();
			
				if(StringUtils.isNotBlank(contratoBean.getCodigoContrato())){
					url=url+contratoBean.getCodigoContrato();
				}
				
				if(proposals!=null){
					ResponseEntity<Object> responseEntity = invocacionPatch(url.toString(), proposals, tSec,Object.class);
						if(responseEntity.getStatusCode()!=null){
							if(responseEntity.getStatusCode().name().equals("OK")){
								return 1;
							}else{
								restTemplate.setErrorHandler(new TestErrorHandler());
								return 0;
							}
						}else{
							return 0;
						}
				}else{
					return -1; //Datos imcompletos
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			restTemplate.setErrorHandler(new TestErrorHandler());
			return 0;
			}
			
		return 0;
	}

	@Override
	public List<ContratoBean> listarPropuesta(String tSec, String tipoDocumento, String nroDocumento, EmpresaBean empresa) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		List<ContratoBean> lstContratos = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String pToDate = formatter.format(new Date());
		
		try{
			String pFromDate = DateUtil.convertFormatDate("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", empresa.getFechaExpiracion());
			String url = propertyUtilCnx.getString(PropuestaConstant.CODIGO_URL_PROPOSALS_LIST).toString();
			String terceros = propertyUtilCnx.getString(PropuestaConstant.CODIGO_PARAM_THIRDPARTPROV_ID_LIST);
			String categoria = propertyUtilCnx.getString(PropuestaConstant.CODIGO_CATEGORY);
			String tipoDoiKey = propertyUtilCnx.getString(PropuestaConstant.CODIGO_TYPEID_PROPOSALS_LIST).toString();
			String numDoiKey = propertyUtilCnx.getString(PropuestaConstant.CODIGO_NUMID_PROPOSALS_LIST.toString());
			String fromDate = propertyUtilCnx.getString(PropuestaConstant.CODIGO_FROM_DATE);
			String toDate = propertyUtilCnx.getString(PropuestaConstant.CODIGO_TO_DATE);
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
			
			if( StringUtils.isNotBlank(empresa.getCdEmpresa()) ){
				params.add(terceros, empresa.getCdEmpresa()); 
			}if( StringUtils.isNotBlank(empresa.getIndenticador()) ){
				params.add(categoria, empresa.getIndenticador());
			}if( StringUtils.isNotBlank(tipoDocumento) ){
				params.add(tipoDoiKey, tipoDocumento);
			}if( StringUtils.isNotBlank(nroDocumento) ){
				params.add(numDoiKey, nroDocumento);
			}if( StringUtils.isNotBlank(pFromDate) ){
				params.add(fromDate, pFromDate);
			}if( StringUtils.isNotBlank(pToDate) ){
				params.add(toDate, pToDate);
			}
			
			UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();	
			ResponseEntity<ProposalResult> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, new HttpEntity<String>(headers), ProposalResult.class);
			ProposalResult proposalResult = responseEntity.getBody();
			
		
			
			if(proposalResult.getData()!=null){
				
				if(proposalResult.getData().size()>0){
					lstContratos = new ArrayList<ContratoBean>();
					for (int i = 0; i < proposalResult.getData().size(); i++) {
						ContratoBean contrato = new ContratoBean();
						
						contrato.setCodigoContrato(proposalResult.getData().get(i).getId());
						contrato.setFechaCreacion(proposalResult.getData().get(i).getRequestDate());
						contrato.setTipoMoneda(proposalResult.getData().get(i).getCurrency());
						contrato.setNombreArchivo(Util.generarNombreConsolidadoPDF(nroDocumento,proposalResult.getData().get(i).getId()));
						
						if(proposalResult.getData().get(i).getDelivery()!=null){
							contrato.setCorreo(proposalResult.getData().get(i).getDelivery().getEmail());
						}
						
						if(proposalResult.getData().get(i).getInitialAmount()!=null){
							if(proposalResult.getData().get(i).getInitialAmount().getAmount()!=null){
								contrato.setImporteBien(""+proposalResult.getData().get(i).getExternalProduct().getCommercialValue().getAmount().doubleValue());
								contrato.setImportePrestamo(""+proposalResult.getData().get(i).getInitialAmount().getAmount().doubleValue());
								double importeInicial =proposalResult.getData().get(i).getExternalProduct().getCommercialValue().getAmount().doubleValue()-proposalResult.getData().get(i).getInitialAmount().getAmount().doubleValue();
								contrato.setImporteInicial(""+importeInicial);
							}

						if(proposalResult.getData().get(i).getInitialFee()!=null){
							contrato.setNumeroCuotas(""+proposalResult.getData().get(i).getInitialFee().getAmount());
						}
						if(proposalResult.getData().get(i).getTariff()!=null && !proposalResult.getData().get(i).equals("")){
							contrato.setCodigoTarifa(proposalResult.getData().get(i).getTariff().getId());
						}
						
						if(proposalResult.getData().get(i).getRelatedProduct()!=null && !proposalResult.getData().get(i).getRelatedProduct().equals("") ){
							if(proposalResult.getData().get(i).getRelatedProduct().getPercentage()!=null && !proposalResult.getData().get(i).getRelatedProduct().getPercentage().equals(""))
							contrato.setImporteSeguroDesgravamen(proposalResult.getData().get(i).getRelatedProduct().getPercentage());
							if(proposalResult.getData().get(i).getRelatedProduct().getRelatedProductType()!=null){
							contrato.setDescripcionTasaSeguro(proposalResult.getData().get(i).getRelatedProduct().getRelatedProductType().getName());	
							contrato.setValorTasaSeguro(new BigDecimal(proposalResult.getData().get(i).getRelatedProduct().getRelatedProductType().getId()).divide(new BigDecimal(100)).toString());
							}
						}
						
						if(proposalResult.getData().get(i).getHolder()!=null){
							contrato.setClienteContrato(new ClienteBean());
							contrato.getClienteContrato().setNombreCompleto(proposalResult.getData().get(i).getHolder().getName());
							contrato.getClienteContrato().setApellidoPaterno(proposalResult.getData().get(i).getHolder().getLastName());
							contrato.getClienteContrato().setApellidoMaterno(proposalResult.getData().get(i).getHolder().getMotherLastName());
						}else{
							contrato.setClienteContrato(new ClienteBean());
						}
						
						if(proposalResult.getData().get(i).getDelivery()!=null){
							if(proposalResult.getData().get(i).getDelivery().getType()!=null){
								contrato.setTipoEnvio(proposalResult.getData().get(i).getDelivery().getType().getId());
							}
							contrato.getClienteContrato().setCorreoCliente(proposalResult.getData().get(i).getDelivery().getEmail());
						}
						
						if(proposalResult.getData().get(i).getStatus()!=null){
							String estado = determinarEstado(proposalResult.getData().get(i).getStatus().getId(),0);
							contrato.setEstadoContrato(estado);

							if(estado.equals(Estado.FIRMADO.name()))
								contrato.setNombreArchivo(Constantes.DOCUMENTO_ACE + contrato.getCodigoContrato() + Constantes.EXTENSION_PDF);
						}
						if(proposalResult.getData().get(i).getTariff()!=null) {
							contrato.setTasaFinanciamiento(
									tariffService.obtenerTarifa(null,proposalResult.getData().get(i).getTariff().getId(), tSec, empresa).getTasa()
									);
							
						}
						contrato.setFechaDesembolso("");
						lstContratos.add(contrato);
					}
				  }
				}
			}			
		}catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
		return lstContratos;
	}
	
	public ContratoBean obtenerPropuesta(String tSec,String pCategoria, String pProveed, String tipoDocumento, String nroDocumento, String pFromDate, String cdPropuesta) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		ContratoBean contrato  = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String pToDate = formatter.format(new Date());
		
		try{
			pFromDate = DateUtil.convertFormatDate("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", pFromDate);
			String url = propertyUtilCnx.getString(PropuestaConstant.CODIGO_URL_PROPOSALS_LIST).toString();
			String terceros = propertyUtilCnx.getString(PropuestaConstant.CODIGO_PARAM_THIRDPARTPROV_ID_LIST);
			String categoria = propertyUtilCnx.getString(PropuestaConstant.CODIGO_CATEGORY);
			String tipoDoiKey = propertyUtilCnx.getString(PropuestaConstant.CODIGO_TYPEID_PROPOSALS_LIST).toString();
			String numDoiKey = propertyUtilCnx.getString(PropuestaConstant.CODIGO_NUMID_PROPOSALS_LIST.toString());
			String fromDate = propertyUtilCnx.getString(PropuestaConstant.CODIGO_FROM_DATE);
			String toDate = propertyUtilCnx.getString(PropuestaConstant.CODIGO_TO_DATE);
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
			
			if( StringUtils.isNotBlank(pCategoria) ){
				params.add(terceros, pProveed); 
			}if( StringUtils.isNotBlank(pProveed) ){
				params.add(categoria, pCategoria);
			}if( StringUtils.isNotBlank(tipoDocumento) ){
				params.add(tipoDoiKey, tipoDocumento);
			}if( StringUtils.isNotBlank(nroDocumento) ){
				params.add(numDoiKey, nroDocumento);
			}if( StringUtils.isNotBlank(pFromDate) ){
				params.add(fromDate, pFromDate);
			}if( StringUtils.isNotBlank(pToDate) ){
				params.add(toDate, pToDate);
			}
			
			UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();	
			ResponseEntity<ProposalResult> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, new HttpEntity<String>(headers), ProposalResult.class);
			ProposalResult proposalResult = responseEntity.getBody();
			
			if(proposalResult.getData()!=null){
				if(proposalResult.getData().size()>0){
					for (int i = 0; i < proposalResult.getData().size(); i++) {
						 if(proposalResult.getData().get(i).getId().equals(cdPropuesta)) {
							 contrato = new ContratoBean();
								contrato.setCodigoContrato(proposalResult.getData().get(i).getId());
								contrato.setFechaCreacion(proposalResult.getData().get(i).getRequestDate());
								contrato.setTipoMoneda(proposalResult.getData().get(i).getCurrency());
								contrato.setNombreArchivo(Util.generarNombreConsolidadoPDF(nroDocumento,proposalResult.getData().get(i).getId()));
								
								if(proposalResult.getData().get(i).getDelivery()!=null){
									contrato.setCorreo(proposalResult.getData().get(i).getDelivery().getEmail());
								}
								
								if(proposalResult.getData().get(i).getInitialAmount()!=null){
									if(proposalResult.getData().get(i).getInitialAmount().getAmount()!=null){
										
										contrato.setImporteBien(""+proposalResult.getData().get(i).getExternalProduct().getCommercialValue().getAmount().doubleValue());
										contrato.setImportePrestamo(""+proposalResult.getData().get(i).getInitialAmount().getAmount().doubleValue());
										double importeInicial =proposalResult.getData().get(i).getExternalProduct().getCommercialValue().getAmount().doubleValue()-proposalResult.getData().get(i).getInitialAmount().getAmount().doubleValue();
										contrato.setImporteInicial(""+importeInicial);
									}

								if(proposalResult.getData().get(i).getInitialFee()!=null){
									contrato.setNumeroCuotas(""+proposalResult.getData().get(i).getInitialFee().getAmount());
								}
								if(proposalResult.getData().get(i).getTariff()!=null && !proposalResult.getData().get(i).equals("")){
									contrato.setCodigoTarifa(proposalResult.getData().get(i).getTariff().getId());
								}
								
								if(proposalResult.getData().get(i).getRelatedProduct()!=null && !proposalResult.getData().get(i).getRelatedProduct().equals("") ){
									if(proposalResult.getData().get(i).getRelatedProduct().getPercentage()!=null && !proposalResult.getData().get(i).getRelatedProduct().getPercentage().equals(""))
									contrato.setImporteSeguroDesgravamen(proposalResult.getData().get(i).getRelatedProduct().getPercentage());
									if(proposalResult.getData().get(i).getRelatedProduct().getRelatedProductType()!=null){
									contrato.setDescripcionTasaSeguro(proposalResult.getData().get(i).getRelatedProduct().getRelatedProductType().getName());	
//									contrato.setValorTasaSeguro(proposalResult.getData().get(i).getRelatedProduct().getRelatedProductType().getId());
									}
								}
								
								if(proposalResult.getData().get(i).getHolder()!=null){
									contrato.setClienteContrato(new ClienteBean());
									contrato.getClienteContrato().setNombreCompleto(proposalResult.getData().get(i).getHolder().getName());
									contrato.getClienteContrato().setApellidoPaterno(proposalResult.getData().get(i).getHolder().getLastName());
									contrato.getClienteContrato().setApellidoMaterno(proposalResult.getData().get(i).getHolder().getMotherLastName());
								}else{
									contrato.setClienteContrato(new ClienteBean());
								}
								
								if(proposalResult.getData().get(i).getDelivery()!=null){
									if(proposalResult.getData().get(i).getDelivery().getType()!=null){
										contrato.setTipoEnvio(proposalResult.getData().get(i).getDelivery().getType().getId());
									}
									contrato.getClienteContrato().setCorreoCliente(proposalResult.getData().get(i).getDelivery().getEmail());
								}
								
								if(proposalResult.getData().get(i).getStatus()!=null){
									String estado = determinarEstado(proposalResult.getData().get(i).getStatus().getId(),0);
									contrato.setEstadoContrato(estado);

									if(estado.equals(Estado.FIRMADO.name()))
										contrato.setNombreArchivo(Constantes.DOCUMENTO_ACE + contrato.getCodigoContrato() + Constantes.EXTENSION_PDF);
								}
								
							}
						 }
					}
				}
			}			
		}catch (Exception e) {
			LOGGER.error(e);
			return null;
		}
		return contrato;
	}
	
	public ProposalsUpdate mapperProposalsUpdateFromContratoBean(ContratoBean contrato) throws ParseException{
		ProposalsUpdate proposal = new ProposalsUpdate();
		 proposal.setDelivery(new Delivery());
		 proposal.getDelivery().setType(new Type());
			if(contrato.getTipoEnvio()!=null ){
				    proposal.getDelivery().getType().setId(determinarTipoEnvio(contrato.getTipoEnvio()).getId());
			}else{
			    proposal.getDelivery().getType().setId("");
			}
			proposal.getDelivery().getType().setName("");
			
			if(contrato.getCorreo()!=null){
				   proposal.getDelivery().setEmail(contrato.getCorreo());
			}else{
				 proposal.getDelivery().setEmail("");
			}
			proposal.setStatus(new Status());
			if(contrato.getEstadoContrato()!=null && !contrato.getEstadoContrato().equals("")){
				proposal.getStatus().setId(determinarEstado(contrato.getEstadoContrato().replace(" ", "_"), 1).replace(" ", "_"));
			}else{
				return null;
			}
		return proposal;
	}

	public Proposals mapperProposalsFromContratoBean(ContratoAltaBean contrato) throws ParseException{
			Proposals proposal = new Proposals();
			proposal.setHolder(new Holder());
		    proposal.getHolder().setIdentityDocuments(new ArrayList<IdentityDocument>());
		    proposal.getHolder().getIdentityDocuments().add(new IdentityDocument());
		    proposal.getHolder().getIdentityDocuments().get(0).setDocumentNumber(contrato.getClienteBean().getNumeroDocumento());
		    proposal.getHolder().getIdentityDocuments().get(0).setDocumentType(new DocumentType());
		    proposal.getHolder().getIdentityDocuments().get(0).getDocumentType().setId(contrato.getClienteBean().getDescTipoDocumento());
		   
		    proposal.setTariff(new Tariff());
		    proposal.getTariff().setId(contrato.getAddNombreTarifa());
		    proposal.setCurrency(determinarMoneda(contrato.getMonedaCredito()).getId());
		    
		    proposal.setInitialAmount(new InitialAmount());
		    proposal.getInitialAmount().setAmount(contrato.getImportePrestamo().intValue());
		    proposal.getInitialAmount().setCurrency(determinarMoneda(contrato.getMonedaCredito()).getId());
		    
		    proposal.setBillingDay(contrato.getAdddiaFacturacion());//TODO REVISAR
		    proposal.setPaymentDay(contrato.getDiaPago());
		    
		    proposal.setDelivery(new Delivery());
		    proposal.getDelivery().setType(new Type());//ProposalService.TipoEnvio.NINGUNO.getTipoEnvio()
		    proposal.getDelivery().getType().setId(contrato.getCodTipoEnvio()!=null?determinarTipoEnvio(contrato.getCodTipoEnvio()).getId():
		    	ProposalService.TipoEnvio.NINGUNO.getTipoEnvio());
		    proposal.getDelivery().setEmail(contrato.getClienteBean().getCorreoCliente());
		    
		    proposal.setOperation(new Operation());
		    proposal.getOperation().setId(contrato.getNumeroContrato());
		    proposal.getOperation().setOperationType(new OperationType());
		    proposal.getOperation().getOperationType().setId("ALTA"); //TODO CAMBIAR
		    
		    proposal.setExternalProduct(new ExternalProduct());
		    proposal.getExternalProduct().setId(contrato.getTelefonoFinanciado());
		    proposal.getExternalProduct().setCommercialValue(new CommercialValue());
		    proposal.getExternalProduct().getCommercialValue().setAmount(contrato.getValorEquipo().intValue()); //setAmount(contrato.getValorEquipo());
		    proposal.getExternalProduct().getCommercialValue().setCurrency(determinarMoneda(contrato.getMonedaCredito()).getId());// TODO : REVISAR
		    
		    proposal.setThirdPartyProvider(new ThirdPartyProvider());
		    proposal.getThirdPartyProvider().setId(contrato.getProveedorTercero());
		    proposal.getThirdPartyProvider().setExternalSalesChannel(new ExternalSalesChannel());
		    proposal.getThirdPartyProvider().getExternalSalesChannel().setId(contrato.getDescripcionCanal());
		    
		    proposal.setBranch(new Branch());
		    proposal.getBranch().setId(contrato.getCdOficina());
			    
		return proposal;
	}
	
	private Value determinarMoneda(int moneda) {
		Value initialAmount;
		if (moneda == 1) {
			initialAmount = new Value(ProposalService.Moneda.PEN.getMoneda());
		} else {
			initialAmount = new Value(ProposalService.Moneda.USD.getMoneda());
		}
		return initialAmount;
	}
	
	private Value determinarTipoEnvio(String tipoEnvio) {
		Value initialAmount;
		if (tipoEnvio.equals(ProposalService.TipoEnvio.VIRTUAL.name())) {
			initialAmount = new Value(ProposalService.TipoEnvio.VIRTUAL.getTipoEnvio());
		} else if (tipoEnvio.equals(ProposalService.TipoEnvio.AMBOS.name())) {
			initialAmount = new Value(ProposalService.TipoEnvio.AMBOS.getTipoEnvio());
		} else if (tipoEnvio.equals(ProposalService.TipoEnvio.FISICO.name())) {
			initialAmount = new Value(ProposalService.TipoEnvio.FISICO.getTipoEnvio());
		} else {
			initialAmount = new Value(ProposalService.TipoEnvio.NINGUNO.getTipoEnvio());
		}
		return initialAmount;
	}
	
	private String determinarEstado(String estado, int indicador) {// 0 de ServAFront 1 de FrontAServ
		Value estadoValue = null;
		if(indicador==0){
			if (estado.equals(ProposalService.EstadoHost.PENDING_SIGNATURE.name())) {//pendiente
				estadoValue = new Value(ProposalService.EstadoHost.PENDING_SIGNATURE.getEstado());
			}else if(estado.equals(ProposalService.EstadoHost.DISBURSED.name())){//desembolsado
				estadoValue = new Value(ProposalService.EstadoHost.DISBURSED.getEstado());
			}else if(estado.equals(ProposalService.EstadoHost.ANNULLED.name())){//Anulado
				estadoValue = new Value(ProposalService.EstadoHost.ANNULLED.getEstado());
			}else if(estado.equals(ProposalService.EstadoHost.SIGNED.name())){
				estadoValue = new Value(ProposalService.EstadoHost.SIGNED.getEstado());
			}else if(estado.equals(ProposalService.EstadoHost.PENDING_COMPLETION_DATA.name())){
				estadoValue = new Value(ProposalService.EstadoHost.PENDING_COMPLETION_DATA.getEstado());
			}
		}else{
			if (estado.equals(ProposalService.Estado.PENDIENTE_FIRMA.name())) {//pendiente
				estadoValue = new Value(ProposalService.Estado.PENDIENTE_FIRMA.getEstado());
			}else if(estado.equals(ProposalService.Estado.DESEMBOLSADO.name())){//desembolsado
				estadoValue = new Value(ProposalService.Estado.DESEMBOLSADO.getEstado());
			}else if(estado.equals(ProposalService.Estado.ANULADO.name())){//Anulado
				estadoValue = new Value(ProposalService.Estado.ANULADO.getEstado());
			}else if(estado.equals(ProposalService.Estado.FIRMADO.name())){
				estadoValue = new Value(ProposalService.Estado.FIRMADO.getEstado());
			}else if(estado.equals(ProposalService.Estado.EN_TRAMITE.name())){
				estadoValue = new Value(ProposalService.Estado.EN_TRAMITE.getEstado());
			}
		}
		
		if(estadoValue !=null){
			return estadoValue.getId().replace("_", " ");
		}else{
			return "";
		}
	}
	
	
	  private <T> ResponseEntity<T> invocacionPatch(String cadenaUrl, Object payload, String tSec, Class<T> clase)
              throws IOException {
          HttpURLConnection httpURLConnection = null;
          OutputStream os = null;
          InputStream in = null;
          try {
              URL url = new URL(cadenaUrl);

              URLConnection urlConnection = url.openConnection();

              httpURLConnection = (HttpURLConnection) urlConnection;

              httpURLConnection.setDoInput(true);
              httpURLConnection.setDoOutput(true);
              httpURLConnection.setRequestProperty("Content-Type", "application/json");
              httpURLConnection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
              httpURLConnection.setRequestMethod("POST");
              httpURLConnection.setRequestProperty("tsec", tSec);

              Gson gson = new Gson();
              String json = gson.toJson(payload);

              os = httpURLConnection.getOutputStream();
              os.write(json.getBytes());
              os.flush();// hace la llamada

              HttpHeaders responseHeaders = new HttpHeaders();
              for (int i = 0; i < httpURLConnection.getHeaderFields().size(); i++) {
                  String headerName = httpURLConnection.getHeaderFieldKey(i);
                  String headerValue = httpURLConnection.getHeaderField(i);
                  if (headerName != null && headerValue != null) {
                      responseHeaders.set(headerName, headerValue);
                  }
                  if (headerName == null && headerValue == null) {
                      break;
                  }
              }            

              if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                  in = httpURLConnection.getInputStream();
              } else {
                  in = httpURLConnection.getErrorStream();

//                  byte[] bytes = IOUtils.toByteArray(in);
//
//                  throw new HttpClientErrorException(HttpStatus.valueOf(httpURLConnection.getResponseCode()),
//                          "Error al invocar patch", responseHeaders, bytes, Charset.defaultCharset());
              }

              T bodyResponse = null;

              if (in != null) {
                  String strBody = IOUtils.toString(in, Charset.defaultCharset().toString());
                  bodyResponse = gson.fromJson(strBody, clase);
              }

              return new ResponseEntity<T>(bodyResponse, responseHeaders, HttpStatus.OK);

          } finally {
              if (in != null) {
                  in.close();
              }
              if (os != null) {
                  os.close();
              }            
              if (httpURLConnection != null) {
                  httpURLConnection.disconnect();
              }
          }
      }
	  
	  public String oficinizar(ClienteBean cliente) {
		  String oficina = null;
		  if(cliente.getEsCliente()) {
			  
		  }
		  

		  return oficina;
	  }
}
