import java.util.*;  
/**
 * this class is a comparator that sorts emails alphabetically
 * @author Cade 
 */
public class CompEmail implements Comparator<Contact>{
    /**
     * this method returns an int letting you know if two contacts' emails are the same or if one is larger/smaller
     * alphabetically
     * @param a represents the first object being compared
     * @param b represents the second object being compared
     * @return int corresponding to the equivalence of the emails of the respective given contacts 
     */
    @Override
    public int compare(Contact a, Contact b) {
        String emailA = a.getEmail().replace(" ","").replace(".","").toLowerCase();
        String emailB = b.getEmail().replace(" ","").replace(".","").toLowerCase();
        int shorterString = (emailA.length() < emailB.length())? emailA.length(): emailB.length();
        int i = 0;
        // this loop goes through the strings until it finds a difference or reaches the end
        while (i < shorterString){
            if (emailA.charAt(i) > emailB.charAt(i)) {
                return -1; // this means that email b comes before email a
            }
            else if (emailA.charAt(i) < emailB.charAt(i)) {
                return 1; // this means that email a comes before email b
            }
            i++;
        }
        if (emailA.length() > emailB.length()) {
            return -1; 
        }
        else if(emailA.length() < emailB.length()) {
            return 1; // shorter one goes first
        }
        return 0; // both are equal in value and length
    }
}