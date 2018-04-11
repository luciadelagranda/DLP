package ast;


public class VarDefinition extends StatementAbstract implements Definition, Statement {

	public Type type;
	public int offset;
	public int scope;
	private String name;

	public VarDefinition(int i, int j) {
		super(i, j);
	}
	
	public VarDefinition(int line, int column, String name, Type type) {
		this(line,column);
		this.name = name;
		this.type = type;
	}
	

	public Type getType() {
		return type;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "VariableDef [type=" + type + ", Offset=" + offset + ", scope=" + scope + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + offset;
		result = prime * result + scope;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		VarDefinition other = (VarDefinition) obj;
		if (offset != other.offset)
			return false;
		if (scope != other.scope)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String getName() {
		return name;
	}

}
