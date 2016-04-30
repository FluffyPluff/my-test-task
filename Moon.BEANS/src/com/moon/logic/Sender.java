package com.moon.logic;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import static com.moon.logic.SenderData.*;


public class Sender {

    private final SenderData senderData;
    private Properties props = System.getProperties();

    public Sender(SenderData senderData) {
        this.senderData = senderData;
    }



    private void setProps() {
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.mime.charset", "UTF-8");
    }



    public void sendMessage()  throws MessagingException,
            UnsupportedEncodingException {

        Authenticator auth = new MyAuthenticator(login, password);
        Session session = Session.getDefaultInstance(props, auth);
        Message msg = new MimeMessage(session);
        setProps();

        try {
            msg.setFrom(new InternetAddress(login));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(content);
            Transport.send(msg);
        } catch (Exception e) {}


    }


}
