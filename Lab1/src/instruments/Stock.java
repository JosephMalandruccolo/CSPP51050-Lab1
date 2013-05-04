package instruments;

import valuation.ValuationVisitor;

public class Stock extends Account {

	//====================================================================
	// =>	PROPERTIES
	//====================================================================
	private String ticker;
	private double price;
	private int shares;
	
	
	//====================================================================
	// =>	CONSTRUCTOR
	//====================================================================
	/**
	 * Construct a Stock
	 * @param accountId - unique identifier
	 * @param price - share price
	 * @param shares - number of shares
	 * @param ticker - ticker symbol
	 */
	public Stock(int accountId, double price, int shares, String ticker) {
		super(accountId);
		this.price = price;
		this.shares = shares;
		this.ticker = ticker;
	}
	
	
	//====================================================================
	// =>	PUBLIC API
	//====================================================================
	/**
	 * return the value of a Stock Account as shares times price
	 */
	public double value() { return this.shares * this.price; }
	
	public void acceptValuationVisitor(ValuationVisitor visitor) {
		visitor.visitStock(this);	
	}
	
	
	//====================================================================
	// =>	GETTERS AND SETTERS
	//====================================================================
	public String getTicker() { return this.ticker; }
	
	public double getPrice() { return this.price; }
	public void setPrice(double price) { this.price = price; }
	
	public int getShares() { return this.shares; }
	
	/**
	 * Method to increase or decrease the number of shares
	 * @param changeInShares - change in shares (may be positive or negative)
	 * @throws IllegalArgumentException - if the number of shares would be less than zero after the update
	 */
	public void updateShares(int changeInShares) {
		
		if (this.shares + changeInShares < 0) {
			throw new IllegalArgumentException("Cannot have fewer than 0 shares");
		} 
		else {
			this.shares += changeInShares;
		}
	}
}
