import java.util.Scanner;
class DisariumNumberOrNot
{
	public static void main(String[] args) 
	{
		Scanner inputScanner = new Scanner(System.in);

		  //getting input from user
		System.out.println("Enter the Number which you wnat to Know whether it is DISARIUM or NOT :");
		long userInput = inputScanner.nextLong();
		long numberToBeChecked = userInput;

	  	// variable to count the no of digits
		int digitCount = 0;

	  	//the add the digits powered number
		int sumOfTheDigits = 0;

		  // this while is to calculate the no of digits
		while(numberToBeChecked>0)
		{
			digitCount++;
			numberToBeChecked/=10;
		}

		  //reEntering the users value because the number is now zero
		numberToBeChecked = userInput;

	  	// this is to add the digit by powering it with its position
	  	// we can given the digitsCount as loop condition
		while(numberToBeChecked>0)
		{
			int digit =(int)numberToBeChecked%10;
			sumOfTheDigits+=Math.pow(digit,digitCount);
			numberToBeChecked/=10;
			digitCount--;
		}

		if(sumOfTheDigits==userInput)
		{
			System.out.println("The given number is a DISARIUM : "+sumOfTheDigits);
		}
		else
		{
			System.out.println("The given number is NOT a DISARIUM : "+sumOfTheDigits);
		}
	}
}
