package com.ceiba.induccion.parqueadero.model;

import java.util.Date;

public class SolicitudServicio {
	
	private String placa;
	private String cilindraje;
	private String tipo;
	private String error;	
	private Date fecha;
	
	public SolicitudServicio(String placa, String cilindraje, String tipo, String error, Date fecha) {
		super();
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipo = tipo;
		this.error = error;
		this.fecha = fecha;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
