package com.bbva.findim.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbva.findim.dom.BioAuthData;
import com.bbva.findim.dom.BioMatchBean;
import com.bbva.findim.dom.BiometriaBean;
import com.bbva.findim.dom.BiometriaValidaMaxIntentosBean;
import com.bbva.findim.dom.ResultadoBiometriaBean;
import com.bbva.findim.web.util.ConvertUtil;
import com.bbva.findim.dom.util.PropertyUtil;
import com.bbva.findim.web.exception.ForbiddenException;
import com.bbva.findim.web.service.BiometriaRestService;
import com.bbva.findim.web.util.AuthExtBean;
import com.bbva.findim.web.util.Tools;

@Controller
public class BiometriaController {

	private static final Logger LOGGER = LogManager.getLogger(BiometriaController.class);

	@Autowired
	private BiometriaRestService biometriaRestService;

	@Autowired
	private PropertyUtil prop;

	@RequestMapping("handshakeBioMatch")
	@ResponseBody
	public BioMatchBean handshakeBioMatch(
			@RequestParam("token") String token,
			@RequestParam("numeroDocumento") String numeroDocumento,
			@RequestParam("codigoProceso") Integer codigoProceso) throws Exception {

		// No permite activar el BioMatch por JavaScript
		boolean bioMatchActivo = "1".equals(prop.getString("bio.match.activo"));
		if (!bioMatchActivo) {
			throw new ForbiddenException();
		}
		LOGGER.info("Cifrando informacion de biometria: numDocumento: " + numeroDocumento + ", " + codigoProceso);

		AuthExtBean ae = new AuthExtBean();
		String encryptedToken = ae.encryptData(token);

		Long numeroSolicitud = biometriaRestService.registrar(numeroDocumento, codigoProceso);

		BioMatchBean bioMatchBean = new BioMatchBean();
		bioMatchBean.setEncryptedToken(encryptedToken);
		bioMatchBean.setExtraData(getExtraDataBioMatch(String.valueOf(numeroSolicitud)));
		bioMatchBean.setNumeroSolicitud(String.valueOf(numeroSolicitud));

		return bioMatchBean;
	}

	private String getExtraDataBioMatch(String numeroSolicitud) throws Exception {
		String url = prop.getString("bio.match.url");
		String user = ConvertUtil.decodeBase64(prop.getString("bio.match.usuario"));
		String pass = ConvertUtil.decodeBase64(prop.getString("bio.match.password"));
		String tipoDocumentoUsuario = prop.getString("bio.match.tipo.documento.usuario");
		String numeroDocumentoUsuario = prop.getString("bio.match.numero.documento.usuario");
		String codigoApp = prop.getString("bio.match.codigo.aplicacion");
		String numeroCliente = prop.getString("bio.match.numero.cliente");

		AuthExtBean ab = new AuthExtBean();

		return ab.encryptData(numeroSolicitud + Tools.TAG
				+ url + Tools.TAG
				+ user + Tools.TAG
				+ pass + Tools.TAG
				+ tipoDocumentoUsuario + Tools.TAG
				+ numeroDocumentoUsuario + Tools.TAG
				+ codigoApp + Tools.TAG
				+ numeroCliente);
	}

	@RequestMapping("biometria/resultado")
	@ResponseBody
	public ResultadoBiometriaBean obtenerResultado(@RequestParam("data") String data) throws Exception {
		AuthExtBean ab = new AuthExtBean();
		BioAuthData bioAuthData = ab.decryptDecodeData(data);

		BiometriaBean biometriaBean = parseToBiometriaBean(bioAuthData);
		try {
			String numeroDocumento = biometriaRestService.actualizar(biometriaBean);
			biometriaBean.setNumeroDocumento(numeroDocumento);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return biometriaRestService.obtenerResultado(biometriaBean);
	}

	@RequestMapping("biometria/validarMaximoIntentos/{numeroDocumento}")
	@ResponseBody
	public BiometriaValidaMaxIntentosBean validarMaximoIntentos(@PathVariable("numeroDocumento") String numeroDocumento) {
		return biometriaRestService.validarMaximoIntentos(numeroDocumento);
	}

	private static BiometriaBean parseToBiometriaBean(BioAuthData bioAuthData) {
		BiometriaBean bean = new BiometriaBean();
		bean.setApellidoMaterno(bioAuthData.getMaterno());
		bean.setApellidoPaterno(bioAuthData.getPaterno());
		bean.setCodError(bioAuthData.getCoError());
		bean.setCodErrorReniec(bioAuthData.getCoErrorReniec());
		bean.setCodPc(bioAuthData.getIdPC());
		bean.setCodRestriccion(bioAuthData.getBioInRestriccion());
		bean.setCodTransaccion(bioAuthData.getIdTxn());
		bean.setCodVigencia(bioAuthData.getBioInVigencia());
		bean.setDescError(bioAuthData.getDeError());
		bean.setDescErrorReniec(bioAuthData.getDeErrorReniec());
		bean.setDescRestriccion(bioAuthData.getBioDesInRestriccion());
		bean.setDescVigencia(bioAuthData.getBioDesInVigencia());
		bean.setNombre(bioAuthData.getNombre());
		bean.setNumeroSolicitud(ConvertUtil.convertToLong(bioAuthData.getNuSolicitud()));
		bean.setMac(bioAuthData.getMac());
		bean.setIp(bioAuthData.getIp());
		return bean;
	}

}
