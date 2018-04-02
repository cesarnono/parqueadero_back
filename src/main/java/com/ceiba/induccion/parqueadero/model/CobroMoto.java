package com.ceiba.induccion.parqueadero.model;

import java.util.Calendar;

import com.ceiba.induccion.parqueadero.entity.CobroEntity;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;

public class CobroMoto extends Cobro {
	
	private String placa;
	private int cilindraje;	
	
	
	public CobroMoto() {
		super();
	}
    
	public CobroMoto(long id,String placa,int cilindraje,Calendar fechaEntrada, Calendar fechaSalida, String estado, long valorServicio, long tiempoServicio,
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
	
	public int getCilindraje() {
		return cilindraje;
	}

	@Override
	public void calcularValorServicio() {
		this.fechaSalida = Calendar.getInstance();
		TiempoServicio tiempoServicio = ParqueaderoUtil.calcularTiempoServicio(this.getFechaEntrada(),
				this.getFechaSalida());
		long valorTotalServicio = this.calcularValorServicioRecursivo(tiempoServicio, 0);
		this.valorServicio = this.aplicarHorasFraccion(tiempoServicio, valorTotalServicio);
		this.aplicarRecargoCilindraje();
		this.descripcionTiempoServicio = tiempoServicio.obtenerTiempoFacturado();
		this.tiempoServicioHoras = tiempoServicio.getTotalHoras() + tiempoServicio.getHoraFraccion();
		
	}
	
	public void aplicarRecargoCilindraje() {
		this.valorServicio = this.cilindraje > ParqueaderoUtil.CILINDRAJE_MOTO_500 ? this.valorServicio +ParqueaderoUtil.RECARGO_CILINDRAJE_MOTO_500: this.valorServicio;
	}

}
