package com.bbva.findim.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.service.TariffService;
import com.bbva.findim.dom.DetalleBean;
import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.TarifaBean;
import com.bbva.findim.dom.util.PropertyUtil;
import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.web.service.SimulacionPagoService;

@Service
public class SimulacionPagoServiceImpl implements SimulacionPagoService {
	
	private static final Logger LOGGER = LogManager.getLogger(SimulacionPagoService.class);

    @Autowired
	private PropertyUtil prop;
    
    @Autowired
	private SeguridadBbvaService seguridad;
    
    @Autowired
	private TariffService tariffService;
    
	public String fecha() {
		return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}

	public String fechaVencimiento() {
		String fechaFinal;

		Calendar fechaVencimiento = GregorianCalendar.getInstance();
		fechaVencimiento.add(Calendar.MONTH, 1);
		fechaFinal = new SimpleDateFormat("dd/MM/yyyy").format(fechaVencimiento.getTime());
		return fechaFinal;
	}

	public SimulacionBean datosInicialesSimulacion(Integer idPadre, EmpresaBean empresa) {

		SimulacionBean simulacion = new SimulacionBean();
		List<TarifaBean> tarifasBean = null;	
		

		simulacion.setFechaPrestamo(fecha());
		simulacion.setFechaVencimiento(fechaVencimiento());
		tarifasBean = tarifas(empresa);
		simulacion.setListaTarifa(tarifasBean);				
		simulacion.setListaComprobantePago(tipoComprobante(idPadre));
		
		return simulacion;
	}

	public List<TarifaBean> tarifas(EmpresaBean empresa) {
		List<TarifaBean> lstTarifas = new ArrayList<>();
		try {
		    
            String tsecApp = seguridad.generarTSec(3);
            lstTarifas = tariffService.mostrarTarifas(tsecApp, empresa);
            System.out.println("FIN LLAMADO ");

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return lstTarifas;
	}
	

	public List<ParametroBean> tipoComprobante(Integer idPadre){
		
		RestTemplate restTemplate = new RestTemplate();
		List<ParametroBean> listaTipoComprobante  = null;

		String uriServicio=prop.getString("sistema.uriservicio").toString();

		final String uri = uriServicio+ "listaDetalleParametro/"+ idPadre;

		try {

			ParametroBean parametros[] = restTemplate.getForObject(uri, ParametroBean[].class);
			listaTipoComprobante = Arrays.asList(parametros);       

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return listaTipoComprobante;
	}
		

	public String fechaActual() {
		
		return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	}


	public String cargoFijoMaximo(BigDecimal cargoFijo, BigDecimal valorCuota) {
		String resultado = "";
		
		if(cargoFijo==null){
			resultado = "";
		}
		else{
			resultado = ""+redondearDosDecimales(cargoFijo.subtract(valorCuota).doubleValue());
		}
			
		return resultado;
	}
	
	public double redondearDosDecimales(double numero) {
		return Math.rint(numero * 100) / 100;
	}

	public List<String> fechasPago(List<DetalleBean> detalles) {
		int indice = 0;
		List<String> fechas = new ArrayList<>();

		while (indice < detalles.size()) {
			fechas.add(invierteFechasYformato(detalles.get(indice).getFechaPago()));
			indice++;
		}
		return fechas;
	}

	/**
	 * invierteFechasYformato(String date)
	 * invierte fechas de 01/12/2016  a 2016-12-01
	 * 
	 * */	
	public String invierteFechasYformato(String date) {
				
		String sDia = date.substring(0, 2);
		String sMes = date.substring(3, 5);
		String sAnio = date.substring(6, 10);
		
		return sAnio +"-"+ sMes +"-"+ sDia;
	}
	
}
