package com.ceiba.induccion.parqueadero.model;

import java.util.Calendar;

import com.ceiba.induccion.parqueadero.entity.CobroEntity;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;

public class CobroCarro extends Cobro {
    private String placa;    
    
    public CobroCarro() {
    	super();
    }
    
    public CobroCarro(long id,String placa,Calendar fechaEntrada, Calendar fechaSalida, String estado, int valorServicio, int tiempoServicio,
			String descripcionTiempoServicio, Servicio servicio) {
    	
    	 super(id,fechaEntrada,fechaSalida,estado,valorServicio,tiempoServicio,descripcionTiempoServicio,servicio);
    	 this.placa = placa;    	
    }
    
	
	public CobroCarro(CobroEntity cobroEntity) {    	
        super(cobroEntity.getId(),cobroEntity.getFechaEntrada(),cobroEntity.getFechaSalida(),cobroEntity.getEstado(),cobroEntity.getValorServicio(),cobroEntity.getTiempoServicio(),cobroEntity.getDescripcionTiempoServicio(),new Servicio(cobroEntity.getServicio()));
	    this.placa = cobroEntity.getPlaca();    	
    }
    
    
    
	public String getPlaca() {
		return placa;
	}

	@Override
	public void calcularValorServicio() {	
		long horasServicio = ParqueaderoUtil.calcularTiempoServicioHoras(this.getFechaEntrada(), this.getFechaSalida());
		this.setValorServicio(this.calcularValorServicioRecursivo(horasServicio, 0));
	}
	
	private long calcularValorServicioRecursivo(long horasServicio,long dias) {
		long valor = 0;
		long rangoHoraInferior = ParqueaderoUtil.NUEVE_HORAS +(ParqueaderoUtil.DIA_EN_HORAS*dias);
		long rangoSuperior = ParqueaderoUtil.DIA_EN_HORAS +(ParqueaderoUtil.DIA_EN_HORAS*dias);
		if(rangoHoraInferior>horasServicio) {
			valor = (horasServicio -(ParqueaderoUtil.DIA_EN_HORAS*dias))* this.getServicio().getTarifaHora();
		}else if(rangoHoraInferior<horasServicio && rangoSuperior > horasServicio) {			
			valor  = this.getServicio().getTarifaDia();
		}else {
			valor = calcularValorServicioRecursivo(horasServicio, dias++);
		}
		return valor;
		
	}
		
		
		
	}