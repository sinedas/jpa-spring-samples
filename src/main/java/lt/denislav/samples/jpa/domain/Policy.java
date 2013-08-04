package lt.denislav.samples.jpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.IndexColumn;

/**
 * Entity representing insurance policy, contract between insurer and policyHolder.
 * 
 * Several claims can be associated for same {@link Policy}.
 * Entity is used to show {@link ManyToOne} usage.
 */
@Entity
public class Policy extends BaseEntity {
	
	/**
	 * Unique number of policy.
	 */
	private String policyNumber;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="policy")	
	@IndexColumn(name = "seqIdx")	
	private List<Claim> claims = new ArrayList<Claim>();
	
	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	@Override
	public String toString() {
		return "Policy [id=" + getId() + ", policyNumber=" + policyNumber + "]";
	}
}
