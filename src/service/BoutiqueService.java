/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author HP
 */

import entity.Boutique;
import entity.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.Myconnexion;

/**
 *
 * @author HP
 */
public class BoutiqueService {

    Connection con = Myconnexion.getInstance().getCnx();
    private Statement stmt;

    public BoutiqueService() {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
  public Boutique details(int id) throws SQLException {

        String requete = "select * from boutique where id ='" + id + "'";

        Boutique P = null;

        try {
           /* ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);*/
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
               P=(new Boutique(rs.getInt("id"),rs.getString("nom_boutique"),rs.getString("desc_boutique"),rs.getString("adresse_boutique"),rs.getString("image")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return P;
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

    public ArrayList<Boutique> afficher()throws SQLException  {
        ArrayList<Boutique> list = new ArrayList<>();

        
            String requete = "SELECT * FROM Boutique";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               list.add(new Boutique(rs.getInt("id"),rs.getString("nom_boutique"),rs.getString("desc_boutique"),rs.getString("adresse_boutique"),rs.getString("image")));
                  
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

    public boolean Modifier(int id, String nom_boutique, String desc_boutique, String adresse_boutique, String image) throws SQLException {
        
          PreparedStatement pre = con.prepareStatement(  "UPDATE Boutique SET nom_boutique= '" + nom_boutique + "' , desc_boutique= '" + desc_boutique + "' ,adresse_boutique= '" + adresse_boutique + "'  ,image= '" + image + "'   WHERE id='" + id + "' ;");
          

        JOptionPane.showMessageDialog(null, "Boutique modifiée avec succées");
        pre.executeUpdate();

        return true;
        
    }
}

