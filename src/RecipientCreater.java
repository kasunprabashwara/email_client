import java.time.LocalDate;
import java.time.format.*;


//this uses factory pattern to create recipients where the user can enter the details of the recipient and the type of recipient and the factory will create the recipient
public class RecipientCreater {
    public static EmailRecipient create(String details) throws ArrayIndexOutOfBoundsException,DateTimeParseException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            details.replaceAll("\\s+", "");
            String[] split=details.split(":",2);
            String[] split2=split[1].split(",");
                switch(split[0]){
                    case "Official":
                        return new OfficialRecipient(split2[0],split2[1],split2[2]);
                    case "OfficialFriend":
                        LocalDate date=LocalDate.parse(split2[3],formatter);  
                        return new OfficialFriend(split2[0],split2[1],split2[2],date);
                    case "Personal":
                        LocalDate date2= LocalDate.parse(split2[3],formatter);
                        return new PersonalRecipient(split2[0],split2[1],split2[2],date2);
                    default:
                        System.out.println("Wrong recipient type. Please enter a valid type");
                        return null;
                }
            }
            catch(DateTimeParseException d){
                System.out.println("Wrong input date. Please enter a valid date");
                return null;
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Wrong input format. Please fill out all the necessory fields");
                return null;
            }
    }
}