package lt.denislav.samples.jpa.domain;
import javax.persistence.Entity;


/**
 * Entity representing any third party related to {@link Claim}, e.g. broker, asset valuer.
 * 
 * In samples is used to show how soft reference is used.
 */
@Entity
public class ThirdParty extends BaseEntity {
	
	/**
	 * Number of claim. Soft reference with {@link Claim} entity.
	 */
	private String claimNumber;
	
	/**
	 * Unique number of third party.
	 */
	private String thirdPartyNumber;

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getThirdPartyNumber() {
		return thirdPartyNumber;
	}

	public void setThirdPartyNumber(String thirdPartyNumber) {
		this.thirdPartyNumber = thirdPartyNumber;
	}
}
