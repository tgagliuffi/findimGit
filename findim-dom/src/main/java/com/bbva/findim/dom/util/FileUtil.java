package com.bbva.findim.dom.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;


public class FileUtil {

	private FileUtil() {
	}

	public static FileTime getCreationTimeOfFile(File archivo) {
		if (archivo == null) {
			return null;
		}
		BasicFileAttributes attr = null;
		try {
			attr = Files.readAttributes(Paths.get(archivo.getAbsolutePath()), BasicFileAttributes.class);
			return attr.creationTime();
		} catch (IOException e) {
			return null;
		}
	}

	public static List<File> listarArchivosPorPrefijo(String directoryPath, String prefijo) {
		File directory = new File(directoryPath);
		File[] listFiles = directory.listFiles();
		List<File> archivosRespuesta = new ArrayList<>();
		for (File archivo : listFiles) {
			if (archivo.getName().startsWith(prefijo)) {
				archivosRespuesta.add(archivo);
			}
		}
		return archivosRespuesta;
	}
	
	public static Boolean eliminarArchivosCarpeta(File file) {
		Boolean res=true;
		try {
			File folder = new File(file.getAbsolutePath());
			if (!file.exists()){
				return res;
			}

			File[] contents = file.listFiles();
		    if (contents != null) {
		        for (File f : contents) {
		        	eliminarArchivosCarpeta(f);
		        }
		    }
		    if(!folder.isDirectory()){
		    	res=file.delete();
		    }
		} catch (Exception e) {
			res=false;
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
		}
		return res;
	}
}
