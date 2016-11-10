//  Interpreter.cpp

#include "Interpreter.h"

Interpreter::Interpreter()
{
    Cmd oCmd;
    oCmd.setCmdName("printx");
    oCmd.setCmdNr(1);
    pCmdLklist.insertCmd(oCmd);
    
    oCmd.setCmdName("inputx");
    oCmd.setCmdNr(2);
    pCmdLklist.insertCmd(oCmd);
    
    pCmdLklist.printCmds();        
    cout<<endl<<endl;
    
    tokenType = 0;
}

Interpreter::~Interpreter()
{  }

void Interpreter::loadFcts(double *r)
{   
   string fname1, pname1, ptype1;
   Parameter parameter1;
   
   while(strcmp(token, "endoffile")!=0)
   {
       Fct MFct;
       PLkList list1;
       
       getToken();
       
       //function name 
       fname1 = token;
       MFct.setFctName(fname1);
       cout<<"Function name: "<<MFct.getFctName()<<endl;
       system("pause");
       //'('
       
       getToken();
       
       if(fname1 != "main")
       {
           while(*token!=')')
           {
                cout<<"Parameter assignment"<<endl;
                //')'
                getToken();                
                ptype1=token;
                parameter1.setParameterType(ptype1);
                
                getToken();
                pname1=token;
                parameter1.setParameterName(pname1);
                
                getToken();
                parameter1.setParameterValue(0);
                parameter1.printParameter();
                cout<<endl;
                
                MFct.insertParameter(parameter1);   
           }
       }
       else
       {
           getToken();
       }
       //'~'
       getToken();
       //'{'
       getToken();
       //'~'
       getToken();
       //statement assignment
       getToken();
       while(*token!='}')
       {
           string statement1;
           while(*token!='~')
           {
               statement1+=token;
               statement1+=" ";
               //function name
               getToken();
           }
           //'}'
           cout<<"Statement is: "<<statement1<<endl;
           MFct.addStatement(statement1);
           getToken();
       }
       getToken();
       cout<<"Number of statements is: "<<MFct.getNrStatements()<<endl;
//     MFct.printStatements();
       pFLkList.insertFct(MFct);
       pFLkList.printFcts();
       cout<<endl<<endl;
   }
   
    cout<<"\nEnd of loadFcts()!!!"<<endl;
   
   run(r);
}

void Interpreter::run(double *r)
{   
    Fct MFct, CFct;
    Variable oVar1;
    int parameterValue, pCount;
    
    cout<<"\nStart Run()..."<<endl;
    
    MFct = pFLkList.searchFct("main");
    
    for (int i=0; i<MFct.getNrStatements(); i++)
    {
        string statement1;
        
        statement1 = MFct.getStatement(i);
        
        cout<<"\nStatement "<<i+1<<" is: "<<statement1<<endl;
        strcpy(p, MFct.getStatement(i).c_str());
        
        getToken();//func name
        
        CFct = pFLkList.searchFct(token);
        
        cout<<"Function name: "<<CFct.getFctName()<<endl;
        
        getToken();//'('
        
        pCount=0;
        
        while(*token!=')')
        {
            Parameter parameter1;
            
            getToken();//parameter value
            
            parameterValue=atoi(token);
            cout<<"Parameter value is "<<parameterValue<<endl;
            
            getToken();//',' or ')'
            
            CFct.setPValue(pCount, parameterValue);
            
            parameter1 = CFct.getParameter(pCount);
            
            oVar1.setVarName(parameter1.getParameterName());
            
            oVar1.setVarValue(parameter1.getParameterValue());
            
            pVarLklist.insertVar(oVar1);
            
            pVarLklist.printVars();
            
            pCount++;
        }
        
        for(int j=0; j<CFct.getNrStatements(); j++)
        {
            strcpy(p, CFct.getStatement(j).c_str());
            cout<<"Statement: "<<CFct.getStatement(j)<<endl;
            Stmt(r);
        }
        system("pause");
    } 
}

void Interpreter::Stmt(double *r)
{   
   getToken();
   
   if (tokenType == 'C')
   {                  
       if (cmdNrG == PRINTX)
       {
          printxStmt(r);
       }
       
       else if (cmdNrG == INPUTX)
       {
          inputxStmt(r);
       }
   }      
   else if (tokenType == 'V')
   {
          moveBackToken();  
          Asg(r);
   }   
}
void Interpreter::printxStmt(double *r)
{
    double var1Value; 
    
    getToken();
    
    var1Value = pVarLklist.searchVar (token);
    
    cout<<"\nInside printxStmt: The value of the var "<<token<<" is: "<<var1Value<<endl;
}

void Interpreter::inputxStmt (double *r)
{
     double nr1;
     string varName1;
     double varValue1;
     Variable oVar1;
     
     getToken();
     varName1 = token;
     
     cout<<"Inside inputxStmt: varName1 = "<<varName1<<endl;
     cout<<"Input a number: "<<endl;
     cin>>nr1;
     
     varValue1 = nr1;
     
     oVar1.setVarName(varName1);
     oVar1.setVarValue(varValue1);
      
     pVarLklist.insertVar (oVar1);
     pVarLklist.printVars ();
     cin.ignore();

}

void Interpreter::Asg(double *r)
{
   string varName1;
   Variable oVar1;
   
   getToken();
      
   if (tokenType == 'V')
   {
      varName1 = token;

//      cout<<"\nInside Asg: The variable is: "<<token<<endl;
                
      getToken();
      
      if (*token != '=')
      {
         cout<<"Error: incorrect input"<<endl;
         system("pause");
         exit(1);
      }
      
      E(r);
      
      oVar1.setVarName(varName1);
      oVar1.setVarValue(*r);
      
      pVarLklist.insertVar (oVar1);
      pVarLklist.printVars();
      cout<<endl;
   }

   else
   {
//      moveBackToken();
      E(r);
   }
}

void Interpreter::E(double *r)
{
   char op;
   double temp;
   
   getToken();
   
   T(r);
   
   while ((op=*token)=='+' || op=='-')
   {
       getToken();
       
       T(&temp);
       
       if (op=='+')
          *r = *r + temp;
          
       else if (op=='-')
          *r = *r - temp;          
    }
}

void Interpreter::T (double *r)
{
     char op;
     double temp;
   
   A(r);
   
   while ((op=*token)=='*' || op=='/')
   {
       getToken();
       
       A(&temp);
           
       if (op=='*')
          *r = *r * temp;
       else if (op=='/')
            *r = *r / temp;
    }     
}

void Interpreter::A(double *r)
{
   if (tokenType == 'N')
   {
      *r = atoi (token);  
      getToken();
      return;
   }

   else if (tokenType == 'V')
   {
      *r = pVarLklist.searchVar (token);  
      getToken();
      return;
   }   
}
void Interpreter::getToken()
{
    char *tmp;
    
   tokenType = 0;
   tmp=token;
   
   *tmp='\0';
   
   if (!*p) return;
   
   while (isSpaceF(*p)) ++p;
   
   if (isDelimF(*p))
   {
      tokenType = DEL;
      *tmp++=*p++;
   }
   
   else if (isDigitF(*p))
   {
      while (!isDelimF(*p))
      {
         *tmp++=*p++;
      }
      
      tokenType = NR;
   }

   else if (isLetterF(*p))
   {
      while (!isDelimF(*p))
      {
         *tmp++=*p++;
      }
      
      tokenType = STRING;
   }
   
   *tmp='\0';
   
   if (tokenType == STRING)
   {
       cmdNrG = pCmdLklist.searchCmd(token);
       
       if (cmdNrG != 0)
          tokenType = COMMAND;
       else
          tokenType = VAR;
    }
}


int Interpreter::isSpaceF(char ch)
{
    if (ch==' ')
    {
       return 1;
    }
    else 
       return 0;
}            

int Interpreter::isLetterF(char ch)
{
    if (((ch>='A')&&(ch<='Z'))||((ch>='a')&&(ch<='z')))
    {
        return 1;
    }
    else
        return 0;
}     

int Interpreter::isDigitF(char ch)
{
    if ((ch>='0')&&(ch<='9'))
    {
        return 1;
    }
    else
        return 0;
}       

int Interpreter::isDelimF(char ch) 
{   
   if ((ch=='+')||(ch=='-')||(ch=='*')||(ch=='/')||(ch=='>')||(ch=='<')||(ch==' ')||(ch=='=')||(ch=='\r')||(ch==0)||
       (ch=='(')||(ch==')')||(ch=='{')||(ch=='}')||(ch=='~')||(ch==','))
       return 1;
   else 
       return 0;
}
   
////////////  moveBackToken  ///////////////
void Interpreter::moveBackToken()
{
     char *t;
     
     t = token;
     
     for (; *t; t++)
     {
         p--;
     }
}
