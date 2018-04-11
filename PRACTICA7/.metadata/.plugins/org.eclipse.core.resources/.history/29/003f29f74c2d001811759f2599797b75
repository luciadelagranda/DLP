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
import ast.Statement;
import ast.UnaryMinus;
import ast.UnaryNot;
import ast.VarDefinition;
import ast.Variable;
import ast.VoidType;
import ast.WhileSetatement;
import ast.Write;

public class TypeCheckingVisitor implements Visitor{
	
	

	@Override
	public Object visit(Arithmetic arithmetic, Object param) {
		arithmetic.getLeft().accept(this,param);
		arithmetic.getRight().accept(this, param);
		arithmetic.setLValue(false);
		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {
		return null;
	}

	@Override
	public Object visit(Assignment assignment, Object param) {
		assignment.getLeft().accept(this,param);
		assignment.getRight().accept(this, param);
		if(!assignment.getLeft().getLValue())
			new ErrorType(assignment, "Una asignación no puede ir a la izquierda");
		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {
		charLiteral.setLValue(false);
		return null;
	}

	@Override
	public Object visit(CharType charType, Object param) {
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
		functionType.getType().accept(this, null);
		for(Statement statement : functionType.getParameters())
			statement.accept(this, null);
		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object param) {
		for (Statement statements : funDefinition.getStatements())
			statements.accept(this, null);
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
		for (Definition definition : program.getDefinitions())
			definition.accept(this, null);
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
	public Object visit(RealLiteral realLiteral, Object param) {
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
		if(varDefinition.getType() !=null)
			varDefinition.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		variable.setLValue(true);
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
		return null;
	}

	@Override
	public Object visit(ErrorType errorType, Object param) {
		return null;
	}

	@Override
	public Object visit(IntType intType, Object param) {
		return null;
	}

	@Override
	public Object visit(VoidType voidType, Object param) {
		return null;
	}

}
