package com.ceiba.induccion.parqueadero.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.induccion.parqueadero.entity.ServicioEntity;
import com.ceiba.induccion.parqueadero.exception.ParqueaderoException;
import com.ceiba.induccion.parqueadero.model.Cobro;
import com.ceiba.induccion.parqueadero.model.CobroCarro;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.repository.ServicioRepository;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;

@Service
public class ParqueaderoService implements IParqueaderoService {

	@Autowired
	ServicioRepository servicioRepository;

	@Override
	public Servicio verificarDisponibilidadServicio(String placa, String tipoServicio) {
		Servicio servicio = null;
		try {
			servicio = this.verificarCupo(tipoServicio);
			this.verificarRestriccionAccesoPorPlaca(placa);
			servicio.setPlacaVehiculo(placa);
		} catch (Exception e) {
			servicio = new Servicio();
			servicio.setError(e.getMessage());
		}
		return servicio;

	}

	@Override
	public Cobro registrarEntrada(Cobro cobro) {
		
		return null;
	}

	@Override
	public Cobro registrarSalida() {
		return null;
	}

	private Servicio verificarCupo(String tipoServicio) throws ParqueaderoException {
		Servicio servicio;
		servicio = this.consultarServicioPorTipo(tipoServicio);
		if (servicio.getCupoDisponible() == 0)
			throw new ParqueaderoException(ParqueaderoUtil.CUPO_NO_DISPONIBLE);

		return servicio;
	}

	private void verificarRestriccionAccesoPorPlaca(String placa) throws ParqueaderoException {
		if (ParqueaderoUtil.getInstance().esPlacaEmpiezaPorA(placa)
				&& !ParqueaderoUtil.getInstance().esDomingoOLunes(new Date())) {
			throw new ParqueaderoException(
					ParqueaderoUtil.NO_ACCESO_PLACA_A);
		}
	}

	public Servicio consultarServicioPorTipo(String tipo) throws ParqueaderoException {
		ServicioEntity servicioEntity = servicioRepository.findByDescripcion(tipo);
		if (servicioEntity == null) {
			throw new ParqueaderoException(ParqueaderoUtil.NO_ENCONTRADO_SERVICIO);
		}
		return new Servicio(servicioEntity);
	}

}
