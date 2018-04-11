package ast;

public class ArrayType extends ASTNodeAbstract implements Type{

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

}
