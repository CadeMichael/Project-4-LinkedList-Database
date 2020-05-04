import java.util.*;

/**
 * this is the driver class for the project, it allows for a demonstration of database with contacts
 * @author cade
 */
public class ContactDatabase extends Database<Contact>{
    private static Database<Contact> contactDB = new Database<Contact>();
    private static Database<Contact> lastPrinted;
    private static ArrayList<Contact> lastPrintedAL;
    private static Boolean lastWasAL;
    
    /**
     * this method iterates through either a database or arraylist and prints each object one by one
     * @param i is an iterable class in java in this case it is an arraylist or database
     */
    public static void printList(Iterable i) {
        if (i != null) {
            Iterator iter  = i.iterator();
            int num = 1;
            while(iter.hasNext()) {
                System.out.println(num + "\n" + iter.next());
                num++;
            }
            
        }
    }
    
    /**
     * the main method "drives" the class and takes arguments from the user
     * @param args becomes system.in and tells the program what to do
     */
    public static void main(String[] args) {
        System.out.println("command: ");
        /** scan is a scanner that holds the user input */
        Scanner scan = new Scanner(System.in);
        /** comA represents the command to initiate a series of functions by the program */
        String comA = (scan.nextLine());
        
        //a
        //until the next command is quit the program will keep asking for more input from the user
        while (!(comA.toLowerCase().equals("quit"))) {
            
            //b
            if (comA.equals("add")) {
                System.out.print("enter name---------->");
                String name = scan.nextLine();
                System.out.print("enter email--------->");
                String email = scan.nextLine();
                System.out.print("enter phone number-->");
                String phone = scan.nextLine();
                Contact c = new Contact(name, email, phone);
                contactDB.add(c);
                System.out.println("added: "+ name + " " + email +" "+phone);
            }
            //c
            else if (comA.equals("listby")) {
                System.out.print("trait-->");
                String trait = scan.nextLine();
                lastPrintedAL = contactDB.getList(trait);
                lastWasAL = true;
                System.out.println("Printing listby: " + trait );
                printList(lastPrintedAL);
            }
            //d
            else if (comA.equals("find")) {
                Contact c = new Contact();
                System.out.print("trait-->");
                String trait = scan.nextLine();
                System.out.print("value-->");
                String value = scan.nextLine();
                if(trait.equals("name")) {
                    c.setName(value);
                }
                else if(trait.equals("email")) {
                    c.setEmail(value);
                }
                else if(trait.equals("phone")) {
                    c.setPhoneNum(value);
                }
                lastPrinted = contactDB.lookup(c, trait);
                lastWasAL = false;
                printList(lastPrinted);
            }
            //e
            else if (comA.equals("delete")) { 
                if(lastWasAL  != null){
                    System.out.print("position-->");
                    String num = scan.nextLine();
                    int pos = Integer.parseInt(num) - 1;
                    if (lastWasAL) {
                        contactDB.delete(lastPrintedAL.get(pos));
                    }
                    else if(!(lastWasAL)){
                        contactDB.delete(lastPrinted.getAtIndex(pos));
                    }
                    else {
                        System.out.println("Print a list to use delete");
                    }
                }
                else {
                    System.out.println("please list before you delete to ensure you know the position");
                }
                
            }
            //f
            else if (comA.equals("makeindex")) {
                System.out.print("trait-->");
                String trait = scan.nextLine();
                contactDB.makeIndex(trait);
            }
            else{
                System.out.println("please enter a valid command");
            }
            
            System.out.println("command: ");
            comA = scan.nextLine();
        }
        System.exit(0);
    }
}