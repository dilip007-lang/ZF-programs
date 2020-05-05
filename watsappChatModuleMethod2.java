import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
class contact{
	private String name;
	private long phNumber;

	public void setName(String name){
		this.name = name;
	}

	public void setPhNumber(long phNumber){
		this.phNumber = phNumber;
	}

	public String getName(){
		return name;
	}

	public long getPhNumber(){
		return phNumber;
	}
}

class chatsModule{
	static Scanner inputScanner = new Scanner(System.in);
	static HashMap<Integer,contact> contactMap = new HashMap<>();
	static HashMap<Integer,ArrayList> chatMap = new HashMap<>();
	static int id = 1;

	static void gettingURL(){
		System.out.println("Enter the URL :");
		String URL = inputScanner.nextLine();
		String[] splitingURL = URL.split(" /");
		if(splitingURL.length>=2){
			switch(splitingURL[1]){
				case "contacts":
					contacts(splitingURL);
					break;
				case "chats":
					chats(splitingURL);
					break;
				default:
					System.out.println("The Entered Resource is not found .....");		
			}
		}	
		else{
			System.out.println("The URL is incorrect ..... try again");
		}

		System.out.println("Do you want to continue ....(y/n)");
		String searchAgain = inputScanner.nextLine();
		if(searchAgain.equalsIgnoreCase("Y")){
			gettingURL();
		}
		else{
			System.exit(1);
		}
	}

	static void contacts(String[] splittedURL){
		switch(splittedURL.length){
			case 2:
				switch(splittedURL[0]){
					case "GET":
						gettingContactList();
						break;
					case "POST":
						creatingNewContact();
						break;
					default:
						System.out.println("the given method is not found ....");	
				}
				break;
			case 3:
				try{
					int contactId = Integer.parseInt(splittedURL[2]);
					switch(splittedURL[0]){
						case "GET":
							gettingSpecificContact(contactId);
							break;
						case "PUT":
							updatingContact(contactId);
							break;
						case "DELETE":
							deletingContact(contactId);
							break;
						default:
							System.out.println("the given method is not found ....");	
							gettingURL();
					}
				}
				catch(Exception e){
					System.out.println("The given path parameter is not a number to identify a specific contact enter a contact Id ....");
				}
				break;	
				default:
					System.out.println("The given URL pattern is WRONG");	
					gettingURL();	
		}
	}

	static void chats(String[] splittedURL){
		switch(splittedURL.length){
			case 2:
				switch(splittedURL[0]){
					case "GET":
						gettingChatList();
						break;
					default:
						System.out.println("the given method is not found for the given resource....");	
				}
				break;
			case 3:
				try{
					int chatId = Integer.parseInt(splittedURL[2]);
					switch(splittedURL[0]){
						case "GET":
							gettingSpecificChat(chatId);
							break;
						case "POST":
							postingNewMessageInChat(chatId);
							break;
						case "DELETE":
							deletingChat(chatId);
							break;
						default:
							System.out.println("the given method is not found for the given resource....");	
					}
				}
				catch(Exception e){
					System.out.println("The given path parameter is not a number to identify a specific contact enter a contact Id ....");
				}
				break;	
				default:
					System.out.println("The given URL pattern is WRONG");		
		}
	}

	static void creatingNewContact(){
		boolean contactAlreadyExists = false;
		System.out.println("Enter The Name :");
		String name = inputScanner.nextLine();
		System.out.println("Enter the Number :");
		long phNumber = Long.parseLong(inputScanner.nextLine());
		 for (Map.Entry<Integer,contact> entry : contactMap.entrySet()){
  			contact contactObj = entry.getValue();
  			if(contactObj.getPhNumber()==phNumber){
  				contactAlreadyExists = true;
  				break;
  			}
  		}
		if(!contactAlreadyExists){
			contact contactObj = new contact();
			contactObj.setName(name);
			contactObj.setPhNumber(phNumber);
			contactMap.put(id,contactObj);
			chatMap.put(id,new ArrayList());
			id+=1;
			System.out.println("Contact created successfully !");
		}
		else{
			System.out.println("Contact already exists ....");
		}
	}
	
	static void gettingContactList(){
		System.out.println("CONTACT ID : \t CONTACT PERSON NAME : \t CONTACT NUMBER ");
        for (Map.Entry<Integer,contact> entry : contactMap.entrySet()){
  			Integer contactId = entry.getKey();
  			contact contactObj = entry.getValue();
  			System.out.println("\t"+contactId+"\t-  "+contactObj.getName()+"\t -  "+contactObj.getPhNumber());
  		}
	}

	static void gettingSpecificContact(int contactId){
		try{
			contact contactObj = contactMap.get(contactId);
			System.out.println("CONTACT ID : \t CONTACT PERSON NAME : \t CONTACT NUMBER ");
			System.out.println("\t"+contactId+"\t-  "+contactObj.getName()+"\t -  "+contactObj.getPhNumber());
		}
		catch(Exception e){
			System.out.println("Contact not found .....");
		}

		try{
			contact contactObj = contactMap.get(contactId);
			System.out.println("CONTACT ID : \t CONTACT PERSON NAME : \t CONTACT NUMBER ");
			System.out.println("\t"+contactId+"\t-  "+contactObj.getName()+"\t -  "+contactObj.getPhNumber());
		}
		catch(Exception e){
			System.out.println("Contact not found .....");
		}
	}


	static void updatingContact(int contactId){
		try{
			contact contactObj = contactMap.get(contactId);
			System.out.println("Name :"+contactObj.getName());
			System.out.println("Phone Number "+contactObj.getPhNumber());
			System.out.println("Enter the New Name(if don't want to change enter the same)");
			String name = inputScanner.nextLine();
			System.out.print("Enter the New Phone Number(if don't want to change enter the same)");
			long number = Long.parseLong(inputScanner.nextLine());
			contactMap.get(contactId).setName(name);
			contactMap.get(contactId).setPhNumber(number);
			System.out.print("Contact updated successfully !");
		}	
		catch(Exception e){
			System.out.println("Contact not found ....");
		}
	}

	static void deletingContact(int contactId){
		try{
			contactMap.remove(contactId);
		}
		catch(Exception e){
			System.out.println("Contact not found ....");
		}
	}

	static void gettingChatList(){
        for (Map.Entry<Integer,ArrayList> entry : chatMap.entrySet()){
  			Integer chatId = entry.getKey();
  			ArrayList messageList = entry.getValue();
  			contact contactObj = contactMap.get(chatId);
  			System.out.println("Chat Id : "+chatId+"\t -  Name "+contactObj.getName());
  			System.out.println("Last message : "+messageList.get(messageList.size()-1));
  		}

	}

	static void gettingSpecificChat(int chatId){
		try{
			ArrayList messageList = chatMap.get(chatId);
			contact contactObj = contactMap.get(chatId);
			System.out.println("Person Name : "+contactObj.getName());
			for(int i=0;i<messageList.size();i++){
				System.out.println(messageList.get(i));
			}
		}
		catch(Exception e){
			System.out.println("chat not found ....");
		}
	}

	static void postingNewMessageInChat(int chatId){
		if(chatMap.containsKey(chatId)){
			contact contactObj = contactMap.get(chatId);
			System.out.println("sending message to "+contactMap.get(chatId).getName());
			System.out.println("Enter the message :");
			String message = inputScanner.nextLine();
			chatMap.get(chatId).add(message);
			System.out.println("Message sent successfully");
		}
	}

	static void deletingChat(int chatId){
		try{
			chatMap.remove(chatId);
		}
		catch(Exception e){
			System.out.println("Chat not found .....");
		}
	}

	public static void main(String[] args) {
		gettingURL();
	}
}
