package ch.common.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ch.common.model.po.BenutzerPo;
import ch.common.model.po.FachPo;
import ch.common.model.po.NotePo;
import ch.common.model.po.SemesterPo;

/**
 * DataAccessObejct f�r den Datenbankzugriff. Persistenz-Schicht. 
 * @author Marina
 *
 */
@Stateless
public class DataAccessObject {

	private EntityManager _entityManager;

	public DataAccessObject() {
		_entityManager = Persistence.createEntityManagerFactory("Notenverwaltung")
							.createEntityManager();
	}

	/**
	 * Speichert die übergebene Entity in die Datenbank.
	 * 
	 * @param entity Entität
	 */
	public <T> T persist(T entity) {
		_entityManager.persist(entity);

		return entity;
	}

	public <T> T get(int id, Class<T> clazz) {
		return _entityManager.find(clazz, id);
	}

	/**
	 * Löscht die übergebene Entität aus der Datenbank.
	 * 
	 * @param entity Entität
	 */
	public <T> void remove(T entity) {
		_entityManager.remove(entity);
	}

	public List<SemesterPo> findSemestersByUserId(int id) {
		CriteriaBuilder criteriaBuilder = _entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(SemesterPo.class);
		Root<BenutzerPo> root = criteriaQuery.from(SemesterPo.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("benutzer"), id));

		try {
			return _entityManager.createQuery(criteriaQuery).getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}

	public List<FachPo> findSubjectsBySemesterId(int id) {
		CriteriaBuilder criteriaBuilder = _entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(FachPo.class);
		Root<BenutzerPo> root = criteriaQuery.from(FachPo.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("semester"), id));

		try {
			return _entityManager.createQuery(criteriaQuery).getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}

	public List<NotePo> findMarksBySubjectId(int id) {
		CriteriaBuilder criteriaBuilder = _entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(NotePo.class);
		Root<BenutzerPo> root = criteriaQuery.from(NotePo.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("fach"), id));

		try {
			return _entityManager.createQuery(criteriaQuery).getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}

	public BenutzerPo getBenutzerByEmail(String email) {

		CriteriaBuilder criteriaBuilder = _entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(BenutzerPo.class);
		Root<BenutzerPo> root = criteriaQuery.from(BenutzerPo.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("email"), email));

		try {
			return (BenutzerPo)_entityManager.createQuery(criteriaQuery).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	public <T> T getEntityByBezeichnung(String bezeichnung, Class<T> tClass) {

		CriteriaBuilder criteriaBuilder = _entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(tClass);
		Root<FachPo> root = criteriaQuery.from(tClass);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("bezeichnung"), bezeichnung));

		try {
			return (T) _entityManager.createQuery(criteriaQuery).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
}
