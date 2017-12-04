package dominio;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import dominio.Vehiculo;
import dominio.Vigilante;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.sistema.SistemaPersistencia;

public class VigilanteTest {
	
	private SistemaPersistencia sistemaPersistencia;
	
	private RepositorioVehiculo repositoriovehiculo;

	@Before
	public void setUp() {
		
		sistemaPersistencia = new SistemaPersistencia();
		
		repositoriovehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
		
		sistemaPersistencia.iniciar();
	}
	@After
	public void tearDown() {
		sistemaPersistencia.terminar();
	}
	
	@Test
	public void ingresarCarroTest() {
		Vehiculo carro = new Carro("carro", "FBW234", false);
		Vigilante vigilante = new Vigilante(repositoriovehiculo);
		vigilante.ingresarVehiculo(carro);
		
		assertEquals("FBW234", repositoriovehiculo.obtenerPorPlaca("FBW234").getPlaca());
		//assertNotNull(carro);
		
	}
	@Test
	public void ingresarMotoTest() {
		Vehiculo moto = new Moto("moto", "FBW23D", false, 1000);
		Vigilante vigilante = new Vigilante(repositoriovehiculo);
		vigilante.ingresarVehiculo(moto);
		
		assertEquals("FBW23D", repositoriovehiculo.obtenerPorPlaca("FBW23D").getPlaca());
		//assertNotNull(moto);
		
	}
}
