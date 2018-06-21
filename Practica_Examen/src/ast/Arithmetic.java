package ast;

import visitor.Visitor;

public class Arithmetic extends ExpressionAbstract {
	
	private String operator;
	private Expression left;
	private Expression right;

	public Arithmetic(int i, int j, Expression ex1, String operator, Expression ex2) {
		super(i, j);
		this.operator = operator;
		this.left = ex1;
		this.right = ex2;
	}

	@Override
	public String toString() {
		return "Arithmetic [operator=" + operator + ", left=" + left + ", right=" + right + "]";
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	

}
