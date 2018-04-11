package ast;

import java.util.List;




public class Program extends ASTNodeAbstract {

	private List<Definition> definitions;
	
	

	public Program(int i, int j, List<Definition> list) {
		super(i, j);
		this.definitions = list;
	}



	@Override
	public String toString() {
		return "Program [definitions=" + definitions + "]";
	}


	


	
	
	

}
