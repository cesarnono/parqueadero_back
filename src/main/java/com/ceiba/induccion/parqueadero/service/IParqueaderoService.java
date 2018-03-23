package com.ceiba.induccion.parqueadero.service;

import com.ceiba.induccion.parqueadero.model.Cobro;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.model.SolicitudServicio;

public interface IParqueaderoService {

	public Servicio verificarDisponibilidadServicio(SolicitudServicio solicitudServicio);

	public Cobro registrarEntrada(Servicio servicio);

	public Cobro registrarSalida(Cobro cobro);

}
