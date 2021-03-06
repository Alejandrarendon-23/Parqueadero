package dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

		try {
			vigilante.ingresarVehiculo(carro);
		} catch (Exception e) {
			fail();
		}

		assertEquals("FBW234", repositoriovehiculo.obtenerPorPlaca("FBW234").getPlaca());
	}

	@Test
	public void ingresarMotoTest() {
		Vehiculo moto = new Moto("moto", "FBW23D", false, 1000);
		AdminParqueaderoServicio vigilante = new AdminParqueaderoServicioImpl();
		try {
			vigilante.ingresarVehiculo(moto);
		} catch (Exception e) {
			fail();
		}

		assertEquals("FBW23D", repositoriovehiculo.obtenerPorPlaca("FBW23D").getPlaca());

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
		try {
			vigilante.ingresarVehiculo(carro);
		} catch (Exception e) {
			fail();
		}

		VehiculoEntity vehiculoIngresado = repositoriovehiculo.obtenerPorPlaca("FBW235");
		vigilante.retirarVehiculo(VehiculoBuilder.convertirADominio(vehiculoIngresado));

		assertEquals("FBW235", repositoriovehiculo.obtenerPorPlaca("FBW235").getPlaca());

	}

	@Test
	public void retiroMotoTest() {
		Vehiculo moto = new Moto("moto", "FBW23K", false, 100);
		AdminParqueaderoServicio vigilante = new AdminParqueaderoServicioImpl();
		try {
			vigilante.ingresarVehiculo(moto);
		} catch (Exception e) {
			fail();
		}

		VehiculoEntity vehiculoIngresado = repositoriovehiculo.obtenerPorPlaca("FBW23K");
		vigilante.retirarVehiculo(VehiculoBuilder.convertirADominio(vehiculoIngresado));

		assertEquals("FBW23K", repositoriovehiculo.obtenerPorPlaca("FBW23K").getPlaca());

	}

	@Test
	public void obtenerCantidadCarrosTest() {
		Vehiculo carro = new Carro("carro", "FBW234", false);
		Vehiculo carro2 = new Carro("carro", "QWE456", false);
		AdminParqueaderoServicio vigilante = new AdminParqueaderoServicioImpl();

		try {
			vigilante.ingresarVehiculo(carro);
			vigilante.ingresarVehiculo(carro2);

		} catch (Exception e) {
			fail();
			
		}

		int listaVehiculos = vigilante.obtenerCantidadCarros();
		assertEquals(2, listaVehiculos);

	}

	@Test
	public void obtenerCantidadMotosTest() {
		Vehiculo moto = new Moto("moto", "FBW23K", false,100);
		Vehiculo moto2 = new Moto("moto", "QWE45L", false,200);
		AdminParqueaderoServicio vigilante = new AdminParqueaderoServicioImpl();

		try {
			vigilante.ingresarVehiculo(moto);
			vigilante.ingresarVehiculo(moto2);

		} catch (Exception e) {
			fail();
			
		}

		int listaVehiculos = vigilante.obtenerCantidadMotos();
		assertEquals(2, listaVehiculos);

	}

}
