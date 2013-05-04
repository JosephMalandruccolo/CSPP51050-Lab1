package valuation;

import instruments.Bond;
import instruments.MoneyMarket;
import instruments.Portfolio;
import instruments.Stock;

public abstract class ValuationVisitor {
	
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
