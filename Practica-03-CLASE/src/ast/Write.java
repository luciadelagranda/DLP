package ast;

public class Write extends StatementAbstract {
	
	private Expression variable;
	
	public Write(int i, int j,  Expression variable) {
		super(i, j);
		this.variable = variable;
	}

	@Override
	public String toString() {
		return "Write [variable=" + variable + "]";
	}
	
	
	

}
