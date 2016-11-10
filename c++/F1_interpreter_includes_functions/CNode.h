//  CNode.h
 
#ifndef CNode_h
#define CNode_h

#include "Cmd.h"
 
class CNode
{
      public:
             Cmd data;
             CNode *next;
             
      public:
             CNode();
             ~CNode();
};

#endif
