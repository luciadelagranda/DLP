package ast.type;

import java.util.List;

import ast.ASTNodeAbstract;
import ast.Expression;

public abstract class TypeAbstract extends ASTNodeAbstract implements Type{


	public TypeAbstract(int i, int j) {
		super(i, j);
	}

	@Override
	public boolean isLogical() {
		return false;
	}

	@Override
	public Type arithmetic(Type type) {
		return null;
	}

	@Override
	public Type arithmetic() {
		return null;
	}

	@Override
	public Type comparison(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type logical(Type type) {
		return null;
	}

	@Override
	public Type logical() {
		return null;
	}

	@Override
	public Type dot(String name) {
		return null;
	}

	@Override
	public Type squareBPacket(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type canBeCast(Type castType) {
		return null;
	}

	@Override
	public Type promotesTo(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type parenthesis(List<Expression> arguments) {
		return null;
	}
	
	@Override
	public int numberOfBytes() {
		throw new IllegalStateException("Error al calcular el número de bytes");
		
	}
	
	@Override
	public char suffix() {
		throw new IllegalStateException("Error al devolver el sufijo.");
	}
}
