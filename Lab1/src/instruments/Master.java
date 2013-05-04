package instruments;

import valuation.BookValueVisitor;

public class Master {
	
	public static void main(String[] args) {
		
		
		Stock stock = new Stock(15, 23.59, 1000, "FB");
		System.out.println("Value: " + stock.value());
		System.out.println("Account ID: " + stock.getAccountId());
		System.out.println("Price: " + stock.getPrice());
		System.out.println("Shares: " + stock.getShares());
		
		BookValueVisitor bv = new BookValueVisitor();
		stock.acceptValuationVisitor(bv);
		
		System.out.println("Stock value: " + bv.totalValueOfSecuritiesVisited());
		
		
		MoneyMarket market = new MoneyMarket(16, 1000.0, 500);
		System.out.println("Value: " + market.value());
		
		BookValueVisitor mv = new BookValueVisitor();
		
		market.acceptValuationVisitor(bv);
		market.acceptValuationVisitor(mv);
		System.out.println("Money Market Value: " + mv.totalValueOfSecuritiesVisited());
		System.out.println("Stock and Money MarketValue: " + bv.totalValueOfSecuritiesVisited());
		
		
		Bond b = new Bond(19, 1000.0, 300.0, 5);
		System.out.println("Bond Value: " + b.value());
		
		BookValueVisitor bndV = new BookValueVisitor();
		
		b.acceptValuationVisitor(bv);
		b.acceptValuationVisitor(bndV);
		
		System.out.println("Value of all three: " + bv.totalValueOfSecuritiesVisited());
		System.out.println("Value of Bond: " + bndV.totalValueOfSecuritiesVisited());
		
	}

}
