package symbols;

import java.util.Hashtable;
import java.util.Map;

public class Env<T> {

	private Map<String,T> table;
	private Env<T> prev;

	public Env() {
		table = new Hashtable<>(20);
		prev = new Env<>();
	}
	
	public void bind(String id, T val) {
		table.put(id, val);
	}
	

	
	public T find(String id) {
		return table.get(id);
	}
	
	
	public Env<T> beginScope() {
		this.prev = this;
		return new Env<>();
	}
	
	public Env<T> endScope() {
		this.table = this.prev.table;
		return this.prev;
	}
	

}
