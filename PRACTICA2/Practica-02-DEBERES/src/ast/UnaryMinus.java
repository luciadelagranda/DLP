package ast;

public class UnaryMinus extends ExpressionAbstract {
	
	private Variable variable;
	
	public UnaryMinus(int i, int j, Variable variable) {
		super(i,j);
		this.variable = variable;
	}

	@Override
	public String toString() {
		return "UnaryMinus [variable=" + variable + "]";
	}
	
	
}
