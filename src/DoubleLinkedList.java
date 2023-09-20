import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class DoubleLinkedList {
	private dllNode head;
	private dllNode tail;
	static Console console = Enigma.getConsole("CENG SOLITAIRE", 80, 30, 20);
	public DoubleLinkedList() {
		head=null;
		tail=null;
	}
	
	public void addNode(dllNode item) {  
        //Create a new node  
        dllNode newNode = new dllNode(item.getName(),item.getScore());  
   
        //if list is empty, head and tail points to newNode  
        if(head == null) {  
            head = tail = newNode;  
        }  
        else {  
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail=newNode;
        }  
    }  
	
	public int size() {
		int count=0;
		if(head==null) {
			System.out.println("List is empty");
		}
		else {
			dllNode temp=head;
			while(temp!=null) {
				count++;
				temp=temp.getNext();
			}
		}
		return count;
	}
	

	public void addList(DoubleLinkedList listToAdd) {
        if(head == null) {  
            head = tail = listToAdd.head;  
        }  
        else {  
            listToAdd.head.setPrev(tail);
            tail.setNext(listToAdd.head);
            tail=listToAdd.tail;
        }  
	}
	
	
	public void orderedAdd(dllNode input) {
		dllNode newNode=new dllNode(input.getName(),input.getScore());
		if(head==null) {
			head=newNode;
		}
		else if((double)head.getScore()<(double)input.getScore()) {
			input.setNext(head);
			head=input;
		}
		else {
			dllNode tmp=head;
			while(tmp.getNext()!=null&&(double)tmp.getNext().getScore()>(double)input.getScore()) {
				tmp=tmp.getNext();
			}
			input.setNext(tmp.getNext());
			tmp.setNext(input);
		}
	}
	
	public void displayForScore() throws IOException {
		File file = new File("HighscoreTable2.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
        FileWriter fileWriter = new FileWriter("HighscoreTable2.txt", false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        
		if(head==null)
			System.out.println("List is empty");
		else {
			dllNode temp=head;
			int counter=1;
			String tmpString="";
			console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
			console.getTextWindow().setCursorPosition(27, 5);
			System.out.println("-----HIGH SCORE TABLE-----");
			while(counter != 11 && temp!=null) {
				tmpString=temp.getName()+" "+temp.getScore();
				console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
				console.getTextWindow().setCursorPosition(34, counter+7);
				System.out.print(tmpString);
				bWriter.write(tmpString);
				bWriter.write("\n");
				temp=temp.getNext();
				counter++;
			}
			bWriter.close();
		}
	}
	
	public void txtWriter() throws IOException {
		FileWriter fileWriter = new FileWriter("HighscoreTable.txt", false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        if(head==null)
			System.out.println("List is empty");
		else {
			dllNode temp=head;
			int counter=1;
			int score = 99999999;
			String strScore = "";
			while(temp!=null) {
				bWriter.write(temp.getName());
				bWriter.write("\n");
				score = (int) Math.round(temp.getScore());
				strScore = String.valueOf(score);
				bWriter.write(strScore);
				bWriter.write("\n");
				temp=temp.getNext();
				counter++;
				}
			bWriter.close();
			}
	}

	public dllNode getTail() {
		return tail;
	}
	
	

}
