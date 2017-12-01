package dominio.repositorio;

import dominio.Vehiculo;

public interface RepositorioVehiculo {

	/**
	 * Permite obtener un vehiculo por placa
	 * @param placa
	 * @return
	 */
	Vehiculo obtenerPorPlaca(String placa);

	/**
	 * Permite agregar un vehiculo al repositorio
	 * @param vehiculo
	 */
	void agregar(Vehiculo vehiculo);
}
