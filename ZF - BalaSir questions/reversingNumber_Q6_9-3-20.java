import java.util.Scanner;
class reversingNumber
{
	public static void main(String[] args)
	{
		Scanner inputScanner = new Scanner(System.in);
		int userInputNumber;
		int numberToBeReversed;
		int reversedNumber = 0;
		int remainder;
		System.out.println("Enter the number to be reversed");
		userInputNumber = inputScanner.nextInt();
		numberToBeReversed = userInputNumber;
		if(numberToBeReversed>=0 && numberToBeReversed<=2147483647)
		{	
			while(numberToBeReversed>0)
			{
				remainder = numberToBeReversed%10;
				reversedNumber *=10;
				reversedNumber +=remainder;
				
				//reversedNumber *=10+remainder;
				numberToBeReversed = numberToBeReversed/10;	
			}
			System.out.println(reversedNumber);
		}
		else{
			System.out.println("Please Enter a number greater than 0 and lesser than 2147483647");
		}


		


		/*
			input:11110000
			output:1111

			input:000011111
			output:1111
		*/
		/*
		to get zero's in starting or ending positions
		using String
		String reversedString = "";
		if(numberToBeReversed!=0)
		{	
			while(numberToBeReversed>0)
			{
				remainder = numberToBeReversed%10;
				reversedString += String.valueOf(remainder);
				numberToBeReversed = numberToBeReversed/10;	
			}
			System.out.println(reversedString);
		}
		*/	
	}
}