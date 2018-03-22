package com.ceiba.induccion.parqueadero.model;

import java.sql.Timestamp;

public abstract class Cobro {
	
    private Timestamp fechaEntrada ;
    private Timestamp fechaSalida;    
    private String estado;
    private int valorServicio;
    private int tiempoServicio;
    private String descripcionTiempoServicio;
    private Servicio servicio;   
    
    public Cobro() {
    	
    }
    
	public Cobro(Timestamp fechaEntrada, Timestamp fechaSalida, String estado, int valorServicio, int tiempoServicio,
			String descripcionTiempoServicio, Servicio servicio) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.estado = estado;
		this.valorServicio = valorServicio;
		this.tiempoServicio = tiempoServicio;
		this.descripcionTiempoServicio = descripcionTiempoServicio;
		this.servicio = servicio;
	}
	public Timestamp getFechaEntrada() {
		return fechaEntrada;
	}
	public Timestamp getFechaSalida() {
		return fechaSalida;
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
