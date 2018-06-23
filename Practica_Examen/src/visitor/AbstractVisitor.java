package visitor;

import ast.Arithmetic;
import ast.Assignment;
import ast.Cast;
import ast.CharLiteral;
import ast.Comparison;
import ast.Definition;
import ast.Expression;
import ast.FieldAccess;
import ast.FunDefinition;
import ast.IfStatement;
import ast.Indexin;
import ast.IntLiteral;
import ast.Invocation;
import ast.Logical;
import ast.Program;
import ast.Read;
import ast.RealLiteral;
import ast.RecordField;
import ast.Return;
import ast.Statement;
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
		arithmetic.getLeft().accept(this,param);
		arithmetic.getRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {
		arrayType.getOf().accept(this,param);
		return null;
	}

	@Override
	public Object visit(Assignment assignment, Object param) {
		assignment.getLeft().accept(this,param);
		assignment.getRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {
		cast.getExp().accept(this,param);
		if(cast.getType()!=null)
			cast.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {
		return null;
	}

	@Override
	public Object visit(CharType charType, Object param) {
		return null;
	}

	@Override
	public Object visit(Comparison comparison, Object param) {
		comparison.getExp1().accept(this,param);
		comparison.getExp2().accept(this, param);
		return null;
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {
		fieldAccess.getExp1().accept(this, param);
		return null;
	}

	@Override
	public Object visit(FunctionType functionType, Object param) {
		functionType.getReturnType().accept(this, param);
		for( VarDefinition def : functionType.getParameters())
			def.accept(this, param);
		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object param) {
		for(Statement statement: funDefinition.getStatements())
			statement.accept(this, param);
		funDefinition.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(IfStatement ifStatement, Object param) {
		ifStatement.getCondition().accept(this, param);
		for(Statement statement: ifStatement.getIfBody())
			statement.accept(this, param);
		if(ifStatement.getElseBody()!= null) {
		for(Statement statement: ifStatement.getElseBody())
			statement.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(Indexin indexin, Object param) {
		indexin.getExp1().accept(this, param);
		indexin.getExp2().accept(this, param);
		return null;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object param) {
		return null;
	}

	@Override
	public Object visit(Invocation invocation, Object param) {
		invocation.getFuncion().accept(this, param);
		for(Expression exp: invocation.getArguments())
			exp.accept(this, param);
		return null;
	}

	@Override
	public Object visit(Logical logical, Object param) {
		logical.getExp1().accept(this, param);
		logical.getExp2().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Program program, Object param) {
		for(Definition d: program.getDefinitions())
			d.accept(this, param);
		return null;
	}

	@Override
	public Object visit(Read read, Object param) {
		read.getExpression().accept(this, param);
		
		return null;
	}

	@Override
	public Object visit(RealLiteral realLiteral, Object param) {
		return null;
	}

	@Override
	public Object visit(RecordField recordField, Object param) {
		recordField.getType().accept(this, param);
		return null;
	}

	@Override
	public Object visit(RecordType recordType, Object param) {
		for (RecordField c : recordType.getFields()) {
			c.accept(this, param);
		}
		return null;
	}

	@Override
	public Object visit(Return return1, Object param) {
		return1.getExpression().accept(this, param);
		return null;
	}

	@Override
	public Object visit(UnaryMinus unaryMinus, Object param) {
		unaryMinus.getOperand().accept(this, param);
		return null;
	}

	@Override
	public Object visit(UnaryNot unaryNot, Object param) {
		unaryNot.getOperand().accept(this, param);
		return null;
	}

	@Override
	public Object visit(VarDefinition varDefinition, Object param) {
		varDefinition.getType().accept(this,param);
		return null;
	}

	@Override
	public Object visit(Variable variable, Object param) {
		return null;
	}

	@Override
	public Object visit(WhileSetatement whileSetatement, Object param) {
		whileSetatement.getCondition().accept(this, param);
		for (Statement c : whileSetatement.getStatements()) 
			c.accept(this, param);
		return null;
	}

	@Override
	public Object visit(Write write, Object param) {
		write.getExpression().accept(this, param);
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
