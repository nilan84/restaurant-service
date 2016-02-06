package lk.uoc.mit.service.controller;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by nilan on 2/6/16.
 */
public class JavaMail {
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;


    public void generateAndSendEmail(String email,String name,Long orderNo) throws AddressException, MessagingException {

// Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

// Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("nilan_jayasekara@yahoo.com"));
        generateMailMessage.setSubject("Just ready you order..");
        String emailBody = "Dear "+name+"<br><br> just start deliver your food item.Order No "+orderNo + "<br><br> Regards, <br>RMS Admin";
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");

// Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

// Enter your correct gmail UserID and Password
// if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", "nilan.rukshan@gmail.com", "fodxrendfdlspoio");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
