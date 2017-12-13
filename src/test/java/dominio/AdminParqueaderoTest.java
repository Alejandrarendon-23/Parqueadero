package dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import dominio.repositorio.RepositorioVehiculo;
import persistencia.builder.VehiculoBuilder;
import persistencia.entidad.VehiculoEntity;
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
		assertNotNull(carro);

	}

	@Test
	public void ingresarMotoTest() {
		Vehiculo moto = new Moto("moto", "FBW23D", false, 1000);
		AdminParqueaderoServicio vigilante = new AdminParqueaderoServicioImpl();
		vigilante.ingresarVehiculo(moto);

		assertEquals("FBW23D", repositoriovehiculo.obtenerPorPlaca("FBW23D").getPlaca());
		assertNotNull(moto);

	}

	@Test
	public void calcularValorTotalPorTiempoCarroTest() {
		Vehiculo carro = new Carro("carro", "FBW234", false);
		fechaIngreso = Calendar.getInstance();
		fechaSalida = Calendar.getInstance();
		fechaSalida.add(Calendar.HOUR, 72);
		CeldaParqueo celda = new CeldaParqueo(carro, fechaIngreso, fechaSalida);
		AdminParqueaderoServicio valor = new AdminParqueaderoServicioImpl();

		assertEquals(24000, valor.calcularValorTotalPorTiempo(celda));
		assertEquals(fechaIngreso, celda.getHoraIngreso());
		assertEquals(fechaSalida, celda.getHoraSalida());
		assertEquals("carro", celda.getVehiculo().getTipo());

	}

	@Test
	public void calcularValorTotalPorTiempoMotoTest() {
		Vehiculo moto = new Moto("moto", "FBW23D", false, 100);
		fechaIngreso = Calendar.getInstance();
		fechaSalida = Calendar.getInstance();
		fechaSalida.add(Calendar.HOUR, 75);
		CeldaParqueo celda = new CeldaParqueo(moto, fechaIngreso, fechaSalida);
		AdminParqueaderoServicio valor = new AdminParqueaderoServicioImpl();

		assertEquals(13500, valor.calcularValorTotalPorTiempo(celda));
		assertEquals(fechaIngreso, celda.getHoraIngreso());
		assertEquals(fechaSalida, celda.getHoraSalida());
		assertEquals("moto", celda.getVehiculo().getTipo());

	}

	@Test
	public void precioTotalPorVehiculoMotoTest() {
		Vehiculo moto = new Moto("moto", "FBW23D", false, 600);
		fechaIngreso = Calendar.getInstance();
		fechaSalida = Calendar.getInstance();
		fechaSalida.add(Calendar.HOUR, 75);
		CeldaParqueo celda = new CeldaParqueo(moto, fechaIngreso, fechaSalida);
		AdminParqueaderoServicio valor = new AdminParqueaderoServicioImpl();

		assertEquals(15500, valor.precioTotalPorVehiculo(celda));
		assertEquals(fechaIngreso, celda.getHoraIngreso());
		assertEquals(fechaSalida, celda.getHoraSalida());
		assertEquals("moto", celda.getVehiculo().getTipo());

	}

	@Test
	public void precioTotalPorVehiculoCarroTest() {
		Vehiculo carro = new Carro("carro", "FBW234", false);
		fechaIngreso = Calendar.getInstance();
		fechaSalida = Calendar.getInstance();
		fechaSalida.add(Calendar.HOUR, 72);
		CeldaParqueo celda = new CeldaParqueo(carro, fechaIngreso, fechaSalida);
		AdminParqueaderoServicio valor = new AdminParqueaderoServicioImpl();

		assertEquals(24000, valor.precioTotalPorVehiculo(celda));
		assertEquals(fechaIngreso, celda.getHoraIngreso());
		assertEquals(fechaSalida, celda.getHoraSalida());
		assertEquals("carro", celda.getVehiculo().getTipo());

	}

	@Test
	public void retiroCarroTest() {
		Vehiculo carro = new Carro("carro", "FBW235", false);
		AdminParqueaderoServicio vigilante = new AdminParqueaderoServicioImpl();
		vigilante.ingresarVehiculo(carro);
		VehiculoEntity vehiculoIngresado = repositoriovehiculo.obtenerPorPlaca("FBW235");
		vigilante.retirarVehiculo(VehiculoBuilder.convertirADominio(vehiculoIngresado));
		
		
		assertEquals("FBW235", repositoriovehiculo.obtenerPorPlaca("FBW235").getPlaca());

	}
	@Test
	public void retiroMotoTest() {
		Vehiculo moto = new Moto("moto", "FBW23K", false, 100);
		AdminParqueaderoServicio vigilante = new AdminParqueaderoServicioImpl();
		vigilante.ingresarVehiculo(moto);
		VehiculoEntity vehiculoIngresado = repositoriovehiculo.obtenerPorPlaca("FBW23K");
		vigilante.retirarVehiculo(VehiculoBuilder.convertirADominio(vehiculoIngresado));
		
		
		assertEquals("FBW23K", repositoriovehiculo.obtenerPorPlaca("FBW23K").getPlaca());

	}

}
