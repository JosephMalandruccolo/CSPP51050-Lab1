package test;

import static org.junit.Assert.*;
import instruments.Stock;

import org.junit.Test;

public class StockTest {

	@Test
	public void testValue() {
		Stock s = new Stock(0, 10.0, 10, "FB");
		assertTrue(100.0 == s.value());
	}

	@Test
	public void testSetPrice() {
		Stock s = new Stock(0, 10.0, 10, "FB");
		s.setPrice(15.0);
		assertTrue(s.getPrice() == 15.0);
	}

	@Test
	public void testUpdateShares1() {
		Stock s = new Stock(0, 10.0, 10, "FB");
		s.updateShares(500);
		assertTrue(510 == s.getShares());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testUpdateShares2() {
		Stock s = new Stock(0, 10.0, 10, "FB");
		s.updateShares(-11);
	}

}
