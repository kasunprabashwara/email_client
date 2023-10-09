import java.time.*;

public class OfficialFriend extends OfficialRecipient implements BirthdayWishable {
    private LocalDate birthday;
    public OfficialFriend(String name,String address,String designation,LocalDate birthday){
        super(name,address,designation);
        this.birthday=birthday;
    }
    public LocalDate getBirthday(){
        return this.birthday;
    }
    public void birthdayWish(){
        Email wish=new Email(address, "Happy birthday","Wish you a happy birthday\nKasun Prabashwara");
        Outbox.getOutboxInstance().addEmails(wish);
        wish.send();
    }
}