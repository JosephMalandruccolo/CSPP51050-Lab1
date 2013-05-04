package instruments;

import java.rmi.activation.UnknownObjectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.activation.UnsupportedDataTypeException;
import javax.lang.model.element.UnknownElementException;

import valuation.ValuationVisitor;

public class Portfolio extends Investment implements Iterable<Investment> {
	
	
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

	public void remove(Investment investment) {
		
		this.children.remove(investment);
		
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
		
		PortfolioIterator iterator = new PortfolioIterator();
		
		while (iterator.hasNext()) {
			
			Investment i = iterator.next();
			
			if (i instanceof Bond) {
				visitor.visitBond((Bond) i);
			} 
			else if (i instanceof Stock) {
				visitor.visitStock((Stock) i);
			}
			else if (i instanceof MoneyMarket) {
				visitor.visitMoneyMarket((MoneyMarket) i);
			}
			else if (i instanceof Portfolio) {
				i.acceptValuationVisitor(visitor);
			}
			else {
				throw new NoSuchElementException("Object of class " + i.getClass() + " is unknown");
			}
		}
	}

	
	//====================================================================
	// =>	ITERATOR
	//====================================================================

	
	public Iterator<Investment> iterator() { return new PortfolioIterator(); }
	
	
	private class PortfolioIterator implements Iterator<Investment> {
		
		//	current index
		int current = 0;

		public boolean hasNext() { return this.current < children.size(); }

		public Investment next() { return children.get(this.current++); }

		public void remove() { throw new UnsupportedOperationException("Remove not supported in PortfolioIterator"); }
		
	}
}
