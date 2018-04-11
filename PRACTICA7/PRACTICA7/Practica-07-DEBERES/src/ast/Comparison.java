package ast;

import visitor.Visitor;

public class Comparison extends ExpressionAbstract implements Expression{
	
	private Expression exp1;
	private Expression exp2;
	private String operator;
	
	public Comparison(int line, int column, Expression exp1, String operator, Expression exp2) {
		super(line,column);
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "Logical [exp1=" + exp1 + ", exp2=" + exp2 + ", operator=" + operator + "]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
