/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author oaca
 */
public class Utilisateur {
    private int id,boutique_id,telephone;
    private String nom,prenom,email,password,question_securite1,question_securite2,etat,image;
    private String role;

    public Utilisateur() {
    }

    public Utilisateur(int id, int boutique_id, int telephone, String nom, String prenom, String email, String password, String question_securite1, String question_securite2, String etat, String image, String role) {
        this.id = id;
        this.boutique_id = boutique_id;
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.question_securite1 = question_securite1;
        this.question_securite2 = question_securite2;
        this.etat = etat;
        this.image = image;
        this.role = role;
    }

    public Utilisateur(int boutique_id, int telephone, String nom, String prenom, String email, String password, String question_securite1, String question_securite2, String etat, String image, String role) {
        this.boutique_id = boutique_id;
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.question_securite1 = question_securite1;
        this.question_securite2 = question_securite2;
        this.etat = etat;
        this.image = image;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoutique_id() {
        return boutique_id;
    }

    public void setBoutique_id(int boutique_id) {
        this.boutique_id = boutique_id;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestion_securite1() {
        return question_securite1;
    }

    public void setQuestion_securite1(String question_securite1) {
        this.question_securite1 = question_securite1;
    }

    public String getQuestion_securite2() {
        return question_securite2;
    }

    public void setQuestion_securite2(String question_securite2) {
        this.question_securite2 = question_securite2;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", boutique_id=" + boutique_id + ", telephone=" + telephone + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", question_securite1=" + question_securite1 + ", question_securite2=" + question_securite2 + ", etat=" + etat + ", image=" + image + ", role=" + role + '}';
    }
    
}
