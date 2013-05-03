package deliverable;


/**
 * Abstract base class for Portfolio and Account subclasses
 * @author Joseph Malandruccolo
 *
 */
public abstract class Investment {
	
	public abstract void add(Investment investment);
	
	public abstract void remove(Investment investment);
	
	public abstract double value();
	
	public abstract Investment getChild(int n);
	
	
}
