package ast;

import visitor.Visitor;

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
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}
