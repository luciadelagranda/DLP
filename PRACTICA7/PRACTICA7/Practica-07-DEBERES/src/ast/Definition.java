package ast;

import visitor.Visitor;

public interface Definition {
	
	public String getName();
	public Type getType();
	public Object accept(Visitor visitor, Object param);
}
