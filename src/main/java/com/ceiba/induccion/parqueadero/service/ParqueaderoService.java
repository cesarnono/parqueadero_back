package com.ceiba.induccion.parqueadero.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private static final Logger logger = LoggerFactory.getLogger(ParqueaderoService.class);

	@Override
	public Servicio verificarDisponibilidadServicio(SolicitudServicio solicitudServicio) {
		Servicio servicio = new Servicio();
		servicio.setError(ParqueaderoUtil.SERVICIO_NO_ENCONTRADO);
		try {
			if (solicitudServicio != null) {
				servicio = this.verificarCupo(solicitudServicio.getTipo());
				if (solicitudServicio.getPlaca() != null) {
					this.verificarRestriccionAccesoPorPlaca(solicitudServicio.getPlaca(), solicitudServicio.getFecha());
				}
				servicio.setSolicitudServicio(solicitudServicio);
			}		

		} catch (Exception e) {
			servicio = new Servicio();
			servicio.setError(e.getMessage());
			logger.error(e.getMessage());
		}
		return servicio;
	}

	@Override
	@Transactional
	public CobroEntity registrarEntrada(Servicio servicio) {
		CobroEntity cobroEntity = null;
		servicio = this.verificarDisponibilidadServicio(servicio != null ? servicio.getSolicitudServicio() : null);
		if (servicio != null && servicio.getError() != null) {
			return this.crearCobroEntityConError(servicio.getError());
		}
		cobroEntity = crearCobroEntity(servicio);
		if (cobroEntity == null) {
			return this.crearCobroEntityConError(ParqueaderoUtil.ERROR_REGISTRAR_ENTRADA);
		}
		cobroRepository.save(cobroEntity);
		servicioRepository.descontarCupoDisponible(cobroEntity.getServicio().getId());
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
		if (servicio != null && servicio.getSolicitudServicio() != null
				&& servicio.getSolicitudServicio().getCilindraje() == null) {
			cobro = new CobroCarro(-1, servicio.getSolicitudServicio().getPlaca(), Calendar.getInstance(), null,
					ParqueaderoUtil.COBRO_PENDIENTE, 0,  servicio);
		} else if (servicio != null && servicio.getSolicitudServicio() != null
				&& servicio.getSolicitudServicio().getCilindraje() != null) {
			cobro = new CobroMoto(-1, servicio.getSolicitudServicio().getPlaca(),
					Integer.parseInt(servicio.getSolicitudServicio().getCilindraje()), Calendar.getInstance(), null,
					ParqueaderoUtil.COBRO_PENDIENTE,  servicio);
		}
		return cobro;
	}

	@Override
	@Transactional
	public Cobro registrarSalida(long idCobro) {
		Cobro cobro = null;
		CobroEntity cobroEntity = this.cobroRepository.findById(idCobro);
		if (cobroEntity != null) {
			cobro = cobroEntity.getCilindraje() != 0 ? new CobroMoto(cobroEntity) : new CobroCarro(cobroEntity);
			cobro.calcularValorServicio();
			this.cobroRepository.delete(cobroEntity);
			CobroEntity cobroEntityFinalizado = new CobroEntity(cobro);
			this.cobroRepository.save(cobroEntityFinalizado);
			this.cobroRepository.actualizarEstadoCobro(ParqueaderoUtil.COBRO_FINALIZADO, cobroEntityFinalizado.getId());
			this.servicioRepository.aumentarCupoDisponible(cobroEntityFinalizado.getServicio().getId());
			cobro.setId(cobroEntityFinalizado.getId());
		}
		return cobro;
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
			throw new ParqueaderoException(ParqueaderoUtil.SERVICIO_NO_ENCONTRADO);
		}
		return new Servicio(servicioEntity);
	}

	@Override
	public List<CobroEntity> consultarCobros(String estado) {
		return this.cobroRepository.findAllByEstadoOrderByFechaEntradaDesc(estado);
	}

	public CobroEntity crearCobroEntityConError(String error) {
		CobroEntity cobroEntity = new CobroEntity();
		cobroEntity.setError(error);
		return cobroEntity;
	}
}
