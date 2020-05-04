import java.util.*; 
/**
 * this class is essentially a linked list in the form of a database, storing a list of nodes where each
 * node is a contact
 * @author Cade
 */
public class Database<E extends DatabaseType<E>> implements Iterable<E>{ 
    /** head represents the beginning of elements in the Database*/
    private DBnode<E> head;
    
    /** hash will store different indexes to fast sort data*/
    private Hashtable<String, ArrayList<E>> hash = new Hashtable<String, ArrayList<E>>();
    
    /** numElements stores the number of elements currently stored in the database*/
    private int numElements = 0;
    
    /**
     * the constructor sets the first node to null
     *@constructor  
     */
    public Database() {
        this.head = null;
    }
    
    /**
     * this method adds  new node to the end of the list 
     * @param element is what is added to the database 
     */
    public void add(E element) {
        
        DBnode<E> newNode = new DBnode<E>(element);
        if(this.head == null) {
            this.head = newNode;
        }
        else {
            DBnode<E> current = this.head;
            // this loop finds the end of the nodes to add the new node to the end
            while(current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        } 
        this.numElements++;
        if (this.hash != null)
            this.hash.clear();
    }
    /**
     * this method deletes all occurences of a given element in the list
     * @param element is the element being deleted
     */
    public void delete(E element) {
        DBnode<E> current = this.head;
        DBnode<E> prev = new DBnode<E>(null);
        
        // this loop checks if the head and any nodes directy after have the element we want to delete
        while (current != null && current.element.equals(element)) {  
            this.head = current.next;  
            current = head;
            this.numElements--;
        }  
        
        // this loop goes through all nodes 
        while (current != null) {
            
            //this loop checks to see if the current node being isolated does NOT contain the element 
            while (current != null && !(current.element.equals(element))) {  
                prev = current;  
                current = current.next;  
            }  
            
            // if the element is not there for all nodes the method ends  
            if (current == null) {
                break;  
            }
            
            // if the while loop stopped it means current has the element so we want to jump over it  
            prev.next = current.next;  
            this.numElements--;
            //we then set the nodes to the next in line   
            current = prev.next;  
        } 
        if(this.hash != null)
            this.hash.clear();
    }
    
    
    
    /**
     * this mehtod simply gives the number of nodes in the database
     *@return the number of elements in the database 
     */
    public int size() {
        return this.numElements;
    }
    
    /**
     * this method finds the element at a given position
     * @param pos is the integer representation of the position, starting at 1
     * @return E the element at the given position
     */
    public E getAtIndex(int pos) {
        if(pos > this.size()) {
            return null;
        }
        DBnode<E> current = this.head;
        int i = 1;
        //this loop continues until the position is reached
        while(current.next != null && i < pos) {
            current = current.next;
            i++;
        }
        return current.element;
    }
    /**
     * this method looks through the database nodes for elements with a particular value based on the given comparator
     * @param element is the element all the elements in the list are compared against
     * @param comp is the comparator comparing the elements
     * @return Database<E> that contains all matching elements
     */
    public Database<E> lookupInList(E element, Comparator<E> comp) {
        Database<E> result = new Database<E>();
        DBnode<E> current = this.head;
        //this look iterates through the whole list of nodes and creates a new list of elements
        while(current != null) {
            if (comp.compare(current.element, element) == 0) {
                result.add(current.element); // if the element is compared to be the same it is added
            }
            current = current.next;
        }
        return result;
    }
    
    /**
     * this method looks in an arraylist for any elements that match a value in a given element using
     * a given comparator
     * @param element is the given element to compare against the elements in the arraylist
     * @param index is the arraylist that contains the elements being compared
     * @param comp is the comparator comparing the elements
     * @return Database<E> that has all elements from the arraylist determined equivalent to the given element
     */
    public Database<E> lookupInIndex(E element, ArrayList<E> index, Comparator<E> comp) {
        Database<E> result = new Database<E>();
        int elementIndex = Collections.binarySearch(index, element, comp);
        if(elementIndex < 0){
            return result;
        }
        else {
            int k = 0;
            //this loop goes through the index and adds all elements equal to the input value to the new linked list
            while(index.size() > k ) {
                if (comp.compare(index.get(k), element) == 0) {
                    result.add(index.get(k)); // if the element is compared to be the same it is added
                }
                k++;
            }
            return result;
        }
    }
    
    /**
     * this element makes an arraylist of elements sorted by a trait and sets it to the hashtable with the trait as 
     * the key
     * @param trait is the string representation of the trait used to create a comparator to compare all elements in 
     * the Database
     */
    public void makeIndex(String trait) {
        if(this.head != null) {
            Comparator<E> comp = head.element.getComparatorByTrait(trait);
            DBnode<E> current = this.head;
            ArrayList<E> elements = new ArrayList<E>();
            while(current != null){
                elements.add(current.element);
                current = current.next;
            }
            elements.sort(comp);
            this.hash.put(trait, elements); // creates a hashtable with trait as the key
        }
    }
    
    /**
     * this method looks first to the hashtable for an array and then to the database to compare the elements of the 
     * database to given element based on a given trait
     * @param element is the element that the elements of the database are compared to
     * @param trait is the trait that gives the corresponding comparator
     * @return Database<E> of elements that match the given element based on the given trait 
     */
    public Database<E> lookup(E element, String trait) {
        Database<E> result = new Database<E>();
        //Returns an empty LinkedList if the Database is empty
        if(this.head == null) {
            return result;
        }
        Comparator<E> comp = element.getComparatorByTrait(trait);
        //If the trait exists in the Hashtable, we lookup in the index
        if(hash.get(trait) != null) {
            System.out.println("looking in the hash table...");
            return lookupInIndex(element, hash.get(trait), comp);
        }
        //If the trait does not exist in the Hashtable, we lookup in the list
        else {
            return lookupInList(element, comp);
        }
    }
    
    
    /**
     * this function makes an arraylist based on a trait
     * @param trait is the new trait being used to make a hash table and order its corresponding arraylist
     * @return ArrayList<E> that will be composed of sorted elements based on the given trait 
     */
    public ArrayList<E> getList(String trait) {
        if (this.head == null){
            return null;
        }
        else if (hash.get(trait) != null) {
            return hash.get(trait);
        }
        this.makeIndex(trait);
        return hash.get(trait);
    }
    
    //-----------------------------------------------------------------------
    /**
     * non-default method from the Iterable interface
     * @return the iterable for the abstract data type
     */
    @Override
    public Iterator<E> iterator() {
        return new DatabaseIterator<E>(this.head);
    }
    /**
     * this is the class that handles the nodes in the Database
     */ 
    private class DBnode<E> { //should this be static 
        
        public E element;
        public DBnode<E> next;
        
        public DBnode(E e) {
            element = e;
            next = null;
        }
    }
    public class DatabaseIterator<E> implements Iterator<E> {
        /** stores the current position of nodes in the database list */
        private DBnode<E> current;
        
        /**
         * instantiates the iterator at the given node
         * @constructor
         * @param start is where the loop will begin
         */
        public DatabaseIterator(DBnode<E> start) {
            current = start;
        }
        
        /**
         * tells if there is a next node in the list
         * @return true or false coresponding to whether the end of the loop has been reached
         */
        public boolean hasNext() {
            return current != null;
        }
        
        /**
         * Generalizes the increment of the loop.  This method should throw an exception 
         * if we are at the end of the loop.
         * @return the next value in the list
         */
        public E next() {
            E element = current.element;
            current = current.next;
            return element;
        }
    }
    
}
