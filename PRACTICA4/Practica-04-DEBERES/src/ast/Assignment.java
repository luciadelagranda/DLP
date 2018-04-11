package ast;

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
	
	
	

}
