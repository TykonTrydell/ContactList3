 /** Class of Objects called Contacts. Holds their names and numbers.
 * Implement a constructor and get/set methods, as well as a ToString
 * 2.0
 * Implements Comparable, overriding the compareTo method so that the parameter
 * contact is compared against the Object.
 * @author Daniel Holt
 * @version 2.0
 * Due Date: 4/14/2024
 */
public class Contact implements Comparable<Contact>{
	public String contactName;
	public String contactNumber;
	
	public Contact() {//default constructor
		this(null, null);
	}
	public Contact(String contactName, String contactNumber){//2 variable constructor
		this.contactName = contactName;
		this.contactNumber = contactNumber;
	}
	
	public String getName() {return contactName;}
	
	public void setName(String newName) {
		contactName = newName;
	}
	
	public String getNumber() {return contactNumber;}
	
	public void setNumber(String newNumber) {
		contactNumber = newNumber;
	}
	public String toString() {
		String result = String.format("Contact [%s: %s]", getName(), getNumber());
		return result;
	}
	@Override
	public int compareTo(Contact c) {
		int contactPosition = c.getName().compareTo(contactName);
		return contactPosition;
	}
}
