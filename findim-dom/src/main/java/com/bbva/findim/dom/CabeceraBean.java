package com.bbva.findim.dom;

import java.math.BigDecimal;
import java.util.List;

public class CabeceraBean {
	private String moneda;
	private String valorEquipoS;
	private String codigoTarifa;
	
	private BigDecimal saldoFinanciar;
	private int metodoEnvio;
	private int numeroCuotas;
	private String tea;
	private String tcea;
	private String seguroDesgravamen;
	private String cargoFijoMaximo;
	
	//campos nuevos
	
	private String inCodSubProducto;
	private Integer diaPago;
	private String inPerCapital;
	private String inPerInteres;
	private String inCarCapital;
	private String inCarInteres;
	private String inMonedaEquipo;
	
	
	private BigDecimal porcentajeCuotaInicial;
	private BigDecimal cuotaInicial;
	private BigDecimal valorEquipo;
	private BigDecimal tarifa;

	
	private List<TextoInformativoBean> listaTexto;
	
	public String getValorEquipoS() {
		return valorEquipoS;
	}

	public void setValorEquipoS(String valorEquipoS) {
		this.valorEquipoS = valorEquipoS;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getValorEquipo() {
		return valorEquipo;
	}

	public void setValorEquipo(BigDecimal valorEquipo) {
		this.valorEquipo = valorEquipo;
	}

	public BigDecimal getTarifa() {
		return tarifa;
	}

	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}

	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	public BigDecimal getPorcentajeCuotaInicial() {
		return porcentajeCuotaInicial;
	}

	public void setPorcentajeCuotaInicial(BigDecimal porcentajeCuotaInicial) {
		this.porcentajeCuotaInicial = porcentajeCuotaInicial;
	}

	public BigDecimal getCuotaInicial() {
		return cuotaInicial;
	}

	public void setCuotaInicial(BigDecimal cuotaInicial) {
		this.cuotaInicial = cuotaInicial;
	}


	
	public BigDecimal getSaldoFinanciar() {
		return saldoFinanciar;
	}

	public void setSaldoFinanciar(BigDecimal saldoFinanciar) {
		this.saldoFinanciar = saldoFinanciar;
	}

	public int getMetodoEnvio() {
		return metodoEnvio;
	}

	public void setMetodoEnvio(int metodoEnvio) {
		this.metodoEnvio = metodoEnvio;
	}

	public int getNumeroCuotas() {
		return numeroCuotas;
	}

	public void setNumeroCuotas(int numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public String getTea() {
		return tea;
	}

	public void setTea(String tea) {
		this.tea = tea;
	}

	public String getTcea() {
		return tcea;
	}

	public void setTcea(String tcea) {
		this.tcea = tcea;
	}

	public String getSeguroDesgravamen() {
		return seguroDesgravamen;
	}

	public void setSeguroDesgravamen(String seguroDesgravamen) {
		this.seguroDesgravamen = seguroDesgravamen;
	}

	public String getCargoFijoMaximo() {
		return cargoFijoMaximo;
	}

	public void setCargoFijoMaximo(String cargoFijoMaximo) {
		this.cargoFijoMaximo = cargoFijoMaximo;
	}

	public List<TextoInformativoBean> getListaTexto() {
		return listaTexto;
	}

	public void setListaTexto(List<TextoInformativoBean> listaTexto) {
		this.listaTexto = listaTexto;
	}

	public String getInCodSubProducto() {
		return inCodSubProducto;
	}

	public void setInCodSubProducto(String inCodSubProducto) {
		this.inCodSubProducto = inCodSubProducto;
	}

	public Integer getDiaPago() {
		return diaPago;
	}

	public void setDiaPago(Integer diaPago) {
		this.diaPago = diaPago;
	}

	public String getInPerCapital() {
		return inPerCapital;
	}

	public void setInPerCapital(String inPerCapital) {
		this.inPerCapital = inPerCapital;
	}

	public String getInPerInteres() {
		return inPerInteres;
	}

	public void setInPerInteres(String inPerInteres) {
		this.inPerInteres = inPerInteres;
	}

	public String getInCarCapital() {
		return inCarCapital;
	}

	public void setInCarCapital(String inCarCapital) {
		this.inCarCapital = inCarCapital;
	}

	public String getInCarInteres() {
		return inCarInteres;
	}

	public void setInCarInteres(String inCarInteres) {
		this.inCarInteres = inCarInteres;
	}

	public String getInMonedaEquipo() {
		return inMonedaEquipo;
	}

	public void setInMonedaEquipo(String inMonedaEquipo) {
		this.inMonedaEquipo = inMonedaEquipo;
	}

	
	
	
}
