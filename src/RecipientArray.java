import java.io.File;  
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

//this class is using the composite pattern to store the recipients in an arraylist
//this class is also using the singleton design pattern
public class RecipientArray {
    private static RecipientArray recipientarray;       //singleton object
    private ArrayList<EmailRecipient> recipients=new ArrayList<EmailRecipient>();
    private File clientList;
    
    public static RecipientArray getRecipientArrayInstance(){
        if(recipientarray==null){
            recipientarray=new RecipientArray();
        }
        return recipientarray;
    }
    private RecipientArray(){
        try{
            clientList=new File("recipients.txt");
            clientList.createNewFile();
            Scanner scan=new Scanner(clientList);
            while(scan.hasNextLine()){
                recipients.add(RecipientCreater.create(scan.nextLine()));
            }
            scan.close();
        }
        catch(IOException e){
            System.out.println("An error occurred while creating the text file.");
        }
    }
    public int getRecpientCount(){
        return recipients.size();
    }
    //helper function to check whether a given recipient has a birthday today
    private boolean isBirthdayPerson(EmailRecipient res,int month,int day){      
        if(res instanceof BirthdayWishable){
            LocalDate birthday=((BirthdayWishable) res).getBirthday();
            if(birthday.getMonthValue()==month && birthday.getDayOfMonth()==day){
                return true;
            }
        }
        return false;
    }
    public void sendBirthdayEmails(){
        LocalDate today=LocalDate.now();
        for(int i=0;i<recipients.size();i++){
            if(isBirthdayPerson(recipients.get(i),today.getMonthValue(),today.getDayOfMonth())){
                ((BirthdayWishable) recipients.get(i)).birthdayWish();
            }
        }    
    }
    public void printBirthdayList(int month,int day){
        for(int i=0;i<recipients.size();i++){
            if(isBirthdayPerson(recipients.get(i),month,day)){
                recipients.get(i).printDetails();
            }
        }
    }
    public boolean addRecipient(String details){
        EmailRecipient temp=RecipientCreater.create(details);
        if(temp!=null){
            recipients.add(temp);
            try{
                FileWriter file=new FileWriter(clientList,true);
                file.write(details+"\n");
                file.close();
            }
            catch(IOException e){
                System.out.println("An error occurred while writing the text file.");
            }
            return true;
        }
        return false;
        
    }
}