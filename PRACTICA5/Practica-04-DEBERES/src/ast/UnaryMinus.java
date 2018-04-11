package ast;

public class UnaryMinus extends ExpressionAbstract implements Expression{
	
	private Expression operand;
	
	public UnaryMinus(int i, int j, Expression operand) {
		super(i,j);
		this.operand = operand;
	}

	@Override
	public String toString() {
		return "UnaryMinus [operand=" + operand + "]";
	}

	
	
	
}
