package ast;

import ast.type.Type;

public abstract class ExpressionAbstract extends ASTNodeAbstract implements Expression {

	private boolean lValue;
	private Type type;

	public ExpressionAbstract(int i, int j) {
		super(i, j);
	}
	
	@Override
	public boolean getLValue() {
		return lValue;
	}
	
	@Override
	public void setLValue(boolean value) {
		this.lValue=value;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}

}
