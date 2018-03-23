package com.ceiba.induccion.parqueadero.model;

import java.util.Calendar;

public class CobroMoto extends Cobro {
	
	private String placa;
	private String cilindraje;	
	
	
	public CobroMoto() {
		super();
	}
    
	public CobroMoto(String placa,String cilindraje,Calendar fechaEntrada, Calendar fechaSalida, String estado, int valorServicio, int tiempoServicio,
			String descripcionTiempoServicio, Servicio servicio) {
    	
    	 super(fechaEntrada,fechaSalida,estado,valorServicio,tiempoServicio,descripcionTiempoServicio,servicio);
    	 this.placa = placa;
    	 this.cilindraje = cilindraje;
    }
	
	public String getPlaca() {
		return placa;
	}
	
	public String getCilindraje() {
		return cilindraje;
	}

	@Override
	public void calcularValorServicio() {
		// TODO Auto-generated method stub
		
	}

}
