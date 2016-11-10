//Blake Conrad
// CSCI350_FP_Blake_1b_Func_1Param_but.java

package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Main extends JFrame{
	
	static Scanner sc = new Scanner(System.in);
	public static HashMap<String, String> HM = new HashMap<String,String>();
	public static String mainExpression;
	public static Interpreter1 obj;
        
        static ArrayList <Tok> AL = new ArrayList<Tok>();
	static ArrayList<String> expressionList = new ArrayList<String>();
        static ArrayList<String> paramList = new ArrayList<String>();
        static FLL functionList2 = new FLL();
	
	public static void main(String[] args) {	
		Main m = new Main();
	}
	public Main(){
		
		//Main Panel
		JPanel mainPanel = new JPanel();
		
                //Input Text Area
                final JTextArea inputTextArea = new JTextArea(10,10);
                inputTextArea.setText("");
                inputTextArea.setEditable(true);
                
                //Output TextArea
                final JTextArea outputTextArea = new JTextArea(10,10);
                outputTextArea.setText("output\n");
                inputTextArea.setEditable(true);
                
                //button
                final JButton functionButton = new JButton("Run");
                functionButton.addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {

                        Interpreter1 obj = null;
                        /* SETUP STRINGS FOR ALL TEXT */
                        String allText = inputTextArea.getText();


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
                while(i < allText.length()){
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
                    
                    while(allText.charAt(i) != '\n') i++;
                    while(allText.charAt(i) == '\n') i++;
                    i++; //'{'

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
				Tok t = new Tok(obj.tok, obj.tokType);
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
                obj.functionList.printList();
                String variableListAsString = obj.variableList.returnAsString();
                String functionListAsString = obj.functionList.returnAsString();
                System.out.println(variableListAsString);
                System.out.println(functionListAsString);
                outputTextArea.append(variableListAsString);
                outputTextArea.append("__________\n\n");
                outputTextArea.append(functionListAsString);
                        
                } // end actionPerformed
                    
                }); //end ActionListener
                
		//Main Text Area
		//final JTextArea mainTextArea = new JTextArea();
		//mainTextArea.setText("Results Are Produced Here...");
		//mainTextArea.setEditable(true);
		
		//MenuBar
		JMenuBar menuBar = new JMenuBar();
		
		//JMenus
		JMenu setupMenu = new JMenu("Setup");
		JMenu functionsMenu = new JMenu("Actions");
		
		//JMenuItems
		JMenuItem setupItem = new JMenuItem("Setup Expression");
                
		setupItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//mainExpression = JOptionPane.showInputDialog("Enter Your Expression: ");
                            mainExpression = inputTextArea.getText();
			}
			
		});
		
		JMenuItem parseItem = new JMenuItem("Run");
                functionButton.addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {

                        Interpreter1 obj = null;
                        /* SETUP STRINGS FOR ALL TEXT */
                        String allText = inputTextArea.getText();


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
                while(i < allText.length()){
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
                    
                    while(allText.charAt(i) != '\n') i++;
                    while(allText.charAt(i) == '\n') i++;
                    i++; //'{'

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
				Tok t = new Tok(obj.tok, obj.tokType);
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
                obj.functionList.printList();
                String variableListAsString = obj.variableList.returnAsString();
                String functionListAsString = obj.functionList.returnAsString();
                System.out.println(variableListAsString);
                System.out.println(functionListAsString);
                outputTextArea.append(variableListAsString);
                outputTextArea.append("__________\n\n");
                outputTextArea.append(functionListAsString);
                        
                } // end actionPerformed
                    
                }); //end ActionListener
		//Add Components
		setupMenu.add(setupItem);
		functionsMenu.add(parseItem);
		menuBar.add(setupMenu);
		menuBar.add(functionsMenu);
		mainPanel.setLayout(new BorderLayout());
                JPanel centerPanel = new JPanel();
                centerPanel.setLayout(new BorderLayout());
                centerPanel.add(inputTextArea, BorderLayout.WEST);
                centerPanel.add(functionButton, BorderLayout.CENTER);
                centerPanel.add(outputTextArea, BorderLayout.EAST);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		this.setJMenuBar(menuBar);
		this.add(mainPanel);
		
		//Housekeeping
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
	}
	
}
