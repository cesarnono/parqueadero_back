package com.ceiba.induccion.parqueadero.model;

import java.util.Calendar;

import com.ceiba.induccion.parqueadero.entity.CobroEntity;

public class CobroMoto extends Cobro {
	
	private String placa;
	private String cilindraje;	
	
	
	public CobroMoto() {
		super();
	}
    
	public CobroMoto(long id,String placa,String cilindraje,Calendar fechaEntrada, Calendar fechaSalida, String estado, int valorServicio, int tiempoServicio,
			String descripcionTiempoServicio, Servicio servicio) {
    	
    	 super(id,fechaEntrada,fechaSalida,estado,valorServicio,tiempoServicio,descripcionTiempoServicio,servicio);
    	 this.placa = placa;
    	 this.cilindraje = cilindraje;
    }
	
	public CobroMoto(CobroEntity cobroEntity) {    	
    	 super(cobroEntity.getId(), cobroEntity.getFechaEntrada(),cobroEntity.getFechaSalida(),cobroEntity.getEstado(),cobroEntity.getValorServicio(),cobroEntity.getTiempoServicio(),cobroEntity.getDescripcionTiempoServicio(),new Servicio(cobroEntity.getServicio()));
    	 this.placa = cobroEntity.getPlaca();
    	 this.cilindraje = cobroEntity.getCilindraje();
    }
	
	public String getPlaca() {
		return placa;
	}
	
	public String getCilindraje() {
		return cilindraje;
	}

	@Override
	public void calcularValorServicio() {
		
		
	}

}
