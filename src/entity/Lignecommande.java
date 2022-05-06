/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author doghm
 */
public class Lignecommande {
    private int id, idcommande, idproduit, quantite;

    public Lignecommande() {
    }

    public Lignecommande(int idcommande, int idproduit, int quantite) {
        this.idcommande = idcommande;
        this.idproduit = idproduit;
        this.quantite = quantite;
    }

    public Lignecommande(int id, int idcommande, int idproduit, int quantite) {
        this.id = id;
        this.idcommande = idcommande;
        this.idproduit = idproduit;
        this.quantite = quantite;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Lignecommande{" + "id=" + id + ", idcommande=" + idcommande + ", idproduit=" + idproduit + ", quantite=" + quantite + '}';
    }

    
    

    
    
    
}
