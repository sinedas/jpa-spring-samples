package lt.denislav.samples.jpa.domain;

import javax.persistence.Entity;

/**
 * Automobile specific claim. 
 * 
 * Entity used in samples to show inheritance usage with JPA.
 */
@Entity
public class AutoClaim extends Claim {

	/**
	 * Number of automobile.
	 */
	private String autoNumber;
	
	public String getAutoNumber() {
		return autoNumber;
	}

	public void setAutoNumber(String autoNumber) {
		this.autoNumber = autoNumber;
	}
	
	@Override
	public String toString() {
		return "Auto" + super.toString();
	}
}
