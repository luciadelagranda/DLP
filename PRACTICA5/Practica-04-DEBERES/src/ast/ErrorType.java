package ast;

import errorhandler.EH;

public class ErrorType extends ASTNodeAbstract implements Type{
	
	private String message;

	public ErrorType(int line, int column, String message) {
		super(line, column);
		this.message= message;
		EH.getEH().addError(this);
	}
	
	public ErrorType(ASTNode node, String message) {
		this(node.getLine(),node.getColum(), message);
		this.message= message;
		EH.getEH().addError(this);
	}

	@Override
	public String toString() {
		return "ERROR: " + message ;
	}
	
	

}
