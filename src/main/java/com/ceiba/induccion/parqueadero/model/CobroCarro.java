package com.ceiba.induccion.parqueadero.model;

import java.util.Calendar;

public class CobroCarro extends Cobro {
    private String placa;    
    
    public CobroCarro() {
    	super();
    }
    
    public CobroCarro(String placa,Calendar fechaEntrada, Calendar fechaSalida, String estado, int valorServicio, int tiempoServicio,
			String descripcionTiempoServicio, Servicio servicio) {
    	
    	 super(fechaEntrada,fechaSalida,estado,valorServicio,tiempoServicio,descripcionTiempoServicio,servicio);
    	 this.placa = placa;    	
    }
    
    
    
	public String getPlaca() {
		return placa;
	}

	@Override
	public void calcularValorServicio() {
		// TODO Auto-generated method stub
		
	}

}
