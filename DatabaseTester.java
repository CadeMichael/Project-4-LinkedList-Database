import java.util.*; 
import org.junit.*;
import static org.junit.Assert.*;
/**
 * this class tests the databse class to make sure that it functions properly
 * @author Cade 
 */

public class DatabaseTester {
    Contact cade = new Contact("cade", "cade.lueker@gmail.com", "917-635-9021");
    Contact mike = new Contact("mike", "mike.meyers@gmail.com", "917-708-4567");
    Contact john = new Contact("john", "john.lenon@gmail.com", "917-668-1346");
    Contact sam = new Contact("sam", "sam.francis@gmail.com", "917-467-4543");
    Contact alex = new Contact("alex", "alex.jones@gmail.com", "917-345-2894");
    @Test
    public void testAddAndSize() {
        Database<Contact> d = new Database<Contact>();
        assertEquals(0, d.size());
        d.add(cade);
        assertEquals(1, d.size());
        d.add(mike);
        assertEquals(2, d.size());
        d.add(john);
        assertEquals(3, d.size());
        d.add(sam);
        assertEquals(4, d.size());
        d.add(alex);
        assertEquals(5, d.size());
        String desiredResult = cade.toString() +"\n";
        desiredResult += mike.toString() +"\n";
        desiredResult += john.toString() +"\n";
        desiredResult += sam.toString() +"\n";
        desiredResult += alex.toString()+ "\n";
        String s ="";
        for(Object x : d) {
            s+=(x + "\n"); 
        }
        assertEquals(desiredResult, s);
    }
    
    @Test
    public void testDeleteAndSize() {
        Database<Contact> d = new Database<Contact>();
        d.delete(cade);
        assertEquals(0, d.size());
        d.add(cade);
        assertEquals(1, d.size());
        d.delete(cade);
        assertEquals(0, d.size());
        d.add(cade);
        d.add(mike);
        d.add(john);
        d.add(sam);
        d.add(alex);
        d.delete(alex);
        String desiredResult = cade.toString() +"\n";
        desiredResult += mike.toString() +"\n";
        desiredResult += john.toString() +"\n";
        desiredResult += sam.toString() +"\n";
        String s ="";
        for(Object x : d) {
            s+=(x + "\n"); 
        }
        assertEquals(desiredResult, s);
        assertEquals(4, d.size());
        d.add(alex);
        d.delete(john);
        assertEquals(4, d.size());
        desiredResult = "";
        desiredResult = cade.toString() +"\n";
        desiredResult += mike.toString() +"\n";
        desiredResult += sam.toString() +"\n";
        desiredResult += alex.toString()+ "\n";
        s ="";
        for(Object x : d) {
            s+=(x + "\n"); 
        }
        assertEquals(desiredResult, s);
    }
    
    @Test 
    public void testGetAtIndex() {
        Database<Contact> d = new Database<Contact>();
        d.add(cade);
        d.add(mike);
        d.add(john);
        d.add(sam);
        d.add(alex);
        
        assertEquals(cade, d.getAtIndex(1));
        assertEquals(mike, d.getAtIndex(2));
        assertEquals(john, d.getAtIndex(3));
        assertEquals(sam, d.getAtIndex(4));
        assertEquals(alex, d.getAtIndex(5));
    } 
    
    @Test
    public void testLookupInList() {
        Database<Contact> d = new Database<Contact>();
        d.add(cade);
        d.add(mike);
        d.add(john);
        d.add(sam);
        d.add(alex);
        CompEmail cE = new CompEmail();
        CompName cN = new CompName();
        CompPhoneNum cP = new CompPhoneNum();
        
        //testing with email
        Database<Contact> d1 = new Database<Contact>();
        d1.add(cade);
        Contact t = new Contact("notcade","cade.lueker@gmail.com","but-has-same-email");
        assertEquals(d1.getAtIndex(1), (d.lookupInList(t,cE)).getAtIndex(1));

        //testing with phone 
        Database<Contact> d2 = new Database<Contact>();
        d2.add(mike);
        Contact t1 = new Contact("notmike","but has same phone","917-708-4567");
        assertEquals(d2.getAtIndex(1), (d.lookupInList(t1,cP)).getAtIndex(1));
        
        //testing with name
        Database<Contact> d3 = new Database<Contact>();
        d3.add(alex);
        Contact t2 = new Contact("alex","not alex","but-has-same-name");
        assertEquals(d3.getAtIndex(1), (d.lookupInList(t2,cN)).getAtIndex(1));
    }
    
    @Test 
    public void testLookupInIndex() {
        Database<Contact> d = new Database<Contact>();
        d.add(cade);
        d.add(mike);
        d.add(john);
        d.add(sam);
        d.add(alex);
        ArrayList<Contact> a = new ArrayList<Contact>();
        a.add(cade);
        a.add(mike);
        a.add(john);
        a.add(sam);
        a.add(alex);
        CompEmail cE = new CompEmail();
        CompName cN = new CompName();
        CompPhoneNum cP = new CompPhoneNum();
        
        //testing with email
        Database<Contact> d1 = new Database<Contact>();
        d1.add(cade);
        a.sort(cE);
        Contact t = new Contact("notcade","cade.lueker@gmail.com","but-has-same-email");
        assertEquals(d1.getAtIndex(1), (d.lookupInIndex(t, a, cE)).getAtIndex(1));
        
        //testing with phone 
        Database<Contact> d2 = new Database<Contact>();
        d2.add(mike);
        a.sort(cP);
        Contact t1 = new Contact("notmike","but has same phone","917-708-4567");
        assertEquals(d2.getAtIndex(1), (d.lookupInIndex(t1, a, cP)).getAtIndex(1));
        
        //testing with name
        Database<Contact> d3 = new Database<Contact>();
        d3.add(alex);
        a.sort(cN);
        Contact t2 = new Contact("alex","not alex","but-has-same-name");
        assertEquals(d3.getAtIndex(1), (d.lookupInIndex(t2, a, cN)).getAtIndex(1));
    }
    
    //getList will only function if makeIndex functions so by testing getList we can simultaniously test makeIndex
    @Test
    public void testGetListandMakeIndex() {
        Database<Contact> d = new Database<Contact>();
        d.add(cade);
        d.add(mike);
        d.add(john);
        d.add(sam);
        d.add(alex);
        //names sorted in reverse alphabetical order
        ArrayList<Contact> names = new ArrayList<Contact>();
        names.add(sam);
        names.add(mike);
        names.add(john);
        names.add(cade);
        names.add(alex);
        assertEquals(true, (names).equals(d.getList("name")));
        //emails sorted in reverse alphabetical order
        ArrayList<Contact> emails = new ArrayList<Contact>();
        emails.add(sam);
        emails.add(mike);
        emails.add(john);
        emails.add(cade);
        emails.add(alex);
        assertEquals(true, (emails).equals(d.getList("email")));
        //numbers sorted numerically
        ArrayList<Contact> nums = new ArrayList<Contact>();
        nums.add(mike);
        nums.add(john);
        nums.add(cade);
        nums.add(sam);
        nums.add(alex);
        assertEquals(true, (nums).equals(d.getList("phone")));
    }
    
    // if lookup works properly it will look to the hashtable before iterating through the whole database
    // if "looking in the hash table..." is printed then it is going through the hashtable. 
    @Test
    public void testLookup() {
        Database<Contact> d = new Database<Contact>();
        d.add(cade);
        d.add(mike);
        d.add(john);
        d.add(sam);
        d.add(alex);
        d.makeIndex("name");
        d.lookup(cade, "name");
        assertEquals(cade,(d.lookup(cade, "name")).getAtIndex(1));
        // from the interactions pane when the above code is run
        //looking in the hash table...
        //Database@5ff17bd
    }
}