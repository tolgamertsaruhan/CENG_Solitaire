import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Solitaire {
    public KeyListener klis; 

	// ------ Standard variables for mouse and keyboard ------
	public int keypr;   // key pressed?
	public int rkey;    // key   (for press/release)
	// -------------------------------------------------------
	  
	public static int px = 49 ,py = 25;
	public static DoubleLinkedList dll=new DoubleLinkedList();
	SingleLinkedList box=new SingleLinkedList();
	MultiLinkedList mll=new MultiLinkedList();
	String playerName="";
	double finalScore=0;
	static Console console = Enigma.getConsole("CENG SOLITAIRE", 80, 30, 20);
	
	Solitaire () throws InterruptedException, NumberFormatException, IOException{
		printMenu();
		inGame();
	}
	
	public void inGame() throws InterruptedException, NumberFormatException, IOException {
		
		
		
		
		mll.addColumn("a");
		mll.addColumn("b");
		mll.addColumn("c");
		mll.addColumn("d");
		mll.addColumn("e");
		/*
		mll.addNumber("a", 1);
		mll.addNumber("a", 6);
		mll.addNumber("b", 3);
		//mll.addNumber("b",2);
		//mll.addNumber("b",2);
		mll.addNumber("c", 9);
		mll.addNumber("c", 3);
		mll.addNumber("c",2);
		mll.addNumber("c", 3);
		mll.addNumber("c",2);
		mll.addNumber("c", 3);
		mll.addNumber("c",2);
		mll.addNumber("d", 3);
		mll.addNumber("d",2);
		mll.addNumber("d", 3);
		mll.addNumber("d",10);
		mll.addNumber("e",10);
		mll.addNumber("e",10);
		mll.addNumber("e",9);
		mll.addNumber("e",9);
		mll.addNumber("e",8);
		mll.addNumber("e",7);
		mll.addNumber("e",6);
		mll.addNumber("e",5);
		mll.addNumber("e",4);
		mll.addNumber("e",3);
		mll.addNumber("e",2);
		mll.addNumber("e",1);*/
		klis=new KeyListener() {
	         public void keyTyped(KeyEvent e) {}
	         public void keyPressed(KeyEvent e) {
	            if(keypr==0) {
	               keypr=1;
	               rkey=e.getKeyCode();
	            }
	         }
	         public void keyReleased(KeyEvent e) {}
	      };
	      console.getTextWindow().addKeyListener(klis);
	      int tempPx=13;
	      int tempPy=7;
	      
	      int finishedSets = 0;
	      int transferredNumber = 0;
	      int tempPx2 = 0;
	      boolean flag = false;
	      boolean flagb = false;
	      boolean finishFlag = false;
     	  int boxNumber = 0;
     	  int tempFinishedSets = 0;
	      console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
	      int [] arr = new int[mll.elementSizeInColumn(px)];
	      
	      
	      while(finishedSets < 6) {
	          if(keypr==1) {    // if keyboard button pressed
	        	  console.getTextWindow().setCursorPosition(tempPx,tempPy);
	        	  console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
	        	  Thread.sleep(100);
	        	  System.out.println(" ");
	             if(rkey==KeyEvent.VK_LEFT) {
	            	 if (px>13) {
	            		 px = px - 9;
	            		 tempPx=px;
	            		 }
	             }
	             if(rkey==KeyEvent.VK_RIGHT) {
	            	 if (px<41) {
	            		 px = px + 9;
	            		 tempPx=px;
	            		 }
	             }
	             if(rkey==KeyEvent.VK_UP) {
	            	 if (py>=8) {
	            		 py = py - 1;
	            		 tempPy=py;
	            	 }
	             }
	             if(rkey==KeyEvent.VK_DOWN) {
	            	 if (py+1< 26) {
	            		 py = py + 1;
	            		 tempPy=py;
	            		 }
	            	 
	            	 
	             }
	             if(rkey==KeyEvent.VK_ESCAPE) {
	            	 finishFlag = true;
	            	 tempFinishedSets = finishedSets;
	            	 finishedSets = 7;
	            	 cleanConsole();
	            	 console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
	            	 console.getTextWindow().setCursorPosition(2 , 2);
	            	 System.out.println("Congratulations, you won the game ! Please enter your name : ");
		             Scanner input=new Scanner(System.in);
		             tempPx=2;
		           	 tempPy=4;
		           	 console.getTextWindow().setCursorPosition(tempPx , tempPy);
		           	 playerName=input.next();
		           	 cleanConsole();
	             }
	             if(rkey==KeyEvent.VK_B) {
	            	 
	            	 console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
	            	 console.getTextWindow().setCursorPosition(64 , 16);
	            	 if (flagb == false) {
	            		 boxNumber = box.printBoxElement();
	            		 if (boxNumber != -1) {
	            			 System.out.println(boxNumber);
	            			 console.setTextAttributes(new TextAttributes(Color.RED, Color.RED));
	    	            	 console.getTextWindow().setCursorPosition(69 , 16);
	    	            	 System.out.println(" ");
	    	            	 flagb = true;
	            		 }
	            		 else {
	            			 console.setTextAttributes(new TextAttributes(Color.WHITE, Color.WHITE));
	            			 System.out.println(boxNumber);
	            		 }
	            		 
	            	 }
	            	 else {
	            		 if (boxNumber != -1) {
	            			 System.out.println(boxNumber);
	            			 console.setTextAttributes(new TextAttributes(Color.RED, Color.RED));
	    	            	 console.getTextWindow().setCursorPosition(69 , 16);
	    	            	 System.out.println(" ");
	    	            	 
	            		 }
	            		 else {
	            			 console.setTextAttributes(new TextAttributes(Color.WHITE, Color.WHITE));
	            			 System.out.println(boxNumber);
	            		 }
	            	 }
	            	 
	            	     	 
	            	 console.getTextWindow().setCursorPosition(px, py);
	             } 
	             if(rkey==KeyEvent.VK_X) {
	            	 String columnName = "a";
	            	 if ((flag == true && flagb == false) || (flag == true && flagb == true)) {
	            		 boolean flagSize = true;
	            		 if(mll.elementSizeInColumn(px) + arr.length < 20)
	            		 {
	            		 flagSize = false;
	            		 boolean flag2 = false;
	            		 if(mll.elementSizeInColumn(px) == 0) {
	            			 if(arr[arr.length - 1] == 10 || arr.length - 1 == 1) {
	        					 flag2 = true;
	            			 }
	            			 if(flag2 == true) {
	            				mll.transferringElements(px, arr);
	     			            mll.display();
	     			            tempPx2 = 0;
	     			            transferredNumber++;
	     			            flag = false;
	     			            finishedSets = finishedSets + mll.linkedListChecker();
	     			           if(flagb == true) {
		     			        	console.setTextAttributes(new TextAttributes(Color.RED, Color.RED));
		     			        	console.getTextWindow().setCursorPosition(69 , 16);
			    	                System.out.println(" ");
		     			         }
	            			 }
	            			 for(int i = 19; i < 24; i++)
	    	            	 {
	    	            		 for(int j = 54; j < 77; j++)
	    		            	 {
	    		            		 console.getTextWindow().setCursorPosition(j , i);
	    		            		 console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
	    		            		 System.out.println(" ");
	    		            	 }
	    	            	 }
	            		 }
	            		 else {
	            			 if(mll.lastNumberFor(px) == arr[arr.length - 1] || mll.lastNumberFor(px) == (arr[arr.length - 1] + 1) || mll.lastNumberFor(px) == (arr[arr.length - 1] - 1)) {
	            				 mll.transferringElements(px, arr);
		     			         mll.display();
		     			         tempPx2 = 0;
		     			         transferredNumber++;
		     			         flag = false;
		     			         finishedSets = finishedSets + mll.linkedListChecker();
		     			         if(flagb == true) {
		     			        	console.setTextAttributes(new TextAttributes(Color.RED, Color.RED));
		     			        	console.getTextWindow().setCursorPosition(69 , 16);
			    	                System.out.println(" ");
		     			         }
		     			        for(int i = 19; i < 24; i++)
		     			        {
		   	            		 for(int j = 54; j < 77; j++)
		   		            	 {
		   		            		 console.getTextWindow().setCursorPosition(j , i);
		   		            		 console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
		   		            		 System.out.println(" ");
		   		            	 }
		     			        }
	            			 }
		            		
		            	 }
	            	 }
	            		 if(flagSize == true)
	            		 {
	            			 for(int i = 19; i < 24; i++)
	    	            	 {
	    	            		 for(int j = 54; j < 77; j++)
	    		            	 {
	    		            		 console.getTextWindow().setCursorPosition(j , i);
	    		            		 console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
	    		            		 System.out.println(" ");
	    		            	 }
	    	            	 }
	            			console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
				            console.getTextWindow().setCursorPosition(54 , 19);
				            System.out.println("Your transfer cannot");
				            console.getTextWindow().setCursorPosition(54 , 20);
				            System.out.println("be performed because");
				            console.getTextWindow().setCursorPosition(54 , 21);
				            System.out.println("it does not fit");
				            console.getTextWindow().setCursorPosition(54 , 22);
				            System.out.println("on the column.");
	            		 }
	            		 flagSize = true;
	            	 }
	            	 else if(flag == false && flagb == true) {
	            		 boolean flagSize = true;
	            		 if(mll.elementSizeInColumn(px) < 19)
	            		 {
	            	     flagSize = false;
	            		 boolean flag2 = false;
	            		 if(mll.elementSizeInColumn(px) == 0) {
	            			 if(boxNumber == 10 || boxNumber == 1) {
	        					 flag2 = true;
	            			 }
	            			 if(flag2 == true) {
	            				 console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
	            				 if(px == 22) {
         							columnName = "b";
         						}
         						else if(px == 31) {
         							columnName = "c";
         						}
         						else if(px == 40) {
         							columnName = "d";
         						}
         						else if(px == 49) {
         							columnName = "e";
         						}
	            				 mll.addNumber(columnName, boxNumber);
	     			            mll.display();
	     			            console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
	     		            	console.getTextWindow().setCursorPosition(64 , 16);
	     		            	System.out.println("  ");
	     		            	console.getTextWindow().setCursorPosition(69 , 16);
		    	                System.out.println(" ");
	     			            tempPx2 = 0;
	     			            transferredNumber++;
	     			            flagb = false;
	     			            finishedSets = finishedSets + mll.linkedListChecker();
	     			           for(int i = 19; i < 24; i++)
	     		            	 {
	     		            		 for(int j = 54; j < 77; j++)
	     			            	 {
	     			            		 console.getTextWindow().setCursorPosition(j , i);
	     			            		 console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
	     			            		 System.out.println(" ");
	     			            	 }
	     		            	 }
	            			 }
	            		 }
	            		 else {
	            			 if(mll.lastNumberFor(px) == boxNumber || mll.lastNumberFor(px) == (boxNumber + 1) || mll.lastNumberFor(px) == (boxNumber - 1)) {
	            				 
	            					console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
	            					if(px == 22) {
            							columnName = "b";
            						}
            						else if(px == 31) {
            							columnName = "c";
            						}
            						else if(px == 40) {
            							columnName = "d";
            						}
            						else if(px == 49) {
            							columnName = "e";
            						}
	            				 mll.addNumber(columnName, boxNumber);
		     			         mll.display();
		     			         console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
	     		            	 console.getTextWindow().setCursorPosition(64 , 16);
	     		            	 System.out.println("  ");
	     		            	 console.getTextWindow().setCursorPosition(69 , 16);
		    	            	 System.out.println(" ");
		     			         tempPx2 = 0;
		     			         transferredNumber++;
		     			         flagb = false;
		     			         finishedSets = finishedSets + mll.linkedListChecker();
		     			        for(int i = 19; i < 24; i++)
		     			        {
		   	            		 for(int j = 54; j < 77; j++)
		   		            	 {
		   		            		 console.getTextWindow().setCursorPosition(j , i);
		   		            		 console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
		   		            		 System.out.println(" ");
		   		            	 }
		     			        }
	            			 }
		            		
		            	 }
	            	 }
	            		 if(flagSize == true)
	            		 {
	            			 for(int i = 19; i < 24; i++)
	    	            	 {
	    	            		 for(int j = 54; j < 77; j++)
	    		            	 {
	    		            		 console.getTextWindow().setCursorPosition(j , i);
	    		            		 console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
	    		            		 System.out.println(" ");
	    		            	 }
	    	            	 }
	            			console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
				            console.getTextWindow().setCursorPosition(54 , 19);
				            System.out.println("Your transfer cannot");
				            console.getTextWindow().setCursorPosition(54 , 20);
				            System.out.println("be performed because");
				            console.getTextWindow().setCursorPosition(54 , 21);
				            System.out.println("it does not fit");
				            console.getTextWindow().setCursorPosition(54 , 22);
				            System.out.println("on the column.");
	            		 }
	            		 flagSize = true;
	            	 }
	            	 /*for(int i = 19; i < 24; i++)
	            	 {
	            		 for(int j = 54; j < 77; j++)
		            	 {
		            		 console.getTextWindow().setCursorPosition(j , i);
		            		 console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
		            		 System.out.println(" ");
		            	 }
	            	 }*/
	            	 
	             }
	             
	             if(rkey==KeyEvent.VK_Z) {
	            	 if(flag == false) {
	            		 	arr=mll.deleteElementAccordIndex(px,py);
		            	 	console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
				            mll.display();
			            	tempPx=px;
			            	tempPx2=px;
			            	tempPy=py;
			            	printGameScreen();
			            	if(flagb == true) {
			            		console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
				            	 console.getTextWindow().setCursorPosition(64 , 16);
			            		 if (boxNumber != -1) {
			            			 System.out.println(boxNumber);        	 
			            		 }
			            		 else {
			            			 console.setTextAttributes(new TextAttributes(Color.WHITE, Color.WHITE));
			            			 System.out.println(boxNumber);
			            		 }
			            	}
			            	if(arr.length > 0) {
			            		flag = true;
			            	}
			            	console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
			            	console.getTextWindow().setCursorPosition(54 , 19);
			            	System.out.println("If you want to cancel");
			            	console.getTextWindow().setCursorPosition(54 , 20);
			            	System.out.println("your selection, you");
			            	console.getTextWindow().setCursorPosition(54 , 21);
			            	System.out.println("can undo it by");
			            	console.getTextWindow().setCursorPosition(54 , 22);
			            	System.out.println("pressing 'A' key");
			            	console.getTextWindow().setCursorPosition(54 , 23);
			            	System.out.println("on your keyboard.");
	            	 }
	            	
	             }
	             
	             if(rkey==KeyEvent.VK_A) {
	            	 if(tempPx2 != 0) {
	            		 mll.transferringElements(tempPx2, arr);
     			         mll.display();
     			         tempPx2 = 0;
     			         flag = false;
     			         if(flagb == true) {
     			        	console.setTextAttributes(new TextAttributes(Color.RED, Color.RED));
     			        	console.getTextWindow().setCursorPosition(69 , 16);
	    	                System.out.println(" ");
     			         }
     			        for(int i = 19; i < 24; i++)
     			        {
   	            		 for(int j = 54; j < 77; j++)
   		            	 {
   		            		 console.getTextWindow().setCursorPosition(j , i);
   		            		 console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
   		            		 System.out.println(" ");
   		            	 }
     			        }
	            	 }
	             }
	             keypr=0;    // last action  
	             
	          }
	          
	         
	          px=tempPx;
	          py=tempPy;
	          console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
	          console.getTextWindow().setCursorPosition(px , py);
             console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
            
             mll.display();
             console.getTextWindow().setCursorPosition(70,8);
             System.out.println(transferredNumber);
             console.getTextWindow().setCursorPosition(67,10);
             System.out.println(finishedSets * 1000);
	  		  console.getTextWindow().setCursorPosition(31, 7);
	  		  //System.out.println(mll.lastNumberFor(1));
	  		  tempPx=px;
	  		  tempPy=py;
	  		  console.getTextWindow().setCursorPosition(px,py);
	  		  console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
	  		  System.out.println("_");
	  		  
	  		 if(finishedSets==5) {
	 	        console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
	            	console.getTextWindow().setCursorPosition(2 , 3);
	            	System.out.println("Congratulations, you won the game ! Please enter your name : ");
	            	Scanner input=new Scanner(System.in);
	            	tempPx=65;
	            	tempPy=3;
	            	console.getTextWindow().setCursorPosition(tempPx , tempPy);
	            	playerName=input.next();
	            	tempFinishedSets = finishedSets;
	            	break;
	 	        	
	 	        }
	  		
	  		
	       }
	        
	        if (tempFinishedSets != 0) {
	        	finalScore = (100 * tempFinishedSets) + ((tempFinishedSets * 1000) / transferredNumber);
	        }
	       
	        if(finishedSets==5 || finishFlag == true) {
	        	highScoreToList(dll);
	        	dllNode playerr=new dllNode(playerName,finalScore);
	        	dll.orderedAdd(playerr);
	        	cleanConsole();
            	dll.displayForScore();
            	dll.txtWriter();
            	console.getTextWindow().setCursorPosition(12 , 18);
            	console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
            	System.out.println("Only the top 10 users in the txt file are shown in the table.");
            	console.getTextWindow().setCursorPosition(20 , 20);
            	System.out.println("Please wait 5 seconds to exit");
            	Thread.sleep(1000);
            	console.getTextWindow().setCursorPosition(20 , 20);
            	System.out.println("Please wait 4 seconds to exit");
            	Thread.sleep(1000);
            	console.getTextWindow().setCursorPosition(20 , 20);
            	System.out.println("Please wait 3 seconds to exit");
            	Thread.sleep(1000);
            	console.getTextWindow().setCursorPosition(20 , 20);
            	System.out.println("Please wait 2 seconds to exit");
            	Thread.sleep(1000);
            	console.getTextWindow().setCursorPosition(20 , 20);
            	System.out.println("Please wait 1 seconds to exit");
            	Thread.sleep(1000);
            	System.exit(1);
	        }
	      
	        console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
	        System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                        Score = " + finalScore + "                                    ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
			System.out.println("                                                                               ");
	      
			
		}
	
	public static void printGameScreen(){
		console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                CENG SOLITAIRE                                 ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("            C-1      C-2      C-3      C-4      C-5                            ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                            TRANSFER:          ");
		System.out.println("                                                                               ");
		System.out.println("                                                            SCORE:             ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                               BOX             ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		console.setTextAttributes(new TextAttributes(Color.WHITE, Color.WHITE));
		console.getTextWindow().setCursorPosition(0 , -1);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO0OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		console.getTextWindow().setCursorPosition(0 , 3);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		console.getTextWindow().setCursorPosition(0 , 28);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		for (int i = 0; i < 29; i++) {
			console.getTextWindow().setCursorPosition(0 , i);
			System.out.println("OO");
			console.getTextWindow().setCursorPosition(77 , i);
			System.out.println("OO");
		}
		console.getTextWindow().setCursorPosition(11 , 6);
		System.out.println("OOOOO");
		console.getTextWindow().setCursorPosition(20 , 6);
		System.out.println("OOOOO");
		console.getTextWindow().setCursorPosition(29 , 6);
		System.out.println("OOOOO");
		console.getTextWindow().setCursorPosition(38 , 6);
		System.out.println("OOOOO");
		console.getTextWindow().setCursorPosition(47 , 6);
		System.out.println("OOOOO");
		for (int i = 7; i < 26; i++) {
			console.getTextWindow().setCursorPosition(11 , i);
			System.out.println("O");
			console.getTextWindow().setCursorPosition(15 , i);
			System.out.println("O");
			console.getTextWindow().setCursorPosition(20 , i);
			System.out.println("O");
			console.getTextWindow().setCursorPosition(24 , i);
			System.out.println("O");
			console.getTextWindow().setCursorPosition(29 , i);
			System.out.println("O");
			console.getTextWindow().setCursorPosition(33 , i);
			System.out.println("O");
			console.getTextWindow().setCursorPosition(38 , i);
			System.out.println("O");
			console.getTextWindow().setCursorPosition(42 , i);
			System.out.println("O");
			console.getTextWindow().setCursorPosition(47 , i);
			System.out.println("O");
			console.getTextWindow().setCursorPosition(51 , i);
			System.out.println("O");
		}
		console.getTextWindow().setCursorPosition(11 , 26);
		System.out.println("OOOOO");
		console.getTextWindow().setCursorPosition(20 , 26);
		System.out.println("OOOOO");
		console.getTextWindow().setCursorPosition(29 , 26);
		System.out.println("OOOOO");
		console.getTextWindow().setCursorPosition(38 , 26);
		System.out.println("OOOOO");
		console.getTextWindow().setCursorPosition(47 , 26);
		System.out.println("OOOOO");
		
		console.getTextWindow().setCursorPosition(61 , 15);
		System.out.println("OOOOOOO");
		console.getTextWindow().setCursorPosition(61 , 16);
		System.out.println("OO");
		console.getTextWindow().setCursorPosition(66 , 16);
		System.out.println("OO");
		console.getTextWindow().setCursorPosition(61 , 17);
		System.out.println("OOOOOOO");
	}
	public void printMenu() throws NumberFormatException, IOException, InterruptedException {
		console.getTextWindow().setCursorPosition(0 , 0);
		console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
		System.out.println("");
		System.out.println("");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                 CENG SOLITAIRE                                ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		System.out.println("                                                                               ");
		console.setTextAttributes(new TextAttributes(Color.WHITE, Color.WHITE));
		console.getTextWindow().setCursorPosition(0 , 1);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		console.getTextWindow().setCursorPosition(0 , 5);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		console.getTextWindow().setCursorPosition(24 , 8);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		console.getTextWindow().setCursorPosition(24 , 12);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		console.getTextWindow().setCursorPosition(24 , 16);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		console.getTextWindow().setCursorPosition(24 , 20);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		console.getTextWindow().setCursorPosition(24 , 24);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		for (int i = 8; i < 25; i++) {
			console.getTextWindow().setCursorPosition(24 , i);
			System.out.println("OO");
			console.getTextWindow().setCursorPosition(53 , i);
			System.out.println("OO");
		}
		console.getTextWindow().setCursorPosition(0 , 28);
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		for (int i = 1; i < 29; i++) {
			console.getTextWindow().setCursorPosition(0 , i);
			System.out.println("OO");
			console.getTextWindow().setCursorPosition(77 , i);
			System.out.println("OO");
		}
		console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
		console.getTextWindow().setCursorPosition(34 , 10);
		System.out.println("<< MENU >>");
		console.getTextWindow().setCursorPosition(33 , 14);
		System.out.println("1- Score Table ");
		console.getTextWindow().setCursorPosition(33 , 18);
		System.out.println("2- Play Game");
		console.getTextWindow().setCursorPosition(33 , 22);
		System.out.println("3- Exit Game");
		
		
		console.getTextWindow().setCursorPosition(39 , 26);
		console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
        Scanner scanner = new Scanner(System.in);
        // Get the menu selection from user
        boolean isValidMenuInput = false;
        String validMenuInputs = "123";
        String menuInput;
        do {
            menuInput = scanner.nextLine();
            if (validMenuInputs.contains(menuInput) && menuInput.length() < 2)
                isValidMenuInput = true;

            else if (!isValidMenuInput) {
            	console.getTextWindow().setCursorPosition(26 , 25);
                System.out.println("Please enter a valid option");
                console.getTextWindow().setCursorPosition(39 , 26);
                System.out.println(" ");
                console.getTextWindow().setCursorPosition(39 , 26);
            }

        } while (!isValidMenuInput);
        scanner.close();
        console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
        switch (menuInput) {
            case "1": {
            	DoubleLinkedList dll=new DoubleLinkedList();
            	highScoreToList(dll);
            	cleanConsole();
            	dll.displayForScore();
            	console.getTextWindow().setCursorPosition(12 , 18);
            	console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
            	System.out.println("Only the top 10 users in the txt file are shown in the table.");
            	console.getTextWindow().setCursorPosition(20 , 20);
            	System.out.println("Please wait 5 seconds to return to the menu");
            	Thread.sleep(1000);
            	console.getTextWindow().setCursorPosition(20 , 20);
            	System.out.println("Please wait 4 seconds to return to the menu");
            	Thread.sleep(1000);
            	console.getTextWindow().setCursorPosition(20 , 20);
            	System.out.println("Please wait 3 seconds to return to the menu");
            	Thread.sleep(1000);
            	console.getTextWindow().setCursorPosition(20 , 20);
            	System.out.println("Please wait 2 seconds to return to the menu");
            	Thread.sleep(1000);
            	console.getTextWindow().setCursorPosition(20 , 20);
            	System.out.println("Please wait 1 seconds to return to the menu");
            	Thread.sleep(1000);
            	cleanConsole();
            	printMenu();
            	break;
            }
            case "2": {
            	cleanConsole();
            	printGameScreen();
            	box = shuffle();
            	firstDistrubute(mll, box);
            	break;
            }
            case "3": {
            	console.getTextWindow().setCursorPosition(24 , 25);
                System.out.println("The CENG SOLITAIRE Is Closing...");
            	try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
                break;
            }
        }
	}
	
	public static void cleanConsole() { // cleans the console
        for (int i = 0; i < 31; i++) {
        	console.setTextAttributes(new TextAttributes(Color.DARK_GRAY, Color.DARK_GRAY));
        	console.getTextWindow().setCursorPosition(0, i);
        	//console.getTextWindow().output("                                                                               ");
        	System.out.println("                                                                               ");
        }
        console.getTextWindow().setCursorPosition(0, 0);
    }
	
	public static void highScoreToList(DoubleLinkedList dll) throws NumberFormatException, IOException {
		FileInputStream fStreamHighScore2=new FileInputStream("HighscoreTable.txt");
		BufferedReader bReaderHighScore2=new BufferedReader(new InputStreamReader(fStreamHighScore2));
		String tmpString;
		int i=0;
		String tmpStr2 = null;
		boolean two=false;
		boolean one=false;
		double tempScore=0;
		while((tmpString=bReaderHighScore2.readLine())!=null) {
			if(i%2==0) {
				tmpStr2=tmpString;
				two=true;
			}
			else {
				tempScore=Integer.parseInt(tmpString);
				one=true;
			}
			if(one&&two) {
				dllNode scoreNode = new dllNode(tmpStr2,tempScore);
				dll.orderedAdd(scoreNode);
				two=false;
				one=false;
			}
			i++;
		}

	}
	
	public static void firstDistrubute(MultiLinkedList mll, SingleLinkedList box) {
		int column=13;
		int row=7;
		mll.addColumn("a");
		mll.addColumn("b");
		mll.addColumn("c");
		mll.addColumn("d");
		mll.addColumn("e");
		String columnName = "a";
		console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));

		while(column < 50) {
			if(column == 22) {
				columnName = "b";
			}
			else if(column == 31) {
				columnName = "c";
			}
			else if(column == 40) {
				columnName = "d";
			}
			else if(column == 49) {
				columnName = "e";
			}
			
			
			for (int i = 0; i < 6; i++) {
				mll.addNumber(columnName, box.printBoxElement());
			}
			column = column + 9;
		}
		mll.display();
	}
	
	public static SingleLinkedList shuffle() {
		SingleLinkedList temp =new SingleLinkedList();
		SingleLinkedList returnBox =new SingleLinkedList();
		int number = 0;
		
		for(int i = 1; i < 11; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				temp.add(i);
			}
		}
		
		while(temp.size() > 0) {
			number = (int) (Math.random() * 10 + 1);
			
			if(temp.search(number) == true) {
				returnBox.add(number);
				temp.oneElementDelete(number);
			}
		}
		return returnBox;
	}
	
	public static int endGame(int finishedSets, int transferNumber) { 
      int endScore = (100 * finishedSets) + ((finishedSets * 1000) / transferNumber);
      return endScore;
    }
	
	
}
