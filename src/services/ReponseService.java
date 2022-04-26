/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import entities.Reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

/**
 *
 * @author mariem
 */
public class ReponseService implements IService<Reponse> {

     Connection cnx;

    public ReponseService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    @Override
    public void ajouterR(Reponse t,int id) {
        
        
        
         try {
                String req = "insert into reponse(reclamation_id,subject,message,created_at) values( '"+id+"','"+ t.getSubject()+"', '"+ t.getMessage()+"', '"+ t.getCreated_at()+"')" ;
                Statement st = cnx.createStatement(); //
                st.executeUpdate(req);  //executeUpdate nestaamlouha maa les fonction l kol ken l afficher
                System.out.println("reponse ajoutée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @Override
    public void modifier(Reponse t) {
        try {
            String req = "update reponse set subject= ?, message = ?, created_at=? where id =?";
            PreparedStatement ps = cnx.prepareStatement(req);  //prepared statement nestaamlouha ki yabda 3ana des parametres à ajouter 
            
            ps.setString(1, t.getSubject());
            ps.setString(2, t.getMessage());
            ps.setString(3, t.getCreated_at());
            ps.setInt(4, t.getId());
            ps.executeUpdate();
             System.out.println("reponse modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }

    @Override
    public void supprimer(int id) {
            try {
                String req = "Delete from `protech`.`reponse` where `reponse`.`id`  = '"+id+"';  ";
                Statement st = cnx.createStatement(); //
                st.executeUpdate(req);
                System.out.println("reponse a été supprimée");
            } catch (SQLException ex) {
               System.out.println(ex.getMessage());
            }
    }

    @Override
    public List<Reponse> recuperer() {
    List<Reponse> reponses = new ArrayList<>();
    try {
            String req = "select * from reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Reponse p = new Reponse();
                p.setId(rs.getInt(1)); //index selon base de donne 
                p.setSubject(rs.getString("subject"));
                p.setMessage(rs.getString("message"));
                reponses.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reponses;
    }

    @Override
    public Reponse recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(Reponse t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
