//  FLkList.h

#ifndef FLkList_h
#define FLkList_h

#include "Fnode.h"

class FLkList
{
    private:
            FNode *pl;
            
    public:
           FLkList();
           ~FLkList();
           
           void insertFct(Fct fct1);
           Fct searchFct(string fctName1);
           void printFcts();
           
};

#endif
