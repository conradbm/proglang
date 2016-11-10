//  Variable.h

#ifndef Variable_h
#define Variable_h

#include "Globals.h"

class Variable : public Globals
{
      public: 
               string varName;
               double varValue;
               
      public:
             Variable();
             
             void setVarName(string varName1);
             string getVarName();
             
             void setVarValue(double varValue1);
             double getVarValue();
};

#endif             
