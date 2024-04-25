package symbols;

import java.util.Hashtable;
import java.util.Map;

public class Env<T> {

	private Map<String,T> table;
	private Env<T> prev; //TODO: inicializar a null

	public Env() {
		table = new Hashtable<>(20);
		prev = new Env<>(); //TODO: nao inicializar o prev
	}

	// TODO: verificar se o id e unico
	public void bind(String id, T val) {
		table.put(id, val);
	}

	public T find(String id) {
		boolean found = false;
		found = table.get(id) != null;
		if (found) {
			return table.get(id);
		}
		//TODO: nao verifica se nao tivermos um prev, ou sej aum nivel a baixo
		do {
			Env<T> currentEnv = this.prev;
			found = currentEnv.table.get(id) != null;
			if (found) {
				return currentEnv.table.get(id);
			}
		}while(!found);
		return null;
	}

	//TODO: criar um construtor novo
	public Env<T> beginScope() {
		Env<T> newEnv = new Env<>();
		newEnv.prev = this;
		return newEnv;
	}
	
	public Env<T> endScope() {
		return this.prev;
	}

}
