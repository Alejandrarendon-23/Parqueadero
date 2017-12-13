package persistencia.sistema;

import javax.persistence.EntityManager;

import dominio.repositorio.RepositorioCelda;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.conexion.ConexionJPA;
import persistencia.repositorio.RepositorioCeldaPersistente;
import persistencia.repositorio.RepositorioVehiculoPersistente;

public class SistemaPersistencia {

	private EntityManager entityManager;

	public SistemaPersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}

	public RepositorioVehiculo obtenerRepositorioVehiculo() {
		return new RepositorioVehiculoPersistente(entityManager);
	}

	public RepositorioCelda obtenerRepositorioCelda() {
		return new RepositorioCeldaPersistente(entityManager);
	}

}
