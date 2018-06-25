package ast;

import ast.type.Type;
import visitor.Visitor;

public interface Expression extends ASTNode{
	
	public boolean getLValue();
	public void setLValue(boolean value);
	public Object accept(Visitor v, Object param);
	public Type getType();
	public void setType(Type type);
}
