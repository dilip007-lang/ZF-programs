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
		int tempStartingNum = startingNum-1;
		int numberIterator = tempStartingNum;
		int rowAndColumn = userRowAndColumn;
		int columnIterator = 1;
		Boolean flag = true;

		for(int i=1;i<=rowAndColumn;i++)
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
			numberIterator = tempStartingNum;
			System.out.println();
			columnIterator+=2;
		}
	}
}

/*
	for static patern
	int num = 7;
	for(int i=1;i<=7;i++){
		for(int j=1;j<i;j++){
			System.out.print(j+" ");
		}
		for(int k=i;k>=1;k++){
			System.out.print(k+" ");
		}
		System.out.println();
	}	
	
*/
