package test;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import instruments.Bond;
import instruments.Investment;
import instruments.MoneyMarket;
import instruments.Portfolio;
import instruments.Stock;

import org.junit.Test;

public class PortfolioTest {

	@Test
	public void testAdd() {
		Portfolio p = new Portfolio(0);
		Stock s = new Stock(1, 10.0, 10, "FB");
		p.add(s);
		assertSame(s, p.getChild(s.getAccountId()));
	}
	
	@Test
	public void testValueSingleInvestment() {
		Portfolio p = new Portfolio(0);
		Stock s = new Stock(1, 10.0, 10, "FB");
		p.add(s);
		assertTrue(p.value() == s.value());
	}
	
	@Test
	public void testValueTwoInvestments() {
		Portfolio p = new Portfolio(0);
		Stock s = new Stock(1, 10.0, 10, "FB");
		MoneyMarket m = new MoneyMarket(2, 1000.0, 500);
		p.add(s);
		p.add(m);
		assertTrue(p.value() == (s.value() + m.value()));
	}
	
	@Test
	public void testValueThreeInvestments() {
		Portfolio p = new Portfolio(0);
		Stock s = new Stock(1, 10.0, 10, "FB");
		MoneyMarket m = new MoneyMarket(2, 1000.0, 500);
		Bond b = new Bond(3, 1000.0, 100.0, 10);
		p.add(s);
		p.add(m);
		p.add(b);
		assertTrue(p.value() == (s.value() + m.value() + b.value()));
	}
	
	@Test
	public void testValueNestedPortfolio() {
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
		assertTrue(p.value() == (p2.value() + sTopLevel.value()));
	}
	
	@Test
	public void testGetTopLevelChild() {
		Portfolio p = new Portfolio(0);
		Stock s = new Stock(1, 10.0, 10, "FB");
		p.add(s);
		Investment i = p.getChild(s.getAccountId());
		assertSame(s, i);
	}
	
	@Test
	public void testGetNestedChild() {
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
		
		Investment i = p.getChild(m.getAccountId());
		assertSame(m, i);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testGetNonExistentChild() {
		Portfolio p = new Portfolio(0);
		Stock s = new Stock(1, 10.0, 10, "FB");
		p.getChild(s.getAccountId());
	}


	@Test
	public void testRemoveTopLevelAccount() {
		Portfolio p = new Portfolio(0);
		Stock s = new Stock(1, 10.0, 10, "FB");
		p.add(s);
		p.remove(s);
		
		assertTrue(p.value() == 0.0);
	}

	

	

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

}
