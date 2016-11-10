package main;
public class VNode {
	Variable data;
	VNode next;
	
	public VNode(Variable v){
		this.data = v;
		next = null;
	}
	public void setNext(VNode n){
		next = n;
		n.next = null;
	}
}
