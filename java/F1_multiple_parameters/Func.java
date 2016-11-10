package main;

import java.util.ArrayList;

public class Func {
    String functionName;
    int maxStatements;
    int nrStatements;
    Param funcParam;
    ArrayList<String> statementList;
    ArrayList<T> tokenList;
    
    
    public Func(String name, int nrStatements, ArrayList<String> statementArray){
        functionName = name;
        maxStatements = 20;
        nrStatements = 0;
        statementList = new ArrayList<>();
        tokenList = new ArrayList<>();
        statementList.addAll(statementArray);
        for(String s : statementList){
            System.out.println(s);
        }
    }
    
    public void setName(String funcName){
        functionName = funcName;
    }
    
    public void setParam(Param p){
        funcParam = p;
    }

    
    /*
    class Fct : public Globals
{
      private: 
               string fctName;
               int maxStatements;
               int nrStatements;
               string statements[20];
               PLkList pPLkList;             
               
      public:
             Fct();
             ~Fct();
                            
             void setFctName(string FctName1);
             string getFctName();
             
             void setNrStatements(int nrStatements1);
             int getNrStatements();
             void printStatements();
             
             void addStatement(string statement1);
             string getStatement(int statementNumber);
             
             void insertParameter(Parameter parameter1);
             void setPValue(int pCount, double pvalue1);
             Parameter getParameter(int pCount);
             
             void printFct();
};
    */
}
