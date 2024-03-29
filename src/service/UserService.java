/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.User;
import entity.userSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Myconnexion;

/**
 *
 * @author skanderzouaoui
 */
public class UserService {
    Connection cnx;

    public UserService() {
        cnx = Myconnexion.getInstance().getCnx();
    }

    public void ajouter(User u) {
         try {

           String req = "insert into utilisateurs(nom,prenom,email,telephone,password,role,isActive)"
                    + "values( '" +u.getNom() + "','" + u.getPrenom() + "',"+ "'" + u.getEmail() + "',"
                    + "'" +u.getTelephone() + "'," + "'" + u.getPassword()+ "'," 
                    + "'" + u.getRole()+ "',"+1+")";
            System.out.println(req);
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Account created");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean login(String email,String password){
       
        try { 
           String req = "select * from utilisateurs where email= '"
                   +email
                   + "' and password= '"
                   +password+"'";
                   //+"' and isActive="+1;
           
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
                userSession.role=rs.getString("role");
                userSession.img=rs.getString("image");
                userSession.password = password;
                userSession.isActive = rs.getBoolean("isActive");
                
                
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
                    +u.getTelephone()+"' ,email='"+ u.getEmail()+"' ,password='"+u.getPassword()+"' ,image='"+u.getImg()+"' where id ="+userSession.id;
            System.out.println(req);
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Account updated"); 
            
                
                userSession.nom =u.getNom();
                userSession.prenom = u.getPrenom();
                userSession.email = u.getEmail();
                userSession.telephone = u.getTelephone();
                userSession.password =u.getPassword();
                userSession.img =u.getImg();
                
                
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void Bloquer(int id) {
        try {
            String req = "update utilisateurs set isActive= "+0+" where id ="+id;
            System.out.println(req);
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     public void Debloquer(int id) {
        try {
            String req = "update utilisateurs set isActive= "+1+" where id ="+id;
            System.out.println(req);
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public List recuperer() {
        List<User> users = new ArrayList<>();
        try {
            String req = "select * from utilisateurs";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setTelephone(rs.getString("telephone"));
                if(rs.getBoolean("isActive")){
                    u.setIsActive("Active");                
                }else{
                    u.setIsActive("Blocked"); 
                }
                
                switch (rs.getString("role")) {
                    case "[\"ROLE_USER\"]":
                        u.setRole("USER");
                        break;
                    case "[\"ROLE_ADMIN\"]":
                        u.setRole("ADMIN");
                        break;
                    case "[\"ROLE_LIV\"]":
                        u.setRole("LIVREUR");
                        break;
                    case "[\"ROLE_COMM\"]":
                        u.setRole("COMMERCANT");
                        break;
                    default:
                        break;
                }
                users.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    
    public List rechercher(String input) {
        List<User> users = new ArrayList<>();
        User u = new User();
        try {
            String req = "select * from utilisateurs where email='"+input+"' OR nom= '"+input+"' OR prenom= '"+input+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                
                u.setId(rs.getInt(1));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setTelephone(rs.getString("telephone"));
                
                switch (rs.getString("role")) {
                    case "[\"ROLE_USER\"]":
                        u.setRole("USER");
                        break;
                    case "[\"ROLE_ADMIN\"]":
                        u.setRole("ADMIN");
                        break;
                    case "[\"ROLE_LIV\"]":
                        u.setRole("LIVREUR");
                        break;
                    case "[\"ROLE_COMM\"]":
                        u.setRole("COMMERCANT");
                        break;
                    default:
                        break;
                }
                 users.add(u);
                       
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
                

        
    }
    public void updatePassword(String email,String password){
        
        try {
            String req = "update utilisateurs set password= '"+password+"' where email = '"+email+"'";
            System.out.println(req);
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        
    }
     public int countTotalCommande() {
        String req = "SELECT COUNT(*) as cu FROM Utilisateurs  ";
        ResultSet rs = null;
        try {
            Statement ste = Myconnexion.getInstance().getCnx().createStatement();
            rs = ste.executeQuery(req);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        int cu = 0;
        try {
            while (rs.next()) {
                cu = rs.getInt("cu");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return cu;
    }
   
}
