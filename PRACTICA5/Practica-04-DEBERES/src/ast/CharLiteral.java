package ast;

public class CharLiteral extends ExpressionAbstract implements Expression{
	
	private int value;
	
	public CharLiteral(int line, int column, char value) {
		super(line, column);
		this.value = value;
	}

	@Override
	public String toString() {
		return "CharLiteral [value=" + value + "]";
	}

}
