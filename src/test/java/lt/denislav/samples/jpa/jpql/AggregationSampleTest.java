package lt.denislav.samples.jpa.jpql;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.persistence.Query;

import lt.denislav.samples.jpa.BaseTest;
import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.ClaimStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

/**
 * Aggregation by COUNT in jpql sample
 * 
 **/
public class AggregationSampleTest extends  BaseTest{
	
	/**
	 * 3 {@link Claim} with different statuses are created.
	 */
	@Before
	public void prepareThreeClaimsInDifferentStatuses() {		
		Claim claim1 = new Claim();
		claim1.setStatus(ClaimStatus.NOTIFICATION);
		
		Claim claim2 = new Claim();
		claim2.setStatus(ClaimStatus.OPEN);
		
		Claim claim3 = new Claim();
		claim3.setStatus(ClaimStatus.CLOSED);
				
		claimsDao.save(claim1);
		claimsDao.save(claim2);
		claimsDao.save(claim3);
		
	}
	
	@After
	public void tearDown() {
		entityManager.createQuery("delete Person p").executeUpdate();
		entityManager.createQuery("delete Claim c").executeUpdate();	
	}
	
	/**
	 * {@link Claim} entities in notification and open states are counted.
	 */
	@Transactional
	@Test
	public void usageOfCount() {		
		Query query = entityManager.createQuery("select count(c) from Claim c where c.status IN (:statuses)");
		query.setParameter("statuses", Lists.newArrayList(ClaimStatus.NOTIFICATION, ClaimStatus.OPEN));
				
		Long count = (Long)query.getSingleResult();
		
		LOG.debug("Count of claims: " + count);
		assertThat(count, equalTo(2L));
	}
	
	
	

}
