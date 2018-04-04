package com.ceiba.induccion.parqueadero.model;

public class TiempoServicio {

	private long dias;
	private long horas;
	private long totalHoras;
	private long horaFraccion;
	
	public TiempoServicio(long totalHoras,long horas) {
		this.totalHoras = totalHoras;
		this.horas = horas;
	}

	public long getDias() {
		return dias;
	}

	public void setDias(long dias) {
		this.dias = dias;
	}

	public long getHoras() {
		return horas;
	}

	public void setHoras(long horas) {
		this.horas = horas;
	}

	public long getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(long totalHoras) {
		this.totalHoras = totalHoras;
	}

	public long getHoraFraccion() {
		return horaFraccion;
	}

	public void setHoraFraccion(long horaFraccion) {
		this.horaFraccion = horaFraccion;
	}

	public String obtenerTiempoFacturado() {		
		StringBuilder tiempoFacturado = new StringBuilder();
		if (dias != 0) {
			tiempoFacturado.append(dias + " Dia(s)  ");
		}
		if (this.horas+this.horaFraccion != 0) {
			tiempoFacturado.append((this.horas+this.horaFraccion) + " Hora(s)");
		}

		return tiempoFacturado.toString();
	}

}
