package ast.type;

import java.util.List;


import ast.ASTNode;
import ast.Expression;
import ast.RecordField;

public interface Type extends ASTNode{

	boolean isLogical();

	Type arithmetic(Type type);

	Type arithmetic();

	Type comparison(Type type);

	Type logical(Type type);

	Type logical();

	Type dot(String name);

	Type squareBPacket(Type type);

	Type canBeCast(Type castType);

	Type promotesTo(Type type);

	Type parenthesis(List<Expression> arguments);
	
	int numberOfBytes();

	char suffix();

	RecordField get(String name);
	
}
