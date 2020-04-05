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
	// this method is the choose the level of the game.
	// there are three levels Easy,Medium,Hard
	static void gameLevel(){
		System.out.println("Enter Game Level (Easy,Medium,Hard) :");
		String gameLevel = inputScanner.next();
		if(gameLevel.equals("Medium")){
			noOfRows += 4;
			noOfColumns += 4;
			noOfBombs += 4;
		}
		else if(gameLevel.equals("Hard")){
			noOfRows += 8;
			noOfColumns += 8;
			noOfBombs += 8;
		}
		// assigning the size of the array
		minesArray = new int[noOfRows][noOfColumns];
		//this array is to store the value which user open or flaged a square the value will be taken from the corresponding position in minesArray and assigned here
		storingUserInput = new String[noOfRows][noOfColumns];
	}
	
	// this method is to place numbers and bombs the first loop will place the bombs and seconf outer loop will place the numbers according to the number of bombs around it
	static void placingNumbersAndBombs(){
		System.out.println("length"+minesArray.length);
		Random randomNumber = new Random();
		for(int i=0;i<noOfBombs;i++){
			int rowRanNum = randomNumber.nextInt(noOfRows);
			int columnRanNum = randomNumber.nextInt(noOfColumns);
			if(minesArray[rowRanNum][columnRanNum]!=-1){
				minesArray[rowRanNum][columnRanNum] = -1;
			}
			else{
				i-=1;
			}	
		}	
		for(int i=0;i<noOfRows;i++){
			for(int j=0;j<noOfColumns;j++){
				if(minesArray[i][j]==-1){
					if( i>=0 && j>=0 && i<noOfRows && j<noOfColumns){
						if((j-1>=0) && minesArray[i][j-1]!=-1){
							minesArray[i][j-1]+=1;	
						}
						if((j+1<noOfColumns) && minesArray[i][j+1]!=-1){
							minesArray[i][j+1]+=1;	
						}
						if((i-1>=0) && (j-1>=0) && minesArray[i-1][j-1]!=-1){
							minesArray[i-1][j-1]+=1;	
						}
						if((i-1>=0) && minesArray[i-1][j]!=-1){
							minesArray[i-1][j]+=1;	
						}
						if((i-1>=0) && (j+1<noOfColumns) && minesArray[i-1][j+1]!=-1){
							minesArray[i-1][j+1]+=1;	
						}
						if((i+1<noOfRows) && (j-1>=0) && minesArray[i+1][j-1]!=-1){
							minesArray[i+1][j-1]+=1;	
						}
						if((i+1<noOfRows) && minesArray[i+1][j]!=-1){
							minesArray[i+1][j]+=1;	
						}
						if((i+1<noOfRows) && (j+1<noOfColumns) && minesArray[i+1][j+1]!=-1){
							minesArray[i+1][j+1]+=1;	
						}
					}
				}
			}
		}
	}
	// this method is to check the condition for opening a square	
	static String checkingSquareOpeningCondition(int row,int column){
		String returnValue = "";
		if(minesArray[row][column] == -1){
			returnValue =  "Loss";
		}
		else if(minesArray[row][column] == 0){
			for(int i=0;i<noOfRows;i++){
				for(int j=0;j<noOfColumns;j++){
					if(minesArray[i][j] == 0){
						storingUserInput[i][j] = Integer.toString(minesArray[i][j]);
					}
				}
			}
			returnValue = "Opened";
		}
		else{
			storingUserInput[row][column] = Integer.toString(minesArray[row][column]);
		}
		System.out.println(storingUserInput[row][column]);
		return returnValue;
	}
	
	// this method is to check the condition to flag a square
	static String checkingSquareFlagCondition(int row,int column){
		String returnValue = "";
		System.out.println(minesArray[row][column]);
		if(minesArray[row][column] == -1){
			storingUserInput[row][column] = "F";
			System.out.println(storingUserInput[row][column]);
			noOfCrtFlags++;	
			if(noOfCrtFlags==noOfBombs){
				returnValue = "Won";
				return returnValue;
			}
			if(userFlagCount>0){
				userFlagCount--;
			}		
			else{
				returnValue = "Loss";
			}
		}
		else{
			storingUserInput[row][column] = "F";
			userFlagCount--;
			returnValue = "Flagged";
		}
		return returnValue;
	}
	
	// this is the method which invokes the above two method to get input and check the input and render result
	static boolean checkingInputValues(int row,int column,String squareType){
		String openedSquare = "";
		String flaggedSquare = "";
		boolean returnValue = false;
		if(row>=0 && row<noOfRows && column>=0 && column<noOfColumns && (squareType.equals("F") || squareType.equals("O"))){
			if(storingUserInput[row][column] == "*"){
				if(squareType.equals("O")){
					openedSquare = checkingSquareOpeningCondition(row,column);
					if(openedSquare.equals("Loss")){
						System.out.println("You Loss the game ....");
						returnValue = true;
					}
				}
				else{
				 	flaggedSquare = checkingSquareFlagCondition(row,column);
				 	if(flaggedSquare.equals("Won")){
				 		System.out.println("You Won the Game .....");
				 		returnValue = true;
				 	}
				 	else if(flaggedSquare.equals("Loss")){
				 		System.out.println("You Loss the Game .... ");
				 		returnValue = true;
				 	}
				}
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
	
	static void gettingInputAndShowingResult(){
		int noOfFlags = noOfBombs;
		for(int i=0;i<noOfRows;i++){
			for(int j=0;j<noOfColumns;j++){
				storingUserInput[i][j] = "*"; 
			}
		}
		boolean startGame = true;
		while(startGame){
			System.out.println("Flag remaining :"+noOfFlags+"\n");
			for(String[] innerArray:storingUserInput){
				for(String arrValues:innerArray){
					System.out.print(arrValues+"\t");
				}
				System.out.println();
			}
			System.out.println("Enter the Row of the square (It should be between 0 to "+noOfRows+")");
			int inputRow = inputScanner.nextInt();
			System.out.println("Enter the Column of the square (It should be between 0 to "+noOfColumns+")");
			int inputColumn = inputScanner.nextInt();
			System.out.println("Do you want OPEN or FLAG the square ('F' for Flag and 'O' for Open ) :");
			String inputSquareType = inputScanner.next();
			if(checkingInputValues(inputRow,inputColumn,inputSquareType)){
				startGame = false;
			}
		}
	}
	
	public static void main(String[] args) {
		gameLevel();	
		placingNumbersAndBombs();
		// just for checking
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
