package lt.denislav.samples.jpa;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.Policy;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;


/**
 * Test shows bidirectional mappings usage
 * 
 **/
public class BidirectionalSampleTest extends BaseTest{
	
	/**
	 * Policy with 2 claims is persisted and loaded.
	 */	
	@Transactional
	@Test
	public void savePolicyWithClaims() {
		Policy policy = new Policy();
		policy.setPolicyNumber("A01");
		
		Claim claim1 = new Claim();
		claim1.setPolicy(policy);
		
		Claim claim2 = new Claim();
		claim2.setPolicy(policy);
		
		policy.getClaims().add(claim1);
		policy.getClaims().add(claim2);
		
		entityManager.persist(policy);
		
		entityManager.flush();
		entityManager.clear();
		
		policy = entityManager.find(Policy.class, policy.getId());
		
		LOG.debug("Policy claims:" + policy.getClaims());
		
		assertThat(policy.getClaims().size(), equalTo(2));
	}
	
	/**
	 * The same logic is done like in previous test,
	 * just now claims are persisted. Also load is done for claim.
	 */
	@Transactional
	@Test
	public void savesClaimsWithPolicy() {
		Policy policy = new Policy();
		policy.setPolicyNumber("A01");
		
		Claim claim1 = new Claim();
		claim1.setPolicy(policy);
		
		Claim claim2 = new Claim();
		claim2.setPolicy(policy);
		
		policy.getClaims().add(claim1);
		policy.getClaims().add(claim2);
		
		claimsDao.save(claim1);
		claimsDao.save(claim2);
		
		Long id = policy.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		policy = entityManager.find(Policy.class, id);
		
		LOG.debug("Policy claims:" + policy.getClaims());
		
		assertThat(policy.getClaims().size(), equalTo(2));
	}
	
}
