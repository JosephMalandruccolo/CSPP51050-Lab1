package instruments;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import valuation.ValuationVisitor;

public class Portfolio extends Investment implements Iterable<Investment> {
	
	
	//====================================================================
	// =>	PROPERTIES
	//====================================================================
	private ArrayList<Investment> children;
	
	
	//====================================================================
	// =>	CONSTRUCTORS
	//====================================================================
	/**
	 * Construct an empty Portfolio
	 * @param id - unique identifier
	 */
	public Portfolio(int id) {
		super(id);
		this.children = new ArrayList<Investment>();
	}
	
	
	/**
	 * Construct a Portfolio with an Investment
	 * @param id - unique identifier
	 * @param instrument - an Investment to add to the Portfolio
	 */
	public Portfolio(int id, Investment instrument) {
		super(id);
		this.children = new ArrayList<Investment>();
		this.children.add(instrument);
	}
	
	
	/**
	 * Construct a Portfolio with another Portfolio
	 * @param id - unique identifier
	 * @param p - a Portfolio
	 */
	public Portfolio(int id, Portfolio p) {
		super(id);
		this.children = new ArrayList<Investment>();
		this.children.add(p);
	}
	
	

	//====================================================================
	// =>	PUBLIC API
	//====================================================================
	/**
	 * add an Investment to the Portfolio
	 */
	public void add(Investment investment) {
		this.children.add(investment);
		
	}

	
	/**
	 * remove an Investment from the Portfolio
	 * if the Investment is in any Portfolio within the this Portfolio, the investment will be removed
	 */
	public void remove(Investment investment) { internalRemove(investment); }
	
	
	/**
	 * Private implementation of remove investment
	 * @param investment - the Investment to remove
	 * @return true if the Investment was removed, false otherwise
	 */
	private boolean internalRemove(Investment investment) {
		PortfolioIterator iterator = new PortfolioIterator();
		
		Investment i;
		boolean removed = false;
		while (iterator.hasNext()) {
			i = iterator.next();
			
			//	case where Investment to remove is a top-level investment
			if (i.getUniqueId() == investment.getUniqueId()) {
				this.children.remove(i);
				removed = true;
				break;
			}
			
			
			//	case where investment may be in a Portfolio
			//	check the contents of the Portfolio for the Investment to remove
			if (i.getClass() == Portfolio.class) {
				Portfolio p = (Portfolio)i;
				removed = p.internalRemove(investment);
			}
			
			if (removed) break;
			
		}
		
		return removed;
		
	}

	
	public double value() {
		double totalValue = 0.0;
		for (Investment i : this.children) {
			totalValue += i.value();
		}
		return totalValue;
	}

	
	/**
	 * Find an Investment in the Portfolio
	 * @param id - unique identifier of the investment
	 * @return - the Investment
	 */
	public Investment getChild(int id) {
		
		for (Investment i : this.children) {
			
			if (i instanceof Bond) {
				if (i.getUniqueId() == id) return i;
				
			} 
			else if (i instanceof Stock) {
				if (i.getUniqueId() == id) return i;
			}
			else if (i instanceof MoneyMarket) {
				if (i.getUniqueId() == id) return i;
			}
			else if (i instanceof Portfolio) return i.getChild(id);
			else {
				throw new NoSuchElementException("Object of class " + i.getClass() + " is unknown");
			}
			
			
		}
		
		throw new NoSuchElementException("This portfolio does not contain a portfolio or account with id : " + id);
		
	}

	
	/**
	 * Accept a ValuationVisitor to value the Portfolio
	 */
	public void acceptValuationVisitor(ValuationVisitor visitor) {
		
		PortfolioIterator iterator = new PortfolioIterator();
		

		Investment i;
		while (iterator.hasNext()) {
			
			i = iterator.next();
			
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
	/**
	 * Iterator through a Portfolio
	 */
	public Iterator<Investment> iterator() { return new PortfolioIterator(); }
	
	
	private class PortfolioIterator implements Iterator<Investment> {
		
		//	current index through children array
		int current = 0;

		public boolean hasNext() { return this.current < children.size(); }

		public Investment next() { return children.get(this.current++); }

		public void remove() { throw new UnsupportedOperationException("Remove not supported in PortfolioIterator"); }
		
	}
}
