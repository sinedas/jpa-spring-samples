package lt.denislav.samples.jpa.domain;
import javax.persistence.Entity;

/**
 * Entity representing loss of claim.
 */
@Entity
public class Loss extends BaseEntity {
	
	/**
	 * Is total loss.
	 */
	private Boolean totalLossInd;
	
	/**
	 * Free format description about loss.
	 */
	private String description;
	
	public Boolean getTotalLossInd() {
		return totalLossInd;
	}

	public void setTotalLossInd(Boolean totalLossInd) {
		this.totalLossInd = totalLossInd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
