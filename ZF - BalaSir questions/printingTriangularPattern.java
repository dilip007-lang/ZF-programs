import java.util.Scanner;
class triangularPattern
{
	public static void main(String[] args) 
	{
		Scanner inputScanner  = new Scanner(System.in);
		System.out.print("Enter the Starting number of the triangle pattern :");
		int userStartingNum = inputScanner.nextInt();
		// temp variable is not needed because I'am not destroying the input
		System.out.print("Enter no of rows for that triangle :");
		int userRowAndColumn = inputScanner.nextInt();
		for(int i=startingNum;i<=rowAndColumn+startingNum;i++)
		{
			for(int j=startingNum;j<i;j++)
			{
				System.out.print(j+" ");
			}
			for(int k=i;k>=startingNum;k--)
			{
				System.out.print(k+" ");
			}
			System.out.println();
		}
	}
}
