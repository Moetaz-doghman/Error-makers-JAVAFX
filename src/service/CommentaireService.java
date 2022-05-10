/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Commentaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static service.Service.connection;

/**
 *
 * @author Asus
 */
public class CommentaireService implements Service<Commentaire> {

    @Override
    public Boolean insert(Commentaire c) {
        try {
            String req = "INSERT into `commentaires` ("
                    + "`annonces_Id`, `contenu`, `email`, `pseudo`) "
                    + "VALUES("
                    + " ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(req);
            Integer i = 0;
            pst.setInt(++i, c.getAnnoncesId());
            pst.setString(++i, c.getContenu());
            pst.setString(++i, c.getEmail());
            pst.setString(++i, c.getPseudo());
            return pst.executeUpdate() > 0;
        } catch (SQLException ee) {
            System.err.println(ee.getMessage());
        }

        return false;
    }

    
    public List<Commentaire> findd() {
        List<Commentaire> l = new ArrayList<>();
        try {
            String req = "SELECT * FROM `commentaires`"
                    + ";";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new Commentaire(
                rs.getInt("id"),
                rs.getInt("annonces_Id"),
                rs.getInt("Parent_Id"),
                rs.getString("Contenu"),
                rs.getInt("Active"),
                rs.getString("Email"),
                rs.getDate("Created_At"),
                rs.getString("Pseudo")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return l;
    }
    public List<Commentaire> findByEvenement(int evenementId) {
        List<Commentaire> l = new ArrayList<>();
        try {
              String req = "SELECT * FROM commentaires"
                    + " WHERE annonces_id  ="+evenementId
                    + ";"
                 ;
            System.out.println(req);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                l.add(new Commentaire(
                rs.getInt("id"),
                rs.getInt("annonces_id"),
                rs.getInt("Parent_Id"),
                rs.getString("contenu"),
                rs.getInt("Active"),
                rs.getString("email"),
                rs.getDate("Created_At"),
                rs.getString("pseudo")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return l;
    }

    @Override
    public Boolean delete(int id) {
 try {
            String req = "DELETE FROM `commentaires` "
                    +"WHERE id="+id
                    + ";";
            Statement s = connection.createStatement();
            System.out.println(req);

            return s.executeUpdate(req) > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public Boolean modify(Commentaire instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commentaire> find(Integer offset, Integer rowCount, String filter, String order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
