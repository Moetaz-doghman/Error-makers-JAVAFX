/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.Demandes;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Myconnexion;

/**
 *
 * @author skanderzouaoui
 */
public class DemandeService {
    Connection cnx;

    public DemandeService() {
        cnx = Myconnexion.getInstance().getCnx();
    }
    
    public void ajouter(Demandes u) {
         try {

           String req = "insert into demandes(nom,prenom,email,telephone,password,role)"
                    + "values( '" +u.getNom() + "','" + u.getPrenom() + "',"+ "'" + u.getEmail() + "',"
                    + "'" +u.getTelephone() + "'," + "'" + u.getPassword()+ "'," 
                    + "'" + u.getRole()+ "')";
            System.out.println(req);
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Account created");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public List recuperer() {
        List<Demandes> dems = new ArrayList<>();
        try {
            String req = "select * from demandes";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Demandes u = new Demandes();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setTelephone(rs.getString("telephone"));
                
                
                switch (rs.getString("role")) {
                    case "[\"ROLE_LIV\"]":
                        u.setRole("LIVREUR");
                        break;
                    case "[\"ROLE_COMM\"]":
                        u.setRole("COMMERCANT");
                        break;
                    default:
                        break;
                }
                dems.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dems;
    }
     
    public void accepter(int id){
         try{
            String req = "select * from demandes where id="+id;

            
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                User d = new User();
                d.setId(rs.getInt("id"));
                d.setNom(rs.getString("nom"));
                d.setPrenom(rs.getString("prenom"));
                d.setEmail(rs.getString("email"));
                d.setTelephone(rs.getString("telephone"));
                d.setPassword(rs.getString("password"));
                d.setRole(rs.getString("role")); 
                
                UserService us = new UserService();
                us.ajouter(d);
                Supprimer(d.getId());

            }
            

        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
           
        }
        
    
         
     }
     
     public void Supprimer (int id) {
        try {
            String req = "delete from demandes where id ="+id;
            System.out.println(req);
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
      public int countTotalCommande() {
        String req = "SELECT COUNT(*) as cu FROM Demandes  ";
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
