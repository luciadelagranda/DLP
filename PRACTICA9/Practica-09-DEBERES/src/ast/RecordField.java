package ast;

import ast.type.Type;
import visitor.Visitor;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecordField other = (RecordField) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
