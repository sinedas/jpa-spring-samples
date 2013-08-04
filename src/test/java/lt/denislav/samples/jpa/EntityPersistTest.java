package lt.denislav.samples.jpa;

import java.util.Date;

import javax.persistence.GeneratedValue;

import lt.denislav.samples.jpa.domain.BaseEntity;
import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.ClaimStatus;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test for showing persisting of entity. 
 * 
 * You can play with  {@link GeneratedValue} annotations in {@link BaseEntity} to see
 * different id generation strategies.
 *  
 * You must enable "org.hibernate.SQL" logger to DEBUG mode to see queries log.
 */
public class EntityPersistTest extends BaseTest {
		
	/**
	 * Test saves and loads {@link Claim} entity.
	 * 
	 * You can play with {@link GeneratedValue} annotations in {@link BaseEntity} to see
	 * different id generation strategies.
	 */
	@Test	
	@Transactional
	public void saveClaim() {
		Claim claim = new Claim();
		claim.setStatus(ClaimStatus.OPEN);
		claim.setDate(new Date());
		claim = claimsDao.save(claim);
		
		LOG.debug("Saved claim:" + claim);
						
		claim = claimsDao.findById(claim.getId());
		
		LOG.debug("Loaded claim:" +  claim);
	}
	
	/**
	 * Tests saving 10 {@link Claim} entities.
	 * 
	 * <p>
	 * Uncomment {@SequenceGenerator} annotations in {@link BaseEntity}
	 * and can play with allocationSize property.
	 * </p>
	 */
	@Test
	@Transactional
	public void saveTenClaims() {
		for (int i = 0; i < 10; i ++) {
			claimsDao.save(new Claim());
		}
		
	}
	
}
