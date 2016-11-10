//  Fct.cpp

#include "Fct.h"

Fct::Fct()
{  
     nrStatements=0;
     maxStatements=20;          
}
Fct::~Fct()
{  }

void Fct::setFctName(string fctName1)
{
     fctName=fctName1;
}

string Fct::getFctName()
{
     return fctName;
}

void Fct::printFct()
{
     cout<<"fctName= "<<fctName;
     //pFctParameters.printParameters();
}

void Fct::setNrStatements(int nrStatements1)
{
     nrStatements=nrStatements1;
}

int Fct::getNrStatements()
{
     return nrStatements;       
}
             
void Fct::addStatement(string statement1)
{
     if(nrStatements<maxStatements)
     {
         statements[nrStatements]=statement1;
         nrStatements++;
     }
}

void Fct::printStatements()
{
     cout<<"Statements in the function"<<endl;
     for(int i=0; i<nrStatements; i++)
         cout<<"Statement "<<i+1<<" :"<<statements[nrStatements]<<endl;
}

string Fct::getStatement(int statementNumber)
{
     return statements[statementNumber];
}

void Fct::insertParameter(Parameter parameter1)
{
     pPLkList.insertParameter(parameter1);
}

void Fct::setPValue(int pCount, double pvalue1)
{
     pPLkList.setPValue(pCount, pvalue1);
}

Parameter Fct::getParameter(int pCount1)
{
       return pPLkList.getParameter(pCount1);
}
