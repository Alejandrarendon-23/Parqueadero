package dominio;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dominio.repositorio.RepositorioVehiculo;
import persistencia.entidad.VehiculoEntity;
import persistencia.sistema.SistemaPersistencia;
import testdatabuilder.VehiculoTestDataBuilder;

public class RepositorioTest {

	private SistemaPersistencia sistemaPersistencia;
	

	@Before
	public void configuration() {

		sistemaPersistencia = new SistemaPersistencia();
	
	}

	@Test
	public void agregarVehiculoTest() {
		RepositorioVehiculo repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoDataBuilder.conPlaca("AWS237").build();

		repositorioVehiculo.agregar(vehiculo);
		Vehiculo vehiculoAlmacenado = repositorioVehiculo.obtenerPorPlaca("AWS237");

		assertEquals("AWS237", vehiculoAlmacenado.getPlaca());
		assertEquals("carro", vehiculoAlmacenado.getTipo());
		assertEquals(true, vehiculoAlmacenado.getEstado());

	}

	@Test
	public void retirarVehiculoTest() {
		RepositorioVehiculo repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoDataBuilder.conPlaca("AWS234").build();

		repositorioVehiculo.agregar(vehiculo);
		Vehiculo vehiculoIngresado = repositorioVehiculo.obtenerPorPlaca("AWS234");
		vehiculoIngresado.setEstado(false);

		repositorioVehiculo.actualizarVehiculo(vehiculoIngresado);
		Vehiculo vehiculoRetirado = repositorioVehiculo.obtenerPorPlaca("AWS234");

		assertEquals("AWS234", vehiculoRetirado.getPlaca());
		assertEquals("carro", vehiculoRetirado.getTipo());
		assertEquals(false, vehiculoRetirado.getEstado());

	}

	@Test
	public void listarVehiculosTest() {
		RepositorioVehiculo repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoDataBuilder.conPlaca("FBW234").build();
		Vehiculo vehiculo2 = vehiculoDataBuilder.conPlaca("AWS237").build();

		repositorioVehiculo.agregar(vehiculo);
		repositorioVehiculo.agregar(vehiculo2);
	
		List<VehiculoEntity> listaVehiculos = repositorioVehiculo.listarvehiculos();
		//listaVehiculos = repositorioVehiculo.listarvehiculos();
		assertEquals(2, listaVehiculos.size());
	}

}
