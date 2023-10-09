import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;

//this class is using the singleton design pattern because there should only be one outbox.
//this class is using the composite pattern as well to store the emails in an arraylist
class Outbox{
    private static Outbox outbox;       //singleton object instance
    private ArrayList<Email> emails;
    public static Outbox getOutboxInstance(){
        if(outbox==null){
            outbox=new Outbox();
        }
        return outbox;
    }
    private Outbox(){
        try{
        FileInputStream fileStream = new FileInputStream("emailList.ser");
        ObjectInputStream objects = new ObjectInputStream(fileStream);
        Object obj=objects.readObject();
        emails=(ArrayList<Email>) obj;
        objects.close();
        }catch(IOException e){
            emails=new ArrayList<Email>();
            File file=new File("emailList.ser");
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        catch(ClassNotFoundException c){
            c.printStackTrace();
        }
    }
    public void addEmails(Email email){
        emails.add(email);
    }
    public void findEmails(LocalDate date){
        for(int i=0;i<emails.size();i++){
            if(emails.get(i).getDatesend().equals(date)){
                emails.get(i).printDetails();
            }
        }
    }
    public void close(){
        try{
            FileOutputStream fileStream = new FileOutputStream("emailList.ser");
            ObjectOutputStream objects;
            objects = new ObjectOutputStream(fileStream);
            objects.writeObject(emails);
            objects.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
