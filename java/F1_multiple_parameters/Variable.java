package main;

public class Variable {
	String variableName;
	int variableValue;
	
	public Variable(String s, int d){
		variableName = s;
		variableValue = d;
	}
	
	public void setName(String name){
		this.variableName = name;
	}
	public void setValue(int value){
		this.variableValue = value;
	}
}
