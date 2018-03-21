package com.ceiba.induccion.parqueadero.model;

import java.sql.Timestamp;

public abstract class Cobro {
	
    private Timestamp fechaEntrada ;
    private Timestamp fechaSalida;
    private int puesto ;
    private String estado;
    private int valorServicio;
    private int tiempoServicio;
    private String descripcionTiempoServicio;
    private Servicio servicio; 
	public Timestamp getFechaEntrada() {
		return fechaEntrada;
	}
	public Timestamp getFechaSalida() {
		return fechaSalida;
	}
	public int getPuesto() {
		return puesto;
	}
	public String getEstado() {
		return estado;
	}
	public int getValorServicio() {
		return valorServicio;
	}
	public int getTiempoServicio() {
		return tiempoServicio;
	}
	public String getDescripcionTiempoServicio() {
		return descripcionTiempoServicio;
	}	
    
    public Servicio getServicio() {
		return servicio;
	}
	public abstract void calcularValorServicio();

}
