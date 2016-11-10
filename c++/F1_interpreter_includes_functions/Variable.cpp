//  Variable.cpp

#include "Variable.h"

Variable::Variable()
{ }

void Variable::setVarName(string varName1)
{
     varName=varName1;
}

string Variable::getVarName()
{
     return varName;
}

void Variable::setVarValue(double varValue1)
{
     varValue=varValue1;
}

double Variable::getVarValue()
{
     return varValue;
}
