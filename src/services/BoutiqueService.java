/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author HP
 */

import entities.Boutique;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import util.MyDB;

/**
 *
 * @author Aicha
 */
public class BoutiqueService {

    Connection con = MyDB.getInstance().getConnection();
    private Statement stmt;

    public BoutiqueService() {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void ajouterBoutique(Boutique B) {
        BoutiqueService cs = new BoutiqueService();
        try {
            String requete = "INSERT INTO Boutique (nom_boutique,desc_boutique,adresse_boutique,image) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, B.getNomBoutique());
            pst.setString(2, B.getDescBoutique());
            pst.setString(3,B.getAdresseBoutique());
            pst.setString(4,B.getImg());

            

            pst.executeUpdate();
            System.out.println("Boutique  ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<Boutique> afficherB()throws SQLException  {
        ArrayList<Boutique> list = new ArrayList<>();

        
            String requete = "SELECT * FROM Boutique";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               list.add(new Boutique(rs.getInt("id"),rs.getString("nomBoutique"),rs.getString("descBoutique"),rs.getString("adresseBoutique"),rs.getString("img")));
                  
               }

        

        return list;
    }

    public void supprimer(int id) {
 try {
            String requete = "DELETE FROM Boutique WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println(" supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }

    public boolean Modifier(int id, String nomBoutique, String descBoutique, String adresseBoutique, String img) throws SQLException {
        
          PreparedStatement pre = con.prepareStatement(  "UPDATE Boutique SET nom_boutique= '" + nomBoutique + "' , desc_boutique= '" + descBoutique + "' ,adresse_boutique= '" + adresseBoutique + "'  ,img= '" + img + "'   WHERE id='" + id + "' ;");
          

        JOptionPane.showMessageDialog(null, "Boutique modifiée avec succées");
        pre.executeUpdate();

        return true;
        
    }
}

