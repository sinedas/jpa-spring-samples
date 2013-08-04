package lt.denislav.samples.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;

import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.ClaimStatus;
import lt.denislav.samples.jpa.dto.ClaimDTO;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test explaining how entity lifecycle works.
 * {@link TransactionConfiguration#defaultRollback()} is set to false.
 */
public class EntityLifeCycleTest extends BaseTest {
		
	private Long claimId;
	
	/**
	 * {@link Claim} is persisted.
	 */
	@Before
	public void prepareClaim() {
		Claim claim = new Claim();	
		claim.setStatus(ClaimStatus.NOTIFICATION);
		claim.setDate(new Date());
		
		claim = claimsDao.save(claim);
		claimId = claim.getId();
	}
	
	/**
	 * Attached {@link Claim} entity is updated on the end of transaction.
	 */
	@Transactional
	@Test
	public void findAndChangeInTransaction() {		
		Claim claim = claimsDao.findById(claimId);
		claim.setStatus(ClaimStatus.OPEN);	
	}
	
	/**
	 * Detached {@link Claim} entity is updated by {@link EntityManager#merge(Object)}.
	 */
	@Transactional
	@Test
	public void mergeDetachedEntity() {		
		Claim claim = claimsDao.findById(claimId);
		claim.setStatus(ClaimStatus.OPEN);
		
		// Persistence context is cleared, all entities become detached.
		entityManager.clear();
		
		// Entity merged
		claim = entityManager.merge(claim);
	}
	
	/**
	 * In {@link FlushModeType#AUTO} mode, attached {@link Claim} entity is
	 * updated before executing jpql query.	
	 */
	@Transactional
	@Test
	public void queryIsCalledInAutoFlushMode() {
		Claim claim = claimsDao.findById(claimId);
		claim.setStatus(ClaimStatus.CLOSED);				
				
		Query query = entityManager.createQuery("select  new " + ClaimDTO.class.getName() + "(c.status) from Claim c");
		query.setFlushMode(FlushModeType.AUTO); // No needed to set. It's default value used for better reading.
		ClaimDTO claimDTO = (ClaimDTO)query.getResultList().get(0);
		
		LOG.info(claimDTO);
		assertThat(claimDTO.getStatus(), equalTo(ClaimStatus.CLOSED));
	}
	
	/**
	 * In {@link FlushModeType#COMMIT} mode, attached {@link Claim} entity is
	 * updated at the end of transaction.
	 */
	@Transactional
	@Test
	public void queryIsCalledInCommitFlushMode() {		
		Claim claim = claimsDao.findById(claimId);
		claim.setStatus(ClaimStatus.CLOSED);				
						
		Query query = entityManager.createQuery("select new " + ClaimDTO.class.getName() + "(c.status) from Claim c");
		query.setFlushMode(FlushModeType.COMMIT);
		ClaimDTO claimDTO = (ClaimDTO)query.getResultList().get(0);
		
		LOG.info(claimDTO);
		assertThat(claimDTO.getStatus(), equalTo(ClaimStatus.NOTIFICATION));
	}
}