import java.awt.Color;

import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class MultiLinkedList {
    private ColumnNode columnNode;
	private NumberNode numberNode;
	static Console console = Enigma.getConsole("CENG SOLITAIRE", 80, 30, 20);
	
	public MultiLinkedList() {
		columnNode=null;
		numberNode=null;
	}
	
	public void addColumn(String columnName) {
		if(columnNode==null) {
			ColumnNode newNode=new ColumnNode(columnName);
			columnNode=newNode;
		}
		else {
			ColumnNode temp=columnNode;
			while(temp.getRight()!=null) {
				temp=temp.getRight();
			}
			ColumnNode newNode=new ColumnNode(columnName);
			temp.setRight(newNode);
		}
	}
	
	public void addNumber(String columnName,int number) {
		if(columnNode==null) {
			System.out.println("Firstly,please add column name");
		}
		else {
			ColumnNode temp=columnNode;
			while(temp!=null) {
				if(temp.getColumnName().equals(columnName)) {
					NumberNode temp2=temp.getDown();
					if(temp2==null) {
						NumberNode newNode=new NumberNode(number);
						temp.setDown(newNode);
					}
					else {
						while(temp2.getNext()!=null)
							temp2=temp2.getNext();
						NumberNode newnode=new NumberNode(number);
						temp2.setNext(newnode);
					}
				}
				temp=temp.getRight();
			}
		}
	}
	
	public int sizeColumn() {
		int count=0;
		if(columnNode==null) {
			System.out.println("linked list is empty");
		}
		else {
			ColumnNode temp=columnNode;
			while(temp!=null) {
				count++;
				temp=temp.getRight();
			}
		}
		return count;
	}
	
	public void display() {
		if(columnNode==null) {
		}
		else {
			int column=13;
			int row=7;
			ColumnNode temp =columnNode;
			while(temp!=null) {
				NumberNode temp2=temp.getDown();
				while(temp2!=null && column < 50) {
					console.getTextWindow().setCursorPosition(column, row);
					System.out.println(temp2.getNumber());
					temp2=temp2.getNext();
					row++;
				}
				column=column+9;
				row=7;
				temp=temp.getRight();
			}
					
		}
	}
	
	public int lastNumberFor(int whichColumn) {
		ColumnNode temp =columnNode;
		switch(whichColumn) {
		case 22: temp=temp.getRight();break;
		case 31: temp=temp.getRight().getRight();break;
		case 40: temp=temp.getRight().getRight().getRight();break;
		case 49: temp=temp.getRight().getRight().getRight().getRight();break;
		
		}
		int temp3=0;
		NumberNode temp2=temp.getDown();
		while(temp2!=null) {
			temp3=(int)temp2.getNumber();
			temp2=temp2.getNext();	
		}
		return temp3;
	}
	
	public int elementSizeInColumn(int x) {
		ColumnNode temp=columnNode;
		int row=0;
		switch(x) {
		case 22: temp=temp.getRight();break;
		case 31: temp=temp.getRight().getRight();break;
		case 40: temp=temp.getRight().getRight().getRight();break;
		case 49: temp=temp.getRight().getRight().getRight().getRight();break;
		default:
			break;
		}
		NumberNode temp2=temp.getDown();
		while(temp2!=null) {
			temp2=temp2.getNext();
			row++;
		}
		
		return row;
	}
	public Object deleteFromLast(int x,int y) {
		
		ColumnNode temp=columnNode;
		
		switch(x) {
		case 22: temp=temp.getRight();break;
		case 31: temp=temp.getRight().getRight();break;
		case 40: temp=temp.getRight().getRight().getRight();break;
		case 49: temp=temp.getRight().getRight().getRight().getRight();break;
		default:
			break;
		}
		console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
		Object retData=null;
		NumberNode temp2=temp.getDown();
		if(elementSizeInColumn(x)==1) {
			retData=(int)temp2.getNumber();
			temp.setDown(null);;
		}
		else {
			while(temp2.getNext().getNext()!=null) {
				temp2=temp2.getNext();
			}
			retData=(int)temp2.getNext().getNumber();
			temp2.setNext(null);
		}

		
		return retData;
		
	}
	public int [] deleteElementAccordIndex(int px,int py) {
		console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
		ColumnNode temp=columnNode;
		if(px==13  && elementSizeInColumn(13)>= py-7||px==22  && elementSizeInColumn(22)>= py-7||px==31  && elementSizeInColumn(31)>= py-7 || px==40  && elementSizeInColumn(40)>= py-7 || px==49  && elementSizeInColumn(49)>= py-7) {
			NumberNode temp2=temp.getDown();
			int howManyDelete=elementSizeInColumn(px)-py+7;
			int[]tempArr=new int[howManyDelete];
			for(int i=0;i<howManyDelete;i++) {
				tempArr[i]=(int)deleteFromLast(px,py);
			}
			return tempArr;
		}
		else {
			int []deneme=new int [0];
			return deneme;
		}
	}
	
	public void transferringElements(int x, int[] tempAr) {
		ColumnNode temp=columnNode;
		
		switch(x) {
		case 22: temp=temp.getRight();break;
		case 31: temp=temp.getRight().getRight();break;
		case 40: temp=temp.getRight().getRight().getRight();break;
		case 49: temp=temp.getRight().getRight().getRight().getRight();break;
		default:
			break;
		}
		
		console.setTextAttributes(new TextAttributes(Color.WHITE, Color.DARK_GRAY));
		
		NumberNode temp2=temp.getDown();
		
		if(elementSizeInColumn(x)==1) {
			for(int i = 1; tempAr.length - i > -1; i++) {
				NumberNode temp3 = new NumberNode(tempAr[tempAr.length - i]);
				temp2.setNext(temp3);
				temp2 = temp2.getNext();
			}
		}
		else if(temp2 == null) {
			for(int i = 1; tempAr.length - i > -1; i++) {
				NumberNode temp3 = new NumberNode(tempAr[tempAr.length - i]);
				if(i == 1){
					temp.setDown(temp3);
					temp2=temp.getDown();
				}
				else {
					temp2.setNext(temp3);
					temp2 = temp2.getNext();
				}
			}
		}
		else {
			while(temp2.getNext()!=null) {
				temp2=temp2.getNext();
			}
			for(int i = 1; tempAr.length - i > -1; i++) {
				NumberNode temp3 = new NumberNode(tempAr[tempAr.length - i]);
				temp2.setNext(temp3);
				temp2 = temp2.getNext();
			}
		}
	}
	public Object indexFinder(int indexColumn,int indexNumber) {

        ColumnNode temp=columnNode;
        int row=0;
        switch(indexColumn) {
        case 22: temp=temp.getRight();break;
        case 31: temp=temp.getRight().getRight();break;
        case 40: temp=temp.getRight().getRight().getRight();break;
        case 49: temp=temp.getRight().getRight().getRight().getRight();break;
        default:
            break;
        }
        NumberNode temp2=temp.getDown();
        for(int i=0;i<indexNumber;i++)
            temp2=temp2.getNext();

        return temp2.getNumber();

	}
	
	public int linkedListChecker() throws InterruptedException {
        boolean flag=true;
        ColumnNode temp =columnNode;
        int x=13;
        int howManySuccess=0;
        int columnCount = 0;
        while(temp!=null) {
            flag=true;
            NumberNode temp2=temp.getDown();
            if(temp2 == null)
            {
            	if(columnCount == 4)
            		break;
            	x+=9;
                temp=temp.getRight();
                temp2=temp.getDown();
                if(columnCount == 5)
                	break;
                columnCount++;
                if(temp2 == null)
                {
                	x+=9;
                    temp=temp.getRight();
                    temp2=temp.getDown();
                    if(columnCount == 5)
                    	break;
                    columnCount++;
                    if(temp2 == null)
                    {
                    	x+=9;
                        temp=temp.getRight();
                        temp2=temp.getDown();
                        if(columnCount == 5)
                        	break;
                        columnCount++;
                        if(temp2 == null)
                        {
                        	x+=9;
                            temp=temp.getRight();
                            temp2=temp.getDown();
                            if(columnCount == 5)
                            	break;
                            columnCount++;
                           
                        }
                    }
                }
            }
            if(elementSizeInColumn(x)==10) {
                if((int)temp2.getNumber()==1) 
                {
                    int counter=1;
                    while(temp2!=null) {
                        if((int)temp2.getNumber()!=counter) {
                            flag=false;
                            break;
                            }
                    counter++;
                    temp2=temp2.getNext();

                }
                    if(flag)
                    {
                    	howManySuccess++;
                    	temp.setDown(null);
                    	Thread.sleep(250);
                    	Solitaire.printGameScreen();
                    }
                    x+=9;
                    temp=temp.getRight();
                }
                else if((int)temp2.getNumber()==10) {
                    int counter=10;
                    while(temp2!=null) {
                        if((int)temp2.getNumber()!=counter) {
                            flag=false;
                            break;
                            }
                    counter--;
                    temp2=temp2.getNext();
                }
                    if(flag)
                    {
                    	howManySuccess++;
                    	temp.setDown(null);
                    	Thread.sleep(250);
                    	Solitaire.printGameScreen();
                    }
                    x+=9;
                temp=temp.getRight();
            }
                else {
                    x+=9;
                    temp=temp.getRight();
                    }
            }
            else {
                flag=false;
                x+=9;
                temp=temp.getRight();
            }
            columnCount++;
            if(columnCount == 5)
            	break;
            

    }
    return howManySuccess;
}
	
}