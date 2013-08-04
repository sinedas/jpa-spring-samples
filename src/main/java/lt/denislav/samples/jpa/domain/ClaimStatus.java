package lt.denislav.samples.jpa.domain;

/**
 * Claim state enum.
 * 
 */
public enum ClaimStatus {

	/**
	 * Notification state, while gathering initial information about claim.
	 */
	NOTIFICATION, 
	
	/**
	 * Open state, while evaluation claim.
	 */
	OPEN, 
	
	/**
	 * Closed state.
	 */
	CLOSED
}
