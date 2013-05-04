package valuation;

import instruments.Bond;
import instruments.MoneyMarket;
import instruments.Stock;

public class BookValueVisitor extends ValuationVisitor {

	
	
	//====================================================================
	// =>	CONSTRUCTOR
	//====================================================================
	public BookValueVisitor() {
		super();
	}
	
	
	//====================================================================
	// =>	Public API
	//====================================================================
	public void visitStock(Stock stock) { this.totalValue += stock.getPrice() * stock.getShares(); }
	
	
	public void visitMoneyMarket(MoneyMarket market) {
		this.totalValue += market.value();
	}
	
	
	public void visitBond(Bond bond) {
		this.totalValue += bond.value();
	}
	
}
