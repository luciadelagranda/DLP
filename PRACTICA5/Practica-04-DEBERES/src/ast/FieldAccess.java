package ast;

public class FieldAccess extends ExpressionAbstract implements Expression{
	
	private Expression exp1;
	private String name;

	public FieldAccess(int line, int column, Expression exp1, String name) {
		super(line,column);
		this.exp1 = exp1;
		this.name = name;
	}

	@Override
	public String toString() {
		return "FieldAccess [exp1=" + exp1 + ", name=" + name + "]";
	}

	

	

	
}
