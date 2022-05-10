/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Evenement {
    private int id;
    private String nom;
    private Double prix;
    private String adresse;
    private Date date;
    private Date datefin;
    private String description;
    private String affiche;
    private String longdesc;

    public Evenement() {
    }

    public Evenement(String nom, Double prix, String adresse, Date date, Date datefin, String description, String affiche, String longdesc) {
        this.id = 0;
        this.nom = nom;
        this.prix = prix;
        this.adresse = adresse;
        this.date = date;
        this.datefin = datefin;
        this.description = description;
        this.affiche = affiche;
        this.longdesc = longdesc;
    }
    public Evenement(int id,String nom, Double prix, String adresse, Date date, Date datefin, String description, String affiche, String longdesc) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.adresse = adresse;
        this.date = date;
        this.datefin = datefin;
        this.description = description;
        this.affiche = affiche;
        this.longdesc = longdesc;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getLongdesc() {
        return longdesc;
    }

    public void setLongdesc(String longdesc) {
        this.longdesc = longdesc;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", adresse=" + adresse + ", date=" + date + ", datefin=" + datefin + ", description=" + description + ", affiche=" + affiche + ", longdesc=" + longdesc + '}';
    }
    
    
}
