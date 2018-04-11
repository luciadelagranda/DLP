package visitor;

import ast.Arithmetic;
import ast.Assignment;
import ast.Cast;
import ast.CharLiteral;
import ast.Comparison;
import ast.FieldAccess;
import ast.FunDefinition;
import ast.IfStatement;
import ast.Indexin;
import ast.IntLiteral;
import ast.InvocationExpr;
import ast.InvocationStat;
import ast.Logical;
import ast.Program;
import ast.Read;
import ast.RealLiteral;
import ast.RecordField;
import ast.Return;
import ast.UnaryMinus;
import ast.UnaryNot;
import ast.VarDefinition;
import ast.Variable;
import ast.WhileSetatement;
import ast.Write;
import ast.type.ArrayType;
import ast.type.CharType;
import ast.type.DoubleType;
import ast.type.ErrorType;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.RecordType;
import ast.type.VoidType;

public class AbstractVisitor implements Visitor{

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
		fieldAccess.getExp1().accept(this, param);
		fieldAccess.setLValue(true);
		return null;
	}

	@Override
	public Object visit(FunctionType functionType, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object param) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		// TODO Auto-generated method stub
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
