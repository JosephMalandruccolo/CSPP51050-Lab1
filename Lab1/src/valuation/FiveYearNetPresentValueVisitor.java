package valuation;

import instruments.Bond;
import instruments.MoneyMarket;
import instruments.Portfolio;
import instruments.Stock;

public class FiveYearNetPresentValueVisitor extends ValuationVisitor {
	
	
	//====================================================================
	// =>	CONSTANTS
	//====================================================================
	private final int YEARS = 5;
	private final int TRAILING_TWENTY_YEAR_STOCK_GROWTH_BIPS = 960;
	
	
	//====================================================================
	// =>	PROPERTIES
	//====================================================================
	private int discountRateBips;
	
	
	
	//====================================================================
	// =>	CONSTRUCTOR
	//====================================================================
	public FiveYearNetPresentValueVisitor(int discountRateBips) {
		super();
		this.discountRateBips = discountRateBips;
	}
	
	
	//====================================================================
	// =>	PUBLIC API
	//====================================================================
	public void visitStock(Stock stock) { 
		double currentStockValue = stock.getPrice() * stock.getShares();
		double futureStockValue = valueAfterCompoundAnualGrowth(currentStockValue, TRAILING_TWENTY_YEAR_STOCK_GROWTH_BIPS, YEARS);
		this.totalValue += netPresentValue(futureStockValue);
	}
	
	
	public void visitMoneyMarket(MoneyMarket market) {
		double currentMMvalue = market.value();
		int rateBips = market.getInterestRate();
		double futureMMvalue = valueAfterCompoundAnualGrowth(currentMMvalue, rateBips, YEARS);
		this.totalValue += netPresentValue(futureMMvalue);
	}
	
	
	public void visitBond(Bond bond) {
		double currentPrinciple = bond.getPrinciple();
		double coupon = bond.getCoupon();
		double maturity = (bond.getMaturity() <= YEARS) ? bond.getMaturity() : YEARS;
		double futureValue = currentPrinciple += coupon * maturity;
		this.totalValue += netPresentValue(futureValue);
	}
	
	public void visitPortfolio(Portfolio portfolio) {
		//	do nothing, the portfolio knows how to escort a valuation methodology through its Investments
	}
	
	
	//====================================================================
	// =>	PRIVATE HELPERS
	//====================================================================
	private double valueAfterCompoundAnualGrowth(double balance, int growthRateBips, int years) {
		return balance * Math.pow((1.0 + (double) (growthRateBips) / (double) (BASIS_POINTS_PER_INTEREST_RATE_PERCENT)), years);
	}
	
	
	private double netPresentValue(double balance) {
		return balance / Math.pow((1.0 + (double)discountRateBips / (double) (BASIS_POINTS_PER_INTEREST_RATE_PERCENT)), YEARS);
	}
	
	
}
