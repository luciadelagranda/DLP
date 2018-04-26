package symbolTable;

import java.util.*;

import ast.Definition;
import ast.VarDefinition;

public class SymbolTable {
	
	private int scope=0;
	private List<Map<String,Definition>> table;
	
	public SymbolTable()  {
		table = new ArrayList<Map<String,Definition>>();
		table.add(new HashMap<String,Definition>());
	}

	public void set() {
		this.scope++;
		table.add(new HashMap<String,Definition>());
	}
	
	public void reset() {
		table.remove(scope);
		this.scope--;
	
	}
	
	public boolean insert(Definition definition) {
		if(definition!=null && findInCurrentScope(definition.getName())== null) {
			table.get(scope).put(definition.getName() , definition);
			definition.setScope(this.scope);
		return true;
		}
		return false;
	}
	
	public Definition find(String id) {
		for( int i = scope; i>=0; i--) {
			if(table.get(i).get(id) != null)
				return table.get(i).get(id);
		}
		
		return null;
		
	}

	public Definition findInCurrentScope(String id) {
		return table.get(scope).get(id);	

	}
}
