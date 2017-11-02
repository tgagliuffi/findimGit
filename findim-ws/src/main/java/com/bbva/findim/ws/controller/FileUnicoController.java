package com.bbva.findim.ws.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbva.findim.dom.FileUnicoRest;
import com.bbva.findim.dom.ParametroBean;

@Controller
public class FileUnicoController {

	private static final Logger LOGGER = LogManager.getLogger(FileUnicoController.class);

	@RequestMapping(headers = { "content-type=application/json" }, method = RequestMethod.POST, value = "/pruebaPercy2")
	@ResponseBody
	public FileUnicoRest addUser(@RequestBody FileUnicoRest request) {
		//String a = request.getPathArchivo();
		return request;
	}

	@RequestMapping(value = "/pruebaPercy/{ppl}", method = RequestMethod.POST)
	@ResponseBody
	public String pruebaPercy(@PathVariable("ppl") String ppl, @RequestBody FileUnicoRest fileUnicoRest) {
		List<ParametroBean> listaParametroBean = null;
		//String a = fileUnicoRest.getPathArchivo();
		try {
			LOGGER.info("[InicioController contratoService.busquedaContrato]");
			// Oracle
			// listaParametroBean = parametroService.listarParametrosDetalle(idPadre);
			LOGGER.info("[InicioController listaParametroBean]:" + listaParametroBean);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return "pplln";
	}

	@RequestMapping(value = "/moverArchivosFileUnico", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> moverArchivosFileUnico(@RequestBody FileUnicoRest fileUnicoRest) throws Exception {
		LOGGER.info("ws done");
		File archivoHost = new File(fileUnicoRest.getPathArchivo());
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(archivoHost);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				direccionarFileUnico(linea, fileUnicoRest);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Error leyendo linea del archivo : " + e.getMessage());
			return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(1, HttpStatus.OK);
	}

	@SuppressWarnings("resource")
	@ResponseBody
	@RequestMapping(value = "/moverArchivosFileUnico2", method = RequestMethod.POST)
	public String moverArchivosFileUnico2(@RequestBody FileUnicoRest fileUnicoIn) throws Exception {
		LOGGER.info("ws done");
		File archivoHost = new File(fileUnicoIn.getPathArchivo());
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(archivoHost);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				direccionarFileUnico(linea, fileUnicoIn);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			LOGGER.error("Error al leer el archivo: " + archivoHost.getName());
			LOGGER.error("Excepcion al leer el archivo : " + e.getMessage());
			return "error";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Error leyendo linea del archivo : " + e.getMessage());
			return "error";
		}
		return "ok";
	}

	public String direccionarFileUnico(String obj, FileUnicoRest fileUnicoIn) {
		// mover file unico
		if (obj != null) {
			System.out.println("");
			List<File> archivos = obtenerArchivosPorContrato(obj.substring(2, 20),
					fileUnicoIn.getRutaContratosFirmados());
			moverFileUnico(archivos, fileUnicoIn);
		}
		return obj;
	}

	private List<File> obtenerArchivosPorContrato(String obj, String rutaContratos) {
		List<File> archivosPorContrato = new ArrayList<>();

		String codigoContrato = obj;// por definir donde vendra el codigo de contrato
		String sDirectorio = rutaContratos;// TODO propertiesExterno.DIRECTORIO_CONTRATOS
		File f = new File(sDirectorio);
		File[] ficheros = f.listFiles();
		for (int x = 0; x < ficheros.length; x++) {
			if (ficheros[x].getName().contains(codigoContrato) && ficheros[x].getName().contains(".pdf")) {
				archivosPorContrato.add(ficheros[x]);
			}
		}
		return archivosPorContrato;
	}

	private void moverFileUnico(List<File> archivos, FileUnicoRest fileUnicoIn) {
		// TODO Auto-generated method stub
		String sDirectorioContratos = fileUnicoIn.getRutaContratosFirmados();// propertiesExterno.DIRECTORIO_CONTRATOS;
		String sDirectorioFileUnico = fileUnicoIn.getRutaDestinoFU();// propertiesExterno.DIRECTORIO_CONTRATOS_FU;

		for (File file : archivos) {

			String origen = sDirectorioContratos + "\\" + file.getName();
			String destino = sDirectorioFileUnico + "\\" + file.getName();

			Path FROM = Paths.get(origen);
			Path TO = Paths.get(destino);
			// sobreescribir el fichero de destino, si existe, y copiar
			// los atributos, incluyendo los permisos rwx
			CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING,
					StandardCopyOption.COPY_ATTRIBUTES };
			try {
				Files.copy(FROM, TO, options);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.error("Error al copiar el archivo: " + file.getName());
			}

		}

	}
}