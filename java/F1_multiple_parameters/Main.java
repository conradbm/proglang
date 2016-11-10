/*
 * FP_CSCI350_Blake_Interp_functions_NoGUI_1a.java
 * Blake Conrad
 * INSTRUCTIONS:
    All input files should be under the general restriction of:
main()
{
...
...
f1(constant / variable)
}
f1(int temp)
{
...
...
printx temp
}
____________
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	static ArrayList <T> AL = new ArrayList<T>();
	static ArrayList<String> expressionList = new ArrayList<String>();
        static ArrayList<String> paramList = new ArrayList<String>();
        static FLL functionList2 = new FLL();
        
        public static void main(String[] args) throws FileNotFoundException {

		Interpreter1 obj = null;
                
                /* GET INPUT FROM FILE */
                File inputFile = new File("testFile.txt");
                Scanner inputScanner = new Scanner(inputFile);
                StringBuilder sb = new StringBuilder();
                while(inputScanner.hasNext()){
                    sb.append(inputScanner.nextLine());
                    sb.append("\n");
                }
                
                /* SETUP STRINGS FOR ALL TEXT */
                String allText = sb.toString();
                String tmpExp = "";
                String mainFuncName ="";
                int i=0;
                
                //make a func object and sets its expressionList property
                while(allText.charAt(i) == '\n') i++;
                while(allText.charAt(i) != '('){
                    mainFuncName+=allText.charAt(i);
                    i++;
                }
                
                System.out.println(mainFuncName);
                System.out.println(allText.charAt(i)); //output: (
                
                /* old--
                i++; // '('
                i++; // ')'
                i++; // '\n'
                i++; // '{'
                i++; // '\n'
                * --old*/
                
                //new--
                while(allText.charAt(i) != '{'){
                    i++;
                }
                 // '{'
                while(allText.charAt(i) != '\n'){
                    i++;
                }
                while(allText.charAt(i) == '\n') i++;
                //--new
                
                while(allText.charAt(i) != '}'){ //for main func
                    if(allText.charAt(i) == '\n'){
                         //System.out.println("a" + tmpExp + "a");
                         if(!tmpExp.equals("")){
                            expressionList.add(tmpExp);
                            tmpExp="";
                         }
                    }
                    else{
                        tmpExp+=allText.charAt(i);
                    }
                    i++;
                }
                
                Func mainFunc = new Func(mainFuncName, expressionList.size(), expressionList);
                functionList2.addFunction(mainFunc);
                expressionList.clear();                
                
                /* General approach for functions other than main */
                 //System.out.println("a" + allText.charAt(i) + "a");
                i++; // '}'
                
                while(allText.charAt(i) == '\n') i++; //from \n -> func1
                 
                String tmpName="";
                String tmpParam="";

                //i++; // '\n' --old
                while(i < sb.length()){
                    while(allText.charAt(i) != '('){
                        if(allText.charAt(i) == '\n') i++;
                        else{
                            tmpName+=allText.charAt(i);
                            i++;
                        }
                        
                    }
                    i++; //'('
                    while(allText.charAt(i) != ')'){
                        tmpParam+=allText.charAt(i); //holds all even if multiples ex) "int a1, int a2"
                        i++;
                    }
                    //i++; // ')'
                    //i++; // '\n'
                    
                    while(allText.charAt(i) != '\n') i++;
                    while(allText.charAt(i) == '\n') i++;
                    i++; //'{'
                    
                    //i++; // '{'
                    while(allText.charAt(i) != '}'){
                        if(allText.charAt(i) == '\n'){
                            
                            if(tmpExp != ""){
                                expressionList.add(tmpExp);
                            }
                            tmpExp="";
                        }
                        else{
                            tmpExp+=allText.charAt(i);
                        }
                        i++;
                    }
                    //if its not the last function
                    if(allText.charAt(i) == '\n'){
                        do{
                            i++;
                        }while(allText.charAt(i) != '\n');
                    }

                    i++; // '}'
                    i++; // '\n'

                    Func tmpFunc = new Func(tmpName, expressionList.size(), expressionList);
                    Param paraObj = new Param(tmpParam);

                    tmpFunc.setParam(paraObj);
                    functionList2.addFunction(tmpFunc);
                    tmpParam="";
                    tmpName="";
                    expressionList.clear();
                }

                /* keep for debugging
                functionList2.printList();
                Func yo = functionList2.findFunction("func1");
                System.out.println("allNames:" + yo.funcParam.varNameList.toString());
                System.out.println("allTypes:" + yo.funcParam.typeList.toString());
                */
                 
                 // keep for fun
                 //StringTokenizer st = new StringTokenizer(allText,"(){}");
                
                for(int j = 0; j < mainFunc.statementList.size(); j++){ //expressions in main
                        if(obj == null){
                            obj = new Interpreter1(mainFunc.statementList.get(j));
                            obj.functionList = functionList2;
                        }
			else{ 
				obj.setExpression(mainFunc.statementList.get(j));
				obj.expIndex = 0;
				obj.expMaxLength = obj.exp.length();
			}
                        
			for(int k = 0; obj.expIndex <= obj.exp.length(); k++){
				obj.getToken();
				T t = new T(obj.tok, obj.tokType);
                                if(t.tokenType.equals("F")){
                                    //System.out.println("IT IS A FUNCTION!!!" + obj.tmpParam);
                                    t.tokenParam = obj.tmpParam;
                                    t.ps = new Param();
                                    t.ps.inlineParams(t.tokenParam);
                                    
                                }
				AL.add(t);
				if(t.tokenValue.equals("")){
					AL.remove(k);
				}
                        }
                        
			obj.tokenList.addAll(AL);
			
			obj.Funct();
			
                        //obj.variableList.printList();
                        
			// clean up
			obj.tokenList.clear();
			AL.clear();
                }
                obj.variableList.printList();
                String functionListString = obj.functionList.returnAsString();
                System.out.println(functionListString);

                //next up is part 1b and 1c, should not be difficult considering I have snagged the output
                //also consider multiple parameters
                
                //So for part B, I need to 1. add textAreaI/O and button
                    //if you click the button, parse the text as a string called "allText",
                        //use "allText" to construct main and others functions,
                
                //!! the only trick is that if PRINTX is called just disbatch a JDialog box
                //!! when variableList.printList() gets called, append that output into the textOutputArea
	}

}




