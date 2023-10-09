import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Properties;


//this implements serializable so that the object can be written to a file
//at the start of the program the recipients will be read from the file and stored in composite outbox class
//ath the end of the program the recipients will be written to the file from the composite outbox class
public class Email implements Serializable{
    private LocalDate datesend;
    private String to,subject,content;
    public Email(String to,String subject,String content){
        this.to=to;
        this.subject=subject;
        this.content=content;
        this.datesend=LocalDate.now();
    }
    public LocalDate getDatesend() {
        return datesend;
    }
    public void printDetails(){
        System.out.println("Sent to- "+to+"\nSubject- "+subject);
    }

    //this method uses the javax.mail package to manage and send the emails using SMTP protocol.
    public void send(String username, String password) {
        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");     
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}