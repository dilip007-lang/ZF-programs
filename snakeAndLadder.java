import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;
class snakeAndLadder{
	private static int[][] gameBoard = new int[10][10];
	private static HashMap<Integer,Integer> snakePosition = new HashMap<>();
	private static HashMap<Integer,Integer> LadderPosition = new HashMap<>();
	static Scanner inputScanner = new Scanner(System.in);
	private static boolean gameEnds = false;
	static void setSnakeAndLadderPosition(){
		snakePosition.put(32,10);
		snakePosition.put(34,6);
		snakePosition.put(48,26);
		snakePosition.put(62,18);
		snakePosition.put(88,24);
		snakePosition.put(95,56);
		snakePosition.put(97,78);

		LadderPosition.put(1,38);
		LadderPosition.put(4,14);
		LadderPosition.put(8,30);
		LadderPosition.put(21,42);
		LadderPosition.put(28,76);
		LadderPosition.put(50,67);
		LadderPosition.put(71,92);
		LadderPosition.put(80,99);		
	}

	static void assigningValuesToGameBoard(){		
		int num1 = 1;
		for(int i=0;i<gameBoard.length;i++){
			for(int j=0;j<gameBoard.length;j++){
				gameBoard[i][j] = num1;
				num1++;
			}
			i++;
			num1+=10;
			for(int k=0;k<gameBoard.length;k++){
				num1--;
				gameBoard[i][k] = num1;	
			}
			num1+=10;
		}
		/*int  value = 0;
		// It takes more time then the other two types
		for(int i=0;i<gameBoard.length;i++){
			for(int j=0;j<gameBoard.length;j++){
				if(i%2==0){
					value++;
					gameBoard[i][j] = value;
				}
				else{
					gameBoard[i][j] = value;
					value--;
				}
			}
			value+=10;
		}*/
	/*int num1 = 0;
		for(int i=0;i<gameBoard.length;i+=2){
			for(int j=0;j<gameBoard.length;j++){
				num1++;
				gameBoard[i][j] = Integer.toString(num1);
			}
			num1+=10;
		}
		num1 = 21;
		for(int i=1;i<gameBoard.length;i+=2){
			for(int j=0;j<gameBoard.length;j++){
				num1--;
				gameBoard[i][j] = Integer.toString(num1);
			}
			num1+=30;
		}*/
	}

	static int generatingRandomNum(){
		Random ran = new Random();
		return ran.nextInt(6)+1;
	}

	static int checkingSnakeAndLadderPosition(int playerPosition,String currentPlayer){
		if(snakePosition.containsKey(playerPosition)){
			playerPosition = snakePosition.get(playerPosition);
			System.out.println("Sorry! A Snake bites "+currentPlayer+" ....");			
		}
		else if(LadderPosition.containsKey(playerPosition)){
			playerPosition = LadderPosition.get(playerPosition);
			System.out.println(playerPosition);
			System.out.println("Wow ! "+currentPlayer+" got a Ladder ....");
		}
		return playerPosition;
	}

	static int assigningPlayerPosition(int currentPosition,String currentPlayer){
		int ranNum = 0;
		int playerPosition = currentPosition;
		String boardReturnValue = "";
		do{
			ranNum = generatingRandomNum();
			if(ranNum==6){
				System.out.println("Wow ! "+currentPlayer+" got an Extra dice  dice number is "+ranNum);
			} 
			else{
				System.out.println(""+currentPlayer+" dice number is "+ranNum);
			}
			playerPosition+=ranNum;
			if(playerPosition>100){
				System.out.println("Sorry Your dice can't be moved because the dice number is going greater that 100");
				playerPosition = playerPosition-ranNum;
			}
			else{
				playerPosition = checkingSnakeAndLadderPosition(playerPosition,currentPlayer);
			}
			
		}
		while(ranNum==6);
		return playerPosition;
	}

	static int gettingDiceValueAndChecking(int currentPlayerPosition,String playerName){
		int playerPosition = currentPlayerPosition;
		System.out.println(""+playerName+" is playing ..");
		System.out.println("Type 'roll' to ROLL the dice");
		if(inputScanner.next().equalsIgnoreCase("roll")){
			playerPosition = assigningPlayerPosition(playerPosition,playerName);
		}
		else{
			System.out.println("Game ends ..");
			System. exit(1);
		}
		checkingWinningCondition(playerPosition,playerName);
		return playerPosition;
	}

	static void checkingWinningCondition(int playerPosition,String playerName){
		if(playerPosition==100){
			System.out.println(playerName+" Won the Game ....");
			gameEnds = true;
		}
	}

	static void printingGameBoard(int player1Position,String player1Name,int player2Position,String player2Name){
		for(int i=gameBoard.length-1;i>=0;i--){
			for(int j=0;j<gameBoard.length;j++){
				if(gameBoard[i][j] == player1Position){
					System.out.print(player1Name+"\t\t");
				}
				else if(gameBoard[i][j] == player2Position){
					System.out.print(player2Name+"   \t");
				}
				else{
					System.out.print(gameBoard[i][j]+"\t\t");
				}
			}
			System.out.println("\n");
		}
	}

	static void processingGame(){
				int player1Position = 0;
		int player2Position = 0;
		System.out.print("Welcome to Snake and Ladder ....");
		System.out.println("Do you want to play with our 'Computer' or an another 'Player' (type 'com' or 'ply2')");
		switch(inputScanner.next()){
			case "com":
			case "computer":
				while(!gameEnds){
					player1Position = gettingDiceValueAndChecking(player1Position,"Player1");
					player2Position = assigningPlayerPosition(player2Position,"Computer");
					checkingWinningCondition(player1Position,"Computer");
					printingGameBoard(player1Position,"Player",player2Position,"Computer");
				}

				break;
			case "ply2":
			case "player2":
				while(!gameEnds){
					player1Position = gettingDiceValueAndChecking(player1Position,"Player1");
					player2Position = gettingDiceValueAndChecking(player2Position,"Player2");	
					printingGameBoard(player1Position,"Player1",player2Position,"player2");
				}
				break;
			default:
				System.out.println("Sorry you didn't gave correct input try again ....");
				processingGame();				
		}
	}

	public static void main(String[] args) {
		assigningValuesToGameBoard();
		setSnakeAndLadderPosition();
		processingGame();
	
	}
}
