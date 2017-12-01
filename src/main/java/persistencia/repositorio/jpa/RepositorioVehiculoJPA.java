package persistencia.repositorio.jpa;

import persistencia.entidad.VehiculoEntity;

public interface RepositorioVehiculoJPA {
	/**
	 * Permite obtener un vehiculo entity por su placa
	 * @param placa
	 * @return
	 */
	VehiculoEntity obtenerVehiculoEntityPorPlaca(String placa);

}
