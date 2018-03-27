package com.ceiba.induccion.parqueadero.model;

import java.util.Calendar;

import com.ceiba.induccion.parqueadero.entity.CobroEntity;
import com.ceiba.induccion.parqueadero.service.ServicioTestDataBuilder;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;

public class CobroMoto extends Cobro {
	
	private String placa;
	private int cilindraje;	
	
	
	public CobroMoto() {
		super();
	}
    
	public CobroMoto(long id,String placa,int cilindraje,Calendar fechaEntrada, Calendar fechaSalida, String estado, int valorServicio, int tiempoServicio,
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
		TiempoServicio tiempoServicio = ParqueaderoUtil.calcularTiempoServicio(this.getFechaEntrada(),
				this.getFechaSalida());
		long valorTotalServicio = this.calcularValorServicioRecursivo(tiempoServicio, 0);
		this.valorServicio = this.aplicarHorasFraccion(tiempoServicio, valorTotalServicio);
		this.aplicarRecargoCilindraje();
		this.descripcionTiempoServicio = tiempoServicio.obtenerTiempoFacturado();
		this.tiempoServicioHoras = tiempoServicio.getTotalHoras() + tiempoServicio.getHoraFraccion();
		
	}
	
	
	/*public static void main(String[] args) {
		Servicio servicio = new ServicioTestDataBuilder().build();
		CobroMoto cobroMoto = new CobroMoto(-1, null,500,
				ParqueaderoUtil.getFechaCalendar("dd-M-yyyy HH:mm:ss", "18-03-2018 01:00:00"),
				ParqueaderoUtil.getFechaCalendar("dd-M-yyyy HH:mm:ss", "20-03-2018 14:10:00"), null, 0, 0, null,
				servicio);
		cobroMoto.calcularValorServicio();
		System.out.println(cobroMoto.getDescripcionTiempoFacturado());

	}*/
	
	public void aplicarRecargoCilindraje() {
		this.valorServicio = this.cilindraje > ParqueaderoUtil.CILINDRAJE_MOTO_500 ? this.valorServicio +ParqueaderoUtil.RECARGO_CILINDRAJE_MOTO_500: this.valorServicio;
	}

}
