%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import scanner.Scanner;
import java.io.Reader;
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

programa : definiciones main
		 ;
		 
definiciones : 
			 |definiciones definicion
			 ; 	

definicion: variable
          | funcion 
		  ;
		     		         		  
funcion: DEF ID '(' parametros ')' ':' tipoReturn body
       
main : DEF MAIN '('')' ':' VOID body       

body: '{' variables sentencias '}'
	| '{' '}'

		  	 
sentencia: RETURN expression ';'
         | while
         | print 
         | input
         | if
         | ifSimple
         | invocation ';'
         | assignment       
         ;
         
invocation : ID '('expressiones')' 

 

assignment : expression '=' expression ';'         	      	         
         
if : IF expression ':' sentencia ELSE sentencia
   | IF expression ':' sentencia ELSE '{' sentencias '}'		
   | IF expression ':' '{' sentencias '}' ELSE sentencia
   | IF expression ':' '{' sentencias '}' ELSE '{' sentencias '}'
   ;
   
ifSimple : IF expression ':' sentencia 		%prec SIMPLE
   		 | IF expression ':' '{' sentencias '}' %prec SIMPLE
      

    
sentencias: sentencia
      | sentencias sentencia
      ;    
		       
parametros: parametrosAUX
		  |
		  ;
 
parametrosAUX: parametro
		  | parametrosAUX ',' parametro
		  ;
		  
parametro : ID ':' tipo
		  ;		  
		  			 		 
variables : variable
		  | variables variable
		 ;
		 
variable :  ident ':' tipo ';'
		 | struct
		 ;

ident : ID
	  | ident ',' ID	
		 
tipo : 	 INT 
       | DOUBLE
       | CHAR
       | '[' INT_CONSTANT ']' tipo
      
               
tipoReturn : tipo						
		   | VOID						
		   ;
		         
struct : ident ':' STRUCT '{' bodyStruct '}' ';'		{}

bodyStruct : variable
		   | bodyStruct variable

expressiones: expression
			| expressiones ',' expression
			| 
 
expression : expression '+' expression
         | expression '-' expression
         | expression '*' expression
         | expression '/' expression
         | expression '%' expression
         | expression '.' expression
         | '(' expression ')'
         | expression '[' expression ']'
         | '!' expression
         | '-' expression
         | expression '>' expression
         | expression '<' expression
         | expression DISTINTO expression
         | expression MAYORIGUAL expression
         | expression MENORIGUAL expression
         | expression EQUIVALENTE expression
         | expression AND expression
         | expression OR expression
         | REAL_CONSTANT
         | INT_CONSTANT
         | CHAR_CONSTANT 
         | ID
         | cast
         | invocation 
         ;      

cast :  '(' tipo ')' expression

while : WHILE expression ':' '{' sentencias '}'                        		               			  	

print: PRINT expressiones ';'

input: INPUT expression ';'
           
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
