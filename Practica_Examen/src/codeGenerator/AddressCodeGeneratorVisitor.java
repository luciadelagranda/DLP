package codeGenerator;

import ast.FieldAccess;
import ast.Indexin;
import ast.VarDefinition;
import ast.Variable;
import ast.type.IntType;

public class AddressCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {
	
	private ValueCodeGeneratorVisitor cgValue;
	
	public AddressCodeGeneratorVisitor(CodeGeneration cg) {
		super(cg);
	}
	
	@Override
	public Object visit(Variable var, Object param) {
		
		VarDefinition varDef = (VarDefinition) var.getVarDefinition();
		
		if(varDef.getScope() == 0)
			cg.pusha(varDef.getOffset());
		else {
			cg.pushbp();
			cg.push(varDef.getOffset());
			cg.add(IntType.getInstancia());
		}
		
		return null;
	}
	
	
	@Override
	public Object visit(FieldAccess fieldAccess, Object param) {
		fieldAccess.getExp1().accept(this, param);
		cg.push(fieldAccess.getExp1().getType().get(fieldAccess.getName()).getOffset());
		cg.add(IntType.getInstancia());
		return null;
	}
	
	@Override
	public Object visit(Indexin index, Object param) {
		index.getExp1().accept(this, param);
		index.getExp2().accept(cgValue, param);
		cg.push(index.getType().numberOfBytes());
		cg.mul(IntType.getInstancia());
		cg.add(IntType.getInstancia());
		return null;
	}
	
	
}
