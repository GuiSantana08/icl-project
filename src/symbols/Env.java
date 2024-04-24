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
		boolean found = false;
		found = table.get(id) != null;
		if (found) {
			return table.get(id);
		}
		do {
			Env<T> currentEnv = this.prev;
			found = currentEnv.table.get(id) != null;
			if (found) {
				return currentEnv.table.get(id);
			}
		}while(!found);
		return null;
	}
	
	public Env<T> beginScope() {
		Env<T> newEnv = new Env<>();
		newEnv.prev = this;
		return newEnv;
	}
	
	public Env<T> endScope() {
		this.table = this.prev.table;
		return this.prev;
	}

}
