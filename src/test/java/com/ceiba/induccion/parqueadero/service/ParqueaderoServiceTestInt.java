package com.ceiba.induccion.parqueadero.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ceiba.induccion.parqueadero.entity.CobroEntity;
import com.ceiba.induccion.parqueadero.entity.ServicioEntity;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.model.SolicitudServicio;
import com.ceiba.induccion.parqueadero.repository.CobroRepository;
import com.ceiba.induccion.parqueadero.repository.ServicioRepository;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoServiceTestInt {

	@Autowired	
	ServicioRepository servicioRepository;
	
	@Autowired
	ParqueaderoService parqueaderoService;
	
	@Autowired
	CobroRepository cobroRepository;
	
    @Before
	public void setup() {
		ServicioEntity servicioCarro = servicioRepository.findByDescripcion( ParqueaderoUtil.SERVICIO_PARQUEO_CARRO);
		ServicioEntity servicioMoto = servicioRepository.findByDescripcion( ParqueaderoUtil.SERVICIO_PARQUEO_MOTO);
		if(servicioCarro == null) {
			servicioCarro = new ServicioEntity(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, 20, 20, 1000, 8000);
			servicioRepository.save(servicioCarro);
		}
		
		if(servicioMoto == null) {
			servicioMoto = new ServicioEntity(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, 10, 10, 500, 4000);
			servicioRepository.save(servicioMoto);
		}		
	}
	
	@Test	
	public void verificarDisponibilidadServicioCarroTest() {
         
		// Arrange		
		SolicitudServicio solicitudServicio = new SolicitudServicio("125-OP1", null, ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, null,ParqueaderoUtil.getFecha());		
		Servicio servicioEsperado = null;
		
		try {
			//Action
		    servicioEsperado = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
		} catch (Exception e) {

		}
		// Assert
		Assert.assertTrue(servicioEsperado != null && servicioEsperado.getError() == null);
	}
	
	@Test
	public void verificarDisponibilidadServicioCarroPlacaA() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_EMPIEZA_CON_A, null, ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, null,ParqueaderoUtil.getFechaDiferenteDomingoYLunes());
		Servicio servicio = null;
		try {
			//Action			
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
		Servicio servicioEsperado = null;
		try {
			//Action			
			servicioEsperado = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
		} catch (Exception e) {

		}
		// Assert
		Assert.assertNotNull(servicioEsperado);
	}
	
	
	@Test
	public void verificarDisponibilidadServicioMotoPlacaA() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_EMPIEZA_CON_A, null, ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null,ParqueaderoUtil.getFechaDiferenteDomingoYLunes());
		Servicio servicio;
		try {
			// Action			
			servicio = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
			// Assert
			Assert.assertEquals(ParqueaderoUtil.NO_ACCESO_PLACA_A,servicio.getError());
		} catch (Exception e) {
		
		}		
	}
	
	@Test
	public void verificarDisponibilidadServicioDomingoYLunesPlacaA() {
		// Arrange
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_EMPIEZA_CON_A, null, ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null,ParqueaderoUtil.getFechaDomingoOLunes());
		Servicio servicio = null;
		try {
			// Action			
			servicio = parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);
			// Assert
			Assert.assertTrue(servicio != null && servicio.getError() ==null);
		} catch (Exception e) {
		
		}		
	}
	
	@Test
	public void registrarEntradaCarroTest() {
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_COMUN, null, ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, null,ParqueaderoUtil.getFecha());
		ServicioEntity servicioEntity =  this.servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO);     
		Servicio servicio = new ServicioTestDataBuilder().withId(servicioEntity.getId()).withDescripcion(servicioEntity.getDescripcion()).withSolicitudServicio(solicitudServicio).build();
		CobroEntity cobroEsperado = null;		
		cobroEsperado = parqueaderoService.registrarEntrada(servicio);		
		Assert.assertTrue(cobroEsperado != null && cobroEsperado.getId() != 0);
	}
	
	@Test
	public void registrarEntradaMotoTest() {
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_COMUN, null, ParqueaderoUtil.SERVICIO_PARQUEO_MOTO, null,ParqueaderoUtil.getFecha());
		ServicioEntity servicioEntity =  this.servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO);     
		Servicio servicio = new ServicioTestDataBuilder().withId(servicioEntity.getId()).withDescripcion(servicioEntity.getDescripcion()).withSolicitudServicio(solicitudServicio).build();
		CobroEntity cobroEsperado = null;		
		cobroEsperado = parqueaderoService.registrarEntrada(servicio);		
		Assert.assertTrue(cobroEsperado != null && cobroEsperado.getId() != 0);
	}

}
