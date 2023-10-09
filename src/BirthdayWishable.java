import java.time.LocalDate;

//some recipients have birthdays (PersonalRecipient,OfficialFriend) and some don't (OfficialRecipient)
public interface BirthdayWishable{
    public void birthdayWish();
    public LocalDate getBirthday();
}