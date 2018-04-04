package com.ceiba.induccion.parqueadero.model;

import java.util.Calendar;

import com.ceiba.induccion.parqueadero.entity.CobroEntity;

public class CobroCarro extends Cobro {
	private String placa;

	public CobroCarro() {
		super();
	}

	public CobroCarro(long id, String placa, Calendar fechaEntrada, Calendar fechaSalida, String estado,
			long valorServicio, Servicio servicio) {

		super(id, fechaEntrada, fechaSalida, estado, valorServicio, 
				servicio);
		this.placa = placa;
	}

	public CobroCarro(CobroEntity cobroEntity) {
		super(cobroEntity.getId(), cobroEntity.getFechaEntrada(), cobroEntity.getFechaSalida(), cobroEntity.getEstado(),
				cobroEntity.getValorServicio(),  new Servicio(cobroEntity.getServicio()));
		this.placa = cobroEntity.getPlaca();
	}

	public String getPlaca() {
		return placa;
	}
}