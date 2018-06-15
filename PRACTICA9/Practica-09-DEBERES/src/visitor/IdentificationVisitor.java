package visitor;

import ast.Definition;


import ast.FunDefinition;

import ast.Statement;

import ast.VarDefinition;
import ast.Variable;

import ast.type.ErrorType;

import symbolTable.SymbolTable;

public class IdentificationVisitor extends AbstractVisitor {

	SymbolTable simbolTable;

	public IdentificationVisitor() {
		simbolTable = new SymbolTable();
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object param) {
		if(!simbolTable.insert(funDefinition))
			return new ErrorType(funDefinition, "La funci�n ya est� definida");
		simbolTable.set();
		funDefinition.getType().accept(this, param);
		for(Statement statement : funDefinition.getStatements())
			statement.accept(this, param);
		simbolTable.reset();
		return null;
	}


	@Override
	public Object visit(VarDefinition varDefinition, Object param) {
		if(!simbolTable.insert(varDefinition))
			return new ErrorType(varDefinition, "La variable está duplicada");
		varDefinition.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		Definition def = simbolTable.find(variable.getName());
		if( def == null)
			new ErrorType(variable, "La variable no ha sido definida");
		else 
			variable.setVarDefinition(def);
		return null;
	}

}
