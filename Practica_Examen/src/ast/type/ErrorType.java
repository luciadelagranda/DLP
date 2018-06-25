package ast.type;

import ast.ASTNode;
import errorhandler.EH;
import visitor.Visitor;

public class ErrorType extends TypeAbstract {
	
	private String message;

	public ErrorType(int line, int column, String message) {
		super(line, column);
		this.message= message;
		EH.getEH().addError(this);
	}
	
	public ErrorType(ASTNode node, String message) {
		this(node.getLine(),node.getColum(), message);
	}

	@Override
	public String toString() {
		return "ERROR: " + message  +  " Linea: " + this.getLine() + " Columna:  " +this.getColum();
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
	

}
