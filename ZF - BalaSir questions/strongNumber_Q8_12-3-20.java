import java.util.*;
class strongNumber
{
	public static void main(String[] args) 
	{
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Enter the Number which you want to check whether it is Strong Number or Not :");
		long userInput = inputScanner.nextLong();
		long numberToBeChecked = userInput;
		long sumOfTheDigits = 0;
		int remainder = 0;
		int sum =1;
		while(numberToBeChecked>0)
		{
			remainder =(int)numberToBeChecked%10;
			while(remainder>=1)
			{ 
				sum = sum*remainder;
				remainder--;
			}
			sumOfTheDigits +=sum;
			numberToBeChecked/=10;
			sum = 1;
		}
		if(sumOfTheDigits==userInput)
		{
			System.out.println("IT IS A STRONG NUMBER!!!! :"+sumOfTheDigits);
		}

		else
		{
			System.out.println("No, It's Not  a Strong number :"+sumOfTheDigits);
		}
	}
}
