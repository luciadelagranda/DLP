package ast;

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
	
	
	

}
