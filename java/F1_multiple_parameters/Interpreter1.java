
package main;


import java.util.*;
import javax.swing.JOptionPane;
import static main.Main.functionList2;

public class Interpreter1 {
	
	/*******************/
	/*** PRIVATE DATA***/
	/*******************/
	
	// FOR TOKENS
	String exp;
	String tok;
	String tokType;
        String tmpParam;
	int expMaxLength;
	public static int i;
	public static int expIndex;
	
	// FOR RESULTS
	public static int result;
	public static int temp;
	
	// FOR STORING DATA
	public static ArrayList<T> tokenList;
	public static VLL variableList;
        public static CLL commandList;
        public static FLL functionList;
        
        // FOR COMMANDS IN OUR LANGUAGE
        Cmd printXCmd;
        Cmd squareXCmd;
        Cmd inputXCmd;
        Cmd ifXCmd;
        
        int PRINTX_ID = 1;
        int SQUAREX_ID = 2;
        int INPUTX_ID = 3;
        int IFX_ID = 4;
        
	/**************************/
	/*** PUBLIC CONSTRUCTOR ***/
	/**************************/
	
	public Interpreter1(String expression)
	{
            
            //Construct basic variables
		exp = expression;
		tok = "";
		tokType = "0";
		expMaxLength = exp.length();
		expIndex = 0;
		tokenList = new ArrayList<T>();
		variableList = new VLL();
                commandList = new CLL();
		result=0;
                
            //Construct CLL
            
                //1st Command - printx
                printXCmd = new Cmd("printx", 1);
                commandList.addCommand(printXCmd);
                
                //2nd Command - squarex
                squareXCmd = new Cmd("squarex", 2);
                commandList.addCommand(squareXCmd);
                
                //3rd Command - inputx
                inputXCmd = new Cmd("inputx", 3);
                commandList.addCommand(inputXCmd);
                
                //4th command - ifx
                ifXCmd =  new Cmd("ifx", 4);
                commandList.addCommand(ifXCmd);
                
                commandList.printList();
	}
	
	
	/****************************************************/
	/*************** CLASS METHODS **********************/
	/****************************************************/
	public void setExpression(String exp2)
	{
		this.exp = exp2;
	}
        
	/*******************************************************/
	/******* COMMANDS OUR LANGUAGE CAN IMPLEMENT ***********/
	/*******************************************************/
        
        
       public void CMDPRINTX(){
            i++;
            System.out.println("printing..." + tokenList.get(i).tokenValue + " " + tokenList.get(i).tokenType);
            if(tokenList.get(i).tokenType.equals("N")){
                
                System.err.println("The value of your literal is: " + tokenList.get(i).tokenValue);
                //store this value and pass it up the tree
            }
            else if(tokenList.get(i).tokenType.equals("V")){
                System.err.println("The value of variable: " + tokenList.get(i).tokenValue + " is: " + variableList.findVariable(tokenList.get(i).tokenValue));
                //store this value and pass it up the tree
            }
        }
       public int CMDSQUAREX(){
           i++;
           int square = 0;
           if(tokenList.get(i).tokenType.equals("N")){
               int temp = Integer.parseInt(tokenList.get(i).tokenValue);
               square = temp * temp;
           }
           else if(tokenList.get(i).tokenType.equals("V")){
               int temp = variableList.findVariable(tokenList.get(i).tokenValue);
               square = temp * temp;
           }
           return square;
       }
       
       public void CMDINPUTX(){
          System.out.println("INSIDE CMDINPUTX()");
          i++;
          String dynamic_name;
          int dynamic_value;
          
          Scanner sc = new Scanner(System.in);
          System.out.println("What do you want the value of " + tokenList.get(i).tokenValue + " to be?");
          dynamic_value = sc.nextInt();
          dynamic_name = tokenList.get(i).tokenValue;
          
          Variable v_tmp = new Variable(dynamic_name, dynamic_value);
                      
          //System.out.println("VTMP: "+ dynamic_name + "," + dynamic_value);
          variableList.addVariable(v_tmp);
       }
        
       public void CMDIFX(){
           System.out.println("INSIDE OF CMDIFX()");
           for(T t : tokenList){
               System.out.println(t.tokenValue + " " + t.tokenType);
           }
           i++; // ifx
           int val1 = variableList.findVariable(tokenList.get(i).tokenValue);
           i++; // x1
           String inequality = tokenList.get(i).tokenValue;
           i++; // < or >
           int val2 = variableList.findVariable(tokenList.get(i).tokenValue);
           i++; // x2
           
           System.out.println("val1:" + val1 + " inequality " + inequality + " val2:" + val2);
           
           if(inequality.equals(">")){
               if(val1 > val2){
                   //execute the rest
                   System.out.println("> IS TRUE");
                   if(tokenList.get(i).tokenType == "C"){
                       i++;
            if(tokenList.get(i).tokenType.equals("N")){
                
                System.err.println("The value of your literal is: " + tokenList.get(i).tokenValue);
                //store this value and pass it up the tree
            }
            else if(tokenList.get(i).tokenType.equals("V")){
                System.err.println("The value of variable: " + tokenList.get(i).tokenValue + " is: " + variableList.findVariable(tokenList.get(i).tokenValue));
                //store this value and pass it up the tree
            }
                   }
                   //Stmt2(i);
               }
           }
           
           else if(inequality.equals("<")){
               if(val1 < val2){
                   //execute the rest
                   System.out.println("< IS TRUE");
                   if(tokenList.get(i).tokenType == "C"){
                       i++;
            if(tokenList.get(i).tokenType.equals("N")){
                
                System.err.println("The value of your literal is: " + tokenList.get(i).tokenValue);
                //store this value and pass it up the tree
            }
            else if(tokenList.get(i).tokenType.equals("V")){
                System.err.println("The value of variable: " + tokenList.get(i).tokenValue + " is: " + variableList.findVariable(tokenList.get(i).tokenValue));
                //store this value and pass it up the tree
            }
                   }
               }
           }
           else{
               System.out.println("Not valid syntax!");
           }

           System.out.println("LEAVING CMDIFX()");
       }
	/**********************************************/
	/******* CLASS METHODS FOR GRAMMAR ***********/
	/**********************************************/
	
       public void Funct(){

           i=0;
           if(tokenList.get(i).tokenType == "F"){
               
               /* SET UP VARIABLE LIST FOR MULTIPLE OR SINGLE PARAMS */
               
               //every func has a param, every param has an arraylist of varNameList and typeList
               //every token holds a param called ps, every param has an arraylist of varNameList and typeList
               
               Func currFunc = functionList.findFunction(tokenList.get(i).tokenValue);
               
               int l = 0;
               for(String s : currFunc.funcParam.varNameList){ //for a1 and a2
                    int maybe = variableList.findVariable(tokenList.get(i).ps.varNameList.get(l)); //find x? find y?
                    if(maybe == 0){ //no find x/y -> value=literalVal, make v = (a1/a2, literalVal);
                            currFunc.funcParam.value = Integer.parseInt(tokenList.get(i).ps.varNameList.get(l));
                            Variable v = new Variable(currFunc.funcParam.varNameList.get(l), currFunc.funcParam.value);
                            variableList.addVariable(v);
                    }
                    else{ // yes found x/y! v = (a1/a2, value_of_x/y);
                        Variable v2 = new Variable(currFunc.funcParam.varNameList.get(l), variableList.findVariable(tokenList.get(i).ps.varNameList.get(l)));
                        variableList.addVariable(v2);
                    }
                    l++;
               }
               
               /* BEGIN EVALUATING EVERY EXPRESSION IN THE CURRENT FUNCTION */
               
               //for every expression in that functions expressionlist
                for(int j = 0; j < currFunc.statementList.size(); j++){ //expressions in main
                    ArrayList<T> AL = new ArrayList<>();
                    this.setExpression(currFunc.statementList.get(j));
                    this.expIndex = 0;
                    this.expMaxLength = currFunc.statementList.get(j).length();
			for(int k = 0; this.expIndex <= this.expMaxLength; k++){
                            //this should tokenize the first line of func1(int a1, int a2, ...)
				this.getToken();
				T t = new T(this.tok, this.tokType);
                                if(t.tokenType.equals("F")){
                                    //for any func2 inside of func1
                                    t.tokenParam = this.tmpParam;
                                    t.ps = new Param();
                                    t.ps.inlineParams(t.tokenParam);
                                }
				AL.add(t);
				if(t.tokenValue.equals("")){
					AL.remove(k);
				}
                        }
                        
			currFunc.tokenList.addAll(AL);
			this.tokenList = currFunc.tokenList;
			this.Funct();
                        
			// clean up
			currFunc.tokenList.clear();
                        this.tokenList.clear();
                }
           }
           else{
               Stmt();
           }
       }
       
        public void Stmt(){
            i = 0;
            if(tokenList.get(i).tokenType.equals("C")){
                int CmdNrG = commandList.findCommand(tokenList.get(i).tokenValue);
                if(CmdNrG == PRINTX_ID){
                    CMDPRINTX();
                }
                else if(CmdNrG == INPUTX_ID){
                    CMDINPUTX();
                }
                else if(CmdNrG == IFX_ID){
                    //our first token is ifx
                    System.out.println("OUR FIRST TOKEN IS IFX!!!");
                    CMDIFX();
                }
            }
            else if(tokenList.get(i).tokenType.equals("V")){
                Asg();
            }
        }
        
        public int Stmt1(){
            System.out.println("INSIDE STMT1()");
            int CmdNrG = commandList.findCommand(tokenList.get(i).tokenValue);
            if(CmdNrG == SQUAREX_ID){
                return CMDSQUAREX();
            }
            else return -1;
        }
        
	public void Asg(){
		i=0;
		
		if(tokenList.get(i).tokenType == "V"){
			i++;
			if(tokenList.get(i).tokenValue.equals("=")){
				i++;
				E();
				Variable v = new Variable(tokenList.get(0).tokenValue, result);
				//System.out.println("v.name: " + v.variableName + "\nv.value: " + v.variableValue);
				variableList.addVariable(v);
				//variableList.printList();
			}
			else{
				System.out.println("Illegal Assignment..");
				System.exit(0);
			}
		}
	}
	
	public void E(){
		T1();
		if(i >= tokenList.size()){
			return;
		}
		while(tokenList.get(i).tokenValue.equals("+") || tokenList.get(i).tokenValue.equals("-")){

			if(tokenList.get(i).tokenValue.equals("+")){
				i++;
				T2();
				result = result + temp;
				if(i >= tokenList.size()){
					break;
				}
			}
			else if(tokenList.get(i).tokenValue.equals("-")){
				i++;
				T2();
				result = result - temp;
				if(i >= tokenList.size()){
					break;
				}
			}
		}
	}
        
	private void T1(){
		A1();
		if(i >= tokenList.size()){
			return;
		}
		while(tokenList.get(i).tokenValue.equals("*") || tokenList.get(i).tokenValue.equals("/")){

			if(tokenList.get(i).tokenValue.equals("*")){
				i++;
				A2();
				result = result * temp;
				if(i >= tokenList.size()){
					break;
				}
			}
			else if(tokenList.get(i).tokenValue.equals("/")){
				i++;
				A2();
				result = result / temp;
				if(i >= tokenList.size()){
					break;
				}
                        }
			
		}
	}
	
	private void T2(){

		A2();
		if(i >= tokenList.size()){
			return;
		}
		while(tokenList.get(i).tokenValue.equals("*") || tokenList.get(i).tokenValue.equals("/")){

			if(tokenList.get(i).tokenValue.equals("*")){
				i++;
				A3(); //multiplication
				if(i >= tokenList.size()){
					break;
				}
			}
			else if(tokenList.get(i).tokenValue.equals("/")){
				i++;
				A4(); //division
				if(i >= tokenList.size()){
					break;
				}
			}
		}
	}
        
	private void A1(){//set up result
 
		if(tokenList.get(i).tokenType == "N"){ 
		  	int val = Integer.parseInt(tokenList.get(i).tokenValue);
		  	result = val;
		  	i++;
		  }
		else if(tokenList.get(i).tokenType == "V"){
			int val = variableList.findVariable(tokenList.get(i).tokenValue);
			result = val;
			i++;
		}
                else if(tokenList.get(i).tokenType == "C"){
                    int maybe = Stmt1();
                    if(maybe != -1){
                        result = maybe;
                        i++;
                    }
                    else{
                        System.err.print("Error: You're command does not have that functionality.\n");
                    }
                }
	}
	
	private void A2(){ // set up temp
		if(tokenList.get(i).tokenType == "N"){ 
			int val = Integer.parseInt(tokenList.get(i).tokenValue);
		  	temp = val;
		  	i++;
		  }
		else if(tokenList.get(i).tokenType == "V"){
			int val = variableList.findVariable(tokenList.get(i).tokenValue);
			temp = val;
			i++;
		}
                else if(tokenList.get(i).tokenType == "C"){
                    int maybe = Stmt1();
                    if(maybe != -1){
                        temp = maybe;
                        i++;
                    }
                    else{
                        System.err.print("Error: You're command does not have that functionality.\n");
                    }
                }
	}
        
	private void A3(){ //multiplication
		System.out.println("Inside A3");

		if(tokenList.get(i).tokenType == "N"){
			int t1 = Integer.parseInt(tokenList.get(i).tokenValue);
			temp = temp * t1;
			i++;
		}
		else if(tokenList.get(i).tokenType == "V"){
			int val = variableList.findVariable(tokenList.get(i).tokenValue);
			temp = temp * val;
			i++;
		}
                else if(tokenList.get(i).tokenType == "C"){
                    int maybe = Stmt1();
                    if(maybe != -1){
                        temp = maybe;
                        i++;
                    }
                    else{
                        System.err.print("Error: You're command does not have that functionality.\n");
                    }
                }
	}
	
	private void A4(){ //division
		System.out.println("Inside A4");
		
		if(tokenList.get(i).tokenType == "N"){
			int t1 = Integer.parseInt(tokenList.get(i).tokenValue);
			temp = temp / t1;
			i++;
		}
		else if(tokenList.get(i).tokenType == "V"){
			int val = variableList.findVariable(tokenList.get(i).tokenValue);
			temp = temp / val;
			i++;
		}
                else if(tokenList.get(i).tokenType == "C"){
                    int maybe = Stmt1();
                    if(maybe != -1){
                        temp = maybe;
                        i++;
                    }
                    else{
                        System.err.print("Error: You're command does not have that functionality.\n");
                    }
                }
	}

        
        /********************************************************/
	/******* HELPER FUNCTIONS TO DETERMINE TOKENS ***********/
	/********************************************************/
        
	public static boolean isNumber(String string) {
	    try {
	        Long.parseLong(string);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	

        private boolean isAlpha(char ch) {

            if(((ch >= 'A')&&(ch <= 'Z'))||((ch >= 'a')&&(ch <= 'z'))){
                return true;
            }
            else{
                return false;
            }
        }

        private boolean isDelim(char ch) {

            if((ch == '+')||(ch == '-')||(ch == '*')||(ch == '/')||(ch == '=')||(ch == '<')||(ch == '>')||(ch == ' ')||(ch == '\r')){
		return true;
            }
	
            else{
		return false;
            }
        }

        private boolean isDigit(char ch) {

            if((ch >= '0') && (ch <= '9')){
		return true;
            }
            else{
		return false;
            }
        }

        private boolean isSpace(char ch) {
            if(ch == ' '){
		return true;
            }
            else{
		return false;
            }
        }
       
        /******************************/
	/******* GET TOKENS ***********/
	/******************************/
        
    public void getToken(){ //good for tokenizing 1 line or statement
            String DEL = "D";
            String NR = "N";
            String VAR = "V";
            String STR = "S";
            String CMD = "C";
            String FUNC = "F";

            tokType = "0";
            tmpParam = "";
            tok = "";
            
            if(exp == "") return;

            try {
                    while(isSpace(exp.charAt(expIndex)))
                    {
                            expIndex++;
                    }
            } catch (StringIndexOutOfBoundsException e) { //Last char is a space
                    tok = "";
                    tokType = "";
                    expIndex++;
                    return;
            }

            //scan the whole exp
            if(exp.contains("(")){
                while(exp.charAt(expIndex) != '('){
                    if(exp.charAt(expIndex) == ' ') expIndex++;
                    else{
                        tok+=exp.charAt(expIndex);
                        expIndex++;
                    }
                }
                System.out.println(tok);
                expIndex++;
                while(exp.charAt(expIndex) != ')'){
                    System.out.println(exp.charAt(expIndex));
                    tmpParam+=exp.charAt(expIndex);
                    //
                    expIndex++;
                }
                expIndex++;
                tokType = FUNC;
                return;
            }
            
            
            
            else if(exp.charAt(expIndex) == '+' ||exp.charAt(expIndex) == '-' ||exp.charAt(expIndex) == '*' ||exp.charAt(expIndex) == '=' ||exp.charAt(expIndex) == '/' || exp.charAt(expIndex) == '<' || exp.charAt(expIndex) == '>')
            {
                            tokType = DEL;
                            tok = Character.toString(exp.charAt(expIndex));
                            expIndex++;
            }


            //IF ITS A DIGIT
            else if(isDigit(exp.charAt(expIndex))){
                    int i = 0;
                    try{
                            while(!isDelim(exp.charAt(expIndex) ) ){
                                    try {
                                            tok += Character.toString(exp.charAt(expIndex));
                                            expIndex++;
                                            i++;

                            } catch (StringIndexOutOfBoundsException e) {
                                    expIndex--;
                                    tok =  Character.toString(exp.charAt(expIndex));
                                    expIndex++;
                                    tokType = NR;
                                    break;
                            }
                    }
                    }catch(StringIndexOutOfBoundsException e){ 

                            if(i > 1){
                                    expMaxLength = expMaxLength - i;
                            }
                            else{
                                    expIndex--;
                                    tok =  Character.toString(exp.charAt(expIndex));
                                    expIndex++;
                            }
                    }
                    tokType = NR;
            }

            //IF ITS A VARIABLE
            else if(isAlpha(exp.charAt(expIndex))){
                    int i = 0;
                    try{ 
                            while(!isDelim(exp.charAt(expIndex) ) ){
                                    try {
                                            tok += Character.toString(exp.charAt(expIndex));
                                            expIndex++;
                                            i++;

                                    } catch (StringIndexOutOfBoundsException e) {
                                    expIndex--;
                                    tok =  Character.toString(exp.charAt(expIndex));
                                    expIndex++;
                                    tokType = STR;
                                    break;
                                    }


                            }
                    }catch(StringIndexOutOfBoundsException e){ 
                            if(i > 1){
                                    expMaxLength = expMaxLength - i;
                            }
                            else{
                                    expMaxLength--;
                                    expIndex--;
                                    tok = Character.toString(exp.charAt(expIndex));	
                                    expIndex++;
                            }
                    }
                    tokType = STR;
            }

        if(tokType.equals("S")){
            int maybe = commandList.findCommand(tok);
            if(maybe != 0){
                tokType = CMD;
            }
            else{
                tokType = VAR;
            }
        }
    }
}

