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
import ast.InvocationExpr;
import ast.InvocationStat;
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

public class TypeCheckingVisitor implements Visitor{
	
	

	@Override
	public Object visit(Arithmetic arithmetic, Object param) {
		arithmetic.getLeft().accept(this,param);
		arithmetic.getRight().accept(this, param);
		arithmetic.setType(arithmetic.getLeft().getType().arithmetic(arithmetic.getRight().getType()));
		if(arithmetic.getType() == null)
			arithmetic.setType(new ErrorType(arithmetic, "La operación arigmética no es correcta"));
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
		if(!assignment.getLeft().getLValue()) {
			String msg = "La expresión de la derecha no se puede asignar a la expresion de la izquierda. La asignacion no es correcta";
			new ErrorType(assignment, msg);
		}
		
		if(assignment.getLeft().getType()!=null && assignment.getRight().getType()!=null) {
			assignment.getLeft().setType(assignment.getRight().getType().promotesTo(assignment.getLeft().getType()));
		}else if(assignment.getLeft().getType() == null)
			assignment.getLeft().setType( new ErrorType(assignment, "El tipo de asignación no coincide."));
		
		
		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {
		cast.getExp().accept(this, param);
		cast.setType(cast.getExp().getType().canBeCast(cast.getCastType()));
		if(cast.getType() == null)
			cast.setType(new ErrorType(cast, "No se puede castear a ese tipo"));
		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {
		charLiteral.setType(CharType.CharTypeInstance(charLiteral.getLine(), charLiteral.getColum()));
		charLiteral.setLValue(false);
		return null;
	}

	@Override
	public Object visit(CharType charType, Object param) {
		return null;
	}

	@Override
	public Object visit(Comparison comparison, Object param) {
		comparison.getExp1().accept(this, param);
		comparison.getExp2().accept(this, param);
		comparison.setType(comparison.getExp1().getType().comparison(comparison.getExp2().getType()));
		if(comparison.getType()== null)
			comparison.setType(new ErrorType(comparison, "No se puede comparar ese tipo"));
		return null;
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {
		fieldAccess.getExp1().accept(this, param);
		fieldAccess.setType(fieldAccess.getExp1().getType().dot(fieldAccess.getName()));
		if(fieldAccess.getType()== null)
			fieldAccess.setType(new ErrorType(fieldAccess, "No se puede acceder a este campo"));
		fieldAccess.setLValue(true);
		return null;
	}

	@Override
	public Object visit(FunctionType functionType, Object param) {
		functionType.getType().accept(this, param);
		for(Statement statement : functionType.getParameters())
			statement.accept(this, param);
		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object param) {
		funDefinition.getType().accept(this, param);
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
		indexin.getExp1().accept(this, param);
		indexin.getExp2().accept(this, param);
		indexin.setType(indexin.getExp1().getType().squareBPacket(indexin.getExp2().getType()));
		if(indexin.getType() == null)
			indexin.setType(new ErrorType(indexin, "Los tipos que intenta indexar no son compatibles"));
		indexin.setLValue(true);
		return null;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object param) {
		intLiteral.setType(IntType.IntTypeInstance(intLiteral.getLine(), intLiteral.getColum()));
		return null;
	}

	@Override
	public Object visit(InvocationExpr invocationExpr, Object param) {
		invocationExpr.getFuncion().accept(this, param);
		for ( Expression ex : invocationExpr.getArguments())
			ex.accept(this, param);
		invocationExpr.setType(invocationExpr.getFuncion().getType().parenthesis(invocationExpr.getArguments()));
		if(invocationExpr.getType() == null)
			invocationExpr.setType(new ErrorType(invocationExpr, "No se puede invocar a esta exprsion"));
		return null;
	}

	@Override
	public Object visit(InvocationStat invocationStat, Object param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Logical logical, Object param) {
		logical.getExp1().accept(this, param);
		logical.getExp2().accept(this, param);
		logical.setType(logical.getExp1().getType().logical(logical.getExp2().getType()));
		if(logical.getType()== null)
			logical.setType(new ErrorType(logical, "No se puede hacer una operación lógica de estos tipos"));
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
			new ErrorType(read, "No puede ir a la izquierda");
		return null;
	}

	@Override
	public Object visit(RealLiteral realLiteral, Object param) {
		realLiteral.setType(DoubleType.DoubleTypeInstance(realLiteral.getLine(), realLiteral.getColum()));
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
		unaryMinus.getOperand().accept(this, param);
		unaryMinus.setType(unaryMinus.getOperand().getType().arithmetic());
		if(unaryMinus.getType() == null) {
			unaryMinus.setType(new ErrorType(unaryMinus, "no se puede negativizar este tipo"));
		}
		return null;
	}

	@Override
	public Object visit(UnaryNot unaryNot, Object param) {
		unaryNot.getOperand().accept(this, param);
		unaryNot.setType(unaryNot.getOperand().getType().logical());
		if(unaryNot.getType()== null)
			unaryNot.setType(new ErrorType(unaryNot, "No se puede hacer una negación este tipo"));
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
		if(variable.getVarDefinition() != null)
			variable.setType(variable.getVarDefinition().getType());
		variable.setLValue(true);
		return null;
	}

	@Override
	public Object visit(WhileSetatement whileSetatement, Object param) {
		whileSetatement.getCondition().accept(this, param);
		if(whileSetatement.getCondition().getType()==null || !whileSetatement.getCondition().getType().isLogical() )
			whileSetatement.getCondition().setType(new ErrorType(whileSetatement, "Se esperaba una condición lógica"));
		for(Statement stm : whileSetatement.getStatements())
			stm.accept(this, param);
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
