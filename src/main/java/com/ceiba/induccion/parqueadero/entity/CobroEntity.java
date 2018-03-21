package com.ceiba.induccion.parqueadero.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Cobro")
public class CobroEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Calendar fechaEntrada;
	private Calendar fechaSalida;
	private int puesto;
	private String estado;
    private int valorServicio;
    private int tiempoServicio;
    private String descripcionTiempoServicio;
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id" ,unique=true, nullable= false, updatable= false)
    private ServicioEntity servicio;    
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Calendar getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Calendar fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public Calendar getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public int getPuesto() {
		return puesto;
	}
	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getValorServicio() {
		return valorServicio;
	}
	public void setValorServicio(int valorServicio) {
		this.valorServicio = valorServicio;
	}
	public int getTiempoServicio() {
		return tiempoServicio;
	}
	public void setTiempoServicio(int tiempoServicio) {
		this.tiempoServicio = tiempoServicio;
	}
	public String getDescripcionTiempoServicio() {
		return descripcionTiempoServicio;
	}
	public void setDescripcionTiempoServicio(String descripcionTiempoServicio) {
		this.descripcionTiempoServicio = descripcionTiempoServicio;
	}
	public ServicioEntity getServicio() {
		return servicio;
	}
	public void setServicio(ServicioEntity servicio) {
		this.servicio = servicio;
	}

}
