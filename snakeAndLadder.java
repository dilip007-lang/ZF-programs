import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;

class playerInfo{
	private String playerName;
	private int playerCurrentPosition;
	private int playerPreviousPosition;
	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}
	public void setPlayerCurrentPosition(int playerCurrentPosition){
		this.playerCurrentPosition = playerCurrentPosition;
	}
	public void setPlayerPreviousPosition(int playerPreviousPosition){
		this.playerPreviousPosition = playerPreviousPosition;
	}
	public String getPlayerName(){
		return playerName;
	}
	public int getPlayerCurrentPosition(){
		return playerCurrentPosition;
	}
	public int getPlayerPreviousPosition(){
		return playerPreviousPosition;
	}
}

class snakeAndLadder{
	private static String[][] stringGameBoard = new String[10][10];
	private static int[][]	gameBoard = new int[10][10];
	private static HashMap<Integer,Integer> snakePosition = new HashMap<>();
	private static HashMap<Integer,Integer> LadderPosition = new HashMap<>();
	private static playerInfo playersObjArr[];
	static Scanner inputScanner = new Scanner(System.in);
	private static int finishedPlayers = 0;
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
		int num1 = 100;
		int increasingValue = 1;
		for(int i=0;i<gameBoard.length;i++){
			for(int j=0;j<gameBoard.length;j++){
				gameBoard[i][j] = num1;
				stringGameBoard[i][j] = Integer.toString(num1);
				num1-=increasingValue;
			}
			increasingValue = increasingValue*(-1);
			num1-=10+(increasingValue);
		}
	}


	static int generatingRandomNum(){
		Random ran = new Random();
		return ran.nextInt(6)+1;
	}


	static int gettingTotalNoOfPlayers(){
		System.out.println("Enter total Number of players ...");
		int totalPlayers = inputScanner.nextInt();
		if(totalPlayers<2){
			gettingTotalNoOfPlayers();	
		}
		playersObjArr = new playerInfo[totalPlayers];
			System.out.println(playersObjArr.length);
		assigningNameToPlayers();
		return totalPlayers;
	}


	static void assigningNameToPlayers(){
		for(int i=0;i<playersObjArr.length;i++){
			playersObjArr[i] = new playerInfo();
			playersObjArr[i].setPlayerName("P"+(i+1)); 
		}
	}

	static void checkingWinningCondition(playerInfo playerObj){
		if(playerObj.getPlayerCurrentPosition()==100){
			finishedPlayers+=1;
			System.out.println(playerObj.getPlayerName()+" Finished the Game ....");
		}
	}

	static void displayGameBoard(){
		for(int i=0;i<stringGameBoard.length;i++){
			for(int j=0;j<stringGameBoard.length;j++){
				System.out.print(stringGameBoard[i][j]+"\t");
			}
			System.out.println();
		}
	}

	static void checkingSnakeAndLadderPosition(playerInfo playerObj){
		int position = playerObj.getPlayerCurrentPosition();
		System.out.println("current position "+position);
		String playerName = playerObj.getPlayerName();
		if(snakePosition.containsKey(playerObj.getPlayerCurrentPosition())){
			System.out.println("snake bites");
			playerObj.setPlayerCurrentPosition(snakePosition.get(position));
			System.out.println("Sorry! A Snake bites "+playerName+" ....");			
		}
		else if(LadderPosition.containsKey(playerObj.getPlayerCurrentPosition())){
			System.out.println("got ladder");
			playerObj.setPlayerCurrentPosition(LadderPosition.get(position));
			System.out.println("Wow ! "+playerName+" got a Ladder ....");
		}
	}

	static void changingValuesInGameBoard(playerInfo playerObj){
		String playerName = playerObj.getPlayerName();
		int currentPos = playerObj.getPlayerCurrentPosition();
		int previousPos = playerObj.getPlayerPreviousPosition();
		for(int i=0;i<gameBoard.length;i++){
			for(int j=0;j<gameBoard.length;j++){
				if(gameBoard[i][j] == currentPos){
					if(stringGameBoard[i][j].startsWith("P")){
						stringGameBoard[i][j] += playerName;
					}
					else{
						stringGameBoard[i][j] = playerName;
					}
					
				}
				else if(gameBoard[i][j] == previousPos){
					if(stringGameBoard[i][j].equals(playerName)){
						stringGameBoard[i][j] = Integer.toString(previousPos);
					}
					else if(stringGameBoard[i][j].contains(playerName)){
						String replacedWord = stringGameBoard[i][j].replace(playerName,"");
						stringGameBoard[i][j] = replacedWord;
					}
				}

			}
		}
	}

	
	static void assigningPlayerPosition(playerInfo playerObj){
		int ranNum = 0;
		int currentPosition = playerObj.getPlayerCurrentPosition();
		String playerName = playerObj.getPlayerName();
		System.out.println(playerName);
		do{
			ranNum = generatingRandomNum();
			if((currentPosition+ranNum)>100){
				System.out.println("Sorry Your position can't be moved because the dice number is going greater that 100");
			}
			else{
				playerObj.setPlayerPreviousPosition(currentPosition);
				currentPosition = currentPosition+ranNum;
				playerObj.setPlayerCurrentPosition(currentPosition);
				checkingSnakeAndLadderPosition(playerObj);
				changingValuesInGameBoard(playerObj);
				if(ranNum==6){
					System.out.println("Wow ! "+playerName+" got an Extra dice because he got a : "+ranNum);
					System.out.println(""+playerObj.getPlayerName()+" is playing ..");
					System.out.println("Type 'roll' to ROLL the dice");
					continue;
				} 
				else{
					System.out.println(""+playerName+" dice number is : "+ranNum);
				}
			}
		}
		while(ranNum==6);
	}

	static void gettingDiceValueAndChecking(playerInfo playerObj){
		System.out.println(""+playerObj.getPlayerName()+" is playing ..");
		System.out.println("Type 'roll' to ROLL the dice");
		if(inputScanner.next().equalsIgnoreCase("roll")){
			assigningPlayerPosition(playerObj);
			displayGameBoard();
			checkingWinningCondition(playerObj);
		}
		else{
			System.out.println("Game ends ..");
			System.exit(1);
		}
	}

	static void processingGame(){
		System.out.println("Welcome to Snake and Ladder game ....");
		int totalNoOfPlayers = gettingTotalNoOfPlayers();
		System.out.println(totalNoOfPlayers+"d");
		System.out.println(finishedPlayers);
		while(finishedPlayers<=totalNoOfPlayers-1){
			for(int i=0;i<totalNoOfPlayers;i++){
				gettingDiceValueAndChecking(playersObjArr[i]);
			}
		}
		System.out.println("Game ends");
	}

	public static void main(String[] args) {
		assigningValuesToGameBoard();
		setSnakeAndLadderPosition();
		processingGame();
	}
}
