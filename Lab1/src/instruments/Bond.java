package instruments;

import valuation.ValuationVisitor;


public class Bond extends Account {

	
	//====================================================================
	// =>	PROPERTIES
	//====================================================================
	private double principle;
	private double annualCoupon;
	private int maturity;
	
	
	//====================================================================
	// =>	CONSTRUCTOR
	//====================================================================
	/**
	 * Constructor for a Bond
	 * @param accountId - unique identifier
	 * @param principle - amount of the loan
	 * @param annualCoupon - annual payment
	 * @param maturity - number of years the payment is made
	 */
	public Bond(int accountId, double principle, double annualCoupon, int maturity) {
		super(accountId);
		this.principle = principle;
		this.annualCoupon = annualCoupon;
		this.maturity = maturity;
	}
	
	
	//====================================================================
	// =>	PUBLIC API
	//====================================================================
	public double value() { return this.principle + (annualCoupon * maturity); }
	
	
	public void acceptValuationVisitor(ValuationVisitor visitor) {
		visitor.visitBond(this);
	}
	
	
	//====================================================================
	// =>	GETTERS AND SETTERS
	//====================================================================
	public double getPrinciple() { return this.principle; }
	public double getCoupon() { return this.annualCoupon; }
	public int getMaturity() { return this.maturity; }
	
}
