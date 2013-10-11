package lt.denislav.samples.jpa.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * Home insurance specific claim. 
 * 
 * Entity used in samples to show inheritance usage in JPA. 
 */
@Entity
public class HomeClaim extends Claim {

	/**
	 * Address of a home property.
	 */
	@Embedded
	private Address adress = new Address();

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

	@Override
	public String toString() {
		return "Home" + super.toString();
	}
}
