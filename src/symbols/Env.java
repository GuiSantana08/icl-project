package symbols;

import java.util.Hashtable;
import java.util.Map;

public class Env<T> {

	private Map<Symbol,T> table;
	private Env<T> prev;

	public Env() {
		table = new Hashtable<>(20);
	}
	
	public void bind(String id, T val) {
	       //TODO:
	}
	

	
	public T find(String id) {
	    //TODO:
	    return null;
	}
	
	
	public Env<T> beginScope() {
	       //TODO:
	    return null;
	}
	
	public Env<T> endScope() {
	       //TODO:
	    return null;
	}
	

}
