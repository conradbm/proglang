
package main;

public class FNode {
        Func data;
	FNode next;
	
	public FNode(Func c){
		this.data = c;
		next = null;
	}
	public void setNext(FNode c){
		next = c;
		c.next = null;
	}
}
