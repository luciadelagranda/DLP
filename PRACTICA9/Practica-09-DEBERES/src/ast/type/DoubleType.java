package ast.type;

import visitor.Visitor;

public class DoubleType extends TypeAbstract implements Type {

	private static DoubleType instancia;

	private DoubleType(int line, int column) {
		super(line, column);
	}

	public static DoubleType DoubleTypeInstance(int line, int column) {
		if (instancia == null)
			instancia = new DoubleType(line, column);
		return instancia;
	}

	@Override
	public String toString() {
		return "DoubleType [double]";
	}

	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

	@Override
	public Type arithmetic(Type type) {
		if (type instanceof IntType || type instanceof CharType || type instanceof DoubleType)
			return this;
		if (type instanceof ErrorType)
			return type;
		return null;
	}

	@Override
	public Type canBeCast(Type type) {
		if (type instanceof IntType || type instanceof DoubleType || type instanceof CharType)
			return this;
		return null;
	}


	@Override
	public Type arithmetic() {
		return this;
	}

	@Override
	public Type comparison(Type expresion) {
		if (expresion instanceof DoubleType) {
			return this;
		} else if (expresion instanceof ErrorType) {
			return expresion;
		}

		return null;
	}
	
	@Override
	public Type promotesTo(Type type) {
		if (type instanceof DoubleType) {
			return this;
		} else if (type instanceof ErrorType) {
			return type;
		} else {
			return null;
		}
	}
}
