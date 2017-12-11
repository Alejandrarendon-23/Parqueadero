package dominio;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import dominio.repositorio.RepositorioVehiculo;
import persistencia.sistema.SistemaPersistencia;
import servicio.AdminParqueaderoServicio;
import servicio.AdminParqueaderoServicioImpl;

public class AdminParqueaderoTest {
	
	private SistemaPersistencia sistemaPersistencia;
	
	private RepositorioVehiculo repositoriovehiculo;
	Calendar fechaIngreso;
	Calendar fechaSalida;

	@Before
	public void setUp() {
		
		sistemaPersistencia = new SistemaPersistencia();
		
		repositoriovehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
		
		
	}
	@Test
	public void ingresarCarroTest() {
		Vehiculo carro = new Carro("carro", "FBW234", false);
		AdminParqueaderoServicio vigilante = new AdminParqueaderoServicioImpl();
		vigilante.ingresarVehiculo(carro);
		
		assertEquals("FBW234", repositoriovehiculo.obtenerPorPlaca("FBW234").getPlaca());
		//assertNotNull(carro);
		
	}
	@Test
	public void ingresarMotoTest() {
		Vehiculo moto = new Moto("moto", "FBW23D", false, 1000);
		AdminParqueaderoServicio vigilante = new AdminParqueaderoServicioImpl();
		vigilante.ingresarVehiculo(moto);
		
		assertEquals("FBW23D", repositoriovehiculo.obtenerPorPlaca("FBW23D").getPlaca());
		//assertNotNull(moto);
		
	}
	@Test
	public void calcularValorTotalPorTiempoCarroTest() {
		Vehiculo carro = new Carro("carro", "FBW234", false);
		fechaIngreso = Calendar.getInstance();
		fechaSalida = Calendar.getInstance();
		fechaSalida.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + 3);
		CeldaParqueo celda = new CeldaParqueo(carro, fechaIngreso, fechaSalida);
		AdminParqueaderoServicio valor = new AdminParqueaderoServicioImpl();
		valor.calcularValorTotalPorTiempo(celda);
		
		//assertEquals(7000, valor.calcularValorTotalPorTiempo(celda)+3000);
		assertEquals(fechaIngreso, celda.getHoraIngreso());
		assertEquals(fechaSalida, celda.getHoraSalida());
		assertEquals("carro", celda.getVehiculo().getTipo());
			
		}
	@Test
	public void calcularValorTotalPorTiempoMotoTest() {
		Vehiculo moto = new Moto("moto", "FBW234", false, 100);
		fechaIngreso = Calendar.getInstance();
		fechaSalida = Calendar.getInstance();
		fechaSalida.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + 3);
		CeldaParqueo celda = new CeldaParqueo(moto, fechaIngreso, fechaSalida);
		AdminParqueaderoServicio valor = new AdminParqueaderoServicioImpl();
		valor.calcularValorTotalPorTiempo(celda);
		
		//assertEquals(7000, valor.calcularValorTotalPorTiempo(celda)+3000);
		assertEquals(fechaIngreso, celda.getHoraIngreso());
		assertEquals(fechaSalida, celda.getHoraSalida());
		assertEquals("moto", celda.getVehiculo().getTipo());
			
		}
	/*@Test
	public void retiroCarroTest() {
		Vehiculo carro = new Carro("carro", "FBW234", false);
		AdminParqueaderoServicio vigilante = new AdminParqueaderoServicioImpl();
		vigilante.retirarVehiculo(carro);
		
		assertEquals("FBW234", repositoriovehiculo.obtenerPorPlaca("FBW234").getPlaca());
		
		
		


	}*/

}
