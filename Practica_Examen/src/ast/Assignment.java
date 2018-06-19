package ast;

import visitor.Visitor;

public class Assignment extends StatementAbstract implements Statement{
	
	private Expression left;
	private Expression right;
	
	public Assignment(int i, int j, Expression ex1, Expression ex2) {
		super(i, j);
		this.left = ex1;
		this.right = ex2;
	}

	@Override
	public String toString() {
		return "Assignment [left=" + left + ", right=" + right + "]";
	}
	
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
	}

}
