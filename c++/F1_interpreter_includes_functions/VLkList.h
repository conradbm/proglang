//  VLkList.h

#ifndef VLkList_h
#define VLkList_h

#include "VNode.h"


class VLkList
{
    private:
            VNode *pl;
            
    public:
           VLkList();
           ~VLkList();
           
           void insertVar (Variable variable1);
           double searchVar (string varName1);
           void printVars();
           
};

#endif
