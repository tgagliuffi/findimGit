package com.bbva.findim.ws.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.FirmaContratoBean;
import com.bbva.findim.dom.RequestFirmaBean;
import com.bbva.findim.dom.ResultadoFirmaBean;
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.ws.service.FirmaContratoService;
import com.bbva.findim.ws.util.Enumeradores.EnumRespuestaSignBox;
import com.google.gson.Gson;

@Service
public class FirmaContratoServiceImpl implements FirmaContratoService {
	
	private static final Logger LOGGER = LogManager.getLogger(FirmaContratoServiceImpl.class);
	private static final Gson GSON = new Gson();

	@Override
	public EnumRespuestaSignBox firmaDocumentoContrato(FirmaContratoBean firmaContratoBean) throws Exception {
		LOGGER.info("firmaDocumentoContrato: tipoDocumento: " + firmaContratoBean.getTipoDocumento()
			+ ", numeroDocumento: " + firmaContratoBean.getNumeroDocumento()
			+ ", codigoContrato: " + firmaContratoBean.getCodigoContrato()
			+ ", tipoContrato: " + firmaContratoBean.getTipoContrato());

		String archivoOrigen = firmaContratoBean.getRutaOrigen() + firmaContratoBean.getTipoContrato() + firmaContratoBean.getCodigoContrato() + Constantes.EXTENSION_PDF;

		String archivoSalida = firmaContratoBean.getRutaSalida() + firmaContratoBean.getTipoContrato() + firmaContratoBean.getCodigoContrato() + Constantes.EXTENSION_PDF;

		String urlString = firmaContratoBean.getRutaSignBoxContract();
		URL url = new URL(urlString);
		URLConnection urlConnection = url.openConnection();

		HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;

		httpUrlConnection.setConnectTimeout(30_000);
		httpUrlConnection.setReadTimeout(30_000);
		httpUrlConnection.setDoInput(true);
		httpUrlConnection.setDoOutput(true);
		httpUrlConnection.setRequestProperty("Content-Type", "application/json");
		httpUrlConnection.setRequestProperty("Accept", "application/json");
		httpUrlConnection.setRequestMethod("POST");

		File originalFile = new File(archivoOrigen);
		String encodedBase64 = null;
		try (FileInputStream fileInputStreamReader = new FileInputStream(originalFile)) {
			byte[] bytes = new byte[(int) originalFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
			fileInputStreamReader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		RequestFirmaBean requestBean = new RequestFirmaBean();
		requestBean.setContrato(encodedBase64);
		requestBean.setTipoDocumento(String.valueOf(firmaContratoBean.getTipoDocumento()));
		requestBean.setNumeroDocumento(firmaContratoBean.getNumeroDocumento());
		requestBean.setNumeroSolicitud(String.valueOf(firmaContratoBean.getCodigoContrato()));
		requestBean.setTipoContrato(Constantes.PREFIJO_BCCF + firmaContratoBean.getTipoContrato());

		String input = GSON.toJson(requestBean);

		LOGGER.debug("input: " + input);

		OutputStream os = httpUrlConnection.getOutputStream();
		os.write(input.getBytes());
		os.flush();

		if (httpUrlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : " + httpUrlConnection.getResponseCode());
		} else {
			LOGGER.info("Success ----> " + httpUrlConnection.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((httpUrlConnection.getInputStream())));
		String output;
		StringBuilder sb = new StringBuilder();
		LOGGER.debug("Output from Server....");
		while ((output = br.readLine()) != null) {
			LOGGER.debug(output);
			sb.append(output + '\n');
		}
		httpUrlConnection.disconnect();

		LOGGER.debug("output.size: " + sb.length());
		ResultadoFirmaBean resultadoFirma = GSON.fromJson(sb.toString(), ResultadoFirmaBean.class);

		if (!EnumRespuestaSignBox.CORRECTO.getCodigo().equals(resultadoFirma.getEstado())) {
			return EnumRespuestaSignBox.ERROR;
		}

		File destinoFile = new File(archivoSalida);

		byte[] decodeBase64 = Base64.decodeBase64(resultadoFirma.getDocumento().getBytes());

		FileOutputStream fileOuputStream = new FileOutputStream(destinoFile);
		fileOuputStream.write(decodeBase64);
		fileOuputStream.close();

		return EnumRespuestaSignBox.CORRECTO;
	}

}
