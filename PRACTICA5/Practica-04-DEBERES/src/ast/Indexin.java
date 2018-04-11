package ast;

public class Indexin extends ExpressionAbstract implements Expression{

	private Expression exp1;
	private Expression exp2;

	public Indexin(int line, int column, Expression exp1, Expression exp2) {
		super(line,column);
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public String toString() {
		return "Indexin [exp1=" + exp1 + ", exp2=" + exp2 + "]";
	}

}
