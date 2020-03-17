import java.util.Scanner;
class triangularPattern
{
	public static void main(String[] args) 
	{
		Scanner inputScanner  = new Scanner(System.in);
		System.out.print("Enter the Starting number of the triangle pattern :");
		int userStartingNum = inputScanner.nextInt();
		System.out.print("Enter no of rows for that triangle :");
		int userRowAndColumn = inputScanner.nextInt();
		int startingNum = userStartingNum;
		int numberIterator = startingNum-1;
		int columnIterator = 1;
		Boolean flag = true;

		for(int i=1;i<=userRowAndColumn;i++)
		{
			for (int j=1;j<=columnIterator;j++)
			{
				if(numberIterator<startingNum && flag)
				{
					numberIterator++;
				}
				else
				{
					flag = false;
					numberIterator--;
				}
				System.out.print(numberIterator+" ");
			}
			flag = true;
			startingNum++;
			numberIterator =userStartingNum-1;
			System.out.println();
			columnIterator+=2;
		}
		
		
		/*
		only two variables needed
		for(int i=startingNum;i<=rowAndColumn+startingNum;i++){
			for(int j=startingNum;j<i;j++){
				System.out.print(j+" ");
			}
			for(int k=i;k>=startingNum;k--){
				System.out.print(k+" ");
			}
			System.out.println();
		}*/
	}
}
