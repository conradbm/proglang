//  Fct.h

#ifndef Fct_h
#define Fct_h

#include "Globals.h"
#include "PLkList.h"

class Fct : public Globals
{
      private: 
               string fctName;
               int maxStatements;
               int nrStatements;
               string statements[20];
               PLkList pPLkList;             
               
      public:
             Fct();
             ~Fct();
                            
             void setFctName(string FctName1);
             string getFctName();
             
             void setNrStatements(int nrStatements1);
             int getNrStatements();
             void printStatements();
             
             void addStatement(string statement1);
             string getStatement(int statementNumber);
             
             void insertParameter(Parameter parameter1);
             void setPValue(int pCount, double pvalue1);
             Parameter getParameter(int pCount);
             
             void printFct();
};

#endif 
