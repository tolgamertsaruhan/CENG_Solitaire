public class dllNode {
	private String name;
	private double score;
	private dllNode prev;
	private dllNode next;
	
	public dllNode(String name,double score) {
		this.name=name;
		this.score=score;
		prev=null;
		next=null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public dllNode getPrev() {
		return prev;
	}

	public void setPrev(dllNode prev) {
		this.prev = prev;
	}

	public dllNode getNext() {
		return next;
	}

	public void setNext(dllNode next) {
		this.next = next;
	}

	
}
