package ast;


import java.util.List;

import visitor.Visitor;

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
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

}
