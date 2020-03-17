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
		
		for(int outerLoop=userStartingNum;outerLoop<(userRowAndColumn+userStartingNum);outerLoop++){
			for(int inner1=userStartingNum;inner1<outerLoop;inner1++){
				System.out.print(inner1+" ");
			}
			for(int inner2=outerLoop;inner2>=userStartingNum;inner2--){
				System.out.print(inner2+" ");
			}
			System.out.println();
		}
	}
}
