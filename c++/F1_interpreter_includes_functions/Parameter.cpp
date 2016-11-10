//  Parameter.cpp

#include "Parameter.h"

Parameter::Parameter()
{  }
Parameter::~Parameter()
{  }

void Parameter::setParameterName(string parameterName1)
{
     parameterName=parameterName1;
}

string Parameter::getParameterName()
{
     return parameterName;
}

void Parameter::setParameterType(string parameterType1)
{
     parameterType=parameterType1;
}

string Parameter::getParameterType()
{
     return parameterType;
}

void Parameter::setParameterValue(double parameterValue1)
{
     parameterValue=parameterValue1;
}

double Parameter::getParameterValue()
{
     return parameterValue;
}

void Parameter::printParameter()
{
     cout<<"parameterName= "<<parameterName<<endl<<"parameterType= "<<parameterType<<endl<<"parameterValue= "<<parameterValue<<endl;
}
