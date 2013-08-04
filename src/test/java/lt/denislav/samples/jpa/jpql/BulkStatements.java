package lt.denislav.samples.jpa.jpql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import javax.persistence.Query;

import lt.denislav.samples.jpa.BaseTest;
import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.ClaimStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

/**
 * Usage of bulk queries sample.
 * 
 */
public class BulkStatements extends BaseTest{

	/**
	 * 2 {@link Claim} entities in different states are created.
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
		
	/**
	 * Changing status of all claims to closed with bulk update.
	 */
	@Transactional
	@Test
	public void usageOfStatementUpdate() {				
		Query query = entityManager.createQuery("update Claim c SET c.status = :status");
		query.setParameter("status",ClaimStatus.CLOSED);
		query.executeUpdate();
		entityManager.flush();
		
		query = entityManager.createQuery("select c from Claim c where c.status IN (:statuses)");
		query.setParameter("statuses", Lists.newArrayList(ClaimStatus.CLOSED));
		
		@SuppressWarnings("unchecked")
		List<Claim> result = query.getResultList();
		
		LOG.debug("Found claims: " + result);
		assertThat(result.size(), equalTo(3));
	}
	
	/**
	 * Deletion of all claims with bulk update.
	 */
	@Transactional
	@Test
	public void usageOfStatementDelete() {
		Query query = entityManager.createQuery("delete Claim c");		
		query.executeUpdate();
		
		query = entityManager.createQuery("select c from Claim c");		
		
		@SuppressWarnings("unchecked")
		List<Claim> result = query.getResultList();
		
		LOG.debug("Found claims: " + result);
		assertThat(result.size(), equalTo(0));
		
	}
	

	
}
