import java.io.FileReader;

import java.io.IOException;

import codeGenerator.ExecuteCodeGeneratorVisitor;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import scanner.Scanner;
import visitor.IdentificationVisitor;
import visitor.OffsetVisitor;
import visitor.TypeCheckingVisitor;
import visitor.Visitor;
import parser.Parser;
import errorhandler.EH;

public class Main {
	public static void main(String args[]) throws IOException {
		if (args.length < 1) {
			System.err.println("Pass me the name of the input file.");
			return;
		}

		FileReader fr = null;
		try {
			fr = new FileReader(args[0]);
		} catch (IOException io) {
			System.err.println("The file " + args[0] + " could not be opened.");
			return;
		}

		// * Scanner and parser creation
		Scanner lexico = new Scanner(fr);
		Parser parser = new Parser(lexico);
		// * Parsing
		parser.run();

		Visitor v1 = new IdentificationVisitor();
		parser.getAST().accept(v1, null);

		Visitor v = new TypeCheckingVisitor();
		parser.getAST().accept(v, null);
		
		Visitor offset = new OffsetVisitor();
		parser.getAST().accept(offset, null);
		
		Visitor execute = new ExecuteCodeGeneratorVisitor(args[0], args[1]);
		parser.getAST().accept(execute, null);

		// * Check errors
		if (EH.getEH().hasErrors()) {
			// * Show errors
			EH.getEH().showErrors(System.err);
		} else {
			// * Show AST
			IntrospectorModel model = new IntrospectorModel("Program", parser.getAST());
			new IntrospectorTree("Introspector", model);
		}
	}

}