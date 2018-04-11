package ast;

public class VoidType extends ASTNodeAbstract implements Type{

	private static VoidType instancia;
	
	
	private VoidType(int line, int column) {
		super(line,column);
	}
	
	public static VoidType VoidTypeInstance(int line, int column) {
		instancia = new VoidType(line, column);
		return instancia;
	}
	
	@Override
	public String toString() {
		return "VoidType [void]";
	}
}
