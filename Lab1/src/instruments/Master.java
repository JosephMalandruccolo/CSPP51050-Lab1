package instruments;

import accountManagement.AccountIdentifier;
import valuation.BookValueVisitor;
import valuation.FiveYearNetPresentValueVisitor;

public class Master {
	
	public static void main(String[] args) {
		
		Stock s = new Stock(1, 10.0, 10, "FB");
		Portfolio p = new Portfolio(2);
		
		p.add(s);
		System.out.println(p.value());
		p.remove(s);
		
		System.out.println(p.value());
		
		
	}//	end main
	
		
		
		

}
