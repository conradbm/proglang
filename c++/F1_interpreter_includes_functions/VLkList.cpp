//  VLkList.cpp

#include "VLkList.h"

VLkList::VLkList()
{
   pl = NULL;
}

VLkList::~VLkList()
{  }

void VLkList::insertVar(Variable variable1)
{
     VNode *p = new VNode;
     
     p->data = variable1;
     
     p->next = pl;
     
     pl = p;
}

double VLkList::searchVar (string varName1)
{
     VNode *p;
     
     for (p=pl; p != NULL; p=p->next)
     {
         if (p->data.getVarName() == varName1)
         {
            return p->data.getVarValue();
         }
     }

     cout<<"\nError: Inside VLkList::searchVar The "<<varName1<<" doesn't exist in the linked-list!"<<endl;
     return 0;
}

void VLkList::printVars()
{
    
    VNode *p = pl;
    
    //cout<<"\nThe 'VARIABLES-linked-list' is: "<<endl;
    
    while (p != NULL)
    {
          cout<<"["<<p->data.getVarName()<<","<<p->data.getVarValue()<<"]"<<"->";
          p=p->next;
    }    
}
