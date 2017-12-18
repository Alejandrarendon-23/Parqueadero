package controlador;

import java.util.Calendar;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dominio.Carro;
import dominio.CeldaParqueo;
import dominio.Moto;
import dominio.Vehiculo;
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

	@RequestMapping("/ingreso")
	public ResponseBuilder ingreso(@RequestParam(value = "placa", required = true) String placa,
			@RequestParam(value = "tipo", required = true) String tipo,
			@RequestParam(value = "cilindraje", required = false, defaultValue = "0") int cilindraje) {
//		Vehiculo vehiculo = null;
//		if (tipo.equals("carro")) {
//
//			vehiculo = new Carro(tipo, placa, false);
//		} else if (tipo.equals("moto")) {
//			vehiculo = new Moto(tipo, placa, false, cilindraje);
//
//		}

		return Response.status(200);

	}

	@RequestMapping("/costo")
	public Response costo(@RequestParam(value = "placa", required = true) String placa,
			@RequestParam(value = "tipo", required = true) String tipo,
			@RequestParam(value = "cilindraje", required = false, defaultValue = "0") int cilindraje) {
		RepositorioCelda repositorioCelda = sistemaPersistencia.obtenerRepositorioCelda();
		CeldaParqueo celda = repositorioCelda.obtenerPorCeldaPlaca(placa);
		celda.setHoraSalida(Calendar.getInstance());
		
		String output = "El valor total a pagar es: " + vehiculoService.cobroTotalPorVehiculo(celda) + " $";
		return Response.status(200).entity(output).build();
	}
	@RequestMapping("/listar")
	public Response listar(@RequestParam(value = "placa", required = true) String placa,
			@RequestParam(value = "tipo", required = true) String tipo,
			@RequestParam(value = "cilindraje", required = false, defaultValue = "0") int cilindraje) {
		
		
		return Response.status(200).entity(vehiculoService.listarCeldas()).build();
		
	}

}
