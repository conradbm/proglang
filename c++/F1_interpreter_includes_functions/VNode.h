//  VNode.h

#ifndef VNode_h
#define VNode_h
 
#include "Variable.h"

class VNode
{
      public:
             Variable data;
             VNode *next;
             
      public:
             VNode();
             ~VNode();
};

#endif
