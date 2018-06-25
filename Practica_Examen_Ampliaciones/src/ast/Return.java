package ast;

import visitor.Visitor;

public class Return extends StatementAbstract {

	private Expression expression;

	public Return(int line, int column, Expression expression) {
		super(line,column);
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "ReturnStatement [expression=" + expression + "]";
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
