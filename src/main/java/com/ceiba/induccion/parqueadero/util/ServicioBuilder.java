package com.ceiba.induccion.parqueadero.util;

import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.model.SolicitudServicio;

public class ServicioBuilder {
	private static final String DESCRIPCION = "carro";
	private static final int CUPOMAXIMO = 20;
	private static final  long TARIFAHORA = 1000;
	private static final long TARIFADIA = 8000;
	private static final int CUPODISPONIBLE = 20;
	
	private long id;
	private String descripcion;
	private int cupoMaximo;
	private long tarifaHora;
	private long tarifaDia;
	private int cupoDisponible;
	private String error;
	private SolicitudServicio solicitudServicio;
	
	
	
	public ServicioBuilder () {
		this.descripcion = DESCRIPCION;
		this.cupoMaximo = CUPOMAXIMO;
		this.tarifaHora = TARIFAHORA;
		this.tarifaDia = TARIFADIA;
		this.cupoDisponible = CUPODISPONIBLE;
	}
	
	public ServicioBuilder withDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}
	
	public ServicioBuilder withCupoDisponible(int cupoDisponible) {
		this.cupoDisponible = cupoDisponible;
		return this;		
	}
	
	public ServicioBuilder withTarifaHora(long tarifaHora) {
		this.tarifaHora = tarifaHora;
		return this;		
	}
	
	public ServicioBuilder withTarifaDia(long tarifaDia) {
		this.tarifaDia = tarifaDia;
		return this;		
	}
	
	public ServicioBuilder withCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
		return this;		
	}
	
	public ServicioBuilder withSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
		return this;
	}
	
	public ServicioBuilder withId(long id) {
		this.id = id;
		return this;
	}
	
	public Servicio build () {
		return new Servicio(this.id, this.descripcion, this.cupoMaximo,this.cupoDisponible, this.tarifaHora, this.tarifaDia, this.error,this.solicitudServicio);
	}
}
