package ast;

public class RealLiteral extends ExpressionAbstract implements Expression{

	private Double value;

	public RealLiteral(int line, int column, Double value) {
		super(line,column);
		this.value = value;
	}

	@Override
	public String toString() {
		return "RealLiteral [value=" + value + "]";
	}

}
