package lt.denislav.samples.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lt.denislav.samples.jpa.dao.ClaimsDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Abstract test containing annotations and properties needed for all tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/applicationContext.xml", "classpath*:META-INF/spring/sample-beans.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public abstract class BaseTest {

	protected static final Log LOG = LogFactory.getLog("lt.denislav.samples.jpa");
	
	@Autowired
	protected ClaimsDao claimsDao;

	@PersistenceContext
	protected EntityManager entityManager;
	
	
}
