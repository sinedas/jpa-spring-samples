import java.util.Date;

import javax.sql.DataSource;

import lt.denislav.samples.jpa.dao.ClaimsDao;
import lt.denislav.samples.jpa.domain.Claim;
import lt.denislav.samples.jpa.domain.ClaimDetail;
import lt.denislav.samples.jpa.domain.ClaimStatus;
import lt.denislav.samples.jpa.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.jpa.AbstractJpaTests;

/**
 * Some kind of legacy test, showing how
 * {@link AbstractJpaTests} can be extended.
 */
public class ClaimOldTest extends AbstractJpaTests {

	@Autowired
	private ClaimsDao claimsDao;
	
	@Override
    protected String[] getConfigLocations() {
        return new String[]{ "classpath*:META-INF/spring/applicationContext.xml", "classpath*:META-INF/spring/sample-beans.xml" };
    }
		
	public void testSaveClaim() {
		Claim claim = new Claim();	
		claim.setStatus(ClaimStatus.OPEN);
		claim.setDate(new Date());
		claim.setDetail(new ClaimDetail());
		
		claimsDao.save(claim);	
		
		sharedEntityManager.flush();
		sharedEntityManager.clear();
	}
	
	/**
     * Method overridden in order to define that DataSource bean with name
     * "dataSource" is expected here when auto wiring by type is executed, thus
     * all other beans with same type will be ignored
     */
    @Override
    public void setDataSource(@Qualifier("dataSource") DataSource dataSource) {
        super.setDataSource(dataSource);
    }
	
}
