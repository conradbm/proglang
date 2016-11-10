//  FNode.h
 
#ifndef FNode_h
#define FNode_h

#include "Fct.h"
 
class FNode
{
      public:
             Fct data;
             FNode *next;
             
      public:
             FNode();
             ~FNode();
};

#endif
