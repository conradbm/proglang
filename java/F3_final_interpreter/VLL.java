package main;

public class VLL {
	
    VNode head;
    public int size;
    
    public VLL(){
	head = null;
    }
	
    public void addVariable(Variable data)
    {
    	if(head == null){
    		head = new VNode(data);   		
    	}
    	else{
    		VNode temp = new VNode(data);
    		VNode current = head;
    		while (current.next != null) {
    			current = current.next;
    		}
    		current.next = temp;
    		size++;
    	}
    }
    
    public int findVariable(String name){
    	
    	VNode current = head;
    	while(current!=null){
    		if(current.data.variableName.equals(name)){
    			return current.data.variableValue;
    		}
    		current = current.next;
    	}
		return 0;
    }
	
    public void printList() {
        VNode current = head;
        while (current != null) {
            System.err.print("[" + current.data.variableName + "," + current.data.variableValue + "]-->");;
            current = current.next;
        }
        System.out.println();
    }
    
    public String returnAsString(){
        VNode current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
        	sb.append("[" + current.data.variableName + "," + current.data.variableValue + "]-->");
        	sb.append("\n");
            current = current.next;
        }
        System.out.println();
        return sb.toString();
    }
    
	
    public int size(){
        return size;
    }	
}
