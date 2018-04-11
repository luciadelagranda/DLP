package ast;

public class Read extends StatementAbstract {

	private Expression variable;

	public Read(int i, int j, Expression var) {
		super(i, j);
		this.variable = var;
		
	}

	@Override
	public String toString() {
		return "Read [variable=" + variable + "]";
	}
	
	
	
}
