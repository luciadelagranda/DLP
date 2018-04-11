package ast;

import visitor.Visitor;

public class IntLiteral extends ExpressionAbstract {

	private int value;

	public IntLiteral(int i, int j, int value) {
		super(i, j);
		this.value = value;
	}

	@Override
	public String toString() {
		return "IntLiteral [value=" + value + "]";
	}
	
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
