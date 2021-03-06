package persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import dominio.repositorio.RepositorioVehiculo;
import persistencia.entidad.VehiculoEntity;

public class RepositorioVehiculoPersistente implements RepositorioVehiculo {

	private static final String VEHICULO_FIND_BY_PLACA = "Vehiculo.findByPlaca";
	private static final String VEHICULO_FIND_ALL = "Vehiculo.findAll";

	private EntityManager entityManager;

	public RepositorioVehiculoPersistente(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	@Override
	public VehiculoEntity obtenerPorPlaca(String placa) {
		Query query = entityManager.createNamedQuery(VEHICULO_FIND_BY_PLACA);
		query.setParameter("placa", placa);
		VehiculoEntity vehiculoEntity = null;
		try {
			vehiculoEntity = (VehiculoEntity) query.getSingleResult();
		} catch (NoResultException nre) {
			vehiculoEntity = null;

		}
		return vehiculoEntity;
	}

	@Override
	public void agregar(VehiculoEntity vehiculo) {
		entityManager.getTransaction().begin();
		entityManager.persist(vehiculo);
		entityManager.getTransaction().commit();

	}

	@Override
	public void actualizarVehiculo(VehiculoEntity vehiculo) {
		Query query = entityManager.createNamedQuery(VEHICULO_FIND_BY_PLACA);
		query.setParameter("placa", vehiculo.getPlaca());
		
		VehiculoEntity vehiculoEntity = (VehiculoEntity) query.getSingleResult();

		vehiculoEntity.setEstado(vehiculo.getEstado());

		entityManager.getTransaction().begin();
		entityManager.merge(vehiculoEntity);
		entityManager.getTransaction().commit();

	}

	@Override
	public List<VehiculoEntity> listarvehiculos() {

		Query query = entityManager.createNamedQuery(VEHICULO_FIND_ALL);

		return query.getResultList();
	}

}
