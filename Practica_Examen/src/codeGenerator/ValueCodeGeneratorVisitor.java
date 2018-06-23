package codeGenerator;

import ast.CharLiteral;
import ast.Comparison;
import ast.Expression;
import ast.FieldAccess;
import ast.Indexin;
import ast.IntLiteral;
import ast.Invocation;
import ast.RealLiteral;
import ast.UnaryMinus;
import ast.UnaryNot;
import ast.Variable;
import ast.type.FunctionType;
import ast.type.IntType;
import ast.type.Type;

public class ValueCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {
	
	private AddressCodeGeneratorVisitor cgAddress;
	
	public ValueCodeGeneratorVisitor(CodeGeneration cg) {
		super(cg);
	}
	
	public ValueCodeGeneratorVisitor(CodeGeneration cg, AddressCodeGeneratorVisitor cgAddress) {
		super(cg);
		this.cgAddress  = cgAddress;
	}
	
	@Override
	public Object visit(IntLiteral intLiteral, Object param) {
		cg.push(intLiteral.getValue());
		return null;
	}
	
	@Override
	public Object visit(CharLiteral charLiteral, Object param) {
		cg.push(charLiteral.getValue());
		return null;
	}
	
	@Override
	public Object visit(RealLiteral realLiteral, Object param) {
		cg.push(realLiteral.getValue());
		return null;
	}
	
	@Override
	public Object visit(Variable var, Object param) {
		var.accept(cgAddress, param);
		cg.load(var.getVarDefinition().getType());
		return null;
	}
	
	@Override
	public Object visit(Indexin indexing, Object param) {
		indexing.accept(cgAddress, param);
		cg.load(indexing.getType());
		return null;
	}
	
	@Override
	public Object visit(FieldAccess field, Object param) {
		field.accept(cgAddress, param);
		cg.load(field.getType());
		return null;
	}
	
	@Override
	public Object visit(Comparison comparison, Object param) {

		Type superType = comparison.getExp1().getType().superType(comparison.getExp2().getType());
		comparison.getExp1().accept(this, param);
		cg.convertion(comparison.getExp1().getType(), superType);
		comparison.getExp2().accept(this, param);
		cg.convertion(comparison.getExp2().getType(), superType);
		cg.comparison(comparison.getOperator(), superType);

		return null;
	}
	
	@Override
	public Object visit(UnaryNot unaryNot, Object param) {
		unaryNot.getOperand().accept(this, param);
		cg.not();
		return null;
	}
	
	@Override
	public Object visit(UnaryMinus unaryMinus, Object param) {
		unaryMinus.getOperand().accept(this, param);
		cg.push(-1);
		cg.convertion(IntType.getInstancia(), unaryMinus.getType());
		cg.mul(unaryMinus.getOperand().getType());
		return null;
	}
	
	@Override
	public Object visit(Invocation invocation, Object param) {
		int cont = 0;
		for (Expression expression : invocation.getArguments()) {
			expression.accept(this, param);
			cont++;
			cg.convertion(expression.getType(),((FunctionType) invocation.getFuncion().getType()).getParameters().get(cont).getType());
		}
		cg.call(invocation.getFuncion().getName());
		return null;

	}

}
