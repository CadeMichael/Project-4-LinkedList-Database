import java.util.*;  
/** 
 * this class represents a contact that will store the contact info of a 
 * specific person
 * @author Cade 
 */
public class Contact implements DatabaseType<Contact> {
    
    /** name stores the contact's name*/
    private String name;
    /** email stores the contact's email*/
    private String email;
    /** phoneNum stores the contact's phone number*/
    private String phoneNum;
    
    /**
     * @constructor initializes what is to be stored as contact information in
     * the feilds
     */  
    public Contact(String n, String e, String p){
        this.name = n;
        this.email = e;
        this.phoneNum = p;
    }
    public Contact() {
        this.name = null;
        this.email = null;
        this.phoneNum =null;
    }
    
    /**
     * returns the name of the contact
     * @return name of contact
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * sets the value of name
     * @param n is the new name of the contact
     */
    public void setName(String n) {
        this.name = n;
    }
    
    /**
     * returns the email of the contact
     * @return email
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * sets the value of name
     * @param e is the new email of the contact
     */
    public void setEmail(String e) {
        this.email = e;
    }
    
    /**
     * returns the phone number of the contact
     * @return phoneNum
     */
    public String getPhoneNum() {
        return this.phoneNum;
    }
    
    /**
     * sets the value of name
     * @param n is the new name of the contact
     */
    public void setPhoneNum(String p) {
        this.phoneNum = p;
    }
    
    /**
     * overrides the toString method to present the contact in a more managable way
     * @return the string representation of the contact
     */
    @Override
    public String toString(){
        return "name: " + this.getName() + "\n email: " + this.getEmail() + "\n phone number: " + this.getPhoneNum();
    }
    
    /**
     * overrides the equals method to see if two contacts are the same
     * @param c is the contact the current contact is being compared to
     */
    @Override
    public boolean equals(Object c) {
        if(c instanceof Contact){
            return (this.getName().equals(((Contact)c).getName()) && this.getEmail().equals(((Contact)c).getEmail())
            && this.getPhoneNum().equals(((Contact)c).getPhoneNum()));
        }
        else{
            return false;
        }
    }
    
    /**
     * this method specifies what contacts should be compared by
     * @return a comparator that compares contacts by a specific feild
     */
    @Override
    public Comparator<Contact> getComparatorByTrait(String trait) {
        if (trait.equals("name")) {
            return new CompName();
        }
        else if (trait.equals("email")) {
            return new CompEmail();
        }
        else if (trait.equals("phone")) {
            return new CompPhoneNum();
        }
        return null;
    }
}
