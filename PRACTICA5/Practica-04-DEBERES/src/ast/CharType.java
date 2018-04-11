package ast;

public class CharType extends ASTNodeAbstract implements Type{
	
	private static CharType instancia;
	
	private CharType(int line, int column) {
		super(line,column);
	}
	
	public static CharType CharTypeInstance(int line, int column) {
		instancia = new CharType(line, column);
		return instancia;
	}
	
	@Override
	public String toString() {
		return "CharType [char]";
	}
}	
