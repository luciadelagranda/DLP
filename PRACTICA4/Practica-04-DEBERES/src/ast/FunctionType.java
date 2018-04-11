package ast;

import java.util.List;

public class FunctionType extends ASTNodeAbstract implements Type{

	private List<VarDefinition> parameters;
	private Type returnType;

	public FunctionType(int line, int column, List<VarDefinition> parameters, Type returnType) {
		super(line,column);
		this.parameters = parameters;
		this.returnType = returnType;
	}

	@Override
	public String toString() {
		return "FuncionType [parameters=" + parameters + ", returnType=" + returnType + "]";
	}

	public Type getType() {
		return returnType;
	}

}
