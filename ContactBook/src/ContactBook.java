import java.util.*;

public class ContactBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Object> contactList = new LinkedList<>();  // New linked list containing objects (contacts)

        // Create new variable for user choice
        char choice;

        // Looping over the choices until user quits
        do {
            System.out.println("**************************************");
            System.out.println("(A)dd");
            System.out.println("(D)elete");
            System.out.println("(E)mail Search");
            System.out.println("(P)rint List");
            System.out.println("(S)earch");
            System.out.println("(Q)uit");
            System.out.println("**************************************");
            System.out.print("Please Enter a command: ");
            boolean found = false;
            choice = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();  
            
            // Switch between cases depending on user choice 
            switch (choice) {
                // Cases where input matches any of the displayed choices
                case 'A':
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, email);
                    contactList.add(newContact);
                    break;

                case 'D':
                    if(contactList.size() == 0){
                        System.out.println("Contact list is empty.");
                    }else{
                        System.out.print("Enter name to delete: ");
                        String deleteName = scanner.nextLine();
                        deleteNameFunction(contactList, deleteName, found, scanner);
                    }
                    break;

                case 'E':
                    if(contactList.size() == 0){
                        System.out.println("Contact list is empty.");
                    }else{
                        System.out.print("Enter name to search: ");
                        String searchEmail = scanner.nextLine();
                        for (Object item : contactList) {
                            Contact contact = (Contact) item;

                            // Case where there is/are contact(s) is on the list with the searchEmail substring
                            if (contact.email.toLowerCase().contains(searchEmail)) {
                                System.out.println("Name: " + contact.name + ", Phone: " + contact.phoneNumber + ", Email: " + contact.email);
                                found = true;
                            }
                        }

                        // Case where email isn't on any contact on the list
                        if (!found) {
                            System.out.println("Email not found.");
                        }
                    }
                    break;

                case 'P':
                    if(contactList.size() == 0){
                        System.out.println("Contact list is empty.");
                    }else{
                        for(Object item : contactList){
                            Contact contact = (Contact) item; 
                            System.out.println("Name: " + contact.name + ", Phone: " + contact.phoneNumber + ", Email: " + contact.email);
                        }
                    }
                    break;

                case 'S':
                    if(contactList.size() == 0){
                        System.out.println("Contact list is empty.");
                    }else{
                        System.out.print("Enter name to search: ");
                        String searchName = scanner.nextLine();
                        searchNameFunction(contactList, searchName, found);

                    }
                    break;

                case 'Q':
                    System.out.println("See you next time...");
                    break;
                
                // Case where user gives invalid options
                default:
                    System.out.println("Please enter a valid option!");
            }
        } while (choice != 'Q');
        scanner.close();
    }

    public static void searchNameFunction(LinkedList<Object> contactList, String searchName, boolean found){
        for (Object item : contactList) {
            Contact contact = (Contact) item;

            // Case where contact is on the list, printing every contact with the searchName substring
            if (contact.name.toLowerCase().contains(searchName)) {
                System.out.println("Name: " + contact.name + ", Phone: " + contact.phoneNumber + ", Email: " + contact.email);
                found = true;
                break;
            }
        }

        // Case where contact isn't on the list
        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    public static void deleteNameFunction(LinkedList<Object> contactList, String deleteName, boolean found, Scanner scanner){
        for (Object item : contactList) {
            Contact contact = (Contact) item;

            // Case where contact is on the list
            if (contact.name.equalsIgnoreCase(deleteName)) {
                System.out.println("Are you sure you want to delete the following contact? (Y/N)");
                System.out.println("Name: " + contact.name + ", Phone: " + contact.phoneNumber + ", Email: " + contact.email);
                String answer;
                found = true;

                do{
                    System.out.print("Y/N: ");
                    answer = scanner.nextLine();
                }while(!answer.toUpperCase().equals("Y") && !answer.toUpperCase().equals("N"));

                if(answer.toUpperCase().equals("Y")){
                    System.out.println("The contact has been deleted");
                    contactList.remove(contact);
                }
                else{
                    System.out.println("Operation cancelled.");
                }
                break;
            }
            
        }

        // Case where contact isn't on the list
        if (!found) {
            System.out.println("Contact not found.");
        }
        
    }
}

class Contact {
    String name;
    String phoneNumber;
    String email;
    Contact next;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.next = null;
    }
}