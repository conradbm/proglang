// Cmd.h

#ifndef Cmd_h
#define Cmd_h

#include "Globals.h"

class Cmd : public Globals
{
      private: 
               string cmdName;
               int cmdNr;
               
      public:
               Cmd();
               ~Cmd();
                            
             void setCmdName(string cmdName1);
             string getCmdName();
             
             void setCmdNr(int cmdNr1);
             int getCmdNr();
};

#endif        
