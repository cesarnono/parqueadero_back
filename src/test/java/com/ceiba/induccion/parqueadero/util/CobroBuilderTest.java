package com.ceiba.induccion.parqueadero.util;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import com.ceiba.induccion.parqueadero.model.Cobro;
import com.ceiba.induccion.parqueadero.model.Servicio;

public class CobroBuilderTest {

	@Test
	public void cobroBuilderTest() {
		CobroBuilder cobroBuilder = new CobroBuilder();
		cobroBuilder.withCilindraje(500);
		cobroBuilder.withPlaca(ParqueaderoUtil.PLACA_COMUN);
		cobroBuilder.withFechaEntrada(Calendar.getInstance());
		cobroBuilder.withFechaSalida(Calendar.getInstance());
		cobroBuilder.whithEstado("PENDIENTE");
		cobroBuilder.withServicio(new Servicio());
		Cobro cobro = cobroBuilder.buildCarro();
		assertTrue(cobro != null );
	}

}
