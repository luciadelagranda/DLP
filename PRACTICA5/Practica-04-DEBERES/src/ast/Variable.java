package ast;

public class Variable extends ExpressionAbstract implements Expression{
	
	private String name;
	
	public Variable(int i, int j, String name) {
		super(i,j);
		this.name = name;
	}

	@Override
	public String toString() {
		return "Variable [name=" + name + "]";
	}

	public String getName() {
		return name;
	}
	
	
	
	

}
