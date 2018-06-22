package codeGenerator;

import ast.Assignment;
import ast.Definition;
import ast.FunDefinition;
import ast.IfStatement;
import ast.Invocation;
import ast.Program;
import ast.Read;
import ast.Statement;
import ast.VarDefinition;
import ast.WhileSetatement;
import ast.Write;
import ast.type.FunctionType;
import ast.type.Type;
import ast.type.VoidType;

public class ExecuteCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor{
	
	AddressCodeGeneratorVisitor cgAddress;
	ValueCodeGeneratorVisitor cgValue;

	public ExecuteCodeGeneratorVisitor(String input, String output) {
		super(new CodeGeneration(input, output));
		cgAddress = new AddressCodeGeneratorVisitor(cg);
		cgValue = new ValueCodeGeneratorVisitor(cg, cgAddress);
		
	}
	
	@Override
	public Object visit(Program program, Object param) {
		
		for (Definition definition : program.getDefinitions()) {
			if (definition instanceof VarDefinition) 
				definition.accept(this, param);}
			
		cg.call("main");
		cg.halt();

		for (Definition definition : program.getDefinitions()) {
			if (definition instanceof FunDefinition) 
				definition.accept(this, param);}
			
		return null;
	}
	
	@Override
	public Object visit(Write write, Object param) {
		write.getExpression().accept(cgValue, param);
		cg.out(write.getExpression().getType());

		return null;
	}
	
	@Override
	public Object visit(Read read, Object param) {
		read.getExpression().accept(cgAddress, param);
		cg.in(read.getExpression().getType());
		cg.store(read.getExpression().getType());

		return null;
	}
	
	@Override
	public Object visit(Assignment assignment, Object param) {
		assignment.getLeft().accept(cgAddress, param);
		assignment.getRight().accept(cgValue, param);
		cg.convertion(assignment.getRight().getType(), assignment.getLeft().getType());
		cg.store(assignment.getLeft().getType());

		return null;
	}
	
	@Override
	public Object visit(FunDefinition funDefinition, Object param) {
		cg.id(funDefinition.getName());
		cg.enter(funDefinition.bytesLocales());

		for (Statement stat : funDefinition.getStatements()) {
			if (!(stat instanceof VarDefinition)) {
				cg.lineComment(stat.getLine());
				stat.accept(this, funDefinition);
			}
		}

		Type ret = ((FunctionType) funDefinition.getType()).getReturnType();
		if (ret == VoidType.getInstancia()) {
			cg.ret(0, funDefinition.bytesLocales(), funDefinition.bytesParametros());
		}
		return null;

	}
	
	@Override
	public Object visit(WhileSetatement whileStatement, Object param) {
		int label = cg.getLabel(2);
		cg.label(label);
		whileStatement.getCondition().accept(cgValue, param);
		cg.jz(label + 1);
		for (Statement stat : whileStatement.getStatements()) {
			cg.lineComment(stat.getLine());
			stat.accept(this, param);
		}
		cg.jmp(label);
		cg.label(label + 1);

		return null;
	}
	
	@Override
	public Object visit(IfStatement ifStatement, Object param) {
		int label = cg.getLabel(2);
		ifStatement.getCondition().accept(cgValue, param);
		cg.jz(label);
		for (Statement stat : ifStatement.getIfBody()) {
			cg.lineComment(stat.getLine());
			stat.accept(this, param);
		}
		cg.jmp(label + 1);
		cg.label(label);
		if (ifStatement.getElseBody() != null) {
			for (Statement stat : ifStatement.getElseBody()) {
				cg.lineComment(stat.getLine());
				stat.accept(this, param);
			}
		}
		cg.label(label + 1);

		return null;
	}
	
	@Override
	public Object visit(Invocation invocation, Object param) {
		invocation.accept(cgValue, param);
		Type type = ((FunctionType) invocation.getFuncion().getType()).getReturnType();
		if (type != VoidType.getInstancia())
			cg.pop(((FunctionType) invocation.getFuncion().getType()).getReturnType());
		return null;

	}

}
