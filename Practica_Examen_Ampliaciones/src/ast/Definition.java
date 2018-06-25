package ast;

import ast.type.Type;
import visitor.Visitor;

public interface Definition {
	
	public String getName();
	public Type getType();
	public Object accept(Visitor visitor, Object param);
	
	public int getScope();
	public void setScope(int scope);
	
}
