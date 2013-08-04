package lt.denislav.samples.jpa.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.hibernate.annotations.IndexColumn;

/**
 * Entity representing insurance claim.
 * Main entity in domain and is used in almost all samples.
 *
 * For inheritance {@link InheritanceType#SINGLE_TABLE} strategy
 * is used. Other strategies could be tried by uncommenting lines with them. 
 * 
 */
@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy=InheritanceType.JOINED)
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Claim extends BaseEntity {

	public Claim() {} 

	/**
	 * Status of the claim.
	 */
	@Enumerated(EnumType.STRING)
	private ClaimStatus status;

	/**
	 * Date and time of accident.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;	
	
	/**
	 * Claim details.
	 */
	@OneToOne(cascade=CascadeType.ALL)
//	@OneToOne
//	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "claimDetailId")
	private ClaimDetail detail;
	
	/**
	 * List of persons which are related to claim.
	 * Used for representing {@link OneToMany} association.
	 */	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@OneToMany(cascade=CascadeType.ALL)
//	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	@JoinColumn(name = "claimId")
	@IndexColumn(name = "seqIdx")	
	private List<Person> persons = new ArrayList<Person>();
	
	/**
	 * Policy for which claim occurred.
	 * Used for representing {@link ManyToOne} association.
	 */
	@ManyToOne(cascade=CascadeType.ALL)	
	@JoinColumn(name="policyId")
	private Policy policy;
	
	/**
	 * Number of claim.
	 */
	private String claimNumber;

	public ClaimStatus getStatus() {
		return status;
	}

	public void setStatus(ClaimStatus status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ClaimDetail getDetail() {
		return detail;
	}

	public void setDetail(ClaimDetail detail) {
		this.detail = detail;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	@Override
	public String toString() {
		return "Claim [id=" + getId() + ", status=" + status + ", date=" + date
				+ ", claimNumber=" + claimNumber + ", version=" + getVersion() + "]";
	}
	
	
}
