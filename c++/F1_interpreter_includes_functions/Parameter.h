//  Parameter.h

#ifndef Parameter_h
#define Parameter_h

#include "Globals.h"

class Parameter
{
      private: 
               string parameterName;
               string parameterType;
               double parameterValue;
               
      public:
               Parameter();
               ~Parameter();
                            
             void setParameterName(string ParameterName1);
             string getParameterName();
             
             void setParameterType(string ParameterType1);
             string getParameterType();
             
             void setParameterValue(double parameterValue1);
             double getParameterValue();
             
             void printParameter();
};

#endif       
