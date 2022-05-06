/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author skanderzouaoui
 */

    

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.Myconnexion;


public class EmailService
{
     Connection cnx;

    public EmailService() {
        cnx = Myconnexion.getInstance().getCnx();
    }
    
            private String username = "noreply.prootech@gmail.com";
            private String password = "ProTECH123";
            
            public void envoyer(String code,String email) {
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
            message.setFrom(new InternetAddress("noreply.prootech@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(email));
            message.setSubject("ProTECH");
            
           
            message.setText(code+"  est votre code de récupération de compte ProTECH. ");


            Transport.send(message);
            System.out.println("Message_envoye");
            } catch (MessagingException e) {
            throw new RuntimeException(e);
            } 
            }
            
            public boolean emailExist(String email){
                
                try {
                    String req = "select * from utilisateurs where email= '"
                            +email+"'";
                    
                    PreparedStatement st = cnx.prepareStatement(req);
                    ResultSet rs = st.executeQuery(req);
                    
                    while(rs.next()){
                        System.out.println("Email Exist");
                        return true;
                        
                    }   
                } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                }
                return false;
            }
            
            public void InsertCodeEmail(String email,String code){
         try {
             String req = "insert into reset_password_request(selector,hashed_token) values( '"+code+"' ,'"+email+"')";
             System.out.println(req);
             
             Statement st = cnx.createStatement();
             st.executeUpdate(req);
             System.out.println("Code inserted");
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
    }
            
            public boolean codeValide(String email,String code){
                try {
                    String req = "select * from reset_password_request where selector= '"+email+"'";
                    System.out.println(req);
                    PreparedStatement st = cnx.prepareStatement(req);
                    ResultSet rs = st.executeQuery(req);
                    
                    while(rs.next()){
                        System.out.println("Email Exist");
                        if(code.equals(rs.getString("hashed_token"))){
                            
                            return true;  
                        }   
                    }   
                } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                }
                return false;
            }
            
            public void deleteCode(String email){
                try {
                    String req = "delete  from reset_password_request where selector= '"+email+"'";
                    Statement st = cnx.createStatement();
                    st.executeUpdate(req);
                    System.out.println("Code deleted");
                   } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                
            }

}
            
