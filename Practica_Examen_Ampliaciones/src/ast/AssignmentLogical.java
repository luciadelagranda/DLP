package ast;

import visitor.Visitor;

public class AssignmentLogical extends ExpressionAbstract {

	private Expression exp1;
	private Expression exp2;
	private String operator;

	public AssignmentLogical(int line, int column, Expression exp1, String operator, Expression exp2) {
		super(line,column);
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.operator = operator;
	}
	
	

	@Override
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



	public String getOperator() {
		return operator;
	}



	public void setOperator(String operator) {
		this.operator = operator;
	}



	@Override
	public String toString() {
		return "AssignmentLogical [exp1=" + exp1 + ", exp2=" + exp2 + ", operator=" + operator + "]";
	}
	
	
	

}
