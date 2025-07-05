package com.digi.config;

import java.util.Properties;

import com.digi.exceptions.UserApiException;
import jakarta.mail.*;


public class EmailSender {

    private static final String PASSWORD = "ygkeidtowvhhmgbo";
    private static final String USERNAME = "bookingsystembook@yandex.ru";

    public static void sendEmail(String to, String subject, String text) {

        String host = "smtp.yandex.ru";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                }
        );

        try {
            EmailUtil.sendEmail(session, to, subject, text);
        }catch (Exception e) {
            throw new UserApiException("Error while sending email");
        }
    }
}
