package lt.denislav.samples.jpa;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.ClaimDetail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;


/**
 * {@link OneToOne} association test. Also example of {@link CascadeType}, {@link FetchType} usage. 
 * 
 * <p>
 * Uncomment @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY) in {@link Claim}.
 * <ul>
 * <li>
 * #{OneToOneSampleTest{@link #loadClaim()}} should fail.
 * </li>
 * <li>
 * #{OneToOneSampleTest{@link #loadClaim_InTransaction()}} should pass.
 * </li>
 * </p>
 **/
public class OneToOneSampleTest extends BaseTest {
	
	private Long claimId;
	
	/**
	 * Saves {@link Claim} with {@link ClaimDetail}.
	 * 
	 * Remove cascade=CascadeType.ALL from {@link OneToOne} {@link Claim#detail} 
	 * to see that detail is not saved together with claim.
	 */
	@Before
	public void prepareClaim() {
		Claim claim = new Claim();
		claim.setDetail(new ClaimDetail());
		
		claim = claimsDao.save(claim);
		claimId	 = claim.getId();
	}
	
	@After
	public void clearClaims() {
		claimsDao.delete(claimId);
	}
		
	/**
	 * If uncomment @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY) in {@link Claim#detail},
	 * test will fail because transaction is not started, entities can be loaded lazily only in transaction.
	 */
	@Test
	public void loadClaim() {
		Claim claim = claimsDao.findById(claimId);	
		ClaimDetail detail = claim.getDetail();
		
		assertThat(detail, notNullValue());
		
		LOG.debug("Loaded claim : " + claim);
		LOG.debug("Loaded details : " + detail);
	}
	
	/**
	 * If uncomment @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY) in {@link Claim#detail},
	 * test will not fail because transaction is started, and entities can be loaded lazily only in transaction. 
	 */
	@Transactional
	@Test
	public void loadClaimInTransaction() {
		Claim claim = claimsDao.findById(claimId);	
		ClaimDetail detail = claim.getDetail();
		
		assertThat(detail, notNullValue());
		
		LOG.debug("Loaded claim : " + claim);
		LOG.debug("Loaded details : " + detail);
	}
}
