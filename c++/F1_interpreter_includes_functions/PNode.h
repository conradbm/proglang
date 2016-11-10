//  PNode.h
 
#ifndef PNode_h
#define PNode_h

#include "Parameter.h"
 
class PNode
{
      public:
             Parameter data;
             PNode *next;
             
      public:
             PNode();
             ~PNode();
};

#endif
