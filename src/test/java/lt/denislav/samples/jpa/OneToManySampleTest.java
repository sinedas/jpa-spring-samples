package lt.denislav.samples.jpa;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link OneToMany} association usage example.
 */
public class OneToManySampleTest extends  BaseTest{
	
	private Long claimId;
		
	/**
	 * Claim with 2 persons is created.
	 * 
	 * You can play with {@link OneToMany#cascade()} property to
	 * see how cascades work.
	 */
	@Before
	public void prepareClaim() {
		Claim claim = new Claim();
		claim.getPersons().add(new Person("John Smith"));
		claim.getPersons().add(new Person("Anne Smith"));
		
		claim = claimsDao.save(claim);
		claimId = claim.getId();		
	}
	
	@After
	public void clearClaims() {
		claimsDao.delete(claimId);
	}
	
	/**
	 * Claim is loaded.
	 * If to change {@link OneToMany#fetch()} property to {@link FetchType#LAZY} in {@link Claim#persons}
	 * test will fail because lazy fetching work only in transaction scope.
	 */
	@Test
	public void loadClaim() {
		Claim claim = claimsDao.findById(claimId);
		
		LOG.debug("Loaded persons" + claim.getPersons());
		assertThat(claim.getPersons().size(), equalTo(2));
	}
	
	/**
	 * 1st person is removed from Claim.
	 * 
	 * If to set {@link OneToMany#orphanRemoval} to true in {@link Claim#persons}
	 * not only foreign key will be set to null, but also {@link Person}
	 * will be removed.
	 */
	@Transactional
	@Test	
	public void removePerson() {
		Claim claim = claimsDao.findById(claimId);
		
		LOG.debug("Loaded persons" + claim.getPersons());
		
		claim.getPersons().remove(0);
	}
}
