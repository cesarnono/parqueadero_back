package com.ceiba.induccion.parqueadero.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicio")
public class ServicioEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String descripcion;
	private int cupoMaximo;
	private int cupoDisponible;
	private int tarifaHora;
	private int tarifaDia;	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCupoMaximo() {
		return cupoMaximo;
	}
	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}
	public int getCupoDisponible() {
		return cupoDisponible;
	}
	public void setCupoDisponible(int cupoDisponible) {
		this.cupoDisponible = cupoDisponible;
	}
	public int getTarifaHora() {
		return tarifaHora;
	}
	public void setTarifaHora(int tarifaHora) {
		this.tarifaHora = tarifaHora;
	}
	public int getTarifaDia() {
		return tarifaDia;
	}
	public void setTarifaDia(int tarifaDia) {
		this.tarifaDia = tarifaDia;
	}	

}
