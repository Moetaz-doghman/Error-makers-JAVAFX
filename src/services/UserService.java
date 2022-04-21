/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.User;
import entities.userSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import util.MyDB;

/**
 *
 * @author skanderzouaoui
 */
public class UserService implements IService {
    Connection cnx;
    static Cipher cipher; 

    public UserService() {
        cnx = MyDB.getInstance().getConnection();
    }


    public void ajouter(User u) {
         try {
//              String req = "insert into utilisateurs(nom,prenom,email,telephone,password)"
//                    + "values( '" + u.getNom() + "', '" + u.getPrenom() + "',"
//                    + "" + u.getEmail() + ")";
//             System.out.println(req);
             
           String req = "insert into utilisateurs(nom,prenom,email,telephone,password,role)"
                    + "values( '" +u.getNom() + "','" + u.getPrenom() + "',"+ "'" + u.getEmail() + "',"
                    + "'" +u.getTelephone() + "'," + "'" + u.getPassword()+ "'," 
                    + "'" + u.getRole()+ "')";
            //System.out.println(req);
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Account created");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean login(String email,String password){
       
        try { 
           String req = "select id,nom,prenom,email,telephone,password from utilisateurs where email= '"
                   +email
                   + "' and password= '"
                   +password
                   +"'";
           
            System.out.println(req);
            
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                System.out.println("Success");
                
                userSession.id= rs.getInt("id");
                userSession.nom = rs.getString("nom");
                userSession.prenom = rs.getString("prenom");
                userSession.email = rs.getString("email");
                userSession.telephone = rs.getString("telephone");
                userSession.password = rs.getString("password");
                userSession.isLoggedIn=true;
                
                return true;
            }

        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
           
        }
        return false;
        
    }

    public void modifier(User u) {
        try {
            String req = "update utilisateurs set nom='"+u.getNom()+"' ,prenom ='" +u.getPrenom() + "' ,telephone ='"
                    +u.getTelephone()+"' ,email='"+ u.getEmail()+"' ,password='"+u.getPassword()+"' where id ="+userSession.id;
            System.out.println(req);
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Account updated"); 
            
                
                userSession.nom =u.getNom();
                userSession.prenom = u.getPrenom();
                userSession.email = u.getEmail();
                userSession.telephone = u.getTelephone();
                userSession.password =u.getPassword();
                
                
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List recuperer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ajouter(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modifier(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     
      public static String decrypt(String encryptedText, SecretKey secretKey)
            throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }
    
    
    
}
