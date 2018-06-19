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



//#line 3 "../../src/parser/parser.y"
/* * Declaraciones de c�digo Java*/
/* * Se sit�an al comienzo del archivo generado*/
/* * El package lo a�ade yacc si utilizamos la opci�n -Jpackage*/

import ast.*;
import ast.type.*;
import scanner.Scanner;
import java.util.*;

//#line 27 "Parser.java"




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
    0,    1,    1,    3,    3,    5,    2,    8,    8,    8,
    8,   10,   10,   11,   11,   11,   11,   11,   11,   11,
   11,   18,   20,   20,   19,   16,   16,   16,   16,   17,
   17,    6,    6,   22,   22,   23,    9,    9,    4,   25,
   25,   24,   24,   24,   26,   26,   26,    7,    7,   27,
   27,   28,   21,   21,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   29,   13,
   14,   15,
};
final static short yylen[] = {                            2,
    2,    0,    2,    1,    1,    8,    7,    4,    2,    3,
    3,    1,    2,    3,    1,    1,    1,    1,    1,    2,
    1,    4,    1,    0,    4,    6,    8,    8,   10,    4,
    6,    1,    0,    1,    3,    3,    1,    2,    4,    1,
    3,    1,    1,    1,    1,    4,    4,    1,    1,    1,
    2,    4,    1,    3,    1,    3,    3,    3,    3,    3,
    3,    3,    4,    2,    2,    3,    3,    3,    3,    3,
    3,    3,    3,    1,    1,    1,    1,    1,    4,    6,
    3,    3,
};
final static short yydefred[] = {                         2,
    0,    0,   40,    0,    1,    3,    4,    5,    0,    0,
    0,    0,    0,    0,    0,   42,   43,   44,    0,    0,
   45,    0,   41,    0,    0,    0,   34,    0,    0,    0,
   39,    0,    0,    0,    0,    0,    0,   50,    0,   36,
    0,   35,    0,    0,   47,   51,   46,   49,    0,   48,
    0,    7,    0,    6,   75,   74,    0,   76,    0,    0,
    0,    0,    0,    0,    0,    0,    9,   37,    0,    0,
   12,    0,   15,   16,   17,   18,   19,    0,   21,   77,
   52,    0,    0,    0,   78,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   11,   38,    0,   10,   13,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   20,    0,    0,   82,    0,
   81,    0,    0,   14,   62,    0,    8,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   61,    0,   22,    0,    0,    0,    0,   79,   25,
   63,    0,    0,    0,   80,    0,    0,   26,    0,    0,
    0,   28,   27,    0,   29,
};
final static short yydgoto[] = {                          1,
    2,    5,    6,    7,    8,   25,   49,   52,   69,   70,
   71,   72,   73,   74,   75,   76,   77,   85,   79,  117,
   86,   26,   27,   21,    9,   22,   37,   38,   80,
};
final static short yysindex[] = {                         0,
    0, -245,    0, -244,    0,    0,    0,    0,   -6,  -24,
  -23,  -80, -215, -213,   30,    0,    0,    0,  -35, -167,
    0,   32,    0,   34,   59,   57,    0,   44, -144,   27,
    0, -188,   68, -213, -135,   -3, -124,    0,  -80,    0,
 -106,    0,   15,  -80,    0,    0,    0,    0,   15,    0,
  390,    0,   81,    0,    0,    0,  101,    0,  384,  384,
  384,  384,  384,  384,  384,  888,    0,    0,  492,  574,
    0,   87,    0,    0,    0,    0,    0,   83,    0,    0,
    0,  384,  101,  413,    0,  -17,  -16,  113,  137,  163,
  130,  -27,  170,  103,    0,    0,  600,    0,    0,  384,
  384,  384,  384,  384,  384,  384,  384,  384,  384,  384,
  384,  384,  384, -113,  384,    0,  110,  109,    0,  384,
    0,   41,  625,    0,    0,  384,    0,  278,  278,  278,
  278,  420,  420,  308,  278,  278,  130,  130,  -27,  -27,
  -27,    0,  334,    0,  413,  870,  870, -109,    0,    0,
    0,  640,  665,  726,    0, -101,  870,    0,  758,  689,
  870,    0,    0,  782,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   59,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  127,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   61,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  360,    0,    0,
    0,  128,  -37,   84,    0,    0,    0,    0,    0,    0,
  212,  -11,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  129,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  218,  468,  498,
  504,  190,  616,    0,  510,  534,  440,  462,   16,   25,
   52,    0,    0,    0,   95,    0,    0,  807,    0,    0,
    0,    0,    0,    0,    0,  830,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  -49,    0,    0,    0,  132,    0,  -30,
  842, 1099,    0,    0,    0,    0,    0,  957,    0,    0,
  -47,    0,  151,  -29,   36,    1,    0,  149,    0,
};
final static int YYTABLESIZE=1225;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         55,
   45,   68,   40,   55,   55,   55,   55,   55,   55,   55,
   20,   50,   87,    3,   10,   14,   15,    4,  114,   96,
   55,   55,   55,   55,   55,   64,  120,  120,   11,   64,
   64,   64,   64,   64,  118,   64,   94,   13,   97,   47,
   13,  119,  121,   23,   53,   24,   64,   64,   64,   64,
   64,   12,   58,   55,   44,   55,   58,   58,   58,   58,
   58,   59,   58,  115,   36,   59,   59,   59,   59,   59,
   28,   59,   36,   58,   58,   58,   58,   58,   16,   17,
   18,   64,   59,   59,   59,   59,   59,   29,   60,   30,
   31,   32,   60,   60,   60,   60,   60,   55,   60,   33,
   34,   35,   55,   55,   40,   55,   55,   55,   58,   60,
   60,   60,   60,   60,    3,  152,  153,   59,   40,   39,
   55,   55,   55,  113,   53,   41,  160,   53,  111,  109,
  164,  110,  114,  112,    3,   54,   43,   51,   54,   81,
   82,  116,   53,  126,   60,  142,  108,  106,  107,  113,
  144,   55,  120,   54,  111,  109,  154,  110,  114,  112,
   16,   17,   18,  146,  159,   48,  113,   32,   24,   23,
  122,  111,  108,  113,  107,  114,  112,  115,  111,  109,
   54,  110,  114,  112,   42,   46,   16,   17,   18,   19,
    0,    0,    0,    0,  123,    0,  108,    0,  107,  113,
    0,    0,    0,  115,  111,  109,  113,  110,  114,  112,
  125,  111,  109,    0,  110,  114,  112,    0,    0,    0,
  115,  124,  108,    0,  107,    0,    0,  115,    0,  108,
   73,  107,    0,   73,    0,    0,   55,   55,   55,   55,
   55,   55,    0,    0,    0,    0,    0,   73,   73,    0,
   73,    0,   65,  115,   65,   65,   65,    0,   70,    0,
  115,   70,   64,   64,   64,   64,   64,   64,    0,   65,
   65,   65,   65,   65,    0,   70,   70,   70,   70,   70,
    0,    0,   73,    0,    0,    0,    0,    0,    0,   58,
   58,   58,   58,   58,   58,    0,    0,    0,   59,   59,
   59,   59,   59,   59,   65,    0,    0,    0,    0,    0,
   70,    0,    0,    0,  113,    0,    0,    0,    0,  111,
  109,    0,  110,  114,  112,   60,   60,   60,   60,   60,
   60,    0,    0,    0,   55,   55,   55,   55,   55,   55,
    0,    0,    0,    0,  113,    0,    0,    0,    0,  111,
  109,    0,  110,  114,  112,    0,    0,    0,    0,    0,
  100,  101,  102,  103,  104,  105,  150,  108,  115,  107,
  113,    0,    0,    0,    0,  111,  109,    0,  110,  114,
  112,    0,    0,    0,    0,    0,  100,  101,  102,  103,
  104,  105,    0,  108,    0,  107,   78,    0,  115,    0,
    0,   78,   78,    0,   78,   78,   78,    0,    0,    0,
  100,  101,  102,  103,  104,  105,   65,    0,    0,   78,
   78,   78,   65,   66,  115,    0,  151,    0,   64,   66,
    0,    0,    0,    0,   64,    0,  100,  101,  102,  103,
  104,  105,    0,  100,  101,  102,  103,  104,  105,  113,
   78,    0,    0,    0,  111,  109,  113,  110,  114,  112,
    0,  111,  109,    0,  110,  114,  112,   73,   73,    0,
    0,    0,  108,    0,  107,    0,    0,    0,    0,  108,
   56,  107,   56,   56,   56,   65,   65,   65,   65,   65,
   65,   70,   70,   70,   70,   70,   70,   56,   56,   56,
   56,   56,   57,  115,   57,   57,   57,    0,   69,    0,
  115,   69,    0,    0,   67,    0,    0,    0,    0,   57,
   57,   57,   57,   57,   65,   69,   69,   69,   69,   69,
    0,   66,   56,    0,    0,    0,   64,    0,   71,    0,
    0,   71,    0,    0,   68,    0,    0,   68,    0,    0,
   66,    0,    0,   66,   57,   71,   71,   71,   71,   71,
   69,   68,   68,   68,   68,   68,    0,   66,   66,   66,
   66,   66,    0,    0,   67,    0,    0,   67,    0,    0,
    0,  100,  101,  102,  103,  104,  105,    0,    0,    0,
   71,   67,   67,   67,   67,   67,   68,    0,    0,    0,
    0,    0,   66,    0,    0,    0,   65,  100,  101,  102,
  103,  104,  105,   66,    0,    0,   95,    0,   64,    0,
    0,    0,    0,    0,    0,    0,   67,    0,    0,    0,
    0,    0,   65,   78,   78,   78,   78,   78,   78,   66,
   55,   56,   83,   58,   64,    0,   55,   56,   57,   58,
   59,   60,    0,   61,   62,    0,   72,   65,    0,   72,
   63,    0,    0,    0,   66,    0,    0,    0,    0,   64,
    0,    0,   65,   72,   72,    0,   72,    0,    0,   66,
    0,    0,    0,    0,   64,    0,  100,  101,  102,  103,
  104,  105,    0,  100,  101,  102,  103,   65,   98,    0,
    0,    0,    0,    0,   66,    0,    0,    0,   72,   64,
    0,    0,    0,   56,   56,   56,   56,   56,   56,    0,
    0,   65,    0,    0,  127,    0,    0,    0,   66,    0,
    0,    0,    0,   64,    0,   57,   57,   57,   57,   57,
   57,   69,   69,   69,   69,   69,   69,  147,   55,   56,
   57,   58,   59,   60,    0,   61,   62,    0,   65,    0,
    0,    0,   63,    0,  155,   66,    0,    0,    0,    0,
   64,   71,   71,   71,   71,   71,   71,   68,   68,   68,
   68,   68,   68,   66,   66,   66,   66,   66,   66,  156,
   65,    0,    0,    0,    0,    0,    0,   66,    0,    0,
    0,    0,   64,    0,    0,    0,    0,   67,   67,   67,
   67,   67,   67,  163,   65,    0,    0,    0,    0,    0,
    0,   66,    0,    0,    0,    0,   64,    0,    0,    0,
   55,   56,   83,   58,   59,   60,    0,   61,   62,   30,
    0,    0,    0,    0,   63,    0,   30,    0,  157,    0,
    0,   30,    0,    0,    0,    0,   55,   56,   83,   58,
   59,   60,   31,   61,   62,    0,    0,    0,    0,   31,
   63,    0,    0,    0,   31,    0,    0,    0,    0,    0,
  161,   55,   56,   83,   58,   59,   60,    0,   61,   62,
    0,    0,    0,   72,   72,   63,   55,   56,   83,   58,
   59,   60,   65,   61,   62,    0,  165,    0,    0,   66,
   63,   99,    0,    0,   64,    0,    0,    0,    0,    0,
   65,   55,   56,   83,   58,   59,   60,   66,   61,   62,
    0,   30,   64,    0,    0,   63,    0,    0,   99,    0,
    0,    0,    0,    0,    0,   55,   56,   83,   58,   59,
   60,    0,   61,   62,   31,    0,    0,    0,    0,   63,
    0,    0,    0,    0,  148,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   55,   56,   83,   58,   59,   60,    0,   61,
   62,    0,    0,   99,   99,  158,   63,    0,    0,    0,
  162,   99,    0,    0,    0,   99,    0,   78,    0,    0,
    0,    0,    0,    0,   55,   56,   83,   58,   59,   60,
    0,   61,   62,    0,    0,   78,   78,    0,   63,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   55,   56,
   83,   58,   59,   60,    0,   61,   62,    0,    0,    0,
    0,    0,   63,   78,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   30,   30,   30,   30,   30,   30,    0,
   30,   30,    0,    0,    0,    0,    0,   30,    0,   78,
    0,    0,    0,    0,    0,    0,   31,   31,   31,   31,
   31,   31,    0,   31,   31,    0,    0,    0,    0,    0,
   31,    0,   78,   78,    0,    0,    0,    0,   78,   78,
   78,    0,    0,   78,    0,   78,   78,   78,    0,    0,
   78,    0,    0,    0,    0,    0,   55,   56,   83,   58,
   59,   60,    0,   61,   62,    0,    0,    0,    0,    0,
   63,    0,    0,    0,   55,   56,   83,   58,    0,    0,
    0,    0,    0,    0,   16,   17,   18,   84,   84,   88,
   89,   90,   91,   92,   93,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  128,  129,
  130,  131,  132,  133,  134,  135,  136,  137,  138,  139,
  140,  141,    0,  143,    0,    0,    0,    0,  145,    0,
    0,    0,    0,    0,  149,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  125,   51,   32,   41,   42,   43,   44,   45,   46,   47,
   91,   41,   60,  259,  259,   40,   40,  263,   46,   69,
   58,   59,   60,   61,   62,   37,   44,   44,  273,   41,
   42,   43,   44,   45,   82,   47,   66,   44,   69,   39,
   44,   59,   59,  259,   44,  259,   58,   59,   60,   61,
   62,   58,   37,   91,   58,   93,   41,   42,   43,   44,
   45,   37,   47,   91,   29,   41,   42,   43,   44,   45,
   41,   47,   37,   58,   59,   60,   61,   62,  267,  268,
  269,   93,   58,   59,   60,   61,   62,  123,   37,  257,
   59,   58,   41,   42,   43,   44,   45,   37,   47,   41,
   44,   58,   42,   43,   44,   45,   46,   47,   93,   58,
   59,   60,   61,   62,  259,  146,  147,   93,   58,   93,
   60,   61,   62,   37,   41,   58,  157,   44,   42,   43,
  161,   45,   46,   47,  259,   41,  272,  123,   44,   59,
   40,   59,   59,   41,   93,  259,   60,   61,   62,   37,
   41,   91,   44,   59,   42,   43,  266,   45,   46,   47,
  267,  268,  269,  123,  266,  272,   37,   41,   41,   41,
   58,   42,   60,   37,   62,   46,   47,   91,   42,   43,
   49,   45,   46,   47,   34,   37,  267,  268,  269,  270,
   -1,   -1,   -1,   -1,   58,   -1,   60,   -1,   62,   37,
   -1,   -1,   -1,   91,   42,   43,   37,   45,   46,   47,
   41,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   91,   59,   60,   -1,   62,   -1,   -1,   91,   -1,   60,
   41,   62,   -1,   44,   -1,   -1,  274,  275,  276,  277,
  278,  279,   -1,   -1,   -1,   -1,   -1,   58,   59,   -1,
   61,   -1,   41,   91,   43,   44,   45,   -1,   41,   -1,
   91,   44,  274,  275,  276,  277,  278,  279,   -1,   58,
   59,   60,   61,   62,   -1,   58,   59,   60,   61,   62,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,  274,
  275,  276,  277,  278,  279,   -1,   -1,   -1,  274,  275,
  276,  277,  278,  279,   93,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,  274,  275,  276,  277,  278,
  279,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
   -1,   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,   59,   60,   91,   62,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,  277,
  278,  279,   -1,   60,   -1,   62,   37,   -1,   91,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,   33,   -1,   -1,   60,
   61,   62,   33,   40,   91,   -1,   93,   -1,   45,   40,
   -1,   -1,   -1,   -1,   45,   -1,  274,  275,  276,  277,
  278,  279,   -1,  274,  275,  276,  277,  278,  279,   37,
   91,   -1,   -1,   -1,   42,   43,   37,   45,   46,   47,
   -1,   42,   43,   -1,   45,   46,   47,  278,  279,   -1,
   -1,   -1,   60,   -1,   62,   -1,   -1,   -1,   -1,   60,
   41,   62,   43,   44,   45,  274,  275,  276,  277,  278,
  279,  274,  275,  276,  277,  278,  279,   58,   59,   60,
   61,   62,   41,   91,   43,   44,   45,   -1,   41,   -1,
   91,   44,   -1,   -1,  125,   -1,   -1,   -1,   -1,   58,
   59,   60,   61,   62,   33,   58,   59,   60,   61,   62,
   -1,   40,   93,   -1,   -1,   -1,   45,   -1,   41,   -1,
   -1,   44,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,
   41,   -1,   -1,   44,   93,   58,   59,   60,   61,   62,
   93,   58,   59,   60,   61,   62,   -1,   58,   59,   60,
   61,   62,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,
   -1,  274,  275,  276,  277,  278,  279,   -1,   -1,   -1,
   93,   58,   59,   60,   61,   62,   93,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   33,  274,  275,  276,
  277,  278,  279,   40,   -1,   -1,  125,   -1,   45,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,
   -1,   -1,   33,  274,  275,  276,  277,  278,  279,   40,
  257,  258,  259,  260,   45,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,   -1,   41,   33,   -1,   44,
  271,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,
   -1,   -1,   33,   58,   59,   -1,   61,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   45,   -1,  274,  275,  276,  277,
  278,  279,   -1,  274,  275,  276,  277,   33,  125,   -1,
   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   93,   45,
   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,   -1,
   -1,   33,   -1,   -1,  125,   -1,   -1,   -1,   40,   -1,
   -1,   -1,   -1,   45,   -1,  274,  275,  276,  277,  278,
  279,  274,  275,  276,  277,  278,  279,  123,  257,  258,
  259,  260,  261,  262,   -1,  264,  265,   -1,   33,   -1,
   -1,   -1,  271,   -1,  125,   40,   -1,   -1,   -1,   -1,
   45,  274,  275,  276,  277,  278,  279,  274,  275,  276,
  277,  278,  279,  274,  275,  276,  277,  278,  279,  125,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,   -1,   -1,  274,  275,  276,
  277,  278,  279,  125,   33,   -1,   -1,   -1,   -1,   -1,
   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,  264,  265,   33,
   -1,   -1,   -1,   -1,  271,   -1,   40,   -1,  123,   -1,
   -1,   45,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   33,  264,  265,   -1,   -1,   -1,   -1,   40,
  271,   -1,   -1,   -1,   45,   -1,   -1,   -1,   -1,   -1,
  123,  257,  258,  259,  260,  261,  262,   -1,  264,  265,
   -1,   -1,   -1,  278,  279,  271,  257,  258,  259,  260,
  261,  262,   33,  264,  265,   -1,  125,   -1,   -1,   40,
  271,   70,   -1,   -1,   45,   -1,   -1,   -1,   -1,   -1,
   33,  257,  258,  259,  260,  261,  262,   40,  264,  265,
   -1,  125,   45,   -1,   -1,  271,   -1,   -1,   97,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,
  262,   -1,  264,  265,  125,   -1,   -1,   -1,   -1,  271,
   -1,   -1,   -1,   -1,  123,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,  264,
  265,   -1,   -1,  152,  153,  154,  271,   -1,   -1,   -1,
  159,  160,   -1,   -1,   -1,  164,   -1,   51,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
   -1,  264,  265,   -1,   -1,   69,   70,   -1,  271,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   -1,  264,  265,   -1,   -1,   -1,
   -1,   -1,  271,   97,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
  264,  265,   -1,   -1,   -1,   -1,   -1,  271,   -1,  123,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,   -1,   -1,   -1,   -1,   -1,
  271,   -1,  146,  147,   -1,   -1,   -1,   -1,  152,  153,
  154,   -1,   -1,  157,   -1,  159,  160,  161,   -1,   -1,
  164,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,   -1,   -1,   -1,   -1,   -1,
  271,   -1,   -1,   -1,  257,  258,  259,  260,   -1,   -1,
   -1,   -1,   -1,   -1,  267,  268,  269,   59,   60,   61,
   62,   63,   64,   65,   66,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   82,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  100,  101,
  102,  103,  104,  105,  106,  107,  108,  109,  110,  111,
  112,  113,   -1,  115,   -1,   -1,   -1,   -1,  120,   -1,
   -1,   -1,   -1,   -1,  126,
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
"body : '{' sentencias '}'",
"body : '{' variables '}'",
"sentencias : sentencia",
"sentencias : sentencias sentencia",
"sentencia : RETURN expression ';'",
"sentencia : while",
"sentencia : print",
"sentencia : input",
"sentencia : if",
"sentencia : ifSimple",
"sentencia : invocation ';'",
"sentencia : assignment",
"invocation : ID '(' paramsInvocation ')'",
"paramsInvocation : expressiones",
"paramsInvocation :",
"assignment : expression '=' expression ';'",
"if : IF expression ':' sentencia ELSE sentencia",
"if : IF expression ':' sentencia ELSE '{' sentencias '}'",
"if : IF expression ':' '{' sentencias '}' ELSE sentencia",
"if : IF expression ':' '{' sentencias '}' ELSE '{' sentencias '}'",
"ifSimple : IF expression ':' sentencia",
"ifSimple : IF expression ':' '{' sentencias '}'",
"parametros : parametrosAUX",
"parametros :",
"parametrosAUX : parametro",
"parametrosAUX : parametrosAUX ',' parametro",
"parametro : ID ':' tipo",
"variables : variable",
"variables : variables variable",
"variable : ident ':' tipoCompuesto ';'",
"ident : ID",
"ident : ident ',' ID",
"tipo : INT",
"tipo : DOUBLE",
"tipo : CHAR",
"tipoCompuesto : tipo",
"tipoCompuesto : '[' INT_CONSTANT ']' tipoCompuesto",
"tipoCompuesto : STRUCT '{' bodyStruct '}'",
"tipoReturn : tipo",
"tipoReturn : VOID",
"bodyStruct : variablesStruct",
"bodyStruct : bodyStruct variablesStruct",
"variablesStruct : ident ':' tipoCompuesto ';'",
"expressiones : expression",
"expressiones : expressiones ',' expression",
"expression : ID",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : expression '.' ID",
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
"expression : cast",
"expression : invocation",
"cast : '(' tipo ')' expression",
"while : WHILE expression ':' '{' sentencias '}'",
"print : PRINT expressiones ';'",
"input : INPUT expressiones ';'",
};

//#line 250 "../../src/parser/parser.y"

// * C�digo Java
// * Se crea una clase "Parser", lo que aqu� ubiquemos ser�:
//	- Atributos, si son variables
//	- M�todos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador l�xico
private Scanner scanner;
private ASTNode raiz;

// * Llamada al analizador l�xico
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

// * Manejo de Errores Sint�cticos
public void yyerror (String error) {
    System.err.println ("Syntactical error at line " + scanner.getLine() + " and column "+scanner.getColumn()+":\n\t"+error);
}

// * Constructor del Sint�ctico
public Parser(Scanner scanner) {
	this.scanner = scanner;
}

public ASTNode getAST(){
    return raiz;
}
//#line 620 "Parser.java"
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
case 1:
//#line 78 "../../src/parser/parser.y"
{yyval = (List<Definition>)val_peek(1); ((List<Definition>)yyval).add((FunDefinition)val_peek(0));
											 raiz = new Program(scanner.getLine(), scanner.getColumn(),(List<Definition>)yyval);}
break;
case 2:
//#line 82 "../../src/parser/parser.y"
{yyval = new ArrayList<Definition>();}
break;
case 3:
//#line 83 "../../src/parser/parser.y"
{yyval = val_peek(1); ((List<Definition>)yyval).addAll((List<Definition>)val_peek(0));}
break;
case 4:
//#line 86 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 5:
//#line 87 "../../src/parser/parser.y"
{yyval = new ArrayList<Definition>(); ((List<Definition>)yyval).add((Definition)val_peek(0));}
break;
case 6:
//#line 90 "../../src/parser/parser.y"
{FunctionType funcionType = new FunctionType(scanner.getLine(), scanner.getColumn(),(List<VarDefinition>)val_peek(4),(Type)val_peek(1));
														 yyval = new FunDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(6),funcionType, (List<Statement>)val_peek(0));}
break;
case 7:
//#line 94 "../../src/parser/parser.y"
{FunctionType funcionType = new FunctionType(scanner.getLine(), scanner.getColumn(),null,VoidType.VoidTypeInstance(scanner.getLine(), scanner.getColumn()));
											 yyval = new FunDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(5),funcionType,(List<Statement>)val_peek(0));}
break;
case 8:
//#line 97 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).addAll((List<VarDefinition>)val_peek(2)); ((List<Statement>)yyval).addAll((List<Statement>)val_peek(1)); }
break;
case 9:
//#line 98 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); }
break;
case 10:
//#line 99 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).addAll((List<Statement>)val_peek(1)); }
break;
case 11:
//#line 100 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).addAll((List<Statement>)val_peek(1)); }
break;
case 12:
//#line 103 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add((Statement)val_peek(0));}
break;
case 13:
//#line 104 "../../src/parser/parser.y"
{yyval = val_peek(1) ; ((List<Statement>)yyval).add((Statement)val_peek(0));}
break;
case 14:
//#line 107 "../../src/parser/parser.y"
{yyval = new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1) );}
break;
case 15:
//#line 108 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 16:
//#line 109 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 17:
//#line 110 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 18:
//#line 111 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 19:
//#line 112 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 20:
//#line 113 "../../src/parser/parser.y"
{yyval = val_peek(1);}
break;
case 21:
//#line 114 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 22:
//#line 117 "../../src/parser/parser.y"
{yyval = new Invocation(scanner.getLine(), scanner.getColumn(),new Variable(scanner.getLine(), scanner.getColumn(),(String)val_peek(3)), (List<Expression>)val_peek(1));}
break;
case 23:
//#line 119 "../../src/parser/parser.y"
{yyval = new ArrayList<Expression>(); ((List<Expression>)yyval).addAll((List<Expression>)val_peek(0));}
break;
case 24:
//#line 120 "../../src/parser/parser.y"
{yyval = new ArrayList<Expression>();}
break;
case 25:
//#line 122 "../../src/parser/parser.y"
{yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1));}
break;
case 26:
//#line 125 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(4),(Statement)val_peek(2),((Statement)val_peek(0)));}
break;
case 27:
//#line 126 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(6),(Statement)val_peek(4),(List<Statement>)val_peek(1));}
break;
case 28:
//#line 127 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(6),(List<Statement>)val_peek(3),((Statement) val_peek(0)));}
break;
case 29:
//#line 128 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(8),(List<Statement>)val_peek(5),(List<Statement>)val_peek(1));}
break;
case 30:
//#line 131 "../../src/parser/parser.y"
{List<Statement> st= new ArrayList<Statement>(); st.add((Statement)val_peek(0));
																	 yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),st) ;}
break;
case 31:
//#line 133 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(4),(List<Statement>)val_peek(1));}
break;
case 32:
//#line 137 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 33:
//#line 138 "../../src/parser/parser.y"
{yyval = new ArrayList<VarDefinition>(); }
break;
case 34:
//#line 141 "../../src/parser/parser.y"
{yyval = new ArrayList<VarDefinition>(); ((List<VarDefinition>)yyval).add((VarDefinition)val_peek(0));}
break;
case 35:
//#line 142 "../../src/parser/parser.y"
{yyval = val_peek(2); ((List<VarDefinition>)val_peek(2)).add((VarDefinition)val_peek(0));}
break;
case 36:
//#line 145 "../../src/parser/parser.y"
{yyval = new VarDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(2),(Type)val_peek(0));}
break;
case 37:
//#line 148 "../../src/parser/parser.y"
{yyval = new ArrayList<VarDefinition>(); ((List<VarDefinition>)yyval).addAll((List<VarDefinition>)val_peek(0));}
break;
case 38:
//#line 149 "../../src/parser/parser.y"
{yyval = val_peek(1); ((List<VarDefinition>)yyval).addAll((List<VarDefinition>)val_peek(0));}
break;
case 39:
//#line 152 "../../src/parser/parser.y"
{List<VarDefinition> variables = new ArrayList<VarDefinition>();
										  for(String ident : ((List<String>)val_peek(3))){
											 VarDefinition var = new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)ident ,(Type)val_peek(1)); 
										 	variables.add(var);
										 	}
										 yyval = variables;
										 }
break;
case 40:
//#line 161 "../../src/parser/parser.y"
{yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0));}
break;
case 41:
//#line 162 "../../src/parser/parser.y"
{yyval = val_peek(2); 
	  									if(!((List<String>)yyval).contains((String)val_peek(0)))
	  										((List<String>)yyval).add((String)val_peek(0));
	  									else
	  										new ErrorType(scanner.getLine(), scanner.getColumn(), "duplicated variable");
	  									}
break;
case 42:
//#line 169 "../../src/parser/parser.y"
{yyval = IntType.IntTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 43:
//#line 170 "../../src/parser/parser.y"
{yyval = DoubleType.DoubleTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 44:
//#line 171 "../../src/parser/parser.y"
{yyval = CharType.CharTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 45:
//#line 173 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 46:
//#line 174 "../../src/parser/parser.y"
{yyval = new ArrayType(scanner.getLine(), scanner.getColumn(),((int)val_peek(2)),(Type)val_peek(0));}
break;
case 47:
//#line 175 "../../src/parser/parser.y"
{yyval = new RecordType(scanner.getLine(), scanner.getColumn(),(List<RecordField>)val_peek(1));}
break;
case 48:
//#line 177 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 49:
//#line 178 "../../src/parser/parser.y"
{yyval = VoidType.VoidTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 50:
//#line 182 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 51:
//#line 183 "../../src/parser/parser.y"
{yyval = val_peek(1);
		   												 for(RecordField rf : (List<RecordField>)val_peek(0)){
		   												 	if(!((List<RecordField>)yyval).contains(rf))
		   												 		((List<RecordField>)yyval).add(rf);
		   												 	else
		   												 		new ErrorType(scanner.getLine(), scanner.getColumn(), "duplicated field");
		   												 }
		   												 
		   												 }
break;
case 52:
//#line 194 "../../src/parser/parser.y"
{List<RecordField> variables = new ArrayList<RecordField>();
														 
    													 for(String ident : ((List<String>)val_peek(3))){
        													RecordField recordField = new RecordField(scanner.getLine(), scanner.getColumn(),((String) ident), (Type)val_peek(1));
        													variables.add(recordField);
        													
        												}
 														 yyval = variables;
														}
break;
case 53:
//#line 206 "../../src/parser/parser.y"
{yyval = new ArrayList<Expression>(); ((List<Expression>)yyval).add((Expression)val_peek(0));}
break;
case 54:
//#line 207 "../../src/parser/parser.y"
{yyval = val_peek(2); ((List<Expression>)yyval).add((Expression)val_peek(0));}
break;
case 55:
//#line 210 "../../src/parser/parser.y"
{yyval = new Variable(scanner.getLine(), scanner.getColumn(),(String)val_peek(0));}
break;
case 56:
//#line 211 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 57:
//#line 212 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 58:
//#line 213 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 59:
//#line 214 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 60:
//#line 215 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 61:
//#line 216 "../../src/parser/parser.y"
{yyval = new FieldAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0));}
break;
case 62:
//#line 217 "../../src/parser/parser.y"
{yyval = val_peek(1);}
break;
case 63:
//#line 218 "../../src/parser/parser.y"
{yyval = new Indexin(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1));}
break;
case 64:
//#line 219 "../../src/parser/parser.y"
{yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0));}
break;
case 65:
//#line 220 "../../src/parser/parser.y"
{yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(0));}
break;
case 66:
//#line 221 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 67:
//#line 222 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 68:
//#line 223 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 69:
//#line 224 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 70:
//#line 225 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 71:
//#line 226 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 72:
//#line 227 "../../src/parser/parser.y"
{yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 73:
//#line 228 "../../src/parser/parser.y"
{yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 74:
//#line 229 "../../src/parser/parser.y"
{yyval = new RealLiteral(scanner.getLine(), scanner.getColumn(),(Double)val_peek(0));}
break;
case 75:
//#line 230 "../../src/parser/parser.y"
{yyval = new IntLiteral(scanner.getLine(), scanner.getColumn(),(int)val_peek(0));}
break;
case 76:
//#line 231 "../../src/parser/parser.y"
{yyval = new CharLiteral(scanner.getLine(), scanner.getColumn(),(char)val_peek(0));}
break;
case 77:
//#line 232 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 78:
//#line 233 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 79:
//#line 236 "../../src/parser/parser.y"
{yyval = new Cast(scanner.getLine(), scanner.getColumn(), (Type)val_peek(2),(Expression)val_peek(0));}
break;
case 80:
//#line 238 "../../src/parser/parser.y"
{yyval = new WhileSetatement(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(1));}
break;
case 81:
//#line 240 "../../src/parser/parser.y"
{
  									 for(Expression exp : ((List<Expression>)val_peek(1)))
        								yyval = new Write(scanner.getLine(), scanner.getColumn(), (Expression)exp);
        							 }
break;
case 82:
//#line 245 "../../src/parser/parser.y"
{for(Expression exp : ((List<Expression>)val_peek(1)))
        								yyval = new Read(scanner.getLine(), scanner.getColumn(), (Expression)exp);
        							}
break;
//#line 1133 "Parser.java"
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
