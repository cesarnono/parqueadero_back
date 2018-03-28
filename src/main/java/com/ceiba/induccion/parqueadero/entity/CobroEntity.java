package com.ceiba.induccion.parqueadero.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private String estado;
    private long valorServicio;
    private long tiempoServicio;
    private String descripcionTiempoServicio;
    private String placa;
    private int cilindraje;   
    @ManyToOne(fetch = FetchType.EAGER)    
    private ServicioEntity servicio;
    
    @Transient
    private String error;
    
    public CobroEntity() {    	
    }
    
    public CobroEntity(Cobro cobro) {    	    	
    	this.fechaEntrada = cobro.getFechaEntrada();
    	this.fechaSalida = cobro.getFechaSalida();
    	this.estado  = cobro.getEstado();
    	this.valorServicio = cobro.getValorServicio();
    	this.descripcionTiempoServicio = cobro.getDescripcionTiempoServicio();
    	this.tiempoServicio = cobro.getTiempoServicioHoras();
    	if(cobro instanceof CobroCarro){
    		CobroCarro cobroCarro = (CobroCarro)cobro;
    		this.placa = cobroCarro.getPlaca();
    	}
    	if(cobro instanceof CobroMoto){
    		CobroMoto cobroMoto= (CobroMoto)cobro;
    		this.placa = cobroMoto.getPlaca();
    		this.cilindraje = cobroMoto.getCilindraje();
    	}    	
    	this.servicio = new ServicioEntity(cobro.getServicio());
    	
    }    
   
	public long getId() {
		return id;
	}
	
	public Calendar getFechaEntrada() {
		return fechaEntrada;
	}
	
	public Calendar getFechaSalida() {
		return fechaSalida;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public long getValorServicio() {
		return valorServicio;
	}
	
	public long getTiempoServicio() {
		return tiempoServicio;
	}
	
	public String getDescripcionTiempoServicio() {
		return descripcionTiempoServicio;
	}
	
	public ServicioEntity getServicio() {
		return servicio;
	}
	
	public String getPlaca() {
		return placa;
	}
	public int getCilindraje() {
		return cilindraje;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
