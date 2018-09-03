package pl.coderslab.preschool_web_service.service;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import pl.coderslab.preschool_web_service.entity.Message;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.Collection;

public class EmailMessage {

    private ServletRequest se;
    private ArrayList<String> sendList;
    private String fromDescrpt;
    private Message message;

    private String emailFrom;
    private String emailAccount;
    private String emailPassword;
    private String emailHost;
    private String emailPort;

    public EmailMessage(ServletRequest se, ArrayList<String> sendList, String fromDescrpt, Message message) throws AddressException, EmailException {

        this.se = se;
        this.sendList = sendList;
        this.fromDescrpt = fromDescrpt;
        this.message = message;

        this.emailFrom = this.se.getServletContext().getInitParameter("emailFrom");
        this.emailAccount = this.se.getServletContext().getInitParameter("emailAccount");
        this.emailPassword = this.se.getServletContext().getInitParameter("emailPassword");
        this.emailHost = this.se.getServletContext().getInitParameter("emailHost");
        this.emailPort = this.se.getServletContext().getInitParameter("emailPort");

        try {

            Email email = new SimpleEmail();

            email.setHostName(emailHost);
            email.setSmtpPort(Integer.parseInt(emailPort));
            email.setAuthentication(emailAccount, emailPassword);
            email.setSSLOnConnect(true);

            email.setSubject(message.getTitle());
            email.setMsg(message.getMessage());

            Collection<InternetAddress> collectionTo = new ArrayList<>();
            for (String address: sendList){
                collectionTo.add(new InternetAddress(address));
            }

            email.setBcc(collectionTo);
            email.setFrom(emailFrom, fromDescrpt);

            email.send();

        } catch (AddressException addressException) {
            addressException.printStackTrace();

        } catch (EmailException emailException) {
            emailException.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
