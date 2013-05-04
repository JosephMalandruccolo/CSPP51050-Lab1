package deliverable;

public class Master {
	
	public static void main(String[] args) {
		
		
		MoneyMarket market = new MoneyMarket(10, 1000.00, 200);
		System.out.println(market.value());
		System.out.println(market.getInterestRate());
		System.out.println(market.getAccountId());
		
		
		Stock stock = new Stock(15, 23.14, 100, "FB");
		System.out.println(stock.value());
		System.out.println(stock.getAccountId());
		System.out.println(stock.getPrice());
		System.out.println(stock.getShares());
		
		
		
	}

}
