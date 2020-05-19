import java.util.ArrayList;
import java.util.Scanner;
class contact{
	private int contactId;
	private String name;
	private long phNumber;

	public void setContactId(int contactId){
		this.contactId = contactId;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setPhNumber(long phNumber){
		this.phNumber = phNumber;
	}

	public int getContactId(){
		return contactId;
	}

	public String getName(){
		return name;
	}

	public long getPhNumber(){
		return phNumber;
	}
}

class chat{
	private ArrayList <String> messages = new ArrayList<>();
	private int chatId;
	private contact chatContactInfo;

	public void setMessage(String message){
		messages.add(message);
	}

	public void setChatId(int chatId){
		this.chatId = chatId;
	}

	public void setContact(contact personContact){
		this.chatContactInfo = personContact;
	}

	public ArrayList getMessages(){
		return messages;
	}

	public int getChatId(){
		return chatId;
	}

	public contact getContact(){
		return chatContactInfo;
	}

	public String recentMessage(){
		return messages.get(messages.size()-1);
	}
}

class chatModule{
	static Scanner inputScanner = new Scanner(System.in);
	static ArrayList<contact> contactList = new ArrayList<>();
	static ArrayList<chat> chatList = new ArrayList<>();
	static int id = 1;
	boolean flag = true;
	static void gettingURL(){
		do{
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
				flag = true;
			}
			else{
				flag = false;
				System.exit(1);
			}
		}
		while(flag);
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
							deletingContact(chatId);
							break;
						default:
							System.out.println("the given method is not found for the given resource....");	
					}
				}
				catch(Exception e ){
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
		for(contact Obj:contactList){
			if(Obj.getPhNumber()==phNumber){
				contactAlreadyExists = true;
			}
		}
		if(!contactAlreadyExists){
			contact contactObj = new contact();
			chat chatObj = new chat();
			contactObj.setContactId(id);
			contactObj.setName(name);
			contactObj.setPhNumber(phNumber);
			chatObj.setChatId(id);
			chatObj.setContact(contactObj);
			contactList.add(contactObj);
			chatList.add(chatObj);
			id+=1;
			System.out.println("Contact created successfully !");
		}
		else{
			System.out.println("Contact already exists ....");
		}
	}
	
	static void gettingContactList(){
		System.out.println("CONTACT ID : \t CONTACT PERSON NAME : \t CONTACT NUMBER ");
		for(contact contactObj:contactList){
			System.out.println("\t"+contactObj.getContactId()+"\t-  "+contactObj.getName()+"\t -  "+contactObj.getPhNumber());
		}
	}

	static void gettingSpecificContact(int contactId){
		boolean flag = false;
		for(contact contactObj:contactList){
			if(contactObj.getContactId()==contactId){
				flag = true;
				System.out.println("CONTACT ID : \t CONTACT PERSON NAME : \t CONTACT NUMBER ");
				System.out.println("\t"+contactObj.getContactId()+"\t-  "+contactObj.getName()+"\t -  "+contactObj.getPhNumber());
				break;
			}	
		}
		if(!flag){
			System.out.println("Contact not found .....");
		}
	}


	static void updatingContact(int contactId){
		boolean flag = false;
		for(int i=0;i<contactList.size();i++){
			if(contactList.get(i).getContactId()==contactId){
				flag = true;
				System.out.println("Name :"+contactList.get(i).getName());
				System.out.println("Phone Number "+contactList.get(i).getPhNumber());
				System.out.println("Do you change the Name of the contact (y/n)");
				String nameOrNum = inputScanner.nextLine();
				switch(nameOrNum){
					case "name":
						System.out.println("Enter the new Name");	
						String name = inputScanner.nextLine();
						contactList.get(i).setName(name);
						System.out.println("Name of the contact updated successfully !");
						chatList.get(i).getContact().setName(name);
						break;

					case "number":
						System.out.println("Enter the new Phone number");
						long number = Long.parseLong(inputScanner.nextLine());
						contactList.get(i).setPhNumber(number);
						System.out.println("Phone Number of the contact updated successfully !");
						chatList.get(i).getContact().setPhNumber(number);
						break;
					default:
						System.out.print("Update process Unsuccessfull .....");	
				}
				break;
			}	
		}
		if(!flag){
			System.out.println("Contact not found ......");		
		}	
	}

	static void deletingContact(int contactId){
		boolean flag = false;
		for(int i=0;i<contactList.size();i++){
			if(contactList.get(i).getContactId()==contactId){
				flag = true;
				contactList.remove(i);
				chatList.remove(i);
				System.out.println("Contact deleted successfully");
				break;
			}	
		}
		if(!flag){
			System.out.println("Contact not found ......");		
		}
	}

	static void gettingChatList(){
		for (chat chatObj: chatList){
			System.out.println("chat Id : "+chatObj.getChatId()+"  Name : "+chatObj.getContact().getName());
			System.out.println("Last message : "+chatObj.recentMessage()+"\n");
		}
	}

	static void gettingSpecificChat(int chatId){
		boolean flag = false;
		for (chat chatObj: chatList){
			if(chatObj.getChatId()==chatId){
				flag = true;
				System.out.println("chat Id : "+chatObj.getChatId()+"  Name : "+chatObj.getContact().getName());
				ArrayList messageList = chatObj.getMessages();
				for(int i=0;i<messageList.size();i++){
					System.out.println(messageList.get(i));
				}				
				break;
			}	

			if(!flag){
				System.out.println("Chat not found .....");
			}
		}	
	}

	static void postingNewMessageInChat(int chatId){
		for (chat chatObj: chatList){
			if(chatObj.getChatId()==chatId){
				System.out.println("sending message to "+chatObj.getContact().getName());
				System.out.println("Enter the message :");
				String message = inputScanner.nextLine();
				chatObj.setMessage(message);
				System.out.println("Message sent successfully  !");
			}
		}
	}

	static void deletingChat(int chatId){
		boolean chatDeleted = false;
		for (chat chatObj: chatList){
			if(chatObj.getChatId()==chatId){
				chatList.remove(chatId);
				System.out.println("Chat deleted successfully !");
				chatDeleted = true;
				break;
			}
		}
		if(!chatDeleted){
			System.out.println("Chat Not found ......");
		}
	}

	public static void main(String[] args) {
		gettingURL();
	}
}
