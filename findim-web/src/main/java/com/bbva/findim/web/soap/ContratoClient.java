package com.bbva.findim.web.soap;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*
import com.bbva.client.exception.WSException;
import com.bbva.client.wsdl.FirmaContrato;
import com.bbva.client.wsdl.FirmarContratosRequest;
import com.bbva.client.wsdl.FirmarContratosResponse;
import com.bbva.client.wsdl.ObjectFactory;
*/
@Service
public class ContratoClient extends WebServiceGatewaySupport{
	
	private Logger LOGGER = LogManager.getLogger(ContratoClient.class);
	/*
	public FirmarContratosResponse sendInfo(String metodo, JAXBElement<String> xml) throws WSException{
		LOGGER.info(metodo);
		
		FirmarContratosRequest request = new FirmarContratosRequest();
		FirmarContratosResponse response = (FirmarContratosResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		/*
		ObjectFactory factory = new ObjectFactory();
		FirmaContrato request = factory.createFirmaContrato();
		//request.set
		
		//LOGGER.info(metodo + " input " + request.getXml().getValue());
		
		FirmarContratosResponse response = (FirmarContratosResponse)getWebServiceTemplate().marshalSendAndReceive(request,new SoapActionCallback("http://tempuri.org/IWSMovilPosicionService/InsertDataXML"));
		LOGGER.info(metodo + " codigo resultado " + response.getCodigoResultado());
		LOGGER.info(metodo + " codigo mensaje " + response.getMensajeResultado());
		return response;
	}*/	
	
}
