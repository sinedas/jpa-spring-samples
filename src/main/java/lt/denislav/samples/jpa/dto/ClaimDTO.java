package lt.denislav.samples.jpa.dto;

import lt.denislav.samples.jpa.domain.ClaimStatus;
import lt.denislav.samples.jpa.domain.ThirdParty;

/**
 * DTO object containing part of {@link Claim} and {@link ThirdParty} information.
 * 
 * Used to show how new constructors could be used in jpql queries.
 *
 */
public class ClaimDTO {

	/**
	 * Status of a {@link Claim}.
	 */
	private ClaimStatus status;
	
	/**
	 * Number of a {@link ThirdParty}.
	 */
	private String thirdPartyNumber;

	public ClaimDTO(ClaimStatus status) {
		super();
		this.status = status;
	}
	
	public ClaimDTO(ClaimStatus status, String thirdPartyNumber) {
		super();
		this.status = status;
		this.thirdPartyNumber = thirdPartyNumber;
	}
	
	public ClaimStatus getStatus() {
		return status;
	}

	public void setStatus(ClaimStatus status) {
		this.status = status;
	}


	public String getThirdPartyNumber() {
		return thirdPartyNumber;
	}


	public void setThirdPartyNumber(String thirdPartyNumber) {
		this.thirdPartyNumber = thirdPartyNumber;
	}

	@Override
	public String toString() {
		return "ClaimDTO [status=" + status + ", thirdPartyNumber="
				+ thirdPartyNumber + "]";
	}


	

	
	
}
