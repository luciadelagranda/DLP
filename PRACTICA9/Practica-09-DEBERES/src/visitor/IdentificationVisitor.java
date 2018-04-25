package visitor;

import ast.Definition;


import ast.FunDefinition;


import ast.Read;

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
		simbolTable.set();
		funDefinition.getType().accept(this, param);
		for(Statement statement : funDefinition.getStatements())
			statement.accept(this, param);
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {
		read.getExpression().accept(this, param);
		if(!read.getExpression().getLValue())
			new ErrorType(read, "No puedes tener ese tipo de expresión a la derecha de un input");
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
		variable.setLValue(true);
		return null;
	}

}
