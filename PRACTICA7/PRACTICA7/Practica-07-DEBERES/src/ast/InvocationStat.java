package ast;

import java.util.List;

import visitor.Visitor;

public class InvocationStat extends StatementAbstract implements Statement {

	private List<Expression> arguments;
	private Variable funcion;

	public InvocationStat(int line, int column, Variable string, List<Expression> arguments) {
		super(line,column);
		this.funcion = string;
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		return "InvocationStatement [arguments=" + arguments + ", funcion=" + funcion + "]";
	}

	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	

}
