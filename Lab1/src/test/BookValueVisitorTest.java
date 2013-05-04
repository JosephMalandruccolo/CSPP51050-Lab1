package test;

import static org.junit.Assert.*;
import instruments.Bond;
import instruments.MoneyMarket;
import instruments.Portfolio;
import instruments.Stock;

import org.junit.Test;

import valuation.BookValueVisitor;

public class BookValueVisitorTest {

	@Test
	public void testVisitStock() {
		Stock s = new Stock(0, 100.0, 300, "FB");
		BookValueVisitor v = new BookValueVisitor();
		s.acceptValuationVisitor(v);
		assertTrue(v.totalValueOfSecuritiesVisited() == s.value());
	}

	@Test
	public void testVisitMoneyMarket() {
		MoneyMarket m = new MoneyMarket(0, 1000.0, 300);
		BookValueVisitor v = new BookValueVisitor();
		m.acceptValuationVisitor(v);
		assertTrue(v.totalValueOfSecuritiesVisited() == m.value());
	}

	@Test
	public void testVisitBond() {
		Bond b = new Bond(0, 1000.0, 300.0, 3);
		BookValueVisitor v = new BookValueVisitor();
		b.acceptValuationVisitor(v);
		assertTrue(v.totalValueOfSecuritiesVisited() == b.value());
	}

	@Test
	public void testVisitPortfolio() {
		
		Portfolio p = new Portfolio(1);
		Stock s = new Stock(2, 10.0, 10, "FB");
		MoneyMarket m = new MoneyMarket(3, 1000.0, 500);
		Bond b = new Bond(4, 1000.0, 100.0, 10);
		p.add(s);
		p.add(m);
		p.add(b);
		
		BookValueVisitor v = new BookValueVisitor();
		p.acceptValuationVisitor(v);
		assertTrue(v.totalValueOfSecuritiesVisited() == p.value());
	}
	
	@Test
	public void testVisitNestedPortfolio() {
		Portfolio p = new Portfolio(0);
		
		//	build a portfolio to nest
		Portfolio p2 = new Portfolio(1);
		Stock s = new Stock(2, 10.0, 10, "FB");
		MoneyMarket m = new MoneyMarket(3, 1000.0, 500);
		Bond b = new Bond(4, 1000.0, 100.0, 10);
		p2.add(s);
		p2.add(m);
		p2.add(b);
		p.add(p2);
		
		//	add a non-Portfolio, top level Investment
		Stock sTopLevel = new Stock(5, 50.0, 50, "MSFT");
		p.add(sTopLevel);
		
		BookValueVisitor v = new BookValueVisitor();
		p.acceptValuationVisitor(v);
		assertTrue(v.totalValueOfSecuritiesVisited() == p.value());
	}

}
