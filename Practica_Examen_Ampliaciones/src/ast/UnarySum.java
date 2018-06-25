package ast;


import visitor.Visitor;

public class UnarySum extends ExpressionAbstract implements Statement {

	private Expression operand;

	public UnarySum(int line, int column, Expression operand) {
		super(line,column);
		this.operand = operand;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		v.visit(this, param);
		return null;
	}

	public Expression getOperand() {
		return operand;
	}

	public void setOperand(Expression operand) {
		this.operand = operand;
	}

	@Override
	public String toString() {
		return "UnarySum [operand=" + operand + "]";
	}

	
}
