package codeGenerator;

import ast.Arithmetic;
import ast.Assignment;
import ast.AssignmentLogical;
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
		throw new IllegalStateException("Plantilla no definida, Aritmetica");
	}

	@Override
	public Object visit(ArrayType arrayType, Object param) {
		throw new IllegalStateException("Plantilla no definida, ArrayType");
	}

	@Override
	public Object visit(Assignment assignment, Object param) {
		throw new IllegalStateException("Plantilla no definida, Asignacion");
	}

	@Override
	public Object visit(Cast cast, Object param) {
		throw new IllegalStateException("Plantilla no definida, Cast");
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {
		throw new IllegalStateException("Plantilla no definida, CharLiteral");
	}

	@Override
	public Object visit(CharType charType, Object param) {
		throw new IllegalStateException("Plantilla no definida, ChartYPE");
	}

	@Override
	public Object visit(Comparison comparison, Object param) {
		throw new IllegalStateException("Plantilla no definida, comparacion");
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {
		throw new IllegalStateException("Plantilla no definida, field access");
	}

	@Override
	public Object visit(FunctionType functionType, Object param) {
		throw new IllegalStateException("Plantilla no definida, funcion tyoe");
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object param) {
		throw new IllegalStateException("Plantilla no definida, fun defi");
	}

	@Override
	public Object visit(IfStatement ifStatement, Object param) {
		throw new IllegalStateException("Plantilla no definida, if");
	}

	@Override
	public Object visit(Indexin indexin, Object param) {
		throw new IllegalStateException("Plantilla no definida, index");
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object param) {
		throw new IllegalStateException("Plantilla no definida, intLiteral");
	}

	@Override
	public Object visit(Invocation invocation, Object param) {
		throw new IllegalStateException("Plantilla no definida, invocation");
	}

	@Override
	public Object visit(Logical logical, Object param) {
		throw new IllegalStateException("Plantilla no definida, logical");
	}

	@Override
	public Object visit(Program program, Object param) {
		throw new IllegalStateException("Plantilla no definida, program");

	}

	@Override
	public Object visit(Read read, Object param) {
		throw new IllegalStateException("Plantilla no definida, read");

	}

	@Override
	public Object visit(RealLiteral realLiteral, Object param) {
		throw new IllegalStateException("Plantilla no definida, realLiteral");

	}

	@Override
	public Object visit(RecordField recordField, Object param) {
		throw new IllegalStateException("Plantilla no definida, record field");

	}

	@Override
	public Object visit(RecordType recordType, Object param) {
		throw new IllegalStateException("Plantilla no definida, record typoe");

	}

	@Override
	public Object visit(Return return1, Object param) {
		return null;

	}

	@Override
	public Object visit(UnaryMinus unaryMinus, Object param) {
		throw new IllegalStateException("Plantilla no definida, Unary minus");

	}

	@Override
	public Object visit(UnaryNot unaryNot, Object param) {
		throw new IllegalStateException("Plantilla no definida, unary not");

	}

	@Override
	public Object visit(VarDefinition varDefinition, Object param) {
		return null;

	}

	@Override
	public Object visit(Variable variable, Object param) {
		throw new IllegalStateException("Plantilla no definida, var");

	}

	@Override
	public Object visit(WhileSetatement whileSetatement, Object param) {
		throw new IllegalStateException("Plantilla no definida, while");

	}

	@Override
	public Object visit(Write write, Object param) {
		throw new IllegalStateException("Plantilla no definida, write");

	}

	@Override
	public Object visit(DoubleType doubleType, Object param) {
		throw new IllegalStateException("Plantilla no definida, double type");

	}

	@Override
	public Object visit(ErrorType errorType, Object param) {
		throw new IllegalStateException("Plantilla no definida, error");

	}

	@Override
	public Object visit(IntType intType, Object param) {
		throw new IllegalStateException("Plantilla no definida, int type");

	}

	@Override
	public Object visit(VoidType voidType, Object param) {
		throw new IllegalStateException("Plantilla no definida, void type");

	}

	@Override
	public Object visit(AssignmentLogical assignmentLogical, Object param) {
		throw new IllegalStateException("Plantilla no definida, Assigancion logica");
	}

}
