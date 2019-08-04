package com.rahnema.utill;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Properties;

public class MailManager {

    private void sendSSLMessage(String recipients[], String subject,
                                String message, String from, String smtp_host_address,
                                String smtp_port_number, Authenticator authenticator,
                                String content_type) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", smtp_host_address);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", smtp_port_number);
        props.put("mail.smtp.socketFactory.port", smtp_port_number);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");


        Session session = Session.getInstance(props, authenticator);
        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);
        msg.setSubject(subject);
        msg.setContent(message, content_type);
        Transport.send(msg);

    }



    public boolean sendMessage(
            String smtp_host_address,
            String smtp_port_number,
            String from, String[] to,
            String subject,
            String message,
            String content_type,
            Authenticator authenticator
    ) throws Exception {

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        new MailManager().sendSSLMessage(to, subject,
                message, from, smtp_host_address, smtp_port_number, authenticator, content_type);
        return true;
    }

}
