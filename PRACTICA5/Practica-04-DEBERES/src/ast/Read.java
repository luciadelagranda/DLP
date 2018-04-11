package ast;


public class Read extends StatementAbstract implements Statement{

	private Expression expression;

	public Read(int line, int column, Expression expression) {
		super(line,column);
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "InputStatement [expression=" + expression + "]";
	}

}
