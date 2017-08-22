package com.bbva.findim.ws.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;

import com.bbva.findim.dom.FileUnicoMetadata;
import com.bbva.findim.dom.common.Constantes;
import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public class Util {

	private static final Logger LOGGER = LogManager.getLogger(Util.class);

	private Util() {
	}

	public static String completarCadena(String cadena, int largo,
			char caracter, boolean izquierda) {
		if (cadena == null)
			return "";
		int laCadena = cadena.length();
		String auxiliar = "";
		for (int i = 0; i < largo - laCadena; i++) {
			auxiliar = auxiliar.concat(String.valueOf(caracter));
		}
		return izquierda ? auxiliar.concat(cadena) : cadena.concat(auxiliar);
	}

	public static String devuelveErrorFormateado(String error) {
		int pos;
		pos = error.indexOf("Cause:");
		if (pos > 0) {
			error = error.substring(pos + 6);
			pos = error.indexOf(":");
			if (pos > 0) {
				error = error.substring(pos + 1).trim();
			}
			error = "ERROR: " + error;
		}
		return error;
	}

	public static boolean esSoloDigitos(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
	}

	public static boolean existDigitos(String s) {
		char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (Character.isDigit(c)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean esFormatoTelefono(String s) {

		char c;
		if (esVacio(s))
			return true;
		s = s.trim();
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (!Character.isDigit(c)) {
				if (c != '-') {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean esFormatoMail(String s) {
		boolean validoArroba = false;
		boolean validoPunto = false;
		char c;
		if (esVacio(s))
			return true;
		s = s.trim();
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (c == '@')
				validoArroba = true;
			if (c == '.')
				validoPunto = true;
		}
		if (!validoArroba)
			return false;
		if (!validoPunto)
			return false;
		return true;
	}

	public static boolean esVacio(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
	}

	public static int devuelveIndicePrimerDigito(String s) {
		if (s == null)
			return -1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '0')
				return i;
		}
		return -1;
	}
	
	public static String getValueNull(String value){
		if (value == null) return null;
		if ("".equals(value.trim())) {
			return null;
		}else
			return value.toUpperCase().trim();
 	}

	public static String getValue(String value){
		if (value == null) return "";
		if ("".equals(value.trim())) {
			return "";
		}else
			return value.toUpperCase().trim();
 	}
	
	public static String sumaCodigo(String cod, int aumento) {
		int l = cod.length();
		int ind = devuelveIndicePrimerDigito(cod);
		if (ind == -1)
			ind = l - 1;
		String codigo = cod.substring(ind, l);
		int intNumero = Integer.valueOf(codigo).intValue();
		intNumero += aumento;
		return completarCadena(String.valueOf(intNumero), l, '0', true);
	}

	public static String copiaCadena(String original, boolean espacios) {
		int l = original.length();
		String copia = "";
		for (int i = 0; i < l; i++) {
			copia += original.charAt(i);
		}
		if (espacios)
			return copia;
		else
			return copia.trim();
	}

	public static boolean isDate(String input) {
		try {
			if (input.length() != 10)
				return false;
			String dia1 = input.substring(0, 2);
			String mes1 = input.substring(3, 5);
			String ano1 = input.substring(6, 10);
			int d = Integer.parseInt(dia1);
			int m = Integer.parseInt(mes1);
			int a = Integer.parseInt(ano1);
			Calendar c = Calendar.getInstance();
			c.set(a, m, d);
			return true;
		} catch (Exception e) {
			return false;
		}
	}	

	public static Date formatoDate(String fecha, String formato) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(formato);
			return df.parse(fecha);
		} catch (Exception e) {
			return null;
		}
	}

	public static Boolean existeCarpeta(String ruta) {
		Boolean res = true;
		try {
			File file = new File(ruta);
			if (!file.isDirectory()) {
				res = false;
			}
		} catch (Exception e) {
			res = false;
			LOGGER.error(e.getMessage(), e);
		}
		return res;
	}

	public static Boolean existeArchivo(String ruta) {
		Boolean res = true;
		try {
			File file = new File(ruta);
			if (!file.isFile()) {
				res = false;
			}
		} catch (Exception e) {
			res = false;
			LOGGER.error(e.getMessage(), e);
		}
		return res;
	}

	public static String interpolarVariables(String template, Map<String, String> variables) {
		StrSubstitutor sub = new StrSubstitutor(variables);
		return sub.replace(template);
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
			LOGGER.error(e.getMessage(), e);
		}
		
		return fileNames;
	}
	
	public static void deleteFilesByPrefix(String path,String prefix){
		List<File> files =null;
		try{
			files=Util.obtenerArchivosEnCarpeta(path);			
			for (File f:files) {
				if (f.isFile() && f.exists() && f.getPath().contains(prefix)) { 
					f.delete();
				} 
			}			
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}		
	}
	
	public static void deleteFilesByPrefixFilter(String path,String prefix,String namePDF){
		List<File> files =null;
		try{
			files=Util.obtenerArchivosEnCarpeta(path);			
			for (File f:files) {
				if (f.isFile() && f.exists() && f.getPath().contains(prefix)) { 
					f.delete();
				} 
			}			
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}		
	}
	
	public  static Date convertFormatDate(String inFormat,String outFormat,String inDate) throws ParseException{
		 Date outDate = null;
		 DateFormat inDF = new SimpleDateFormat(inFormat);
		 DateFormat outDF = new SimpleDateFormat(outFormat);
		    if (inDate != null) {
		        try {
		            Date date = inDF.parse(inDate);
		            outDate = outDF.parse(outDF.format(date));
		            
		        } catch (ParseException ex){ 
		        }
		    }
		    return outDate;
	 }
	
	public  static String changeFormatDate(String inFormat,String outFormat,String inDate) throws ParseException{
		 String outDate = null;
		 DateFormat inDF = new SimpleDateFormat(inFormat);
		 DateFormat outDF = new SimpleDateFormat(outFormat);
		    if (inDate != null) {
		        try {
		            Date date = inDF.parse(inDate);
		            outDate = outDF.format(date);
		            
		        } catch (ParseException ex){ 
		        }
		    }
		    return outDate;
	 }

	public  static String convertDateToString(Date date, String strFormat) throws ParseException{
		 DateFormat df = new SimpleDateFormat(strFormat);  
		 String text = df.format(date);  
		 return text;
	 }
	
	
	public static String obtenerNombreMes(int nroMes){
		String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre"
	            ,"Octubre","Noviembre","Diciemrbre"};
        return meses[nroMes-1];
	}
	
	public static String generarNombreConsolidadoPDF(String nroDNI, String codContrato){
		String nombrePDF = Constantes.DOCUMENTO_T + nroDNI + codContrato+ Constantes.EXTENSION_PDF;
		return nombrePDF;
	}
	
	public static void generarMetadata(String csvFile,FileUnicoMetadata metadata) {
        FileWriter writer;
		try {
			writer = new FileWriter(csvFile);
			List<String> list = new ArrayList<String>();
            list.add(metadata.getCodigoCentral());
            list.add(metadata.getDoi());
            list.add(metadata.getTipoDoi());
            list.add(metadata.getOficinaGestora());
            list.add(metadata.getNumeroContrato());
            list.add(metadata.getNumeroSolicitud());
            list.add(metadata.getNumeroOperacion());
            list.add(metadata.getProcedencia());
            list.add(metadata.getLocalizacionFisica());
            list.add(metadata.getEstadoConciliacion());
            list.add(metadata.getEstadoValidacion());
            list.add(metadata.getFechaBaseVigencia());
            list.add(metadata.getFechaFinalVigencia());
            list.add(metadata.getCodigoHash());
            list.add(metadata.getTipoInterviniente());
            list.add(metadata.getCodigoCentralInterviniente());
            list.add(metadata.getTipoDoiInterviniente());
            list.add(metadata.getDoiInterviniente());
            list.add(metadata.getContratosReferenciados());
            list.add(metadata.getDocumento());

            CSVUtils.writeLine(writer, list,'|');
			
	        writer.flush();
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			LOGGER.error(e.getMessage(), e);
		} finally {
			if (document.isOpen()) {
				document.close();
			}
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException ioe) {
				LOGGER.error(ioe.getMessage(), ioe);
			}
			if (streamOfPDFFiles != null) {
				for (InputStream is : streamOfPDFFiles) {
					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							LOGGER.error("Error cerrando InputStream");
						}
					}
				}
			}
		}
	}
	
	
}
