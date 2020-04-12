import java.util.Random;
import java.util.Scanner;
class minesweeperGame{
	static int noOfRows = 8;
	static int noOfColumns = 8;
	static int noOfBombs = 8;
	static int[][] minesArray;
	static String[][] storingUserInput;
	static Scanner inputScanner = new Scanner(System.in);
	static int userFlagCount = noOfBombs;
	static int noOfCrtFlags;
	static int squaresToBeOpened = (noOfRows*noOfColumns)-noOfBombs;
 
	static void gameLevel(){
		System.out.println("Enter Game Level (Easy,Medium,Hard) :");
		String gameLevel = inputScanner.next();
		if(gameLevel.equalsIgnoreCase("Medium")){
			noOfRows += 4;
			noOfColumns += 4;
			noOfBombs += 4;
		}
		else if(gameLevel.equalsIgnoreCase("Hard")){
			noOfRows += 8;
			noOfColumns += 8;
			noOfBombs += 8;
		}
		minesArray = new int[noOfRows][noOfColumns];
		storingUserInput = new String[noOfRows][noOfColumns];
		for(int i=0;i<noOfRows;i++){
			for(int j=0;j<noOfColumns;j++){
				minesArray[i][j] = 0;
				storingUserInput[i][j] = "*";
			}
		}
	}

	static void conditionsForPlacingNumbers(int row,int column){
		if((column-1>=0) && minesArray[row][column-1]!=-1){
			minesArray[row][column-1]+=1;	
		}
		if((column+1<noOfColumns) && minesArray[row][column+1]!=-1){
			minesArray[row][column+1]+=1;	
		}
		if((row-1>=0) && (column-1>=0) && minesArray[row-1][column-1]!=-1){
			minesArray[row-1][column-1]+=1;	
		}
		if((row-1>=0) && minesArray[row-1][column]!=-1){
			minesArray[row-1][column]+=1;	
		}
		if((row-1>=0) && (column+1<noOfColumns) && minesArray[row-1][column+1]!=-1){
			minesArray[row-1][column+1]+=1;	
		}
		if((row+1<noOfRows) && (column-1>=0) && minesArray[row+1][column-1]!=-1){
			minesArray[row+1][column-1]+=1;	
		}
		if((row+1<noOfRows) && minesArray[row+1][column]!=-1){
			minesArray[row+1][column]+=1;	
		}
		if((row+1<noOfRows) && (column+1<noOfColumns) && minesArray[row+1][column+1]!=-1){
			minesArray[row+1][column+1]+=1;	
		}		
	}
	static void placingNumbersAndBombs(){
		Random randomNumber = new Random();
		for(int i=0;i<noOfBombs;i++){
			int rowRanNum = randomNumber.nextInt(noOfRows);
			int columnRanNum = randomNumber.nextInt(noOfColumns);
			if(minesArray[rowRanNum][columnRanNum]!=-1){
				minesArray[rowRanNum][columnRanNum] = -1;	
			}
			else{
				if(rowRanNum<noOfRows-1 && minesArray[rowRanNum+1][columnRanNum]!=-1){
					minesArray[rowRanNum+1][columnRanNum] = -1;
					rowRanNum+=1;
				}
				else if(columnRanNum<noOfColumns-1 && minesArray[rowRanNum][columnRanNum+1]!=-1){
					minesArray[rowRanNum][columnRanNum+1] = -1;
					columnRanNum+=1;
				}
				else if(columnRanNum>0 && minesArray[rowRanNum][columnRanNum-1]!=-1){
					minesArray[rowRanNum][columnRanNum-1] = -1;
					columnRanNum-=1;
				}
				else if(rowRanNum>0 && minesArray[rowRanNum-1][columnRanNum]!=-1){
					minesArray[rowRanNum-1][columnRanNum] = -1;
					rowRanNum-=1;
				}
			}
			conditionsForPlacingNumbers(rowRanNum,columnRanNum);						
		}	
	}

	static String winningCondition(){
		String result = "";
		if(userFlagCount==0 && noOfCrtFlags==noOfBombs){
			result = "Won";
		}
		else if(userFlagCount==0 && noOfCrtFlags<noOfBombs){
			result = "Loss";
		}	
		return result;
	}

	static String checkingSquareOpeningCondition(int row,int column){
		String returnValue = "";
		squaresToBeOpened--;
		if(squaresToBeOpened!=0){
			if(minesArray[row][column] == -1){
			storingUserInput[row][column] = "-1";
			returnValue =  "Loss";
			}
			else if(minesArray[row][column] == 0){
				squaresToBeOpened++;
				for(int i=0;i<noOfRows;i++){
					for(int j=0;j<noOfColumns;j++){
						if(minesArray[i][j] == 0){
							storingUserInput[i][j] = Integer.toString(minesArray[i][j]);
							squaresToBeOpened--;
						}
					}
				}
			}
			else{
				storingUserInput[row][column] = Integer.toString(minesArray[row][column]);
			}
		}

		else{
			if(minesArray[row][column] == -1){
				storingUserInput[row][column] = "-1";
				returnValue =  "Loss";
			}
			else{
				storingUserInput[row][column] = Integer.toString(minesArray[row][column]);
				returnValue = winningCondition();
			}
		}
		return returnValue;
	}

	static String checkingSquareFlagCondition(int row,int column){
		String returnValue = "";
		userFlagCount--;		
		if(userFlagCount>0){
			storingUserInput[row][column] = "F";
			if(minesArray[row][column] == -1){
				noOfCrtFlags++;	
			}
		}
		else if(userFlagCount==0){
			storingUserInput[row][column] = "F";
			if(minesArray[row][column] == -1){
				noOfCrtFlags++;	
			}
			if(squaresToBeOpened == 0){
				returnValue = winningCondition();
			}
		}
		else{
			System.out.println("You have placed all of your flags");
		}	
		return returnValue;
	}

	static void unflaggingSquare(int row,int column){
		if(storingUserInput[row][column].equalsIgnoreCase("F")){
			storingUserInput[row][column] = "*";
			userFlagCount++;
			if(minesArray[row][column] == -1){
				noOfCrtFlags--;
			}
			System.out.println("The given square is unflagged");
		}
		else{
			System.out.println("The given square is not a flagged square");
		}
	}

	static boolean checkingInputValues(int row,int column,String squareType){
		String openedSquare = "";
		String flaggedSquare = "";
		String unflaggedSquare = "";
		boolean returnValue = false;
		if(row>=0 && row<noOfRows && column>=0 && column<noOfColumns && (squareType.equalsIgnoreCase("F") || squareType.equalsIgnoreCase("O") || squareType.equalsIgnoreCase("UF"))){
			if(storingUserInput[row][column] == "*"){
				if(squareType.equalsIgnoreCase("O")){
					openedSquare = checkingSquareOpeningCondition(row,column);
					if(openedSquare.equals("Loss")){
						System.out.println("You Loss the game ....");
						returnValue = true;
					}
					else if(openedSquare.equals("Won")){
						System.out.println("You Won the game ....");
						returnValue = true;
					}
				}
				else{
				 	flaggedSquare = checkingSquareFlagCondition(row,column);
				 	//if(userFlagCount==0){
					 	if(flaggedSquare.equals("Won")){
					 		System.out.println("You Won the Game .....");
					 		returnValue = true;
					 	}
					 	else if(flaggedSquare.equals("Loss")){
					 		System.out.println("You Loss the Game .... ");
					 		returnValue = true;
					 	}
					//}	
				}
			}
			else if(squareType.equalsIgnoreCase("UF")){
				unflaggingSquare(row,column);
			}
			else{
				System.out.println("The square is already active");
			}
		}
		else{
			System.out.println("Please enter correct value some of your inputs are not satisfying our constraints ...");
			returnValue = false;
		}
		return returnValue;
	}

	static void displayTable(){
		System.out.println("Flag remaining :"+userFlagCount+"\n");
			for(String[] innerArray:storingUserInput){
				for(String arrValues:innerArray){
					System.out.print(arrValues+"\t");
				}
				System.out.println();
			}
	}

	static void gettingInputAndShowingResult(){
		boolean startGame = true;
		while(startGame){
			displayTable();
			System.out.println("Enter the Row of the square (It should be between 0 to "+noOfRows+")");
			int inputRow = inputScanner.nextInt();
			System.out.println("Enter the Column of the square (It should be between 0 to "+noOfColumns+")");
			int inputColumn = inputScanner.nextInt();
			System.out.println("Do you want OPEN, FLAG or UNFLAG the square ('F' for Flag , 'O' for Open and 'UF' for unflag the square) :");
			String inputSquareType = inputScanner.next();
			if(checkingInputValues(inputRow,inputColumn,inputSquareType)){
				startGame = false;
			}
		}
	}

	public static void main(String[] args) {
		gameLevel();	
		placingNumbersAndBombs();
		for(int[] innerArray : minesArray){
			for(int values : innerArray){
				System.out.print(values+"\t");	
			}
			System.out.println();	
		}
		gettingInputAndShowingResult();
		for(int[] innerArray : minesArray){
			for(int values : innerArray){
				System.out.print(values+"\t");	
			}
			System.out.println();	
		}
	}
}
