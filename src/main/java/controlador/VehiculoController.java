package controlador;

import java.util.Calendar;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dominio.CeldaParqueo;
import dominio.Moto;
import dominio.Vehiculo;
import dominio.repositorio.RepositorioCelda;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.entidad.VehiculoEntity;
import persistencia.sistema.SistemaPersistencia;
import servicio.AdminParqueaderoServicio;

@Component
@RestController
public class VehiculoController {

	@Autowired
	private AdminParqueaderoServicio vehiculoService;

	private SistemaPersistencia sistemaPersistencia = new SistemaPersistencia();

	public VehiculoController(AdminParqueaderoServicio vehiculoService) {
		this.vehiculoService = vehiculoService;

	}

	@RequestMapping(value = "/ingreso", method = RequestMethod.POST)
	public Response ingreso(@RequestParam(value = "placa", required = true) String placa,
			@RequestParam(value = "tipo", required = true) String tipo,
			@RequestParam(value = "cilindraje", required = false, defaultValue = "0") int cilindraje) {
		RepositorioVehiculo repositorioVehiculo = sistemaPersistencia.obtenerRepositorioVehiculo();
		
		Vehiculo vehiculo = new Moto(tipo, placa, false, cilindraje);
		vehiculoService.ingresarVehiculo(vehiculo);
		VehiculoEntity vehiculoEntity = repositorioVehiculo.obtenerPorPlaca(placa);
		vehiculoEntity.getPlaca();

		return Response.status(201).build();
	}

	@RequestMapping(value = "/costo", method = RequestMethod.POST)
	public Response costo(@RequestParam(value = "placa", required = true) String placa,
			@RequestParam(value = "tipo", required = true) String tipo,
			@RequestParam(value = "cilindraje", required = false, defaultValue = "0") int cilindraje) {
		RepositorioCelda repositorioCelda = sistemaPersistencia.obtenerRepositorioCelda();
		CeldaParqueo celda = repositorioCelda.obtenerPorCeldaPlaca(placa);
		celda.setHoraSalida(Calendar.getInstance());
		

		String output = "El valor total a pagar es: " + vehiculoService.cobroTotalPorVehiculo(celda) + " $";
		return Response.status(201).entity(output).build();
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public Response listar() {

		return Response.status(200).entity(vehiculoService.listarCeldas()).build();

	}

}
