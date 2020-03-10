
import java.util.Scanner;
class sumOfEvenDigits{
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		int inputNumber;
		int sum = 0;
		int digit;

		System.out.println("Enter a number to get the sum of even digits in it:");
		inputNumber = inputScanner.nextInt();
		if(inputNumber>0)
		{
			while(inputNumber>0)
			{
				digit = inputNumber%10;
				if(digit%2==0)
				{
					sum+=digit;
				}
				inputNumber=inputNumber/10;	
			}
			System.out.println("The Sum of even digits:");
			System.out.println(sum);



			/*
				(or) without a variable for concating the digits
				while(inputNumber>0)
				{
					if((inputNumber%10)%2==0){
						sum+=inputNumber%10;
						inputNumber=inputNumber/10;
					}
					
					else{
						inputNumber=inputNumber/10;
					}
				}
				System.out.println("The Sum of even digits:");
				System.out.println(sum);
			*/
		}
		else
		{
			System.out.println("Please enter a valid number");
		}
		

	}
}