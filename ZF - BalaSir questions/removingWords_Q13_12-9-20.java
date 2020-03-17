import java.util.Scanner;
class removingWordFromString{
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);

		System.out.print("Enter the sentence :");
		String userInputSentence = inputScanner.nextLine();

		System.out.print("Enter the word to be removed from the sentence :");
		String userInputRemoveWord = inputScanner.nextLine();
		
		String replacedSentence = userInputSentence.replace(wordToBeRemoved,"");
		System.out.println("Using replace method :"+replacedSentence);
		
		/*without replace() method*/
			String crtSentence = "";
			int removeWordLength = userInputRemoveWord.length();
			int sentenceLength = userInputSentence.length();
			for(int i=0;i<sentenceLength;i++)
			{
				if((i+removeWordLength<=sentenceLength) && userInputSentence.substring(i,i+removeWordLength).equals(userInputRemoveWord))
				{
					i = i+removeWordLength-1;
				}
				else
				{
					crtSentence+=userInputSentence.substring(i,i+1);
				}
			}	
			System.out.println("Removed sentence without using replace method :"+crtSentence);
			inputScanner.close();
	}
}	
