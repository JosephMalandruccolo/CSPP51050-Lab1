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
	
	public abstract double value();
	
	public abstract Investment getChild(int n);
	
	public abstract void acceptValuationVisitor(ValuationVisitor visitor);
	
	
	
	//====================================================================
	// =>	GETTERS / SETTERS
	//====================================================================
	public int getUniqueId() {
		return this.uniqueIdentifier;
	}
	
}
