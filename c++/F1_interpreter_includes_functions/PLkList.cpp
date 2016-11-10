//  PLkList.cpp

#include "PLkList.h"

PLkList::PLkList()
{
   pl = NULL;
}

PLkList::~PLkList()
{ }

void PLkList::insertParameter(Parameter parameter1)
{    
     PNode *p = new PNode;
     
     p->data = parameter1;
     
     p->next = pl;
     
     pl = p;
}

double PLkList::searchParameter (string parameterName1)
{
     PNode *p;
     
     for (p=pl; p != NULL; p=p->next)
     {
         if (p->data.getParameterName() == parameterName1)
         {
            return p->data.getParameterValue();
         }
     }
     return 0;
}

void PLkList::setPValue(int pCount, double parameterValue1)
{
     PNode *p = pl;
     for(int i=0; i<pCount; i++)
     {
         p=p->next;
     }
     p->data.printParameter();
     p->data.setParameterValue(parameterValue1);
     p->data.printParameter();
}

Parameter PLkList::getParameter(int pCount)
{
     PNode *p = pl;
     for(int i=0; i<pCount; i++)
     {
         p=p->next;
     }
     return p->data;
}

void PLkList::printParameters()
{
    
    PNode *p = pl;
    
    cout<<"\nThe 'parameter-linked-list' is: "<<endl;
    
    while (p != NULL)
    {
          cout<<p->data.getParameterName()<<"->";
          p=p->next;
    }    
}
