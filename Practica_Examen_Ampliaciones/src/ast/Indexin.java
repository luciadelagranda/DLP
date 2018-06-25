package ast;

import visitor.Visitor;

public class Indexin extends ExpressionAbstract implements Statement{

	private Expression exp1;
	private Expression exp2;

	public Indexin(int line, int column, Expression exp1, Expression exp2) {
		super(line,column);
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public String toString() {
		return "Indexin [exp1=" + exp1 + ", exp2=" + exp2 + "]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public Expression getExp1() {
		return exp1;
	}

	public void setExp1(Expression exp1) {
		this.exp1 = exp1;
	}

	public Expression getExp2() {
		return exp2;
	}

	public void setExp2(Expression exp2) {
		this.exp2 = exp2;
	}

}
