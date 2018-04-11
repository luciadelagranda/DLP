package ast;

import visitor.Visitor;

public interface Expression {
	
	public boolean getLValue();
	public void setLValue(boolean value);
	public Object accept(Visitor v, Object param);
}
