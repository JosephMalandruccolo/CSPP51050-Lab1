package instruments;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import valuation.ValuationVisitor;

public class Portfolio extends Investment {
	
	
	//====================================================================
	// =>	PROPERTIES
	//====================================================================
	private ArrayList<Investment> children;
	
	
	//====================================================================
	// =>	CONSTRUCTORS
	//====================================================================
	public Portfolio(int id) {
		super(id);
		this.children = new ArrayList<Investment>();
	}
	
	
	public Portfolio(int id, Investment instrument) {
		super(id);
		this.children = new ArrayList<Investment>();
		this.children.add(instrument);
	}
	
	
	public Portfolio(int id, Portfolio p) {
		super(id);
		this.children = new ArrayList<Investment>();
		this.children.add(p);
	}
	
	

	//====================================================================
	// =>	PUBLIC API
	//====================================================================
	
	public void add(Investment investment) {
		this.children.add(investment);
		
	}

	@Override
	public void remove(Investment investment) {
		// TODO Auto-generated method stub
		
	}

	
	public double value() {
		double totalValue = 0.0;
		for (Investment i : this.children) {
			totalValue += i.value();
		}
		return totalValue;
	}

	
	public Investment getChild(int id) {
		
		for (Investment i : this.children) {
			if (i.getUniqueId() == id) {
				return i;
			}
		}
		
		throw new NoSuchElementException("This portfolio does not contain a portfolio or account with id : " + id);
		
	}

	@Override
	public void acceptValuationVisitor(ValuationVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
