package valuation;

import deliverable.Bond;
import deliverable.MoneyMarket;
import deliverable.Stock;

public abstract class ValuationVisitor {
	
	public abstract void visitStock(Stock stock);
	public abstract void visitMoneyMarket(MoneyMarket market);
	public abstract void visitBond(Bond bond);

}
