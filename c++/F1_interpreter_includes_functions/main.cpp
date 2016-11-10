/* Interpreter.cpp
   - we make the Interpreter recognize 'functions'

   Language is L = {x1=2+3*4, x2=2*x1+10, printx x1, inputx x2, ...}
   
   Alphabet is: {var, nr, +, -, *, printx, inputx}
   
   Grammar is: Stmt -> printxStmt / inputxStmt / Asg
               inputx -> inputx Var
               printxStmt -> printx Var
               Asg -> VAR=E / E
               E -> T+E / T-E / T
               T -> A*T / A
               A -> NR / VAR
               
   The starting letter is Stmt
   
   
 
   New Language is L = {x = y^2 + 3, y = e^(x) + z^2, z = arctan(e^(pi)), x = ln(y^2) + z^2, ...}
   New Grammar is : Asg := VAR=E
                 E   := T + E | T - E | F + E | F - E | F | T
                 F   := e^(E) | ln(E) | arctan(E)
                 T   := A*T | A
                 A   := NR | VAR
   Input: 
          main()
          {
          func1(2)
          func2(3)
          }
          func1(int a1)
          {
          x1=2*a1+3
          x2=x1+10
          printx x2
          }
          func2(int a2)
          {
          x3=3*a2+100
          printx x3
          }endoffile
*/

#include "Interpreter.h"
#include <fstream>

char *readInputFile(char *filepath, int maxSize, char *array1);

////////////////// main() ////////////////////////////////
int main()
{
    char *p1 = new char(200);
    double ans;
    Interpreter interp1;
    int maxSize=300;
    char *sourceF;
    
    cout<<"\nReading the input file...";
    
    interp1.p = readInputFile("inputFile.txt", maxSize, sourceF);
    
    cout<<"\nDone reading the input file!"<<endl<<endl;
    
    interp1.loadFcts(&ans);
    
    cout<<endl<<endl;
    system("pause");
}

char *readInputFile(char *filepath, int maxSize, char *array1)
{
     array1 = new char[maxSize];
     
     int chars_read=0;
     ifstream input(filepath);
     
     while(input)
     {
        input.get(array1[chars_read]);
        if(array1[chars_read] == '\n')
        {
          array1[chars_read] = '~';
        }
        
        ++chars_read;
     }
     array1[chars_read] = '\0';
     
     input.close();
     
     return array1;
}
