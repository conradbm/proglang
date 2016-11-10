//  FLkList.cpp

#include "FLkList.h"

FLkList::FLkList()
{
   pl = NULL;
}

FLkList::~FLkList()
{ }

void FLkList::insertFct(Fct fct1)
{
     FNode *p = new FNode;
     
     p->data = fct1;
     
     p->next = pl;
     
     pl = p;
}

Fct FLkList::searchFct(string fctName1)
{
     FNode *p;
     
     for (p=pl; p != NULL; p=p->next)
     {
         if (p->data.getFctName() == fctName1)
         {
            return p->data;
         }
     }
}

void FLkList::printFcts()
{
    
    FNode *p = pl;
    
    cout<<"\nThe 'FUNCTIONS-linked-list' is: "<<endl;
    
    while (p != NULL)
    {
          cout<<p->data.getFctName()<<"->";
          p=p->next;
    }    
}
