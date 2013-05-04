package valuation;

import instruments.Bond;
import instruments.MoneyMarket;
import instruments.Portfolio;
import instruments.Stock;

/**
 * Abstract class representing a valuation methodology for Investments
 * @author Joseph Malandruccolo
 *
 */
public abstract class ValuationVisitor {
	
	//====================================================================
	// =>	CONSTANTS
	//====================================================================
	public final int BASIS_POINTS_PER_INTEREST_RATE_PERCENT = 10000;
	
	
	
	//====================================================================
	// =>	PROPERTIES
	//====================================================================
	protected double totalValue;
	
	//====================================================================
	// =>	CONSTRUCTOR
	//====================================================================
	public ValuationVisitor() {
		this.totalValue = 0;
	}
	
	
	//====================================================================
	// =>	ABSTRACT METHODS
	//====================================================================
	public abstract void visitStock(Stock stock);
	public abstract void visitMoneyMarket(MoneyMarket market);
	public abstract void visitBond(Bond bond);
	public abstract void visitPortfolio(Portfolio portfolio);
	
	
	//====================================================================
	// =>	CONCRETE METHODS IN ABSTRACT CLASS
	//====================================================================
	public double totalValueOfSecuritiesVisited() {
		return this.totalValue;
	}

}
