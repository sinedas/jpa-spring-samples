package lt.denislav.samples.jpa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lt.denislav.samples.jpa.domain.AutoClaim;
import lt.denislav.samples.jpa.domain.Claim;

import org.junit.Test;


/**
 * Creation of {@link EntityManagerFactory} test.
 */
public class EntityManagerFactoryTest {

	/**
	 * {@link EntityManagerFactory} and 
	 * {@link EntityManager} are created manually.
	 * 
	 * {@link Claim} is persisted in application managed
	 * transaction.
	 */
	@Test
	public void testManager() {
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("default");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(new Claim());
		em.flush();
		em.clear();
				
		em.getTransaction().commit();
	}
}
