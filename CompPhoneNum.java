import java.util.*;  
/**
 * this class is a comparator that sorts numbers numerically
 * @author Cade 
 */
public class CompPhoneNum implements Comparator<Contact>{
    /**
     * this method returns an int letting you know if two contacts' phone numbers are the same or if one is 
     * larger/smaller numerically
     * @param a represents the first object being compared
     * @param b represents the second object being compared
     * @return int corresponding to the equivalence of the numbers of the respective given contacts 
     */
    @Override
    public int compare(Contact a, Contact b) {
        String numA = a.getPhoneNum().replace("-","");
        String numB = b.getPhoneNum().replace("-","");
        int shorterString = (numA.length() < numB.length())? numA.length(): numB.length();
        int i = 0;
        // this loop goes through the strings until it finds a difference or reaches the end
        while (i < shorterString){
            if (numA.charAt(i) > numB.charAt(i)) {
                return -1; // this means that number b comes before number a
            }
            else if (numA.charAt(i) < numB.charAt(i)) {
                return 1; // this means that number a comes before number b
            }
            i++;
        }
        return 0; // both numbers are equal, all phone numbers should be same length
    }
}