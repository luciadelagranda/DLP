package ast;

public abstract class ASTNodeAbstract implements ASTNode {
	
	private int line;
	private int colum;
	
	public ASTNodeAbstract(int i , int j) {
		this.line= i;
		this.colum = j;
	}
	
	
	@Override
	public int getLine() {
		return line;
	}

	@Override
	public int getColum() {
		return colum;
	}
	
}
