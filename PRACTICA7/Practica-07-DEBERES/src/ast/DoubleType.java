package ast;

import visitor.Visitor;

public class DoubleType extends ASTNodeAbstract implements Type{
	
	private static DoubleType instancia;
	
	private DoubleType(int line, int column) {
		super(line,column);
	}
	
	public static DoubleType DoubleTypeInstance(int line, int column) {
		instancia = new DoubleType(line, column);
		return instancia;
	}
	
	@Override
	public String toString() {
		return "DoubleType [double]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
