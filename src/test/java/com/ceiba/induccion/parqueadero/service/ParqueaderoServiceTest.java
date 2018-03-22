package com.ceiba.induccion.parqueadero.service;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;

public class ParqueaderoServiceTest {
	@Mock
	ParqueaderoService parqueaderoService;

	@Test
	public void verificarDisponibilidadServicioCarroTest() {

		// Arrange
		String placa = "125-OP1";		
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion("carro").build();
		try {
			//
			when(parqueaderoService.consultarServicioPorTipo(servicio.getDescripcion())).thenReturn(servicio);
			servicio = parqueaderoService.verificarDisponibilidadServicio(placa, servicio.getDescripcion());
		} catch (Exception e) {

		}
		// Assert
		Assert.assertNotNull(servicio);

	}

	@Test
	public void verificarDisponibilidadServicioCarroSinCupoDisponibleTest() {
		// Arrange
		String placa = "125-OP1";		
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion("carro").withCupoDisponible(0).build();
		try {
			//
			when(parqueaderoService.consultarServicioPorTipo(servicio.getDescripcion())).thenReturn(servicio);
			servicio = parqueaderoService.verificarDisponibilidadServicio(placa, servicio.getDescripcion());
			// Assert
			Assert.assertEquals(ParqueaderoUtil.CUPO_NO_DISPONIBLE,servicio.getError());
		} catch (Exception e) {
		
		}
		
	}
	
	@Test
	public void verificarDisponibilidadServicioCarroPlacaA() {
		// Arrange
		String placa = "A25-OP1";		
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion("carro").build();
		try {
			//
			when(parqueaderoService.consultarServicioPorTipo(servicio.getDescripcion())).thenReturn(servicio);
			servicio = parqueaderoService.verificarDisponibilidadServicio(placa, servicio.getDescripcion());
			// Assert
			Assert.assertEquals(ParqueaderoUtil.NO_ACCESO_PLACA_A,servicio.getError());
		} catch (Exception e) {
		
		}
		
	}
	
	
	@Test
	public void verificarDisponibilidadServicioMotoTest() {

		// Arrange
		String placa = "125-OP1";		
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion("moto").build();
		try {
			//
			when(parqueaderoService.consultarServicioPorTipo(servicio.getDescripcion())).thenReturn(servicio);
			servicio = parqueaderoService.verificarDisponibilidadServicio(placa, servicio.getDescripcion());
		} catch (Exception e) {

		}
		// Assert
		Assert.assertNotNull(servicio);

	}

	@Test
	public void verificarDisponibilidadServicioMotoSinCupoDisponibleTest() {
		// Arrange
		String placa = "125-OP1";		
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion("moto").withCupoDisponible(0).build();
		try {
			//
			when(parqueaderoService.consultarServicioPorTipo(servicio.getDescripcion())).thenReturn(servicio);
			servicio = parqueaderoService.verificarDisponibilidadServicio(placa, servicio.getDescripcion());
			// Assert
			Assert.assertEquals(ParqueaderoUtil.CUPO_NO_DISPONIBLE,servicio.getError());
		} catch (Exception e) {
		
		}
		
	}
	
	@Test
	public void verificarDisponibilidadServicioMotoPlacaA() {
		// Arrange
		String placa = "A25-OP1";		
		Servicio servicio = new ServicioTestDataBuilder().withDescripcion("moto").build();
		try {
			//
			when(parqueaderoService.consultarServicioPorTipo(servicio.getDescripcion())).thenReturn(servicio);
			servicio = parqueaderoService.verificarDisponibilidadServicio(placa, servicio.getDescripcion());
			// Assert
			Assert.assertEquals(ParqueaderoUtil.NO_ACCESO_PLACA_A,servicio.getError());
		} catch (Exception e) {
		
		}
		
	}

}
