package com.bbva.findim.dom;

import java.io.File;
import java.util.List;

public class EmailMessage {

	private String emailDestino;
	private String emailFrom;
	private String asunto;
	private List<String> bccList;
	private String textoContenido;
	private char estadoCorreo = '0';
	private String mensajeError;
	private List<File> files;

	public String getEmailDestino() {
		return emailDestino;
	}

	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getTextoContenido() {
		return textoContenido;
	}

	public void setTextoContenido(String textoContenido) {
		this.textoContenido = textoContenido;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public char getEstadoCorreo() {
		return estadoCorreo;
	}

	public void setEstadoCorreo(char estadoCorreo) {
		this.estadoCorreo = estadoCorreo;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public List<String> getBccList() {
		return bccList;
	}

	public void setBccList(List<String> bccList) {
		this.bccList = bccList;
	}

}