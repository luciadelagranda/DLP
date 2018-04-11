package ast;

import java.util.List;

public class Program extends ASTNodeAbstract {

	private List<Statement> statements;

	public Program(int i, int j, List<Statement> statements) {
		super(i, j);
		this.statements = statements;
	}

	@Override
	public String toString() {
		return "Program [statements=" + statements + "]";
	}
	
	
	

}
