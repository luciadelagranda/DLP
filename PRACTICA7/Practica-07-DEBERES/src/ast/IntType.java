package ast;

import visitor.Visitor;

public class IntType extends ASTNodeAbstract implements Type{
	
	private static IntType instancia;
	
	
	private IntType(int line, int column) {
		super(line,column);
	}
	
	public static IntType IntTypeInstance(int line, int column) {
		instancia = new IntType(line, column);
		return instancia;
	}
	
	@Override
	public String toString() {
		return "IntType [int]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
