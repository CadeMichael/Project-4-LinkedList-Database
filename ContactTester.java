import org.junit.*;
import static org.junit.Assert.*;
/**
 * this class tests the contact class to make sure that it functions properly
 * @author Cade 
 */

public class ContactTester {
    @Test 
    public void testGetters() {
        Contact c = new Contact("cade", "cade.lueker@gmail.com", "917-608-9021");
        
        assertEquals("cade", c.getName());
        assertEquals("cade.lueker@gmail.com",c.getEmail());
        assertEquals("917-608-9021",c.getPhoneNum());
    }
    
    @Test 
    public void testSetters() {
        Contact c = new Contact("cade", "cade.lueker@gmail.com", "917-608-9021");
        c.setName("Joe Shmo");
        c.setEmail("Joe.Shmo@gmail.com");
        c.setPhoneNum("111-222-3333");
        
        assertEquals("Joe Shmo", c.getName());
        assertEquals("Joe.Shmo@gmail.com",c.getEmail());
        assertEquals("111-222-3333",c.getPhoneNum());
    }
   
    @Test 
    public void testToString() {
        Contact c = new Contact("cade", "cade.lueker@gmail.com", "917-608-9021");
        String s = ("name: " + c.getName() + "\n email: " + c.getEmail() + "\n phone number: " + c.getPhoneNum());
        
        assertEquals(s, c.toString());
    }
    
    @Test
    public void testEquals() {
        Contact c = new Contact("cade", "cade.lueker@gmail.com", "917-608-9021");
        Contact c1 = new Contact("cade", "cade.lueker@gmail.com", "917-608-9021");
        Contact c2 = new Contact("john", "cade.lueker@gmail.com", "917-608-9021");
        Contact c3 = new Contact("cade", "john.lueker@gmail.com", "917-608-9021");
        Contact c4 = new Contact("cade", "cade.lueker@gmail.com", "917-608-9022");
        
        assertEquals(true,c.equals(c1));
        assertEquals(false,c.equals(c2));
        assertEquals(false,c.equals(c3));
        assertEquals(false,c.equals(c4));
    }
    
    @Test
    public void testComparators() {
        CompEmail cE = new CompEmail();
        CompName cN = new CompName();
        CompPhoneNum cP = new CompPhoneNum();
        Contact c = new Contact("cade", "cade.lueker@gmail.com", "917-608-9021");
        Contact c1 = new Contact("caddo", "caddo.lueker@gmail.com", "917-608-9021");
        Contact c2 = new Contact("john", "john.lueker@gmail.com", "817-608-9021");
        Contact c3 = new Contact("cade", "cade.secretemail@gmail.com", "927-608-9021");
        Contact c4 = new Contact("c", "cade.lueker@gmail.com", "917-608-9122");
        
        assertEquals(1, cN.compare(c,c2));
        assertEquals(0, cN.compare(c,c3));
        assertEquals(-1, cN.compare(c,c4));
        assertEquals(1, cN.compare(c1,c2));
        assertEquals(-1, cN.compare(c2,c3));
        
        assertEquals(-1, cE.compare(c,c1));
        assertEquals(0, cE.compare(c,c4));
        assertEquals(1, cE.compare(c,c3));
        assertEquals(-1, cE.compare(c2,c1));
        assertEquals(1, cE.compare(c3,c2));
        
        assertEquals(-1, cP.compare(c,c2));
        assertEquals(0, cP.compare(c,c1));
        assertEquals(-1, cP.compare(c3,c4));
        assertEquals(-1, cP.compare(c3,c1));
        assertEquals(1, cP.compare(c2,c4));
    }
    
}