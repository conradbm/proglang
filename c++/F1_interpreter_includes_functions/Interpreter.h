//  Interpreter.h

#ifndef Interpreter_h
#define Interpreter_h

#include "Globals.h"
#include "CLkList.h"
#include "VLkList.h"
#include "PLkList.h"
#include "FLkList.h"

#include <cstring>  // needed for 'strcmp'
#include <cstdlib>  // needed for 'atoi'

class Interpreter : public Globals
{       
   public:
          VLkList pVarLklist;
          CLkList pCmdLklist;
          FLkList pFLkList;
                     
   public:
        Interpreter();
        ~Interpreter();

        void loadFcts(double *r);
        void run(double *r);
        void Stmt (double *r);        
        void printxStmt (double *r);  
        void inputxStmt (double *r);
        void Asg (double *r);        
        void E (double *r);
        void T (double *r);
        void A (double *r);
        void getToken();
        int isSpaceF(char ch);
        int isLetterF(char ch);
        int isDigitF(char ch);
        int isDelimF(char ch);
        void moveBackToken();
};

#endif
