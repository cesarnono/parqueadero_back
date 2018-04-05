package com.ceiba.induccion.parqueadero.service;
import java.util.Calendar;
import com.ceiba.induccion.parqueadero.model.CobroCarro;
import com.ceiba.induccion.parqueadero.model.CobroMoto;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;

public class CobroTestDataBuilder {

	private static final Calendar FECHA_ENTRADA = ParqueaderoUtil.getFechaCalendar("dd-M-yyyy HH:mm:ss",
			"18-03-2018 01:00:00");
	private static final Calendar FECHA_SALIDA = ParqueaderoUtil.getFechaCalendar("dd-M-yyyy HH:mm:ss",
			"18-03-2018 06:10:00");
	private static final String ESTADO = "PENDIENTE";
	private static final String PLACA = ParqueaderoUtil.PLACA_COMUN;

	private long id;
	private Calendar fechaEntrada;
	private Calendar fechaSalida;
	private String estado;
	protected long valorServicio;
	protected long tiempoServicioHoras;
	protected String descripcionTiempoServicio;
	private Servicio servicio;	
	private String placa;
	private int cilindraje;

	public CobroTestDataBuilder() {
		this.fechaEntrada = FECHA_ENTRADA;
		this.fechaSalida = FECHA_SALIDA;
		this.estado = ESTADO;
		this.placa = PLACA;
		this.servicio = new ServicioTestDataBuilder().withDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO)
				.withCupoMaximo(20).withTarifaDia(ParqueaderoUtil.TARIFA_DIA_CARRO)
				.withTarifaHora(ParqueaderoUtil.TARIFA_HORA_CARRO).build();
	}

	public CobroTestDataBuilder withFechaEntrada(Calendar fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
		return this;
	}

	public CobroTestDataBuilder withFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public CobroTestDataBuilder whithEstado(String estado) {
		this.estado = estado;
		return this;
	}

	public CobroTestDataBuilder withServicio(Servicio servicio) {
		this.servicio = servicio;
		return this;
	}

	public CobroTestDataBuilder withCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	
	public CobroTestDataBuilder withId(long id) {
		this.id = id;
		return this;
	}
	
	public CobroTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public CobroCarro buildCarro() {
		return new CobroCarro(this.id, this.placa, this.fechaEntrada, this.fechaSalida, this.estado, this.valorServicio,
				 this.servicio);
	}

	public CobroMoto buildMoto() {
		return new CobroMoto(this.id, this.placa, this.cilindraje, this.fechaEntrada, this.fechaSalida, this.estado,
				this.servicio);
	}

}
