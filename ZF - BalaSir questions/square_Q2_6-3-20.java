import java.util.Scanner;

class printingSquare
{
	public static void main(String[] args) 
	{
		Scanner inputScanner = new Scanner(System.in);
		int num = 0;
		int squareNumber;
		System.out.println("Please Enter the square number:");
		squareNumber = inputScanner.nextInt();
		if(squareNumber>=1 && squareNumber<=100)
		{
			System.out.println("Snake Square numbers:");
			for(int i=1;i<=squareNumber;i++)
			{
				for(int j=1;j<=squareNumber;j++)
				{
					if(i%2!=0)
					{
						num++;
						System.out.print(num+" ");
					}
					else
					{
						System.out.print(num+" ");
						num--;
					}
				}
				num+=squareNumber;
				System.out.println();
			}	
		}
		else
		{
			System.out.println("please enter a number between 1 to 100");
		}	
	}
}
	
/*
1 2 3 4 5 6 
12 11 10 9 8 7 
13 14 15 16 17 18 
24 23 22 21 20 19 
25 26 27 28 29 30 
36 35 34 33 32 31
*/
