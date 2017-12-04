package dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dominio.Vehiculo;
import testdatabuilder.VehiculoTestDataBuilder;

public class VehiculoTest {
	
	private static final boolean ESTADO = false;
	private static final String TIPO = "carro";
	private static final String PLACA = "FBW234";
	
	@Test
	public void CrearVehiculoTest() {
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
			conTipo(TIPO).
			conPlaca(PLACA).
			conEstado(ESTADO);
	
		// act
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();

		// assert
		assertEquals(TIPO, vehiculo.getTipo());
		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(ESTADO, vehiculo.getEstado());

		
	}
	

}
