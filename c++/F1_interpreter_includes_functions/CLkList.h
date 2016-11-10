//  CLkList.h

#ifndef CLkList_h
#define CLkList_h

#include "CNode.h"

class CLkList
{
    private:
            CNode *pl;
            
    public:
           CLkList();
           ~CLkList();
           
           void insertCmd (Cmd cmd1);
           int searchCmd (string cmdName1);
           void printCmds();
           
};

#endif
