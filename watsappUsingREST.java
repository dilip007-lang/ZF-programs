import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map;
import java.util.Map.Entry;
class contact{
	private static int staticContactId;
	private int contactId;
	public static HashMap<Integer,contact> contactList = new HashMap<>();	
	private String contactName;
	private long contactNumber;

	public contact(){
		staticContactId++;
		contactId = staticContactId;
	}

	public void setContactName(String contactName){
		this.contactName = contactName;
	}

	public void setContactNumber(long contactNumber){
		this.contactNumber = contactNumber;
	}

	public int getContactId(){
		return contactId;
	}

	public String getContactName(){
		return contactName;
	}

	public long getContactNumber(){
		return contactNumber;
	}

	public static void setContact(int key,contact value){
		contactList.put(key,value);
	}

	public static HashMap getContactList(){
		return contactList;
	}

	public static contact getContactObject(int contactId){
		return contactList.get(contactId);
	}

	public static void processingContactModule(String[] splittedURL){
		int contactId = 0;
		switch(splittedURL.length){
			case 2:
				if(splittedURL[0].equals("get")){
					gettingContactList();
				}
				else{
					System.out.println("Given URL is wrong (Given Method not found) ..\n");
				}
				break;
			case 3:
				switch(splittedURL[0]){
					case "get":
						gettingSpecificContact(Integer.parseInt(splittedURL[2]));
						break;
					case "post":
						creatingNewContact(splittedURL[2]);
						break;
					case "delete":
						deletingContact(Integer.parseInt(splittedURL[2]));
						break;
					default:
						System.out.println("Given URL is wrong (Given Method not found) ..\n");	
				}
				break;
			case 4:
				if(splittedURL[0].equals("put")){
					updatingContact( Integer.parseInt(splittedURL[2]),splittedURL[3]);
				}
				else{
					System.out.println("Given URL is wrong (Given Method not found) ..\n");
				}
				break;
			default:
				System.out.println("The given URL is wrong");
		}
	}

	public static void gettingContactList(){
		Iterator<Entry<Integer,contact>> it = contact.getContactList().entrySet().iterator();
		System.out.println("Contact Id\t|   contact Name\t|    contact Number\t\n");
		while(it.hasNext()){
			Map.Entry<Integer,contact> contact =it.next();
			System.out.println("\t"+contact.getKey()+"\t\t"+contact.getValue().getContactName()+"\t  "+contact.getValue().getContactNumber()+"\n");
		}	
	}

	public static void gettingSpecificContact(int contactId){
		contact contactObj = (contact)contact.getContactList().get(contactId);
		if(contactObj!=null){
			System.out.println("Contact Id\t|   contact Name\t|    contact Number\t");
			System.out.println("\n"+contactId+"\t\t"+contactObj.getContactName()+"\t\t"+contactObj.getContactNumber()+"\n");
		}
		else{
			System.out.println("Contact Id not found ....");
		}
	}

	public static void creatingNewContact(String details){
		String[] contactDetails = details.split(",");
		String name = contactDetails[0];
		long number = Long.parseLong(contactDetails[1]);
		boolean contactAlreadyExist = checkingContactExist(number);
		if(!contactAlreadyExist){
			contact contactObj = new contact();
			contactObj.setContactName(name);
			contactObj.setContactNumber(number);
			contact.setContact(contactObj.getContactId(),contactObj);
			System.out.println("Contact created successfully ...\n");
		}
		else{
			System.out.println("Contact Number Already exists ...\n");
		}	
	}	

	public static boolean checkingContactExist(long phNum){
		Iterator<Entry<Integer,contact>> it = contact.getContactList().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer,contact> contact =it.next();
			if(contact.getValue().getContactNumber() == phNum){
				return true;		
			}
		}
		return false;
	}

	public static void deletingContact(int contactId){
		try{
			contact.getContactList().remove(contactId);
			System.out.print("Contact Deleted !\n");
		}
		catch(Exception ex){
			System.out.print("Contact Id not found ....\n");
		}
	}

	public static void updatingContact(int contactId,String updateDetails){
		contact conObj = (contact)contact.getContactList().get(contactId);
		if(conObj!=null){
			try{
				String[] contactDetails = updateDetails.split(",");
				conObj.setContactName(contactDetails[0]);
				long phNum = Long.parseLong(contactDetails[1]);
				if(!checkingContactExist(phNum)){
					conObj.setContactNumber(phNum);
					System.out.println("Contact Updated successfully ...\n");
				}
				else{
					System.out.println("Contact Number already Exist ... \n");
				}
			}
			catch(Exception ex){
				System.out.println("Given parameters are wrong ... \n");
			}	
		}
		else{
			System.out.print("Contact Id not found ....\n");
		} 
	}
}

class chat{
	private static int staticChatId;
	private int chatId;
	private int contactId;
	private int messageId;
	private String chatStatus;
	private static HashMap<Integer,chat> chatList = new HashMap<>();	
	private HashMap<Integer,message> messageList = new HashMap<>();

	public chat(){
		staticChatId++;
		chatId = staticChatId;
	}

	public void setContactId(int contactId){
		this.contactId = contactId;
	}

	public void setMessageId(){
		messageId++;
	}

	public void setChatStatus(String chatStatus){
		this.chatStatus = chatStatus;
	}

	public void setMessageObj(int msgId,message postMessage){
		messageList.put(msgId,postMessage);
	}

	public static void setChat(int id,chat chatObj){
		chatList.put(id,chatObj);
	}

	public String getChatStatus(){
		return chatStatus;
	}

	public int getChatId(){
		return chatId;
	}

	public int getContactId(){
		return contactId;
	}

	public int getMessageId(){
		return messageId;
	}

	public HashMap getMessageList(){
		return messageList;
	}

	public static HashMap getChatList(){
		return chatList;
	}

	public message getLastMessageObj(int messageId){
		return messageList.get(messageId);
	}

	public static void processingChatModule(String[] splittedURL){
		int chatId = 0;
		switch(splittedURL.length){
			case 2:
				if(splittedURL[0].equals("get")){
					gettingChatList();
				}
				else{
					System.out.println("Given URL is wrong (Given Method not found) ..\n");
				}
				break;
			case 3:
				chatId =Integer.parseInt(splittedURL[2]);
				switch(splittedURL[0]){
					case "get":
						gettingSpecificChat(chatId);
						break;
					case "delete":
						deletingChat(chatId);
						break;
					default:
						System.out.println("Given URL is wrong (Given Method not found) ..\n");	
				}
				break;		
			case 4:

				chatId =Integer.parseInt(splittedURL[2]);
				switch(splittedURL[0]){
					case "post":
						postingNewMessage(chatId,splittedURL[3]);
						break;
					case "delete":
						deletingMessage(chatId,Integer.parseInt(splittedURL[3]));
						break;
					case "put":
						archiveChat(chatId,splittedURL[3]);	
						break;
					default:
						System.out.println("Given URL is wrong (Given Method not found) ..\n");	
				}
				break;
			default:
				System.out.println("The given URL is wrong\n");
		}
	}

	public static void postingNewMessage(int chatId,String message){
		if(chat.getChatList().containsKey(chatId)){
			chat chatObj = (chat)chat.getChatList().get(chatId);
			creatingMessageObj(chatObj,message);
			System.out.println("Message Added successfully ...\n");
		} 
		else{
			if(contactIdExists(chatId)){
				System.out.println("Chat Id not found mapped Given Id mapped with anpther contact\n");
			}
			else{
				if(contact.getContactList().containsKey(chatId)){
					creatingChatObj(chatId,message);
					System.out.println("message posted successfully ...\n");
				}
				else{
					System.out.println("Contact Not found for the given Id ...\n");
				}
			}	
		}
	}

	public static void creatingMessageObj(chat chatObj,String message){
		message messageObj = new message();
		messageObj.setChatId(chatObj.getChatId());
		messageObj.setContactId(chatObj.getContactId());
		messageObj.setMessage(message);
		messageObj.setMessageStatus("outgoing");
		chatObj.setMessageId();
		chatObj.setMessageObj(chatObj.getMessageId(),messageObj);

	}

	public static void creatingChatObj(int contactId,String message){
		contact contactObj =(contact) contact.getContactList().get(contactId);
		chat chatObj = new chat();
		chatObj.setChatStatus("active");
		System.out.println("contact Id :"+contactObj.getContactId());
		chatObj.setContactId(contactId);
		chat.creatingMessageObj(chatObj,message);
		chat.setChat(chatObj.getChatId(),chatObj);
		System.out.println("New Chat Created  chat Id = "+chatObj.getChatId());
	}

	public static boolean contactIdExists(int id){
		Iterator<Entry<Integer,chat>> itr = chat.getChatList().entrySet().iterator();
		int contactId = 0;
		while(itr.hasNext()){
			Map.Entry<Integer,chat> chatObj =itr.next();
			contactId = chatObj.getValue().getContactId();
			if(contactId == id){
				return true;
			}
		}
		return false;
	}

	public static void gettingChatList(){
		Iterator<Entry<Integer,chat>> chatItr = chat.getChatList().entrySet().iterator();
		System.out.println("\tCHATS :\t");
		printingChat(chatItr,"active");
		System.out.println("\n\tARCHIVED CHATS :\t");
		printingChat(chatItr,"unActive");
	}

	public static void printingChat(Iterator<Entry<Integer,chat>> chatItr,String chatStatus){
		int contactId = 0;
		while(chatItr.hasNext()){
			Map.Entry<Integer,chat> chatList = chatItr.next();
			chat chatObj = chatList.getValue();
			contactId = chatObj.getContactId();
			if(chatObj.getChatStatus().equals(chatStatus)){
				contact contactObj = (contact)contact.getContactList().get(contactId);
				System.out.println("Chat Name : "+contactObj.getContactName());
				System.out.println("last Message :"+chatObj.getLastMessageObj(chatObj.getMessageId()).getMessage()+"\n");
			}
		}
	}

	public static void gettingSpecificChat(int chatId){
		if(chat.getChatList().containsKey(chatId)){
			chat chatObj = (chat)chat.getChatList().get(chatId);
			int contactId = chatObj.getContactId();
			contact contactObj = (contact)contact.getContactList().get(contactId);
			System.out.println("Chat Name :"+contactObj.getContactName());
			Iterator<Entry<Integer,message>> messageList = chatObj.getMessageList().entrySet().iterator();
			while(messageList.hasNext()){
				Map.Entry<Integer,message> messageObj = messageList.next(); 
				System.out.println(messageObj.getValue().getMessage()+"/n");
			}
		}
	}

	public static void deletingChat(int chatId){
		try{
			chat.getChatList().remove(chatId);
			System.out.print("Chat deleted ...\n");
		}	
		catch(Exception ex){
			System.out.println("Chat Id not found ...\n");
		}
	}

	public static void deletingMessage(int chatId,int msgId){
		if(chat.getChatList().containsKey(chatId)){
			chat chatObj = (chat)chat.getChatList().get(chatId);
			try{
				chatObj.getMessageList().remove(msgId);
				System.out.println("Message Deleted ...\n");

			}
			catch(Exception ex){
				System.out.println("Message Id not found ...\n");
			}	
		}
	}

	public static void archiveChat(int chatId,String chatStatus){
		if(chatList.containsKey(chatId)){
			if(chatStatus.equals("archive")){
				chatList.get(chatId).setChatStatus("unActive");
				System.out.println("Chat Archive successfully ...\n");
			}
			else{
				chatList.get(chatId).setChatStatus("active");	
			}
		}
	}
}

class message{
	private int chatId;
	private int contactId;
	private String message; 
	private String messageStatus;

	public void setChatId(int chatId){
		this.chatId = chatId;
	}

	public void setContactId(int contactId){
		this.contactId = contactId;		
	}

	public void setMessage(String message){
		this.message = message;
	}

	public void setMessageStatus(String incomingOrOutgoing){
		messageStatus = incomingOrOutgoing;
	}

	public int getChatId(){
		return chatId;
	}

	public int getContactId(){
		return contactId;
	}

	public String getMessage(){
		return message;
	}

	public String getMessageStatus(){
		return messageStatus;
	}
}

class myStatus{
	private static int myStatusId;
	private static HashMap<Integer,String> myStatusList = new HashMap<>();
	public static String recentMyStatus(){
		return myStatusList.get(myStatusId);
	}
	public static void postMyStatus(String status){
		myStatusId++;
		myStatusList.put(myStatusId,status);
		System.out.println("New status added .....");
	}
	public static void getMyStatus(){
		System.out.println("Status Id \t-\t status ");
		for (Map.Entry<Integer,String> entry : myStatusList.entrySet()){
           	System.out.println(entry.getKey()+"\t"+entry.getValue());
   		} 
	}
	public static void deletingMyStatus(int statusId){
		try{
			myStatusList.remove(statusId);
			System.out.println("Status deleted successfully ....");
		}	
		catch(Exception ex){
			System.out.println("Status Id not found .....");
		}
	}
	public static void forwardStatus(int statusId,int contactId){
		boolean fowarded = false;
		if(myStatusList.containsKey(statusId)){
			String forwardStatus = myStatusList.get(statusId);
			Iterator<Map.Entry<Integer, chat>> itr = chat.getChatList().entrySet().iterator();  
  			while(itr.hasNext()){ 
	           	Map.Entry<Integer, chat> entry = itr.next(); 
	           	chat chatObj = entry.getValue();
	           	if(chatObj.getContactId()==contactId){
	           		chat.creatingMessageObj(chatObj,forwardStatus);
	           		System.out.println("forwarded successfully ...");
	           		fowarded = true;
	           		break;
	           	}
			}
			if(!fowarded){
	     		chat.creatingChatObj(contactId,forwardStatus);
				System.out.println("forwarded successfully ...");
	     	}
		}
	}		
}

class status{	
	private int statusId;
	private boolean mute = false;
	private HashMap<Integer,String> statusList = new HashMap<>();
	public void setMutevalue(boolean mutevalue){
		mute = mutevalue;
	}
	public void setStatus(String status){
		statusId++;
		statusList.put(statusId,status);	
	}
	public boolean getMuteValue(){
		return mute;
	}
	public String getRecentStatus(){
		return statusList.get(statusId);
	}
	public HashMap getStatusList(){

		return statusList;
	}


	private static HashMap<Integer,status> contactStatusList= new HashMap<>();
	public static void postingStatus(int contactId,String status){
		if(contactStatusList.containsKey(contactId)){
			status statusObj = contactStatusList.get(contactId);
			statusObj.setStatus(status);
			System.out.println("Status added successfully ....");
		}
		else{
			if(contact.getContactList().containsKey(contactId)){
				status statusObj = new status();
				statusObj.setStatus(status);
				contactStatusList.put(contactId,statusObj);		
				System.out.println("Status posted successfully ....");
			}
		}
	} 
	public static void gettingAllStatus(){
		System.out.println("My Status : "+myStatus.recentMyStatus());
		System.out.println("\n\nContact Name \t - \t Recent Status \n");
		for (Map.Entry<Integer,status> entry : contactStatusList.entrySet()){
			contact contactObj = contact.getContactObject(entry.getKey());
			status statusObj = entry.getValue();
			if(!statusObj.getMuteValue()){
				System.out.println(contactObj.getContactName()+"\t"+statusObj.getRecentStatus());
			}
   		} 
	}
	
	public static void gettingSpecificStatus(int contactId){
		if(contactStatusList.containsKey(contactId)){
			contact contactObj = contact.getContactObject(contactId);
			System.out.println("Contact Name :"+contactObj.getContactName());
			status statusObj = (status)contactStatusList.get(contactId);
			HashMap list = statusObj.getStatusList();
			System.out.println("Status Id \t-\t status ");
			Iterator<Map.Entry<Integer, String>> itr = list.entrySet().iterator();          
  		    while(itr.hasNext()){ 
           	Map.Entry<Integer, String> entry = itr.next(); 
           	System.out.println(entry.getKey()+"\t"+entry.getValue()+"\n");
     		} 
		}
	}
	public static void muteContact(int contactId,String booleanValueForMute){
		if(contactStatusList.containsKey(contactId)){
			if(booleanValueForMute.equals("mute")){
				contactStatusList.get(contactId).setMutevalue(true);
				System.out.println("Status muted ...");
			}
			else{
				contactStatusList.get(contactId).setMutevalue(false);
				System.out.print("Status unmuted ....");
			}
			
		}
	}
	
	public static void statusReply(int contactId,int statusId,String replymessage){
		boolean replyPosted = false;
		if(contactStatusList.containsKey(contactId)){
			status statusObj = contactStatusList.get(contactId);
			if(statusObj.getStatusList().containsKey(statusId)){
				String status =(String) statusObj.getStatusList().get(statusId);
				Iterator<Map.Entry<Integer, chat>> itr = chat.getChatList().entrySet().iterator();  
				String replay = "Status : "+status+"\n reply message : "+replymessage;
  		    	while(itr.hasNext()){ 
           			Map.Entry<Integer, chat> entry = itr.next(); 
           			chat chatObj = entry.getValue();
           			if(chatObj.getContactId()==contactId){
           				chat.creatingMessageObj(chatObj,replay);
           				System.out.println("Reply posted successfully ...");
           				replyPosted = true;
           				break;
           			}
     			} 
     			if(!replyPosted){
     				chat.creatingChatObj(contactId,replay);
					System.out.println("Reply posted successfully ...");
     			}
			}
		}	
	}
	public static void processingStatusModule(String[] splittedURL){
		switch(splittedURL.length){
			case 2:
				if(splittedURL[0].equals("get")){
					gettingAllStatus();
				}
				else{
					System.out.println("Given URL is wrong (Method for the path not found ...)");
				}
				break;
			case 3:
				switch(splittedURL[0]){
					case "post":
						myStatus.postMyStatus(splittedURL[2]);
						break;
					case "get":
						try{
							int contactId = Integer.parseInt(splittedURL[2]);
							gettingSpecificStatus(contactId);
						}
						catch(Exception ex){
							if(splittedURL[2].equals("mystatus")){
								myStatus.getMyStatus();
							}
							else{
								System.out.println("Given URL is wrong (path parameter).....");
							}
						}
						break;
					case "delete":
						myStatus.deletingMyStatus(Integer.parseInt(splittedURL[2]));
						break;
					default:
							System.out.println("Given URL is wrong (Method for the path not found ...)");		
				}	
				break;
			case 4:
				switch(splittedURL[0]){
					case "post":
						postingStatus(Integer.parseInt(splittedURL[2]),splittedURL[3]);
						break;
					case "put":
						muteContact(Integer.parseInt(splittedURL[2]),splittedURL[3]);
						break;
					default:
						System.out.println("Given URL is wrong (Method for the path not found ...)");		
				}
				break;
			case 5:
				if(splittedURL[0].equals("post") && splittedURL[3].equals("forward")){
					int statusId = Integer.parseInt(splittedURL[2]);
					int contactId = Integer.parseInt(splittedURL[4]);
					myStatus.forwardStatus(statusId,contactId);
				}	
				else{
					System.out.println("Given URL is wrong (Method for the path not found ...)");
				}	
				break;
			case 6:
				if(splittedURL[0].equals("post") && splittedURL[4].equals("reply")){
					int contactId = Integer.parseInt(splittedURL[2]);
					int statusId = Integer.parseInt(splittedURL[3]);
					statusReply(contactId,statusId,splittedURL[5]);
				}
				else{
					System.out.println("Given URL is wrong (Method for the path not found ...)");
				}		
		}	
	}
}

class watsapp{	
	static Scanner inputScanner = new Scanner(System.in);
	public static void processURL(String URL){
		String[] splittedURL = URL.split(" /");
		if(splittedURL.length>=2){
			switch(splittedURL[1]){
				case "contacts":
					contact.processingContactModule(splittedURL);
					break;
				case "chats":
					chat.processingChatModule(splittedURL);
					break;
				case "status":
					status.processingStatusModule(splittedURL);
					break;	
				default:
					System.out.println("Given URL is Wrong (entity not found) ......\n");
			}
		}
		else{
			System.out.println("Given URL is not valid ...\n");	
		}
	}	

	public static void main(String[] args) {
		boolean getURL = true;
		do{
			System.out.println("\nType 'end' to Exit ...\n");
			System.out.println("Enter the URL :");
			String URL = inputScanner.nextLine();
			if(URL.equals("end")){
				getURL = false;	
			}
			else{
				processURL(URL);
			}
		}
		while(getURL);
	}
}
