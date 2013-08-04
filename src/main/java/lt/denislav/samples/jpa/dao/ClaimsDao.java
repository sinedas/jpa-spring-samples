package lt.denislav.samples.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lt.denislav.samples.jpa.domain.Claim;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClaimsDao responsible for CRUD operations on {@link Claim} entity.
 * 
 */
@Repository
public class ClaimsDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Persist {@link Claim} if it's new entity.
	 * Merges {@link Claim} if it's detached entity.
	 * 
	 * @param claim Entity to persist/merge.
	 * @return In case of persisting returns managed entity, in case of merging returns managed entity that the state was merged to.
	 */
	@Transactional
	public Claim save(Claim claim) {
		if (claim.getId() == null) { 
			entityManager.persist(claim);
		} {
			claim = entityManager.merge(claim);
		}
		return claim;
	}
	
	/**
	 * Finds {@link Claim} by it's primary key.
	 * 
	 * @param id Id of {@link Claim}
	 * @return Loaded {@link Claim} entity.
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	public Claim findById(Long id) {
		return entityManager.find(Claim.class, id);		
	}
	
	/**
	 * Deletes {@link Claim} by it's primary key.
	 * 
	 * @param id Id of {@link Claim}
	 */
	@Transactional()
	public void delete(Long id) {
		entityManager.remove( entityManager.find(Claim.class, id));
	}
}
