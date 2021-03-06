package com.bbva.findim.web.controller;

import java.math.BigDecimal;
//import java.text.DecimalFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bbva.findim.bck.service.LoanService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.service.TariffService;
import com.bbva.findim.dom.CabeceraBean;
import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.TarifaBean;
import com.bbva.findim.web.common.Constantes;
import com.bbva.findim.dom.util.PropertyUtil;
import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.web.service.SimulacionPagoService;
import com.google.gson.Gson;

@Controller
public class SimulacionController {

	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

	@Autowired
	private SimulacionPagoService simulacionPagoService;
	
	@Autowired
	private TariffService tariffService;
	
	@Autowired 
	private LoanService loanService;
	
	@Autowired
	private PropertyUtil prop;
	
	@Autowired
	private SeguridadBbvaService seguridad;

	@RequestMapping(value = "/simulacionDelPago1", method = RequestMethod.GET, produces = Constantes.APPLICATION_JSON_UTF_8_VALUE)
	@ResponseBody
	public String  simulacionDelPago(Model model,	
			@RequestParam(value = "cargoFijoMaximo", required = false) BigDecimal cargoFijoMaximo,
			@RequestParam(value = "tipoMoneda", required = false) String tipoMoneda,
			@RequestParam(value = "valorEquipo", required = false) BigDecimal valorEquipo,
			@RequestParam(value = "tarifaFinanciamiento", required = false) String tarifaFinanciamiento,
			@RequestParam(value = "porcentajeCuotaInicial", required = false) BigDecimal porcentajeCuotaInicial,
			@RequestParam(value = "cuotaInicial", required = false) BigDecimal cuotaInicial,
			@RequestParam(value = "saldoAFinanciar", required = false) BigDecimal saldoAFinanciar,
			@RequestParam(value = "metodoEnvio", required = false) String metodoEnvio,
			HttpServletRequest request, HttpServletResponse response) {	

		Gson gson = new Gson();
		String gsonString = null;
		SimulacionBean simulacion = null;
		EmpresaBean empresa  = null;

		try {	
			if(request.getSession().getAttribute("empresa")!=null){
				empresa = (EmpresaBean) request.getSession().getAttribute("empresa");
				simulacion = mapper(tipoMoneda, valorEquipo, 
						tariffService.obtenerTarifa(request.getSession().getAttribute("lstTarifa"), tarifaFinanciamiento, null,empresa), porcentajeCuotaInicial,
						cuotaInicial, saldoAFinanciar, metodoEnvio);
		        
				String tSec = seguridad.generarTSec(2);
				simulacion = loanService.simularPrestamo(simulacion, tSec);
				String tcea =simulacion.getCabecera().getTcea().toString();
				simulacion.getCabecera().setCargoFijoMaximo(simulacionPagoService.cargoFijoMaximo(cargoFijoMaximo, new BigDecimal(simulacion.getDetalle().get(0).getValorCuota())));


				if (tcea == null) {
					tcea = Constantes.MENSAJE_NO_DATA;
		        	simulacion.getCabecera().setTcea(tcea);
				} 

		    	simulacion.setCodigoMensaje(1);
		        simulacion.setTipoRespuesta(1);
		        simulacion.setTipoError(0);
		        gson = new Gson();
				gsonString = gson.toJson(simulacion);
			}
			
			
		}catch(Exception e){
			LOGGER.error(e.getMessage(),e);
		}	

		return gsonString;
	}

	@RequestMapping(value = "/datosInicialesSimulacion", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String  datosInicialesSimulacion(Model model, 									
			HttpServletRequest request, HttpServletResponse response) {
		
		Gson gson = new Gson();
		String gsonString = null;		
		SimulacionBean datosInicialesSimulacion=null;
		Integer idTipoEnvio = Integer.parseInt(prop.getString("datosComplementarios.idTipoEnvio").toString());
		EmpresaBean empresa  = null;
				
		try {
			if(request.getSession().getAttribute("empresa")!=null){
				empresa = (EmpresaBean) request.getSession().getAttribute("empresa");
				
				datosInicialesSimulacion = simulacionPagoService.datosInicialesSimulacion(idTipoEnvio, empresa);
				if(datosInicialesSimulacion.getListaTarifa().size()>0)
					request.getSession().setAttribute("lstTarifa", datosInicialesSimulacion.getListaTarifa());
				
				gson = new Gson();
				gsonString = gson.toJson(datosInicialesSimulacion);	
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}	
		return gsonString;
	}
	
	@RequestMapping("simulacionImpresion")
	public ModelAndView simulacionImpresion(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("simulacionImpresion");
		String objetoContenedor = request.getParameter("objetoContenedor");
		LOGGER.debug("objetoContenedor: " + objetoContenedor);
		mv.addObject("objetoContenedor", objetoContenedor);
		return mv;
	}
	
	@SuppressWarnings("deprecation")
	public  SimulacionBean mapper(String tipoMoneda, BigDecimal valorEquipo, TarifaBean tarifaFinanciamiento,
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