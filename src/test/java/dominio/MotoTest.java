package dominio;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import dominio.Moto;
import dominio.Vehiculo;
import testdatabuilder.MotoTestDataBuilder;
public class MotoTest {
	
	private static final boolean ESTADO = false;
	private static final String TIPO = "moto";
	private static final String PLACA = "FBW23D";
	private static final int CILINDRAJE = 1000;
	
	@Test
	public void CrearMotoTest() {
		// arrange
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().
			conTipo(TIPO).
			conPlaca(PLACA).
			conEstado(ESTADO).
			conCilindraje(CILINDRAJE);
		
	
		// act
		Moto moto = motoTestDataBuilder.build();

		// assert
		assertEquals(TIPO, moto.getTipo());
		assertEquals(PLACA, moto.getPlaca());
		assertEquals(ESTADO, moto.getEstado());
		assertEquals(CILINDRAJE, moto.getCilindraje());

		
	}

}
