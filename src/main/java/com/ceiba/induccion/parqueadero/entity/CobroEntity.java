package com.ceiba.induccion.parqueadero.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import com.ceiba.induccion.parqueadero.model.Cobro;
import com.ceiba.induccion.parqueadero.model.CobroCarro;
import com.ceiba.induccion.parqueadero.model.CobroMoto;


@Entity
@Table(name="cobro")
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
    private String placa;
    private String cilindraje;   
    @ManyToOne(fetch = FetchType.LAZY)    
    private ServicioEntity servicio;
    
    @Transient
    private String error;
    
    public CobroEntity() {    	
    }
    
    public CobroEntity(Cobro cobro) {    	    	
    	this.fechaEntrada = cobro.getFechaEntrada();
    	this.estado  = cobro.getEstado();
    	if(cobro instanceof CobroCarro){
    		CobroCarro cobroCarro = (CobroCarro)cobro;
    		this.placa = cobroCarro.getPlaca();
    	}
    	if(cobro instanceof CobroMoto){
    		CobroMoto cobroMoto= (CobroMoto)cobro;
    		this.placa = cobroMoto.getPlaca();
    		this.cilindraje = cobroMoto.getCilindraje();
    	}
    	this.servicio = new ServicioEntity();
    	this.servicio.setId(cobro.getServicio().getId());
    }    
   
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
	/*public ServicioEntity getServicio() {
		return servicio;
	}
	public void setServicio(ServicioEntity servicio) {
		this.servicio = servicio;
	}*/
	public String getPlaca() {
		return placa;
	}
	public String getCilindraje() {
		return cilindraje;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
	

}
