package errorhandler;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import ast.ErrorType;

public class EH {
	
	private  List<ErrorType> errors;
	private static EH instance = null;
	
	private EH( List<ErrorType> errors) {
		this.errors = errors;
	}
	
	public static EH getEH() {
		if(instance == null)
			instance = new EH(new ArrayList<ErrorType>());
		
		return instance;
	}
	
	public  boolean  hasErrors() {
		if(errors == null || errors.isEmpty())
			return false;
		return true;
		
	}
	
	public void addError(ErrorType error) {
		if(errors!= null) 
			errors.add(error);
	}
	
	public  void showErrors(PrintStream ps) {
		for(ErrorType err : errors)
			ps.println(err.toString());
	}
}
