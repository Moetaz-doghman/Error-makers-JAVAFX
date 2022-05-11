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

//import Config.SMSApi;
import entity.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.Myconnexion;

/**
 *
 * @author Aicha
 */
public class ProduitService {

    Connection con = Myconnexion.getInstance().getCnx();
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
        
//SMSApi.sendSMS("Nous avons ajouté un produit"+P.getNom_produit().toString()+
//        "\n" +P.getDesc_produit().toString()+" \n avec un prix de "+P.getPrix_produit().toString()+
//                        " \n disponible en une quantité de "+P.getQuantite_produit().toString());
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
    public Produit details(int id) throws SQLException {

        String requete = "select * from produit where id ='" + id + "'";

        Produit P = null;

        try {
           /* ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);*/
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                P=(new Produit(rs.getInt("id"),rs.getString("prix_produit"),rs.getString("quantite_produit"),rs.getString("desc_produit"),rs.getString("image"),rs.getString("nom_produit")) );
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return P;
    }
    
    public HashMap<String, Double> StatistiqueParQT() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = con.createStatement();
            String query = "SELECT quantite_produit, COUNT(*) as nb , nom_produit  FROM produit GROUP BY nom_produit;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key =  rs.getString("nom_produit");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
}
    
      public ArrayList<Produit> findbyboutique(int id)throws SQLException {
        ArrayList<Produit> list = new ArrayList<>();

       
            String requete = "SELECT * FROM Produit where boutique_id="+id;
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                list.add(new Produit(rs.getInt("id"),rs.getString("prix_produit"),rs.getString("quantite_produit"),rs.getString("desc_produit"),rs.getString("image"),rs.getString("nom_produit")) );
                
            }

        

        return list;
    }
}

