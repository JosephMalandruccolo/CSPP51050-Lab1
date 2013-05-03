package deliverable;

public class MoneyMarket extends Account {

	
	//====================================================================
	// =>	PROPERTIES
	//====================================================================
	private int dollars;
	private int cents;
	private int interestRateBips;
	
	//====================================================================
	// =>	CONSTRUCTOR
	//====================================================================
	public MoneyMarket(int accountId, double initialValue, int basisPoints) {
		super(accountId);
		setDollarsAndCentsFromValue(initialValue);
		this.interestRateBips = basisPoints;
	}

	//====================================================================
	// =>	PUBLIC API
	//====================================================================
	public double value() {
		double value = this.dollars;
		value += this.cents / 100.0;
		
		return value;
		
	}
	
	
	public void deposit(double amount) {
		
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit not defined for amounts less than or equal to 0");
		}
		else {
			setDollarsAndCentsFromValue(amount + this.value());
		}
	}
	
	
	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdraw not defined for amounts less than or equal to 0");
		}
		else if (amount > this.value()) {
			throw new IllegalArgumentException("Cannot withdraw more than current balance");
		}
		else {
			setDollarsAndCentsFromValue(this.value() - amount);
		}
	}
	
	
	public void addInterestForBalance(double balance) {
		double rate = this.interestRateBips / 10000;
		setDollarsAndCentsFromValue(balance * rate );
	}
	
	
	//====================================================================
	// =>	GETTERS AND SETTERS
	//====================================================================
	public int getInterestRate() { return this.interestRateBips; }
	public void setInterestRate(int basisPoints) { this.interestRateBips = basisPoints; }
	
	
	
	//====================================================================
	// =>	PRIVATE HELPERS
	//====================================================================
	private void setDollarsAndCentsFromValue(double value) {
		this.dollars = (int) value;
		this.cents = (int) (value * 100) % 100;
	}
	
}


