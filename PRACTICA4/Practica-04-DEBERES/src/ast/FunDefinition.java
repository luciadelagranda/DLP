package ast;

import java.util.List;

public class FunDefinition extends ASTNodeAbstract implements Definition {

	private String name;
	private Type type;
	private List<Statement> statements;

	public FunDefinition(int line, int column, String string, Type type,
			List<Statement> statements) {
		super(line, column);
		this.name = string;
		this.type = type;
		this.statements = statements;
	}


	@Override
	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		return "FuncionDef [name=" + name + ", type=" + type + ", statements=" + statements + "]";
	}


	@Override
	public Type getType() {
		return type;
	}

}
