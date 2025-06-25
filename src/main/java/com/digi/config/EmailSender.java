package com.digi.config;

import java.util.Properties;
import jakarta.mail.*;


public class EmailSender {

    private static final String password = "ygkeidtowvhhmgbo";
    private static final String username = "bookingsystembook@yandex.ru";

    public static void sendEmail(String to, String subject, String text) {

        String host = "smtp.yandex.ru";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                getPasswordAuthentication() → {
                    return new PasswordAuthentication(username, password);
        });

        try {
            EmailUtil.sendEmail(session, to, subject, text);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
