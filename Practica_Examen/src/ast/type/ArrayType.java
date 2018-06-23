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
			return of;
		} else if (index instanceof ErrorType) {
			return index;
		} else {
			return null;
		}
	}

	public Type getOf() {
		return of;
	}

	public void setOf(Type of) {
		this.of = of;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public int numberOfBytes() {
		return this.getOf().numberOfBytes() * this.getSize();
	}

	
	
}
