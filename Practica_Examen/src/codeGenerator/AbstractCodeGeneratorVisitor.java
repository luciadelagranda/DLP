package codeGenerator;

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
import ast.Invocation;
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
import visitor.Visitor;

public class AbstractCodeGeneratorVisitor implements Visitor {

	protected CodeGeneration cg;

	public AbstractCodeGeneratorVisitor(CodeGeneration cg) {
		super();
		this.cg = cg;
	}

	@Override
	public Object visit(Arithmetic arithmetic, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(Assignment assignment, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(Cast cast, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(CharType charType, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(Comparison comparison, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(FunctionType functionType, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(IfStatement ifStatement, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(Indexin indexin, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(Invocation invocation, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(Logical logical, Object param) {
		throw new IllegalStateException("Plantilla no definida");
	}

	@Override
	public Object visit(Program program, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(Read read, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(RealLiteral realLiteral, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(RecordField recordField, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(RecordType recordType, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(Return return1, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(UnaryMinus unaryMinus, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(UnaryNot unaryNot, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(VarDefinition varDefinition, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(Variable variable, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(WhileSetatement whileSetatement, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(Write write, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(DoubleType doubleType, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(ErrorType errorType, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(IntType intType, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

	@Override
	public Object visit(VoidType voidType, Object param) {
		throw new IllegalStateException("Plantilla no definida");

	}

}
