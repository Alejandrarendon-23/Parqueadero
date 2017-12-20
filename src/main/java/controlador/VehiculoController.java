package controlador;

import java.util.Calendar;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dominio.CeldaParqueo;
import dominio.Moto;
import dominio.repositorio.RepositorioCelda;
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

	@RequestMapping(path = "/ingreso", method = RequestMethod.POST, consumes = "application/json")
	public Response ingreso(@RequestBody Moto vehiculo) {

		vehiculoService.ingresarVehiculo(vehiculo);

		return Response.status(201).build();
	}

	@RequestMapping(path = "/costo", method = RequestMethod.GET)
	public Response costo(@RequestParam(value = "placa", required = true) String placa) {

		RepositorioCelda repositorioCelda = sistemaPersistencia.obtenerRepositorioCelda();
		CeldaParqueo celda = repositorioCelda.obtenerPorCeldaPlaca(placa);
		celda.setHoraSalida(Calendar.getInstance());

		String output = "El valor total a pagar es: " + vehiculoService.cobroTotalPorVehiculo(celda) + " $";
		return Response.status(200).entity(output).build();
	}

	@RequestMapping(path = "/listar", method = RequestMethod.GET)
	public Response listar() {

		return Response.status(200).entity(vehiculoService.listarCeldas()).build();

	}

}
