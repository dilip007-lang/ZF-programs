
import java.util.Scanner;
class printingNumbersInTriangleShape
{
	public static void main(String[] args)
	{
		Scanner inputScanner = new Scanner(System.in);
		int noOfRowsAndColumns;
		int num = 1;
		System.out.println("Enter the number of rows that you want to form a number triagle :");
		noOfRowsAndColumns = inputScanner.nextInt();
		if(noOfRowsAndColumns>=1 && noOfRowsAndColumns<=100)
		{
			System.out.println("Numbers is triangle shape:");
			for(int i=1;i<=noOfRowsAndColumns;i++)
			{
				for(int j=i;j<=noOfRowsAndColumns;j++)
				{
					System.out.print(num+" ");
					num++;
				}
				System.out.println();
			}
		}
		else
		{
			System.out.println("The rows should be between 1 to 100");
		}
	}
}
