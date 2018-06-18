package ast;

import java.util.List;

import visitor.Visitor;

public class InvocationExpr extends ExpressionAbstract implements Statement {
	private List<Expression> arguments;
	private Variable funcion;

	public InvocationExpr(int line, int column, Variable variable, List<Expression> arguments) {
		super(line,column);
		this.funcion = variable;
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		return "InvocationStatement [arguments=" + arguments + ", funcion=" + funcion + "]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public List<Expression> getArguments() {
		return arguments;
	}

	public void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
	}

	public Variable getFuncion() {
		return funcion;
	}

	public void setFuncion(Variable funcion) {
		this.funcion = funcion;
	}
}
