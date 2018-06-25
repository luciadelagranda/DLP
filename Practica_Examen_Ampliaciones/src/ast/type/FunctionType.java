package ast.type;

import java.util.ArrayList;

import java.util.List;

import ast.Expression;
import ast.VarDefinition;
import visitor.Visitor;

public class FunctionType extends TypeAbstract  {

	private List<VarDefinition> parameters;
	private Type returnType;

	public FunctionType(int line, int column, List<VarDefinition> parameters, Type returnType) {
		super(line, column);
		this.parameters = parameters;
		this.returnType = returnType;
	}

	@Override
	public String toString() {
		return "FuncionType [parameters=" + parameters + ", returnType=" + returnType + "]";
	}

	public Type getReturnType() {
		return returnType;
	}

	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public List<VarDefinition> getParameters() {
		if (parameters != null)
			return parameters;
		return new ArrayList<VarDefinition>();
	}

	public void setParameters(List<VarDefinition> parameters) {
		this.parameters = parameters;
	}

	@Override
	public Type parenthesis(List<Expression> expressions) {
		if (parameters.size() != expressions.size())
			return null;
		for (int i = 0; i < expressions.size(); i++) {
			if (expressions.get(i).getType().promotesTo(parameters.get(i).getType()) == null)
				return new ErrorType(expressions.get(i).getType().getLine(), expressions.get(i).getType().getColum(),
						"Los tipos de los argumentos no coinciden con los tipos de los parámetros. ");
		}

		return returnType;
	}

	@Override
	public int numberOfBytes() {
		if (getReturnType() instanceof VoidType)
			return 0;
		if (getReturnType() instanceof ErrorType)
			throw new IllegalStateException();

		return getReturnType().numberOfBytes();

	}
	
	

}
