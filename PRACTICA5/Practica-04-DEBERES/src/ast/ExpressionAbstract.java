package ast;

public abstract class ExpressionAbstract extends ASTNodeAbstract implements Expression {

	private boolean lValue;

	public ExpressionAbstract(int i, int j) {
		super(i, j);
	}
	
	@Override
	public boolean getLValue() {
		return lValue;
	}
	
	@Override
	public void setLValue(boolean value) {
		this.lValue=value;
	}

}
