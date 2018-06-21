package ast;

import visitor.Visitor;

public class RealLiteral extends ExpressionAbstract implements Expression{

	private Double value;

	public RealLiteral(int line, int column, Double value) {
		super(line,column);
		this.value = value;
	}

	@Override
	public String toString() {
		return "RealLiteral [value=" + value + "]";
	}
	
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	
}
