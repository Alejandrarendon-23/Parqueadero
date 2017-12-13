package dominio.repositorio;
import dominio.CeldaParqueo;
import persistencia.entidad.CeldaEntity;

public interface RepositorioCelda {
	
	/**
	 * Permite obtener una celda por placa
	 * 
	 * @param placa
	 * @return
	 */
	CeldaParqueo obtenerPorCeldaPlaca(String placa);
	/**
	 * Permite agregar una celda al repositorio
	 * 
	 * @param celda
	 */

	void agregarCelda(CeldaEntity celda);

}
