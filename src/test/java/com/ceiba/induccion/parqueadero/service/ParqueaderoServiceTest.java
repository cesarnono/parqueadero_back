package com.ceiba.induccion.parqueadero.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;
import java.util.Calendar;
import static org.mockito.Mockito.*;
import com.ceiba.induccion.parqueadero.entity.CobroEntity;
import com.ceiba.induccion.parqueadero.entity.ServicioEntity;
import com.ceiba.induccion.parqueadero.model.Cobro;
import com.ceiba.induccion.parqueadero.model.CobroCarro;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.model.SolicitudServicio;
import com.ceiba.induccion.parqueadero.repository.CobroRepository;
import com.ceiba.induccion.parqueadero.repository.ServicioRepository;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoServiceTest {
	@MockBean
	ServicioRepository servicioRepository;
	
	@Autowired
	ParqueaderoService parqueaderoService;
	
	@MockBean
	CobroRepository cobroRepository;	

	@Test
	public void verificarDisponibilidadServicioCarroTest() {

		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio("125-OP1", null, ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, null,ParqueaderoUtil.getFecha());
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO).build();
		Servicio servicioEsperado = null;
		try {
			//Action			
			when(servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO)).thenReturn(new ServicioEntity(servicio));
		    servicioEsperado = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
		} catch (Exception e) {

		}
		// Assert
		Assert.assertNotNull(servicioEsperado);
	}

	@Test
	public void verificarDisponibilidadServicioCarroSinCupoDisponibleTest() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio("125-OP1", null, ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, null,ParqueaderoUtil.getFecha());
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO).withCupoDisponible(0).build();
		try {
			//Action
			when(servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO)).thenReturn(new ServicioEntity(servicio));
			servicio = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
			// Assert
			Assert.assertEquals(ParqueaderoUtil.CUPO_NO_DISPONIBLE,servicio.getError());
		} catch (Exception e) {
		
		}
		
	}
	
	@Test
	public void verificarDisponibilidadServicioCarroPlacaA() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_EMPIEZA_CON_A, null, ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, null,ParqueaderoUtil.getFecha());
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO).build();
		try {
			//Action
			when(ParqueaderoUtil.getFecha()).thenReturn(ParqueaderoUtil.getFechaDiferenteDomingoYLunes());
			when(servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO)).thenReturn(new ServicioEntity(servicio));
			servicio = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
			// Assert
			Assert.assertEquals(ParqueaderoUtil.NO_ACCESO_PLACA_A,servicio.getError());
		} catch (Exception e) {
		
		}
		
	}
	
	
	@Test
	public void verificarDisponibilidadServicioMotoTest() {
		// Arrange		
		SolicitudServicio solicitudServicio = new SolicitudServicio("1A5-OP1", null, ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null,ParqueaderoUtil.getFecha());
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO).build();
		Servicio servicioEsperado = null;
		try {
			//Action
			when(servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO)).thenReturn(new ServicioEntity(servicio));
			servicioEsperado = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
		} catch (Exception e) {

		}
		// Assert
		Assert.assertNotNull(servicioEsperado);
	}

	@Test
	public void verificarDisponibilidadServicioMotoSinCupoDisponibleTest() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio("1A5-OP1", null, ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null,ParqueaderoUtil.getFecha());
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO).withCupoDisponible(0).build();
		try {
			//Action
			when(servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO)).thenReturn(new ServicioEntity(servicio));
			servicio = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
			// Assert
			Assert.assertEquals(ParqueaderoUtil.CUPO_NO_DISPONIBLE,servicio.getError());
		} catch (Exception e) {
		
		}
		
	}
	
	@Test
	public void verificarDisponibilidadServicioMotoPlacaA() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_EMPIEZA_CON_A, null, ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null,ParqueaderoUtil.getFechaDiferenteDomingoYLunes());
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO).build();
		try {
			// Action
			when(servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO)).thenReturn(new ServicioEntity(servicio));			
			servicio = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
			// Assert
			Assert.assertEquals(ParqueaderoUtil.NO_ACCESO_PLACA_A,servicio.getError());
		} catch (Exception e) {
		
		}		
	}
	
	
	@Test
	public void verificarDisponibilidadServicioDomingoOLunesPlacaA() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_EMPIEZA_CON_A, null, ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null,ParqueaderoUtil.getFechaDomingoOLunes());
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO).build();
		Servicio servicioEsperado= null;
		try {
			// Action
			when(servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO)).thenReturn(new ServicioEntity(servicio));			
			servicioEsperado = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
			// Assert
			Assert.assertTrue(servicioEsperado != null && servicioEsperado.getError() == null);
		} catch (Exception e) {
		
		}		
	}
	
	@Test
	public void registrarEntradaCarroTest() {
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_COMUN, null, ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, null,ParqueaderoUtil.getFecha());
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO).withSolicitudServicio(solicitudServicio).build();
		CobroEntity cobroEsperado = null;
		when(cobroRepository.save(null)).thenReturn(parqueaderoService.crearCobroEntity(servicio));
		cobroEsperado = parqueaderoService.registrarEntrada(servicio);
		Assert.assertTrue(cobroEsperado != null && cobroEsperado.getError() == null);
	}
	
	@Test
	public void registrarEntradaMotoTest() {
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_COMUN, null, ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null,ParqueaderoUtil.getFecha());
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO).withSolicitudServicio(solicitudServicio).build();
		CobroEntity cobroEsperado = null;
		when(cobroRepository.save(null)).thenReturn(parqueaderoService.crearCobroEntity(servicio));
		cobroEsperado = parqueaderoService.registrarEntrada(servicio);
		Assert.assertTrue(cobroEsperado != null && cobroEsperado.getError() == null);
	}
	
	@Test
	public void errorRegistrarEntradaTest() {
		Servicio servicio = null;
		CobroEntity cobroEsperado = null;
		cobroEsperado = parqueaderoService.registrarEntrada(servicio);
		Assert.assertTrue(cobroEsperado != null && cobroEsperado.getError() != null && ParqueaderoUtil.ERROR_REGISTRAR_ENTRADA.equals( cobroEsperado.getError()));
	}	
	
	@Test
	public void registrarSalidaCarroCobroTest() {	
		CobroCarro cobroCarro = new CobroTestDataBuilder().buildCarro();
		CobroEntity cobroEntity    = new CobroEntity(cobroCarro);
		when(cobroRepository.findById(1L)).thenReturn(cobroEntity);		
		doNothing().when(cobroRepository).delete(cobroEntity);
		when(cobroRepository.save(cobroEntity)).thenReturn(cobroEntity);
		when(cobroRepository.actualizarEstadoCobro("FINALIZADA", 1L)).thenReturn(1);
		when(servicioRepository.aumentarCupoDisponible(1L)).thenReturn(1);
		//
		Cobro cobroEsperado = parqueaderoService.registrarSalida(1L);
		//
		Assert.assertTrue(cobroEsperado != null && cobroEsperado.getValorServicio() != 0);		
	}
	
	
	@Test
	public void registrarSalidaCarroCobroCincoHorasTest() {	
		CobroCarro cobroCarro = new CobroTestDataBuilder().withFechaSalida(Calendar.getInstance()).withFechaEntrada(ParqueaderoUtil.restarHorasCalendar(Calendar.getInstance(), -5)).buildCarro();		
		CobroEntity cobroEntity    = new CobroEntity(cobroCarro);
		when(cobroRepository.findById(1L)).thenReturn(cobroEntity);		
		doNothing().when(cobroRepository).delete(cobroEntity);
		when(cobroRepository.save(cobroEntity)).thenReturn(cobroEntity);
		when(cobroRepository.actualizarEstadoCobro("FINALIZADA", 1L)).thenReturn(1);
		when(servicioRepository.aumentarCupoDisponible(1L)).thenReturn(1);
		//
		Cobro cobroEsperado = parqueaderoService.registrarSalida(1L);
		//
		Assert.assertTrue(cobroEsperado != null && cobroEsperado.getValorServicio() == (ParqueaderoUtil.TARIFA_HORA_CARRO*5));		
	}
	
	
	@Test
	public void registrarSalidaCarroCobroNueveHorasTest() {	
		CobroCarro cobroCarro = new CobroTestDataBuilder().withFechaSalida(Calendar.getInstance()).withFechaEntrada(ParqueaderoUtil.restarHorasCalendar(Calendar.getInstance(), -9)).buildCarro();		
		CobroEntity cobroEntity    = new CobroEntity(cobroCarro);
		when(cobroRepository.findById(1L)).thenReturn(cobroEntity);		
		doNothing().when(cobroRepository).delete(cobroEntity);
		when(cobroRepository.save(cobroEntity)).thenReturn(cobroEntity);
		when(cobroRepository.actualizarEstadoCobro("FINALIZADA", 1L)).thenReturn(1);
		when(servicioRepository.aumentarCupoDisponible(1L)).thenReturn(1);		
		//
		Cobro cobroEsperado = parqueaderoService.registrarSalida(1L);		
		//
		Assert.assertTrue(cobroEsperado != null && cobroEsperado.getValorServicio() == (ParqueaderoUtil.TARIFA_DIA_CARRO*1));		
	}
	
	@Test
	public void registrarSalidaMotoCilindraje550CobroCincoHorasTest() {	
		Cobro  cobroMoto = new CobroTestDataBuilder()
				.withFechaSalida(Calendar.getInstance()).withFechaEntrada(ParqueaderoUtil.restarHorasCalendar(Calendar.getInstance(), -5))
				.withServicio( new ServicioTestDataBuilder().withDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO)
				.withCupoMaximo(20).withTarifaDia(ParqueaderoUtil.TARIFA_DIA_MOTO)
				.withTarifaHora(ParqueaderoUtil.TARIFA_HORA_MOTO).build())
				.withCilindraje(ParqueaderoUtil.CILINDRAJE_MOTO_550).buildMoto();		
		CobroEntity cobroEntity    = new CobroEntity(cobroMoto);
		when(cobroRepository.findById(1L)).thenReturn(cobroEntity);		
		doNothing().when(cobroRepository).delete(cobroEntity);
		when(cobroRepository.save(cobroEntity)).thenReturn(cobroEntity);
		when(cobroRepository.actualizarEstadoCobro("FINALIZADA", 1L)).thenReturn(1);
		when(servicioRepository.aumentarCupoDisponible(1L)).thenReturn(1);
		//
		Cobro cobroEsperado = parqueaderoService.registrarSalida(1L);
		long valorServicio = ((ParqueaderoUtil.TARIFA_HORA_MOTO*5)+ParqueaderoUtil.RECARGO_CILINDRAJE_MOTO_500);
		//
		Assert.assertTrue(cobroEsperado != null && cobroEsperado.getValorServicio() == valorServicio);		
	}

}
