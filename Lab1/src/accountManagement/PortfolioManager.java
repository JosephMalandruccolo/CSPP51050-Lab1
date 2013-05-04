package accountManagement;

import instruments.Investment;
import instruments.Portfolio;
import java.util.HashMap;
import valuation.BookValueVisitor;

/**
 * PortfolioManager encapsulates various methods of portfolio creation and management
 * @author Joseph Malandruccolo
 *
 */
public class PortfolioManager {
	
	//====================================================================
	// =>	SINGLETON OBJECT
	//====================================================================
	private static PortfolioManager sharedInstance;
	
	
	//====================================================================
	// =>	INSTANCE PROPERTIES
	//====================================================================
	HashMap<Integer, Portfolio> portfolios;
	
	
	//====================================================================
	// =>	ACCESS METHODS
	//====================================================================
	private PortfolioManager() {
		this.portfolios = new HashMap<Integer, Portfolio>();
	}
	
	
	public static PortfolioManager sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new PortfolioManager();
		}
		
		return sharedInstance;
		
	}
	
	
	//====================================================================
	// =>	INSTANCE METHODS
	//====================================================================
	public Portfolio createPortfolio() {
		Portfolio p = new Portfolio(AccountIdentifier.sharedInstance().getPortfolioId());
		this.portfolios.put(p.getUniqueId(), p);
		return p;
	}
	
	
	public Portfolio getPortfolioById(int id) {
		return this.portfolios.get(id);
	}
	
	
	public double bookValueForPortfolio(Portfolio p) {
		BookValueVisitor v = new BookValueVisitor();
		p.acceptValuationVisitor(v);
		return v.totalValueOfSecuritiesVisited();
	}
	
	
	public double bookValueForPortfolioById(int id) {
		Portfolio p = this.getPortfolioById(id);
		return this.bookValueForPortfolio(p);
	}
	
	
	public void addInvestmentToPortfolio(Investment i, Portfolio p) { p.add(i); }
	public void removeInvestmentFromPortfolio(Investment i, Portfolio p) { p.remove(i); }
	
	
}
