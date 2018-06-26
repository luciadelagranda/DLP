package visitor;





import ast.Arithmetic;

import ast.Assignment;
import ast.AssignmentLogical;
import ast.Cast;
import ast.CharLiteral;
import ast.Comparison;
import ast.Expression;
import ast.FieldAccess;
import ast.FunDefinition;
import ast.IfStatement;
import ast.Indexin;
import ast.IntLiteral;
import ast.Invocation;
import ast.Logical;
import ast.Read;
import ast.RealLiteral;
import ast.Return;
import ast.Statement;
import ast.UnaryMinus;
import ast.UnaryNot;
import ast.UnarySum;
import ast.Variable;
import ast.WhileSetatement;
import ast.type.CharType;
import ast.type.DoubleType;
import ast.type.ErrorType;
import ast.type.FunctionType;
import ast.type.IntType;

public class TypeCheckingVisitor extends AbstractVisitor{
	
	

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
	public Object visit(Assignment assignment, Object param) {
		assignment.getLeft().accept(this,param);
		assignment.getRight().accept(this, param);
		
		if(!assignment.getLeft().getLValue()) {
			String msg = "La expresión de la derecha no se puede asignar a la expresion de la izquierda. La asignacion no es correcta. ";
			new ErrorType(assignment, msg);
		}
		
		if(assignment.getLeft().getType()!=null && assignment.getRight().getType()!=null) {
			assignment.getLeft().setType(assignment.getRight().getType().promotesTo(assignment.getLeft().getType()));
		
			if(assignment.getLeft().getType() == null)
				assignment.getLeft().setType( new ErrorType(assignment, "El tipo de la asignación no coincide."));
		}
		return null;
	}

	@Override
	public Object visit(Cast cast, Object param) {
		cast.getExp().accept(this, param);
		cast.getCastType().accept(this, param);
		
		cast.setType(cast.getCastType().canBeCast(cast.getExp().getType()));
		if(cast.getType() == null)
			cast.setType(new ErrorType(cast, "No se puede castear a ese tipo"));
		
		cast.setLValue(false);
		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object param) {
		charLiteral.setType(CharType.CharTypeInstance(charLiteral.getLine(), charLiteral.getColum()));
		charLiteral.setLValue(false);
		return null;
	}

	@Override
	public Object visit(Comparison comparison, Object param) {
		comparison.getExp1().accept(this, param);
		comparison.getExp2().accept(this, param);
		
		comparison.setType(comparison.getExp1().getType().comparison(comparison.getExp2().getType()));
		if(comparison.getType()== null)
			comparison.setType(new ErrorType(comparison, "No se puede comparar ese tipo"));
		comparison.setLValue(false);
		return null;
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {
		fieldAccess.getExp1().accept(this, param);
		
		if(fieldAccess.getExp1().getType()!= null) {
			fieldAccess.setType(fieldAccess.getExp1().getType().dot(fieldAccess.getName()));
			if(fieldAccess.getType()== null)
				fieldAccess.setType(new ErrorType(fieldAccess, "No se puede acceder a este campo"));
		}
		
		if(fieldAccess.getExp1().getLValue())
			fieldAccess.setLValue(true);
		
		return null;
	}

	@Override
	public Object visit(FunctionType functionType, Object param) {
		functionType.getReturnType().accept(this, param);
		for(Statement statement : functionType.getParameters())
			statement.accept(this, param);
		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object param) {
		funDefinition.getType().accept(this, param);
		if(funDefinition.getStatements()!=null)
			for (Statement statements : funDefinition.getStatements())
				statements.accept(this, funDefinition.getType());
		return null;
	}


	@Override
	public Object visit(Indexin indexin, Object param) {
		indexin.getExp1().accept(this, param);
		indexin.getExp2().accept(this, param);
		
		indexin.setType(indexin.getExp1().getType().squareBPacket(indexin.getExp2().getType()));
		if(indexin.getType() == null)
			indexin.setType(new ErrorType(indexin, "No se puede acceder a array."));
		
		indexin.setLValue(true);
		return null;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object param) {
		intLiteral.setType(IntType.IntTypeInstance(intLiteral.getLine(), intLiteral.getColum()));
		intLiteral.setLValue(false);

		return null;
	}

	@Override
	public Object visit(Invocation invocation, Object param) {
		invocation.getFuncion().accept(this, param);
		
		if(!invocation.getArguments().isEmpty()) {
			for ( Expression ex : invocation.getArguments())
				ex.accept(this, param);
		}
		
		invocation.setType(invocation.getFuncion().getType().parenthesis(invocation.getArguments())); 
		
		
		if(invocation.getType() == null)
			invocation.setType(new ErrorType(invocation, "No se puede invocar a esta expresión"));
		
		invocation.setLValue(false);
		return null;
	}

	@Override
	public Object visit(Logical logical, Object param) {
		logical.getExp1().accept(this, param);
		logical.getExp2().accept(this, param);
		logical.setType(logical.getExp1().getType().logical(logical.getExp2().getType()));
		if(logical.getType()== null)
			logical.setType(new ErrorType(logical, "No se puede hacer una operación lógica de estos tipos"));
		logical.setLValue(false);
		return null;
	}
	
	@Override
	public Object visit(AssignmentLogical assignmentLogical, Object param) {
		assignmentLogical.getExp1().accept(this, param);
		assignmentLogical.getExp2().accept(this, param);
		assignmentLogical.setType(assignmentLogical.getExp1().getType().logical(assignmentLogical.getExp2().getType()));
		if(assignmentLogical.getType()== null)
			assignmentLogical.setType(new ErrorType(assignmentLogical, "No se puede hacer una operación lógica de estos tipos"));
		
		if(!assignmentLogical.getExp1().getLValue()) {
			String msg = "La expresión de la derecha no se puede asignar a la expresion de la izquierda. La asignacion no es correcta. ";
			new ErrorType(assignmentLogical, msg);
		}
		
		if(assignmentLogical.getExp1().getType()!=null && assignmentLogical.getExp2().getType()!=null) {
			assignmentLogical.getExp1().setType(assignmentLogical.getExp2().getType().promotesTo(assignmentLogical.getExp1().getType()));
		
			if(assignmentLogical.getExp1().getType() == null)
				assignmentLogical.getExp1().setType( new ErrorType(assignmentLogical, "El tipo de la asignación no coincide."));
		}
		return null;
	}


	@Override
	public Object visit(RealLiteral realLiteral, Object param) {
		realLiteral.setType(DoubleType.DoubleTypeInstance(realLiteral.getLine(), realLiteral.getColum()));
		realLiteral.setLValue(false);
		return null;
	}


	@Override
	public Object visit(UnaryMinus unaryMinus, Object param) {
		unaryMinus.getOperand().accept(this, param);
		unaryMinus.setType(unaryMinus.getOperand().getType().arithmetic());
		if(unaryMinus.getType() == null) {
			unaryMinus.setType(new ErrorType(unaryMinus, "No se puede negativizar este tipo"));
		}
		
		unaryMinus.setLValue(false);
		return null;
	}
	
	@Override
	public Object visit(UnarySum unarySum, Object param) {
		if(unarySum.getOperand() instanceof Variable) {
		unarySum.getOperand().accept(this, param);
		unarySum.setType(unarySum.getOperand().getType().arithmetic());
		if(unarySum.getType() == null) {
			unarySum.setType(new ErrorType(unarySum, "No se puede sumar este tipo"));
		}
		}
		else
			unarySum.setType(new ErrorType(unarySum, "No se puede añadir uno si no es una variable"));
		
		unarySum.setLValue(false);
		return null;
	}

	@Override
	public Object visit(UnaryNot unaryNot, Object param) {
		unaryNot.getOperand().accept(this, param);
		unaryNot.setType(unaryNot.getOperand().getType().logical());
		if(unaryNot.getType()== null)
			unaryNot.setType(new ErrorType(unaryNot, "No se puede hacer una negación de este tipo"));
		unaryNot.setLValue(false);
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
			whileSetatement.getCondition().setType(new ErrorType(whileSetatement, "Se esperaba una condición lógica en el while"));
		for(Statement stm : whileSetatement.getStatements())
			stm.accept(this, param);
		return null;
	}
	
	@Override
	public Object visit(IfStatement ifStatement, Object param) {
		ifStatement.getCondition().accept(this, param);
		
		if(ifStatement.getCondition().getType()==null || !ifStatement.getCondition().getType().isLogical() )
			ifStatement.getCondition().setType(new ErrorType(ifStatement, "Se esperaba una condición lógica en el if"));
		
		for(Statement statement: ifStatement.getIfBody())
			statement.accept(this, param);
		
		if(ifStatement.getElseBody()!= null) {
		for(Statement statement: ifStatement.getElseBody())
			statement.accept(this, param);
		}
		return null;
	}
	
	@Override
	public Object visit(Return return1, Object param) {
		return1.getExpression().accept(this, param);
		
		if (return1.getExpression().getType()!= null) {
			FunctionType funcionType = (FunctionType) param;

			if (return1.getExpression().getType().promotesTo(funcionType.getReturnType()) == null) {
				new ErrorType(return1,
						"El tipo de la funcion no es compatible con el tipo de retorno.");
			}
		}

		return null;
	}
	
	@Override
	public Object visit(Read read, Object param) {
		read.getExpression().accept(this, param);
		if(!read.getExpression().getLValue()) 
			new ErrorType(read, "No puedes importar ese tipo de expression");
		
		return null;
	
	}
	
}
	
