package lt.denislav.samples.jpa;

import java.util.Date;

import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.ClaimStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test shows difference of
 * @Transactional(propagation=Propagation.SUPPORTS) and @Transactional(readOnly=true).
 */
public class TransactionPropagationTest extends BaseTest {
	
	Long claimId;
	
	@BeforeTransaction
	public void prepareClaim() {
		LOG.debug("[prepareClaim] Preparing claims");
		
		Claim claim = new Claim();	
		claim.setStatus(ClaimStatus.OPEN);
		claim.setDate(new Date());
		
		claim = claimsDao.save(claim);	
		claimId = claim.getId();
	}
	
	@AfterTransaction
	public void clearClaims() {
		claimsDao.delete(claimId);
	}
		
	/**
	 * Exception is thrown on flush.
	 */
	@Transactional(propagation=Propagation.SUPPORTS)
	@Test(expected=TransactionRequiredException.class)
	public void findQueryInPropagartion() {
		Query query = entityManager.createQuery("select c from Claim c");
		Claim claim = (Claim)query.getResultList().get(0);
				
		claim.setStatus(ClaimStatus.CLOSED);
		entityManager.flush();
	}
	
	/**
	 * {@link Claim} is updated on flush, besides fact that transaction marked as readonly.
	 */
	@Transactional(readOnly=true)
	@Test
	public void findQueryInReadonly() {
		Query query = entityManager.createQuery("select c from Claim c");
		Claim claim = (Claim)query.getResultList().get(0);
				
		claim.setStatus(ClaimStatus.CLOSED);
		entityManager.flush();
	}
}