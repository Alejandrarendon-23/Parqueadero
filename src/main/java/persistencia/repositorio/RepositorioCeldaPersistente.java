package persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import dominio.CeldaParqueo;
import dominio.repositorio.RepositorioCelda;
import persistencia.builder.CeldaBuilder;
import persistencia.entidad.CeldaEntity;

public class RepositorioCeldaPersistente implements RepositorioCelda {

	private static final String CELDA_FIND_BY_PLACA = "Celda.findByPlaca";
	private static final String CELDA_FIND_ALL = "Celda.findAll";

	private EntityManager entityManager;

	public RepositorioCeldaPersistente(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	@Override
	public CeldaParqueo obtenerPorCeldaPlaca(String placa) {
		Query query = entityManager.createNamedQuery(CELDA_FIND_BY_PLACA);
		query.setParameter("placa", placa);
		CeldaEntity celdaEntity = null;
		try {
			celdaEntity = (CeldaEntity) query.getSingleResult();
		} catch (NoResultException nre) {
			celdaEntity = null;

		}
		return CeldaBuilder.convertirADominio(celdaEntity);
		
	}

	@Override
	public void agregarCelda(CeldaEntity celda) {
		entityManager.getTransaction().begin();
		entityManager.persist(celda);
		entityManager.getTransaction().commit();
	}
	@Override
	public List<CeldaEntity> listarcelda() {
		Query query = entityManager.createNamedQuery(CELDA_FIND_ALL);
		

		return query.getResultList();
	}

}
