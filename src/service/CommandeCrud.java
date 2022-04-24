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
public class CommandeCrud implements ICommande<Commande> {

    public String MaxUsedAdresse() {
        String adresse="";
        try {
            String requete = "SELECT MAX(adresse) FROM commande   ";
            Statement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                adresse=rs.getString(1);
                System.out.println("adresse"+adresse);
                break;
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return adresse;
    }

    @Override
    public boolean AjouterCommande(Commande t) {
        try {
            String requete = "insert into commande(nom_client,date_commande,adresse,montant)"+"values('"+t.getNom_client()+"','"+t.getDateCommande()+"','"+t.getAdresse()+"','"+t.getMontant()+"')";
            
            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate(requete);            

          
            System.out.println(requete);
            
            System.out.println("Commande ajouté!");
            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
        
      
    }
   
    
    @Override
    public boolean ModifierCommande(Commande t) {
        
        try {
            String requete = "UPDATE commande SET etat_commande=? WHERE id=?";
            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, t.getEtat_commande());
            pst.setInt(2, t.getIdcommande());
            pst.executeUpdate();
            System.out.println("Categorie été modifiée");
            System.out.print(requete);

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }
  

    @Override
    public boolean SupprimerCommande(int id) {
        try {
            String requete = "DELETE FROM commande where id=" + String.valueOf(id) + "";
            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);
            System.out.println("Categorie supprimée");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public List<Commande> AfficherCommande(Commande c) {
        List<Commande> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM commande ";
            Statement pst = Myconnexion.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Commande r = new Commande();
                r.setIdcommande(rs.getInt("id"));
                r.setNom_client(rs.getString("nom_client"));
                r.setPrenom_client(rs.getString("prenom_client"));
               r.setTelephone(rs.getString("phone"));
                r.setAdresse(rs.getString("adresse"));
                 r.setMontant(rs.getString("montant"));
                 r.setMode_paiement(rs.getInt("mode_paiemenet"));
                 r.setEtat_commande(rs.getInt("etat_commande"));
                r.setDateCommande(rs.getDate("date_commande"));


                
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return list;
    }

    @Override
    public int countTotalCommande() {
        String req = "SELECT COUNT(*) as cu FROM commande  ";
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
