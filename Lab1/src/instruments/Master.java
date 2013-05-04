package instruments;

import accountManagement.AccountIdentifier;
import valuation.BookValueVisitor;
import valuation.FiveYearNetPresentValueVisitor;

public class Master {
	
	public static void main(String[] args) {
		
		/*
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
		
		
		System.out.println("==================");
		AccountIdentifier id = AccountIdentifier.sharedInstance();
		System.out.println(id.getAccoundId());
		System.out.println(id.getAccoundId());
		System.out.println(id.getPortfolioId());
		System.out.println(id.getPortfolioId());
		
		AccountIdentifier id2 = AccountIdentifier.sharedInstance();
		System.out.println(id2.getAccoundId());
		System.out.println(id2.getPortfolioId());
		*/
		
	
		Portfolio p1 = new Portfolio(AccountIdentifier.sharedInstance().getPortfolioId());
		Stock s1 = new Stock(AccountIdentifier.sharedInstance().getAccoundId(), 10.10, 100, "FB");
		Bond b1 = new Bond(AccountIdentifier.sharedInstance().getAccoundId(), 1000.0, 100.0, 5);
		MoneyMarket m1 = new MoneyMarket(AccountIdentifier.sharedInstance().getAccoundId(), 1000.0, 500);
		Portfolio p2 = new Portfolio(AccountIdentifier.sharedInstance().getPortfolioId());
		Stock s2 = new Stock(AccountIdentifier.sharedInstance().getAccoundId(), 100000.0, 1, "MSFT");
		p2.add(s2);
		
		p1.add(s1);
		p1.add(b1);
		p1.add(m1);
		p1.add(p2);
		
		BookValueVisitor v = new BookValueVisitor();
		
		p1.acceptValuationVisitor(v);
		
		System.out.println(v.totalValueOfSecuritiesVisited());
		
		
		FiveYearNetPresentValueVisitor npv = new FiveYearNetPresentValueVisitor(700);
		
		p1.acceptValuationVisitor(npv);
		System.out.println(npv.totalValueOfSecuritiesVisited());
		
		
	}//	end main
	
		
		
		

}
