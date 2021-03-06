package com.ceiba.induccion.parqueadero.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.ceiba.induccion.parqueadero.model.Servicio;

@Entity
@Table(name = "servicio")
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
	private long tarifaHora;
	private long tarifaDia;	

	public ServicioEntity() {

	}

	public ServicioEntity(String descripcion, int cupoMaximo, int cupoDisponible, int tarifaHora, int tarifaDia) {
		super();
		this.descripcion = descripcion;
		this.cupoMaximo = cupoMaximo;
		this.cupoDisponible = cupoDisponible;
		this.tarifaHora = tarifaHora;
		this.tarifaDia = tarifaDia;
	}

	public ServicioEntity(Servicio servicio) {
		super();		
		this.descripcion = servicio.getDescripcion();
		this.cupoMaximo = servicio.getCantidadMaxima();
		this.cupoDisponible = servicio.getCupoDisponible();
		this.tarifaHora = servicio.getTarifaHora();
		this.tarifaDia = servicio.getTarifaDia();
		this.id   = servicio.getId();
	}

	public long getId() {
		return id;
	}	

	public String getDescripcion() {
		return descripcion;
	}

	

	public int getCupoMaximo() {
		return cupoMaximo;
	}	

	public int getCupoDisponible() {
		return cupoDisponible;
	}	

	public long getTarifaHora() {
		return tarifaHora;
	}	

	public long getTarifaDia() {
		return tarifaDia;
	}

}
