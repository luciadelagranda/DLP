package ast;

import visitor.Visitor;

public class Variable extends ExpressionAbstract {
	
	private String name;
	private Definition varDefinition;
	
	public Variable(int i, int j, String name) {
		super(i,j);
		this.name = name;
	}

	@Override
	public String toString() {
		return "Variable [name=" + name + "]";
	}

	public String getName() {
		return name;
	}
	
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public Definition getVarDefinition() {
		return varDefinition;
	}

	public void setVarDefinition(Definition def) {
		this.varDefinition = def;
	}
	

}
