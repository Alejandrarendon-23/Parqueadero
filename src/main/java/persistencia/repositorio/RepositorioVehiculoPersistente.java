package persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Vehiculo;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.builder.VehiculoBuilder;
import persistencia.entidad.VehiculoEntity;



public class RepositorioVehiculoPersistente implements RepositorioVehiculo{
	
	private static final String VEHICULO_FIND_BY_PLACA = "Vehiculo.findByPlaca";
	private static final String VEHICULO_FIND_ALL = "Vehiculo.findAll";
	
	private EntityManager entityManager;

	public RepositorioVehiculoPersistente(EntityManager entityManager) {
		
		
		this.entityManager = entityManager;
	}	

	@Override
	public Vehiculo obtenerPorPlaca(String placa) {
		Query query = entityManager.createNamedQuery(VEHICULO_FIND_BY_PLACA);
		query.setParameter("placa", placa);
		VehiculoEntity vehiculoEntity = (VehiculoEntity) query.getSingleResult();

		return VehiculoBuilder.convertirADominio(vehiculoEntity);
	}

	@Override
	public void agregar(Vehiculo vehiculo) {
		entityManager.getTransaction().begin();
		entityManager.persist(VehiculoBuilder.convertirAEntity(vehiculo));
		entityManager.getTransaction().commit();
		

	}

	@Override
	public void actualizarVehiculo(Vehiculo vehiculo) {
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
		List<VehiculoEntity> listaVehiculos = new ArrayList<>();
		Query query = entityManager.createNamedQuery(VEHICULO_FIND_ALL);
		listaVehiculos = query.getResultList();
		
		return listaVehiculos;
	}


}
