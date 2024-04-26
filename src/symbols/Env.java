package symbols;

import exceptions.DuplicateVariableFoundException;

import java.util.Hashtable;
import java.util.Map;

public class Env<T> {

	private Map<String,T> table;
	private Env<T> prev = null;

	public Env() {
		table = new Hashtable<>(20);
	}

	public void bind(String id, T val) throws DuplicateVariableFoundException {
		var bi = table.get(id);
		if (bi != null)
			throw new DuplicateVariableFoundException(id);
		table.put(id, val);
	}

	public T find(String id) {
		for (Env<T> e = this; e != null; e = e.prev) {
			T found = e.table.get(id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	public Env<T> beginScope() {
		Env<T> newEnv = new Env<>();
		newEnv.prev = this;
		return newEnv;
	}
	
	public Env<T> endScope() {
		return this.prev;
	}

}
