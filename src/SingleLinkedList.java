
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class SingleLinkedList {
	
	private Node head;
	
	public void add(Object data) {
		Node newNode=new Node(data);
		
		if(head==null)
			head=newNode;
		else {
			Node temp=head;
			while(temp.getLink()!=null) {
				   temp=temp.getLink();
			}
			temp.setLink(newNode);
		}
	}
	
	public int size() {
		if(head==null)
			return 0;
		else {
			int count=0;
			Node temp=head;
			while(temp!=null) {
				temp=temp.getLink();
				count++;
			}
			return count;
		}
	}
	
	public int printBoxElement() {
		if(head==null) {
			return -1;
		}
		else if(head.getLink()==null) {
			int tempData=(int)head.getData();
			head=null;
			return tempData;	
		}
		else {
			int tempData=(int)head.getData();
			head=head.getLink();
			return tempData;
			}
	}
	
	
	public boolean search(Object data) {
		if(head==null) {
			System.out.println("List is empty");
			return false;
		}
		else {
			Node temp=head;
			while(temp!=null) {
				if((Integer)temp.getData()==(Integer)data)
					return true;
				temp=temp.getLink();
			}
			return false;
		}
	}
	
	public void oneElementDelete(Object data) {
		boolean flag = false;
		
		if((Integer) head.getData() == (Integer) data) {
			head = head.getLink();
			flag = true;
		}
		
		Node previous = null;
		Node temp = head;
		
		while(temp != null && flag == false) {
			if((Integer) temp.getData() == (Integer) data) {
				previous.setLink(temp.getLink());
				temp = previous;
				break;
			}
			
			previous = temp;
			temp = temp.getLink();
		}
	}

}
