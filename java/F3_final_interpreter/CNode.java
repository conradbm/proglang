
package main;
public class CNode {
    	Cmd data;
	CNode next;
	
	public CNode(Cmd c){
		this.data = c;
		next = null;
	}
	public void setNext(CNode c){
		next = c;
		c.next = null;
	}
}
