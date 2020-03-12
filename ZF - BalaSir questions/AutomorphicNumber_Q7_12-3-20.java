import java.util.Scanner;
class AutomorphicNumber{
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		System.out.print("Enter the Number which you want to check whether it is a Automorphic Number or Not :");

		long userInput = inputScanner.nextInt();
		long numberToBeChecked = userInput;
		long squaredNumber = (long)Math.pow(numberToBeChecked,2);
		long tempSquaredNumber = squaredNumber;
		long endingNumber = 0;
		long modulo = 1;
		if(squaredNumber==0)
		{
			System.out.println("It's a AUTOMORPHIC NUMBER");
		}
		else
		{
			while(numberToBeChecked>0)
			{
				endingNumber += (tempSquaredNumber%10)*modulo;
				modulo*=10;
				numberToBeChecked/=10;
				tempSquaredNumber/=10;
			}
			if(endingNumber==userInput)
			{
				System.out.println("It's a AUTOMORPHIC NUMBER your input :"+userInput+" It's square :"+squaredNumber);
			}	
			else
			{
				System.out.println("It's NOT an AUTOMORPHIC NUMBER number your input :"+userInput+" It's square :"+squaredNumber);
			}	
		}	
	}
}

			/*
			Scanner inputScanner = new Scanner(System.in);
				System.out.print("Enter the Number which you want to check whether it is a Automorphic Number or Not :");

				long userInput = inputScanner.nextInt();
				long numberToBeChecked = userInput;
				long squaredNumber = (long)Math.pow(numberToBeChecked,2);
				long tempSquaredNumber = squaredNumber;
				Boolean flag = false;
				long endingNumber = 0;
				long modulo = 1;

				/*while(numberToBeChecked>0)
				{
					if(numberToBeChecked%10==tempSquaredNumber%10)
					{
						flag = true;
					}
					else
					{
						flag = false;
						break;
					}
					numberToBeChecked/=10;
					tempSquaredNumber/=10;
				}	
				if(flag)
				{
					System.out.println("It's a AutomorphicNumber number your input :"+userInput+" It's square :"+squaredNumber);
				}
				else
				{
					System.out.println("It's Not a AutomorphicNumber number your input :"+userInput+" It's square :"+squaredNumber);	
				}*/



			/*with some extra variables :
				Scanner inputScanner = new Scanner(System.in);
				System.out.print("Enter the Number which you want to check whether it is a Automorphic Number or Not :");

				int userInput = inputScanner.nextInt();
				int numberToBeChecked = userInput;

				int powerNumber = 2;
				int squaredNumber = (int)Math.pow(numberToBeChecked,powerNumber);
				int tempSquaredNumber = squaredNumber;

				int originalNumberDigit;
				int squaredNumberDigit;
				Boolean flag = false;

				while(numberToBeChecked>0){

					originalNumberDigit = numberToBeChecked%10;
					squaredNumberDigit = tempSquaredNumber%10;
					if(squaredNumberDigit==originalNumberDigit){
						flag = true;
					}
					else{
						flag = false;
						break;
					}
					numberToBeChecked/=10;
					tempSquaredNumber/=10;
				}	

				if(flag){
					System.out.println("It's a AutomorphicNumber number your input :"+userInput+" It's square :"+squaredNumber);
				}
				else{
					System.out.println("It's not a AutomorphicNumber number your input :"+userInput+" It's square :"+squaredNumber);	
				}*/