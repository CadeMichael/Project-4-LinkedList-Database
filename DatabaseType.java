import java.util.*; 
/**
 * @author Cade Lueker
 */

public interface DatabaseType<T> {
    /**
     * this method takes a trait and creates a comparator that compares two objects based on that trait
     * @param trait is a String that represents some trait heild by entries in the database
     */ 
    public Comparator<T> getComparatorByTrait(String trait);
}
