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

import entities.Produit;
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
public class ProduitService {

    Connection con = MyDB.getInstance().getConnection();
    private Statement stmt;

    public ProduitService() {
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void ajouterProduit(Produit P) {
        ProduitService cs = new ProduitService();
        try {
            String requete = "INSERT INTO Produit (prix_produit,quantite_produit,desc_produit,image,nom_produit) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, P.getPrix_produit());
            pst.setString(2, P.getQuantite_produit());
            pst.setString(3, P.getDesc_produit());
            pst.setString(4, P.getImage());
            pst.setString(5, P.getNom_produit());

            

            pst.executeUpdate();
            System.out.println("Produit  ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<Produit> afficher()throws SQLException {
        ArrayList<Produit> list = new ArrayList<>();

       
            String requete = "SELECT * FROM Produit";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                list.add(new Produit(rs.getInt("id"),rs.getString("prix_produit"),rs.getString("quantite_produit"),rs.getString("desc_produit"),rs.getString("image"),rs.getString("nom_produit")) );
                
            }

        

        return list;
    }

    public void supprimer(int id) {
 try {
            String requete = "DELETE FROM Produit WHERE id=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println(" supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }
    public boolean Modifier(int id, String prix_produit, String quantite_produit, String desc_produit, String image, String nom_produit) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE produit SET prix_produit= '" + prix_produit + "' , quantite_produit= '" + quantite_produit + "' ,desc_produit= '" + desc_produit + "'  ,image= '" + image + "'  ,nom_produit= '" + nom_produit + "' WHERE id='" + id + "' ;");

        JOptionPane.showMessageDialog(null, "Product modifiée avec succées");
        pre.executeUpdate();

        return true;
    }
   /* public void Modifier(int id ,Produit P) {
        
        String requete = "UPDATE `produit` SET" 
                    + "`prix_produit`=?,`quantite_produit`=?,`desc_produit`=?,`image`=?,`nom_produit`=? WHERE id="+ id;
           
        try {
             PreparedStatement pst = con.prepareStatement(requete);
            //pst.setInt(1, P.getId());
            pst.setString(1, P.getPrix_produit());
            pst.setString(2, P.getQuantite_produit());
            pst.setString(3, P.getDesc_produit());
            pst.setString(4, P.getImage());
            pst.setString(5, P.getNom_produit());
            
            pst.executeUpdate();
            System.out.println("Produit modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }*/
}

