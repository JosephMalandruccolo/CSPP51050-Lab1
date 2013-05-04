package instruments;

/**
 * Abstract base class for the 'Stock', 'MoneyMarket', and 'Bond'
 * @author Joseph Malandruccolo
 */
public abstract class Account extends Investment {
	
	//====================================================================
	// =>	CONSTRUCTOR
	//====================================================================
	public Account(int accountId) {
		super(accountId);
	}
	
	
	//====================================================================
	// =>	PUBLIC INTERFACE
	//====================================================================
	public void add(Investment investment) {
		throw new UnsupportedOperationException("add is not supported in 'Account' and its subclasses");
	}
	
	
	public void remove(Investment investment) {
		throw new UnsupportedOperationException("remove is not supported in 'Account' and its subclasses");
	}
	
	
	public Investment getChild(int n) {
		throw new UnsupportedOperationException("getChild is not supported in 'Account' and its subclasses");
	}
	
	
	//====================================================================
	// =>	GETTERS AND SETTERS
	//====================================================================
	public int getAccountId() { return this.getUniqueId(); }
	
	
}
