package lt.denislav.samples.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Entity containing claim details information.
 * 
 * In samples is used to show {@link OneToOne} association usage.
 */
@Entity
public class ClaimDetail extends BaseEntity {

	public ClaimDetail() {
		super();
	}
	
	public ClaimDetail(String description) {
		super();
		this.description = description;
	}
		
	/**
	 * Free format description about claim.
	 */
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ClaimDetail [id=" + getId() + ", description=" + description + "]";
	}
	
	
	
}
