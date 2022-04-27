/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author oaca
 */
public class Vehicule {
   private int id ;
    private String matricule,couleur,type_vehicule,marque,etat_vehicule;
    private Date date_entretient;

    public Vehicule(int id, String matricule, String couleur, String type_vehicule, String marque, String etat_vehicule, Date date_entretient) {
        this.id = id;
        this.matricule = matricule;
        this.couleur = couleur;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat_vehicule = etat_vehicule;
        this.date_entretient = date_entretient;
    }

    public Vehicule(String matricule, String couleur, String type_vehicule, String marque, String etat_vehicule, Date date_entretient) {
        this.matricule = matricule;
        this.couleur = couleur;
        this.type_vehicule = type_vehicule;
        this.marque = marque;
        this.etat_vehicule = etat_vehicule;
        this.date_entretient = date_entretient;
    }

    public Vehicule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getType_vehicule() {
        return type_vehicule;
    }

    public void setType_vehicule(String type_vehicule) {
        this.type_vehicule = type_vehicule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getEtat_vehicule() {
        return etat_vehicule;
    }

    public void setEtat_vehicule(String etat_vehicule) {
        this.etat_vehicule = etat_vehicule;
    }

    public Date getDate_entretient() {
        return date_entretient;
    }

    public void setDate_entretient(Date date_entretient) {
        this.date_entretient = date_entretient;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "id=" + id + ", matricule=" + matricule + ", couleur=" + couleur + ", type_vehicule=" + type_vehicule + ", marque=" + marque + ", etat_vehicule=" + etat_vehicule + ", date_entretient=" + date_entretient + '}';
    }

  

}
