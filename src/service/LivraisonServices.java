/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Commande;
import entity.Livraison;
import entity.User;
import entity.Vehicule;
import java.sql.Connection;
import java.util.Date;
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
 * @author oaca
 */
public class LivraisonServices {
     private Connection cnx;
    private Statement ste;
    private PreparedStatement pre;

    public LivraisonServices() {
        cnx = Myconnexion.getInstance().getCnx();
    }

    public ArrayList<Livraison> DisplayAll() throws SQLException {
        ArrayList<Livraison> TabF = new ArrayList<>();
        String req = "SELECT * FROM livraison";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

            TabF.add(new Livraison(rs.getInt("id"), rs.getInt("livreur_id"), rs.getInt("commande_id"), rs.getInt("vehicule_id"), rs.getString("etat_livraison"), rs.getDate("date_livraison"), rs.getString("prix_livraison"), rs.getDate("fin_livraison")));
        }

        return TabF;
    }
    public void ajouter(Livraison rv) throws SQLException {

       
        pre = cnx.prepareStatement("INSERT INTO livraison  (`livreur_id`,`commande_id`,`vehicule_id`,`etat_livraison`,`date_livraison`,`prix_livraison`,`fin_livraison`) VALUES (?,?,?,?,?,?,?) ;");

            //System.out.println(rv.getVoiture().getIdvoit()+"  " +rv.getUser().getIdu());
           java.sql.Date d=new java.sql.Date(rv.getDate_livraison().getTime());
           java.sql.Date d1=new java.sql.Date(rv.getFin_livraison().getTime());

                           pre.setInt(1, rv.getLivreur_id());
                           pre.setInt(2, rv.getCommande_id());
                           pre.setInt(3, rv.getVehicule_id()); 
                           pre.setString(4, rv.getEtat_livraison());
                           
                           pre.setDate(5,d);
                           pre.setString(6, rv.getPrix_livraison());
                           pre.setDate(7,d1);
       
   
        
            pre.executeUpdate();
            System.out.println("Livraison  crée avec succes");

        } 

        public boolean modifier(int id, int livreur_id, int commande_id, int vehicule_id, String etat_livraison, Date date_livraison, String prix_livraison, Date fin_livraison) throws SQLException {
       PreparedStatement pre = cnx.prepareStatement("UPDATE livraison SET livreur_id= '" + livreur_id + "' , commande_id= '" + commande_id + "' ,vehicule_id= '" + vehicule_id + "'  ,etat_livraison= '" + etat_livraison + "'  ,date_livraison= '" + date_livraison + "' ,prix_livraison= '" + prix_livraison + "' ,fin_livraison= '" + fin_livraison + "' WHERE id='" + id + "' ;");

            JOptionPane.showMessageDialog(null, "Livraison modifiée avec succées");
        pre.executeUpdate();

        return true;
    
    }
         public void supprimerL(int id) {
         try {
             String requete = "DELETE FROM livraison  WHERE id =?";
       PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
                        System.out.println(" supprimée !");


        } catch (SQLException ex) {

            System.err.println(ex.getMessage());

        }
        
    }
         
         public ArrayList<Livraison> afficher() throws SQLException {
        ArrayList<Livraison> TabF = new ArrayList<>();
        String req = "SELECT * FROM livraison";
        PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

            TabF.add(new Livraison(rs.getInt("id"), rs.getInt("livreur_id"), rs.getInt("commande_id"), rs.getInt("vehicule_id"), rs.getString("etat_Livraison"),rs.getDate("date_livraison") , rs.getString("prix_livraison"), rs.getDate("fin_livraison")) );
        }

        return TabF;
    }
              public ArrayList findCatrgory() throws SQLException {
        ArrayList arr = new ArrayList();
        ste = cnx.createStatement();

        ResultSet rs = ste.executeQuery("select id,matricule,couleur,type_vehicule,marque,date_entretient,etat_vehicule from vehicule  ");
        while (rs.next()) {

            int id = rs.getInt("id");
            String Matricule = rs.getString("matricule");
            String couleur = rs.getString("couleur");
            String type_vehicule = rs.getString("type_vehicule");
          String  marque = rs.getString("marque");
          String etat_vehicule = rs.getString("etat_vehicule");
          Date date_entretient = rs.getDate("date_entretient");

            Vehicule f = new Vehicule(id, Matricule, couleur, type_vehicule,marque,etat_vehicule,date_entretient);
            arr.add(f);
        }
        return arr;

    }
              
        public ArrayList findCatrgoory() throws SQLException {
        ArrayList arr = new ArrayList();
        ste = cnx.createStatement();

        //ResultSet rs = ste.executeQuery("select * from  utilisateurs where role = [\"ROLE_LIV\",\"ROLE_USER\"]  ");
    //    ResultSet rs = ste.executeQuery("select * from  utilisateurs where role
        ResultSet rs = ste.executeQuery("SELECT * FROM utilisateurs  WHERE role ='"+"[\"ROLE_LIV\",\"ROLE_USER\"]"+ "' ");

        while (rs.next()) {

            int id = rs.getInt("id");
            int boutique_id = rs.getInt("boutique_id");
            String telephone = rs.getString("telephone");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            String  password = rs.getString("password");
            String question_securite1 = rs.getString("question_securite1");
            String question_securite2 = rs.getString("question_securite2");
            String etat = rs.getString("etat");
            String image = rs.getString("image");
            String role = rs.getString("role");


           // User f = new User(id, , telephone, nom,prenom,email,password,role,question_securite1,question_securite2,etat,image);
           User f = new User(id, nom, prenom ,role, email,telephone,password);
            arr.add(f);
        }
        return arr;

    }
                  
        public ArrayList findCatrgooory() throws SQLException {
        ArrayList arr = new ArrayList();
        ste = cnx.createStatement();

        ResultSet rs = ste.executeQuery("select * from commande");
        while (rs.next()) {

            int idcommande = rs.getInt("id");
            
          String nom_client = rs.getString("nom_client");
          String prenom_client = rs.getString("prenom_client");
          String adresse = rs.getString("adresse");
          String  postcode = rs.getString("postcode");
          int etat_commande = rs.getInt("etat_commande");
          int mode_paiement = rs.getInt("mode_paiemenet");
        
                      
            String montant = rs.getString("montant");
            


            Commande f = new Commande(idcommande, nom_client, prenom_client, adresse,montant,postcode,etat_commande,mode_paiement);
            arr.add(f);
        }
        return arr;

    }
        public ArrayList<Livraison> triParPrix() throws SQLException {
        ArrayList<Livraison> TabM = new ArrayList<>();
        String req = "SELECT * FROM livraison order by  prix_livraison Desc";
      PreparedStatement p;
        p = cnx.prepareStatement(req);
        ResultSet rs = p.executeQuery();
        while (rs.next()) {

            TabM.add(new Livraison(rs.getInt("id"), rs.getInt("livreur_id"), rs.getInt("commande_id"), rs.getInt("vehicule_id"), rs.getString("etat_Livraison"),rs.getDate("date_livraison") , rs.getString("prix_livraison"), rs.getDate("fin_livraison")) );
        }
        return TabM;
    }
    
        
    }

