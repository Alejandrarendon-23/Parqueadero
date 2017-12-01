package persistencia.sistema;

import javax.persistence.EntityManager;

import dominio.repositorio.RepositorioVehiculo;
import persistencia.repositorio.RepositorioVehiculoPersistente;
import persistencia.conexion.ConexionJPA;

public class SistemaPersistencia {
	
	private EntityManager entityManager;

	public SistemaPersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}

	public RepositorioVehiculo obtenerRepositorioVehiculo() {
		return new RepositorioVehiculoPersistente(entityManager);
	}
	public void iniciar() {
		entityManager.getTransaction().begin();
	}

	public void terminar() {
		entityManager.getTransaction().commit();
	}

}
