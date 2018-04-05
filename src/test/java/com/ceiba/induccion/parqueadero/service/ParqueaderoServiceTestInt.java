package com.ceiba.induccion.parqueadero.service;

import java.util.Calendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ceiba.induccion.parqueadero.entity.CobroEntity;
import com.ceiba.induccion.parqueadero.entity.ServicioEntity;
import com.ceiba.induccion.parqueadero.model.Cobro;
import com.ceiba.induccion.parqueadero.model.CobroCarro;
import com.ceiba.induccion.parqueadero.model.CobroMoto;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.model.SolicitudServicio;
import com.ceiba.induccion.parqueadero.repository.CobroRepository;
import com.ceiba.induccion.parqueadero.repository.ServicioRepository;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;
import org.junit.runners.MethodSorters;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParqueaderoServiceTestInt {

	@Autowired
	ServicioRepository servicioRepository;

	@Autowired
	ParqueaderoService parqueaderoService;

	@Autowired
	CobroRepository cobroRepository;

	@Before
	public void setup() {
		ServicioEntity servicioCarro = servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO);
		ServicioEntity servicioMoto = servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO);
		if (servicioCarro == null) {
			servicioCarro = new ServicioEntity(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, 20, 20, 1000, 8000);
			servicioRepository.save(servicioCarro);
		}

		if (servicioMoto == null) {
			servicioMoto = new ServicioEntity(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, 10, 10, 500, 4000);
			servicioRepository.save(servicioMoto);
		}
	}

	@Test
	public void verificarDisponibilidadServicioCarroTest() {

		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio("125-OP1", null,
				ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, null, ParqueaderoUtil.getFecha());
		Servicio servicioEsperado = null;
		// Action
		servicioEsperado = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);

		// Assert
		Assert.assertTrue(servicioEsperado != null && servicioEsperado.getError() == null);
	}

	@Test
	public void verificarDisponibilidadServicioCarroPlacaA() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_EMPIEZA_CON_A, null,
				ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, null, ParqueaderoUtil.getFechaDiferenteDomingoYLunes());
		Servicio servicio = null;

		// Action
		servicio = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
		// Assert
		Assert.assertEquals(ParqueaderoUtil.NO_ACCESO_PLACA_A, servicio.getError());

	}

	@Test
	public void verificarDisponibilidadServicioMotoTest() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio("1A5-OP1", null,
				ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null, ParqueaderoUtil.getFecha());
		Servicio servicioEsperado = null;

		// Action
		servicioEsperado = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);

		// Assert
		Assert.assertNotNull(servicioEsperado);
	}

	@Test
	public void verificarDisponibilidadServicioMotoPlacaA() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_EMPIEZA_CON_A, null,
				ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null, ParqueaderoUtil.getFechaDiferenteDomingoYLunes());
		Servicio servicio;

		// Action
		servicio = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
		// Assert
		Assert.assertEquals(ParqueaderoUtil.NO_ACCESO_PLACA_A, servicio.getError());

	}

	@Test
	public void verificarDisponibilidadServicioDomingoYLunesPlacaA() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_EMPIEZA_CON_A, null,
				ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null, ParqueaderoUtil.getFechaDomingoOLunes());
		Servicio servicio = null;

		// Action
		servicio = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
		// Assert
		Assert.assertTrue(servicio != null && servicio.getError() == null);

	}

	@Test
	public void registrarEntradaCarroTest() {
		System.out.println("ejecutando  registrarEntradaCarroTest");
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_COMUN, null,
				ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, null, ParqueaderoUtil.getFecha());
		ServicioEntity servicioEntity = this.servicioRepository
				.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO);
		Servicio servicio = new ServicioTestDataBuilder().withId(servicioEntity.getId())
				.withDescripcion(servicioEntity.getDescripcion()).withSolicitudServicio(solicitudServicio).build();
		CobroEntity cobroEsperado = null;
		cobroEsperado = parqueaderoService.registrarEntrada(servicio);
		ServicioEntity servicioEntity2 = this.servicioRepository
				.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO);
		Assert.assertTrue(cobroEsperado != null && cobroEsperado.getId() != 0
				&& ParqueaderoUtil.PLACA_COMUN.equals(cobroEsperado.getPlaca())
				&& servicioEntity2.getCupoDisponible() == (servicioEntity2.getCupoMaximo() - 1));
	}

	@Test
	public void registrarEntradaMotoTest() {
		System.out.println("ejecutando  registrarEntradaMotoTest");
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_COMUN_MOTO, null,
				ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null, ParqueaderoUtil.getFecha());
		ServicioEntity servicioEntity = this.servicioRepository
				.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO);
		Servicio servicio = new ServicioTestDataBuilder().withId(servicioEntity.getId())
				.withDescripcion(servicioEntity.getDescripcion()).withSolicitudServicio(solicitudServicio).build();
		CobroEntity cobroEsperado = null;
		cobroEsperado = parqueaderoService.registrarEntrada(servicio);
		ServicioEntity servicioEntity2 = this.servicioRepository
				.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO);
		Assert.assertTrue(cobroEsperado != null && cobroEsperado.getId() != 0
				&& ParqueaderoUtil.PLACA_COMUN_MOTO.equals(cobroEsperado.getPlaca())
				&& servicioEntity2.getCupoDisponible() == (servicioEntity2.getCupoMaximo() - 1));

	}

	@Test
	public void registrarSalidaCarroCobroTest() {
		System.out.println("ejecutando  registrarSalidaCarroCobroTest");
		long idCobroPendienteCarro = this.registrarEntradaCarroBD();
		CobroEntity cobroEntradaEntity = cobroRepository.findById(idCobroPendienteCarro);
		Cobro cobroSalida = parqueaderoService.registrarSalida(cobroEntradaEntity.getId());
		CobroEntity cobroEntitySalida = cobroRepository.findById(cobroSalida.getId());
		//
		Assert.assertTrue(cobroEntitySalida != null && cobroEntitySalida.getValorServicio() != 0
				&& cobroEntitySalida.getFechaSalida() != null
				&& cobroEntitySalida.getDescripcionTiempoServicio() != null
				&& ParqueaderoUtil.COBRO_FINALIZADO.equals(cobroEntitySalida.getEstado())
				&& cobroRepository.findById(cobroEntradaEntity.getId()) == null);
	}

	@Test
	public void registrarSalidaMotoCobroTest() {
		System.out.println("ejecutando  registrarSalidaMotoCobroTest");
		long idCobroPendienteMoto = this.registrarEntradaMotoBD();
		CobroEntity cobroEntradaEntity = cobroRepository.findById(idCobroPendienteMoto);
		Cobro cobroSalida = parqueaderoService.registrarSalida(cobroEntradaEntity.getId());
		CobroEntity cobroEntitySalida = cobroRepository.findById(cobroSalida.getId());
		//
		Assert.assertTrue(cobroEntitySalida != null && cobroEntitySalida.getValorServicio() != 0
				&& cobroEntitySalida.getFechaSalida() != null
				&& cobroEntitySalida.getDescripcionTiempoServicio() != null
				&& ParqueaderoUtil.COBRO_FINALIZADO.equals(cobroEntitySalida.getEstado())
				&& cobroRepository.findById(cobroEntradaEntity.getId()) == null);
	}

	private long registrarEntradaCarroBD() {
		ServicioEntity servicioCarro = servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO);
		CobroCarro cobroCarro = new CobroTestDataBuilder()
				.withFechaEntrada(ParqueaderoUtil.restarHorasCalendar(Calendar.getInstance(), -5)).withFechaSalida(null)
				.whithEstado("PENDIENTE").withServicio(new Servicio(servicioCarro)).buildCarro();

		CobroEntity cobroEntity = new CobroEntity(cobroCarro);
		cobroRepository.save(cobroEntity);
		return cobroEntity.getId();
	}

	private long registrarEntradaMotoBD() {
		ServicioEntity servicioMoto = servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO);
		CobroMoto cobroMoto = new CobroTestDataBuilder().withPlaca(ParqueaderoUtil.PLACA_COMUN_MOTO).withCilindraje(200)
				.withFechaEntrada(ParqueaderoUtil.restarHorasCalendar(Calendar.getInstance(), -5)).withFechaSalida(null)
				.whithEstado("PENDIENTE").withServicio(new Servicio(servicioMoto)).buildMoto();

		CobroEntity cobroEntity2 = new CobroEntity(cobroMoto);
		cobroRepository.save(cobroEntity2);
		return cobroEntity2.getId();
	}

}
