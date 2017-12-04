package dominio;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dominio.Vehiculo;
import testdatabuilder.CarroTestDataBuilder;

public class CarroTest {
	
	private static final boolean ESTADO = false;
	private static final String TIPO = "carro";
	private static final String PLACA = "FBW234";
	
	@Test
	public void CrearVehiculoTest() {
		// arrange
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().
			conTipo(TIPO).
			conPlaca(PLACA).
			conEstado(ESTADO);
	
		// act
		Vehiculo vehiculo = carroTestDataBuilder.build();

		// assert
		assertEquals(TIPO, vehiculo.getTipo());
		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(ESTADO, vehiculo.getEstado());

		
	}

}
