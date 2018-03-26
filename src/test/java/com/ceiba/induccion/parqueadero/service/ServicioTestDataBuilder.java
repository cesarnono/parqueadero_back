package com.ceiba.induccion.parqueadero.service;

import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.model.SolicitudServicio;

public class ServicioTestDataBuilder {
	
	
	private static final String DESCRIPCION = "carro";
	private static final int CUPOMAXIMO = 20;
	private static final  int TARIFAHORA = 1000;
	private static final int TARIFADIA = 8000;
	private static final int CUPODISPONIBLE = 20;
	
	private long id;
	private String descripcion;
	private int cupoMaximo;
	private int tarifaHora;
	private int tarifaDia;
	private int cupoDisponible;
	private String error;
	private SolicitudServicio solicitudServicio;
	
	
	
	public ServicioTestDataBuilder () {
		this.descripcion = DESCRIPCION;
		this.cupoMaximo = CUPOMAXIMO;
		this.tarifaHora = TARIFAHORA;
		this.tarifaDia = TARIFADIA;
		this.cupoDisponible = CUPODISPONIBLE;
	}
	
	public ServicioTestDataBuilder withDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}
	
	public ServicioTestDataBuilder withCupoDisponible(int cupoDisponible) {
		this.cupoDisponible = cupoDisponible;
		return this;		
	}
	
	public ServicioTestDataBuilder withTarifaHora(int tarifaHora) {
		this.tarifaHora = tarifaHora;
		return this;		
	}
	
	public ServicioTestDataBuilder withTarifaDia(int tarifaDia) {
		this.tarifaDia = tarifaDia;
		return this;		
	}
	
	public ServicioTestDataBuilder withCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
		return this;		
	}
	
	public ServicioTestDataBuilder withSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
		return this;
	}
	
	public ServicioTestDataBuilder withId(long id) {
		this.id = id;
		return this;
	}
	
	public Servicio build () {
		return new Servicio(this.id, this.descripcion, this.cupoMaximo,this.cupoDisponible, this.tarifaHora, this.tarifaDia, this.error,this.solicitudServicio);
	}
	

}
