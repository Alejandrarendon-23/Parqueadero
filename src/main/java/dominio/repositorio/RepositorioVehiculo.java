package dominio.repositorio;

import java.util.List;

import dominio.Vehiculo;
import persistencia.entidad.VehiculoEntity;

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
	void actualizarVehiculo(Vehiculo vehiculo);
	List<VehiculoEntity> listarvehiculos();
}
