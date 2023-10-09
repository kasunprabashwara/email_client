import java.time.*;

public class PersonalRecipient extends EmailRecipient implements BirthdayWishable{
    private String nickname;
    private LocalDate birthday;
    public PersonalRecipient(String name,String nickname,String address,LocalDate birthday){
        super(name,address);
        this.nickname=nickname;
        this.birthday=birthday;
    }
    public LocalDate getBirthday(){
        return this.birthday;
    }
    public void birthdayWish(){
        Email wish=new Email(address, "Happy birthday","Wish you a happy birthday. Have a blast.\nLove from Kasun");
        Outbox.getOutboxInstance().addEmails(wish);
        wish.send();
    }
    public void printDetails(){
        System.out.println("Name- "+name+"("+nickname+")");
    }
}