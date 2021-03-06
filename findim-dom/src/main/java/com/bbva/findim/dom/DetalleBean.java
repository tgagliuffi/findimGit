package com.bbva.findim.dom;


public class DetalleBean {

	private int numeroCuota;
	private String fechaPago;
	private int numeroDias;
	private double saldoCapital;
	private String amortizacion;
	private double intereses;
	private String seguroDesgravamen;
	private double comisionEnvioPago;
	private String pago;
	private double cuotaTotal;
	private double factorActualizacionMensual;
	private int cuotaJulio;
	private int cuotaDiciembre;
	private String cuotaNormal;
	private double cuota;
	private int diasCuota;
	private String fim;
	private String interesMensual;
	private double seguro;
	private double saldoAntes;
	private String saldoAntesS;
	private double valorCuota;
	private double montoPenalidad;
	private double montoPagado;
	private String fechaVencimiento;
	private Long cdPrepago;
	private Integer tipoCobranza;

	public String getSaldoAntesS() {
		return saldoAntesS;
	}

	public void setSaldoAntesS(String saldoAntesS) {
		this.saldoAntesS = saldoAntesS;
	}

	public String getInteresMensual() {
		return interesMensual;
	}

	public void setInteresMensual(String interesMensual) {
		this.interesMensual = interesMensual;
	}

	public double getSaldoAntes() {
		return saldoAntes;
	}

	public void setSaldoAntes(double saldoAntes) {
		this.saldoAntes = saldoAntes;
	}

	public double getSeguro() {
		return seguro;
	}

	public void setSeguro(double seguro) {
		this.seguro = seguro;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

	public int getDiasCuota() {
		return diasCuota;
	}

	public void setDiasCuota(int diasCuota) {
		this.diasCuota = diasCuota;
	}

	public String getCuotaNormal() {
		return cuotaNormal;
	}

	public void setCuotaNormal(String cuotaNormal) {
		this.cuotaNormal = cuotaNormal;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

	public int getCuotaJulio() {
		return cuotaJulio;
	}

	public void setCuotaJulio(int cuotaJulio) {
		this.cuotaJulio = cuotaJulio;
	}

	public int getCuotaDiciembre() {
		return cuotaDiciembre;
	}

	public void setCuotaDiciembre(int cuotaDiciembre) {
		this.cuotaDiciembre = cuotaDiciembre;
	}

	public double getFactorActualizacionMensual() {
		return factorActualizacionMensual;
	}

	public void setFactorActualizacionMensual(double factorActualizacionMensual) {
		this.factorActualizacionMensual = factorActualizacionMensual;
	}

	public int getNumeroDias() {
		return numeroDias;
	}

	public void setNumeroDias(int numeroDias) {
		this.numeroDias = numeroDias;
	}

	public int getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public double getSaldoCapital() {
		return saldoCapital;
	}

	public void setSaldoCapital(double saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	public String getAmortizacion() {
		return amortizacion;
	}

	public void setAmortizacion(String amortizacion) {
		this.amortizacion = amortizacion;
	}

	public double getIntereses() {
		return intereses;
	}

	public void setIntereses(double intereses) {
		this.intereses = intereses;
	}

	public String getSeguroDesgravamen() {
		return seguroDesgravamen;
	}

	public void setSeguroDesgravamen(String seguroDesgravamen) {
		this.seguroDesgravamen = seguroDesgravamen;
	}

	public double getComisionEnvioPago() {
		return comisionEnvioPago;
	}

	public void setComisionEnvioPago(double comisionEnvioPago) {
		this.comisionEnvioPago = comisionEnvioPago;
	}

	public String getPago() {
		return pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}

	public double getCuotaTotal() {
		return cuotaTotal;
	}

	public void setCuotaTotal(double cuotaTotal) {
		this.cuotaTotal = cuotaTotal;
	}

	public double getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(double valorCuota) {
		this.valorCuota = valorCuota;
	}

	public double getMontoPenalidad() {
		return montoPenalidad;
	}

	public void setMontoPenalidad(double montoPenalidad) {
		this.montoPenalidad = montoPenalidad;
	}

	public double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	@Override
	public String toString() {
		return "DetalleBean [numeroCuota=" + numeroCuota + ", fechaPago=" + fechaPago + ", saldoCapital="
				+ saldoCapital + ", amortizacion=" + amortizacion + ", intereses=" + intereses + ", seguroDesgravamen="
				+ seguroDesgravamen + ", comisionEnvioPago=" + comisionEnvioPago + ", pago=" + pago + ", cuotaTotal="
				+ cuotaTotal + ", numeroDias=" + numeroDias + ", diasCuota=" + diasCuota
				+ ", factorActualizacionMensual=" + factorActualizacionMensual + ", cuotaJulio=" + cuotaJulio
				+ ", cuotaDiciembre=" + cuotaDiciembre + ", cuotaNormal=" + cuotaNormal + ", cuota=" + cuota
				+ ", saldoAntes=" + saldoAntes + ", fim=" + fim + ", interesMensual=" + interesMensual + ", seguro="
				+ seguro + ", valorCuota=" + valorCuota + ", montoPenalidad=" + montoPenalidad + ", montoPagado="
				+ montoPagado + ", fechaVencimiento=" + fechaVencimiento + "]";
	}

	public Long getCdPrepago() {
		return cdPrepago;
	}

	public void setCdPrepago(Long cdPrepago) {
		this.cdPrepago = cdPrepago;
	}

	public Integer getTipoCobranza() {
		return tipoCobranza;
	}

	public void setTipoCobranza(Integer tipoCobranza) {
		this.tipoCobranza = tipoCobranza;
	}

}