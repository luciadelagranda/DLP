package ast;

import ast.type.Type;
import visitor.Visitor;

public class Cast extends ExpressionAbstract implements Expression {

	private Type castType;
	private Expression exp;

	public Cast(int line, int column, Type castType, Expression exp) {
		super(line,column);
		this.castType = castType;
		this.exp = exp;
	}

	@Override
	public String toString() {
		return "Cast [castType=" + castType + ", exp=" + exp + "]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public Type getCastType() {
		return castType;
	}

	public void setCastType(Type castType) {
		this.castType = castType;
	}

	public Expression getExp() {
		return exp;
	}

	public void setExp(Expression exp) {
		this.exp = exp;
	}
	
}
