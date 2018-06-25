package ast;

import java.util.List;

import ast.type.FunctionType;
import ast.type.Type;
import visitor.Visitor;

public class FunDefinition extends ASTNodeAbstract implements Definition {

	private String name;
	private Type type;
	private List<Statement> statements;
	private int scope;

	public FunDefinition(int line, int column, String string, Type type,
			List<Statement> statements) {
		super(line, column);
		this.name = string;
		this.type = type;
		this.statements = statements;
	}


	@Override
	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		return "FuncionDef [name=" + name + ", type=" + type + ", statements=" + statements + "]";
	}


	@Override
	public Type getType() {
		return type;
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}


	public List<Statement> getStatements() {
		return statements;
	}


	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}


	@Override
	public int getScope() {
		return scope;
	}


	@Override
	public void setScope(int scope) {
		this.scope = scope;
		
	}


	public int bytesLocales() {
		int cont = 0;
		for(Statement stat : statements)
			if(stat instanceof VarDefinition)
				cont += ((VarDefinition) stat).getType().numberOfBytes();
		return cont;
	}


	public int bytesParametros() {
		int cont = 0;
		for(VarDefinition var : ((FunctionType) type).getParameters())
				cont += var.getType().numberOfBytes();
		return cont;
	}
	
	
}