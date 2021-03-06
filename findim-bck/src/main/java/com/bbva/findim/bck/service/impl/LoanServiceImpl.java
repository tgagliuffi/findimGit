package com.bbva.findim.bck.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.bbva.findim.bck.domain.communs.Value;
import com.bbva.findim.bck.domain.loan.InterestPeriodicity;
import com.bbva.findim.bck.domain.loan.Loan;
import com.bbva.findim.bck.domain.loan.LoanResult;
import com.bbva.findim.bck.domain.loanInput.InsuranceCoverage;
import com.bbva.findim.bck.domain.loanInput.Participant;
import com.bbva.findim.bck.domain.loanInput.SimulateInput;
import com.bbva.findim.bck.service.LoanService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.util.PropertyUtilCnx;
import com.bbva.findim.bck.util.UtilDate;
import com.bbva.findim.bck.util.ConstantesConection.Parametro.PrestamoConstant;
import com.bbva.findim.dom.CabeceraBean;
import com.bbva.findim.dom.DetalleBean;
import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.TotalesBean;
import com.bbva.findim.dom.util.NumberUtil;
import com.bbva.findim.dom.CondicionBean;
import com.bbva.findim.dom.PrestamoBean;

@Service("loanService")
public class LoanServiceImpl extends BaseServiceBackImpl  implements LoanService{
	@Autowired
	private PropertyUtilCnx propertyUtilCnx;
	
	@Override
	public PrestamoBean obtenerPrestamo(PrestamoBean prestamoBean, String tSec)throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		String url = propertyUtilCnx.getString(PrestamoConstant.CODIGO_URL_LOAN).toString();
		String numKey = propertyUtilCnx.getString(PrestamoConstant.CODIGO_NUMID_LOAN).toString();
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		if(StringUtils.isNotBlank(numKey)){
			params.add("", prestamoBean.getPrestamoId());
		}
		
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url+prestamoBean.getPrestamoId()).build();	
		ResponseEntity<Loan> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, new HttpEntity<String>(headers), Loan.class);
		
		Loan loanResult = responseEntity.getBody();
			if(loanResult!=null){ //TODO: VALIDAR SI EXISTE CAMPO DE ERROR
				
				prestamoBean.setPrestamoId(loanResult.getId());
				
					if(loanResult.getProduct()!=null){
						prestamoBean.setIdProducto(loanResult.getProduct().getId());
						prestamoBean.setNbProducto(loanResult.getProduct().getName());
						if(loanResult.getProduct().getCommercialClassifications()!=null){
							prestamoBean.setIdClasifProducto(loanResult.getProduct().getCommercialClassifications().get(0).getId());
//							prestamoBean.setNbClasifProducto(loanResult.getProduct().getCommercialClassifications().get(0).get));
						}
					}
					if(loanResult.getParticipants()!=null){
						for (int i = 0; i < loanResult.getParticipants().size(); i++) {
							prestamoBean.setNbCliente(loanResult.getParticipants().get(i).getName() );
							prestamoBean.setApPatCliente(loanResult.getParticipants().get(i).getLastName());
							prestamoBean.setApMatCliente(loanResult.getParticipants().get(i).getMothersLastName());
						}
					}
					if(loanResult.getCurrency()!=null){
//						prestamoBean.setTipoMoneda(loanResult.getCurrency().getId());
						prestamoBean.setMoneda(loanResult.getCurrency().getName());
					}
					if(loanResult.getInterests()!=null){
						for (int i = 0; i < loanResult.getInterests().size(); i++) {
							if(loanResult.getInterests().get(i).getType().equals("01"))//annualPercentageRate
								prestamoBean.setImpTCEA(NumberUtil.formaterBigDecimal(loanResult.getInterests().get(i).getPercentage(), null, null,null));
							if(loanResult.getInterests().get(i).getType().equals("02"))//nominalInterestRate 
								prestamoBean.setImpTEA(NumberUtil.formaterBigDecimal(loanResult.getInterests().get(i).getPercentage(), null, null,null));
							if(loanResult.getInterests().get(i).getType().equals("07"))//int moratorio
								prestamoBean.setImpIntMoratorio(NumberUtil.formaterBigDecimal(loanResult.getInterests().get(i).getPercentage(), null, null,null));
					}
					if(loanResult.getQualification()!=null){
							prestamoBean.setNbCalification(loanResult.getQualification().getResult());
					}
					if(loanResult.getLoanManagementDates()!=null){
						for (int i = 0; i < loanResult.getLoanManagementDates().size(); i++) {
							if(loanResult.getLoanManagementDates().get(i).getType().getName().equals("APROBACION")){
								prestamoBean.setFhDesembolso(UtilDate.convertStringToJUtilDate(loanResult.getLoanManagementDates().get(i).getDate(),"yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
							}else if(loanResult.getLoanManagementDates().get(i).getType().getName().equals("DESEMBOLSO")){
								prestamoBean.setFhAprobacion(UtilDate.convertStringToJUtilDate(loanResult.getLoanManagementDates().get(i).getDate(),"yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
							}
						}
					}
					prestamoBean.setNuDiaPagoPrest(loanResult.getPaymentDay());
					prestamoBean.setFhSolicitudPrestamo(UtilDate.convertStringToJUtilDate(loanResult.getRequestDate(),"yyyy-MM-dd"));
				
//					if(loanResult.getIndicators()!=null){
//						prestamoBean.setIsJugador(loanResult.getIndicators().getIsBadPayer());;
//						prestamoBean.setIsRefinanciado(loanResult.getIndicators().getIsRefinanced());;
//						prestamoBean.setIsSeguro(loanResult.getIndicators().getIsInsurance());;
//						prestamoBean.setIsVariableInteres(loanResult.getIndicators().getIsVariableInterest());
//					}
				}
			
	}
		return prestamoBean;
	}
	
	@Override
	public SimulacionBean simularPrestamo(SimulacionBean simulacion, String tSec) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		
		SimulateInput loanBack = new SimulateInput();
		loanBack = mapperSimulacionBeanToLoan(simulacion);
		
		String url = propertyUtilCnx.getString(PrestamoConstant.CODIGO_URL_LOAN_SIMULATE).toString();
		HttpEntity<SimulateInput> entity = new HttpEntity<SimulateInput>(loanBack,headers);
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).build();	
		ResponseEntity<LoanResult> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, entity, LoanResult.class);
	
		LoanResult loanResult = responseEntity.getBody();
		SimulacionBean simulacionFromt = null;
		Double seguro = 0.00;
		
		if(loanResult!=null && !loanResult.equals("")){
			simulacionFromt = new SimulacionBean();
			simulacionFromt.setCdSubProducto(simulacion.getCdSubProducto());
			simulacionFromt.setCabecera(new CabeceraBean());
			simulacionFromt.getCabecera().setCodigoTarifa(simulacion.getCabecera().getCodigoTarifa());
			simulacionFromt.getCabecera().setNumeroCuotas(simulacion.getCabecera().getNumeroCuotas());
			simulacionFromt.getCabecera().setValorEquipoS(simulacion.getCabecera().getValorEquipo().toString());
			simulacionFromt.getCabecera().setTea(simulacion.getCabecera().getTea());
			simulacionFromt.getCabecera().setMoneda(loanResult.getLoan().getCurrency().getName());
			if(loanResult.getLoan().getProduct()!=null){
				simulacionFromt.setIdProducto(loanResult.getLoan().getProduct().getId());
				simulacionFromt.setNbProducto(loanResult.getLoan().getProduct().getName());
				if(loanResult.getLoan().getProduct().getCommercialClassifications()!=null){
					if(loanResult.getLoan().getProduct().getCommercialClassifications().size()>0)
						for (int i = 0; i < loanResult.getLoan().getProduct().getCommercialClassifications().size(); i++) {
							simulacionFromt.setCdSubProducto(loanResult.getLoan().getProduct().getCommercialClassifications().get(i).getId());
						}
				}
				
			}
			
			if(loanResult.getLoan().getConditions()!=null){
				if(loanResult.getLoan().getConditions().getItems().size()>0){
					simulacionFromt.setLstCondiciones(new ArrayList<CondicionBean>());
					for (int i = 0; i < loanResult.getLoan().getConditions().getItems().size(); i++) {
						CondicionBean condicion = new CondicionBean();
						condicion.setNbCondicion(loanResult.getLoan().getConditions().getItems().get(i).getName().toString());
						if(loanResult.getLoan().getConditions().getItems().get(i).getValues()!=null){
							condicion.setDtCondicion(new ArrayList<String>());
							for (int j = 0; j < loanResult.getLoan().getConditions().getItems().get(i).getValues().size(); j++) {
								condicion.getDtCondicion().add(loanResult.getLoan().getConditions().getItems().get(i).getValues().get(j).getValue());
							}
						}
					}
				}
			}
			//cronograma cabecera
			if(loanResult.getLoan().getAmortizationSchedule()!=null){
				if(loanResult.getLoan().getAmortizationSchedule().getCapitalTotalAmount().getAmount()!=null)
				simulacionFromt.getCabecera().setSaldoFinanciar(new BigDecimal(loanResult.getLoan().getAmortizationSchedule().getCapitalTotalAmount().getAmount()));
				
				if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments()!=null){
					if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().size()>0){
						simulacionFromt.setDetalle(new ArrayList<DetalleBean>());
						for (int i = 0; i < loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().size(); i++) {
							DetalleBean cuota = new DetalleBean();
							cuota.setFechaVencimiento(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getDueDate());
							
							if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getCapitalAmount()!=null){
								cuota.setAmortizacion(NumberUtil.formaterString(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getCapitalAmount().getAmount(), null, null,null));
							}
							
							if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getInterestAmount()!=null){
								cuota.setInteresMensual(NumberUtil.formaterString(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getInterestAmount().getAmount(), null, null,null));
							}
							
							if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getPendingAmount()!=null){
								cuota.setSaldoCapital(NumberUtil.formaterDouble(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getPendingAmount().getAmount(), null, null, null));
								cuota.setSaldoAntesS(NumberUtil.formaterString(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getPendingAmount().getAmount(), null, null,null));
							}
							
							if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getExpensesAmount()!=null){
								if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getExpensesAmount().getDetailedExpenses().size()>0){
									for (int j = 0; j < loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getExpensesAmount().getDetailedExpenses().size(); j++) {
										if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getExpensesAmount().getDetailedExpenses()!=null){
											for (int j2 = 0; j2 < loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getExpensesAmount().getDetailedExpenses().size(); j2++) {
												if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getExpensesAmount().getDetailedExpenses().get(j2).getType()!=null){
													if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getExpensesAmount().getDetailedExpenses().get(j2).getType().getId().equals("01")){//GASTOS
														if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getExpensesAmount().getDetailedExpenses().get(j2).getAmount()!=null){
															cuota.setComisionEnvioPago(NumberUtil.formaterDouble(null,null,null,loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getExpensesAmount().getDetailedExpenses().get(j2).getAmount().getAmount()));
														}
													}
												}
											}
										}
										
									}
								}
							}
							if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getSubsidizedAmount()!=null){
									cuota.setSeguro(NumberUtil.formaterDouble(null, null, null, loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getSubsidizedAmount().getAmount()));
							}
							if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getAmount()!=null)
								cuota.setCuotaTotal(NumberUtil.formaterDouble(null, null, null, loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getAmount().getAmount()));
							
							if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getDueDate()!=null){
								cuota.setFechaPago(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getDueDate()!=null?
										UtilDate.changeFormatString(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getDueDate(), "dd-MM-YYYY"):"");
							}
							
							if(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getId()!=null){
								cuota.setNumeroCuota(Integer.parseInt(loanResult.getLoan().getAmortizationSchedule().getDetailedAmortizationPayments().get(i).getId().toString()));
							}
							simulacionFromt.getDetalle().add(cuota);
						}
						
					}
				}
			
			}
			if(loanResult.getLoan().getInterests()!=null && loanResult.getLoan().getInterests().size()>0){
				for (int i = 0; i < loanResult.getLoan().getInterests().size(); i++) {
					if(loanResult.getLoan().getInterests().get(i).getType()!=null){
						if(loanResult.getLoan().getInterests().get(i).getType().getId().equals("09"))
							simulacionFromt.getCabecera().setTcea(NumberUtil.formaterString(loanResult.getLoan().getInterests().get(i).getPercentage(), null, null, null)
							);
					}
				}
			}
			
			if(loanResult.getLoan().getCommercialValueAmount()!=null){
				simulacionFromt.getCabecera().setValorEquipo(NumberUtil.formaterBigDecimal(null, null, null, loanResult.getLoan().getCommercialValueAmount().getAmount()));
			}
			if(simulacion.getCabecera().getSeguroDesgravamen()!=null && !simulacion.getCabecera().getSeguroDesgravamen().equals("")) {
				 seguro = Double.parseDouble(simulacion.getCabecera().getSeguroDesgravamen())/100;
				 simulacionFromt.getCabecera().setSeguroDesgravamen(seguro.toString());
			}
			
			simulacionFromt.setTotales(new TotalesBean());
			if(loanResult.getLoan().getPaymentTotalAmount()!=null){//Total a Pagar
				simulacionFromt.getTotales().setTotalCuotaTotal(NumberUtil.formaterString(null, null, null,loanResult.getLoan().getPaymentTotalAmount().getAmount()));
			}
			if(loanResult.getLoan().getSubsidizedTotalAmount()!=null){//SeguroDegravamen
				simulacionFromt.getTotales().setTotalSeguroDesgravamen(NumberUtil.formaterString(null, null, null,loanResult.getLoan().getSubsidizedTotalAmount().getAmount()));
			}
			if(loanResult.getLoan().getInterestTotalAmount()!=null){//Total interes
				simulacionFromt.getTotales().setTotalIntereses(NumberUtil.formaterString(null, null, null,loanResult.getLoan().getInterestTotalAmount().getAmount()));
			}
			if(loanResult.getLoan().getExpensesTotalAmount()!=null){//Total Gastos
				simulacionFromt.getTotales().setComisionEnvio(NumberUtil.formaterString(null, null, null,loanResult.getLoan().getExpensesTotalAmount().getAmount()));
			}
			if(loanResult.getLoan().getAwardedAmount()!=null){//Total Gastos
				simulacionFromt.getTotales().setTotalSaldoCapital(NumberUtil.formaterString(null, null, null, loanResult.getLoan().getAwardedAmount().getAmount()));
			}
		}
		return simulacionFromt;
	}
	
	public SimulateInput mapperSimulacionBeanToLoan(SimulacionBean simulacionBean) throws ParseException{
		SimulateInput simulateLoan = new SimulateInput();
		if(simulacionBean!=null){
			if(simulacionBean.getCabecera()!=null){
				simulateLoan.setPaymentDay(simulacionBean.getCabecera().getDiaPago());
				simulateLoan.setInterestPeriodicity(new InterestPeriodicity());
				simulateLoan.getInterestPeriodicity().setTimeUnit(new com.bbva.findim.bck.domain.loan.TimeUnit());
				
				simulateLoan.setProduct(new com.bbva.findim.bck.domain.loanInput.Product());
				simulateLoan.getProduct().setCommercialClassifications(new ArrayList<com.bbva.findim.bck.domain.loanInput.CommercialClassification>());
				simulateLoan.getProduct().getCommercialClassifications().add(new com.bbva.findim.bck.domain.loanInput.CommercialClassification());
				simulateLoan.getProduct().getCommercialClassifications().get(0).setId(simulacionBean.getCdSubProducto());
				
				simulateLoan.getProduct().setPurposes(new ArrayList<com.bbva.findim.bck.domain.loanInput.Purpose>());
				simulateLoan.getProduct().getPurposes().add(new com.bbva.findim.bck.domain.loanInput.Purpose());
				simulateLoan.getProduct().getPurposes().get(0).setId(propertyUtilCnx.getString(PrestamoConstant.CODIGO_PARAMETER_PRODUCT_PURSPOSES));
				
				simulateLoan.setCommercialValueAmount(new com.bbva.findim.bck.domain.loanInput.CommercialValueAmount());
				simulateLoan.getCommercialValueAmount().setAmount(simulacionBean.getCabecera().getValorEquipo().intValue());
				simulateLoan.getCommercialValueAmount().setCurrency(simulacionBean.getCabecera().getMoneda().toString());
				
				simulateLoan.setAwardedAmount(new com.bbva.findim.bck.domain.loanInput.AwardedAmount());
				simulateLoan.getAwardedAmount().setAmount(simulacionBean.getCabecera().getSaldoFinanciar().intValue());
				simulateLoan.getAwardedAmount().setCurrency(simulacionBean.getCabecera().getInMonedaEquipo());
				simulateLoan.setTerm(new com.bbva.findim.bck.domain.loanInput.Term());
				simulateLoan.getTerm().setAmount(simulacionBean.getCabecera().getNumeroCuotas());
				simulateLoan.getTerm().setTimeUnit(new com.bbva.findim.bck.domain.loanInput.TimeUnit());
				simulateLoan.getTerm().getTimeUnit().setId(propertyUtilCnx.getString(PrestamoConstant.CODIGO_PARAMETER_TERM_TIMEUNIT)); // TODO : ACTUALIZAR VALOR - MESES 
				simulateLoan.setIndicators(new com.bbva.findim.bck.domain.loanInput.Indicators());
				simulateLoan.getIndicators().setInsuranceCoverage(new InsuranceCoverage());
				simulateLoan.getIndicators().getInsuranceCoverage().setId(propertyUtilCnx.getString(PrestamoConstant.CODIGO_PARAMETER_IND_INSURANCECOVERAGE));//VER VALOR DE SETEO
				simulateLoan.setInterests(new ArrayList<com.bbva.findim.bck.domain.loanInput.Interest>());
				simulateLoan.getInterests().add(new com.bbva.findim.bck.domain.loanInput.Interest());
				simulateLoan.getInterests().get(0).setPercentage(null);
				simulateLoan.setRelatedContracts(new ArrayList<com.bbva.findim.bck.domain.loanInput.RelatedContract>());
				simulateLoan.getRelatedContracts().add(new com.bbva.findim.bck.domain.loanInput.RelatedContract());
				simulateLoan.getRelatedContracts().get(0).setProduct(new com.bbva.findim.bck.domain.loanInput.Product());
				simulateLoan.getRelatedContracts().get(0).getProduct().setId(propertyUtilCnx.getString(PrestamoConstant.CODIGO_PARAMETER_RELATEDCONTRACTS));
				simulateLoan.setConditions(new com.bbva.findim.bck.domain.loanInput.Conditions());
				simulateLoan.getConditions().setItems(new ArrayList<com.bbva.findim.bck.domain.loanInput.Item>());
				simulateLoan.getConditions().getItems().add(0, new com.bbva.findim.bck.domain.loanInput.Item());
				simulateLoan.getConditions().getItems().add(1, new com.bbva.findim.bck.domain.loanInput.Item());
				simulateLoan.getConditions().getItems().get(1).setValues(new ArrayList<Value>());
				simulateLoan.getConditions().getItems().add(2, new com.bbva.findim.bck.domain.loanInput.Item());
				simulateLoan.getConditions().getItems().add(3, new com.bbva.findim.bck.domain.loanInput.Item());
				simulateLoan.getConditions().getItems().get(3).setValues(new ArrayList<Value>());
				simulateLoan.getConditions().getItems().get(3).getValues().add(new Value() );
				
				String[] codigosEnvioFisico = propertyUtilCnx.getString(PrestamoConstant.CODIGO_PARAMETER_ENVIO_FISICO).split("-");
				
				for (int i = 0; i < codigosEnvioFisico.length; i++) {
					if(codigosEnvioFisico[i].toString().equals(String.valueOf(simulacionBean.getCabecera().getMetodoEnvio()))){
						simulateLoan.getConditions().getItems().get(3).getValues().get(0).setIsSelected(false);
					}else{
						simulateLoan.getConditions().getItems().get(3).getValues().get(0).setIsSelected(true);

					}
				}
					
				simulateLoan.getConditions().getItems().add(4, new com.bbva.findim.bck.domain.loanInput.Item());
				
				simulateLoan.setParticipants(new ArrayList<com.bbva.findim.bck.domain.loanInput.Participant>());
				simulateLoan.getParticipants().add(new Participant());
				if(simulacionBean.getFechaNacimientoCliente()==null)
					simulateLoan.getParticipants().get(0).setBirthDate(propertyUtilCnx.getString(PrestamoConstant.CODIGO_PARAMETER_PART_BIRTHDATE));
				else
					simulateLoan.getParticipants().get(0).setBirthDate(simulacionBean.getFechaNacimientoCliente());	
				simulateLoan.setRequestDate(UtilDate.convertJUtilDateToString(new Date(), "yyyy-MM-dd"));
				
				
			}
		}
		return simulateLoan;
	}

}