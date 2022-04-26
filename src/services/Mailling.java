/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mariem
 */
public class Mailling {
            private String username = "meddeb.mariem@esprit.tn";
            private String password = "213JFT3085";
            Reclamation E = new Reclamation();
            public void envoyer(Reclamation r) {
            // Etape 1 : Création de la session
            Properties props = new Properties();
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port","25");

            Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
            }
            });
            try {
            // Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("meddeb.mariem@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse("meddeb.mariem@esprit.tn"));
            message.setSubject("ProTECH");
            
            DateTimeFormatter Date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            System.out.println(Date.format(now));
            message.setText("La réclamation du client : "+r.getName()+" a été bien éditée le :"+Date.format(now));


            Transport.send(message);
            System.out.println("Message_envoye");
            } catch (MessagingException e) {
            throw new RuntimeException(e);
            } 
            }

}
