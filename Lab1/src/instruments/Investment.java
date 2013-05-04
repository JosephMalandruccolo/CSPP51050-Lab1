package instruments;

import valuation.ValuationVisitor;


/**
 * Abstract base class for Portfolio and Account subclasses
 * @author Joseph Malandruccolo
 *
 */
public abstract class Investment {
	
	//====================================================================
	// =>	PROPERTIES
	//====================================================================
	private int uniqueIdentifier;
	
	
	
	//====================================================================
	// =>	CONSTRUCTOR
	//====================================================================
	public Investment(int id) {
		this.uniqueIdentifier = id;
	}
	
	
	//====================================================================
	// =>	ABSTRACT METHODS
	//====================================================================
	public abstract void add(Investment investment);
	public abstract void remove(Investment investment);
	
	/**
	 * @return - the value of an Investment and any sub-Investments
	 */
	public abstract double value();
	
	
	/**
	 * Get a child identified by a parameter
	 * @param n - an identifier for a child
	 * @return the child Investment
	 */
	public abstract Investment getChild(int n);
	
	
	/**
	 * There may be many ways to value an Investment
	 * By accepting a valuation visitor, the Investment object may be valued using an arbitrary valuation methodology
	 * @param visitor - a ValuationVisitor
	 */
	public abstract void acceptValuationVisitor(ValuationVisitor visitor);
	
	
	
	//====================================================================
	// =>	GETTERS / SETTERS
	//====================================================================
	public int getUniqueId() {
		return this.uniqueIdentifier;
	}
	
}
