/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HP
 */
public class Produit {
    private int id ;
    private String prix_produit;
    private String quantite_produit ;
    private String desc_produit,image,nom_produit;

    public Produit(int id, String prix_produit, String quantite_produit, String desc_produit, String image, String nom_produit) {
        this.id = id;
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.desc_produit = desc_produit;
        this.image = image;
        this.nom_produit = nom_produit;
    }

    public Produit(String prix_produit, String quantite_produit, String desc_produit, String image, String nom_produit) {
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.desc_produit = desc_produit;
        this.image = image;
        this.nom_produit = nom_produit;
    }

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(String prix_produit) {
        this.prix_produit = prix_produit;
    }

    public String getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(String quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public String getDesc_produit() {
        return desc_produit;
    }

    public void setDesc_produit(String desc_produit) {
        this.desc_produit = desc_produit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", prix_produit=" + prix_produit + ", quantite_produit=" + quantite_produit + ", desc_produit=" + desc_produit + ", image=" + image + ", nom_produit=" + nom_produit + '}';
    }


    
    
    
    
}
