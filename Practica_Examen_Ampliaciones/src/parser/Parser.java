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
public final static short AND_EQUALS=280;
public final static short OR_EQUALS=281;
public final static short SIMPLE=282;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    5,    2,    8,    8,    8,
    8,   10,   10,   11,   11,   11,   11,   11,   11,   11,
   11,   18,   20,   20,   19,   19,   19,   16,   16,   16,
   16,   17,   17,    6,    6,   22,   22,   23,    9,    9,
    4,   25,   25,   24,   24,   24,   26,   26,   26,    7,
    7,   27,   27,   28,   21,   21,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   29,   13,   14,   15,
};
final static short yylen[] = {                            2,
    2,    0,    2,    1,    1,    8,    7,    4,    2,    3,
    3,    1,    2,    3,    1,    1,    1,    1,    1,    2,
    1,    4,    1,    0,    4,    4,    4,    6,    8,    8,
   10,    4,    6,    1,    0,    1,    3,    3,    1,    2,
    4,    1,    3,    1,    1,    1,    1,    4,    4,    1,
    1,    1,    2,    4,    1,    3,    1,    3,    3,    3,
    3,    3,    3,    3,    4,    2,    2,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    1,    1,    1,
    1,    1,    4,    6,    3,    3,
};
final static short yydefred[] = {                         2,
    0,    0,   42,    0,    1,    3,    4,    5,    0,    0,
    0,    0,    0,    0,    0,   44,   45,   46,    0,    0,
   47,    0,   43,    0,    0,    0,   36,    0,    0,    0,
   41,    0,    0,    0,    0,    0,    0,   52,    0,   38,
    0,   37,    0,    0,   49,   53,   48,   51,    0,   50,
    0,    7,    0,    6,   79,   78,    0,   80,    0,    0,
    0,    0,    0,    0,    0,    0,    9,   39,    0,    0,
   12,    0,   15,   16,   17,   18,   19,    0,   21,   81,
   54,    0,    0,    0,   82,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   11,   40,    0,   10,   13,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   20,    0,    0,
    0,    0,   86,    0,   85,    0,    0,   14,   64,    0,
    8,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   63,    0,   22,
    0,    0,    0,    0,    0,    0,   83,   26,   27,   25,
   65,    0,    0,    0,   84,    0,    0,   28,    0,    0,
    0,   30,   29,    0,   31,
};
final static short yydgoto[] = {                          1,
    2,    5,    6,    7,    8,   25,   49,   52,   69,   70,
   71,   72,   73,   74,   75,   76,   77,   85,   79,  119,
   86,   26,   27,   21,    9,   22,   37,   38,   80,
};
final static short yysindex[] = {                         0,
    0, -247,    0, -256,    0,    0,    0,    0,   -3,  -25,
   -1,  -80, -213, -188,   32,    0,    0,    0,  -44, -176,
    0,   31,    0,   33,   51,   56,    0,   43, -144,   23,
    0, -225,   69, -188, -141,   44, -123,    0,  -80,    0,
 -128,    0,    5,  -80,    0,    0,    0,    0,    5,    0,
  501,    0,   78,    0,    0,    0,  111,    0,  278,  278,
  278,  278,  278,  278,  278,  992,    0,    0,  809,  825,
    0,   87,    0,    0,    0,    0,    0,   94,    0,    0,
    0,  278,  111,  388,    0,  -30,   21,  113,  137,  163,
  170,  -27,  189,  127,    0,    0,  840,    0,    0,  278,
  278,  278,  278,  278,  278,  278,  278,  278,  278,  278,
  278,  278,  278,  278,  278, -105,  278,    0,  129,  128,
  278,  278,    0,  278,    0,   53,  857,    0,    0,  278,
    0,  120,  120,  120,  120,  662,  662,  612,  636,  310,
  120,  120,  170,  170,  -27,  -27,  -27,    0,  336,    0,
  662,  662,  388, 1014, 1014,  -89,    0,    0,    0,    0,
    0,  874,  890,  907,    0,  -85, 1014,    0,  923,  938,
 1014,    0,    0,  953,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  144,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  145,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   61,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  360,    0,    0,
    0,  150,  -37,   76,    0,    0,    0,    0,    0,    0,
  214,  -11,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  151,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  461,  469,  499,  507,  512,  710,  -60,  -33,    0,
  535,  560,  413,  439,   16,   25,   52,    0,    0,    0,
  760,  764,  102,    0,    0,  968,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  983,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  -31,    0,    0,    0,  147,    0,  -29,
  697, 1254,    0,    0,    0,    0,    0, 1138,    0,    0,
  -47,    0,  159,  -14,    8,  125,    0,  157,    0,
};
final static int YYTABLESIZE=1384;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         57,
   76,   45,   10,   57,   57,   57,   57,   57,   57,   57,
   20,    3,   87,  124,   14,    4,   11,   40,  116,   68,
   57,   57,   57,   57,   57,   66,   50,   77,  123,   66,
   66,   66,   66,   66,  120,   66,   36,   96,   15,   97,
   13,   16,   17,   18,   36,   23,   66,   66,   66,   66,
   66,   94,   60,   57,   12,   57,   60,   60,   60,   60,
   60,   61,   60,  117,  124,   61,   61,   61,   61,   61,
   24,   61,   28,   60,   60,   60,   60,   60,   29,  125,
   30,   66,   61,   61,   61,   61,   61,   13,   62,   31,
   32,   33,   62,   62,   62,   62,   62,   57,   62,   34,
   35,   44,   57,   57,   42,   57,   57,   57,   60,   62,
   62,   62,   62,   62,    3,   39,   55,   61,   42,   55,
   57,   57,   57,  115,  162,  163,   41,   51,  113,  111,
   43,  112,  116,  114,   55,    3,   81,  170,   16,   17,
   18,  174,   56,   48,   62,   56,  110,  108,  109,  115,
   82,   57,  118,  148,  113,  111,  115,  112,  116,  114,
   56,  113,  111,   47,  112,  116,  114,  130,   53,  150,
  126,  124,  110,  115,  109,  154,  164,  117,  113,  111,
  169,  112,  116,  114,   35,   34,   16,   17,   18,   19,
   24,   23,   42,   46,  127,   54,  110,    0,  109,  115,
    0,    0,    0,  117,  113,  111,  115,  112,  116,  114,
  117,  113,    0,    0,    0,  116,  114,   76,   76,   76,
   76,  128,  110,    0,  109,  115,    0,  117,    0,  129,
  113,  111,    0,  112,  116,  114,   57,   57,   57,   57,
   57,   57,   57,   57,   77,   77,   77,   77,  110,    0,
  109,    0,    0,  117,   67,    0,   67,   67,   67,    0,
  117,    0,   66,   66,   66,   66,   66,   66,   66,   66,
    0,   67,   67,   67,   67,   67,    0,    0,    0,  117,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   60,
   60,   60,   60,   60,   60,   60,   60,    0,   61,   61,
   61,   61,   61,   61,   61,   61,   67,    0,    0,    0,
   65,    0,    0,    0,    0,    0,    0,   66,    0,    0,
    0,    0,   64,    0,    0,   62,   62,   62,   62,   62,
   62,   62,   62,    0,   57,   57,   57,   57,   57,   57,
   57,   57,    0,    0,    0,    0,  115,    0,    0,    0,
    0,  113,  111,    0,  112,  116,  114,    0,    0,    0,
  100,  101,  102,  103,  104,  105,  106,  107,  160,  110,
    0,  109,  115,    0,    0,    0,    0,  113,  111,    0,
  112,  116,  114,    0,    0,    0,  100,  101,  102,  103,
  104,  105,  121,  122,    0,  110,   82,  109,    0,    0,
  117,   82,   82,    0,   82,   82,   82,    0,    0,    0,
  100,  101,  102,  103,  104,  105,  121,  122,    0,   82,
   82,   82,    0,    0,  115,    0,  117,    0,  161,  113,
  111,    0,  112,  116,  114,    0,  100,  101,  102,  103,
  104,  105,  121,  122,    0,    0,    0,  110,    0,  109,
   82,    0,    0,   58,    0,   58,   58,   58,    0,    0,
    0,    0,  100,  101,  102,  103,  104,  105,  121,  122,
   58,   58,   58,   58,   58,    0,    0,    0,  117,   59,
    0,   59,   59,   59,    0,    0,    0,   67,   67,   67,
   67,   67,   67,   67,   67,    0,   59,   59,   59,   59,
   59,   72,    0,    0,   72,   58,    0,    0,    0,   71,
    0,    0,   71,    0,    0,    0,    0,    0,   72,   72,
   72,   72,   72,    0,    0,    0,   71,   71,   71,   71,
   71,   59,    0,   65,   55,   56,   83,   58,    0,   73,
   66,    0,   73,    0,    0,   64,    0,   70,    0,    0,
   70,    0,   75,   72,    0,   75,   73,   73,   73,   73,
   73,   71,    0,    0,   70,   70,   70,   70,   70,   75,
   75,    0,   75,    0,    0,   68,    0,    0,   68,    0,
    0,    0,    0,  100,  101,  102,  103,  104,  105,  121,
  122,   73,   68,   68,   68,   68,   68,    0,    0,   70,
   69,    0,    0,   69,   75,    0,    0,    0,    0,  100,
  101,  102,  103,  104,  105,  121,  122,   69,   69,   69,
   69,   69,    0,    0,    0,   67,    0,   68,    0,    0,
    0,    0,    0,   82,   82,   82,   82,   82,   82,   82,
   82,    0,    0,    0,    0,    0,    0,    0,  115,    0,
    0,    0,   69,  113,  111,    0,  112,  116,  114,    0,
    0,  100,  101,  102,  103,  104,  105,  121,  122,    0,
  158,  110,  115,  109,    0,    0,    0,  113,  111,    0,
  112,  116,  114,    0,    0,    0,   58,   58,   58,   58,
   58,   58,   58,   58,  159,  110,    0,  109,  115,    0,
    0,    0,  117,  113,  111,    0,  112,  116,  114,    0,
    0,    0,   59,   59,   59,   59,   59,   59,   59,   59,
    0,  110,    0,  109,    0,    0,  117,    0,    0,    0,
    0,    0,    0,    0,   72,   72,   72,   72,   72,   72,
   72,   72,   71,   71,   71,   71,   71,   71,   71,   71,
   74,    0,  117,   74,    0,    0,    0,   55,   56,   57,
   58,   59,   60,    0,   61,   62,   99,   74,   74,    0,
   74,   63,   73,   73,   73,   73,   73,   73,   73,   73,
   70,   70,   70,   70,   70,   70,   70,   70,    0,   75,
   75,   75,   75,   99,    0,    0,    0,    0,    0,    0,
   76,    0,   74,   76,   77,    0,    0,   77,   68,   68,
   68,   68,   68,   68,   68,   68,    0,   76,   76,    0,
   76,   77,   77,  156,   77,    0,    0,    0,    0,    0,
    0,    0,    0,   69,   69,   69,   69,   69,   69,   69,
   69,   65,    0,    0,    0,    0,    0,    0,   66,    0,
    0,    0,   76,   64,    0,    0,   77,   65,   99,   99,
  168,    0,    0,    0,   66,  172,   99,    0,    0,   64,
   99,    0,   65,    0,    0,    0,    0,    0,    0,   66,
    0,    0,    0,    0,   64,  100,  101,  102,  103,   65,
    0,    0,    0,    0,    0,    0,   66,    0,    0,    0,
    0,   64,    0,    0,    0,    0,   65,    0,    0,  100,
  101,  102,  103,   66,    0,    0,    0,    0,   64,    0,
    0,    0,   65,    0,    0,    0,    0,    0,    0,   66,
    0,    0,    0,   95,   64,  100,  101,  102,  103,   65,
    0,    0,    0,    0,    0,    0,   66,    0,    0,   98,
    0,   64,    0,    0,    0,   65,    0,    0,    0,    0,
    0,    0,   66,    0,  131,    0,    0,   64,    0,    0,
   65,    0,    0,    0,    0,    0,    0,   66,    0,  155,
    0,    0,   64,    0,    0,   65,    0,   74,   74,   74,
   74,    0,   66,    0,    0,    0,    0,   64,  165,    0,
   32,    0,    0,    0,    0,    0,    0,   32,    0,    0,
    0,    0,   32,    0,  166,   33,    0,    0,    0,    0,
    0,    0,   33,    0,   65,    0,    0,   33,    0,  167,
    0,   66,    0,    0,    0,    0,   64,   76,   76,   76,
   76,   77,   77,   77,   77,  171,   65,    0,    0,    0,
    0,    0,    0,   66,    0,    0,    0,    0,   64,    0,
    0,    0,  173,    0,    0,   55,   56,   57,   58,   59,
   60,    0,   61,   62,    0,    0,    0,  175,    0,   63,
    0,   55,   56,   83,   58,   59,   60,    0,   61,   62,
    0,    0,   32,    0,    0,   63,   55,   56,   83,   58,
   59,   60,    0,   61,   62,    0,    0,   33,    0,    0,
   63,    0,    0,   55,   56,   83,   58,   59,   60,    0,
   61,   62,    0,    0,    0,    0,    0,   63,    0,    0,
   55,   56,   83,   58,   59,   60,    0,   61,   62,    0,
    0,    0,    0,    0,   63,    0,   55,   56,   83,   58,
   59,   60,    0,   61,   62,    0,    0,    0,    0,    0,
   63,    0,    0,   55,   56,   83,   58,   59,   60,    0,
   61,   62,    0,    0,    0,    0,    0,   63,    0,   55,
   56,   83,   58,   59,   60,    0,   61,   62,   78,    0,
    0,    0,    0,   63,   55,   56,   83,   58,   59,   60,
    0,   61,   62,    0,    0,    0,   78,   78,   63,   55,
   56,   83,   58,   59,   60,    0,   61,   62,    0,    0,
    0,    0,    0,   63,   32,   32,   32,   32,   32,   32,
    0,   32,   32,    0,   78,    0,    0,    0,   32,   33,
   33,   33,   33,   33,   33,    0,   33,   33,   55,   56,
   83,   58,    0,   33,    0,    0,    0,    0,   16,   17,
   18,    0,    0,    0,   78,    0,    0,    0,    0,    0,
   55,   56,   83,   58,   59,   60,    0,   61,   62,    0,
    0,    0,    0,    0,   63,    0,    0,    0,    0,    0,
    0,   78,   78,    0,    0,    0,    0,    0,    0,   78,
   78,   78,    0,    0,   78,    0,   78,   78,   78,    0,
    0,   78,   84,   84,   88,   89,   90,   91,   92,   93,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   84,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  132,  133,  134,  135,  136,  137,  138,
  139,  140,  141,  142,  143,  144,  145,  146,  147,    0,
  149,    0,    0,    0,  151,  152,    0,  153,    0,    0,
    0,    0,    0,  157,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   61,  125,  259,   41,   42,   43,   44,   45,   46,   47,
   91,  259,   60,   44,   40,  263,  273,   32,   46,   51,
   58,   59,   60,   61,   62,   37,   41,   61,   59,   41,
   42,   43,   44,   45,   82,   47,   29,   69,   40,   69,
   44,  267,  268,  269,   37,  259,   58,   59,   60,   61,
   62,   66,   37,   91,   58,   93,   41,   42,   43,   44,
   45,   37,   47,   91,   44,   41,   42,   43,   44,   45,
  259,   47,   41,   58,   59,   60,   61,   62,  123,   59,
  257,   93,   58,   59,   60,   61,   62,   44,   37,   59,
   58,   41,   41,   42,   43,   44,   45,   37,   47,   44,
   58,   58,   42,   43,   44,   45,   46,   47,   93,   58,
   59,   60,   61,   62,  259,   93,   41,   93,   58,   44,
   60,   61,   62,   37,  154,  155,   58,  123,   42,   43,
  272,   45,   46,   47,   59,  259,   59,  167,  267,  268,
  269,  171,   41,  272,   93,   44,   60,   61,   62,   37,
   40,   91,   59,  259,   42,   43,   37,   45,   46,   47,
   59,   42,   43,   39,   45,   46,   47,   41,   44,   41,
   58,   44,   60,   37,   62,  123,  266,   91,   42,   43,
  266,   45,   46,   47,   41,   41,  267,  268,  269,  270,
   41,   41,   34,   37,   58,   49,   60,   -1,   62,   37,
   -1,   -1,   -1,   91,   42,   43,   37,   45,   46,   47,
   91,   42,   -1,   -1,   -1,   46,   47,  278,  279,  280,
  281,   59,   60,   -1,   62,   37,   -1,   91,   -1,   41,
   42,   43,   -1,   45,   46,   47,  274,  275,  276,  277,
  278,  279,  280,  281,  278,  279,  280,  281,   60,   -1,
   62,   -1,   -1,   91,   41,   -1,   43,   44,   45,   -1,
   91,   -1,  274,  275,  276,  277,  278,  279,  280,  281,
   -1,   58,   59,   60,   61,   62,   -1,   -1,   -1,   91,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,
  275,  276,  277,  278,  279,  280,  281,   -1,  274,  275,
  276,  277,  278,  279,  280,  281,   93,   -1,   -1,   -1,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,  274,  275,  276,  277,  278,
  279,  280,  281,   -1,  274,  275,  276,  277,  278,  279,
  280,  281,   -1,   -1,   -1,   -1,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,  280,  281,   59,   60,
   -1,   62,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,  274,  275,  276,  277,
  278,  279,  280,  281,   -1,   60,   37,   62,   -1,   -1,
   91,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,  280,  281,   -1,   60,
   61,   62,   -1,   -1,   37,   -1,   91,   -1,   93,   42,
   43,   -1,   45,   46,   47,   -1,  274,  275,  276,  277,
  278,  279,  280,  281,   -1,   -1,   -1,   60,   -1,   62,
   91,   -1,   -1,   41,   -1,   43,   44,   45,   -1,   -1,
   -1,   -1,  274,  275,  276,  277,  278,  279,  280,  281,
   58,   59,   60,   61,   62,   -1,   -1,   -1,   91,   41,
   -1,   43,   44,   45,   -1,   -1,   -1,  274,  275,  276,
  277,  278,  279,  280,  281,   -1,   58,   59,   60,   61,
   62,   41,   -1,   -1,   44,   93,   -1,   -1,   -1,   41,
   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   58,   59,
   60,   61,   62,   -1,   -1,   -1,   58,   59,   60,   61,
   62,   93,   -1,   33,  257,  258,  259,  260,   -1,   41,
   40,   -1,   44,   -1,   -1,   45,   -1,   41,   -1,   -1,
   44,   -1,   41,   93,   -1,   44,   58,   59,   60,   61,
   62,   93,   -1,   -1,   58,   59,   60,   61,   62,   58,
   59,   -1,   61,   -1,   -1,   41,   -1,   -1,   44,   -1,
   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,  280,
  281,   93,   58,   59,   60,   61,   62,   -1,   -1,   93,
   41,   -1,   -1,   44,   93,   -1,   -1,   -1,   -1,  274,
  275,  276,  277,  278,  279,  280,  281,   58,   59,   60,
   61,   62,   -1,   -1,   -1,  125,   -1,   93,   -1,   -1,
   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,  280,
  281,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   37,   -1,
   -1,   -1,   93,   42,   43,   -1,   45,   46,   47,   -1,
   -1,  274,  275,  276,  277,  278,  279,  280,  281,   -1,
   59,   60,   37,   62,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,  274,  275,  276,  277,
  278,  279,  280,  281,   59,   60,   -1,   62,   37,   -1,
   -1,   -1,   91,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,  274,  275,  276,  277,  278,  279,  280,  281,
   -1,   60,   -1,   62,   -1,   -1,   91,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
  280,  281,  274,  275,  276,  277,  278,  279,  280,  281,
   41,   -1,   91,   44,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,   -1,  264,  265,   70,   58,   59,   -1,
   61,  271,  274,  275,  276,  277,  278,  279,  280,  281,
  274,  275,  276,  277,  278,  279,  280,  281,   -1,  278,
  279,  280,  281,   97,   -1,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   93,   44,   41,   -1,   -1,   44,  274,  275,
  276,  277,  278,  279,  280,  281,   -1,   58,   59,   -1,
   61,   58,   59,  127,   61,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,  280,
  281,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,
   -1,   -1,   93,   45,   -1,   -1,   93,   33,  162,  163,
  164,   -1,   -1,   -1,   40,  169,  170,   -1,   -1,   45,
  174,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   45,  274,  275,  276,  277,   33,
   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,
   -1,   45,   -1,   -1,   -1,   -1,   33,   -1,   -1,  274,
  275,  276,  277,   40,   -1,   -1,   -1,   -1,   45,   -1,
   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   -1,   -1,   -1,  125,   45,  274,  275,  276,  277,   33,
   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,  125,
   -1,   45,   -1,   -1,   -1,   33,   -1,   -1,   -1,   -1,
   -1,   -1,   40,   -1,  125,   -1,   -1,   45,   -1,   -1,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,  123,
   -1,   -1,   45,   -1,   -1,   33,   -1,  278,  279,  280,
  281,   -1,   40,   -1,   -1,   -1,   -1,   45,  125,   -1,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,  125,   33,   -1,   -1,   -1,   -1,
   -1,   -1,   40,   -1,   33,   -1,   -1,   45,   -1,  123,
   -1,   40,   -1,   -1,   -1,   -1,   45,  278,  279,  280,
  281,  278,  279,  280,  281,  123,   33,   -1,   -1,   -1,
   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,
   -1,   -1,  125,   -1,   -1,  257,  258,  259,  260,  261,
  262,   -1,  264,  265,   -1,   -1,   -1,  125,   -1,  271,
   -1,  257,  258,  259,  260,  261,  262,   -1,  264,  265,
   -1,   -1,  125,   -1,   -1,  271,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,   -1,   -1,  125,   -1,   -1,
  271,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
  264,  265,   -1,   -1,   -1,   -1,   -1,  271,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,  264,  265,   -1,
   -1,   -1,   -1,   -1,  271,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,   -1,   -1,   -1,   -1,   -1,
  271,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
  264,  265,   -1,   -1,   -1,   -1,   -1,  271,   -1,  257,
  258,  259,  260,  261,  262,   -1,  264,  265,   51,   -1,
   -1,   -1,   -1,  271,  257,  258,  259,  260,  261,  262,
   -1,  264,  265,   -1,   -1,   -1,   69,   70,  271,  257,
  258,  259,  260,  261,  262,   -1,  264,  265,   -1,   -1,
   -1,   -1,   -1,  271,  257,  258,  259,  260,  261,  262,
   -1,  264,  265,   -1,   97,   -1,   -1,   -1,  271,  257,
  258,  259,  260,  261,  262,   -1,  264,  265,  257,  258,
  259,  260,   -1,  271,   -1,   -1,   -1,   -1,  267,  268,
  269,   -1,   -1,   -1,  127,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  261,  262,   -1,  264,  265,   -1,
   -1,   -1,   -1,   -1,  271,   -1,   -1,   -1,   -1,   -1,
   -1,  154,  155,   -1,   -1,   -1,   -1,   -1,   -1,  162,
  163,  164,   -1,   -1,  167,   -1,  169,  170,  171,   -1,
   -1,  174,   59,   60,   61,   62,   63,   64,   65,   66,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   82,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  100,  101,  102,  103,  104,  105,  106,
  107,  108,  109,  110,  111,  112,  113,  114,  115,   -1,
  117,   -1,   -1,   -1,  121,  122,   -1,  124,   -1,   -1,
   -1,   -1,   -1,  130,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=282;
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
"DISTINTO","OR","AND","AND_EQUALS","OR_EQUALS","SIMPLE",
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
"assignment : expression AND_EQUALS expression ';'",
"assignment : expression OR_EQUALS expression ';'",
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
"expression : expression AND_EQUALS expression",
"expression : expression OR_EQUALS expression",
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

//#line 265 "../../src/parser/parser.y"

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
//#line 661 "Parser.java"
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
//#line 82 "../../src/parser/parser.y"
{yyval = (List<Definition>)val_peek(1); ((List<Definition>)yyval).add((FunDefinition)val_peek(0));
											 raiz = new Program(scanner.getLine(), scanner.getColumn(),(List<Definition>)yyval);}
break;
case 2:
//#line 86 "../../src/parser/parser.y"
{yyval = new ArrayList<Definition>();}
break;
case 3:
//#line 87 "../../src/parser/parser.y"
{yyval = val_peek(1); ((List<Definition>)yyval).addAll((List<Definition>)val_peek(0));}
break;
case 4:
//#line 90 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 5:
//#line 91 "../../src/parser/parser.y"
{yyval = new ArrayList<Definition>(); ((List<Definition>)yyval).add((Definition)val_peek(0));}
break;
case 6:
//#line 94 "../../src/parser/parser.y"
{FunctionType funcionType = new FunctionType(scanner.getLine(), scanner.getColumn(),(List<VarDefinition>)val_peek(4),(Type)val_peek(1));
														 yyval = new FunDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(6),funcionType, (List<Statement>)val_peek(0));}
break;
case 7:
//#line 98 "../../src/parser/parser.y"
{FunctionType funcionType = new FunctionType(scanner.getLine(), scanner.getColumn(),null,VoidType.VoidTypeInstance(scanner.getLine(), scanner.getColumn()));
											 yyval = new FunDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(5),funcionType,(List<Statement>)val_peek(0));}
break;
case 8:
//#line 101 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).addAll((List<VarDefinition>)val_peek(2)); ((List<Statement>)yyval).addAll((List<Statement>)val_peek(1)); }
break;
case 9:
//#line 102 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); }
break;
case 10:
//#line 103 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).addAll((List<Statement>)val_peek(1)); }
break;
case 11:
//#line 104 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).addAll((List<Statement>)val_peek(1)); }
break;
case 12:
//#line 107 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).addAll((List<Statement>)val_peek(0));}
break;
case 13:
//#line 108 "../../src/parser/parser.y"
{yyval = val_peek(1) ; ((List<Statement>)yyval).addAll((List<Statement>)val_peek(0));}
break;
case 14:
//#line 111 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add(new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1) ));}
break;
case 15:
//#line 112 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add((Statement)val_peek(0));}
break;
case 16:
//#line 113 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 17:
//#line 114 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 18:
//#line 115 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add((Statement)val_peek(0));}
break;
case 19:
//#line 116 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add((Statement)val_peek(0));}
break;
case 20:
//#line 117 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add((Statement)val_peek(1));}
break;
case 21:
//#line 118 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add((Statement)val_peek(0));}
break;
case 22:
//#line 121 "../../src/parser/parser.y"
{yyval = new Invocation(scanner.getLine(), scanner.getColumn(),new Variable(scanner.getLine(), scanner.getColumn(),(String)val_peek(3)), (List<Expression>)val_peek(1));}
break;
case 23:
//#line 123 "../../src/parser/parser.y"
{yyval = new ArrayList<Expression>(); ((List<Expression>)yyval).addAll((List<Expression>)val_peek(0));}
break;
case 24:
//#line 124 "../../src/parser/parser.y"
{yyval = new ArrayList<Expression>();}
break;
case 25:
//#line 126 "../../src/parser/parser.y"
{yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1));}
break;
case 26:
//#line 127 "../../src/parser/parser.y"
{yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3),(String) val_peek(2), (Expression)val_peek(1)));}
break;
case 27:
//#line 128 "../../src/parser/parser.y"
{yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3),(String) val_peek(2), (Expression)val_peek(1)));}
break;
case 28:
//#line 131 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(4),(List<Statement>)val_peek(2),((List<Statement>)val_peek(0)));}
break;
case 29:
//#line 132 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(6),(List<Statement>)val_peek(4),(List<Statement>)val_peek(1));}
break;
case 30:
//#line 133 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(6),(List<Statement>)val_peek(3),((List<Statement>) val_peek(0)));}
break;
case 31:
//#line 134 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(8),(List<Statement>)val_peek(5),(List<Statement>)val_peek(1));}
break;
case 32:
//#line 137 "../../src/parser/parser.y"
{List<Statement> st= new ArrayList<Statement>(); st.addAll((List<Statement>)val_peek(0));
																	 yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),st) ;}
break;
case 33:
//#line 139 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(4),(List<Statement>)val_peek(1));}
break;
case 34:
//#line 143 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 35:
//#line 144 "../../src/parser/parser.y"
{yyval = new ArrayList<VarDefinition>(); }
break;
case 36:
//#line 147 "../../src/parser/parser.y"
{yyval = new ArrayList<VarDefinition>(); ((List<VarDefinition>)yyval).add((VarDefinition)val_peek(0));}
break;
case 37:
//#line 148 "../../src/parser/parser.y"
{yyval = val_peek(2); ((List<VarDefinition>)val_peek(2)).add((VarDefinition)val_peek(0));}
break;
case 38:
//#line 151 "../../src/parser/parser.y"
{yyval = new VarDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(2),(Type)val_peek(0));}
break;
case 39:
//#line 154 "../../src/parser/parser.y"
{yyval = new ArrayList<VarDefinition>(); ((List<VarDefinition>)yyval).addAll((List<VarDefinition>)val_peek(0));}
break;
case 40:
//#line 155 "../../src/parser/parser.y"
{yyval = val_peek(1); ((List<VarDefinition>)yyval).addAll((List<VarDefinition>)val_peek(0));}
break;
case 41:
//#line 158 "../../src/parser/parser.y"
{List<VarDefinition> variables = new ArrayList<VarDefinition>();
										  for(String ident : ((List<String>)val_peek(3))){
											 VarDefinition var = new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)ident ,(Type)val_peek(1)); 
										 	variables.add(var);
										 	}
										 yyval = variables;
										 }
break;
case 42:
//#line 167 "../../src/parser/parser.y"
{yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0));}
break;
case 43:
//#line 168 "../../src/parser/parser.y"
{yyval = val_peek(2); 
	  									if(!((List<String>)yyval).contains((String)val_peek(0)))
	  										((List<String>)yyval).add((String)val_peek(0));
	  									else
	  										new ErrorType(scanner.getLine(), scanner.getColumn(), "duplicated variable");
	  									}
break;
case 44:
//#line 175 "../../src/parser/parser.y"
{yyval = IntType.IntTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 45:
//#line 176 "../../src/parser/parser.y"
{yyval = DoubleType.DoubleTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 46:
//#line 177 "../../src/parser/parser.y"
{yyval = CharType.CharTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 47:
//#line 179 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 48:
//#line 180 "../../src/parser/parser.y"
{yyval = new ArrayType(scanner.getLine(), scanner.getColumn(),((int)val_peek(2)),(Type)val_peek(0));}
break;
case 49:
//#line 181 "../../src/parser/parser.y"
{yyval = new RecordType(scanner.getLine(), scanner.getColumn(),(List<RecordField>)val_peek(1));}
break;
case 50:
//#line 183 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 51:
//#line 184 "../../src/parser/parser.y"
{yyval = VoidType.VoidTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 52:
//#line 188 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 53:
//#line 189 "../../src/parser/parser.y"
{yyval = val_peek(1);
		   												 for(RecordField rf : (List<RecordField>)val_peek(0)){
		   												 	if(!((List<RecordField>)yyval).contains(rf))
		   												 		((List<RecordField>)yyval).add(rf);
		   												 	else
		   												 		new ErrorType(scanner.getLine(), scanner.getColumn(), "duplicated field");
		   												 }
		   												 
		   												 }
break;
case 54:
//#line 200 "../../src/parser/parser.y"
{List<RecordField> variables = new ArrayList<RecordField>();
														 
    													 for(String ident : ((List<String>)val_peek(3))){
        													RecordField recordField = new RecordField(scanner.getLine(), scanner.getColumn(),((String) ident), (Type)val_peek(1));
        													variables.add(recordField);
        													
        												}
 														 yyval = variables;
														}
break;
case 55:
//#line 212 "../../src/parser/parser.y"
{yyval = new ArrayList<Expression>(); ((List<Expression>)yyval).add((Expression)val_peek(0));}
break;
case 56:
//#line 213 "../../src/parser/parser.y"
{yyval = val_peek(2); ((List<Expression>)yyval).add((Expression)val_peek(0));}
break;
case 57:
//#line 216 "../../src/parser/parser.y"
{yyval = new Variable(scanner.getLine(), scanner.getColumn(),(String)val_peek(0));}
break;
case 58:
//#line 217 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 59:
//#line 218 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 60:
//#line 219 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 61:
//#line 220 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 62:
//#line 221 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 63:
//#line 222 "../../src/parser/parser.y"
{yyval = new FieldAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0));}
break;
case 64:
//#line 223 "../../src/parser/parser.y"
{yyval = val_peek(1);}
break;
case 65:
//#line 224 "../../src/parser/parser.y"
{yyval = new Indexin(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1));}
break;
case 66:
//#line 225 "../../src/parser/parser.y"
{yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0));}
break;
case 67:
//#line 226 "../../src/parser/parser.y"
{yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(0));}
break;
case 68:
//#line 227 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 69:
//#line 228 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 70:
//#line 229 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 71:
//#line 230 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 72:
//#line 231 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 73:
//#line 232 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 74:
//#line 233 "../../src/parser/parser.y"
{yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 75:
//#line 234 "../../src/parser/parser.y"
{yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 76:
//#line 235 "../../src/parser/parser.y"
{yyval = new AssignmentLogical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 77:
//#line 236 "../../src/parser/parser.y"
{yyval = new AssignmentLogical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 78:
//#line 237 "../../src/parser/parser.y"
{yyval = new RealLiteral(scanner.getLine(), scanner.getColumn(),(Double)val_peek(0));}
break;
case 79:
//#line 238 "../../src/parser/parser.y"
{yyval = new IntLiteral(scanner.getLine(), scanner.getColumn(),(int)val_peek(0));}
break;
case 80:
//#line 239 "../../src/parser/parser.y"
{yyval = new CharLiteral(scanner.getLine(), scanner.getColumn(),(char)val_peek(0));}
break;
case 81:
//#line 240 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 82:
//#line 241 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 83:
//#line 244 "../../src/parser/parser.y"
{yyval = new Cast(scanner.getLine(), scanner.getColumn(), (Type)val_peek(2),(Expression)val_peek(0));}
break;
case 84:
//#line 246 "../../src/parser/parser.y"
{yyval = new WhileSetatement(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(1));}
break;
case 85:
//#line 248 "../../src/parser/parser.y"
{List<Statement> statements = new ArrayList<Statement>();
  									 for(Expression exp : ((List<Expression>)val_peek(1))){
        								yyval = new Write(scanner.getLine(), scanner.getColumn(), (Expression)exp);
        							 	statements.add((Statement)yyval);
        							 	}
        							 	yyval=statements;
        							 }
break;
case 86:
//#line 256 "../../src/parser/parser.y"
{List<Statement> statements = new ArrayList<Statement>();
										for(Expression exp : ((List<Expression>)val_peek(1))){
        								yyval = new Read(scanner.getLine(), scanner.getColumn(), (Expression)exp);
        								statements.add((Statement)yyval);
        								}
        								yyval=statements;
        							}
break;
//#line 1197 "Parser.java"
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
