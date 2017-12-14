package dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dominio.repositorio.RepositorioCelda;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.builder.CeldaBuilder;
import persistencia.builder.VehiculoBuilder;
import persistencia.entidad.CeldaEntity;
import persistencia.entidad.VehiculoEntity;
import persistencia.sistema.SistemaPersistencia;
import testdatabuilder.CeldaParqueoTestDataBuilder;
import testdatabuilder.VehiculoTestDataBuilder;

public class RepositorioTest {

	private SistemaPersistencia sistemaPersistencia;
	Calendar fechaIngreso;
	Calendar fechaIngreso2;
	Calendar fechaSalida;

	@Before
	public void configuration() {

		sistemaPersistencia = new SistemaPersistencia();

	}

	@Test
	public void agregarVehiculoTest() {
		RepositorioVehiculo repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoDataBuilder.conPlaca("AWS237").build();

		repositorioVehiculo.agregar(VehiculoBuilder.convertirAEntity(vehiculo));
		VehiculoEntity vehiculoAlmacenado = repositorioVehiculo.obtenerPorPlaca("AWS237");

		assertEquals("AWS237", vehiculoAlmacenado.getPlaca());
		assertEquals("carro", vehiculoAlmacenado.getTipo());
		assertEquals(true, vehiculoAlmacenado.getEstado());
		assertNotNull(vehiculo);
	}

	@Test
	public void actualizarVehiculoTest() {
		RepositorioVehiculo repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoDataBuilder.conPlaca("AWS234").build();

		repositorioVehiculo.agregar(VehiculoBuilder.convertirAEntity(vehiculo));
		VehiculoEntity vehiculoIngresado = repositorioVehiculo.obtenerPorPlaca("AWS234");
		vehiculoIngresado.setEstado(false);

		repositorioVehiculo.actualizarVehiculo(vehiculoIngresado);
		VehiculoEntity vehiculoRetirado = repositorioVehiculo.obtenerPorPlaca("AWS234");

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

		repositorioVehiculo.agregar(VehiculoBuilder.convertirAEntity(vehiculo));
		repositorioVehiculo.agregar(VehiculoBuilder.convertirAEntity(vehiculo2));

		List<VehiculoEntity> listaVehiculos = repositorioVehiculo.listarvehiculos();
		// listaVehiculos = repositorioVehiculo.listarvehiculos();
		assertEquals(2, listaVehiculos.size());
	}
//	@Test
//	public void listarCeldaTest() {
//		fechaIngreso = Calendar.getInstance();
//		fechaIngreso2 = Calendar.getInstance();
//		fechaIngreso2.add(Calendar.HOUR, 3);
//		RepositorioCelda repositorioCelda = sistemaPersistencia.obtenerRepositorioCelda();
//		CeldaParqueoTestDataBuilder celdaDataBuilder = new CeldaParqueoTestDataBuilder();
//		CeldaParqueo celda = celdaDataBuilder.conFechaIngreso(fechaIngreso).build();
//		CeldaParqueo celda2 = celdaDataBuilder.conFechaIngreso(fechaIngreso2).build();
//
//		repositorioCelda.agregarCelda(CeldaBuilder.convertirAEntity(celda));
//		repositorioCelda.agregarCelda(CeldaBuilder.convertirAEntity(celda2));
//
//		List<CeldaEntity> listaCeldas = repositorioCelda.listarcelda();
//		assertEquals(2, listaCeldas.size());
//	}

	@Test
	public void agregarCeldaTest() {
		Vehiculo vehiculo = new Vehiculo("carro","AWS237", true);
		fechaIngreso = Calendar.getInstance();
		
		RepositorioVehiculo repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
		repositorioVehiculo.agregar(VehiculoBuilder.convertirAEntity(vehiculo));
		// CeldaParqueo celdap= new CeldaParqueo(vehiculo,fechaIngreso,fechaSalida );
		VehiculoEntity vehiculoEntity = repositorioVehiculo.obtenerPorPlaca("AWS237");
		RepositorioCelda repositorioCelda = sistemaPersistencia.obtenerRepositorioCelda();
		CeldaParqueoTestDataBuilder celdaDataBuilder = new CeldaParqueoTestDataBuilder();
		CeldaParqueo celda = celdaDataBuilder.conFechaIngreso(fechaIngreso).build();
		CeldaEntity celdaEntity = CeldaBuilder.convertirAEntity(celda);
		celdaEntity.setVehiculo(vehiculoEntity);

		repositorioCelda.agregarCelda(celdaEntity);
		CeldaParqueo vehiculoAlmacenado = repositorioCelda.obtenerPorCeldaPlaca("AWS237");
		

		assertEquals("AWS237", vehiculoAlmacenado.getVehiculo().getPlaca());
		assertEquals(fechaIngreso, vehiculoAlmacenado.getHoraIngreso());
		//assertEquals(fechaSalida, vehiculoAlmacenado.getHoraSalida());

	}

}
