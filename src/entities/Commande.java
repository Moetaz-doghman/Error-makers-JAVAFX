/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author doghm
 */
public class Commande {
    private int idcommande;
    private String nom_client, prenom_client,  adresse, montant,postcode;
    private int etat_commande , mode_paiement ;

    public Commande() {
    }

    public Commande(int idcommande, String nom_client, String prenom_client, String adresse, String montant, String postcode, int etat_commande, int mode_paiement) {
        this.idcommande = idcommande;
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.adresse = adresse;
        this.montant = montant;
        this.postcode = postcode;
        this.etat_commande = etat_commande;
        this.mode_paiement = mode_paiement;
    }

    public Commande(String nom_client, String prenom_client, String adresse, String montant, String postcode, int etat_commande, int mode_paiement) {
        this.nom_client = nom_client;
        this.prenom_client = prenom_client;
        this.adresse = adresse;
        this.montant = montant;
        this.postcode = postcode;
        this.etat_commande = etat_commande;
        this.mode_paiement = mode_paiement;
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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getEtat_commande() {
        return etat_commande;
    }

    public void setEtat_commande(int etat_commande) {
        this.etat_commande = etat_commande;
    }

    public int getMode_paiement() {
        return mode_paiement;
    }

    public void setMode_paiement(int mode_paiement) {
        this.mode_paiement = mode_paiement;
    }

    @Override
    public String toString() {
        return "Commande{" + "idcommande=" + idcommande + ", nom_client=" + nom_client + ", prenom_client=" + prenom_client + ", adresse=" + adresse + ", montant=" + montant + ", postcode=" + postcode + ", etat_commande=" + etat_commande + ", mode_paiement=" + mode_paiement + '}';
    }

    
  
    
    
    
}