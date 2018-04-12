package ast;

import java.util.ArrayList;
import java.util.List;

import visitor.Visitor;



public class IfStatement extends StatementAbstract implements Statement{

	private Expression condition;
	private List<Statement> ifBody;
	private List<Statement> elseBody;

	public IfStatement(int line, int column, Expression condition, List<Statement> ifBody , List<Statement> elseBody) {
		super(line,column);
		this.condition= condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}

	public IfStatement(int line, int column, Expression condition, List<Statement> ifBody) {
		super(line,column);
		this.condition= condition;
		this.ifBody = ifBody;
		this.elseBody = null;
	}

	public IfStatement(int line, int column, Expression condition, List<Statement> ifBody, Statement statement) {
		super(line,column);
		this.condition = condition;
		this.ifBody= ifBody;
		this.elseBody = new ArrayList<Statement>();
		this.elseBody.add(statement);
	}
	
	public IfStatement(int line, int column, Expression condition, Statement statement, List<Statement> elseBody) {
		super(line,column);
		this.condition = condition;
		this.elseBody= elseBody;
		this.ifBody = new ArrayList<Statement>();
		this.ifBody.add(statement);
	}
	
	public IfStatement(int line, int column, Expression condition, Statement ifBody, Statement elseBody) {
		super(line,column);
		this.condition = condition;
		this.ifBody = new ArrayList<Statement>();
		this.ifBody.add(ifBody);
		this.elseBody = new ArrayList<Statement>();
		this.elseBody.add(elseBody);
	}

	@Override
	public String toString() {
		return "IfStatement [condition=" + condition + ", ifBody=" + ifBody + ", elseBody=" + elseBody + "]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
