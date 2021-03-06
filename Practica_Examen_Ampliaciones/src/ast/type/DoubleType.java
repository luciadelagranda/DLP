package ast.type;

import visitor.Visitor;

public class DoubleType extends TypeAbstract {

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
		else if (type instanceof ErrorType)
			return type;
		return null;
	}

	@Override
	public Type arithmetic() {
		return this;
	}

	@Override
	public Type comparison(Type expression) {
		if (expression instanceof DoubleType || expression instanceof CharType || expression instanceof IntType) {
			return IntType.IntTypeInstance(expression.getLine(), expression.getColum());
		} else if (expression instanceof ErrorType) {
			return expression;
		}

		return null;
	}

	@Override
	public Type promotesTo(Type type) {
		if (type instanceof DoubleType)
			return this;
		else if (type instanceof ErrorType)
			return type;
		return null;
	}

	@Override
	public int numberOfBytes() {
		return 4;
	}
	
	@Override
	public char suffix() {
		return 'F';
	}
	
	@Override
	public Type superType(Type type) {
		if (type instanceof ErrorType) 
			return type;
		else if (type instanceof CharType || type instanceof DoubleType || type instanceof IntType) 
			return this;

		return null;
	}
	
	
}
