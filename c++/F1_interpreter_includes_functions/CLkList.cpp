//  CLkList.cpp

#include "CLkList.h"

CLkList::CLkList()
{
   pl = NULL;
}

CLkList::~CLkList()
{ }

void CLkList::insertCmd(Cmd cmd1)
{
     CNode *p = new CNode;
     
     p->data = cmd1;
     
     p->next = pl;
     
     pl = p;
}

int CLkList::searchCmd (string cmdName1)
{
     CNode *p;
     
     for (p=pl; p != NULL; p=p->next)
     {
         if (p->data.getCmdName() == cmdName1)
         {
            return p->data.getCmdNr();
         }
     }

//     cout<<"\nError: The "<<cmdName1<<" doesn't exist in the cmd-linked-list!"<<endl;
     return 0;
}

void CLkList::printCmds()
{
    
    CNode *p = pl;
    
    cout<<"\nThe 'COMANDS-linked-list' is: "<<endl;
    
    while (p != NULL)
    {
          cout<<p->data.getCmdName()<<"->";
          p=p->next;
    }    
}
