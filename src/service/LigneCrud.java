/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.Myconnexion;
import service.ICommande;
import entity.Categorie;
import entity.Commande;
import entity.Lignecommande;
import java.sql.PreparedStatement;//If you want to execute a Statement object many times, it usually reduces execution time to use a PreparedStatement object instead.
import java.sql.ResultSet;//A ResultSet object maintains a cursor pointing to its current row of data
import java.sql.SQLException;
import java.sql.Statement;//The object used for executing a static SQL statement and returning the results it produces.
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rania
 */
public class LigneCrud implements ILigne<Lignecommande> {

  

    @Override
    public boolean AjouterligneCommande(Lignecommande c) {
          try {
            //String requete = "insert into ligne_commande(produit_id,commande_id,quantite)"+"values('"c.getIdproduit()+"','"+c.getIdcommande()+"','"+c.getQuantite()+"')";
        String requete = "insert into ligne_commande(produit_id,commande_id,quantite)"+"values('"+c.getIdproduit()+"','"+c.getIdcommande()+"','"+c.getQuantite()+"')";

            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate(requete);            

          
            System.out.println(requete);
            
            System.out.println("Ligne commande ajouté!");
            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

       @Override
    public int countTotalCommande() {
        String req = "SELECT COUNT(*) as cu FROM ligne_commande  ";
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

    @Override
    public boolean SupprimerLigne(int id) {
    try {
            String requete = "DELETE FROM ligne_commande where id=" + String.valueOf(id) + "";
            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);
            System.out.println("Ligne supprimée");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;    
    }

    @Override
    public List<Lignecommande> AfficherLigne(Lignecommande c) {
     List<Lignecommande> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM ligne_commande ";
            Statement pst = Myconnexion.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Lignecommande r = new Lignecommande();
                r.setId(rs.getInt("id"));
                r.setIdproduit(rs.getInt("produit_id"));
                r.setIdcommande(rs.getInt("commande_id"));
               r.setQuantite(rs.getInt("quantite"));
              


                
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return list;    }
    
     public String Maxusedproduct() {
        String produit="";
        try {
            String requete = "SELECT MAX(produit_id) FROM ligne_commande   ";
            Statement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                produit=rs.getString(1);
               // System.out.println("adresse"+adresse);
                break;
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return produit;
    }

 
    

      

}
