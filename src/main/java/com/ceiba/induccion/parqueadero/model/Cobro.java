package com.ceiba.induccion.parqueadero.model;


import java.util.Calendar;

public abstract class Cobro {
	
    private Calendar fechaEntrada ;
    private Calendar fechaSalida;    
    private String estado;
    private int valorServicio;
    private int tiempoServicio;
    private String descripcionTiempoServicio;
    private Servicio servicio;   
    
    public Cobro() {
    	
    }
    
	public Cobro(Calendar fechaEntrada, Calendar fechaSalida, String estado, int valorServicio, int tiempoServicio,
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
    
	public Calendar getFechaEntrada() {
		return fechaEntrada;
	}

	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public abstract void calcularValorServicio();

}
