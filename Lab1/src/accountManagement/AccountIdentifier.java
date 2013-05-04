package accountManagement;

/**
 * Shared Account ID assignment manager
 * Using this class to assign account IDs avoids account ID collisions
 * @author Joseph Malandruccolo
 *
 */
public class AccountIdentifier {
	
	//====================================================================
	// =>	SINGLETON OBJECT
	//====================================================================
	private static AccountIdentifier sharedInstance;
	
	
	//====================================================================
	// =>	INSTANCE PROPERTIES
	//====================================================================
	private int nextAccountId;
	private int nextPortfolioId;
	
	
	
	
	//====================================================================
	// =>	ACCESS METHODS
	//====================================================================
	private AccountIdentifier() {
		this.nextAccountId = 50000000;
		this.nextPortfolioId = 1;
	}
	
	
	public static AccountIdentifier sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new AccountIdentifier();
		}
		
		return sharedInstance;
		
	}
	
	
	//====================================================================
	// =>	INSTANCE METHODS
	//====================================================================
	public int getAccoundId() { return this.nextAccountId++; }
	public int getPortfolioId() { return this.nextPortfolioId++; }
	
	
}
