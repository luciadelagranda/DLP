package visitor;

import ast.Arithmetic;
import ast.ArrayType;
import ast.Assignment;
import ast.Cast;
import ast.CharLiteral;
import ast.CharType;
import ast.Comparison;
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

public interface Visitor {

	Object visit(Arithmetic arithmetic, Object param);

	Object visit(ArrayType arrayType, Object param);

	Object visit(Assignment assignment, Object param);

	Object visit(Cast cast, Object param);

	Object visit(CharLiteral charLiteral, Object param);

	Object visit(CharType charType, Object param);

	Object visit(Comparison comparison, Object param);

	Object visit(FieldAccess fieldAccess, Object param);

	Object visit(FunctionType functionType, Object param);

	Object visit(FunDefinition funDefinition, Object param);

	Object visit(IfStatement ifStatement, Object param);

	Object visit(Indexin indexin, Object param);

	Object visit(IntLiteral intLiteral, Object param);

	Object visit(InvocationExpr invocationExpr, Object param);

	Object visit(InvocationStat invocationStat, Object param);

	Object visit(Logical logical, Object param);

	Object visit(Program program, Object param);

	Object visit(Read read, Object param);

	Object visit(RealLiteral realLiteral, Object param);

	Object visit(RecordField recordField, Object param);

	Object visit(RecordType recordType, Object param);

	Object visit(Return return1, Object param);

	Object visit(UnaryMinus unaryMinus, Object param);

	Object visit(UnaryNot unaryNot, Object param);

	Object visit(VarDefinition varDefinition, Object param);

	Object visit(Variable variable, Object param);

	Object visit(WhileSetatement whileSetatement, Object param);

	Object visit(Write write, Object param);

	Object visit(DoubleType doubleType, Object param);

	Object visit(ErrorType errorType, Object param);

	Object visit(IntType intType, Object param);

	Object visit(VoidType voidType, Object param);
	
	
	

}
