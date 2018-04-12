// ************  Código a incluir ********************

package scanner;
import parser.Parser;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Scanner
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************

// * Para acceder al número de línea (yyline es package)
public int getLine() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumn() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

// * Valor semantico del token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}

%}

// ************  Patrones (macros) ********************
ConstanteEntera = [0-9]*

Espacio = [ \t\r\n]
Coment= \#.*
Coments = \"\"\"~\"\"\"

Exponente= [0-9]?[Ee][+-]?[0-9]+
ConstanteDecimal = [0-9]+[.][0-9]* | [0-9]*[.]?[0-9]+[Ee]-?[0-9]+

Ident = [a-zA-Z_][a-zA-Z0-9_]*

Char = \'~\'

Ascii = [']\\[0-9]*[']

%%
// ************  Acciones ********************

// * COMENTARIOS
{Espacio}			{ }

{Coment} 			{ }

{Coments} 			{ }
         			  
//PALABRAS CLAVE

input				{ this.yylval = yytext();
         			  return Parser.INPUT; }

print				{ this.yylval = yytext();
         			  return Parser.PRINT; }

def					{ this.yylval = yytext();
         			  return Parser.DEF; }
         			  
while				{ this.yylval = yytext();
         			  return Parser.WHILE; }
         			  
if					{ this.yylval = yytext();
         			  return Parser.IF; }
         			  
else				{ this.yylval = yytext();
         			  return Parser.ELSE; }
         			  
int					{ this.yylval = yytext();
         			  return Parser.INT; } 
         			  
double				{ this.yylval = yytext();
         			  return Parser.DOUBLE; }
         			  
char				{ this.yylval = yytext();
         			  return Parser.CHAR; }
         			  
return				{ this.yylval = yytext();
         			  return Parser.RETURN; }
         			  
void				{ this.yylval = yytext();
         			  return Parser.VOID; }

struct				{ this.yylval = yytext();
         			  return Parser.STRUCT; }         			   

main				{ this.yylval = yytext();
         			  return Parser.MAIN; }

id					{ this.yylval = yytext();
         			  return Parser.ID; }

//OPERADORES
{Exponente}			{ this.yylval = new Double(yytext());
         			  return Parser.REAL_CONSTANT; }

"+"					{ this.yylval = yytext();
					  return '+'; }

"-"					{ this.yylval = yytext();
					  return '-'; }

"/"					{ this.yylval = yytext();
					  return '/'; }

"*"					{ this.yylval =yytext();
					  return '*'; }

"%"					{ this.yylval = yytext();
					  return '%'; }
					  
"<"					{ this.yylval = yytext();
					  return '<'; }

">"					{ this.yylval = yytext();
					  return '>'; }

"="					{ this.yylval = yytext();
					  return '='; }

"<="				{ this.yylval = yytext();
					  return Parser.MENORIGUAL; }

">="				{ this.yylval = yytext();
					  return Parser.MAYORIGUAL; }
					  
"=="				{ this.yylval = yytext();
					  return Parser.EQUIVALENTE; }

"!="				{ this.yylval = yytext();
					  return Parser.DISTINTO; }

"!" 				{ this.yylval = yytext();
					  return '!'; }
							
"||" 				{ this.yylval = yytext();
					  return Parser.OR; }
							
"&&" 				{ this.yylval = yytext();
					  return Parser.AND; } 
					  				  				  			  				  					  	  				  					  
//CONSTANTES

{ConstanteEntera}	{ this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;  }
         
{ConstanteDecimal}	{ this.yylval = new Double(yytext());
         			  return Parser.REAL_CONSTANT;  }

{Ident}				{ this.yylval = new String(yytext());
         			  return Parser.ID; }

'\\n'				{ this.yylval = new Character('\n');
         			  return Parser.CHAR_CONSTANT;}
         			  
'\\t'				{ this.yylval = new Character('\t');
         			  return Parser.CHAR_CONSTANT;}
         			  
{Ascii}				{ this.yylval = (char) Integer.parseInt(yytext().replace("'","").replace("\\",""));
         			  return Parser.CHAR_CONSTANT;}
         			           			  
{Char}				{ this.yylval = new Character(yytext().charAt(1));
         			  return Parser.CHAR_CONSTANT;}

//DELIMITADORES
"("					{ this.yylval = yytext();
					  return '('; }
					  
")"					{ this.yylval = yytext();
					  return ')'; }					  

"{"					{ this.yylval =  yytext();
         			  return '{'; }
         			  		
"}"					{ this.yylval =  yytext();
					  return '}'; }
					  
"["					{ this.yylval =  yytext();
					  return '['; }
					  
"]"					{ this.yylval =  yytext();
					  return ']'; }   
         			  
";"					{ this.yylval = yytext();
					  return ';'; }
					  
","					{ this.yylval = yytext();
					  return ','; }	         			           			         			         			           			  			       			           			     
         			  
":"					{ this.yylval =  yytext();
         			  return ':'; }
         			  
"."					{ this.yylval =  yytext();
         			  return '.'; } 			  
         			  		       		
	  
// * Cualquier otro carácter
.			{ System.err.println ("Lexical error at line " 	+ this.getLine() + " and column "+getColumn()+":\n\tUnknow character \'"+ yycharat(0)+"\'."); }		
