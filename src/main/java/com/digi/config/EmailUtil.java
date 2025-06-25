package com.digi.config;

import jakarta.mail.Session;
import jakarta.mail.Message;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtil {

    static final String FROM_EMAIL = "bookingsystembook@yandex.ru";

    public static void sendEmail(Session session, String toEmail, String subject, String text) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            System.out.println("Sent message successfully");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
