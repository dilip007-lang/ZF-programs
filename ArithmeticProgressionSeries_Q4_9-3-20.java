
import java.util.Scanner;
class ArithmeticProgressionSeries{
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		int noOfElements;
		int startingNumber;
		int difference;
		System.out.println("The number of Elements should be greater than 0 and lesser than 10001,the Starting number should be greater than or equal to 0 and Difference must be greater than 0");
		
		System.out.println("Enter the total number of elements :");
		noOfElements = inputScanner.nextInt();

		System.out.println("Enter the Starting number :");
		startingNumber = inputScanner.nextInt();

		System.out.println("Enter the difference :");
		difference = inputScanner.nextInt();

		if(noOfElements>=1 &&noOfElements<=10000 && startingNumber>=0 && difference>0 && difference<=10000000 && startingNumber<=10000000)
		{
			System.out.print(startingNumber);
			for(int i=2;i<=noOfElements;i++)
			{
				startingNumber+=difference;
				System.out.print(","+startingNumber);
				
			}
			System.out.println();

			/*
			for(int totalElements:noOfElements)
			{
				startingNumber+=difference;
				System.out.print(startingNumber+",");
				
			}
			*/
		}
		else
		{
			System.out.println("Please Enter your input correctly ");
		}
	}
}