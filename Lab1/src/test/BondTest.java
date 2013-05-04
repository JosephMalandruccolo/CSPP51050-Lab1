package test;

import static org.junit.Assert.*;
import instruments.Bond;
import org.junit.Test;

public class BondTest {
	
	@Test
	public void oneYearValue() {
		Bond b = new Bond(0, 1000.0, 100.0, 1);
		assertTrue(1100.0 == b.value());
	}
	
	
	@Test
	public void ThreeYearValue() {
		Bond b = new Bond(0, 1000.0, 100.0, 3);
		assertTrue(1300.0 == b.value());
	}

}
