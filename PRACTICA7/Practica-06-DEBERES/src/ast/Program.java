package ast;

import java.util.List;

import visitor.Visitor;




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


	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}



	public List<Definition> getDefinitions() {
		return definitions;
	}



	public void setDefinitions(List<Definition> definitions) {
		this.definitions = definitions;
	}


	
	
	

}
