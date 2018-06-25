package ast;

import visitor.Visitor;

public interface ASTNode {
	
	public int getLine();
	public int getColum();
	public Object accept(Visitor v, Object object);
	
}
