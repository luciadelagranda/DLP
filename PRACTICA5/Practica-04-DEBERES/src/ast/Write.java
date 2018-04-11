package ast;


public class Write extends StatementAbstract implements Statement{

	private Expression expression;

	public Write(int line, int column, Expression expression) {
		super(line,column);
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "PrintStatement [expression=" + expression + "]";
	}

}
