package com.ceiba.induccion.parqueadero.service;

import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ceiba.induccion.parqueadero.entity.CobroEntity;
import com.ceiba.induccion.parqueadero.entity.ServicioEntity;
import com.ceiba.induccion.parqueadero.exception.ParqueaderoException;
import com.ceiba.induccion.parqueadero.model.Cobro;
import com.ceiba.induccion.parqueadero.model.CobroCarro;
import com.ceiba.induccion.parqueadero.model.CobroMoto;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.model.SolicitudServicio;
import com.ceiba.induccion.parqueadero.repository.CobroRepository;
import com.ceiba.induccion.parqueadero.repository.ServicioRepository;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;

@Service
public class ParqueaderoService implements IParqueaderoService {

	@Autowired
	ServicioRepository servicioRepository;

	@Autowired
	CobroRepository cobroRepository;

	@Override
	public Servicio verificarDisponibilidadServicio(SolicitudServicio solicitudServicio) {
		Servicio servicio = null;
		try {
			if (solicitudServicio != null) {
				servicio = this.verificarCupo(solicitudServicio.getTipo());
				this.verificarRestriccionAccesoPorPlaca(solicitudServicio.getPlaca(), solicitudServicio.getFecha());
				servicio.setSolicitudServicio(solicitudServicio);
			}

		} catch (Exception e) {
			servicio = new Servicio();
			servicio.setError(e.getMessage());
		}
		return servicio;

	}

	@Override	
	public CobroEntity registrarEntrada(Servicio servicio) {
		CobroEntity cobroEntity = crearCobroEntity(servicio);
		if (cobroEntity == null) {
			cobroEntity = new CobroEntity();
			cobroEntity.setError(ParqueaderoUtil.ERROR_REGISTRAR_ENTRADA);
		}
		cobroRepository.save(cobroEntity);
		return cobroEntity;
	}

	public CobroEntity crearCobroEntity(Servicio servicio) {
		CobroEntity cobroEntity = null;
		Cobro cobro = this.crearCobroEntradaParqueadero(servicio);
		if (cobro != null) {
			cobroEntity = new CobroEntity(cobro);
		}
		return cobroEntity;
	}

	private Cobro crearCobroEntradaParqueadero(Servicio servicio) {
		Cobro cobro = null;
		if (servicio != null && servicio.getSolicitudServicio().getCilindraje() == null) {
			cobro = new CobroCarro(-1,servicio.getSolicitudServicio().getPlaca(), Calendar.getInstance(), null,
					ParqueaderoUtil.COBRO_PENDIENTE, 0, 0, null, servicio);
		} else if (servicio != null && servicio.getSolicitudServicio().getPlaca() == null) {
			cobro = new CobroMoto(-1,servicio.getSolicitudServicio().getCilindraje(),
					servicio.getSolicitudServicio().getPlaca(), Calendar.getInstance(), null,
					ParqueaderoUtil.COBRO_PENDIENTE, 0, 0, null, servicio);
		}
		return cobro;
	}

	@Override
	public Cobro registrarSalida(long idCobro) {	
		Cobro cobro = null;
		CobroEntity cobroEntity = this.cobroRepository.getOne(idCobro);
		if(cobroEntity != null) {
			if(cobroEntity.getCilindraje() != null) {
				cobro = new CobroMoto(cobroEntity);
				cobro.setFechaSalida(Calendar.getInstance());
				cobro.calcularValorServicio();
			}else {
				cobro = new CobroCarro(cobroEntity);
				cobro.setFechaSalida(Calendar.getInstance());
				cobro.calcularValorServicio();
			}
		    this.cobroRepository.delete(cobroEntity);
		    CobroEntity cobroEntityFinalizado = new CobroEntity(cobro);
		    this.cobroRepository.save(cobroEntityFinalizado);
		}
		
		return null;
	}

	private Servicio verificarCupo(String tipoServicio) throws ParqueaderoException {
		Servicio servicio;
		servicio = this.consultarServicioPorTipo(tipoServicio);
		if (servicio.getCupoDisponible() == 0)
			throw new ParqueaderoException(ParqueaderoUtil.CUPO_NO_DISPONIBLE);

		return servicio;
	}

	private void verificarRestriccionAccesoPorPlaca(String placa, Date fecha) throws ParqueaderoException {
		if (ParqueaderoUtil.getInstance().esPlacaEmpiezaPorA(placa)
				&& !ParqueaderoUtil.getInstance().esDomingoOLunes(fecha)) {
			throw new ParqueaderoException(ParqueaderoUtil.NO_ACCESO_PLACA_A);
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
