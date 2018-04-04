package com.ceiba.induccion.parqueadero.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ceiba.induccion.parqueadero.entity.CobroEntity;
import com.ceiba.induccion.parqueadero.entity.ServicioEntity;
import com.ceiba.induccion.parqueadero.model.CobroCarro;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.model.SolicitudServicio;
import com.ceiba.induccion.parqueadero.repository.CobroRepository;
import com.ceiba.induccion.parqueadero.repository.ServicioRepository;
import com.ceiba.induccion.parqueadero.service.CobroTestDataBuilder;
import com.ceiba.induccion.parqueadero.service.ParqueaderoService;
import com.ceiba.induccion.parqueadero.service.ServicioTestDataBuilder;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ParqueaderoController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParqueaderoControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	ServicioRepository servicioRepository;
	
	@MockBean
	CobroRepository cobroRepository;

	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
    private ParqueaderoService service;

	private ServicioEntity servicioCarro;
	private ServicioEntity servicioMoto;

	@Before
	public void setup() {
		 servicioCarro = servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO);
		 servicioMoto = servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_MOTO);
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
	public void testRegistrarEntradaCarro() throws Exception {
		//
		SolicitudServicio solicitudServicio = new SolicitudServicio(ParqueaderoUtil.PLACA_COMUN, null,
				ParqueaderoUtil.SERVICIO_PARQUEO_CARRO, null, ParqueaderoUtil.getFecha());
		Servicio servicio = new ServicioTestDataBuilder().withId(servicioCarro.getId())
				.withDescripcion(servicioCarro.getDescripcion()).withSolicitudServicio(solicitudServicio).build();
		this.mvc.perform(post("/entrada").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(servicio))).andExpect(status().isOk());		
	}
}
