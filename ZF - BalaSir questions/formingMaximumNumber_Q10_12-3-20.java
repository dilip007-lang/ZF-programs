import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
class formingMaximumNumber{
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Enter a number to get maximize version of it :");
		int userInputNum = inputScanner.nextInt();
		int inputNum = userInputNum;
		ArrayList<Integer> digitsArr = new ArrayList<Integer>();
		int digit;
		int maximizedNumber = 0;
		while(inputNum>0){
			digit = inputNum%10;
			inputNum/=10;
			digitsArr.add(digit);
		}
		Collections.sort(digitsArr);
		for(int i=digitsArr.size()-1;i>=0;i--){
			maximizedNumber = (maximizedNumber*10)+digitsArr.get(i);
		}
		System.out.println(maximizedNumber);
	}
}

class usingArray{
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Enter a number to get maximize version of it :");
		/*getting a number from user(in command line)*/
		
		int userInputNum = inputScanner.nextInt();
		int inputNum = userInputNum;
		ArrayList<Integer> digitsArrList = new ArrayList<Integer>();
		int digit;
		int maximizedNumber = 0;

		/*Seperating the number into digits and storing it in an ArrayList*/
		while(inputNum>0){
			digit = inputNum%10;
			inputNum/=10;
			digitsArrList.add(digit);
		}

		/*Adding the elements in the arraylist into an array by converting the object[] to Integer[]] */
		Integer[] digitsArray = new Integer[digitsArrList.size()];
		digitsArray = digitsArrList.toArray(digitsArray);
		int temp;

		
		/*sorting the array in descing order*/

		for(int i=0;i<digitsArray.length-1;i++){
			for(int j=i+1;j<digitsArray.length;j++){
				if(digitsArray[j]>digitsArray[i]){
					temp = digitsArray[j];
					digitsArray[j] = digitsArray[i];
					digitsArray[i] = temp;
				}
			}
		}

		//Arrays.sort(digitsArray); - it can be used


		/*adding the digits from the array to a int variable */
		for(int elements:digitsArray){
			maximizedNumber = (maximizedNumber*10)+elements;
		}
		System.out.println(maximizedNumber);
	}	
}

/*	With array:
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Enter a number to get maximize version of it :");
		int userInputNum = inputScanner.nextInt();
		int inputNum = userInputNum;
		int arrSize = 0;
		int[] digitsArr = new int[arrSize];
		int digit;
		int maximizedNumber = 0;
		while(inputNum>0){
			arrSize++;
			digit = inputNum%10;
			digitsArr[arrSize-1] = digit;
			inputNum/=10;
		}
		System.out.print(digitsArr.length());
*/
