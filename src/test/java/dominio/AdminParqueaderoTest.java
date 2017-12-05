package dominio;

import static org.junit.Assert.assertEquals;

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
}
