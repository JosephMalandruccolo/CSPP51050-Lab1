package test;

import static org.junit.Assert.*;
import instruments.MoneyMarket;

import org.junit.Test;

public class MoneyMarketTest {

	@Test
	public void testValue() {
		MoneyMarket m = new MoneyMarket(0, 1000.0, 500);
		assertTrue(1000.0 == m.value());
	}

	@Test
	public void testDeposit1() {
		MoneyMarket m = new MoneyMarket(0, 1000.0, 500);
		m.deposit(500.0);
		assertTrue(1500.0 == m.value());
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testDeposit2() {
		MoneyMarket m = new MoneyMarket(0, 1000.0, 500);
		m.deposit(-500.0);
	}

	@Test
	public void testWithdraw1() {
		MoneyMarket m = new MoneyMarket(0, 1000.0, 500);
		m.withdraw(500.0);
		assertTrue(500.0 == m.value());
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testWithdraw2() {
		MoneyMarket m = new MoneyMarket(0, 1000.0, 500);
		m.withdraw(1001.0);
	}

	@Test
	public void testAddInterestForBalance() {
		MoneyMarket m = new MoneyMarket(0, 1000.0, 500);
		m.addInterestForBalance(900.0);
		assertTrue(1045.0 == m.value());
	}


	@Test
	public void testSetInterestRate() {
		MoneyMarket m = new MoneyMarket(0, 1000.0, 500);
		m.setInterestRate(750);
		assertTrue(750 == m.getInterestRate());
	}

}
