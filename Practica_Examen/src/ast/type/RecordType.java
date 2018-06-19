package ast.type;

import java.util.List;

import ast.RecordField;
import visitor.Visitor;

public class RecordType extends TypeAbstract implements Type{

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
	
	@Override
	public Type dot(String name) {
		for(RecordField rf : fields)
			if(rf.getName().equals(name))
				return rf.getType();
		return null;
	}

	public List<RecordField> getFields() {
		return fields;
	}

	public void setFields(List<RecordField> fields) {
		this.fields = fields;
	}
}
