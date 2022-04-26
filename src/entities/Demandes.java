/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author skanderzouaoui
 */
public class Demandes {
    private int id ;
    private String nom,prenom,role,email,telephone,password;

    public Demandes() {
    }
    
    public Demandes(int id, String nom, String prenom, String role, String email, String telephone, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
    }

    public Demandes(String nom, String prenom, String role, String email, String telephone, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Demandes{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", role=" + role + ", email=" + email + ", telephone=" + telephone + ", password=" + password + '}';
    }

   

    
    
    
    
    
}
