package dominio;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import testdatabuilder.CeldaParqueoTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

public class CeldaParqueoTest {

	Calendar fechaIngreso;
	Calendar fechaSalida;
	private Vehiculo vehiculoCarro;
	private Vehiculo vehiculoMoto;
	
	@Before
	public void init() {
		vehiculoCarro = new VehiculoTestDataBuilder().conTipo("carro").build();
		vehiculoMoto = new VehiculoTestDataBuilder().conTipo("moto").build();

	}
    @Test
	public void CrearCeldaParqueoCarroTest() {
		fechaIngreso = Calendar.getInstance();
		fechaSalida = Calendar.getInstance();
		fechaSalida.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + 3);
		// act
		CeldaParqueo celdaParqueo = new CeldaParqueoTestDataBuilder()
				.conVehiculo(vehiculoCarro).conFechaIngreso(fechaIngreso).conFechaSalida(fechaSalida).build();

		// assert
		assertEquals(fechaIngreso, celdaParqueo.getHoraIngreso());
		assertEquals(fechaSalida, celdaParqueo.getHoraSalida());
		assertEquals("carro", celdaParqueo.getVehiculo().getTipo());

	}
    @Test
    public void CrearCeldaParqueoMotoTest() {
		fechaIngreso = Calendar.getInstance();
		fechaSalida = Calendar.getInstance();
		fechaSalida.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + 3);
		// act
		CeldaParqueo celdaParqueo = new CeldaParqueoTestDataBuilder()
				.conVehiculo(vehiculoMoto).conFechaIngreso(fechaIngreso).conFechaSalida(fechaSalida).build();

		// assert
		assertEquals(fechaIngreso, celdaParqueo.getHoraIngreso());
		assertEquals(fechaSalida, celdaParqueo.getHoraSalida());
		assertEquals("moto", celdaParqueo.getVehiculo().getTipo());

	}
}
