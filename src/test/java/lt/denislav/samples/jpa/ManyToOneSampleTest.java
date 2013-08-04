package lt.denislav.samples.jpa;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.Query;

import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.Policy;

import org.junit.Test;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;


/**
 * {@link ManyToOne} association test. 
 * Also example of using jpql and aggregation functions.
 * 
 **/
public class ManyToOneSampleTest extends BaseTest{
	
	/**
	 * Saves 2 {@link Claim} associated to the same {@link Policy}.
	 */	
	public void prepareClaims() {
		Policy policy = new Policy();
		policy.setPolicyNumber("A01");
		
		Claim claim1 = new Claim();
		claim1.setPolicy(policy);
		
		Claim claim2 = new Claim();
		claim2.setPolicy(policy);
		
		policy.getClaims().add(claim1);
		policy.getClaims().add(claim2);
		
		claim1 = claimsDao.save(claim1);
		claim2 = claimsDao.save(claim2);	
		
		entityManager.flush();
		entityManager.clear();
	}
	
	/**
	 * Finds claims by policy number.
	 */
	@Transactional
	@Test
	public void findClaimsByPolicyNumber() {
		prepareClaims();
		
		Query query = entityManager.createQuery("select c from Claim c where c.policy.policyNumber = :policyNumber");
		query.setParameter("policyNumber", "A01");
		
		@SuppressWarnings("unchecked")
		List<Claim> result = query.getResultList();
		
		LOG.debug("Found claims: " + result);
		assertThat(result.size(), equalTo(2));
	}
	
	/**
	 * Finds claims count by policy number.
	 */
	@Transactional
	@Test
	public void findClaimsCountByPolicyNumber() {
		prepareClaims();
		
		Query query = entityManager.createQuery("select count(c) from Claim c where c.policy.policyNumber = :policyNumber");
		query.setParameter("policyNumber", "A01");
		
		Long result = (Long)query.getSingleResult();
		
		LOG.debug("Found claims sum: " + result);
		assertThat(result, equalTo(2L));
	}

	
	
	

}
