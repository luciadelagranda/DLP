package visitor;

import ast.FunDefinition;
import ast.RecordField;
import ast.Statement;
import ast.VarDefinition;
import ast.type.FunctionType;
import ast.type.RecordType;

public class OffsetVisitor extends AbstractVisitor{
	
	private int globalCont = 0;
	private int localCont = 0;
	private int paramCont = 4;
	
	@Override
	public Object visit(VarDefinition varDefinition, Object param) {
		varDefinition.getType().accept(this, param);
		if(varDefinition.getScope() == 0) {
			varDefinition.setOffset(globalCont);
			globalCont += varDefinition.getType().numberOfBytes();
		}
		else {
			if(!(boolean) param) {
				localCont -= varDefinition.getType().numberOfBytes();
				varDefinition.setOffset(localCont);
			}
			else {
				varDefinition.setOffset(paramCont);
				paramCont += varDefinition.getType().numberOfBytes();
			}
		}
		return null;
			
	}
	
	
	@Override
	public Object visit(FunDefinition funDefinition, Object param) {
		funDefinition.getType().accept(this, param);
		
		for (Statement statement : funDefinition.getStatements()) 
			statement.accept(this, false);
		
		localCont = 0;
		paramCont = 4;
		return null;

	}
	
	@Override
	public Object visit(FunctionType functionType, Object param) {
		functionType.getReturnType().accept(this, param);
		
		for (int i = (functionType.getParameters().size() - 1);i >= 0;i--)
			functionType.getParameters().get(i).accept(this, true);
		
		return null;
	}
	
	@Override
	public Object visit(RecordType recordType, Object param) {
		int cont = 0;
		for (RecordField field : recordType.getFields()) {
			field.accept(this, param);
			field.setOffset(cont);
			cont += field.getType().numberOfBytes();
		}
		return null;
	}
	
}
