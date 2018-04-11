package visitor;

import ast.Arithmetic;
import ast.ArrayType;
import ast.Assignment;
import ast.Cast;
import ast.CharLiteral;
import ast.CharType;
import ast.Comparison;
import ast.Definition;
import ast.DoubleType;
import ast.ErrorType;
import ast.FieldAccess;
import ast.FunDefinition;
import ast.FunctionType;
import ast.IfStatement;
import ast.Indexin;
import ast.IntLiteral;
import ast.IntType;
import ast.InvocationExpr;
import ast.InvocationStat;
import ast.Logical;
import ast.Program;
import ast.Read;
import ast.RealLiteral;
import ast.RecordField;
import ast.RecordType;
import ast.Return;
import ast.UnaryMinus;
import ast.UnaryNot;
import ast.VarDefinition;
import ast.Variable;
import ast.VoidType;
import ast.WhileSetatement;
import ast.Write;
import symbolTable.SymbolTable;

public class IdentificationVisitor implements Visitor {

	SymbolTable simbolTable;

	public IdentificationVisitor() {
		simbolTable = new SymbolTable();
	}

	@Override
	public Object visit(Arithmetic arithmetic, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Assignment assignment, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(CharType charType, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Comparison comparison, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FunctionType functionType, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object param) {
		simbolTable.set();
		return null;
	}

	@Override
	public Object visit(IfStatement ifStatement, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Indexin indexin, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(InvocationExpr invocationExpr, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(InvocationStat invocationStat, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Logical logical, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Program program, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(RealLiteral realLiteral, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(RecordField recordField, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(RecordType recordType, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Return return1, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(UnaryMinus unaryMinus, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(UnaryNot unaryNot, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(VarDefinition varDefinition, Object param) {
		if(!simbolTable.insert(varDefinition))
			return new ErrorType(varDefinition, "La variable est� duplicada");
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		Definition def = simbolTable.find(variable.getName());
		if( def == null)
			new ErrorType(variable, "La variable no ha sifo definida");
		else 
			variable.setVarDefinition(def);
			
		return null;
	}

	@Override
	public Object visit(WhileSetatement whileSetatement, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Write write, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DoubleType doubleType, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ErrorType errorType, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IntType intType, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(VoidType voidType, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

}
