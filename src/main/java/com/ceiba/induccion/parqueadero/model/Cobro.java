package com.ceiba.induccion.parqueadero.model;


import java.util.Calendar;

public abstract class Cobro {
	
    private long id;
	private Calendar fechaEntrada ;
    private Calendar fechaSalida;    
    private String estado;
    private long valorServicio;
    private int tiempoServicio;
    private String descripcionTiempoServicio;
    private Servicio servicio;   
    private String error;
    
    public Cobro() {
    	
    }
    
	public Cobro(long id,Calendar fechaEntrada, Calendar fechaSalida, String estado, int valorServicio, int tiempoServicio,
			String descripcionTiempoServicio, Servicio servicio) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.estado = estado;
		this.valorServicio = valorServicio;
		this.tiempoServicio = tiempoServicio;
		this.descripcionTiempoServicio = descripcionTiempoServicio;
		this.servicio = servicio;
		this.id = id;
	}
	
	
		
	public String getEstado() {
		return estado;
	}
	public long getValorServicio() {
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setValorServicio(long valorServicio) {
		this.valorServicio = valorServicio;
	}	
	
	
	

}
