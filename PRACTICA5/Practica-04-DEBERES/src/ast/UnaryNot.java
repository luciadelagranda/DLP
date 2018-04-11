package ast;

public class UnaryNot extends ExpressionAbstract implements Expression {

	private Expression operand;

	public UnaryNot(int i, int j, Expression operand) {
		super(i, j);
		this.operand = operand;
	}

	@Override
	public String toString() {
		return "UnaryMinus [operand=" + operand + "]";
	}
}
