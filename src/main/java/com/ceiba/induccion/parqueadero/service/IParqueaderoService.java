package com.ceiba.induccion.parqueadero.service;

import com.ceiba.induccion.parqueadero.model.Cobro;
import com.ceiba.induccion.parqueadero.model.Servicio;

public interface IParqueaderoService {

	public Servicio verificarDisponibilidadServicio(String placa,String tipo);

	public Cobro registrarEntrada(Cobro cobro);

	public Cobro registrarSalida();

}
