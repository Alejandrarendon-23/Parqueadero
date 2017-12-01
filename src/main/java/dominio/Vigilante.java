package dominio;

import dominio.repositorio.RepositorioVehiculo;

public class Vigilante {

	RepositorioVehiculo repositoriovehiculo;
	
	public void ingresarVehiculo(Vehiculo carro) {
		repositoriovehiculo.agregar(carro);
	}
	
}
