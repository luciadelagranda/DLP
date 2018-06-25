package ast.type;

import visitor.Visitor;

public class VoidType extends TypeAbstract {

	private static VoidType instancia;
	
	
	private VoidType(int line, int column) {
		super(line,column);
	}
	
	public static VoidType VoidTypeInstance(int line, int column) {
		if(instancia==null)
			instancia = new VoidType(line, column);
		return instancia;
	}
	
	@Override
	public String toString() {
		return "VoidType [void]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public static Type getInstancia() {
		return instancia;
	}
}
