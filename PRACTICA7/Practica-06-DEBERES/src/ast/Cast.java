package ast;

import visitor.Visitor;

public class Cast extends ExpressionAbstract implements Expression {

	private Type castType;
	private Expression exp;

	public Cast(int line, int column, Type castType, Expression exp) {
		super(line,column);
		this.castType = castType;
		this.exp = exp;
	}

	@Override
	public String toString() {
		return "Cast [castType=" + castType + ", exp=" + exp + "]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
	
}
