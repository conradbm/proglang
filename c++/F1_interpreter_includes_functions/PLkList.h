//  PLkList.h

#ifndef PLkList_h
#define PLkList_h

#include "PNode.h"


class PLkList
{
    private:
            PNode *pl;
            
    public:
           PLkList();
           ~PLkList();
           
           void insertParameter (Parameter parameter1);
           double searchParameter (string parameterName1);
           void setPValue(int pCount, double parameterValue1);
           Parameter getParameter(int pCount);
           void printParameters();
};

#endif
