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
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/

import ast.*;
import scanner.Scanner;
import java.io.Reader;
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
   10,   10,   11,   11,   11,   11,   11,   11,   11,   11,
   18,   21,   20,   20,   19,   16,   16,   16,   16,   17,
   17,    6,    6,   23,   23,   24,    9,    9,    4,   26,
   26,   25,   25,   25,   27,   27,   27,    7,    7,   28,
   28,   29,   22,   22,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   30,   13,
   14,   15,
};
final static short yylen[] = {                            2,
    2,    0,    2,    1,    1,    8,    7,    4,    2,    3,
    1,    2,    3,    1,    1,    1,    1,    1,    2,    1,
    4,    4,    1,    0,    4,    6,    8,    8,   10,    4,
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
   11,    0,   14,   15,   16,   17,   18,    0,   20,   78,
   77,   52,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   38,    0,    0,   10,   12,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   19,    0,    0,    0,   82,
    0,   81,    0,    0,   13,   62,    0,    8,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   61,    0,    0,    0,    0,    0,    0,    0,
   79,   25,   63,   22,    0,    0,    0,   80,    0,    0,
   26,    0,    0,    0,   28,   27,    0,   29,
};
final static short yydgoto[] = {                          1,
    2,    5,    6,    7,    8,   25,   49,   52,   69,   70,
   71,   72,   73,   74,   75,   76,   77,   78,   79,  117,
   80,  118,   26,   27,   21,    9,   22,   37,   38,   81,
};
final static short yysindex[] = {                         0,
    0, -220,    0, -221,    0,    0,    0,    0,   21,  -38,
  -25,  -75, -218, -188,   32,    0,    0,    0,  -43, -167,
    0,   42,    0,   33,   97,   84,    0,   85, -117,   51,
    0, -142,   92, -188, -121,   44, -124,    0,  -75,    0,
 -152,    0,   30,  -75,    0,    0,    0,    0,   30,    0,
  537,    0,   98,    0,    0,    0,  119,    0,  382,  382,
  382,  382,  382,  382,  382,  275,    0,    0,  930,  589,
    0,   94,    0,    0,    0,    0,    0,  103,    0,    0,
    0,    0,  382,  129,  434,  -17,   -4,  121,  128,  154,
  180,  -27,  161,  136,    0,  624,  119,    0,    0,  382,
  382,  382,  382,  382,  382,  382,  382,  382,  382,  382,
  382,  382,  382,  -79,  382,    0,  141,  140,  382,    0,
  382,    0,   64,  655,    0,    0,  382,    0,  343,  343,
  343,  343,  458,  458,  187,  343,  343,  180,  180,  -27,
  -27,  -27,    0,  214,    0,  148,  434,  960,  960,  -61,
    0,    0,    0,    0,  682,  727,  770,    0,  -57,  960,
    0,  822,  847,  960,    0,    0,  865,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   97,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  169,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   61,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  170,  -37,  -30,    0,    0,    0,    0,    0,
  468,  -11,    0,    0,    0,    0,  407,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  174,  170,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  316,  514,
  543,  555,  228,  658,    0,  565,  594,  478,  507,   16,
   25,   52,    0,    0,   87,    0,  -24,    0,    0,  885,
    0,    0,    0,    0,    0,    0,    0,    0,  915,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  -23,    0,    0,    0,  171,    0,   12,
  188, 1143,    0,    0,    0,    0,    0,    0,    0,   99,
    0,  -15,    0,  191,  -29,   63,  -26,    0,  194,    0,
};
final static int YYTABLESIZE=1270;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         55,
   45,   14,   40,   55,   55,   55,   55,   55,   55,   55,
   53,   50,   47,   53,   15,   20,   54,   53,  114,   54,
   55,   55,   55,   55,   55,   64,  121,   68,   53,   64,
   64,   64,   64,   64,   54,   64,   94,   10,    3,  121,
   23,  120,    4,   86,   87,   95,   64,   64,   64,   64,
   64,   11,   58,   55,  122,   55,   58,   58,   58,   58,
   58,   59,   58,  115,   13,   59,   59,   59,   59,   59,
   24,   59,   28,   58,   58,   58,   58,   58,   12,   29,
   96,   64,   59,   59,   59,   59,   59,   13,   60,   30,
   32,   36,   60,   60,   60,   60,   60,   55,   60,   36,
   31,   44,   55,   55,   40,   55,   55,   55,   58,   60,
   60,   60,   60,   60,   16,   17,   18,   59,   40,   48,
   55,   55,   55,   22,   16,   17,   18,   34,   22,   22,
  113,   22,   22,   22,    3,  111,  109,   33,  110,  114,
  112,    3,   35,   39,   60,   21,   22,   22,   22,   41,
   43,   55,   51,  108,  106,  107,   82,  113,   83,  155,
  156,  116,  111,  109,  113,  110,  114,  112,  119,  111,
  109,  163,  110,  114,  112,  167,  127,   22,  123,  143,
  108,  145,  107,  121,  115,  124,  148,  108,  154,  107,
  113,   16,   17,   18,   19,  111,  109,  113,  110,  114,
  112,  126,  111,  109,  157,  110,  114,  112,  162,   32,
   24,  115,  125,  108,   23,  107,  113,  146,  115,   54,
  108,  111,  107,  113,   42,  114,  112,    0,  111,  109,
   46,  110,  114,  112,    0,    0,   55,   55,   55,   55,
   55,   55,    0,    0,  115,  152,  108,    0,  107,    0,
  113,  115,    0,    0,    0,  111,  109,   99,  110,  114,
  112,    0,   64,   64,   64,   64,   64,   64,   73,    0,
  115,   73,    0,  108,    0,  107,    0,  115,    0,    0,
    0,    0,    0,   99,    0,   73,   73,    0,   73,   58,
   58,   58,   58,   58,   58,    0,    0,    0,   59,   59,
   59,   59,   59,   59,  115,    0,  153,   65,    0,    0,
    0,  150,    0,    0,   66,    0,    0,    0,    0,   64,
   73,    0,    0,    0,    0,   60,   60,   60,   60,   60,
   60,    0,    0,    0,   55,   55,   55,   55,   55,   55,
    0,    0,   99,   99,  161,    0,    0,    0,    0,  165,
   99,    0,    0,    0,   99,    0,   70,    0,    0,   70,
   22,   22,   22,   22,   22,   22,    0,  100,  101,  102,
  103,  104,  105,   70,   70,   70,   70,   70,    0,  113,
    0,    0,    0,    0,  111,  109,    0,  110,  114,  112,
    0,    0,    0,    0,  100,  101,  102,  103,  104,  105,
    0,  100,  101,  102,  103,  104,  105,    0,   70,    0,
    0,    0,    0,    0,   65,    0,    0,    0,    0,    0,
    0,   66,    0,    0,    0,    0,   64,  100,  101,  102,
  103,  104,  105,  115,  100,  101,  102,  103,  104,  105,
    0,    0,    0,   55,    0,    0,    0,    0,   55,   55,
    0,   55,   55,   55,    0,    0,    0,    0,    0,    0,
  100,  101,  102,  103,  104,  105,   55,   55,   55,    0,
  113,    0,    0,    0,    0,  111,  109,    0,  110,  114,
  112,    0,    0,    0,    0,    0,    0,  100,  101,  102,
  103,  104,  105,  108,  113,  107,    0,   55,    0,  111,
  109,    0,  110,  114,  112,   73,   73,    0,   65,    0,
   65,   65,   65,    0,    0,    0,    0,  108,   56,  107,
   56,   56,   56,    0,  115,   65,   65,   65,   65,   65,
    0,   55,   56,   84,   58,   56,   56,   56,   56,   56,
    0,   16,   17,   18,    0,    0,    0,   57,  115,   57,
   57,   57,    0,    0,   69,    0,    0,   69,    0,    0,
   65,    0,    0,    0,   57,   57,   57,   57,   57,   65,
   56,   69,   69,   69,   69,   69,   66,    0,    0,    0,
    0,   64,    0,   71,    0,    0,   71,    0,    0,   70,
   70,   70,   70,   70,   70,   68,    0,    0,   68,   57,
   71,   71,   71,   71,   71,   66,   69,    0,   66,    0,
    0,    0,   68,   68,   68,   68,   68,    0,    0,    0,
    0,   65,   66,   66,   66,   66,   66,    0,   66,    0,
    0,    0,    0,   64,   67,   71,    0,   67,   55,   56,
   84,   58,    0,    0,    0,    0,    0,   68,    0,    0,
    0,   67,   67,   67,   67,   67,   65,   66,    0,    0,
    0,   67,    0,   66,    0,    0,    0,    0,   64,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   55,   55,   55,   55,   55,   55,   67,   65,    0,    0,
    0,    0,    0,    0,   66,    0,    0,    0,   72,   64,
    0,   72,    0,    0,    0,    0,    0,  100,  101,  102,
  103,  104,  105,   98,   65,   72,   72,    0,   72,    0,
    0,   66,    0,    0,    0,    0,   64,    0,    0,    0,
    0,  100,  101,  102,  103,    0,    0,    0,    0,    0,
    0,   65,   65,   65,   65,   65,   65,    0,  128,    0,
   72,   56,   56,   56,   56,   56,   56,    0,    0,   65,
    0,    0,    0,    0,    0,    0,   66,    0,    0,    0,
    0,   64,    0,    0,    0,    0,    0,  149,    0,    0,
   57,   57,   57,   57,   57,   57,    0,   69,   69,   69,
   69,   69,   69,   55,   56,   57,   58,   59,   60,    0,
   61,   62,   65,    0,    0,    0,  158,   63,    0,   66,
    0,    0,    0,    0,   64,    0,   71,   71,   71,   71,
   71,   71,    0,    0,    0,    0,    0,    0,   68,   68,
   68,   68,   68,   68,    0,    0,    0,    0,   66,   66,
   66,   66,   66,   66,    0,   55,   56,   97,   58,   59,
   60,  159,   61,   62,   65,    0,    0,    0,    0,   63,
    0,   66,    0,    0,    0,    0,   64,   67,   67,   67,
   67,   67,   67,    0,    0,    0,    0,    0,    0,   65,
   55,   56,   97,   58,   59,   60,   66,   61,   62,    0,
    0,   64,  160,    0,   63,    0,    0,   65,    0,    0,
    0,    0,    0,    0,   66,    0,    0,    0,    0,   64,
    0,   55,   56,   97,   58,   59,   60,   30,   61,   62,
    0,    0,    0,    0,   30,   63,    0,    0,    0,   30,
    0,    0,    0,    0,    0,   72,   72,    0,   55,   56,
   97,   58,   59,   60,  164,   61,   62,   31,    0,    0,
    0,    0,   63,    0,   31,    0,    0,    0,    0,   31,
    0,    0,   65,    0,    0,    0,    0,    0,    0,   66,
    0,  166,    0,    0,   64,    0,    0,    0,    0,    0,
    0,    0,    0,   55,   56,   97,   58,   59,   60,  168,
   61,   62,   65,    0,    0,    0,    0,   63,    0,   66,
    0,    0,    0,    0,   64,    0,    0,    0,    0,   30,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   55,   56,   97,   58,
   59,   60,    0,   61,   62,    0,    0,    0,    0,   31,
   63,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   55,   56,
   97,   58,   59,   60,    0,   61,   62,    0,    0,    0,
    0,    0,   63,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   55,   56,   97,   58,   59,   60,    0,
   61,   62,    0,    0,    0,    0,    0,   63,    0,    0,
    0,   55,   56,   97,   58,   59,   60,    0,   61,   62,
    0,    0,    0,    0,    0,   63,    0,    0,    0,    0,
    0,   30,   30,   30,   30,   30,   30,    0,   30,   30,
    0,    0,    0,    0,    0,   30,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   31,   31,   31,   31,   31,   31,    0,   31,   31,
    0,    0,    0,    0,    0,   31,   55,   56,   57,   58,
   59,   60,    0,   61,   62,    0,    0,    0,    0,    0,
   63,   85,   85,   88,   89,   90,   91,   92,   93,    0,
    0,    0,    0,    0,    0,    0,   55,   56,   97,   58,
   59,   60,    0,   61,   62,   85,    0,    0,    0,    0,
   63,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  129,  130,  131,  132,  133,  134,  135,  136,
  137,  138,  139,  140,  141,  142,    0,  144,    0,    0,
    0,   85,    0,  147,    0,    0,    0,    0,    0,  151,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  125,   40,   32,   41,   42,   43,   44,   45,   46,   47,
   41,   41,   39,   44,   40,   91,   41,   44,   46,   44,
   58,   59,   60,   61,   62,   37,   44,   51,   59,   41,
   42,   43,   44,   45,   59,   47,   66,  259,  259,   44,
  259,   59,  263,   59,   60,   69,   58,   59,   60,   61,
   62,  273,   37,   91,   59,   93,   41,   42,   43,   44,
   45,   37,   47,   91,   44,   41,   42,   43,   44,   45,
  259,   47,   41,   58,   59,   60,   61,   62,   58,  123,
   69,   93,   58,   59,   60,   61,   62,   44,   37,  257,
   58,   29,   41,   42,   43,   44,   45,   37,   47,   37,
   59,   58,   42,   43,   44,   45,   46,   47,   93,   58,
   59,   60,   61,   62,  267,  268,  269,   93,   58,  272,
   60,   61,   62,   37,  267,  268,  269,   44,   42,   43,
   37,   45,   46,   47,  259,   42,   43,   41,   45,   46,
   47,  259,   58,   93,   93,   59,   60,   61,   62,   58,
  272,   91,  123,   60,   61,   62,   59,   37,   40,  148,
  149,   59,   42,   43,   37,   45,   46,   47,   40,   42,
   43,  160,   45,   46,   47,  164,   41,   91,   58,  259,
   60,   41,   62,   44,   91,   58,  123,   60,   41,   62,
   37,  267,  268,  269,  270,   42,   43,   37,   45,   46,
   47,   41,   42,   43,  266,   45,   46,   47,  266,   41,
   41,   91,   59,   60,   41,   62,   37,  119,   91,   49,
   60,   42,   62,   37,   34,   46,   47,   -1,   42,   43,
   37,   45,   46,   47,   -1,   -1,  274,  275,  276,  277,
  278,  279,   -1,   -1,   91,   59,   60,   -1,   62,   -1,
   37,   91,   -1,   -1,   -1,   42,   43,   70,   45,   46,
   47,   -1,  274,  275,  276,  277,  278,  279,   41,   -1,
   91,   44,   -1,   60,   -1,   62,   -1,   91,   -1,   -1,
   -1,   -1,   -1,   96,   -1,   58,   59,   -1,   61,  274,
  275,  276,  277,  278,  279,   -1,   -1,   -1,  274,  275,
  276,  277,  278,  279,   91,   -1,   93,   33,   -1,   -1,
   -1,  124,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,
   93,   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,
  279,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
   -1,   -1,  155,  156,  157,   -1,   -1,   -1,   -1,  162,
  163,   -1,   -1,   -1,  167,   -1,   41,   -1,   -1,   44,
  274,  275,  276,  277,  278,  279,   -1,  274,  275,  276,
  277,  278,  279,   58,   59,   60,   61,   62,   -1,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,  274,  275,  276,  277,  278,  279,
   -1,  274,  275,  276,  277,  278,  279,   -1,   93,   -1,
   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,
   -1,   40,   -1,   -1,   -1,   -1,   45,  274,  275,  276,
  277,  278,  279,   91,  274,  275,  276,  277,  278,  279,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,   60,   61,   62,   -1,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,
  277,  278,  279,   60,   37,   62,   -1,   91,   -1,   42,
   43,   -1,   45,   46,   47,  278,  279,   -1,   41,   -1,
   43,   44,   45,   -1,   -1,   -1,   -1,   60,   41,   62,
   43,   44,   45,   -1,   91,   58,   59,   60,   61,   62,
   -1,  257,  258,  259,  260,   58,   59,   60,   61,   62,
   -1,  267,  268,  269,   -1,   -1,   -1,   41,   91,   43,
   44,   45,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,
   93,   -1,   -1,   -1,   58,   59,   60,   61,   62,   33,
   93,   58,   59,   60,   61,   62,   40,   -1,   -1,   -1,
   -1,   45,   -1,   41,   -1,   -1,   44,   -1,   -1,  274,
  275,  276,  277,  278,  279,   41,   -1,   -1,   44,   93,
   58,   59,   60,   61,   62,   41,   93,   -1,   44,   -1,
   -1,   -1,   58,   59,   60,   61,   62,   -1,   -1,   -1,
   -1,   33,   58,   59,   60,   61,   62,   -1,   40,   -1,
   -1,   -1,   -1,   45,   41,   93,   -1,   44,  257,  258,
  259,  260,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   58,   59,   60,   61,   62,   33,   93,   -1,   -1,
   -1,  125,   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  274,  275,  276,  277,  278,  279,   93,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   41,   45,
   -1,   44,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,
  277,  278,  279,  125,   33,   58,   59,   -1,   61,   -1,
   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,
   -1,  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,
   -1,  274,  275,  276,  277,  278,  279,   -1,  125,   -1,
   93,  274,  275,  276,  277,  278,  279,   -1,   -1,   33,
   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,
   -1,   45,   -1,   -1,   -1,   -1,   -1,  123,   -1,   -1,
  274,  275,  276,  277,  278,  279,   -1,  274,  275,  276,
  277,  278,  279,  257,  258,  259,  260,  261,  262,   -1,
  264,  265,   33,   -1,   -1,   -1,  125,  271,   -1,   40,
   -1,   -1,   -1,   -1,   45,   -1,  274,  275,  276,  277,
  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,
  276,  277,  278,  279,   -1,   -1,   -1,   -1,  274,  275,
  276,  277,  278,  279,   -1,  257,  258,  259,  260,  261,
  262,  125,  264,  265,   33,   -1,   -1,   -1,   -1,  271,
   -1,   40,   -1,   -1,   -1,   -1,   45,  274,  275,  276,
  277,  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,   33,
  257,  258,  259,  260,  261,  262,   40,  264,  265,   -1,
   -1,   45,  123,   -1,  271,   -1,   -1,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,
   -1,  257,  258,  259,  260,  261,  262,   33,  264,  265,
   -1,   -1,   -1,   -1,   40,  271,   -1,   -1,   -1,   45,
   -1,   -1,   -1,   -1,   -1,  278,  279,   -1,  257,  258,
  259,  260,  261,  262,  123,  264,  265,   33,   -1,   -1,
   -1,   -1,  271,   -1,   40,   -1,   -1,   -1,   -1,   45,
   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   -1,  125,   -1,   -1,   45,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,  125,
  264,  265,   33,   -1,   -1,   -1,   -1,  271,   -1,   40,
   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,   -1,  125,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,   -1,   -1,   -1,   -1,  125,
  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,  261,  262,   -1,  264,  265,   -1,   -1,   -1,
   -1,   -1,  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,   -1,
  264,  265,   -1,   -1,   -1,   -1,   -1,  271,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   -1,  264,  265,
   -1,   -1,   -1,   -1,   -1,  271,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   -1,  264,  265,
   -1,   -1,   -1,   -1,   -1,  271,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  261,  262,   -1,  264,  265,
   -1,   -1,   -1,   -1,   -1,  271,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,   -1,   -1,   -1,   -1,   -1,
  271,   59,   60,   61,   62,   63,   64,   65,   66,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  261,  262,   -1,  264,  265,   83,   -1,   -1,   -1,   -1,
  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  100,  101,  102,  103,  104,  105,  106,  107,
  108,  109,  110,  111,  112,  113,   -1,  115,   -1,   -1,
   -1,  119,   -1,  121,   -1,   -1,   -1,   -1,   -1,  127,
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
"sentencias : sentencia",
"sentencias : sentencias sentencia",
"sentencia : RETURN expression ';'",
"sentencia : while",
"sentencia : print",
"sentencia : input",
"sentencia : if",
"sentencia : ifSimple",
"sentencia : invocationStat ';'",
"sentencia : assignment",
"invocationStat : ID '(' paramsInvocation ')'",
"invocationExpr : ID '(' paramsInvocation ')'",
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
"expression : invocationExpr",
"cast : '(' tipo ')' expression",
"while : WHILE expression ':' '{' sentencias '}'",
"print : PRINT expressiones ';'",
"input : INPUT expressiones ';'",
};

//#line 251 "../../src/parser/parser.y"

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
//#line 628 "Parser.java"
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
														 yyval = new FunDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(6), funcionType, (List<Statement>)val_peek(0));}
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
{yyval = val_peek(1); }
break;
case 10:
//#line 99 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).addAll((List<Statement>)val_peek(1)); }
break;
case 11:
//#line 102 "../../src/parser/parser.y"
{yyval = new ArrayList<Statement>(); ((List<Statement>)yyval).add((Statement)val_peek(0));}
break;
case 12:
//#line 103 "../../src/parser/parser.y"
{yyval = val_peek(1) ; ((List<Statement>)yyval).add((Statement)val_peek(0));}
break;
case 13:
//#line 106 "../../src/parser/parser.y"
{yyval = new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1) );}
break;
case 14:
//#line 107 "../../src/parser/parser.y"
{yyval = val_peek(0);}
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
{yyval = val_peek(1);}
break;
case 20:
//#line 113 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 21:
//#line 116 "../../src/parser/parser.y"
{yyval = new InvocationStat(scanner.getLine(), scanner.getColumn(),new Variable(scanner.getLine(), scanner.getColumn(),(String)val_peek(3)), (List<Expression>)val_peek(1));}
break;
case 22:
//#line 119 "../../src/parser/parser.y"
{yyval = new InvocationExpr(scanner.getLine(), scanner.getColumn(),new Variable(scanner.getLine(), scanner.getColumn(),(String)val_peek(3)), (List<Expression>)val_peek(1));}
break;
case 23:
//#line 121 "../../src/parser/parser.y"
{yyval = new ArrayList<Expression>(); ((List<Expression>)yyval).addAll((List<Expression>)val_peek(0));}
break;
case 24:
//#line 122 "../../src/parser/parser.y"
{yyval = new ArrayList<Expression>();}
break;
case 25:
//#line 124 "../../src/parser/parser.y"
{yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1));}
break;
case 26:
//#line 127 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(4),(Statement)val_peek(2),((Statement)val_peek(0)));}
break;
case 27:
//#line 128 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(6),(Statement)val_peek(4),(List<Statement>)val_peek(1));}
break;
case 28:
//#line 129 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(6),(List<Statement>)val_peek(3),((Statement) val_peek(0)));}
break;
case 29:
//#line 130 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(8),(List<Statement>)val_peek(5),(List<Statement>)val_peek(1));}
break;
case 30:
//#line 133 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(2),(List<Statement>)val_peek(0));}
break;
case 31:
//#line 134 "../../src/parser/parser.y"
{yyval = new IfStatement(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(4),(List<Statement>)val_peek(1));}
break;
case 32:
//#line 138 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 33:
//#line 139 "../../src/parser/parser.y"
{yyval = new ArrayList<VarDefinition>(); }
break;
case 34:
//#line 142 "../../src/parser/parser.y"
{yyval = new ArrayList<VarDefinition>(); ((List<VarDefinition>)yyval).add((VarDefinition)val_peek(0));}
break;
case 35:
//#line 143 "../../src/parser/parser.y"
{yyval = val_peek(2); ((List<VarDefinition>)val_peek(2)).add((VarDefinition)val_peek(0));}
break;
case 36:
//#line 146 "../../src/parser/parser.y"
{yyval = new VarDefinition(scanner.getLine(), scanner.getColumn(),(String)val_peek(2),(Type)val_peek(0));}
break;
case 37:
//#line 149 "../../src/parser/parser.y"
{yyval = new ArrayList<VarDefinition>(); ((List<VarDefinition>)yyval).addAll((List<VarDefinition>)val_peek(0));}
break;
case 38:
//#line 150 "../../src/parser/parser.y"
{yyval = val_peek(1); ((List<VarDefinition>)yyval).addAll((List<VarDefinition>)val_peek(0));}
break;
case 39:
//#line 153 "../../src/parser/parser.y"
{List<VarDefinition> variables = new ArrayList<VarDefinition>();
										  for(String ident : ((List<String>)val_peek(3))){
											 VarDefinition var = new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)ident ,(Type)val_peek(1)); 
										 	variables.add(var);
										 	}
										 yyval = variables;
										 }
break;
case 40:
//#line 162 "../../src/parser/parser.y"
{yyval = new ArrayList<String>(); ((List<String>)yyval).add((String)val_peek(0));}
break;
case 41:
//#line 163 "../../src/parser/parser.y"
{yyval = val_peek(2); 
	  									if(!((List<String>)yyval).contains((String)val_peek(0)))
	  										((List<String>)yyval).add((String)val_peek(0));
	  									else
	  										new ErrorType(scanner.getLine(), scanner.getColumn(), "duplicated variable");
	  									}
break;
case 42:
//#line 170 "../../src/parser/parser.y"
{yyval = IntType.IntTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 43:
//#line 171 "../../src/parser/parser.y"
{yyval = DoubleType.DoubleTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 44:
//#line 172 "../../src/parser/parser.y"
{yyval = CharType.CharTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 45:
//#line 174 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 46:
//#line 175 "../../src/parser/parser.y"
{yyval = new ArrayType(scanner.getLine(), scanner.getColumn(),((int)val_peek(2)),(Type)val_peek(0));}
break;
case 47:
//#line 176 "../../src/parser/parser.y"
{yyval = new RecordType(scanner.getLine(), scanner.getColumn(),(List<RecordField>)val_peek(1));}
break;
case 48:
//#line 178 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 49:
//#line 179 "../../src/parser/parser.y"
{yyval = VoidType.VoidTypeInstance(scanner.getLine(), scanner.getColumn());}
break;
case 50:
//#line 183 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 51:
//#line 184 "../../src/parser/parser.y"
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
//#line 195 "../../src/parser/parser.y"
{List<RecordField> variables = new ArrayList<RecordField>();
														 
    													 for(String ident : ((List<String>)val_peek(3))){
        													RecordField recordField = new RecordField(scanner.getLine(), scanner.getColumn(),((String) ident), (Type)val_peek(1));
        													variables.add(recordField);
        													
        												}
 														 yyval = variables;
														}
break;
case 53:
//#line 207 "../../src/parser/parser.y"
{yyval = new ArrayList<Expression>(); ((List<Expression>)yyval).add((Expression)val_peek(0));}
break;
case 54:
//#line 208 "../../src/parser/parser.y"
{yyval = val_peek(2); ((List<Expression>)yyval).add((Expression)val_peek(0));}
break;
case 55:
//#line 211 "../../src/parser/parser.y"
{yyval = new Variable(scanner.getLine(), scanner.getColumn(),(String)val_peek(0));}
break;
case 56:
//#line 212 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 57:
//#line 213 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 58:
//#line 214 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 59:
//#line 215 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 60:
//#line 216 "../../src/parser/parser.y"
{yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String) val_peek(1) , (Expression)val_peek(0));}
break;
case 61:
//#line 217 "../../src/parser/parser.y"
{yyval = new FieldAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0));}
break;
case 62:
//#line 218 "../../src/parser/parser.y"
{yyval = val_peek(1);}
break;
case 63:
//#line 219 "../../src/parser/parser.y"
{yyval = new Indexin(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1));}
break;
case 64:
//#line 220 "../../src/parser/parser.y"
{yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0));}
break;
case 65:
//#line 221 "../../src/parser/parser.y"
{yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(),(Expression)val_peek(0));}
break;
case 66:
//#line 222 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 67:
//#line 223 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 68:
//#line 224 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 69:
//#line 225 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 70:
//#line 226 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 71:
//#line 227 "../../src/parser/parser.y"
{yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 72:
//#line 228 "../../src/parser/parser.y"
{yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 73:
//#line 229 "../../src/parser/parser.y"
{yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2),(String) val_peek(1), (Expression)val_peek(0));}
break;
case 74:
//#line 230 "../../src/parser/parser.y"
{yyval = new RealLiteral(scanner.getLine(), scanner.getColumn(),(Double)val_peek(0));}
break;
case 75:
//#line 231 "../../src/parser/parser.y"
{yyval = new IntLiteral(scanner.getLine(), scanner.getColumn(),(int)val_peek(0));}
break;
case 76:
//#line 232 "../../src/parser/parser.y"
{yyval = new CharLiteral(scanner.getLine(), scanner.getColumn(),(char)val_peek(0));}
break;
case 77:
//#line 233 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 78:
//#line 234 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 79:
//#line 237 "../../src/parser/parser.y"
{yyval = new Cast(scanner.getLine(), scanner.getColumn(), (Type)val_peek(2),(Expression)val_peek(0));}
break;
case 80:
//#line 239 "../../src/parser/parser.y"
{yyval = new WhileSetatement(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (List<Statement>)val_peek(1));}
break;
case 81:
//#line 241 "../../src/parser/parser.y"
{
  									 for(Expression exp : ((List<Expression>)val_peek(1)))
        								yyval = new Write(scanner.getLine(), scanner.getColumn(), (Expression)exp);
        							 }
break;
case 82:
//#line 246 "../../src/parser/parser.y"
{for(Expression exp : ((List<Expression>)val_peek(1)))
        								yyval = new Read(scanner.getLine(), scanner.getColumn(), (Expression)exp);
        							}
break;
//#line 1140 "Parser.java"
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
