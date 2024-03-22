/**This program obtains file name from user to create a list of Contacts
 * then it calls a method to sort the contacts by alphabetic first name.
 * Displays a menu for user to choose from: Display contacts alphabetically,
 * Display contacts in reverse alphabetic order, Search for a contact,
 * or exit.
 * Have both types of search methods (linear and binary) to find a contact,
 *but use binary, since it is more efficient.
 * 2.0
 * Read contacts from Contacts BST instead of a list;
 * Use Binary Tree system to keep the contacts sorted.
 * modify menu to: Add Contact, Remove Contact, Display Contact, Search for Contact
 * and exit, using ContactsBST where needed.
 * 
* @author Daniel Holt
* @version 2.0
 * Due Date: 3/31/2024
 */
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class ContactSearchDriver implements ContactInterface {
		public static void main(String[] args) {
			Scanner input = new Scanner(System.in);
			//TODO use ContactsBST instead of a list, use insert method
			String inputfilename = getInputFileName(input);
			ContactsBST contactTree = fillContactList(inputfilename);
					
			int menucontrol = 0; //Variable that will control do.while and switch
			do {
				menucontrol = getMenuChoice(input);
				switch (menucontrol) {
					case MENU_ADD_A_CONTACT://add a contact
						Contact contactAdd = getFullContact(input);
						
						break;
					case MENU_REMOVE_A_CONTACT://remove a contact
						String contactRemove = getContactName(input);
						Contact tempContact = contactTree.Search(contactRemove);
							if (tempContact != null) {//if the contact does exist
								contactTree.Remove(contactRemove);//remove it
								System.out.println("Contact removed");//inform user
							}
							else {//otherwise, tell them that contact was not found.
								System.out.println("Contact not found.");
							}
						break;
					case MENU_DISPLAY_CONTACTS://display alphabetic
						contactTree.Print();
						break;//break out of switch
					case MENU_SEARCH_CONTACTS://search contact
						String contactSearch = getContactName(input);
						contactTree.Search(contactSearch);//call contactsBTS instead
						break;
					case MENU_EXIT:
						System.out.printf("%nGood-bye!");
						break;
					default:
						System.out.println("Error");//due to getMenuChoice method this should
						break;//never be seen, but program it anyways.
				}
			}while(menucontrol != MENU_EXIT); //end the do.while loop
			input.close(); //close out the scanner.
		}
		
		public static int getMenuChoice(Scanner input) {
			int numberselect = 0;
			String errormessage = "Error: Selection must be between " + MENU_ADD_A_CONTACT +
					" and " + MENU_EXIT + " inclusively.";
			do {
				System.out.println(""); //for readability
				System.out.println("Contact List");
				System.out.println("------------");
				System.out.println("Select one of the following operations:");
				System.out.printf("%d. Add a contact%n", MENU_ADD_A_CONTACT);
				System.out.printf("%d. Remove a contact%n", MENU_REMOVE_A_CONTACT);
				System.out.printf("%d. Display contacts in alphabetical order%n", MENU_DISPLAY_CONTACTS);
				System.out.printf("%d. Search contacts%n", MENU_SEARCH_CONTACTS);
				System.out.printf("%d. Exit%n",MENU_EXIT);
			
				try {
					System.out.print("Enter your selection here: ");
					numberselect = input.nextInt();
					if(numberselect < MENU_ADD_A_CONTACT || numberselect > MENU_EXIT) {
						System.out.printf("%n%s%n", errormessage);
					}	
				}
				catch(InputMismatchException inputMismatchException) {
					System.err.printf("%nException: %s%n", inputMismatchException);
					input.nextLine();//discard input so user can try again
					System.out.printf("%n%s%n", errormessage);
				}
				
			}while(numberselect < MENU_ADD_A_CONTACT || numberselect > MENU_EXIT);
			return numberselect;
		}//end method getMenuChoice
		
		
		public static String getInputFileName(Scanner input) {
			//Obtain the file name as input from user
			String inputfilename = null;
			boolean fileFound = false;
			do{//do this loop while the file isn't found
				System.out.printf("Enter input file name: ");
				inputfilename = input.next();
				File inputFile = new File(inputfilename);//try to open the file
				if (inputFile.exists()) {//if the file exists
					fileFound = true;//toggle that the file was found.
				}
				else {//otherwise, prompt to re-enter file name
					System.out.printf("File not found. Please try again.\n");
				}
			}while(!fileFound);//if it is found, they are let out.
			return inputfilename;
		}//end method getInputFileName
		
		public static List<Contact> fillContactList (String inputFileName) {
			//read a list of contacts from a file into a list<Contact>
			List<Contact> newList = new ArrayList<Contact>();
			try{
				File inFile = new File(inputFileName);
				Scanner filefinder = new Scanner(inFile);//reinitialize the incoming file
				while(filefinder.hasNext()) {//while there are more lines in the file
					String contactString = filefinder.nextLine();//take the next line in
					String[] str = contactString.split(" ");//split the string at the space
					String tempName = str[0] + " " + str[1];
					String tempNumber = str[2];
					Contact tempContact = new Contact(tempName, tempNumber);
					newList.add(tempContact);
				}
				filefinder.close();
			}
			catch(IOException e) {
				System.err.print("File not found. Please try again");
			}
			return newList;
		}
		public static Contact getFullContact(Scanner input) {
			input.nextLine();//clear the integer buffer
			System.out.printf("Enter new contact name: ");
			String tempName = input.nextLine();
			System.out.printf("Enter new contact number: ");
			String tempNumber = input.nextLine();
			Contact tempContact = new Contact(tempName, tempNumber);
			return tempContact;
		}
		public static String getContactName(Scanner input) {
			input.nextLine();//clear out the integer buffer
			System.out.printf("Enter contact name: ");
			String name = input.nextLine();
			return name;
		}	
}
