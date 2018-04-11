package ast.type;

import visitor.Visitor;

public class IntType extends TypeAbstract implements Type{
	
	private static IntType instancia;
	
	
	private IntType(int line, int column) {
		super(line,column);
	}
	
	public static IntType IntTypeInstance(int line, int column) {
		if(instancia==null)
			instancia = new IntType(line, column);
		return instancia;
	}
	
	@Override
	public String toString() {
		return "IntType [int]";
	}
	
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
	
	@Override
	public boolean isLogical() {
		return true;
	}
	
	@Override
	public Type arithmetic(Type type) {
		if(type instanceof IntType || type instanceof ErrorType || type instanceof DoubleType)
			return type;
		if(type instanceof CharType)
			return this;
		return null;
	}
	
	@Override
	public Type arithmetic() {
		return this;
	}
	
	@Override
	public Type canBeCast(Type type) {
		if(type instanceof IntType || type instanceof DoubleType || type instanceof CharType) 
			return this;
		return null;
	}
	
	@Override
	public Type logical() {
		return this;
	}
	
	@Override
	public Type logical(Type type) {
		if(type instanceof IntType)
			return this;
		return null;
	}
	
	@Override
	public Type comparison(Type expression) {
		if (expression instanceof IntType) {
			return this;
		} else if (expression instanceof ErrorType) {
			return expression;
		} else {
			return null;
		}
	}
	
	@Override
	public Type promotesTo(Type tipo) {
		if (tipo instanceof IntType) {
			return this;
		} else if (tipo instanceof ErrorType) {
			return tipo;
		} else {
			return null;
		}
	}
}
