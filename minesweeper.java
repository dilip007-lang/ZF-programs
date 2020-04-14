import java.util.Random;
import java.util.Scanner;
class minesweeperGame{
	static int noOfRows;
	static int noOfColumns;
	static int noOfBombs;
	static int[][] minesArray;
	static String[][] storingUserInput;
	static Scanner inputScanner = new Scanner(System.in);
	static int userFlagCount = noOfBombs;
	static int noOfCrtFlags;
	static int squaresToBeOpened = (noOfRows*noOfColumns)-noOfBombs;
 
	static void gameLevel(){
		System.out.println("Enter Game Level (Easy,Medium,Hard) :");
		String gameLevel = inputScanner.next();
			switch (gameLevel) {
				case "easy":
				case "Easy":
					noOfRows = 8;
					noOfColumns = 8;
					noOfBombs = 8;
					break;

				case "medium":
				case "Medium":
					noOfRows = 12;
					noOfColumns = 12;
					noOfBombs = 12;
					break;

				case "hard":
				case "Hard":
					noOfRows = 16;
					noOfColumns = 16;
					noOfBombs = 16;
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

	static void conditionsForPlacingNumbersAndBombs(int row,int column,int value){
		/*
		int rowStarting = 0;
		int rowEnding = 0;
		int columnStarting = 0;
		int columnEnding = 0;

		switch(row){
			case 0:
				rowStarting = row;
				rowEnding = row+1;
				break;
			case 7:
			case 11:
			case 15:
				rowEnding = row;
				rowStarting = row-1;
				break;
			default:
				rowStarting = row-1;
				rowEnding = row+1;	
		}

		switch(column){
			case 0:
				columnStarting = column;
				columnEnding = column+1;
				break;
			case 7:
			case 11:
			case 15:
				columnEnding = column;	
				columnStarting = column-1;
			default:
				columnStarting = column-1;
				columnEnding = column+1;	
		}
		*/

		int rowStarting = row-1;
		int rowEnding = row+1;
		int columnStarting = column-1;
		int columnEnding = column+1;

		switch(row){
			case 0:
				rowStarting = row;
				break;
			case 7:
			case 11:
			case 15:
				rowEnding = row;
		}

		switch(column){
			case 0:
				columnStarting = column;
				break;
			case 7:
			case 11:
			case 15:
				columnEnding = column;	
		}

		switch(value){
			case 1:
				for(int i=rowStarting;i<=rowEnding;i++){
					for(int j=columnStarting;j<=columnEnding;j++){
						if(minesArray[i][j]!=-1){
							minesArray[i][j]+=1;
						} 
					}
				}
				break;

			case -1:
				for(int i=rowStarting;i<=rowEnding;i++){
					for(int j=columnStarting;j<=columnEnding;j++){
						if(minesArray[i][j]!=-1){
							minesArray[i][j] = -1;
							conditionsForPlacingNumbersAndBombs(i,j,1);
							break;
						}
					}
				}
				break;

			case 0:
				for(int i=rowStarting;i<=rowEnding;i++){
					for(int j=columnStarting;j<=columnEnding;j++){
						if(minesArray[i][j] == 0){
							storingUserInput[i][j] = Integer.toString(minesArray[i][j]);
							squaresToBeOpened--;
						}
					}
				}			
		}
	}

	static void placingNumbersAndBombs(){
		Random randomNumber = new Random();
		for(int i=0;i<noOfBombs;i++){
			int rowRanNum = randomNumber.nextInt(noOfRows);
			int columnRanNum = randomNumber.nextInt(noOfColumns);
			if(minesArray[rowRanNum][columnRanNum]!=-1){
				minesArray[rowRanNum][columnRanNum] = -1;
				conditionsForPlacingNumbersAndBombs(rowRanNum,columnRanNum,1);
			}
			else{
				conditionsForPlacingNumbersAndBombs(rowRanNum,columnRanNum,-1);
			}					
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
			switch(minesArray[row][column]){
				case -1:
					storingUserInput[row][column] = "-1";
					returnValue =  "Loss";
					break;

				case 0:
					squaresToBeOpened++;
					conditionsForPlacingNumbersAndBombs(row,column,0);
					break;

				default:
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
		switch(storingUserInput[row][column]){
			case "*":

		}


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

	/*static void unflaggingSquare(int row,int column){
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
	}*/

	static boolean checkingInputValues(int row,int column,String squareType){
		String openedSquare = "";
		String flaggedSquare = "";
		String unflaggedSquare = "";
		boolean returnValue = false;
		if(row>=0 && row<noOfRows && column>=0 && column<noOfColumns){
			switch(squareType){
				case "O":
				case "o":
					openedSquare = checkingSquareOpeningCondition(row,column);
					switch(openedSquare){
						case "Loss":
							System.out.println("You Loss the game ....");
							returnValue = true;
							break;

						case "Won":
							System.out.println("You Won the game ....");
							returnValue = true;	
					}
				case "F":
				case "f":
				 	flaggedSquare = checkingSquareFlagCondition(row,column);
				 	switch(flaggedSquare){
						case "Loss":
							System.out.println("You Loss the game ....");
							returnValue = true;
							break;

						case "Won":
							System.out.println("You Won the game ....");
							returnValue = true;	
					}	
				default:
					System.out.println("Please enter wheather you want to open or flag the square (if you want to unflag a flagged square pls choose flag itself)");	 	
			}
		}
		else{
			System.out.println("The given square is not exists ...");
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
