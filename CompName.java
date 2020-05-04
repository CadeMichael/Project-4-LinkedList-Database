import java.util.*;  
/**
 * this class is a comparator that sorts name alphabetically 
 * @author Cade 
 */
public class CompName implements Comparator<Contact>{
    /**
     * this method returns an int letting you know if two contacts' names are the same or if one is larger/smaller
     * alphabetically
     * @param a represents the first object being compared
     * @param b represents the second object being compared
     * @return int corresponding to the equivalence of the names of the respective given contacts 
     */
    @Override
    public int compare(Contact a, Contact b) {
        String nameA = a.getName().replace(" ","").toLowerCase();
        String nameB = b.getName().replace(" ","").toLowerCase();
        int shorterString = (nameA.length() < nameB.length())? nameA.length(): nameB.length();
        int i = 0;
        // this loop goes through the strings until it finds a difference or reaches the end
        while (i < shorterString){
            if (nameA.charAt(i) > nameB.charAt(i)) {
                return -1; // this means that name b comes before name a
            }
            else if (nameA.charAt(i) < nameB.charAt(i)) {
                return 1; // this means that name a comes before name b
            }
            i++;
        }
        if (nameA.length() > nameB.length()) {
            return -1; // shorter one goes first
        }
        else if(nameA.length() < nameB.length()) {
            return 1; // shorter one goes first
        }
        return 0; // both are equal in value and length
    }
}