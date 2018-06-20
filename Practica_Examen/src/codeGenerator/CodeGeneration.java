package codeGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ast.type.Type;

public class CodeGeneration {

	private PrintWriter out;
	private int label = 0;

	public CodeGeneration(String input, String output) {

		try {
			out = new PrintWriter(new FileWriter(output));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void pushbp() {
		out.println("PUSH BP");
		out.flush();
	}
	
	public void pusha(int address) {
		out.println("PUSHA " + address);
		out.flush();
	}
	
	public void push(int x) {
		out.println("PUSHI " + x);
		out.flush();
	}
	
	public void push(double x) {
		out.println("PUSHF " + x);
		out.flush();
	}
	
	public void push(char x) {
		out.println("PUSHB " + x);
		out.flush();
	}
	
	public void load(Type type) {
		out.println("LOAD" + type.suffix());
		out.flush();
	}
	
	public void store(Type type) {
		out.println("STORE" + type.suffix());
		out.flush();
	}
	
	public void pop(Type type) {
		out.println("POP" + type.suffix());
		out.flush();
	}
	
	public void dup(Type type) {
		out.println("DUP" + type.suffix());
		out.flush();
	}
	
	public void add(Type type) {
		out.println("ADD" + type.suffix());
		out.flush();
	}
	
	public void sub(Type type) {
		out.println("SUB" + type.suffix());
		out.flush();
	}
	
	public void mul(Type type) {
		out.println("MUL" + type.suffix());
		out.flush();
	}
	
	public void div(Type type) {
		out.println("DIV" + type.suffix());
		out.flush();
	}
	
	public void comparison(String operand, Type type) {
		switch (operand) {
		case ">":
			out.println("GT" + type.suffix());
			break;
		case "<":
			out.println("LT" + type.suffix());
			break;
		case ">=":
			out.println("GE" + type.suffix());
			break;
		case "<=":
			out.println("LE" + type.suffix());
			break;
		case "==":
			out.println("EQ" + type.suffix());
			break;
		case "!=":
			out.println("NE" + type.suffix());
			break;
		default:
			break;
		}
	}
	
	public void and() {
		out.println("AND");
		out.flush();
	}
	
	public void or() {
		out.println("OR");
		out.flush();
	}
	
	public void not() {
		out.println("NOT");
		out.flush();
	}
	
	public void out(Type type) {
		out.println("OUT" + type.suffix());
		out.flush();
	}
	
	public void in(Type type) {
		out.println("IN" + type.suffix());
		out.flush();
	}
	
	private void b2i() {
		out.println("B2I");
		out.flush();
	}
	
	private void i2f() {
		out.println("I2F");
		out.flush();
	}
	
	private void i2b() {
		out.println("I2B");
		out.flush();
	}
	
	private void f2i() {
		out.println("F2I");
		out.flush();
	}
	
	public void convertion(Type typeOriginal, Type typeConvert) {
		switch (typeOriginal.suffix()) {
		case 'B':
			if(typeConvert.suffix() == 'I')
				b2i();
			break;
		case 'I':
			if(typeConvert.suffix() == 'F')
				i2f();
			else if(typeConvert.suffix() == 'B')
				i2b();
			break;
		case 'F':
			if(typeConvert.suffix() == 'I')
				f2i();
			break;

		default:
			break;
		}
	}
	
	public void id(String name){
		out.println(name + ":");
		out.flush();
	}
	
	public void jmp(int number) {
		out.println("JMP label" + number);
		out.flush();
	}
	
	public void jz(int number) {
		out.println("JZ label" + number);
		out.flush();
	}
	
	public void jnz(int number) {
		out.println("JNZ label" + number);
		out.flush();
	}
	
	public void call(String name) {
		out.println("CALL " + name);
		out.flush();
	}
	
	public void enter(int constant) {
		out.println("ENTER " + constant);
		out.flush();
	}
	
	public void ret(int constant, int constant1, int constant2) {
		out.println("RET " + constant + "," + constant1 + "," + constant2);
		out.flush();
	}
	
	public void halt() {
		out.println("HALT");
		out.flush();
	}
	
	public void sourceComment(String constant) {
		out.println("#source \"" + constant + "\"");
		out.flush();
	}
	
	public void lineComment(int constant) {
		out.println("#line " + constant);
		out.flush();
	}

}
