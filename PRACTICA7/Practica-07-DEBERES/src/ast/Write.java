package ast;

import visitor.Visitor;

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
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
