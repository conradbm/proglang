
package main;

public class FLL {
    
    FNode head;
    public int size;
    
    public FLL(){
	head = null;
    }
	
    public void addFunction(Func data)
    {
    	if(head == null){
    		head = new FNode(data);   		
    	}
    	else{
    		FNode temp = new FNode(data);
    		FNode current = head;
    		while (current.next != null) {
    			current = current.next;
    		}
    		current.next = temp;
    		size++;
    	}
    }
    
    public Func findFunction(String name){ //if found return commandID
    	
    	FNode current = head;
    	while(current!=null){
    		if(current.data.functionName.equals(name)){
    			return current.data;
    		}
    		current = current.next;
        }
        return null;
    }
	
    public void printList() {
        FNode current = head;
        while (current != null) {
            System.out.print("[" + current.data.functionName + "]-->");
            current = current.next;
        }
        System.out.println();
    }
    public String returnAsString(){
        FNode current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            try{
        	sb.append("[" + current.data.functionName + "," + " exp count: " + current.data.statementList.size() + "," + " param count: "+ current.data.funcParam.varNameList.size() +"]-->");
        	sb.append("\n");
            current = current.next;
            }catch(Exception e){ //main func exception
        	sb.append("[" + current.data.functionName + "," + " exp count: " + current.data.statementList.size() + "," + " param count: 0 " + "]-->");
        	sb.append("\n");
            current = current.next;
            }
        }
        System.out.println();
        return sb.toString();
    }
  	
    public int size(){
        return size;
    }	
}
