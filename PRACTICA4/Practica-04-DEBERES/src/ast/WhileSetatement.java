package ast;


import java.util.List;

public class WhileSetatement extends StatementAbstract implements Statement{

	private Expression condition;
	private List<Statement> statements;

	public WhileSetatement(int line, int column, Expression condition, List<Statement> statements) {
		super(line, column);
		this.condition = condition;
		this.statements = statements;
	}

	@Override
	public String toString() {
		return "WhileSetatement [condition=" + condition + ", statements=" + statements + "]";
	}

}
