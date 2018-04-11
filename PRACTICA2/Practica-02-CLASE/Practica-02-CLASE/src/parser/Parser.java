
package parser;

import scanner.Scanner;

public class Parser {

    // * Tokens
    public final static int INT_CONSTANT = 257;

	public static final int REAL_CONSTANT = 258;

	public static final int ID = 259;

	public static final int CHAR_CONSTANT = 260;
	
	public static final int INPUT = 261;
	
	public static final int PRINT = 262;
	
	public static final int DEF = 263;
	
	public static final int WHILE = 264;
	
	public static final int IF = 265;
	
	public static final int ELSE = 266;
	
	public static final int INT = 267;
	
	public static final int DOUBLE = 268;
	
	public static final int CHAR = 269;
	
	public static final int STRUCT = 270;
	
	public static final int RETURN = 271;
	
	public static final int VOID = 272;
    
    private Scanner scanner;
    
    public Parser(Scanner scanner) {
        this.scanner = scanner;
    }
}