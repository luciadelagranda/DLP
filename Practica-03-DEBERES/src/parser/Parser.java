//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package parser;



//#line 2 "../../src/parser/parser.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import scanner.Scanner;
import java.io.Reader;
//#line 23 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short INT_CONSTANT=257;
public final static short REAL_CONSTANT=258;
public final static short ID=259;
public final static short CHAR_CONSTANT=260;
public final static short INPUT=261;
public final static short PRINT=262;
public final static short DEF=263;
public final static short WHILE=264;
public final static short IF=265;
public final static short ELSE=266;
public final static short INT=267;
public final static short DOUBLE=268;
public final static short CHAR=269;
public final static short STRUCT=270;
public final static short RETURN=271;
public final static short VOID=272;
public final static short MAIN=273;
public final static short MENORIGUAL=274;
public final static short MAYORIGUAL=275;
public final static short EQUIVALENTE=276;
public final static short DISTINTO=277;
public final static short OR=278;
public final static short AND=279;
public final static short SIMPLE=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    5,    2,    8,    8,   11,
   11,   11,   11,   11,   11,   11,   11,   18,   19,   16,
   16,   16,   16,   17,   17,   10,   10,    6,    6,   21,
   21,   22,    9,    9,    4,    4,   24,   24,   23,   23,
   23,   23,    7,    7,   25,   26,   26,   20,   20,   20,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   27,   13,   14,   15,
};
final static short yylen[] = {                            2,
    2,    0,    2,    1,    1,    8,    7,    4,    2,    3,
    1,    1,    1,    1,    1,    2,    1,    4,    4,    6,
    8,    8,   10,    4,    6,    1,    2,    1,    0,    1,
    3,    3,    1,    2,    4,    1,    1,    3,    1,    1,
    1,    4,    1,    1,    7,    1,    2,    1,    3,    0,
    3,    3,    3,    3,    3,    3,    3,    4,    2,    2,
    3,    3,    3,    3,    3,    3,    3,    3,    1,    1,
    1,    1,    1,    1,    4,    6,    3,    3,
};
final static short yydefred[] = {                         2,
    0,    0,   37,    0,    1,    3,    4,    5,    0,   36,
    0,    0,    0,    0,    0,    0,   39,   40,   41,    0,
    0,    0,   38,    0,    0,    0,   30,    0,    0,    0,
   35,    0,    0,    0,    0,   46,    0,    0,   32,    0,
   31,    0,    0,   47,   42,   44,    0,   43,    0,    7,
   45,    6,    9,   33,    0,   70,   69,    0,   71,    0,
    0,    0,    0,    0,    0,    0,    0,   34,    0,   26,
    0,   11,   12,   13,   14,   15,    0,   17,   73,    0,
    0,    0,   74,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    8,   27,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   16,    0,   78,   77,    0,    0,    0,   10,   57,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   18,    0,    0,    0,
    0,   75,   19,   58,    0,    0,    0,   76,    0,    0,
   20,    0,    0,    0,   22,   21,    0,   23,
};
final static short yydgoto[] = {                          1,
    2,    5,    6,    7,    8,   25,   47,   50,   55,   69,
   70,   71,   72,   73,   74,   75,   76,   83,   78,   85,
   26,   27,   22,    9,   10,   37,   79,
};
final static short yysindex[] = {                         0,
    0, -244,    0, -257,    0,    0,    0,    0,   -6,    0,
  -28,  -22,  -48, -239, -218,    3,    0,    0,    0,  -78,
 -177,   22,    0,   32,   51,   57,    0,   49, -144,   23,
    0,   45,   68, -218, -133,    0, -122,   45,    0,  -74,
    0,   17,   82,    0,    0,    0,   17,    0,  -98,    0,
    0,    0,    0,    0,  916,    0,    0,  103,    0,   84,
   84,   84,   84,   84,   84,   84,  787,    0,  536,    0,
  110,    0,    0,    0,    0,    0,   85,    0,    0,   84,
  103,  121,    0,  457,  -31,  145,  171,  214,  336,  -45,
  310,  118,    0,    0,   84,   84,   84,   84,   84,   84,
   84,   84,   84,   84,   84,   84,   84,   84,   84,   84,
    0,   -2,    0,    0,   84,   28,  210,    0,    0,   84,
  241,  241,  241,  241,  468,  468,  366,  241,  241,  336,
  336,  -45,  -45,  -45,   69,  392,    0,  457,  931,  931,
 -104,    0,    0,    0,  590,  677,  730,    0, -101,  931,
    0,  778,  835,  931,    0,    0,  854,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  128,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    3,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   88,    0,    0,
   20,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  416,    0,    0,   47,
  -37,    0,    0,  -30,    0,    0,    0,    0,  477,   16,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  538,  560,  566,  588,  165,  186,    0,  614,  644,  502,
  513,   25,   52,   61,  -11,    0,    0,   -4,    0,    0,
  871,    0,    0,    0,    0,    0,    0,    0,  900,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  147,    0,    0,    0,  126,    0,  -12,
  163, 1143,    0,    0,    0,    0,    0,  858,    0,   94,
    0,  141,   33,    0,    0,    0,    0,
};
final static int YYTABLESIZE=1263;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         72,
  109,   11,   43,   72,   72,   72,   72,   72,   72,   72,
   48,   15,  115,   48,    3,   12,   21,   16,    4,   23,
   72,   72,   72,   72,   72,   56,   53,  114,   48,   56,
   56,   56,   56,   56,   56,   56,   49,   14,  137,   49,
   24,  115,   21,   28,   29,  110,   56,   56,   56,   56,
   56,   13,   59,   72,   49,   72,   59,   59,   59,   59,
   59,   53,   59,   50,   39,   53,   53,   53,   53,   53,
   45,   53,   48,   59,   59,   59,   59,   59,   50,   30,
   31,   56,   53,   53,   53,   53,   53,   50,   54,   32,
   50,   33,   54,   54,   54,   54,   54,   55,   54,   92,
   34,   55,   55,   55,   55,   55,   35,   55,   59,   54,
   54,   54,   54,   54,    3,   38,   66,   53,   55,   55,
   55,   55,   55,   67,   72,   40,  145,  146,   65,   72,
   72,   37,   72,   72,   72,   21,    3,  153,   42,   49,
   51,  157,   80,  111,   54,   37,  108,   72,   72,   72,
  139,  106,  104,   55,  105,  109,  107,  108,  120,  110,
    3,  147,  106,  104,  152,  105,  109,  107,   29,  103,
  101,  102,   52,  112,   41,   36,    0,    0,   72,  113,
  103,  108,  102,   44,    0,    0,  106,  104,    0,  105,
  109,  107,   17,   18,   19,   54,    0,   46,    0,    0,
  110,   68,  116,    0,  103,   68,  102,  108,   68,    0,
    0,  110,  106,  104,    0,  105,  109,  107,   17,   18,
   19,   20,   68,   68,    0,   68,   67,    0,  117,   67,
  103,   94,  102,    0,    0,  110,   72,   72,   72,   72,
   72,   72,   66,   67,   67,    0,   67,    0,    0,   67,
  108,    0,    0,    0,   65,  106,  104,   68,  105,  109,
  107,  110,   56,   56,   56,   56,   56,   56,    0,    0,
    0,    0,  118,  103,    0,  102,    0,  108,   67,  141,
    0,    0,  106,  104,    0,  105,  109,  107,    0,   59,
   59,   59,   59,   59,   59,    0,    0,    0,   53,   53,
   53,   53,   53,   53,  110,    0,    0,   94,   94,  151,
    0,   17,   18,   19,  155,   94,    0,    0,    0,   94,
    0,    0,    0,    0,    0,   54,   54,   54,   54,   54,
   54,  110,  140,    0,   55,   55,   55,   55,   55,   55,
   56,   57,   81,   59,    0,    0,  108,    0,    0,    0,
  119,  106,  104,    0,  105,  109,  107,    0,    0,    0,
    0,   72,   72,   72,   72,   72,   72,    0,    0,  103,
    0,  102,  108,    0,    0,    0,    0,  106,    0,    0,
    0,  109,  107,   95,   96,   97,   98,   99,  100,    0,
    0,    0,    0,    0,   95,   96,   97,   98,   99,  100,
  110,    0,  108,    0,    0,    0,    0,  106,  104,    0,
  105,  109,  107,    0,    0,    0,    0,    0,   95,   96,
   97,   98,   99,  100,  143,  103,  110,  102,  108,    0,
    0,    0,    0,  106,  104,    0,  105,  109,  107,    0,
    0,    0,   68,   68,   95,   96,   97,   98,   99,  100,
    0,  103,   74,  102,    0,    0,  110,   74,   74,    0,
   74,   74,   74,   67,   67,    0,   56,   57,   81,   59,
   60,   61,    0,   62,   63,   74,   74,   74,    0,    0,
   64,    0,  110,    0,  144,    0,    0,   95,   96,   97,
   98,   99,  100,  108,    0,    0,    0,    0,  106,  104,
    0,  105,  109,  107,  108,    0,   74,    0,    0,  106,
  104,    0,  105,  109,  107,    0,  103,   60,  102,   60,
   60,   60,    0,    0,    0,    0,    0,  103,    0,  102,
    0,    0,    0,    0,   60,   60,   60,   60,   60,    0,
    0,    0,   51,    0,   51,   51,   51,  110,    0,    0,
    0,    0,    0,   52,    0,   52,   52,   52,  110,   51,
   51,   51,   51,   51,    0,    0,    0,    0,   66,   60,
   52,   52,   52,   52,   52,   67,    0,    0,   65,    0,
   65,   65,    0,   95,   96,   97,   98,   99,  100,    0,
    0,    0,    0,    0,   51,   65,   65,   65,   65,   65,
   64,    0,    0,   64,    0,   52,   66,    0,    0,   66,
    0,    0,    0,    0,    0,    0,    0,   64,   64,   64,
   64,   64,   66,   66,   66,   66,   66,   66,   63,   67,
   65,   63,    0,    0,   65,    0,    0,    0,    0,   95,
   96,   97,   98,   99,  100,   63,   63,   63,   63,   63,
    0,    0,   64,    0,   61,    0,    0,   61,   66,    0,
   93,    0,    0,    0,    0,   95,   96,   97,   98,   99,
  100,   61,   61,   61,   61,   61,    0,    0,    0,    0,
   63,    0,    0,    0,   62,    0,    0,   62,    0,   74,
   74,   74,   74,   74,   74,    0,    0,    0,    0,    0,
    0,   62,   62,   62,   62,   62,   61,    0,    0,   66,
    0,    0,    0,    0,  148,    0,   67,    0,    0,    0,
    0,   65,    0,    0,    0,    0,    0,    0,    0,    0,
   95,   96,   97,   98,   99,  100,   62,    0,    0,    0,
    0,   95,   96,   97,   98,    0,    0,    0,    0,    0,
   60,   60,   60,   60,   60,   60,    0,    0,    0,    0,
    0,    0,   66,    0,    0,    0,    0,    0,    0,   67,
    0,    0,    0,    0,   65,   51,   51,   51,   51,   51,
   51,    0,    0,    0,    0,    0,   52,   52,   52,   52,
   52,   52,   56,   57,   81,   59,   60,   61,    0,   62,
   63,  149,    0,    0,    0,    0,   64,    0,    0,    0,
   66,   65,   65,   65,   65,   65,   65,   67,    0,   66,
    0,    0,   65,    0,    0,    0,   67,    0,    0,    0,
    0,   65,    0,   64,   64,   64,   64,   64,   64,   66,
   66,   66,   66,   66,   66,    0,   56,   57,   81,   59,
   60,   61,  150,   62,   63,    0,    0,    0,    0,    0,
   64,   63,   63,   63,   63,   63,   63,   66,    0,    0,
    0,    0,    0,    0,   67,    0,    0,   21,    0,   65,
    0,    0,    0,    0,    0,    0,   66,   61,   61,   61,
   61,   61,   61,   67,    0,    0,    0,    0,   65,    0,
  154,    0,    0,   24,    0,    0,    0,    0,    0,    0,
   24,    0,   77,    0,    0,   24,    0,   62,   62,   62,
   62,   62,   62,    0,    0,    0,   77,    0,    0,    0,
    0,    0,   25,   56,   57,   81,   59,   60,   61,   25,
   62,   63,    0,    0,   25,    0,    0,   64,   66,    0,
    0,    0,    0,    0,    0,   67,    0,    0,    0,  156,
   65,    0,    0,   66,    0,    0,    0,    0,    0,    0,
   67,    0,    0,    0,   77,   65,    0,    0,  158,    0,
    0,    0,    0,    0,    0,    0,   56,   57,   81,   59,
   60,   61,    0,   62,   63,   24,   77,   77,    0,    0,
   64,    0,   77,   77,   77,    0,    0,   77,    0,   77,
   77,   77,    0,    0,   77,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   25,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   56,   57,   81,   59,   60,   61,
    0,   62,   63,   56,   57,   81,   59,    0,   64,    0,
    0,    0,    0,   17,   18,   19,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   56,   57,   81,   59,   60,   61,    0,   62,   63,
    0,    0,    0,    0,    0,   64,    0,    0,    0,    0,
   56,   57,   81,   59,   60,   61,    0,   62,   63,    0,
    0,    0,    0,    0,   64,    0,    0,   24,   24,   24,
   24,   24,   24,    0,   24,   24,    0,    0,    0,    0,
    0,   24,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   25,   25,   25,   25,
   25,   25,    0,   25,   25,    0,    0,    0,    0,    0,
   25,    0,   56,   57,   58,   59,   60,   61,    0,   62,
   63,    0,    0,    0,    0,    0,   64,   56,   57,   81,
   59,   60,   61,    0,   62,   63,    0,    0,    0,    0,
    0,   64,   82,   84,   86,   87,   88,   89,   90,   91,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   84,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  121,  122,  123,
  124,  125,  126,  127,  128,  129,  130,  131,  132,  133,
  134,  135,  136,    0,    0,    0,    0,  138,    0,    0,
    0,    0,  142,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   46,  259,  125,   41,   42,   43,   44,   45,   46,   47,
   41,   40,   44,   44,  259,  273,   91,   40,  263,  259,
   58,   59,   60,   61,   62,   37,  125,   59,   59,   41,
   42,   43,   44,   45,   46,   47,   41,   44,   41,   44,
  259,   44,   91,   41,  123,   91,   58,   59,   60,   61,
   62,   58,   37,   91,   59,   93,   41,   42,   43,   44,
   45,   37,   47,   44,   32,   41,   42,   43,   44,   45,
   38,   47,   40,   58,   59,   60,   61,   62,   59,  257,
   59,   93,   58,   59,   60,   61,   62,   41,   37,   58,
   44,   41,   41,   42,   43,   44,   45,   37,   47,   67,
   44,   41,   42,   43,   44,   45,   58,   47,   93,   58,
   59,   60,   61,   62,  259,   93,   33,   93,   58,   59,
   60,   61,   62,   40,   37,   58,  139,  140,   45,   42,
   43,   44,   45,   46,   47,   91,  259,  150,  272,  123,
   59,  154,   40,   59,   93,   58,   37,   60,   61,   62,
  123,   42,   43,   93,   45,   46,   47,   37,   41,   91,
  259,  266,   42,   43,  266,   45,   46,   47,   41,   60,
   61,   62,   47,   80,   34,   29,   -1,   -1,   91,   59,
   60,   37,   62,   37,   -1,   -1,   42,   43,   -1,   45,
   46,   47,  267,  268,  269,   49,   -1,  272,   -1,   -1,
   91,   55,   58,   -1,   60,   41,   62,   37,   44,   -1,
   -1,   91,   42,   43,   -1,   45,   46,   47,  267,  268,
  269,  270,   58,   59,   -1,   61,   41,   -1,   58,   44,
   60,   69,   62,   -1,   -1,   91,  274,  275,  276,  277,
  278,  279,   33,   58,   59,   -1,   61,   -1,   -1,   40,
   37,   -1,   -1,   -1,   45,   42,   43,   93,   45,   46,
   47,   91,  274,  275,  276,  277,  278,  279,   -1,   -1,
   -1,   -1,   59,   60,   -1,   62,   -1,   37,   93,  117,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,  274,
  275,  276,  277,  278,  279,   -1,   -1,   -1,  274,  275,
  276,  277,  278,  279,   91,   -1,   -1,  145,  146,  147,
   -1,  267,  268,  269,  152,  153,   -1,   -1,   -1,  157,
   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,
  279,   91,  123,   -1,  274,  275,  276,  277,  278,  279,
  257,  258,  259,  260,   -1,   -1,   37,   -1,   -1,   -1,
   41,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,  274,  275,  276,  277,  278,  279,   -1,   -1,   60,
   -1,   62,   37,   -1,   -1,   -1,   -1,   42,   -1,   -1,
   -1,   46,   47,  274,  275,  276,  277,  278,  279,   -1,
   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
   91,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,  274,  275,
  276,  277,  278,  279,   59,   60,   91,   62,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,  278,  279,  274,  275,  276,  277,  278,  279,
   -1,   60,   37,   62,   -1,   -1,   91,   42,   43,   -1,
   45,   46,   47,  278,  279,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,   60,   61,   62,   -1,   -1,
  271,   -1,   91,   -1,   93,   -1,   -1,  274,  275,  276,
  277,  278,  279,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   37,   -1,   91,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   60,   41,   62,   43,
   44,   45,   -1,   -1,   -1,   -1,   -1,   60,   -1,   62,
   -1,   -1,   -1,   -1,   58,   59,   60,   61,   62,   -1,
   -1,   -1,   41,   -1,   43,   44,   45,   91,   -1,   -1,
   -1,   -1,   -1,   41,   -1,   43,   44,   45,   91,   58,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   33,   93,
   58,   59,   60,   61,   62,   40,   -1,   -1,   41,   -1,
   45,   44,   -1,  274,  275,  276,  277,  278,  279,   -1,
   -1,   -1,   -1,   -1,   93,   58,   59,   60,   61,   62,
   41,   -1,   -1,   44,   -1,   93,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,   60,
   61,   62,   33,   58,   59,   60,   61,   62,   41,   40,
   93,   44,   -1,   -1,   45,   -1,   -1,   -1,   -1,  274,
  275,  276,  277,  278,  279,   58,   59,   60,   61,   62,
   -1,   -1,   93,   -1,   41,   -1,   -1,   44,   93,   -1,
  125,   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,
  279,   58,   59,   60,   61,   62,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,  274,
  275,  276,  277,  278,  279,   -1,   -1,   -1,   -1,   -1,
   -1,   58,   59,   60,   61,   62,   93,   -1,   -1,   33,
   -1,   -1,   -1,   -1,  125,   -1,   40,   -1,   -1,   -1,
   -1,   45,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,   93,   -1,   -1,   -1,
   -1,  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,   -1,   -1,   -1,   -1,
   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   45,  274,  275,  276,  277,  278,
  279,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,
  278,  279,  257,  258,  259,  260,  261,  262,   -1,  264,
  265,  125,   -1,   -1,   -1,   -1,  271,   -1,   -1,   -1,
   33,  274,  275,  276,  277,  278,  279,   40,   -1,   33,
   -1,   -1,   45,   -1,   -1,   -1,   40,   -1,   -1,   -1,
   -1,   45,   -1,  274,  275,  276,  277,  278,  279,  274,
  275,  276,  277,  278,  279,   -1,  257,  258,  259,  260,
  261,  262,  123,  264,  265,   -1,   -1,   -1,   -1,   -1,
  271,  274,  275,  276,  277,  278,  279,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   40,   -1,   -1,   91,   -1,   45,
   -1,   -1,   -1,   -1,   -1,   -1,   33,  274,  275,  276,
  277,  278,  279,   40,   -1,   -1,   -1,   -1,   45,   -1,
  123,   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,
   40,   -1,   55,   -1,   -1,   45,   -1,  274,  275,  276,
  277,  278,  279,   -1,   -1,   -1,   69,   -1,   -1,   -1,
   -1,   -1,   33,  257,  258,  259,  260,  261,  262,   40,
  264,  265,   -1,   -1,   45,   -1,   -1,  271,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,  125,
   45,   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,
   40,   -1,   -1,   -1,  117,   45,   -1,   -1,  125,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,  125,  139,  140,   -1,   -1,
  271,   -1,  145,  146,  147,   -1,   -1,  150,   -1,  152,
  153,  154,   -1,   -1,  157,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
   -1,  264,  265,  257,  258,  259,  260,   -1,  271,   -1,
   -1,   -1,   -1,  267,  268,  269,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   -1,  264,  265,
   -1,   -1,   -1,   -1,   -1,  271,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,  264,  265,   -1,
   -1,   -1,   -1,   -1,  271,   -1,   -1,  257,  258,  259,
  260,  261,  262,   -1,  264,  265,   -1,   -1,   -1,   -1,
   -1,  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,   -1,   -1,   -1,   -1,   -1,
  271,   -1,  257,  258,  259,  260,  261,  262,   -1,  264,
  265,   -1,   -1,   -1,   -1,   -1,  271,  257,  258,  259,
  260,  261,  262,   -1,  264,  265,   -1,   -1,   -1,   -1,
   -1,  271,   60,   61,   62,   63,   64,   65,   66,   67,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   80,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   95,   96,   97,
   98,   99,  100,  101,  102,  103,  104,  105,  106,  107,
  108,  109,  110,   -1,   -1,   -1,   -1,  115,   -1,   -1,
   -1,   -1,  120,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=280;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"INT_CONSTANT","REAL_CONSTANT",
"ID","CHAR_CONSTANT","INPUT","PRINT","DEF","WHILE","IF","ELSE","INT","DOUBLE",
"CHAR","STRUCT","RETURN","VOID","MAIN","MENORIGUAL","MAYORIGUAL","EQUIVALENTE",
"DISTINTO","OR","AND","SIMPLE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones main",
"definiciones :",
"definiciones : definiciones definicion",
"definicion : variable",
"definicion : funcion",
"funcion : DEF ID '(' parametros ')' ':' tipoReturn body",
"main : DEF MAIN '(' ')' ':' VOID body",
"body : '{' variables sentencias '}'",
"body : '{' '}'",
"sentencia : RETURN expression ';'",
"sentencia : while",
"sentencia : print",
"sentencia : input",
"sentencia : if",
"sentencia : ifSimple",
"sentencia : invocation ';'",
"sentencia : assignment",
"invocation : ID '(' expressiones ')'",
"assignment : expression '=' expression ';'",
"if : IF expression ':' sentencia ELSE sentencia",
"if : IF expression ':' sentencia ELSE '{' sentencias '}'",
"if : IF expression ':' '{' sentencias '}' ELSE sentencia",
"if : IF expression ':' '{' sentencias '}' ELSE '{' sentencias '}'",
"ifSimple : IF expression ':' sentencia",
"ifSimple : IF expression ':' '{' sentencias '}'",
"sentencias : sentencia",
"sentencias : sentencias sentencia",
"parametros : parametrosAUX",
"parametros :",
"parametrosAUX : parametro",
"parametrosAUX : parametrosAUX ',' parametro",
"parametro : ID ':' tipo",
"variables : variable",
"variables : variables variable",
"variable : ident ':' tipo ';'",
"variable : struct",
"ident : ID",
"ident : ident ',' ID",
"tipo : INT",
"tipo : DOUBLE",
"tipo : CHAR",
"tipo : '[' INT_CONSTANT ']' tipo",
"tipoReturn : tipo",
"tipoReturn : VOID",
"struct : ident ':' STRUCT '{' bodyStruct '}' ';'",
"bodyStruct : variable",
"bodyStruct : bodyStruct variable",
"expressiones : expression",
"expressiones : expressiones ',' expression",
"expressiones :",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : expression '.' expression",
"expression : '(' expression ')'",
"expression : expression '[' expression ']'",
"expression : '!' expression",
"expression : '-' expression",
"expression : expression '>' expression",
"expression : expression '<' expression",
"expression : expression DISTINTO expression",
"expression : expression MAYORIGUAL expression",
"expression : expression MENORIGUAL expression",
"expression : expression EQUIVALENTE expression",
"expression : expression AND expression",
"expression : expression OR expression",
"expression : REAL_CONSTANT",
"expression : INT_CONSTANT",
"expression : CHAR_CONSTANT",
"expression : ID",
"expression : cast",
"expression : invocation",
"cast : '(' tipo ')' expression",
"while : WHILE expression ':' '{' sentencias '}'",
"print : PRINT expressiones ';'",
"input : INPUT expression ';'",
};

//#line 200 "../../src/parser/parser.y"

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
//#line 611 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 45:
//#line 156 "../../src/parser/parser.y"
{}
break;
//#line 764 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
