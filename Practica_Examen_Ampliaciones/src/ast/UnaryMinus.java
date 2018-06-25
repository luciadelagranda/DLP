package ast;

import visitor.Visitor;

public class UnaryMinus extends ExpressionAbstract {
	
	private Expression operand;
	
	public UnaryMinus(int i, int j, Expression operand) {
		super(i,j);
		this.operand = operand;
	}

	@Override
	public String toString() {
		return "UnaryMinus [operand=" + operand + "]";
	}

	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public Expression getOperand() {
		return operand;
	}

	public void setOperand(Expression operand) {
		this.operand = operand;
	}
	
}
