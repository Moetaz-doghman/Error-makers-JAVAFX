/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Reclamation;
import entity.Reponse;
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
 * @author mariem
 */
public class ReclamationService implements IService<Reclamation>{

    
        Connection cnx;

    public ReclamationService() {
        cnx = Myconnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(Reclamation t) {
        
            String etat = "En cours";
            try {
                String req = "insert into reclamation(name,subject,message,etat,type,email) values( '"+ t.getName()+"', '"+ t.getSubject()+"', '"+ t.getMessage()+"', '"+etat+"', '"+t.getType()+"', '"+ t.getEmail()+"')" ;
                Statement st = cnx.createStatement(); //
                st.executeUpdate(req);  //executeUpdate nestaamlouha maa les fonction l kol ken l afficher
                System.out.println("reclamation ajoutée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @Override
    public void modifier(Reclamation t) {

         try {
            String req = "update reclamation set name = ?, subject= ?, message = ?, etat=?, type=?, email=? where id =?";
            PreparedStatement ps = cnx.prepareStatement(req);  //prepared statement nestaamlouha ki yabda 3ana des parametres à ajouter 
            ps.setString(1, t.getName());
            ps.setString(2, t.getSubject());
            ps.setString(3, t.getMessage());
            ps.setString(4, t.getEtat());
            ps.setString(5, t.getType());
            ps.setString(6, t.getEmail());
            ps.setInt(7, t.getId());
            ps.executeUpdate();
             System.out.println("reclamation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        
            try {
                String req = "Delete from `protech`.`reclamation` where `reclamation`.`id`  = '"+id+"';  ";
                Statement st = cnx.createStatement(); //
                st.executeUpdate(req);
                System.out.println("reclamation a été supprimée");
            } catch (SQLException ex) {
               System.out.println(ex.getMessage());
            }
    }

    @Override
    public List<Reclamation> recuperer() {
 List<Reclamation> reclamations = new ArrayList<>();
        try {
            String req = "select * from reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Reclamation p = new Reclamation();
                p.setId(rs.getInt("id")); //index selon base de donne 
                p.setName(rs.getString("name"));
                p.setSubject(rs.getString("subject"));
                p.setMessage(rs.getString("message"));
                p.setEtat(rs.getString("etat"));
                p.setType(rs.getString("type"));
                p.setEmail(rs.getString("email"));
                reclamations.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }    

    @Override
    public Reclamation recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterR(Reclamation t, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    //recherche par type
    public List<Reclamation> RechercherReclamation(String type) {
         List<Reclamation> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM reclamation where type LIKE '"+type+"%'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setSubject(rs.getString("subject"));
                r.setMessage(rs.getString("message"));
                r.setType(rs.getString("type"));
                r.setEmail(rs.getString("email"));


                
                list.add(r);
            }
    }
        catch(SQLException c){
            
        }
        return list ; 
    }
    
    
    public String recupererNom(int id) {
       
        
        Reponse p = new Reponse();
        String nom = new String() ; 
        try {
           
            String req = "select name from `protech`.`reclamation` where `reclamation`.`id` = '"+id+"';  ";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            
     
                while(rs.next()){
                nom = rs.getString("name"); //index selon base de donne 
               
                }  
        
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nom ;
    }

}
