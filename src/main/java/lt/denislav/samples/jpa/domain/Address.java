package lt.denislav.samples.jpa.domain;

import javax.persistence.Embeddable;

/**
 * Entity representing address.
 * 
 * Used in {@link HomeClaim} to show address of property, but also could be used in other places too.
 * In samples used to show how to use {@link Embeddable}.
 **/
@Embeddable
public class Address {
	
	private String addressLine;
	
	private String postalCode;
	
	private String contryCd;

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getContryCd() {
		return contryCd;
	}

	public void setContryCd(String contryCd) {
		this.contryCd = contryCd;
	}
	
	
	
}
