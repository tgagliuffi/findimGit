package com.bbva.findim.ws.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;

import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public final class UtilPersistencia {

	public static final Logger logger = LogManager.getLogger(UtilPersistencia.class);

	public static boolean isNumeroValido(Float lNumero) {
		try {
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean isNumerico(String cadena) {
		boolean b;
		try {
			Integer.parseInt(cadena);
			b = true;
		} catch (NumberFormatException e) {
			b = false;
		}
		return b;
	}

	public static String completarCeros(String cadenaNumerica, String tamanioCadenaNumerica) {
		// El valor de cadena numerica debe ser mayor a 1
		String cadenaFormateada;
		if (cadenaNumerica != null && !cadenaNumerica.isEmpty()) {
			if (isNumerico(cadenaNumerica)) {
				cadenaFormateada = String.format("%1$0" + tamanioCadenaNumerica + ".0f", new Double(cadenaNumerica));
			} else {
				int i = Integer.parseInt(tamanioCadenaNumerica) - cadenaNumerica.length();
				if (i <= 0) {
					cadenaFormateada = cadenaNumerica;
				} else {
					cadenaFormateada = String.format("%1$0" + i + ".0f", new Double("0"));
					cadenaFormateada = cadenaFormateada + cadenaNumerica;
				}
			}
		} else {
			cadenaFormateada = String.format("%1$0" + tamanioCadenaNumerica + ".0f", new Double("0"));
		}
		return cadenaFormateada;
	}

	public static String completarEspacios(String cadena, String tamanioCadena) {
		String cadenaFormateada;
		if (cadena != null) {
			cadenaFormateada = String.format("%1$-" + tamanioCadena + "s", cadena);
		} else {
			cadenaFormateada = String.format("%1$-" + tamanioCadena + "s", "");
		}
		return cadenaFormateada;
	}

	public static void concatenaPDF(List<InputStream> streamOfPDFFiles, OutputStream outputStream, boolean paginate) {
		Document document = new Document();
		try {
			List<InputStream> pdfs = streamOfPDFFiles;
			List<PdfReader> readers = new ArrayList<>();
			Iterator<InputStream> iteratorPDFs = pdfs.iterator();

			while (iteratorPDFs.hasNext()) {
				InputStream pdf = iteratorPDFs.next();
				PdfReader pdfReader = new PdfReader(pdf);
				readers.add(pdfReader);
			}

			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			PdfImportedPage page;
			int pageOfCurrentReaderPDF = 0;
			Iterator<PdfReader> iteratorPDFReader = readers.iterator();

			while (iteratorPDFReader.hasNext()) {
				PdfReader pdfReader = iteratorPDFReader.next();
				while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
					Rectangle rectangle = pdfReader.getPageSizeWithRotation(1);
					document.setPageSize(rectangle);
					document.newPage();

					pageOfCurrentReaderPDF++;
					page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
					switch (rectangle.getRotation()) {
					case 0:
						cb.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
						break;
					case 90:
						cb.addTemplate(page, 0, -1f, 1f, 0, 0, pdfReader.getPageSizeWithRotation(1).getHeight());
						break;
					case 180:
						cb.addTemplate(page, -1f, 0, 0, -1f, 0, 0);
						break;
					case 270:
						cb.addTemplate(page, 0, 1.0F, -1.0F, 0, pdfReader.getPageSizeWithRotation(1).getWidth(), 0);
						break;
					default:
						break;
					}
					if (paginate) {
						cb.beginText();
						cb.getPdfDocument().getPageSize();
						cb.endText();
					}
				}
				pageOfCurrentReaderPDF = 0;
			}
			outputStream.flush();
			document.close();
			outputStream.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (document.isOpen()) {
				document.close();
			}
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException ioe) {
				logger.error(ioe.getMessage(), ioe);
			}
			if (streamOfPDFFiles != null) {
				for (InputStream is : streamOfPDFFiles) {
					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							logger.error("Error cerrando InputStream");
						}
					}
				}
			}
		}
	}
	
	/*************************************************/
	public static Boolean existeArchivo(String ruta){
		Boolean res=true;
		try{
			File file = new File(ruta);
			if(!file.isFile()){
				res=false;
			}	
		}catch(Exception e){
			res=false;
			logger.error(e.getMessage(), e);
		}	
		return res;
	}
	
	public static Boolean existeCarpeta(String ruta){
		Boolean res=true;
		try{
			File file = new File(ruta);
			if(!file.isDirectory()){
				res=false;
			}	
		}catch(Exception e){
			res=false;
			logger.error(e.getMessage(), e);
		}
		return res;
	}
	
	public static List<File> obtenerArchivosEnCarpeta(String ruta){
		List<File> fileNames= new ArrayList<File>();
		try{
			File directory = new File(ruta);
			File[] listOfFiles = directory.listFiles();
			if(directory.isDirectory()){
				for (int i = 0; i < listOfFiles.length; i++)         {
		            if (listOfFiles[i].isFile())             {
		            	fileNames.add(listOfFiles[i]);
		            }
		        }
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		
		return fileNames;
	}
	
	public static void deleteFilesByPrefix(String path,String prefix){
		List<File> files =null;
		try{
			files=UtilPersistencia.obtenerArchivosEnCarpeta(path);			
			for (File f:files) {
				if (f.isFile() && f.exists() && f.getPath().contains(prefix)) { 
					f.delete();
				} 
			}			
		}catch(Exception e){
			logger.error("error al eliminar archivos temporales..."+e.getMessage());
		}		
	}
	
	public static void exportJaspersToPDF(List<JasperPrint> jprintlist,String ruta){
		JRPdfExporter exporter =null;
		try{
			exporter = new JRPdfExporter();
		    exporter.setExporterInput(SimpleExporterInput.getInstance(jprintlist)); //Set as export input
		    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ruta));		    
		    exporter.exportReport();
		}catch(Exception e){
			logger.error("Error al generar el PDF..."+e.getMessage());
		}		
	}
	
	public static String interpolarVariables(String template, Map<String, String> variables) {
		   StrSubstitutor sub = new StrSubstitutor(variables);
		   return sub.replace(template);
	}

}