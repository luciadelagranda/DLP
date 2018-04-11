
%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage

import ast.*;
import scanner.Scanner;
import java.io.Reader;
import java.util.*;

%}

// * Declaraciones Yacc
%token INT_CONSTANT

%token REAL_CONSTANT

%token ID

%token CHAR_CONSTANT
	
%token INPUT
	
%token PRINT
	
%token DEF
	
%token WHILE
	
%token IF
	
%token ELSE
	
%token INT
	
%token DOUBLE
	
%token CHAR
	
%token STRUCT
	
%token RETURN
	
%token VOID

%token MAIN

%token MENORIGUAL
	
%token MAYORIGUAL
	
%token EQUIVALENTE
	
%token DISTINTO 

%token OR
	
%token AND


%right '='
%left AND OR
%left '>' '<' DISTINTO MAYORIGUAL MENORIGUAL EQUIVALENTE
%left '+' '-'
%left '*' '/' '%'
%nonassoc '!'
%left '.'
%nonassoc '[' ']'
%nonassoc '(' ')'
%nonassoc SIMPLE
%nonassoc ELSE


%%
// * Gramática y acciones Yacc

programa : definiciones main				{$$ = (List<Definition>)$1; ((List<Definition>)$$).add((FunDefinition)$2);
											 raiz = new Program(scanner.getLine(), scanner.getColumn(),(List<Definition>)$$);}
		 ;
		 
definiciones : 								{$$ = new ArrayList<Definition>();}
			 |definiciones definicion       {$$ = $1; ((List<Definition>)$$).addAll((List<Definition>)$2);}	
			 ; 	

definicion: variable						{$$ = $1;}
          | funcion							{$$ = new ArrayList<Definition>(); ((List<Definition>)$$).add((Definition)$1);}
		  ;
		     		         		  
funcion: DEF ID '(' parametros ')' ':' tipoReturn body 	{FunctionType funcionType = new FunctionType(scanner.getLine(), scanner.getColumn(),(List<VarDefinition>)$4,(Type)$7);
														 $$ = new FunDefinition(scanner.getLine(), scanner.getColumn(),(String)$2,funcionType, (List<Statement>)$8);}
	   ;
	    	
main : DEF MAIN '('')' ':' VOID body		{FunctionType funcionType = new FunctionType(scanner.getLine(), scanner.getColumn(),null,VoidType.VoidTypeInstance(scanner.getLine(), scanner.getColumn()));
											 $$ = new FunDefinition(scanner.getLine(), scanner.getColumn(),(String)$2,funcionType,(List<Statement>)$7);}

body: '{' variables sentencias '}'			{$$ = new ArrayList<Statement>(); ((List<Statement>)$$).addAll((List<VarDefinition>)$2); ((List<Statement>)$$).addAll((List<Statement>)$3); }
	| '{' '}'								{$$ = $1; }
	| '{' sentencias '}'					{$$ = new ArrayList<Statement>(); ((List<Statement>)$$).addAll((List<Statement>)$2); }
	;

sentencias: sentencia						{$$ = new ArrayList<Statement>(); ((List<Statement>)$$).add((Statement)$1);}
      | sentencias sentencia				{$$ = $1 ; ((List<Statement>)$$).add((Statement)$2);}
      ;
      		  	 
sentencia: RETURN expression ';'			{$$ = new Return(scanner.getLine(), scanner.getColumn(), (Expression)$2 );}
         | while							{$$ = $1;}
         | print 							{$$ = $1;}
         | input							{$$ = $1;}
         | if								{$$ = $1;}
         | ifSimple							{$$ = $1;}
         | invocationStat ';'				{$$ = $1;}
         | assignment						{$$ = $1;}
         ;
         
invocationStat : ID '('paramsInvocation')'   {$$ = new InvocationStat(scanner.getLine(), scanner.getColumn(),new Variable(scanner.getLine(), scanner.getColumn(),(String)$1), (List<Expression>)$3);}
			   

invocationExpr : ID '('paramsInvocation')'   {$$ = new InvocationExpr(scanner.getLine(), scanner.getColumn(),new Variable(scanner.getLine(), scanner.getColumn(),(String)$1), (List<Expression>)$3);}

paramsInvocation: expressiones				 {$$ = new ArrayList<Expression>(); ((List<Expression>)$$).addAll((List<Expression>)$1);}
				| 							 {$$ = new ArrayList<Expression>();}
				;
assignment : expression '=' expression ';' {$$ = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3);}
           ;
         
if : IF expression ':' sentencia ELSE sentencia						{$$ = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)$2,(Statement)$4,((Statement)$6));}
   | IF expression ':' sentencia ELSE '{' sentencias '}'			{$$ = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)$2,(Statement)$4,(List<Statement>)$7);}
   | IF expression ':' '{' sentencias '}' ELSE sentencia			{$$ = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)$2,(List<Statement>)$5,((Statement) $8));}
   | IF expression ':' '{' sentencias '}' ELSE '{' sentencias '}'	{$$ = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)$2,(List<Statement>)$5,(List<Statement>)$9);}
   ;
   
ifSimple : IF expression ':' sentencia 		%prec SIMPLE			{$$ = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)$2,(List<Statement>)$4);}
   		 | IF expression ':' '{' sentencias '}' %prec SIMPLE		{$$ = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)$2,(List<Statement>)$5);}
      
    
		       
parametros: parametrosAUX					{$$ = $1;}		
		  |									{$$ = new ArrayList(); }
		  ;
 
parametrosAUX: parametro					{$$ = new ArrayList<VarDefinition>(); ((List<VarDefinition>)$$).add((VarDefinition)$1);}
		  | parametrosAUX ',' parametro		{$$ = $1; ((List<VarDefinition>)$1).add((VarDefinition)$3);}
		  ;
		  
parametro : ID ':' tipo					{$$ = new VarDefinition(scanner.getLine(), scanner.getColumn(),(String)$1,(Type)$3);}
		  ;		  
		  			 		 
variables : variable					{$$ = new ArrayList<VarDefinition>(); ((List<VarDefinition>)$$).addAll((List<VarDefinition>)$1);}
		  | variables variable			{$$ = $1; ((List<VarDefinition>)$$).addAll((List<VarDefinition>)$2);}
		 ;
		 
variable :  ident ':' tipoCompuesto ';'	{List<VarDefinition> variables = new ArrayList<VarDefinition>();
										 for(String ident : ((List<String>)$1))
										 	variables.add(new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)ident ,(Type)$3));	
										 $$ = variables;
										 }
		 ;

ident : ID								{$$ = new ArrayList<String>(); ((List<String>)$$).add((String)$1);}
	  | ident ',' ID					{$$ = $1; ((List<String>)$$).add((String)$3);}
		 
tipo : 	 INT 							{$$ = IntType.IntTypeInstance(scanner.getLine(), scanner.getColumn());}
       | DOUBLE							{$$ = DoubleType.DoubleTypeInstance(scanner.getLine(), scanner.getColumn());}
       | CHAR							{$$ = CharType.CharTypeInstance(scanner.getLine(), scanner.getColumn());}

tipoCompuesto : tipo							   {$$ = $1;}
			  | '[' INT_CONSTANT ']' tipoCompuesto {$$ = new ArrayType(scanner.getLine(), scanner.getColumn(),((int)$2),(Type)$4);}        
              | STRUCT '{' bodyStruct '}' 	   	   {$$ = new RecordType(scanner.getLine(), scanner.getColumn(),(List<RecordField>)$3);}	
               
tipoReturn : tipo						{$$ = $1;}
		   | VOID						{$$ = VoidType.VoidTypeInstance(scanner.getLine(), scanner.getColumn());}
		   ;
		         

bodyStruct : variablesStruct							{$$ = $1;}
		   | bodyStruct variablesStruct	   				{$$ = $1; ((List<RecordField>)$$).addAll((List<RecordField>)$2);}


variablesStruct : ident ':' tipoCompuesto ';'			{List<RecordField> variables = new ArrayList<RecordField>();
    													 for(String ident : ((List<String>)$1))
        													variables.add(new RecordField(scanner.getLine(), scanner.getColumn(),((String) ident), (Type)$3));
 														 $$ = variables;
														}



expressiones: expression					{$$ = new ArrayList<Expression>(); ((List<Expression>)$$).add((Expression)$1);}
			| expressiones ',' expression	{$$ = $1; ((List<Expression>)$$).add((Expression)$3);}
			
 
expression : ID								{$$ = new Variable(scanner.getLine(), scanner.getColumn(),(String)$1);} 
		 | expression '+' expression		{$$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String) $2 , (Expression)$3);}
         | expression '-' expression		{$$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String) $2 , (Expression)$3);}
         | expression '*' expression		{$$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String) $2 , (Expression)$3);}
         | expression '/' expression		{$$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String) $2 , (Expression)$3);}
         | expression '%' expression		{$$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String) $2 , (Expression)$3);}
         | expression '.' ID				{$$ = new FieldAccess(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String)$3);}
         | '(' expression ')'				{$$ = $2;}
         | expression '[' expression ']'	{$$ = new Indexin(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3);}
         | '!' expression					{$$ = new Logical(scanner.getLine(), scanner.getColumn(), null,"!", (Expression)$2);}
         | '-' expression					{$$ = new UnaryMinus(scanner.getLine(), scanner.getColumn(),(Expression)$2);}
         | expression '>' expression		 {$$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1,(String) $2, (Expression)$3);}
         | expression '<' expression		 {$$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1,(String) $2, (Expression)$3);}
         | expression DISTINTO expression    {$$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1,(String) $2, (Expression)$3);}
         | expression MAYORIGUAL expression  {$$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1,(String) $2, (Expression)$3);}
         | expression MENORIGUAL expression  {$$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1,(String) $2, (Expression)$3);}
         | expression EQUIVALENTE expression {$$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1,(String) $2, (Expression)$3);}
         | expression AND expression		 {$$ = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)$1,(String) $2, (Expression)$3);}
         | expression OR expression			 {$$ = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)$1,(String) $2, (Expression)$3);}	
         | REAL_CONSTANT					{$$ = new RealLiteral(scanner.getLine(), scanner.getColumn(),(Double)$1);}
         | INT_CONSTANT						{$$ = new IntLiteral(scanner.getLine(), scanner.getColumn(),(int)$1);}
         | CHAR_CONSTANT 					{$$ = new CharLiteral(scanner.getLine(), scanner.getColumn(),(char)$1);}
         | cast								{$$ = $1;}
         | invocationExpr					{$$ = $1;}
         ;      

cast :  '(' tipo ')' expression				{$$ = new Cast(scanner.getLine(), scanner.getColumn(), (Type)$2,(Expression)$4);}

while : WHILE expression ':' '{' sentencias '}'    {$$ = new WhileSetatement(scanner.getLine(), scanner.getColumn(), (Expression)$2, (List<Statement>)$5);}                		               			  	

print: PRINT expressiones ';'		{
  									 for(Expression exp : ((List<Expression>)$2))
        								$$ = new Write(scanner.getLine(), scanner.getColumn(), (Expression)exp);
        							 }			

input: INPUT expressiones ';'		{for(Expression exp : ((List<Expression>)$2))
        								$$ = new Write(scanner.getLine(), scanner.getColumn(), (Expression)exp);
        							}
           
%%

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private Scanner scanner;
private ASTNode raiz;

// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
		token=scanner.yylex(); 	
		this.yylval = scanner.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Lexical error at line " + scanner.getLine() + " and column "+scanner.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Syntactical error at line " + scanner.getLine() + " and column "+scanner.getColumn()+":\n\t"+error);
}

// * Constructor del Sintáctico
public Parser(Scanner scanner) {
	this.scanner = scanner;
}

public ASTNode getAST(){
    return raiz;
}
