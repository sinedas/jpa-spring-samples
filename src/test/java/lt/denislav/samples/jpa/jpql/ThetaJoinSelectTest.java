package lt.denislav.samples.jpa.jpql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import javax.persistence.Query;

import lt.denislav.samples.jpa.BaseTest;
import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.ClaimStatus;
import lt.denislav.samples.jpa.domain.ThirdParty;
import lt.denislav.samples.jpa.dto.ClaimDTO;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * "Theta join" and new constructor usage in jpql.
 * 
 */
public class ThetaJoinSelectTest extends BaseTest {

	/**
	 * {@link Claim} and {@link ThirdParty} with same claim number created.
	 */
	@Before
	public void prepareClaimAndThirdParty() {		
		Claim claim = new Claim();
		claim.setStatus(ClaimStatus.OPEN);
		claim.setClaimNumber("First");
		claimsDao.save(claim);
		
		ThirdParty party = new ThirdParty();
		party.setClaimNumber("First");
		party.setThirdPartyNumber("12345679");
		
		entityManager.persist(party);
	}
			
	/**
	 * "Theta join" and new constructor usage in jpql.
	 */
	@Transactional
	@Test
	public void usageOfConstructorAndThetaJoinInSelect() {
		Query query = entityManager.createQuery("select distinct new " + ClaimDTO.class.getName() + "(c.status, t.thirdPartyNumber) " +
				"from Claim c, ThirdParty t where c.claimNumber = t.claimNumber ");
				
		@SuppressWarnings("unchecked")
		List<ClaimDTO> list = query.getResultList();
		LOG.debug("Found claims dto: " + list);
		
		assertThat(list.size(), equalTo(1));
	}
	
	
}
