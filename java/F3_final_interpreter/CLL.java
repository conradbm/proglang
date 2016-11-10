package main;

public class CLL {
	
    CNode head;
    public int size;
    
    public CLL(){
	head = null;
    }
	
    public void addCommand(Cmd data)
    {
    	if(head == null){
    		head = new CNode(data);   		
    	}
    	else{
    		CNode temp = new CNode(data);
    		CNode current = head;
    		while (current.next != null) {
    			current = current.next;
    		}
    		current.next = temp;
    		size++;
    	}
    }
    
    public int findCommand(String name){ //if found return commandID
    	
    	CNode current = head;
    	while(current!=null){
    		if(current.data.commandName.equals(name)){
    			return current.data.commandID;
    		}
    		current = current.next;
    	}
		return 0;
    }
	
    public void printList() {
        CNode current = head;
        while (current != null) {
            System.out.print("[" + current.data.commandName + "," + current.data.commandID + "]-->");
            current = current.next;
        }
        System.out.println();
    }
  	
    public int size(){
        return size;
    }	
}
