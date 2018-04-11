package ast;

public class RecordField extends ASTNodeAbstract {

	private String name;
	private Type type;
	public int offset;

	public RecordField(int i, int j, String name, Type type) {
		super(i, j);
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return "RecordField [name=" + name + ", type=" + type + ", offset=" + offset + "]";
	}

	

}
