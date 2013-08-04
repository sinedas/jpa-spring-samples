package lt.denislav.samples.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.ClaimStatus;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Test shows how optimistic lock mechanism works.
 * Test is igored by default, because other tests fail after it.
 * 
 * Uncomment {@link Ignore} annotation an run test separately.
 */
@Ignore
public  class OptimisticLockTest extends BaseTest {
	
	@PersistenceUnit
	protected EntityManagerFactory factory;
	
	/**
	 * 2 application managed transactions try to merge
	 * the {@link Claim} at same time.
	 * 
	 * On second transaction commit exception is thrown.
	 */
	@Test
	public void optimisticLock() {
		Long claimId = prepareClaim();
		
		EntityManager manager1 = factory.createEntityManager();
		manager1.getTransaction().begin();
		Claim claim1 = manager1.find(Claim.class, claimId);		
		claim1.setStatus(ClaimStatus.NOTIFICATION);		
		
		EntityManager manager2 = factory.createEntityManager();
		manager2.getTransaction().begin();
		Claim claim2 = manager2.find(Claim.class, claimId);		
		claim2.setStatus(ClaimStatus.OPEN);		
		
		manager1.getTransaction().commit();
		try {
			manager2.getTransaction().commit();
			Assert.isTrue(false);
		} catch (Exception ex) {
			LOG.debug("Exception catched:" + ex);
		} finally {
			manager1.close();
			manager2.close();
			
			factory.close();
		}
	}
	
	public Long prepareClaim() {
		Claim claim = new Claim();
		claim = claimsDao.save(claim);
		return claim.getId();
	}
	
	
}
