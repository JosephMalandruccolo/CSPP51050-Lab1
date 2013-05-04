package test;

import static org.junit.Assert.*;
import instruments.Bond;
import instruments.MoneyMarket;
import instruments.Portfolio;
import instruments.Stock;
import org.junit.Test;
import valuation.FiveYearNetPresentValueVisitor;

public class FiveYearNetPresentValueVisitorTest {

	@Test
	public void testVisitStock() {
		Stock s = new Stock(0, 10.0, 10, "FB");
		FiveYearNetPresentValueVisitor v = new FiveYearNetPresentValueVisitor(500);
		s.acceptValuationVisitor(v);
		double manuallyComputedNPV = 123.91;
		assertTrue(v.totalValueOfSecuritiesVisited() == manuallyComputedNPV);
	}

	@Test
	public void testVisitMoneyMarket() {
		MoneyMarket m = new MoneyMarket(0, 1000.0, 300);
		FiveYearNetPresentValueVisitor v = new FiveYearNetPresentValueVisitor(500);
		m.acceptValuationVisitor(v);
		double manuallyComputedNPV = 908.32;
		assertTrue(v.totalValueOfSecuritiesVisited() == manuallyComputedNPV);
	}

	@Test
	public void testVisitBond() {
		Bond b = new Bond(0, 1000.0, 100.0, 5);
		FiveYearNetPresentValueVisitor v = new FiveYearNetPresentValueVisitor(500);
		b.acceptValuationVisitor(v);
		double manuallyComputedNPV = 1175.29;
		assertTrue(v.totalValueOfSecuritiesVisited() == manuallyComputedNPV);
	}
	
	@Test
	public void testVisitBondWithLongMaturity() {
		//	ten year bond should give same 5 yr NPV as short term bond
		Bond b = new Bond(0, 1000.0, 100.0, 10);
		FiveYearNetPresentValueVisitor v = new FiveYearNetPresentValueVisitor(500);
		b.acceptValuationVisitor(v);
		double manuallyComputedNPV = 1175.29;
		assertTrue(v.totalValueOfSecuritiesVisited() == manuallyComputedNPV);
	}

	@Test
	public void testVisitPortfolio() {
		Portfolio p = new Portfolio(0);
		
		//	build a portfolio to nest
		Portfolio p2 = new Portfolio(1);
		Stock s = new Stock(0, 10.0, 10, "FB");
		MoneyMarket m = new MoneyMarket(3, 1000.0, 300);
		Bond b = new Bond(4, 1000.0, 100.0, 5);
		p2.add(s);
		p2.add(m);
		p2.add(b);
		p.add(p2);
		
		//	add a non-Portfolio, top level Investment
		Stock sTopLevel = new Stock(5, 10.0, 10, "MSFT");
		p.add(sTopLevel);
		
		FiveYearNetPresentValueVisitor v = new FiveYearNetPresentValueVisitor(500);
		p.acceptValuationVisitor(v);
		double manuallyComputedNPV = 1175.29 + 908.32 + 123.91 + 123.91;
		assertTrue(v.totalValueOfSecuritiesVisited() == manuallyComputedNPV);
	}

}
