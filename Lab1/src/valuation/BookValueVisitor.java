package valuation;

import instruments.Bond;
import instruments.MoneyMarket;
import instruments.Portfolio;
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
	
	public void visitPortfolio(Portfolio portfolio) {
		//	do nothing, the portfolio knows how to escort a valuation methodology through its Investments
	}
	
}
