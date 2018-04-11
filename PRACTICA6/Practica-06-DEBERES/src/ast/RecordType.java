package ast;

import java.util.List;

import visitor.Visitor;

public class RecordType extends ASTNodeAbstract implements Type{

	private List<RecordField> fields;

	public RecordType(int line, int column, List<RecordField> fields) {
		super(line,column);
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "RecordType [fields=" + fields + "]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
