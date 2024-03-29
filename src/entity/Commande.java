/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author doghm
 */
public class Commande {
    private int idcommande;
    private String nom_client, prenom_client, telephone, adresse, montant;
    private int etat_commande , mode_paiement ;
    Date dateCommande;

    public Commande(String nom_client, Date dateCommande, String adresse, String montant) {
         this.nom_client = nom_client;
   
        this.adresse = adresse;
        this.montant = montant;
       this.dateCommande = dateCommande;
    }
           

 

    public Commande(String nom, String prenom, String phone, Date DateLyoum, String adresse, String montant, int etat_commande, int mode_paiement) {
       this.nom_client = nom;
        this.prenom_client = prenom;
        this.telephone = phone;
        this.adresse = adresse;
        this.montant = montant;
        this.etat_commande = etat_commande;
        this.mode_paiement = mode_paiement;
        this.dateCommande = DateLyoum;
    }

 
    
 

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }


    public Commande() {
    }

    public Commande(int idcommande, int etat_commande) {
        this.idcommande = idcommande;
        this.etat_commande = etat_commande;
    }

    

    public Commande(int idcommande, String nom_client, String prenom_client, String telephone, String adresse, String montant, int mode_paiement, int etat_commande) {
        this.idcommande = idcommande;
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.telephone = telephone;
        this.adresse = adresse;
        this.montant = montant;
        this.mode_paiement = mode_paiement;
        this.etat_commande = etat_commande;
    }
    

    public Commande(String nom_client, String prenom_client, String telephone, String adresse, String montant, int mode_paiement, int etat_commande) {
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.telephone = telephone;
        this.adresse = adresse;
        this.montant = montant;
        this.mode_paiement = mode_paiement;
        this.etat_commande = etat_commande;
    }

    
    

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

  

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
    

    public int getMode_paiement() {
        return mode_paiement;
    }

    public void setMode_paiement(int mode_paiement) {
        this.mode_paiement = mode_paiement;
    }

    public int getEtat_commande() {
        return etat_commande;
    }

    public void setEtat_commande(int etat_commande) {
        this.etat_commande = etat_commande;
    }

    @Override
    public String toString() {
        return "Commande{" + "idcommande=" + idcommande + ", nom_client=" + nom_client + ", prenom_client=" + prenom_client + ", telephone=" + telephone + ", adresse=" + adresse + ", montant=" + montant + ", mode_paiement=" + mode_paiement + ", etat_commande=" + etat_commande + '}';
    }

    

    
    
    
    
    
}
