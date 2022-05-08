/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author oaca
 */
public class Livraison {
    private int id,livreur_id,commande_id,vehicule_id;
    private String etat_livraison;
    private Date date_livraison;
    private String prix_livraison;
    private Date fin_livraison;
   
    
    

    public Livraison() {
    }

    public Livraison(int id, int livreur_id, int commande_id, int vehicule_id, String etat_livraison, Date date_livraison, String prix_livraison, Date fin_livraison) {
        this.id = id;
        this.livreur_id = livreur_id;
        this.commande_id = commande_id;
        this.vehicule_id = vehicule_id;
        this.etat_livraison = etat_livraison;
        this.date_livraison = date_livraison;
        this.prix_livraison = prix_livraison;
        this.fin_livraison = fin_livraison;
    }

    public Livraison(int livreur_id, int commande_id, int vehicule_id, String etat_livraison, Date date_livraison, String prix_livraison, Date fin_livraison) {
        this.livreur_id = livreur_id;
        this.commande_id = commande_id;
        this.vehicule_id = vehicule_id;
        this.etat_livraison = etat_livraison;
        this.date_livraison = date_livraison;
        this.prix_livraison = prix_livraison;
        this.fin_livraison = fin_livraison;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivreur_id() {
        return livreur_id;
    }

    public void setLivreur_id(int livreur_id) {
        this.livreur_id = livreur_id;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public int getVehicule_id() {
        return vehicule_id;
    }

    public void setVehicule_id(int vehicule_id) {
        this.vehicule_id = vehicule_id;
    }

    public String getEtat_livraison() {
        return etat_livraison;
    }

    public void setEtat_livraison(String etat_livraison) {
        this.etat_livraison = etat_livraison;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public String getPrix_livraison() {
        return prix_livraison;
    }

    public void setPrix_livraison(String prix_livraison) {
        this.prix_livraison = prix_livraison;
    }

    public Date getFin_livraison() {
        return fin_livraison;
    }

    public void setFin_livraison(Date fin_livraison) {
        this.fin_livraison = fin_livraison;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", livreur_id=" + livreur_id + ", commande_id=" + commande_id + ", vehicule_id=" + vehicule_id + ", etat_livraison=" + etat_livraison + ", date_livraison=" + date_livraison + ", prix_livraison=" + prix_livraison + ", fin_livraison=" + fin_livraison + '}';
    }


    
}
