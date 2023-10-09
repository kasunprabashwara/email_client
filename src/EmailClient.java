import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.nio.file.*;

public class EmailClient {
    private static int recipientCount;
    private static RecipientArray recipients;
    private static Outbox outbox;
    private static String email;
    private static String password;

    public static void main(String[] args) {

        // Read the email and password from the "credentials" file if it exists, otherwise prompt the user to enter them
        // once the user enters the email and password, they will be stored in the "credentials" file
        File credentialsFile = new File("credentials");
        try {
            // Read or create the "credentials" file
            if (credentialsFile.exists()) {
                Scanner scan = new Scanner(credentialsFile);
                email = scan.nextLine();
                password = scan.nextLine();
                scan.close();
            } else {
                Scanner userInput = new Scanner(System.in);
                System.out.println("Enter your email address:");
                email = userInput.nextLine();
                System.out.println("Enter your password:");
                password = userInput.nextLine();

                try {
                    credentialsFile.createNewFile();
                    FileWriter file = new FileWriter(credentialsFile, true);
                    file.write(email + "\n" + password + "\n");
                    file.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while creating the credentials file.");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the credentials file.");
            e.printStackTrace();
        }

        //initiating recipient array by creating objects using the text file
        recipients=RecipientArray.getRecipientArrayInstance();   
        //sending birthday emails to all the PersonalRecipients and OfficialFriends who have birthday today  
        recipients.sendBirthdayEmails();                    
        recipientCount=recipients.getRecpientCount();
        outbox=Outbox.getOutboxInstance();              //initiating outbox by deserializing email objects
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Scanner scanner = new Scanner(System.in);  
        System.out.println("Enter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays on a given date\n"
                + "4 - Printing out details of all the emails sent on a paticular day\n"
                + "5 - Printing out the number of recipient objects in the application\n"
                + "6 - Close the client");    
        //menu will be displayed until the user enters 6
        loop:while(true){
            String option = scanner.nextLine();
            switch(option){
                case "1":
                    System.out.println("Insert details- \n\nvalid formats,\nOfficial: nimal,nimal@gmail.com,ceo\n"
                    +"OfficialFriend: kamal,kamal@gamail.com,clerk,1990/02/12\n"
                    +"Personal: wimal,<nickname>,wimal@gmail.com,2000/01/22");
                    String details=scanner.nextLine();
                    if(recipients.addRecipient(details)){
                        System.out.println("Recipient added successfully");
                        recipientCount++;
                    }     
                    break;
                case "2":
                    System.out.println("Enter your recievers address-");
                    String to=scanner.nextLine();
                    System.out.println("Enter the subject of the email-");
                    String subject=scanner.nextLine();
                    System.out.println("Enter your massage-");
                    String content=scanner.nextLine();
                    Email email=new Email(to,subject,content);
                    email.send();
                    outbox.addEmails(email);
                    System.out.println("Email sent");
                    break;
                case "3":
                    System.out.println("Insert date(format - MM/dd ex: 09/17)");
                    String date=scanner.nextLine();
                    recipients.printBirthdayList(Integer.parseInt(date.substring(0,2)),Integer.parseInt(date.substring(3,5)));
                    break;
                case "4":
                    System.out.println("insert date(format - yyyy/MM/dd ex: 2018/09/17)"); 
                    try{
                        outbox.findEmails(LocalDate.parse(scanner.nextLine(),formatter));
                    }
                    catch(DateTimeParseException d){
                        System.out.println("Wrong input date.Please enter a valid date");
                    }
                    break;
                case "5":
                    System.out.println("Number of recipents- : "+recipientCount);
                    break;
                case "6":
                    outbox.close();     //this will serialize email objects too
                    scanner.close();
                    System.out.println("Application closed");
                    break loop;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
