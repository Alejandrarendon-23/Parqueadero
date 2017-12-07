package controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import dominio.Vehiculo;
import servicio.AdminParqueaderoServicio;


@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired
	@Qualifier("vehiculoServiceImpl")
	private AdminParqueaderoServicio vehiculoService;
	
	@PostMapping("/ingresarVehiculo")
	public String ingesarVehiculo(Vehiculo vehiculo) {
		
		vehiculoService.ingresarVehiculo(vehiculo);
		return "se ingreso el vehiculo";
	}
}
