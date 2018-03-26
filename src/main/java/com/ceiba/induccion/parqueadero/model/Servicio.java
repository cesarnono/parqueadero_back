package com.ceiba.induccion.parqueadero.model;

import com.ceiba.induccion.parqueadero.entity.ServicioEntity;

public class Servicio {
	
	private long id;
	private String descripcion;
	private int cantidadMaxima;
	private int tarifaHora;
	private int tarifaDia;
	private int cupoDisponible;
	private String error;	
	private SolicitudServicio solicitudServicio;
	
	public Servicio() {
		super();
	}	
	
	public Servicio(ServicioEntity servicioEntity) {
		super();		
		this.id = servicioEntity.getId();
		this.descripcion = servicioEntity.getDescripcion();
		this.cantidadMaxima = servicioEntity.getCupoMaximo();
		this.tarifaHora = servicioEntity.getTarifaHora();
		this.tarifaDia = servicioEntity.getTarifaDia();		
		this.cupoDisponible = servicioEntity.getCupoDisponible();
	}
	public Servicio(long id, String descripcion, int cantidadMaxima,int cupoDisponible, int tarifaHora, int tarifaDia, String error, SolicitudServicio solicitudServicio) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.cantidadMaxima = cantidadMaxima;
		this.tarifaHora = tarifaHora;
		this.tarifaDia = tarifaDia;
		this.error = error;
		this.cupoDisponible = cupoDisponible;
		this.solicitudServicio = solicitudServicio;
	}



	public String getDescripcion() {
		return descripcion;
	}
	public int getCantidadMaxima() {
		return cantidadMaxima;
	}
	public int getTarifaHora() {
		return tarifaHora;
	}
	public int getTarifaDia() {
		return tarifaDia;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public long getId() {
		return id;
	}	
	public int getCupoDisponible() {
		return cupoDisponible;
	}

	public SolicitudServicio getSolicitudServicio() {
		return solicitudServicio;
	}

	public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
	}

}
