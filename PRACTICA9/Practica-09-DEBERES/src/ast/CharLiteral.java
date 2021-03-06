package ast;

import visitor.Visitor;

public class CharLiteral extends ExpressionAbstract implements Expression{
	
	private int value;
	
	public CharLiteral(int line, int column, char value) {
		super(line, column);
		this.value = value;
	}

	@Override
	public String toString() {
		return "CharLiteral [value=" + value + "]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
