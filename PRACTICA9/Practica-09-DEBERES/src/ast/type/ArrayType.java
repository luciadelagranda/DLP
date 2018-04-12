package ast.type;

import visitor.Visitor;

public class ArrayType extends TypeAbstract  implements Type{

	private int size;
	private Type of;

	public ArrayType(int line, int column, int size, Type of) {
		super(line,column);
		this.size=size;
		this.of = of;
	}

	@Override
	public String toString() {
		return "ArrayType [size=" + size + ", of=" + of + "]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
	
	@Override
	public Type squareBPacket(Type index) {
		if (index instanceof IntType) {
			return this;
		} else if (index instanceof ErrorType) {
			return index;
		} else {
			return null;
		}
	}
}
