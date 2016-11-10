//  Globals.h

#ifndef Globals_h
#define Globals_h

#include <iostream>
#include <string>
using namespace std;
 
class Globals
{
   public:
   	    char DEL, NR, VAR, STRING, COMMAND;
   	    int PRINTX, INPUTX;
        char *p;
        char token[200];
        char tokenType;
        int cmdNrG;

   public:
        Globals();
};  

#endif
