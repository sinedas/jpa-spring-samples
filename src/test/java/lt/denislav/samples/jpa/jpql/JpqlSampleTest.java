package lt.denislav.samples.jpa.jpql;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import javax.persistence.Query;

import lt.denislav.samples.jpa.BaseTest;
import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.ClaimStatus;
import lt.denislav.samples.jpa.domain.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

/**
 * Variuous statements in jpql queries.
 * 
 **/
public class JpqlSampleTest extends  BaseTest{
		
	@Before
	public void prepareTwoClaimsWithDifferentSizeOfPersons() {
		Claim claim1 = new Claim();
		claim1.setStatus(ClaimStatus.NOTIFICATION);
		claim1.setPersons(Lists.newArrayList(new Person("John"), new Person("Wiljam")));
		
		Claim claim2 = new Claim();
		claim2.setStatus(ClaimStatus.OPEN);
		claim2.setPersons(Lists.newArrayList(new Person("Peter")));
		
		Claim claim3 = new Claim();	
		claim3.setStatus(ClaimStatus.CLOSED);
		
		claimsDao.save(claim1);
		claimsDao.save(claim2);
		claimsDao.save(claim3);
	}
		
	/**
	 * Usage of "EMPTY" in jpql.
	 */
	@Transactional
	@Test
	public void usageOfStatementEmpty() {		
		Query query = entityManager.createQuery("select c from Claim c where c.persons is EMPTY");
				
		@SuppressWarnings("unchecked")
		List<Claim> result = query.getResultList();
		
		LOG.debug("Found claims: " + result);
		assertThat(result.size(), equalTo(1));
	}
	
	/**
	 * Usage of statement "IN" in jpql
	 */
	@Transactional
	@Test
	public void usageOfStatementIn() {
		
		Query query = entityManager.createQuery("select c from Claim c where c.status IN (:statuses)");
		query.setParameter("statuses", Lists.newArrayList(ClaimStatus.NOTIFICATION, ClaimStatus.OPEN));
		
		@SuppressWarnings("unchecked")
		List<Claim> result = query.getResultList();
		
		LOG.debug("Found claims: " + result);
		assertThat(result.size(), equalTo(2));
	}
	
	
	
	

}
