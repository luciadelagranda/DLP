package ast;

public class Return extends StatementAbstract implements Statement {

	private Expression expression;

	public Return(int line, int column, Expression expression) {
		super(line,column);
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "ReturnStatement [expression=" + expression + "]";
	}

	

}
