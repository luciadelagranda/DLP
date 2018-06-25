package ast.type;

import visitor.Visitor;

public class CharType extends TypeAbstract {

	private static CharType instancia;

	private CharType(int line, int column) {
		super(line, column);
	}

	public static CharType CharTypeInstance(int line, int column) {
		if (instancia == null)
			instancia = new CharType(line, column);
		return instancia;
	}

	@Override
	public String toString() {
		return "CharType [char]";
	}

	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
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
	public Type comparison(Type expression) {
		if (expression instanceof ErrorType) {
			return expression;
		} else if (expression instanceof CharType || expression instanceof DoubleType
				|| expression instanceof IntType) {
			return IntType.getInstancia();
		}

		return null;
	}

	@Override
	public Type logical() {
		return this;
	}

	@Override
	public Type logical(Type expression) {
		if (expression instanceof CharType || expression instanceof IntType) {
			return IntType.getInstancia();
		} else if (expression instanceof ErrorType) {
			return expression;
		}

		return null;

	}

	@Override
	public Type promotesTo(Type type) {
		if (type instanceof CharType)
			return this;
		else if (type instanceof ErrorType || type instanceof DoubleType || type instanceof IntType)
			return type;
		return null;

	}

	@Override
	public Type arithmetic(Type type) {
		if (type instanceof ErrorType || type instanceof DoubleType || type instanceof IntType)
			return type;
		if (type instanceof CharType)
			return this;
		return null;

	}

	@Override
	public int numberOfBytes() {
		return 1;
	}
	
	@Override
	public char suffix() {
		return 'B';
	}
	
	@Override
	public Type superType(Type type) {
		if (type instanceof ErrorType || type instanceof DoubleType || type instanceof IntType) 
			return type;
		else if (type instanceof CharType) 
			return IntType.getInstancia();

		return null;
	}

	public static Object getInstancia() {
		return instancia;
	}
}
